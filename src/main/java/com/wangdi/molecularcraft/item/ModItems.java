package com.wangdi.molecularcraft.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public final class ModItems
{
    // atoms
    public static final Item HYDROGEN_ATOM = new ItemElement.ItemHydrogen();
    public static final Item HELIUM_ATOM = new ItemElement.ItemHelium();
    public static final Item LITHIUM_ATOM = new ItemElement.ItemLithium();
    public static final Item BERYLLIUM_ATOM = new ItemElement.ItemBeryllium();
    public static final Item BORON_ATOM = new ItemElement.ItemBoron();
    public static final Item CARBON_ATOM = new ItemElement.ItemCarbon();
    public static final Item NITROGEN_ATOM = new ItemElement.ItemNitrogen();
    public static final Item OXYGEN_ATOM = new ItemElement.ItemOxygen();
    public static final Item FLUORINE_ATOM = new ItemElement.ItemFluorine();
    public static final Item NEON_ATOM = new ItemElement.ItemNeon();
    public static final Item SODIUM_ATOM = new ItemElement.ItemSodium();
    public static final Item MAGNESIUM_ATOM = new ItemElement.ItemMagnesium();
    public static final Item ALUMINIUM_ATOM = new ItemElement.ItemAluminium();
    public static final Item SILICON_ATOM = new ItemElement.ItemSilicon();
    public static final Item PHOSPHORUS_ATOM = new ItemElement.ItemPhosphorus();
    public static final Item SULFUR_ATOM = new ItemElement.ItemSulfur();
    public static final Item CHLORINE_ATOM = new ItemElement.ItemChlorine();
    public static final Item ARGON_ATOM = new ItemElement.ItemArgon();
    public static final Item POTASSIUM_ATOM = new ItemElement.ItemPotassium();
    public static final Item CALCIUM_ATOM = new ItemElement.ItemCalcium();
    public static final Item SCANDIUM_ATOM = new ItemElement.ItemScandium();
    public static final Item TITANIUM_ATOM = new ItemElement.ItemTitanium();
    public static final Item VANADIUM_ATOM = new ItemElement.ItemVanadium();
    public static final Item CHROMIUM_ATOM = new ItemElement.ItemChromium();
    public static final Item MANGANESE_ATOM = new ItemElement.ItemManganese();
    public static final Item IRON_ATOM = new ItemElement.ItemIron();
    public static final Item COBALT_ATOM = new ItemElement.ItemCobalt();
    public static final Item NICKEL_ATOM = new ItemElement.ItemNickel();
    public static final Item COPPER_ATOM = new ItemElement.ItemCopper();
    public static final Item ZINC_ATOM = new ItemElement.ItemZinc();
    public static final Item GALLIUM_ATOM = new ItemElement.ItemGallium();
    public static final Item GERMANIUM_ATOM = new ItemElement.ItemGermanium();
    public static final Item ARSENIC_ATOM = new ItemElement.ItemArsenic();
    public static final Item SELENIUM_ATOM = new ItemElement.ItemSelenium();
    public static final Item BROMINE_ATOM = new ItemElement.ItemBromine();
    public static final Item KRYPTON_ATOM = new ItemElement.ItemKrypton();
    public static final Item RUBIDIUM_ATOM = new ItemElement.ItemRubidium();
    public static final Item STRONTIUM_ATOM = new ItemElement.ItemStrontium();
    public static final Item YTTRIUM_ATOM = new ItemElement.ItemYttrium();
    public static final Item ZIRCONIUM_ATOM = new ItemElement.ItemZirconium();
    public static final Item NIOBIUM_ATOM = new ItemElement.ItemNiobium();
    public static final Item MOLYBDENUM_ATOM = new ItemElement.ItemMolybdenum();
    public static final Item TECHNETIUM_ATOM = new ItemElement.ItemTechnetium();
    public static final Item RUTHENIUM_ATOM = new ItemElement.ItemRuthenium();
    public static final Item RHODIUM_ATOM = new ItemElement.ItemRhodium();
    public static final Item PALLADIUM_ATOM = new ItemElement.ItemPalladium();
    public static final Item SILVER_ATOM = new ItemElement.ItemSilver();
    public static final Item CADMIUM_ATOM = new ItemElement.ItemCadmium();
    public static final Item INDIUM_ATOM = new ItemElement.ItemIndium();
    public static final Item TIN_ATOM = new ItemElement.ItemTin();
    public static final Item ANTIMONY_ATOM = new ItemElement.ItemAntimony();
    public static final Item TELLURIUM_ATOM = new ItemElement.ItemTellurium();
    public static final Item IODINE_ATOM = new ItemElement.ItemIodine();
    public static final Item XENON_ATOM = new ItemElement.ItemXenon();
    public static final Item CESIUM_ATOM = new ItemElement.ItemCesium();
    public static final Item BARIUM_ATOM = new ItemElement.ItemBarium();
    public static final Item LANTHANUM_ATOM = new ItemElement.ItemLanthanum();
    public static final Item CERIUM_ATOM = new ItemElement.ItemCerium();
    public static final Item PRASEODYMIUM_ATOM = new ItemElement.ItemPraseodymium();
    public static final Item NEODYMIUM_ATOM = new ItemElement.ItemNeodymium();
    public static final Item PROMETHIUM_ATOM = new ItemElement.ItemPromethium();
    public static final Item SAMARIUM_ATOM = new ItemElement.ItemSamarium();
    public static final Item EUROPIUM_ATOM = new ItemElement.ItemEuropium();
    public static final Item GADOLINIUM_ATOM = new ItemElement.ItemGadolinium();
    public static final Item TERBIUM_ATOM = new ItemElement.ItemTerbium();
    public static final Item DYSPROSIUM_ATOM = new ItemElement.ItemDysprosium();
    public static final Item HOLMIUM_ATOM = new ItemElement.ItemHolmium();
    public static final Item ERBIUM_ATOM = new ItemElement.ItemErbium();
    public static final Item THULIUM_ATOM = new ItemElement.ItemThulium();
    public static final Item YTTERBIUM_ATOM = new ItemElement.ItemYtterbium();
    public static final Item LUTETIUM_ATOM = new ItemElement.ItemLutetium();
    public static final Item HAFNIUM_ATOM = new ItemElement.ItemHafnium();
    public static final Item TANTALUM_ATOM = new ItemElement.ItemTantalum();
    public static final Item TUNGSTEN_ATOM = new ItemElement.ItemTungsten();
    public static final Item RHENIUM_ATOM = new ItemElement.ItemRhenium();
    public static final Item OSMIUM_ATOM = new ItemElement.ItemOsmium();
    public static final Item IRIDIUM_ATOM = new ItemElement.ItemIridium();
    public static final Item PLATINUM_ATOM = new ItemElement.ItemPlatinum();
    public static final Item GOLD_ATOM = new ItemElement.ItemGold();
    public static final Item MERCURY_ATOM = new ItemElement.ItemMercury();
    public static final Item THALLIUM_ATOM = new ItemElement.ItemThallium();
    public static final Item LEAD_ATOM = new ItemElement.ItemLead();
    public static final Item BISMUTH_ATOM = new ItemElement.ItemBismuth();
    public static final Item POLONIUM_ATOM = new ItemElement.ItemPolonium();
    public static final Item ASTATINE_ATOM = new ItemElement.ItemAstatine();
    public static final Item RADON_ATOM = new ItemElement.ItemRadon();
    public static final Item FRANCIUM_ATOM = new ItemElement.ItemFrancium();
    public static final Item RADIUM_ATOM = new ItemElement.ItemRadium();
    public static final Item ACTINIUM_ATOM = new ItemElement.ItemActinium();
    public static final Item THORIUM_ATOM = new ItemElement.ItemThorium();
    public static final Item PROTACTINIUM_ATOM = new ItemElement.ItemProtactinium();
    public static final Item URANIUM_ATOM = new ItemElement.ItemUranium();
    public static final Item NEPTUNIUM_ATOM = new ItemElement.ItemNeptunium();
    public static final Item PLUTONIUM_ATOM = new ItemElement.ItemPlutonium();
    public static final Item AMERICIUM_ATOM = new ItemElement.ItemAmericium();
    public static final Item CURIUM_ATOM = new ItemElement.ItemCurium();
    public static final Item BERKELIUM_ATOM = new ItemElement.ItemBerkelium();
    public static final Item CALIFORNIUM_ATOM = new ItemElement.ItemCalifornium();
    public static final Item EINSTEINIUM_ATOM = new ItemElement.ItemEinsteinium();
    public static final Item FERMIUM_ATOM = new ItemElement.ItemFermium();
    public static final Item MENDELEVIUM_ATOM = new ItemElement.ItemMendelevium();
    public static final Item NOBELIUM_ATOM = new ItemElement.ItemNobelium();
    public static final Item LAWRENCIUM_ATOM = new ItemElement.ItemLawrencium();
    public static final Item RUTHERFORDIUM_ATOM = new ItemElement.ItemRutherfordium();
    public static final Item DUBNIUM_ATOM = new ItemElement.ItemDubnium();
    public static final Item SEABORGIUM_ATOM = new ItemElement.ItemSeaborgium();
    public static final Item BOHRIUM_ATOM = new ItemElement.ItemBohrium();
    public static final Item HASSIUM_ATOM = new ItemElement.ItemHassium();
    public static final Item MEITNERIUM_ATOM = new ItemElement.ItemMeitnerium();
    public static final Item DARMSTADTIUM_ATOM = new ItemElement.ItemDarmstadtium();
    public static final Item ROENTGENIUM_ATOM = new ItemElement.ItemRoentgenium();
    public static final Item COPERNICIUM_ATOM = new ItemElement.ItemCopernicium();
    public static final Item NIHONIUM_ATOM = new ItemElement.ItemNihonium();
    public static final Item FLEROVIUM_ATOM = new ItemElement.ItemFlerovium();
    public static final Item MOSCOVIUM_ATOM = new ItemElement.ItemMoscovium();
    public static final Item LIVERMORIUM_ATOM = new ItemElement.ItemLivermorium();
    public static final Item TENNESSINE_ATOM = new ItemElement.ItemTennessine();
    public static final Item OGANESSON_ATOM = new ItemElement.ItemOganesson();

    // molecules
    public static final Item HYDROGEN = new ItemSubstance.ItemHydrogen();
    public static final Item HELIUM = new ItemSubstance.ItemHelium();
    public static final Item LITHIUM = new ItemSubstance.ItemLithium();
    public static final Item BERYLLIUM = new ItemSubstance.ItemBeryllium();
    public static final Item BORON = new ItemSubstance.ItemBoron();
    public static final Item CARBON = new ItemSubstance.ItemCarbon();
    public static final Item NITROGEN = new ItemSubstance.ItemNitrogen();
    public static final Item OXYGEN = new ItemSubstance.ItemOxygen();
    public static final Item FLUORINE = new ItemSubstance.ItemFluorine();
    public static final Item NEON = new ItemSubstance.ItemNeon();
    public static final Item SODIUM = new ItemSubstance.ItemSodium();
    public static final Item MAGNESIUM = new ItemSubstance.ItemMagnesium();
    public static final Item ALUMINIUM = new ItemSubstance.ItemAluminium();
    public static final Item SILICON = new ItemSubstance.ItemSilicon();
    public static final Item PHOSPHORUS = new ItemSubstance.ItemPhosphorus();
    public static final Item SULFUR = new ItemSubstance.ItemSulfur();
    public static final Item CHLORINE = new ItemSubstance.ItemChlorine();
    public static final Item ARGON = new ItemSubstance.ItemArgon();
    public static final Item POTASSIUM = new ItemSubstance.ItemPotassium();
    public static final Item CALCIUM = new ItemSubstance.ItemCalcium();
    public static final Item SCANDIUM = new ItemSubstance.ItemScandium();
    public static final Item TITANIUM = new ItemSubstance.ItemTitanium();
    public static final Item VANADIUM = new ItemSubstance.ItemVanadium();
    public static final Item CHROMIUM = new ItemSubstance.ItemChromium();
    public static final Item MANGANESE = new ItemSubstance.ItemManganese();
    public static final Item IRON = new ItemSubstance.ItemIron();
    public static final Item COBALT = new ItemSubstance.ItemCobalt();
    public static final Item NICKEL = new ItemSubstance.ItemNickel();
    public static final Item COPPER = new ItemSubstance.ItemCopper();
    public static final Item ZINC = new ItemSubstance.ItemZinc();
    public static final Item GALLIUM = new ItemSubstance.ItemGallium();
    public static final Item GERMANIUM = new ItemSubstance.ItemGermanium();
    public static final Item ARSENIC = new ItemSubstance.ItemArsenic();
    public static final Item SELENIUM = new ItemSubstance.ItemSelenium();
    public static final Item BROMINE = new ItemSubstance.ItemBromine();
    public static final Item KRYPTON = new ItemSubstance.ItemKrypton();
    public static final Item RUBIDIUM = new ItemSubstance.ItemRubidium();
    public static final Item STRONTIUM = new ItemSubstance.ItemStrontium();
    public static final Item YTTRIUM = new ItemSubstance.ItemYttrium();
    public static final Item ZIRCONIUM = new ItemSubstance.ItemZirconium();
    public static final Item NIOBIUM = new ItemSubstance.ItemNiobium();
    public static final Item MOLYBDENUM = new ItemSubstance.ItemMolybdenum();
    public static final Item TECHNETIUM = new ItemSubstance.ItemTechnetium();
    public static final Item RUTHENIUM = new ItemSubstance.ItemRuthenium();
    public static final Item RHODIUM = new ItemSubstance.ItemRhodium();
    public static final Item PALLADIUM = new ItemSubstance.ItemPalladium();
    public static final Item SILVER = new ItemSubstance.ItemSilver();
    public static final Item CADMIUM = new ItemSubstance.ItemCadmium();
    public static final Item INDIUM = new ItemSubstance.ItemIndium();
    public static final Item TIN = new ItemSubstance.ItemTin();
    public static final Item ANTIMONY = new ItemSubstance.ItemAntimony();
    public static final Item TELLURIUM = new ItemSubstance.ItemTellurium();
    public static final Item IODINE = new ItemSubstance.ItemIodine();
    public static final Item XENON = new ItemSubstance.ItemXenon();
    public static final Item CESIUM = new ItemSubstance.ItemCesium();
    public static final Item BARIUM = new ItemSubstance.ItemBarium();
    public static final Item LANTHANUM = new ItemSubstance.ItemLanthanum();
    public static final Item CERIUM = new ItemSubstance.ItemCerium();
    public static final Item PRASEODYMIUM = new ItemSubstance.ItemPraseodymium();
    public static final Item NEODYMIUM = new ItemSubstance.ItemNeodymium();
    public static final Item PROMETHIUM = new ItemSubstance.ItemPromethium();
    public static final Item SAMARIUM = new ItemSubstance.ItemSamarium();
    public static final Item EUROPIUM = new ItemSubstance.ItemEuropium();
    public static final Item GADOLINIUM = new ItemSubstance.ItemGadolinium();
    public static final Item TERBIUM = new ItemSubstance.ItemTerbium();
    public static final Item DYSPROSIUM = new ItemSubstance.ItemDysprosium();
    public static final Item HOLMIUM = new ItemSubstance.ItemHolmium();
    public static final Item ERBIUM = new ItemSubstance.ItemErbium();
    public static final Item THULIUM = new ItemSubstance.ItemThulium();
    public static final Item YTTERBIUM = new ItemSubstance.ItemYtterbium();
    public static final Item LUTETIUM = new ItemSubstance.ItemLutetium();
    public static final Item HAFNIUM = new ItemSubstance.ItemHafnium();
    public static final Item TANTALUM = new ItemSubstance.ItemTantalum();
    public static final Item TUNGSTEN = new ItemSubstance.ItemTungsten();
    public static final Item RHENIUM = new ItemSubstance.ItemRhenium();
    public static final Item OSMIUM = new ItemSubstance.ItemOsmium();
    public static final Item IRIDIUM = new ItemSubstance.ItemIridium();
    public static final Item PLATINUM = new ItemSubstance.ItemPlatinum();
    public static final Item GOLD = new ItemSubstance.ItemGold();
    public static final Item MERCURY = new ItemSubstance.ItemMercury();
    public static final Item THALLIUM = new ItemSubstance.ItemThallium();
    public static final Item LEAD = new ItemSubstance.ItemLead();
    public static final Item BISMUTH = new ItemSubstance.ItemBismuth();
    public static final Item POLONIUM = new ItemSubstance.ItemPolonium();
    public static final Item ASTATINE = new ItemSubstance.ItemAstatine();
    public static final Item RADON = new ItemSubstance.ItemRadon();
    public static final Item FRANCIUM = new ItemSubstance.ItemFrancium();
    public static final Item RADIUM = new ItemSubstance.ItemRadium();
    public static final Item ACTINIUM = new ItemSubstance.ItemActinium();
    public static final Item THORIUM = new ItemSubstance.ItemThorium();
    public static final Item PROTACTINIUM = new ItemSubstance.ItemProtactinium();
    public static final Item URANIUM = new ItemSubstance.ItemUranium();
    public static final Item NEPTUNIUM = new ItemSubstance.ItemNeptunium();
    public static final Item PLUTONIUM = new ItemSubstance.ItemPlutonium();
    public static final Item AMERICIUM = new ItemSubstance.ItemAmericium();
    public static final Item CURIUM = new ItemSubstance.ItemCurium();
    public static final Item BERKELIUM = new ItemSubstance.ItemBerkelium();
    public static final Item CALIFORNIUM = new ItemSubstance.ItemCalifornium();
    public static final Item EINSTEINIUM = new ItemSubstance.ItemEinsteinium();
    public static final Item FERMIUM = new ItemSubstance.ItemFermium();
    public static final Item MENDELEVIUM = new ItemSubstance.ItemMendelevium();
    public static final Item NOBELIUM = new ItemSubstance.ItemNobelium();
    public static final Item LAWRENCIUM = new ItemSubstance.ItemLawrencium();
    public static final Item RUTHERFORDIUM = new ItemSubstance.ItemRutherfordium();
    public static final Item DUBNIUM = new ItemSubstance.ItemDubnium();
    public static final Item SEABORGIUM = new ItemSubstance.ItemSeaborgium();
    public static final Item BOHRIUM = new ItemSubstance.ItemBohrium();
    public static final Item HASSIUM = new ItemSubstance.ItemHassium();
    public static final Item MEITNERIUM = new ItemSubstance.ItemMeitnerium();
    public static final Item DARMSTADTIUM = new ItemSubstance.ItemDarmstadtium();
    public static final Item ROENTGENIUM = new ItemSubstance.ItemRoentgenium();
    public static final Item COPERNICIUM = new ItemSubstance.ItemCopernicium();
    public static final Item NIHONIUM = new ItemSubstance.ItemNihonium();
    public static final Item FLEROVIUM = new ItemSubstance.ItemFlerovium();
    public static final Item MOSCOVIUM = new ItemSubstance.ItemMoscovium();
    public static final Item LIVERMORIUM = new ItemSubstance.ItemLivermorium();
    public static final Item TENNESSINE = new ItemSubstance.ItemTennessine();
    public static final Item OGANESSON = new ItemSubstance.ItemOganesson();

    // substance
    public static final Item WATER = new ItemSubstance.ItemWater();

    // misc
    public static final Item PROTON = new ItemParticle.ItemProton();
    public static final Item NEUTRON = new ItemParticle.ItemNeutron();
    public static final Item ENERGY_BATTERY = new ItemContainer.ItemEnergyBattery();
    public static final Item GENERATOR = new ItemGenerator();
    public static final Item CONTROL_ROD = new ItemControlRod();
    public static final Item FUEL_ROD = new ItemFuelRod();
    public static final Item BUCKET_NUCLEAR_CONTAMINATED_WATER = new ItemBucketNuclearContaminatedWater();

    public static final Item RADIATION_SUIT_HELMET = ItemArmorBased.RadiationSuit.getHelmet();
    public static final Item RADIATION_SUIT_CHESTPLATE = ItemArmorBased.RadiationSuit.getChestplate();
    public static final Item RADIATION_SUIT_LEGGINGS = ItemArmorBased.RadiationSuit.getLeggings();
    public static final Item RADIATION_SUIT_BOOTS = ItemArmorBased.RadiationSuit.getBoots();

    public static final Item[] ELEMENTS =
    {
        HYDROGEN_ATOM, HELIUM_ATOM, LITHIUM_ATOM, BERYLLIUM_ATOM, BORON_ATOM, CARBON_ATOM, NITROGEN_ATOM, OXYGEN_ATOM, FLUORINE_ATOM, NEON_ATOM,
        SODIUM_ATOM, MAGNESIUM_ATOM, ALUMINIUM_ATOM, SILICON_ATOM, PHOSPHORUS_ATOM, SULFUR_ATOM, CHLORINE_ATOM, ARGON_ATOM, POTASSIUM_ATOM, CALCIUM_ATOM,
        SCANDIUM_ATOM, TITANIUM_ATOM, VANADIUM_ATOM, CHROMIUM_ATOM, MANGANESE_ATOM, IRON_ATOM, COBALT_ATOM, NICKEL_ATOM, COPPER_ATOM, ZINC_ATOM,
        GALLIUM_ATOM, GERMANIUM_ATOM, ARSENIC_ATOM, SELENIUM_ATOM, BROMINE_ATOM, KRYPTON_ATOM, RUBIDIUM_ATOM, STRONTIUM_ATOM, YTTRIUM_ATOM, ZIRCONIUM_ATOM,
        NIOBIUM_ATOM, MOLYBDENUM_ATOM, TECHNETIUM_ATOM, RUTHENIUM_ATOM, RHODIUM_ATOM, PALLADIUM_ATOM, SILVER_ATOM, CADMIUM_ATOM, INDIUM_ATOM, TIN_ATOM,
        ANTIMONY_ATOM, TELLURIUM_ATOM, IODINE_ATOM, XENON_ATOM, CESIUM_ATOM, BARIUM_ATOM, LANTHANUM_ATOM, CERIUM_ATOM, PRASEODYMIUM_ATOM, NEODYMIUM_ATOM,
        PROMETHIUM_ATOM, SAMARIUM_ATOM, EUROPIUM_ATOM, GADOLINIUM_ATOM, TERBIUM_ATOM, DYSPROSIUM_ATOM, HOLMIUM_ATOM, ERBIUM_ATOM, THULIUM_ATOM, YTTERBIUM_ATOM,
        LUTETIUM_ATOM, HAFNIUM_ATOM, TANTALUM_ATOM, TUNGSTEN_ATOM, RHENIUM_ATOM, OSMIUM_ATOM, IRIDIUM_ATOM, PLATINUM_ATOM, GOLD_ATOM, MERCURY_ATOM,
        THALLIUM_ATOM, LEAD_ATOM, BISMUTH_ATOM, POLONIUM_ATOM, ASTATINE_ATOM, RADON_ATOM, FRANCIUM_ATOM, RADIUM_ATOM, ACTINIUM_ATOM, THORIUM_ATOM,
        PROTACTINIUM_ATOM, URANIUM_ATOM, NEPTUNIUM_ATOM, PLUTONIUM_ATOM, AMERICIUM_ATOM, CURIUM_ATOM, BERKELIUM_ATOM, CALIFORNIUM_ATOM, EINSTEINIUM_ATOM, FERMIUM_ATOM,
        MENDELEVIUM_ATOM, NOBELIUM_ATOM, LAWRENCIUM_ATOM, RUTHERFORDIUM_ATOM, DUBNIUM_ATOM, SEABORGIUM_ATOM, BOHRIUM_ATOM, HASSIUM_ATOM, MEITNERIUM_ATOM, DARMSTADTIUM_ATOM,
        ROENTGENIUM_ATOM, COPERNICIUM_ATOM, NIHONIUM_ATOM, FLEROVIUM_ATOM, MOSCOVIUM_ATOM, LIVERMORIUM_ATOM, TENNESSINE_ATOM, OGANESSON_ATOM
    };

    public static final Item[] ELEMENTARY_SUBSISTANCES =
    {
        HYDROGEN, HELIUM, LITHIUM, BERYLLIUM, BORON, CARBON, NITROGEN, OXYGEN, FLUORINE, NEON,
        SODIUM, MAGNESIUM, ALUMINIUM, SILICON, PHOSPHORUS, SULFUR, CHLORINE, ARGON, POTASSIUM, CALCIUM,
        SCANDIUM, TITANIUM, VANADIUM, CHROMIUM, MANGANESE, IRON, COBALT, NICKEL, COPPER, ZINC,
        GALLIUM, GERMANIUM, ARSENIC, SELENIUM, BROMINE, KRYPTON, RUBIDIUM, STRONTIUM, YTTRIUM, ZIRCONIUM,
        NIOBIUM, MOLYBDENUM, TECHNETIUM, RUTHENIUM, RHODIUM, PALLADIUM, SILVER, CADMIUM, INDIUM, TIN,
        ANTIMONY, TELLURIUM, IODINE, XENON, CESIUM, BARIUM, LANTHANUM, CERIUM, PRASEODYMIUM, NEODYMIUM,
        PROMETHIUM, SAMARIUM, EUROPIUM, GADOLINIUM, TERBIUM, DYSPROSIUM, HOLMIUM, ERBIUM, THULIUM, YTTERBIUM,
        LUTETIUM, HAFNIUM, TANTALUM, TUNGSTEN, RHENIUM, OSMIUM, IRIDIUM, PLATINUM, GOLD, MERCURY,
        THALLIUM, LEAD, BISMUTH, POLONIUM, ASTATINE, RADON, FRANCIUM, RADIUM, ACTINIUM, THORIUM,
        PROTACTINIUM, URANIUM, NEPTUNIUM, PLUTONIUM, AMERICIUM, CURIUM, BERKELIUM, CALIFORNIUM, EINSTEINIUM, FERMIUM,
        MENDELEVIUM, NOBELIUM, LAWRENCIUM, RUTHERFORDIUM, DUBNIUM, SEABORGIUM, BOHRIUM, HASSIUM, MEITNERIUM, DARMSTADTIUM,
        ROENTGENIUM, COPERNICIUM, NIHONIUM, FLEROVIUM, MOSCOVIUM, LIVERMORIUM, TENNESSINE, OGANESSON
    };

    public static final Item[] SUBSISTANCES = new Item[]
    {

    };

    public static final Item[] MISC = new Item[]
    {
        PROTON, NEUTRON, ENERGY_BATTERY, GENERATOR, CONTROL_ROD, FUEL_ROD, BUCKET_NUCLEAR_CONTAMINATED_WATER,
        RADIATION_SUIT_HELMET, RADIATION_SUIT_CHESTPLATE, RADIATION_SUIT_LEGGINGS, RADIATION_SUIT_BOOTS
    };

    public static final List<Item[]> ITEMS_ARRAY = new ArrayList<Item[]>(4)
    {
        {
            this.add(ELEMENTS);
            this.add(ELEMENTARY_SUBSISTANCES);
            this.add(SUBSISTANCES);
            this.add(MISC);
        }
    };
    public static final Item[] ITEMS = mergeItems(ITEMS_ARRAY);

    private static Item[] mergeItems(List<Item[]> itemLists)
    {
        int totalLength = 0, index = 0;
        for (Item[] list : itemLists) totalLength += list.length;
        Item[] result = new Item[totalLength];

        for (Item[] list : itemLists)
        {
            for (Item item : list)
                result[index++] = item;
        }

        return result;
    }
}