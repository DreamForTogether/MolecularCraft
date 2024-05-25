package com.wangdi.molecularcraft.tileentity;

import java.util.Arrays;
import java.util.List;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockElementDecomposer;
import com.wangdi.molecularcraft.block.BlockEnergyConduit;
import com.wangdi.molecularcraft.block.BlockFissionReactor;
import com.wangdi.molecularcraft.block.BlockSubstanceDecomposer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public final class ModTileEntities
{
    public static final List<Class<? extends TileEntity>> TILEENTITY_CLASSES = Arrays.asList
    (
        TileEntityElementDecomposer.class,
        TileEntityEnergyConduit.class,
        TileEntityFissionReactor.class,
        TileEntitySubstanceDecomposer.class
    );

    public static final ResourceLocation[] TILEENTITY_RESOURCE =
    {
        new ResourceLocation(MolecularCraft.MODID, BlockElementDecomposer.NAME),
        new ResourceLocation(MolecularCraft.MODID, BlockEnergyConduit.NAME),
        new ResourceLocation(MolecularCraft.MODID, BlockFissionReactor.NAME),
        new ResourceLocation(MolecularCraft.MODID, BlockSubstanceDecomposer.NAME)
    };
}