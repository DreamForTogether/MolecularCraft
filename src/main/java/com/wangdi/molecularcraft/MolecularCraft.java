/**
 * Copyright (c) 2024.  by WangDi
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 * 
 * 
 * 
 * MolecularCraft is an open-source Minecraft mod
 * Main propose is in order to facilitate Physics and Chemistry learning.
 * 
 * Minecraft version:   1.12.2
 * Forge:               MinecraftForge 14.23.5.2859
 * Release:             beta version
 * Release date:        2024-05-17
 * GitHub:              https://github.com/wangdi111/MolecularCraft
 * 
 * Build environment:   Visual Studio Code 2024
 * Compiler:            JDK 1.8.0_301
 * System information:  Windows 10
 */

package com.wangdi.molecularcraft;

import com.wangdi.molecularcraft.proxy.CommonProxy;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

//mod main class
@Mod(modid = MolecularCraft.MODID, name = MolecularCraft.NAME, version = MolecularCraft.VERSION)
public final class MolecularCraft
{
    public static final String MODID = "molecularcraft";
    public static final String NAME = "Molecular Craft";
    public static final String VERSION = "0.0.0.28";

    @Mod.Instance(MolecularCraft.MODID)
    public static MolecularCraft INSTANCE;

    @SidedProxy(clientSide = "com.wangdi.molecularcraft.proxy.ClientProxy",
                serverSide = "com.wangdi.molecularcraft.proxy.CommonProxy")
    private static CommonProxy PROXY;
    private static Logger LOGGER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
        LOGGER.info("Initializing: " + MODID);
        PROXY.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}