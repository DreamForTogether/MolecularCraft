package com.wangdi.molecularcraft.inventory;

import com.wangdi.molecularcraft.item.ItemElement;
import com.wangdi.molecularcraft.item.crafting.ModRecipes;
import com.wangdi.molecularcraft.tileentity.TileEntityElementDecomposer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ContainerElementDecomposer extends Container
{
    private static final class SlotElementDecomposerInput extends SlotInput
    {
        public SlotElementDecomposerInput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
        {
            super(inventoryIn, slotIndex, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return ItemElement.isElement(stack.getItem());
        }
    }

    private static final int INVENTORY_START_INDEX = TileEntityElementDecomposer.ITEMSTACKS_SIZE;
    private static final int INVENTORY_END_INDEX = INVENTORY_START_INDEX + 26;
    private static final int MAIN_INVENTORY_START_INDEX = INVENTORY_END_INDEX + 1;
    private static final int MAIN_INVENTORY_END_INDEX = MAIN_INVENTORY_START_INDEX + 8;

    private final IInventory tileEntity;

    private int decompositionMode = 0; // 0 -> toEnergy; 1 -> toParticle
    private int decompositionNeedTime = 100; // the time for decomposition 1 mol Item
    private int currentTime = 0; // the time count for decomposition
    private double decompositionNeedEnergy = 0; // the energy for decomposition 1 mol Item
    private double currentEnergy = 0; // currentEnergy store in the container

    public ContainerElementDecomposer(InventoryPlayer playerInventory, IInventory tileEntity)
    {
        //IItemHandler handler = tileeEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.tileEntity = tileEntity;
        // input slot
        this.addSlotToContainer(new SlotElementDecomposerInput(tileEntity, 0, 8, 36));

        // output slots
        int index = 1;
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 6; x++)
                this.addSlotToContainer(new SlotOutput(playerInventory.player, tileEntity, index++, 62 + x * 18, 18 + y * 18));
        }

        // Inventory slots
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 9; x++)
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 86 + y * 18));
        }

        for (int k = 0; k < 9; k++)
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 144));
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
            
            if (this.decompositionMode != this.tileEntity.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
            if (this.decompositionNeedTime != this.tileEntity.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
            if (this.currentTime != this.tileEntity.getField(2)) listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
            if ((int)(this.decompositionNeedEnergy) != this.tileEntity.getField(3)) listener.sendWindowProperty(this, 3, this.tileEntity.getField(3));
            if ((int)(this.currentEnergy) != this.tileEntity.getField(4)) listener.sendWindowProperty(this, 4, this.tileEntity.getField(4));
        }
        
        this.decompositionMode = this.tileEntity.getField(0);
        this.decompositionNeedTime = this.tileEntity.getField(1);
        this.currentTime = this.tileEntity.getField(2);
        this.decompositionNeedEnergy = (double)(this.tileEntity.getField(3));
        this.currentEnergy = (double)(this.tileEntity.getField(4));
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

            if (index > 0 && index < TileEntityElementDecomposer.ITEMSTACKS_SIZE) // index in the container
            {
                playerIn.addExperience((int)(ModRecipes.ELEMENT_DECOMPOSER.getExperience(itemstack)));
                if (!this.mergeItemStack(itemstack1, INVENTORY_START_INDEX, MAIN_INVENTORY_END_INDEX + 1, true)) return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1, itemstack1);
            }
            else if (index >= TileEntityElementDecomposer.ITEMSTACKS_SIZE) // in the inventory field
            {
                if (ModRecipes.ELEMENT_DECOMPOSER.getResult(itemstack1) != null)
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) return ItemStack.EMPTY;
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