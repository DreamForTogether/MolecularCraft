package com.wangdi.molecularcraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBaseCreativeTabs extends CreativeTabs
{
    private Item icon;
    private Item[] items;

    public ModBaseCreativeTabs(String name, Item icon, Item[] items)
    {
        super(name);

        this.icon = icon;
        this.items = items;
    }

    public ModBaseCreativeTabs(int index, String name, Item icon, Item[] items)
    {
        super(index, name);

        this.icon = icon;
        this.items = items;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(this.icon);
    }

    @Override
    @SuppressWarnings("null")
    public void displayAllRelevantItems(NonNullList<ItemStack> items)
    {
        items.clear();

        for (Item item : this.items)
        {
            ItemStack temp = new ItemStack(item);
            items.add(temp);
        }
    }
}