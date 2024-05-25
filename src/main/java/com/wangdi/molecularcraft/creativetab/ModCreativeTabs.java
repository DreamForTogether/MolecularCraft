package com.wangdi.molecularcraft.creativetab;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.ModBlocks;
import com.wangdi.molecularcraft.item.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ModCreativeTabs
{
    public static final String PARTIVLES_CREATIVETAB_NAME = MolecularCraft.MODID + "_particles";
    public static final String ELEMENTS_CREATIVETAB_NAME = MolecularCraft.MODID + "_elements";
    public static final String ELEMENTARY_SUBSTANCES_CREATIVETAB_NAME = MolecularCraft.MODID + "_elementary_substances";
    public static final String SUBSTANCES_CREATIVETAB_NAME = MolecularCraft.MODID + "_substances";
    public static final String BLOCKS_CREATIVETAB_NAME = MolecularCraft.MODID + "_blocks";
    public static final String TOOLS_NAME = MolecularCraft.MODID + "_tools";
    public static final String MISC_NAME = MolecularCraft.MODID + "_misc";

    public static final CreativeTabs ELEMENTS = new ModBaseCreativeTabs(ELEMENTS_CREATIVETAB_NAME, ModItems.HYDROGEN_ATOM, ModItems.ELEMENTS);
    public static final CreativeTabs ELEMENTARY_SUBSTANCES = new ModBaseCreativeTabs(ELEMENTARY_SUBSTANCES_CREATIVETAB_NAME, ModItems.HYDROGEN_ATOM, ModItems.ELEMENTARY_SUBSISTANCES);
    public static final CreativeTabs SUBSTANCES = new ModBaseCreativeTabs(SUBSTANCES_CREATIVETAB_NAME, ModItems.WATER, ModItems.SUBSISTANCES);
    
    public static final CreativeTabs BLOCKS = new CreativeTabs(BLOCKS_CREATIVETAB_NAME)
    {
        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModBlocks.ELEMENT_DECOMPOSER);
        }
    };

    public static final CreativeTabs MISC = new ModBaseCreativeTabs(MISC_NAME, ModItems.BUCKET_NUCLEAR_CONTAMINATED_WATER, ModItems.MISC);

    public static final CreativeTabs[] CREATIVE_TABS = new CreativeTabs[] {ELEMENTS, ELEMENTARY_SUBSTANCES, SUBSTANCES, BLOCKS, MISC};
}