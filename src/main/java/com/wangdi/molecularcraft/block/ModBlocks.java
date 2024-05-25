package com.wangdi.molecularcraft.block;

import com.wangdi.molecularcraft.fluids.FluidBlock;

import net.minecraft.block.Block;

public final class ModBlocks
{
    public static final Block ELEMENT_DECOMPOSER = new BlockElementDecomposer(false);
    public static final Block WORK_ELEMENT_DECOMPOSER = new BlockElementDecomposer(true);
    public static final Block FISSION_REACTOR = new BlockFissionReactor(false);
    public static final Block WORK_FISSION_REACTOR = new BlockFissionReactor(true);
    public static final Block SUBSTANCE_DECOMPOSER = new BlockSubstanceDecomposer(false);
    public static final Block WORK_SUBSTANCE_DECOMPOSER = new BlockSubstanceDecomposer(true);
    //public static final Block FUSION_REACTOR = new BlockFusionReactor(false);
    //public static final Block WORK_FUSION_REACTOR = new BlockFusionReactor(true);

    public static final Block ENERGY_CONDUIT = new BlockEnergyConduit();

    // fluid
    public static final Block NUCLEAR_CONTAMINATED_WATER = new FluidBlock.FluidNuclearContaminatedWater();

    public static final Block[] BLOCKS = new Block[]
    {
        ELEMENT_DECOMPOSER, WORK_ELEMENT_DECOMPOSER,
        FISSION_REACTOR, WORK_FISSION_REACTOR,
        SUBSTANCE_DECOMPOSER, WORK_SUBSTANCE_DECOMPOSER,
        //FUSION_REACTOR, WORK_FUSION_REACTOR,
        ENERGY_CONDUIT, NUCLEAR_CONTAMINATED_WATER
    };
}