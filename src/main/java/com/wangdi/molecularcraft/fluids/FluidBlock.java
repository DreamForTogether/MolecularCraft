package com.wangdi.molecularcraft.fluids;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.util.MainUtil;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class FluidBlock extends BlockFluidClassic
{
    public static class FluidNuclearContaminatedWater extends FluidBlock
    {
        public static final String NAME = "nuclear_contaminated_water";

        public FluidNuclearContaminatedWater()
        {
            super(NAME, ModFluids.NUCLEAR_CONTAMINATED_WATER, Material.WATER);
            this.setCreativeTab(ModCreativeTabs.MISC);
        }
    }

    public FluidBlock(String name, Fluid fluid, Material material)
	{
		super(fluid, material);

		this.setRegistryName(name);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", name));
        this.setCreativeTab(ModCreativeTabs.MISC);
	}
	
	public EnumBlockRenderType getRenderType(@SuppressWarnings("null") IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
}