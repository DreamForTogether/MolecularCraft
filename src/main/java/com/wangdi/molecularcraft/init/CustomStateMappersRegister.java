package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.ModBlocks;
import com.wangdi.molecularcraft.fluids.FluidLiquid;
import com.wangdi.util.MainUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CustomStateMappersRegister
{
    public static void registerCustomStateMappers()
    {
        ModelLoader.setCustomStateMapper(ModBlocks.NUCLEAR_CONTAMINATED_WATER, new StateMapperBase()
		{
			protected ModelResourceLocation getModelResourceLocation(@SuppressWarnings("null") IBlockState state)
			{   
				return new ModelResourceLocation(MainUtil.combinedStrings(MolecularCraft.MODID, ":", FluidLiquid.FluidNuclearContaminatedWater.NAME), "fluid");
			}
		});
    }
}