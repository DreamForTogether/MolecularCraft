package com.wangdi.molecularcraft.item.crafting;

import java.util.Map;
import java.util.HashMap;

import net.minecraft.item.ItemStack;

public abstract class ModBaseRecipes
{
    protected Map<ItemStack, Float> experienceList;
    protected Map<ItemStack, ItemStack[]> resultList;

    public ModBaseRecipes()
    {
        this.experienceList = new HashMap<>();
        this.resultList = new HashMap<>();
    }

    public abstract float getExperience(ItemStack stack);
    public abstract ItemStack[] getResult(ItemStack stack);
}