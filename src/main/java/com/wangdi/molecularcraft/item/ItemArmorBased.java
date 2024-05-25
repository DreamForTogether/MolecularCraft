package com.wangdi.molecularcraft.item;

import java.util.HashMap;
import java.util.Map;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.util.MainUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemArmorBased extends ItemArmor
{
    private static final Map<EntityEquipmentSlot, String> NAME_TABLE = new HashMap<EntityEquipmentSlot, String>()
    {
        {
            this.put(EntityEquipmentSlot.HEAD, "_helmet");
            this.put(EntityEquipmentSlot.CHEST, "_chestplate");
            this.put(EntityEquipmentSlot.LEGS, "_leggings");
            this.put(EntityEquipmentSlot.FEET, "_boots");
        }
    };

    public static interface IItemArmor
    {
        public Item getHelmet();
        public Item getChestplate();
        public Item getLeggings();
        public Item getBoots();
    }

    public static class RadiationSuit extends ItemArmorBased
    {
        public static final String NAME = "radiation_suit";

        public static Item getHelmet()
        {
            return new RadiationSuit(EntityEquipmentSlot.HEAD);
        }

        public static Item getChestplate()
        {
            return new RadiationSuit(EntityEquipmentSlot.CHEST);
        }

        public static Item getLeggings()
        {
            return new RadiationSuit(EntityEquipmentSlot.LEGS);
        }

        public static Item getBoots()
        {
            return new RadiationSuit(EntityEquipmentSlot.FEET);
        }

        public RadiationSuit(EntityEquipmentSlot equipmentSlotIn)
        {
            super(NAME + NAME_TABLE.get(equipmentSlotIn), ArmorMaterial.IRON, 1, equipmentSlotIn, ModCreativeTabs.MISC);
        }
    }

    public ItemArmorBased(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, CreativeTabs tab)
    {
		super(materialIn, renderIndexIn, equipmentSlotIn);

		this.setRegistryName(name);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", name));
		this.setCreativeTab(tab);
	}
}