package com.wangdi.molecularcraft.tileentity;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockEnergyConduit;
import com.wangdi.molecularcraft.block.BlockFissionReactor;
import com.wangdi.molecularcraft.inventory.ContainerFissionReactor;
import com.wangdi.molecularcraft.item.ItemBucketNuclearContaminatedWater;
import com.wangdi.molecularcraft.item.ItemContainer;
import com.wangdi.molecularcraft.item.ModItems;
import com.wangdi.molecularcraft.item.crafting.FissionReactorRecipes;
import com.wangdi.molecularcraft.item.crafting.ModRecipes;
import com.wangdi.util.EnergyContainer;
import com.wangdi.util.ItemStackHandlerHelper;
import com.wangdi.util.MainUtil;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

/**
 *  ItemStack
 *  controlRodItemStack = this.getStackInSlot(0)
 *  triggerItemStack = this.getStackInSlot(1)
 *  inputItemStack = this.getStackInSlot(2)
 *  coolingLiquidItemStack = this.getStackInSlot(3)
 *  generatorItemStack = this.getStackInSlot(4)
 *  storageItemStack = this.getStackInSlot(5)
 */
public final class TileEntityFissionReactor extends TileEntityLockable implements ITickable, ISidedInventory, EnergyContainer
{
    public static final int ITEMSTACKS_SIZE = 13;
    public static final String CUSTOME_NAME = "container.fission_reactor";

    private static final int MAX_REACTOR_HEIGHT = 58;
    private static final int[] SLOTS_TOP = {2};
    private static final int[] SLOTS_SIDES = {1};
    private static final int[] SLOTS_BOTTOM = MainUtil.continuallyIntArray(6, ITEMSTACKS_SIZE - 1);

    @SideOnly(Side.CLIENT)
    public static boolean isWorking(TileEntityFissionReactor tileEntity)
    {
        return tileEntity.getField(1) > 0;
    }

    private NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(ITEMSTACKS_SIZE, ItemStack.EMPTY);

    private boolean isActivate;
    private int workNeedTime = 10000; // the time for work 1 mol Item
    private int currentTime = 0; // the time count for work
    private double currentEnergy = 0.0; // currentEnergy store in the container
    private double currentEnergyChange = 0.0;
    private String customName;

    // used for gui
    private int reactorAnimation = 0;

    public TileEntityFissionReactor()
    {
        
    }

