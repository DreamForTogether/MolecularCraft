package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class BlocksRegister
{
    public BlocksRegister()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @SuppressWarnings("null")
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        for (Block block : ModBlocks.BLOCKS)
        {
            if (block != null)
            {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
                event.getRegistry().register(block);
            }
            else
                throw new NullPointerException("Failed to register Block: " + block.getRegistryName() + " as it is null!");
        }
    }

    @SubscribeEvent
    @SuppressWarnings("null")
    public static void registerItemBlocks(RegistryEvent.Register<Item> event)
    {
        for (Block block : ModBlocks.BLOCKS)
        {
            if (block != null)
            {
                ResourceLocation registryName = block.getRegistryName();
                Item itemBlock = new ItemBlock(block).setRegistryName(registryName);
                ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(registryName, "inventory"));
                event.getRegistry().register(itemBlock);
            }
            else
                throw new NullPointerException("Failed to register ItemBlock: " + block.getRegistryName() + " as it is null!");
        }
    }
}