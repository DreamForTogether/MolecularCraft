package com.wangdi.molecularcraft.proxy;

import com.wangdi.molecularcraft.init.ModelsRegister;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {

    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        // register models
        new ModelsRegister();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }
}