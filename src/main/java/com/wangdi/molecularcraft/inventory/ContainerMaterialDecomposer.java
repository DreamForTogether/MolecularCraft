package com.wangdi.molecularcraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public final class ContainerMaterialDecomposer extends Container
{
    public ContainerMaterialDecomposer()
    {

    }

    @Override
    @SuppressWarnings("null")
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
}