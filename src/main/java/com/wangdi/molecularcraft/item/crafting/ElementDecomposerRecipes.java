package com.wangdi.molecularcraft.item.crafting;

import java.util.Map.Entry;

import com.wangdi.molecularcraft.item.ItemElement;
import com.wangdi.molecularcraft.item.ModItems;
import com.wangdi.util.MainUtil;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ElementDecomposerRecipes extends ModBaseRecipes
{
    public ElementDecomposerRecipes()
    {
        super();

        for (Item item : ModItems.ELEMENTS)
            this.addRecipe(new ItemStack(item), ((ItemElement)(item)).getDecompositionResult());
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
}