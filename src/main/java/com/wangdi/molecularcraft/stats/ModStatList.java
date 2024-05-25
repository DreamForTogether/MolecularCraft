package com.wangdi.molecularcraft.stats;

import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
import net.minecraft.util.text.TextComponentTranslation;

public final class ModStatList
{
    public static final String ELEMENT_DECOMPOSER = "stat.element_decomposerInteraction";
    public static final StatBase ELEMENT_DECOMPOSER_INTERACTION = (new StatBasic(ELEMENT_DECOMPOSER, new TextComponentTranslation(ELEMENT_DECOMPOSER, new Object[0]))).registerStat();

    public ModStatList()
    {

    }
}