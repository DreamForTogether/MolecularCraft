package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.item.ModItems;
import com.wangdi.molecularcraft.potion.ModPotionTypes;
import com.wangdi.molecularcraft.potion.ModPotions;
import com.wangdi.molecularcraft.potion.PotionBased;

import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.init.PotionTypes;

@Mod.EventBusSubscriber
public class PotionTypesRegister
{
    public PotionTypesRegister()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void registerPotionTypes(RegistryEvent.Register<PotionType> event)
    {
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.FUEL_ROD, ((PotionBased)(ModPotions.RADIATION)).getPotionType());
        event.getRegistry().registerAll(ModPotionTypes.POTION_TYPES);
    }
}