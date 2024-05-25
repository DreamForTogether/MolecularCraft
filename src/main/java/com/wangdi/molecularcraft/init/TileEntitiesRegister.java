package com.wangdi.molecularcraft.init;

import com.wangdi.molecularcraft.tileentity.ModTileEntities;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public final class TileEntitiesRegister
{
    public static void registerTileEntities()
    {
        for (int i = 0 ; i < ModTileEntities.TILEENTITY_CLASSES.size(); i++)
            GameRegistry.registerTileEntity(ModTileEntities.TILEENTITY_CLASSES.get(i), ModTileEntities.TILEENTITY_RESOURCE[i]);
    }

    public TileEntitiesRegister()
    {

    }
}