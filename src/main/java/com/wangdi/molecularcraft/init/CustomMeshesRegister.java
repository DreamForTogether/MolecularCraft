package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.ModBlocks;
import com.wangdi.molecularcraft.fluids.FluidLiquid;
import com.wangdi.util.MainUtil;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CustomMeshesRegister
{
    public static void registerCustomMeshes()
    {
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.NUCLEAR_CONTAMINATED_WATER), new ItemMeshDefinition()
		{
			public ModelResourceLocation getModelLocation(@SuppressWarnings("null") ItemStack stack)
            {
				return new ModelResourceLocation(MainUtil.combinedStrings(MolecularCraft.MODID, ":", FluidLiquid.FluidNuclearContaminatedWater.NAME), "fluid");
			}
		});
    }
}