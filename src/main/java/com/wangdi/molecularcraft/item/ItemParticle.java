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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemParticle extends Item
{
    public static final String NEUTRON_NAME = "neutron";
    public static final String PROTON_NAME = "proton";
    public static final class ItemProton extends ItemParticle {public ItemProton() {super(PROTON_NAME, PhysicsConstant.PROTON_MASS);}}
    public static final class ItemNeutron extends ItemParticle {public ItemNeutron() {super(NEUTRON_NAME, PhysicsConstant.NEUTRON_MASS);}}

    public double mass;

    public ItemParticle(String name, double mass)
    {
        this.mass = mass;
        
        this.setRegistryName(name);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", name));
        this.setCreativeTab(ModCreativeTabs.MISC);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("null")
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        PrefixNumber mass_PrefixNumber = new PrefixNumber(this.mass * PhysicsConstant.NA * PhysicsConstant.u);
        String name = MainUtil.combinedStrings(TextFormatting.GOLD.toString(), this.getItemStackDisplayName(stack));

        if (!tooltip.isEmpty()) tooltip.remove(tooltip.size() - 1);
        if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips)
            name += MainUtil.combinedStrings(TextFormatting.WHITE.toString(), " (#", Integer.toString(Item.getIdFromItem(this)), ")");

        tooltip.add(name);
        tooltip.add(MainUtil.combinedStrings(TextFormatting.GREEN.toString(), I18n.format(Constant.MASS_TEXT), ": ", mass_PrefixNumber.toSplitString(), PhysicsConstant.STANDARD_MASS_UNIT));
    }
}