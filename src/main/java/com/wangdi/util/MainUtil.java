package com.wangdi.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public final class MainUtil
{
    public MainUtil()
    {

    }

    // ====================== java function start ======================

    /**
     * Prints "debug" to the console for debugging purposes.
     *
     * @param  None  This function does not take any parameters.
     * @return None  This function does not return any value.
     */
    public static void debug()
    {
        System.out.println("debug");
    }

    /**
     * Concatenates an array of strings into a single string.
     *
     * @param  array  the array of strings to be concatenated
     * @return        the concatenated string
     */
    public static String combinedStrings(String ... array)
    {
        StringBuilder sb = new StringBuilder();

        for (String string : array)
            sb.append(string);

        return sb.toString();
    }

    /**
     * Returns the maximum of three double values.
     *
     * @param  a  the first double value
     * @param  b  the second double value
     * @param  c  the third double value
     * @return    the maximum of the three double values
     */
    public static double max(double a, double b, double c)
    {
        return Math.max(Math.max(a, b), c);
    }

    /**
     * Returns the minimum of three double values.
     *
     * @param  a  the first double value
     * @param  b  the second double value
     * @param  c  the third double value
     * @return    the minimum of the three double values
     */
    public static double min(double a, double b, double c)
    {
        return Math.min(Math.min(a, b), c);
    }

    // ====================== java function end ======================

    // ====================== minecraft function start ======================

    /**
     * Checks if a resource exists in the Minecraft texture manager.
     *
     * @param  resource  the ResourceLocation of the resource to check
     * @return           true if the resource exists, false otherwise
     */
    public static boolean resourceExist(ResourceLocation resource)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        try
        {
            minecraft.getTextureManager().getTexture(resource);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Compares two ItemStack objects and returns a boolean indicating whether they are equal.
     *
     * @param  stack1  the first ItemStack object to compare
     * @param  stack2  the second ItemStack object to compare
     * @return         true if the items and metadata of stack1 and stack2 are equal, false otherwise
     */
    public static boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
    
    /**
     * Generates an array of integers from the given start and end values, inclusive.
     *
     * @param  start  the starting value of the array
     * @param  end    the ending value of the array
     * @return        an array of integers from start to end, inclusive
     */
    public static int[] continuallyIntArray(int start, int end)
    {
        int size = end - start + 1;
        int[] array = new int[size];

        for (int i = start, count = 0; i <= end; i++, count++)
            array[count] = i;
        
        return array;
    }

    /**
     * Converts an array of generic type T to a NonNullList of the same type.
     *
     * @param  items  the array of generic type T to be converted
     * @return        the NonNullList containing the elements of the input array
     */
    public static <T> List<T> ArraytoNonNullList(T[] items)
    {
        NonNullList<T> array = NonNullList.create();

        for (T item : items)
            array.add(item);
            
        return array;
    }

    /**
     * Converts an array of Blocks to an array of corresponding ItemBlocks.
     *
     * @param  blocks  the array of Blocks to be converted
     * @return         the array of ItemBlocks corresponding to the input Blocks
     */
    public static Item[] BlocksToItems(Block[] blocks)
    {
        Item[] items = new ItemBlock[blocks.length];
        
        for (int i = 0; i < items.length; i++)
            items[i] = new ItemBlock(blocks[i]);

        return items;
    }

    /**
     * Retrieves the maximum health attribute value of the given EntityLivingBase.
     *
     * @param  entity  the EntityLivingBase object to retrieve the max health attribute from
     * @return         the maximum health attribute value, or 0.0 if the attribute is not found
     */
    public static double getMaxHealth(EntityLivingBase entity)
    {
        IAttributeInstance attributeInstance = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);

        if (attributeInstance != null)
            return attributeInstance.getBaseValue();

        return 0.0;
    }

    /**
     * Sets the maximum health attribute value of the given EntityLivingBase.
     *
     * @param entity    the EntityLivingBase object whose maximum health attribute value is to be set
     * @param maxHealth the new maximum health value to be set
     */
    public static void setMaxHealth(EntityLivingBase entity, double maxHealth)
    {
        if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
            entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);

        if (entity.getHealth() > maxHealth)
            entity.setHealth((float)(maxHealth));
        else
            entity.setHealth(entity.getHealth());
    }

    // ====================== minecraft function end ======================
}