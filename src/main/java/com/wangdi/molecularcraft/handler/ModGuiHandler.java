package com.wangdi.molecularcraft.handler;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.client.inventory.GuiElementDecomposer;
import com.wangdi.molecularcraft.client.inventory.GuiFissionReactor;
import com.wangdi.molecularcraft.client.inventory.GuiSubstanceDecomposer;
import com.wangdi.molecularcraft.inventory.ContainerElementDecomposer;
import com.wangdi.molecularcraft.inventory.ContainerFissionReactor;
import com.wangdi.molecularcraft.inventory.ContainerSubstanceDecomposer;
import com.wangdi.molecularcraft.tileentity.TileEntityElementDecomposer;
import com.wangdi.molecularcraft.tileentity.TileEntityFissionReactor;
import com.wangdi.molecularcraft.tileentity.TileEntitySubstanceDecomposer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public final class ModGuiHandler implements IGuiHandler
{
    public static final int GUI_ELEMENT_DECOMPOSER = 1;
    public static final int GUI_SUBSTANCE_DECOMPOSER = 2;
    public static final int GUI_FISSION_REACTOR = 3;

    public ModGuiHandler()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(MolecularCraft.INSTANCE, this);
    }
    
    //server gui
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        IInventory inventory = (IInventory)(world.getTileEntity(new BlockPos(x, y, z)));

        switch (ID)
        {
            case GUI_ELEMENT_DECOMPOSER:
                return new ContainerElementDecomposer(player.inventory, (TileEntityElementDecomposer)(inventory));
            case GUI_SUBSTANCE_DECOMPOSER:
                return new ContainerSubstanceDecomposer(player.inventory, (TileEntitySubstanceDecomposer)(inventory));
            case GUI_FISSION_REACTOR:
                return new ContainerFissionReactor(player.inventory, (TileEntityFissionReactor)(inventory));

            default:
                return null;
        }
    }
    
    //clinet gui
    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        switch (ID)
        {
            case GUI_ELEMENT_DECOMPOSER:
                return new GuiElementDecomposer(player.inventory,(TileEntityElementDecomposer)(tileEntity));
            case GUI_SUBSTANCE_DECOMPOSER:
                return new GuiSubstanceDecomposer(player.inventory,(TileEntitySubstanceDecomposer)(tileEntity));
            case GUI_FISSION_REACTOR:
                return new GuiFissionReactor(player.inventory, (TileEntityFissionReactor)(tileEntity));
            
            default:
                return null;
        }
    }

}