    @Override
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public void setCustomInventoryName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : CUSTOME_NAME;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation(CUSTOME_NAME);
    }

    // read the data
    @Override
    @SuppressWarnings("null")
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        this.itemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.itemStacks);

        this.isActivate = compound.getBoolean("IsActivated");
        this.currentTime = compound.getInteger("CurrentTime");
        this.currentEnergy = compound.getDouble("CurrentEnergy");
        this.currentEnergyChange = compound.getDouble("currentEnergyChange");
        this.setCustomName(compound.hasKey("CustomName", 8) ? compound.getString("CustomName") : CUSTOME_NAME);

        this.reactorAnimation = compound.getInteger("reactorAnimation");
        this.updateWork();
    }

    //save the data
    @Override
    @SuppressWarnings("null")
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        compound.setBoolean("IsActivated", this.isActivate);
        compound.setInteger("WorkNeedTime", this.workNeedTime);
        compound.setInteger("CurrentTime", this.currentTime);
        compound.setDouble("CurrentEnergy", this.currentEnergy);
        compound.setDouble("currentEnergyChange", this.currentEnergyChange);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);

        if (this.hasCustomName())
            compound.setString("CustomName", this.customName);

        compound.setInteger("reactorAnimation", this.reactorAnimation);
        return compound;
    }

    // the machine is working or not
    public boolean isWorking()
    {
        return this.currentTime > 0;
    }

    // have control rod or not
    public boolean isControlled()
    {
        return FissionReactorRecipes.isValidControl(this.getStackInSlot(0).getItem());
    }

    public boolean hasTrigger()
    {
        return FissionReactorRecipes.isValidTrigger(this.getStackInSlot(1).getItem());
    }

    public boolean hasRawMaterialInput()
    {
        return FissionReactorRecipes.isValidInput(this.getStackInSlot(2).getItem());
    }

    public boolean hasLiquidCooling()
    {
        return FissionReactorRecipes.isValidCoolLiquid(this.getStackInSlot(3).getItem());
    }

    // can generate energy or not
    public boolean hasGenerator()
    {
        return FissionReactorRecipes.isValidGenerator(this.getStackInSlot(4).getItem());
    }

    // used to update all items in the item slot.
    @Override
    public void update()
    {
        boolean flag = this.isWorking();

        if (!this.world.isRemote)
        {
            this.updateWork();
                
            // no control rod
            if (this.isWorking() && (!this.isControlled() || !this.hasLiquidCooling()))
            {
                
            }

            // transfer energy
            if (this.currentEnergy > 0)
            {
                EnumFacing current = this.world.getBlockState(this.pos).getValue(BlockHorizontal.FACING);
                BlockPos neighborPos = this.pos.offset(current.rotateY().getOpposite());
                TileEntity neighborTileEntity = this.world.getTileEntity(neighborPos);

                if (neighborTileEntity instanceof TileEntityEnergyConduit)
                {
                    TileEntityEnergyConduit tileEntity = (TileEntityEnergyConduit)(neighborTileEntity);
                    double conduitEnergy = tileEntity.getCurrentEnergy();
                    double remainUncharged = tileEntity.getRemainingUncharged();
                    double energyTransmit = MainUtil.min(remainUncharged, this.currentEnergy, BlockEnergyConduit.TRANSMISSION_RATE);

                    if (!((TileEntityEnergyConduit)(neighborTileEntity)).isFullCharged())
                    {
                        conduitEnergy += energyTransmit;
                        this.currentEnergy -= energyTransmit;

                        if (tileEntity.isEnergyOverflow())
                            conduitEnergy = tileEntity.getMaxStoredEnergy();
                            
                        tileEntity.setCurrentEnergy(conduitEnergy);
                    }
                }
            }

            if (this.isWorking())
            {
                ItemStack storageItemStack = this.getStackInSlot(5).copy();
                Item storageItem = storageItemStack.getItem();

                this.isActivate = true;
                this.currentTime++;
                this.reactorAnimation++;

                if (this.reactorAnimation > MAX_REACTOR_HEIGHT)
                    this.reactorAnimation = MAX_REACTOR_HEIGHT;

                if (this.hasGenerator() && this.hasLiquidCooling())
                {
                    double increment = this.currentEnergyChange / FissionReactorRecipes.DEFAULT_TIME;

                    if (!FissionReactorRecipes.isValidEnergyStorage(storageItem) || ((ItemContainer.ItemEnergyBattery)(storageItem)).isFull(storageItemStack))
                        this.currentEnergy += increment;
                    else
                        ((ItemContainer.ItemEnergyBattery)(storageItem)).grow(this.getStackInSlot(5), increment);

                    if (this.isFullCharged())
                        this.currentEnergy = BlockFissionReactor.MAX_STORE_ENERGY;
                }
                
                // output result
                if (this.currentTime == this.workNeedTime)
                {
                    this.currentTime = 0;
                    ItemStack[] decomposeResult = ModRecipes.FISSION_REACTOR.getResult(this.getStackInSlot(2).copy());

                    if (decomposeResult != null && ItemStackHandlerHelper.canAddItems(this.itemStacks, 6, decomposeResult))
                    {
                        for (int i = 0; i < decomposeResult.length - 1; i++)
                            ItemStackHandlerHelper.addItemToHandler(this.itemStacks, decomposeResult[i], 6);
                    }
                }

                // increase
                if (this.getStackInSlot(3).getItem().getClass().equals(Items.WATER_BUCKET.getClass()))
                {
                    ItemStack newItemStack = new ItemStack(ModItems.BUCKET_NUCLEAR_CONTAMINATED_WATER);
                    Item newItem = newItemStack.getItem();
                    this.itemStacks.set(3,newItemStack);

                    if (newItem.getClass().equals(ItemBucketNuclearContaminatedWater.class))
                        ((ItemBucketNuclearContaminatedWater)(newItem)).grow(newItemStack, FissionReactorRecipes.DEFAULT_RADIATION_EMIT);
                }
                else
                {
                    ItemStack itemStack = this.getStackInSlot(3);
                    Item item = itemStack.getItem();
                    
                    if (item.getClass().equals(ItemBucketNuclearContaminatedWater.class))
                        ((ItemBucketNuclearContaminatedWater)(item)).grow(itemStack, FissionReactorRecipes.DEFAULT_RADIATION_EMIT);
                }
            }
            // when it is working
            else if ((this.isActivate || this.hasTrigger()) && this.hasRawMaterialInput())
            {
                ItemStack triggerItemStack = this.getStackInSlot(1);
                ItemStack inputItemStack = this.getStackInSlot(2);
                ItemStack[] decomposeResultCopy = ModRecipes.FISSION_REACTOR.getResult(this.getStackInSlot(2).copy());

                if (ItemStackHandlerHelper.canAddItems(this.itemStacks, 6, decomposeResultCopy))
                {
                    if (!this.isActivate)
                        triggerItemStack.shrink(1);

                    this.isActivate = true;
                    this.currentTime++;
                    inputItemStack.shrink(1);
                }
                else
                {
                    this.isActivate = false;
                    this.reactorAnimation--;

                    if (this.reactorAnimation < 0)
                        this.reactorAnimation = 0;
                }
            }
            else
            {
                this.isActivate = false;
                this.reactorAnimation--;

                if (this.reactorAnimation < 0)
                    this.reactorAnimation = 0;
            }

            if (flag != this.isWorking())
                BlockFissionReactor.setState(this.isWorking(), world, pos);

            this.markDirty();
        }
    }

    @Override
    @SuppressWarnings("null")
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double)(this.pos.getX() + 0.5), (double)(this.pos.getY() + 0.5), (double)(this.pos.getZ()) + 0.5) <= 64.0;
    }

    @Override
    public void setCurrentEnergy(double currentEnergy)
    {
        this.currentEnergy = currentEnergy;   
    }

    @Override
    public double getCurrentEnergy()
    {
        return this.currentEnergy;
    }

    @Override
    public double getMaxStoredEnergy()
    {
        return BlockFissionReactor.MAX_STORE_ENERGY;
    }

    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0: {this.workNeedTime = value; return;}
            case 1: {this.currentTime = value; return;}
            case 2: {this.currentEnergy = (double)(value); return;}
            case 3: {this.reactorAnimation = value; return;}
            default: return;
        }
    }

    @Override
    public int getField(int index)
    {
        switch (index)
        {
            case 0: {return this.workNeedTime;}
            case 1: {return this.currentTime;}
            case 2: {return (int)(this.currentEnergy);}
            case 3: {return this.reactorAnimation;}
            default: return 0;
        }
    }

    @Override
    @SuppressWarnings("null")
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        Item stackItem = stack.getItem();

        switch (index)
        {
            case 0: return FissionReactorRecipes.isValidControl(stackItem);
            case 1: return FissionReactorRecipes.isValidTrigger(stackItem);
            case 2: return FissionReactorRecipes.isValidInput(stackItem);
            case 3: return FissionReactorRecipes.isValidCoolLiquid(stackItem);
            case 4: return FissionReactorRecipes.isValidGenerator(stackItem);
            case 5: return FissionReactorRecipes.isValidEnergyStorage(stackItem);
            default: return false;
        }
    }

    // the size(slot) the block has
    @Override
    public int getSizeInventory()
    {
        return this.itemStacks.size();
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.itemStacks, index);
    }

    // Used to reduce the number of items in a specified slot and return the reduced stack of items.
    // Typically used to handle item reduction operations in containers, such as players removing items from containers.
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.itemStacks, index, count);
    }

    // check all the slots are all empty
    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.itemStacks)
        {
            if (!itemstack.isEmpty())
                return false;
        }

        return true;
    }

    // specifies the slots on a face (e.g. the input or output face of a machine especially things like hopper) 
    // that allows item interaction
    @Override
    @SuppressWarnings("null")
    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
            return SLOTS_BOTTOM;
        else
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
    }

    // build a container object associated with a TileEntity
    @Override
    @SuppressWarnings("null")
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerFissionReactor(playerInventory, this);
    }

     // do some thing when open this container
    @Override
    @SuppressWarnings("null")
    public void openInventory(EntityPlayer player)
    {

    }
    
    // do some thing when close this container
    @Override
    @SuppressWarnings("null")
    public void closeInventory(EntityPlayer player)
    {

    }

    // the max in the stack
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    // used to empty all items in the item slot.
    @Override
    public void clear()
    {
        this.itemStacks.clear();
    }

    // an integer variable that stores the state or other information inside a Tile Entity
    @Override
    public int getFieldCount()
    {
        return 4;
    }

    @Override
    public String getGuiID()
    {
        return MolecularCraft.MODID + ":" + BlockFissionReactor.NAME;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.itemStacks.get(index);
    }

    // set the stack at position of index
    @Override
    @SuppressWarnings("null")
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.itemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.itemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (index == 0 && !flag)
            this.markDirty();
    }

    @Override
    @SuppressWarnings("null")
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    @SuppressWarnings("null")
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        // some lateral logic
        return true;
    }

    IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

    @Override
    @SuppressWarnings({"unchecked", "null"})
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if (facing == EnumFacing.DOWN)
                return (T)(handlerBottom);
            else if (facing == EnumFacing.UP)
                return (T)(handlerTop);
            else
                return (T)(handlerSide);
        }
        return super.getCapability(capability, facing);
    }

    private void updateWork()
    {
        this.workNeedTime = FissionReactorRecipes.DEFAULT_TIME;
        this.currentEnergyChange = this.getCurrentEnergyChange();
    }

    private double getCurrentEnergyChange()
    {
        return ((FissionReactorRecipes)(ModRecipes.FISSION_REACTOR)).getEnergyRelease();
    }
}