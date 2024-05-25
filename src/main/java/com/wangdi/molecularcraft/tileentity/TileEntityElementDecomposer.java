
package com.wangdi.molecularcraft.tileentity;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockElementDecomposer;
import com.wangdi.molecularcraft.inventory.ContainerElementDecomposer;
import com.wangdi.molecularcraft.item.ItemElement;
import com.wangdi.molecularcraft.item.ModItems;
import com.wangdi.molecularcraft.item.crafting.ModRecipes;
import com.wangdi.util.MainUtil;
import com.wangdi.util.EnergyContainer;
import com.wangdi.util.ItemStackHandlerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public final class TileEntityElementDecomposer extends TileEntityLockable implements ITickable, ISidedInventory, EnergyContainer
{
    public static final int TO_ENERGY_MODE = 0;
    public static final int DECOMPOSITION_MODE = 1;
    public static final int ITEMSTACKS_SIZE = 19;
    public static final String CUSTOME_NAME = "container.element_decomposer";

    private static final int[] SLOTS_TOP = {0};
    private static final int[] SLOTS_SIDES = new int[0];
    private static final int[] SLOTS_BOTTOM = MainUtil.continuallyIntArray(1, ITEMSTACKS_SIZE - 1);

    @SideOnly(Side.CLIENT)
    public static boolean isWorking(TileEntityElementDecomposer tileEntity)
    {
        return tileEntity.getField(0) > 0;
    }

    private NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(ITEMSTACKS_SIZE, ItemStack.EMPTY);

    private int workMode = 0; // 0 -> toEnergy; 1 -> toParticle
    private int workNeedTime = 10000; // the time for work 1 mol Item
    private int currentTime = 0; // the time count for work
    
    private double workNeedEnergy = 0; // the energy for work 1 mol Item
    private double currentEnergy = 0; // currentEnergy store in the container

    private String customName;

    private ItemStack inputItemStack = new ItemStack(ModItems.HYDROGEN_ATOM);
    private Item inputItem = this.inputItemStack.copy().getItem();
    private ItemStack[] decomposeResult = ModRecipes.ELEMENT_DECOMPOSER.getResult(this.inputItemStack);

    public TileEntityElementDecomposer()
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

        this.workMode = compound.getInteger("WorkMode");
        this.currentTime = compound.getInteger("CurrentTime");
        this.currentEnergy = compound.getDouble("CurrentEnergy");
        this.setCustomName(compound.hasKey("CustomName", 8) ? compound.getString("CustomName") : CUSTOME_NAME);

        if (ItemElement.isElement(this.getStackInSlot(0).getItem()))
            this.updateWork();
    }

    //save the data
    @Override
    @SuppressWarnings("null")
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("WorkMode", this.workMode);
        compound.setInteger("WorkNeedTime", this.workNeedTime);
        compound.setInteger("CurrentTime", this.currentTime);
        compound.setDouble("WorkNeedEnergy", this.workNeedEnergy);
        compound.setDouble("CurrentEnergy", this.currentEnergy);
        ItemStackHelper.saveAllItems(compound, this.itemStacks);

        if (this.hasCustomName())
            compound.setString("CustomName", this.customName);

        return compound;
    }

    // the machine is working or not
    public boolean isWorking()
    {
        return this.currentTime > 0;
    }

    // used to update all items in the item slot.
    @Override
    public void update()
    {
        boolean flag = this.isWorking();

        if (!this.world.isRemote)
        {
            if (this.isWorking())
            {
                this.currentTime++;

                if (this.workMode == TO_ENERGY_MODE) this.currentEnergy += ((ItemElement)(this.inputItem)).getParticleEnergy() / this.workNeedTime;
                else if (this.workMode == DECOMPOSITION_MODE) this.currentEnergy -= this.workNeedEnergy / this.workNeedTime;

                if (this.isFullCharged())
                    this.currentEnergy = BlockElementDecomposer.MAX_STORE_ENERGY;

                if (this.currentTime == this.workNeedTime)
                {
                    this.currentTime = 0;

                    if (this.workMode == TO_ENERGY_MODE)
                        return;
                    else if (this.decomposeResult != null && ItemStackHandlerHelper.canAddItems(this.itemStacks, 1, decomposeResult))
                    {
                        for (ItemStack itemStack : this.decomposeResult)
                            ItemStackHandlerHelper.addItemToHandler(this.itemStacks, itemStack, 1);
                    }
                }
            }
            else if (!this.getStackInSlot(0).isEmpty())
            {
                this.updateWork();
                
                if (this.workMode == TO_ENERGY_MODE || this.workMode == DECOMPOSITION_MODE &&
                    ItemStackHandlerHelper.canAddItems(this.itemStacks, 1, decomposeResult))
                {
                    this.currentTime++;
                    this.inputItemStack.shrink(1);
                }
            }
            
            if (flag != this.isWorking())
                BlockElementDecomposer.setState(this.isWorking(), world, pos);

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
            return player.getDistanceSq((double)(this.pos.getX() + 0.5D), (double)(this.pos.getY() + 0.5D), (double)(this.pos.getZ()) + 0.5D) <= 64.0D;
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
        return BlockElementDecomposer.MAX_STORE_ENERGY;
    }

    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0: {this.workMode = value; return;}
            case 1: {this.workNeedTime = value; return;}
            case 2: {this.currentTime = value; return;}
            case 3: {this.workNeedEnergy = (double)(value); return;}
            case 4: {this.currentEnergy = (double)(value); return;}
            default: return;
        }
    }

    @Override
    public int getField(int index)
    {
        switch (index)
        {
            case 0: {return this.workMode;}
            case 1: {return this.workNeedTime;}
            case 2: {return this.currentTime;}
            case 3: {return (int)(this.workNeedEnergy);}
            case 4: {return (int)(this.currentEnergy);}
            default: return 0;
        }
    }

    @Override
    @SuppressWarnings("null")
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return (index == 0) ? ItemElement.isElement(stack.getItem()) : false;
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
        return new ContainerElementDecomposer(playerInventory, this);
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
        return 5;
    }

    @Override
    public String getGuiID()
    {
        return MolecularCraft.MODID + ":" + BlockElementDecomposer.NAME;
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
    @SuppressWarnings({"null", "unchecked"})
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T)(this.handlerBottom);
            else if (facing == EnumFacing.UP)
                return (T)(this.handlerTop);
            else
                return (T)(handlerSide);
        return super.getCapability(capability, facing);
    }

    private void updateWork()
    {
        this.inputItemStack = this.getStackInSlot(0);
        this.inputItem = this.inputItemStack.copy().getItem();
        this.workNeedTime = ((ItemElement)(this.inputItem)).timeForDecompose();
        this.workNeedEnergy = ((ItemElement)(this.inputItem)).getChangeInEnergy();
        this.workMode = this.getWorkMode();
        this.decomposeResult = ModRecipes.ELEMENT_DECOMPOSER.getResult(new ItemStack(this.inputItem));
    }

    private int getWorkMode()
    {
        return (this.currentEnergy > this.workNeedEnergy) ? DECOMPOSITION_MODE : TO_ENERGY_MODE;
    }
}