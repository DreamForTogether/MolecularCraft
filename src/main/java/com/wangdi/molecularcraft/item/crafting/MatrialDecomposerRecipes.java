package com.wangdi.molecularcraft.item.crafting;

import java.util.Map;
import java.util.Map.Entry;

import java.util.HashMap;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class MatrialDecomposerRecipes extends ModBaseRecipes
{
    public static final Map<Item, Double> MASS_TABLE = generateMassTable();

    private static Map<Item, Double> generateMassTable()
    {
        final double defaultMass = 2500;

        Map<Item, Double> hashMap = new HashMap<>();

        hashMap.put(new ItemBlock(Blocks.STONE), 2500.0);
        hashMap.put(new ItemBlock(Blocks.GRASS), 1500.0);
        hashMap.put(new ItemBlock(Blocks.DIRT), 1500.0);
        hashMap.put(new ItemBlock(Blocks.COBBLESTONE), 2500.0);
        hashMap.put(new ItemBlock(Blocks.PLANKS), 150.0);
        hashMap.put(new ItemBlock(Blocks.BEDROCK), 19250.0);
        hashMap.put(new ItemBlock(Blocks.SAND), 1600.0);
        hashMap.put(new ItemBlock(Blocks.GRAVEL), 1600.0);
        hashMap.put(new ItemBlock(Blocks.GOLD_ORE), 4355.5556);
        hashMap.put(new ItemBlock(Blocks.IRON_ORE), 2726.9841);
        hashMap.put(new ItemBlock(Blocks.COAL_ORE), 2322.2222);
        hashMap.put(new ItemBlock(Blocks.LOG), 600.0);
        hashMap.put(new ItemBlock(Blocks.SPONGE), 900.0);
        hashMap.put(new ItemBlock(Blocks.GLASS), 2600.0);
        hashMap.put(new ItemBlock(Blocks.LAPIS_ORE), defaultMass);
        hashMap.put(new ItemBlock(Blocks.LAPIS_BLOCK), defaultMass);
        hashMap.put(new ItemBlock(Blocks.SANDSTONE), 2000.0);
        hashMap.put(new ItemBlock(Blocks.WOOL), 1000.0);
        
        return hashMap;
    }

    public MatrialDecomposerRecipes()
    {
        super();

        ItemStack[] stoneResult = {};
        this.addRecipe(new ItemStack(new ItemBlock(Blocks.STONE)), stoneResult, 0.0f);
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    private void addRecipe(ItemStack input, ItemStack[] result, float experience)
    {
        this.resultList.put(input, result);
        this.experienceList.put(input, experience);
    }

    @Override
    public ItemStack[] getResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack[]> entry : this.resultList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
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
            if (this.compareItemStacks(stack, entry.getKey()))
                return ((Float)entry.getValue()).floatValue();
        }

        return 0.0F;
    }
}