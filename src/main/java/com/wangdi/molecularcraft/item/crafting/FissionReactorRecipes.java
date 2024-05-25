package com.wangdi.molecularcraft.item.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wangdi.molecularcraft.item.ModItems;
import com.wangdi.util.MainUtil;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FissionReactorRecipes extends ModBaseRecipes
{
    public static final int DEFAULT_TIME = 1000; //24000;
    public static final float DEFAULT_EXPERIENCE = 10.0f;
    public static final double DEFAULT_ENERGY = 82093612824.487;
    public static final double DEFAULT_RADIATION_EMIT = 4.1666;
    
    private Map<ItemStack, List<ItemStack[]>> resultList;

    public static boolean isValidControl(Item item)
    {
        return item.getClass().equals(ModItems.CONTROL_ROD.getClass());
    }

    public static boolean isValidTrigger(Item item)
    {
        Class<? extends Item> itemClass = item.getClass();
        return itemClass.equals((new ItemBlock(Blocks.TNT)).getClass());
    }

    public static boolean isValidInput(Item item)
    {
        return item.getClass().equals(ModItems.FUEL_ROD.getClass());
    }

    public static boolean isValidCoolLiquid(Item item)
    {
        Class<? extends Item> itemClass = item.getClass();
        return itemClass.equals(Items.WATER_BUCKET.getClass()) ||
               itemClass.equals(ModItems.BUCKET_NUCLEAR_CONTAMINATED_WATER.getClass());
    }

    public static boolean isValidGenerator(Item item)
    {
        return item.getClass().equals(ModItems.GENERATOR.getClass());
    }

    public static boolean isValidEnergyStorage(Item item)
    {
        return item.getClass().equals(ModItems.ENERGY_BATTERY.getClass());
    }

    public FissionReactorRecipes()
    {
        this.resultList = new HashMap<>();

        this.addRecipe(new ItemStack(ModItems.URANIUM), new ItemStack[][]
        {
            {new ItemStack(ModItems.STRONTIUM), new ItemStack(ModItems.XENON), new ItemStack(ModItems.NEUTRON, 10)},
            {new ItemStack(ModItems.STRONTIUM), new ItemStack(ModItems.XENON), new ItemStack(ModItems.NEUTRON, 3)},
            {new ItemStack(ModItems.BARIUM), new ItemStack(ModItems.KRYPTON), new ItemStack(ModItems.NEUTRON, 3)}
        });

        this.addRecipe(new ItemStack(ModItems.FUEL_ROD), new ItemStack[][]
        {
            {new ItemStack(ModItems.STRONTIUM), new ItemStack(ModItems.XENON), new ItemStack(ModItems.NEUTRON, 10)},
            {new ItemStack(ModItems.STRONTIUM), new ItemStack(ModItems.XENON), new ItemStack(ModItems.NEUTRON, 3)},
            {new ItemStack(ModItems.BARIUM), new ItemStack(ModItems.KRYPTON), new ItemStack(ModItems.NEUTRON, 3)}
        });
    }

    private void addRecipe(ItemStack input, ItemStack[][] result)
    {
        this.resultList.put(input, Arrays.asList(result));
    }

    @Override
    public float getExperience(ItemStack stack)
    {
        return DEFAULT_EXPERIENCE;
    }

    public double getEnergyRelease()
    {
        return DEFAULT_ENERGY;
    }

    @Override
    public ItemStack[] getResult(ItemStack stack)
    {
        List<ItemStack[]> result = new ArrayList<>();

        for (Entry<ItemStack, List<ItemStack[]>> entry : this.resultList.entrySet())
        {
            if (MainUtil.compareItemStacks(stack, entry.getKey()))
            {
                // generate index [0, result.size() - 1]
                result = entry.getValue();
                int index = (int)(Math.random() * result.size());
                return result.get(index);
            }
        }

        return null;
    }
}