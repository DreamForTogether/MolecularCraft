package com.wangdi.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public final class ItemStackHandlerHelper
{
    public ItemStackHandlerHelper()
    {

    }

    public static NonNullList<ItemStack> copyItemStacks(NonNullList<ItemStack> resource)
    {
        NonNullList<ItemStack> result = NonNullList.withSize(resource.size(), ItemStack.EMPTY);

        for (ItemStack itemStack : resource)
            result.add(itemStack.copy());
        
        return result;
    }

    public static boolean canAddItems(NonNullList<ItemStack> itemStacks, int startIndex, ItemStack[] result)
    {
        Map<Item, Integer> totalSpace = new HashMap<>();
        Map<Item, Integer> totalNeed = new HashMap<>();

        if (itemStacks == null || result == null)
            return false;

        for (int i = 0; i < result.length; i++)
        {
            ItemStack itemStack = result[i];
            Item item = itemStack.getItem();

            if (!totalSpace.containsKey(item))
                totalSpace.put(item, totalRemain(itemStacks, startIndex, itemStack));
            else
                totalSpace.put(item, totalSpace.get(item) + totalRemain(itemStacks, startIndex, itemStack));
            
            if (!totalNeed.containsKey(item))
                totalNeed.put(item, itemStack.getCount());
            else
                totalNeed.put(item, itemStack.getCount() + totalNeed.get(item));
        }

        for (ItemStack itemStack : result)
        {
            Item item = itemStack.getItem();

            if (totalNeed.get(item) >= totalSpace.get(item))
                return false;
        }

        return true;
    }
    
    public static void addItemToHandler(NonNullList<ItemStack> list, ItemStack item, int startSlot)
    {
        if (startSlot >= list.size())
            throw new ArrayIndexOutOfBoundsException();
        
        ItemStack copy = item.copy();
        int needSpace = copy.getCount();
        
        for (int i = startSlot; i < list.size(); i++)
        {
            ItemStack current = list.get(i);

            if (current.isEmpty())
            {
                list.set(i, copy);
                return;
            }
            else if (current.isItemEqual(copy))
            {
                int slotRemain = remain(list, i);

                if (slotRemain >= needSpace)
                {
                    list.get(i).setCount(current.getCount() + needSpace);
                    return;
                }
                else
                {
                    list.get(i).setCount(copy.getMaxStackSize());
                    needSpace -= slotRemain;
                }
            }

            if (needSpace <= 0)
                return;
        }
    }

    public static boolean isFull(NonNullList<ItemStack> list, int slot)
    {
        return remain(list, slot) == 0;
    }

    public static int remain(NonNullList<ItemStack> list, int slot)
    {
        ItemStack itemStack = list.get(slot);

        if (!itemStack.isEmpty())
        {
            int remain = itemStack.getMaxStackSize() - itemStack.getCount();
            return remain >= 0 ? remain : 0;
        }
        else
            return -1;
    }

    public static int totalRemain(NonNullList<ItemStack> list, int startIndex, ItemStack itemStack)
    {
        int sum = 0;

        for (int i = startIndex; i < list.size(); i++)
        {
            ItemStack current = list.get(i);

            if (current.isEmpty())
                sum += 64;

            else if (current.isItemEqual(itemStack))
                sum += remain(list, i);
        }

        return sum;
    }
}