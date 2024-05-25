package com.wangdi.molecularcraft.fluids;

import net.minecraftforge.fluids.Fluid;

public class ModFluids
{
    public static Fluid NUCLEAR_CONTAMINATED_WATER = new FluidLiquid.FluidNuclearContaminatedWater();

    public static final Fluid[] FLUIDS = new Fluid[]
    {
        NUCLEAR_CONTAMINATED_WATER
    };
}