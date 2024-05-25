package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.item.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class ModelsRegister
{
    public ModelsRegister()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event)
    {
        for (Item item : ModItems.ITEMS)
        {
            if (item != null)
                registerModel(item);
            else
                System.err.println("Failed to register Model: " + item + " as it is null!");
        }
    }

    @SuppressWarnings("null")
    private void registerModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}