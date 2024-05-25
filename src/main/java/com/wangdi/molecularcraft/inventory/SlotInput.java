package com.wangdi.molecularcraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInput extends Slot
{
    public SlotInput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    @SuppressWarnings("null")
    public boolean isItemValid(ItemStack stack)
    {
        return true;
    }
}