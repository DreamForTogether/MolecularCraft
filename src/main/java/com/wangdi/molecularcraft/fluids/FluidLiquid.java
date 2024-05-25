package com.wangdi.molecularcraft.fluids;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.util.MainUtil;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquid extends Fluid
{
    public static class FluidNuclearContaminatedWater extends FluidLiquid
    {
        public static String NAME = "nuclear_contaminated_water";
        public static ResourceLocation STILL = new ResourceLocation("molecularcraft:blocks/nuclear_contaminated_water_still");
        public static ResourceLocation FLOWING = new ResourceLocation("molecularcraft:blocks/nuclear_contaminated_water_flow");

        public FluidNuclearContaminatedWater()
        {
            super(MainUtil.combinedStrings(MolecularCraft.MODID, ".", NAME), STILL, FLOWING);
        }
    }

	public FluidLiquid(String name, ResourceLocation still, ResourceLocation flow)
    {
		super(name, still, flow);

		this.setUnlocalizedName(name);
	}
}