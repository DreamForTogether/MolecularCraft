package com.wangdi.molecularcraft.item.crafting;

import java.util.HashMap;
import java.util.Map.Entry;

import com.wangdi.molecularcraft.item.ItemSubstance;
import com.wangdi.molecularcraft.item.ModItems;
import com.wangdi.util.MainUtil;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SubstanceDecomposerRecipes extends ModBaseRecipes
{
    public SubstanceDecomposerRecipes()
    {
        super();
        this.resultList = new HashMap<>();

        for (Item item : ModItems.ELEMENTARY_SUBSISTANCES)
            this.addRecipe(new ItemStack(item), ((ItemSubstance)(item)).getComposition());
    }

    private void addRecipe(ItemStack input, ItemStack[] result)
    {
        int count = 0;

        for (ItemStack itemStack : result)
            count += itemStack.getCount();

        this.resultList.put(input, result);
        this.experienceList.put(input, count * 1F);
    }

    @Override
    public ItemStack[] getResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack[]> entry : this.resultList.entrySet())
        {
            if (MainUtil.compareItemStacks(stack, entry.getKey()))
                return entry.getValue();
        }

        return null;
    }

    @Override
    public float getExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (MainUtil.compareItemStacks(stack, entry.getKey()))
                return entry.getValue();
        }

        return 0.0F;
    }
}