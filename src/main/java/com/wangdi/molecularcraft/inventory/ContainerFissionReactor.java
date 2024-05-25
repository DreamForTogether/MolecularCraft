package com.wangdi.molecularcraft.inventory;

import com.wangdi.molecularcraft.item.crafting.FissionReactorRecipes;
import com.wangdi.molecularcraft.item.crafting.ModRecipes;
import com.wangdi.molecularcraft.tileentity.TileEntityFissionReactor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ContainerFissionReactor extends Container
{
    private static final class SlotFusionReactorControl extends SlotInput
    {
        public SlotFusionReactorControl(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return FissionReactorRecipes.isValidControl(stack.getItem());
        }
    }

    private static final class SlotFusionReactorTrigger extends SlotInput
    {
        public SlotFusionReactorTrigger(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return FissionReactorRecipes.isValidTrigger(stack.getItem());
        }
    }

    private static final class SlotFusionReactorInput extends SlotInput
    {
        public SlotFusionReactorInput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return FissionReactorRecipes.isValidInput(stack.getItem());
        }
    }

    private static final class SlotICoolingLiquidInput extends SlotInput
    {
        public SlotICoolingLiquidInput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return FissionReactorRecipes.isValidCoolLiquid(stack.getItem());
        }
    }

    private static final class SlotGenerator extends SlotInput
    {
        public SlotGenerator(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return FissionReactorRecipes.isValidGenerator(stack.getItem());
        }
    }

    private static final class SlotFusionReactorStorage extends SlotInput
    {
        public SlotFusionReactorStorage(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return FissionReactorRecipes.isValidEnergyStorage(stack.getItem());
        }
    }

    private static final int INVENTORY_START_INDEX = TileEntityFissionReactor.ITEMSTACKS_SIZE;
    private static final int INVENTORY_END_INDEX = INVENTORY_START_INDEX + 26;
    private static final int MAIN_INVENTORY_START_INDEX = INVENTORY_END_INDEX + 1;
    private static final int MAIN_INVENTORY_END_INDEX = MAIN_INVENTORY_START_INDEX + 8;

    private final IInventory tileEntity;

    private int workNeedTime = 10000; // the time for work 1 mol Item
    private int currentTime = 0; // the time count for work
    private double currentEnergy = 0.0; // currentEnergy store in the container

    private int reactorAnimation = 0;

    public ContainerFissionReactor(InventoryPlayer playerInventory, IInventory tileEntity)
    {
        //IItemHandler handler = tileeEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.tileEntity = tileEntity;

        // input slot
        this.addSlotToContainer(new SlotFusionReactorControl(tileEntity, 0, 8, 32));
        this.addSlotToContainer(new SlotFusionReactorTrigger(tileEntity, 1, 8, 50));
        this.addSlotToContainer(new SlotFusionReactorInput(tileEntity, 2, 8, 68));
        this.addSlotToContainer(new SlotICoolingLiquidInput(tileEntity, 3, 134, 37));
        this.addSlotToContainer(new SlotGenerator(tileEntity, 4, 84, 88));
        this.addSlotToContainer(new SlotFusionReactorStorage(tileEntity, 5, 152, 112));

        // output slots
        int index = 6;
        for(int y = 0; y < 2; y++)
        {
            for(int x = 0; x < 3; x++)
                this.addSlotToContainer(new SlotOutput(playerInventory.player, tileEntity, index++, 8 + x * 18, 100 + y * 18));
        }

        // Inventory slots
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 9; x++)
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 150 + y * 18));
        }

        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 208));
    }

    @Override
    @SuppressWarnings("null")
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileEntity);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); i++)
        {
            IContainerListener listener = (IContainerListener)(this.listeners.get(i));
            
            if (this.workNeedTime != this.tileEntity.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
            if (this.currentTime != this.tileEntity.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
            if (this.currentEnergy != this.tileEntity.getField(2)) listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
            if (this.reactorAnimation != this.tileEntity.getField(3)) listener.sendWindowProperty(this, 3, this.tileEntity.getField(3));
        }
        
        this.workNeedTime = this.tileEntity.getField(0);
        this.currentTime = this.tileEntity.getField(1);
        this.currentEnergy = this.tileEntity.getField(2);
        this.reactorAnimation = this.tileEntity.getField(3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileEntity.setField(id, data);
    }

    @Override
    @SuppressWarnings("null")
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    @SuppressWarnings("null")
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack1 = itemstack1.copy();

            if (index > 1 && index < TileEntityFissionReactor.ITEMSTACKS_SIZE) // index in the container
            {
                playerIn.addExperience((int)(ModRecipes.FISSION_REACTOR.getExperience(itemstack)));
                if (!this.mergeItemStack(itemstack1, INVENTORY_START_INDEX, MAIN_INVENTORY_END_INDEX + 1, true)) return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1, itemstack1);
            }
            else if (index >= TileEntityFissionReactor.ITEMSTACKS_SIZE) // in the inventory field
            {
                if (ModRecipes.FISSION_REACTOR.getResult(itemstack1) != null)
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) return ItemStack.EMPTY;
                else if (index >= INVENTORY_START_INDEX && index <= INVENTORY_END_INDEX)
                    if (!this.mergeItemStack(itemstack1, MAIN_INVENTORY_START_INDEX, MAIN_INVENTORY_END_INDEX + 1, false)) return ItemStack.EMPTY;
                else if (index >= MAIN_INVENTORY_START_INDEX && index <= MAIN_INVENTORY_END_INDEX && !this.mergeItemStack(itemstack1, INVENTORY_START_INDEX, INVENTORY_END_INDEX, false)) return ItemStack.EMPTY;
            }
            else if (!this.mergeItemStack(itemstack1, INVENTORY_START_INDEX, MAIN_INVENTORY_END_INDEX + 1, false)) return ItemStack.EMPTY;

            if (itemstack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();
        }

        return itemstack;
    }
}