package com.wangdi.molecularcraft.potion;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

public class PotionTypeBased extends PotionType
{
    public PotionTypeBased(@Nullable String name, PotionEffect... effects)
    {
        super(name, effects);
        this.setRegistryName(MolecularCraft.MODID, name);
    }
}