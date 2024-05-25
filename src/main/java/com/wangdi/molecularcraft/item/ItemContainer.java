package com.wangdi.molecularcraft.item;

import java.util.List;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;
import com.wangdi.util.physics.PhysicsConstant;
import com.wangdi.util.physics.PrefixNumber;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemContainer extends Item
{
    public static final class ItemEnergyBattery extends ItemContainer
    {
        public static final String NAME = "energy_battery";
        public static final double MAX_ENERGY_STORAGE = 5e13;

        public ItemEnergyBattery()
        {
            super(NAME, MAX_ENERGY_STORAGE);
        }
    }

    public static final String VALUE_TAG = "value";
    public static final String MAX_VALUE_TAG = "max_value";

    private double maxValue;

    public ItemContainer(String name, double maxValue)
    {
        this.maxValue = maxValue;

        this.setMaxStackSize(1);
        this.setRegistryName(name);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", name));
        this.setCreativeTab(ModCreativeTabs.ELEMENTS);
    }

    @Override
    @SuppressWarnings("null")
    public boolean showDurabilityBar(ItemStack stack)
    {
        return this.getValue(stack) < this.getMaxValue();
    }

    @Override
    @SuppressWarnings("null")
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return 1.0 - this.getValue(stack) / this.getMaxValue();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("null")
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String name = TextFormatting.GOLD + this.getItemStackDisplayName(stack);

        if (!tooltip.isEmpty()) tooltip.remove(tooltip.size() - 1);
        if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips) name += TextFormatting.WHITE + " (#" + Item.getIdFromItem(this) + ")";

        tooltip.add(name);
        tooltip.add(MainUtil.combinedStrings(TextFormatting.BLUE.toString(),
                                             I18n.format(Constant.ENERGY_STORAGE_TEXT), ": ", 
                                             new PrefixNumber(this.getValue(stack)).toSplitString(),
                                             PhysicsConstant.STANDARD_ENERGY_UNIT, " / ",
                                             new PrefixNumber(this.getMaxValue()).toSplitString(),
                                             PhysicsConstant.STANDARD_ENERGY_UNIT));
    }

    public double getValue(ItemStack stack)
    {
        NBTTagCompound compound = stack.getTagCompound();

        if (compound == null)
        {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
            return 0.0;
        }

        return compound.getDouble(VALUE_TAG);
    }

    public void setValue(ItemStack stack, double value)
    {
        NBTTagCompound compound = stack.getTagCompound();

        if (compound == null)
        {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
        }

        compound.setDouble(VALUE_TAG, value);
    }

    public double getMaxValue()
    {
        return this.maxValue;
    }

    public void grow(ItemStack stack, double value)
    {
        double newValue = this.getValue(stack) + value;

        if (newValue > this.maxValue)
            newValue = this.maxValue;

        this.setValue(stack, newValue);
    }

    public void shrink(ItemStack stack, double value)
    {
        this.grow(stack, -value);
    }

    public boolean isFull(ItemStack stack)
    {
        return this.getValue(stack) >= this.getMaxValue();
    }
}