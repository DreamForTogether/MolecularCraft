package com.wangdi.molecularcraft.proxy;

import com.wangdi.molecularcraft.handler.ModGuiHandler;
import com.wangdi.molecularcraft.init.BlocksRegister;
import com.wangdi.molecularcraft.init.CustomMeshesRegister;
import com.wangdi.molecularcraft.init.CustomStateMappersRegister;
import com.wangdi.molecularcraft.init.FluidsRegister;
import com.wangdi.molecularcraft.init.ItemsRegister;
import com.wangdi.molecularcraft.init.PotionTypesRegister;
import com.wangdi.molecularcraft.init.PotionsRegister;
import com.wangdi.molecularcraft.init.TileEntitiesRegister;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
    public CommonProxy()
    {
        
    }

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    public void preInit(FMLPreInitializationEvent event)
    {
        FluidsRegister.registerFluids();
        TileEntitiesRegister.registerTileEntities(); // register tileEnity
        CustomMeshesRegister.registerCustomMeshes();
        CustomStateMappersRegister.registerCustomStateMappers();

        new ItemsRegister(); //register the items
        new BlocksRegister(); // register the blocks
        new PotionsRegister(); // register the potions
        new ModGuiHandler(); // register the gui handler
        new PotionsRegister(); // register the potions
        new PotionTypesRegister(); // register the potion types
    }

    public void init(FMLInitializationEvent event)
    {

    }
}