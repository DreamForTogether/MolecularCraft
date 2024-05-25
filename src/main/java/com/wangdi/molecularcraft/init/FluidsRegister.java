package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.fluids.ModFluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FluidsRegister
{	
    public static void registerFluids()
    {
        for (Fluid fluid : ModFluids.FLUIDS)
        {
            if (fluid != null)
                registerFluid(fluid);
        }
	}

	public static void registerFluid(Fluid fluid)
    {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}