package com.wangdi.molecularcraft.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;
import com.wangdi.util.chemistry.ChemicalFormula;
import com.wangdi.util.chemistry.ChemicalSymbol;
import com.wangdi.util.chemistry.SubstanceHelper;
import com.wangdi.util.physics.PhysicsConstant;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSubstance extends Item
{
    public static final int TIME_PER_MOLECULAR = 100;

    private static final Map<String, Item> ITEMS_TABLE = new HashMap<String, Item>()
    {
        {
            this.put(ChemicalSymbol.HYDROGEN, ModItems.HYDROGEN_ATOM);
            this.put(ChemicalSymbol.HELIUM, ModItems.HELIUM_ATOM);
            this.put(ChemicalSymbol.LITHIUM, ModItems.LITHIUM_ATOM);
            this.put(ChemicalSymbol.BERYLLIUM, ModItems.BERYLLIUM_ATOM);
            this.put(ChemicalSymbol.BORON, ModItems.BORON_ATOM);
            this.put(ChemicalSymbol.CARBON, ModItems.CARBON_ATOM);
            this.put(ChemicalSymbol.NITROGEN, ModItems.NITROGEN_ATOM);
            this.put(ChemicalSymbol.OXYGEN, ModItems.OXYGEN_ATOM);
            this.put(ChemicalSymbol.FLUORINE, ModItems.FLUORINE_ATOM);
            this.put(ChemicalSymbol.NEON, ModItems.NEON_ATOM);
            this.put(ChemicalSymbol.SODIUM, ModItems.SODIUM_ATOM);
            this.put(ChemicalSymbol.MAGNESIUM, ModItems.MAGNESIUM_ATOM);
            this.put(ChemicalSymbol.ALUMINIUM, ModItems.ALUMINIUM_ATOM);
            this.put(ChemicalSymbol.SILICON, ModItems.SILICON_ATOM);
            this.put(ChemicalSymbol.PHOSPHORUS, ModItems.PHOSPHORUS_ATOM);
            this.put(ChemicalSymbol.SULFUR, ModItems.SULFUR_ATOM);
            this.put(ChemicalSymbol.CHLORINE, ModItems.CHLORINE_ATOM);
            this.put(ChemicalSymbol.ARGON, ModItems.ARGON_ATOM);
            this.put(ChemicalSymbol.POTASSIUM, ModItems.POTASSIUM_ATOM);
            this.put(ChemicalSymbol.CALCIUM, ModItems.CALCIUM_ATOM);
            this.put(ChemicalSymbol.SCANDIUM, ModItems.SCANDIUM_ATOM);
            this.put(ChemicalSymbol.TITANIUM, ModItems.TITANIUM_ATOM);
            this.put(ChemicalSymbol.VANADIUM, ModItems.VANADIUM_ATOM);
            this.put(ChemicalSymbol.CHROMIUM, ModItems.CHROMIUM_ATOM);
            this.put(ChemicalSymbol.MANGANESE, ModItems.MANGANESE_ATOM);
            this.put(ChemicalSymbol.IRON, ModItems.IRON_ATOM);
            this.put(ChemicalSymbol.COBALT, ModItems.COBALT_ATOM);
            this.put(ChemicalSymbol.NICKEL, ModItems.NICKEL_ATOM);
            this.put(ChemicalSymbol.COPPER, ModItems.COPPER_ATOM);
            this.put(ChemicalSymbol.ZINC, ModItems.ZINC_ATOM);
            this.put(ChemicalSymbol.GALLIUM, ModItems.GALLIUM_ATOM);
            this.put(ChemicalSymbol.GERMANIUM, ModItems.GERMANIUM_ATOM);
            this.put(ChemicalSymbol.ARSENIC, ModItems.ARSENIC_ATOM);
            this.put(ChemicalSymbol.SELENIUM, ModItems.SELENIUM_ATOM);
            this.put(ChemicalSymbol.BROMINE, ModItems.BROMINE_ATOM);
            this.put(ChemicalSymbol.KRYPTON, ModItems.KRYPTON_ATOM);
            this.put(ChemicalSymbol.RUBIDIUM, ModItems.RUBIDIUM_ATOM);
            this.put(ChemicalSymbol.STRONTIUM, ModItems.STRONTIUM_ATOM);
            this.put(ChemicalSymbol.YTTRIUM, ModItems.YTTRIUM_ATOM);
            this.put(ChemicalSymbol.ZIRCONIUM, ModItems.ZIRCONIUM_ATOM);
            this.put(ChemicalSymbol.NIOBIUM, ModItems.NIOBIUM_ATOM);
            this.put(ChemicalSymbol.MOLYBDENUM, ModItems.MOLYBDENUM_ATOM);
            this.put(ChemicalSymbol.TECHNETIUM, ModItems.TECHNETIUM_ATOM);
            this.put(ChemicalSymbol.RUTHENIUM, ModItems.RUTHENIUM_ATOM);
            this.put(ChemicalSymbol.RHODIUM, ModItems.RHODIUM_ATOM);
            this.put(ChemicalSymbol.PALLADIUM, ModItems.PALLADIUM_ATOM);
            this.put(ChemicalSymbol.SILVER, ModItems.SILVER_ATOM);
            this.put(ChemicalSymbol.CADMIUM, ModItems.CADMIUM_ATOM);
            this.put(ChemicalSymbol.INDIUM, ModItems.INDIUM_ATOM);
            this.put(ChemicalSymbol.TIN, ModItems.TIN_ATOM);
            this.put(ChemicalSymbol.ANTIMONY, ModItems.ANTIMONY_ATOM);
            this.put(ChemicalSymbol.TELLURIUM, ModItems.TELLURIUM_ATOM);
            this.put(ChemicalSymbol.IODINE, ModItems.IODINE_ATOM);
            this.put(ChemicalSymbol.XENON, ModItems.XENON_ATOM);
            this.put(ChemicalSymbol.CESIUM, ModItems.CESIUM_ATOM);
            this.put(ChemicalSymbol.BARIUM, ModItems.BARIUM_ATOM);
            this.put(ChemicalSymbol.LANTHANUM, ModItems.LANTHANUM_ATOM);
            this.put(ChemicalSymbol.CERIUM, ModItems.CERIUM_ATOM);
            this.put(ChemicalSymbol.PRASEODYMIUM, ModItems.PRASEODYMIUM_ATOM);
            this.put(ChemicalSymbol.NEODYMIUM, ModItems.NEODYMIUM_ATOM);
            this.put(ChemicalSymbol.PROMETHIUM, ModItems.PROMETHIUM_ATOM);
            this.put(ChemicalSymbol.SAMARIUM, ModItems.SAMARIUM_ATOM);
            this.put(ChemicalSymbol.EUROPIUM, ModItems.EUROPIUM_ATOM);
            this.put(ChemicalSymbol.GADOLINIUM, ModItems.GADOLINIUM_ATOM);
            this.put(ChemicalSymbol.TERBIUM, ModItems.TERBIUM_ATOM);
            this.put(ChemicalSymbol.DYSPROSIUM, ModItems.DYSPROSIUM_ATOM);
            this.put(ChemicalSymbol.HOLMIUM, ModItems.HOLMIUM_ATOM);
            this.put(ChemicalSymbol.ERBIUM, ModItems.ERBIUM_ATOM);
            this.put(ChemicalSymbol.THULIUM, ModItems.THULIUM_ATOM);
            this.put(ChemicalSymbol.YTTERBIUM, ModItems.YTTERBIUM_ATOM);
            this.put(ChemicalSymbol.LUTETIUM, ModItems.LUTETIUM_ATOM);
            this.put(ChemicalSymbol.HAFNIUM, ModItems.HAFNIUM_ATOM);
            this.put(ChemicalSymbol.TANTALUM, ModItems.TANTALUM_ATOM);
            this.put(ChemicalSymbol.TUNGSTEN, ModItems.TUNGSTEN_ATOM);
            this.put(ChemicalSymbol.RHENIUM, ModItems.RHENIUM_ATOM);
            this.put(ChemicalSymbol.OSMIUM, ModItems.OSMIUM_ATOM);
            this.put(ChemicalSymbol.IRIDIUM, ModItems.IRIDIUM_ATOM);
            this.put(ChemicalSymbol.PLATINUM, ModItems.PLATINUM_ATOM);
            this.put(ChemicalSymbol.GOLD, ModItems.GOLD_ATOM);
            this.put(ChemicalSymbol.MERCURY, ModItems.MERCURY_ATOM);
            this.put(ChemicalSymbol.THALLIUM, ModItems.THALLIUM_ATOM);
            this.put(ChemicalSymbol.LEAD, ModItems.LEAD_ATOM);
            this.put(ChemicalSymbol.BISMUTH, ModItems.BISMUTH_ATOM);
            this.put(ChemicalSymbol.POLONIUM, ModItems.POLONIUM_ATOM);
            this.put(ChemicalSymbol.ASTATINE, ModItems.ASTATINE_ATOM);
            this.put(ChemicalSymbol.RADON, ModItems.RADON_ATOM);
            this.put(ChemicalSymbol.FRANCIUM, ModItems.FRANCIUM_ATOM);
            this.put(ChemicalSymbol.RADIUM, ModItems.RADIUM_ATOM);
            this.put(ChemicalSymbol.ACTINIUM, ModItems.ACTINIUM_ATOM);
            this.put(ChemicalSymbol.THORIUM, ModItems.THORIUM_ATOM);
            this.put(ChemicalSymbol.PROTACTINIUM, ModItems.PROTACTINIUM_ATOM);
            this.put(ChemicalSymbol.URANIUM, ModItems.URANIUM_ATOM);
            this.put(ChemicalSymbol.NEPTUNIUM, ModItems.NEPTUNIUM_ATOM);
            this.put(ChemicalSymbol.PLUTONIUM, ModItems.PLUTONIUM_ATOM);
            this.put(ChemicalSymbol.AMERICIUM, ModItems.AMERICIUM_ATOM);
            this.put(ChemicalSymbol.CURIUM, ModItems.CURIUM_ATOM);
            this.put(ChemicalSymbol.BERKELIUM, ModItems.BERKELIUM_ATOM);
            this.put(ChemicalSymbol.CALIFORNIUM, ModItems.CALIFORNIUM_ATOM);
            this.put(ChemicalSymbol.EINSTEINIUM, ModItems.EINSTEINIUM_ATOM);
            this.put(ChemicalSymbol.FERMIUM, ModItems.FERMIUM_ATOM);
            this.put(ChemicalSymbol.MENDELEVIUM, ModItems.MENDELEVIUM_ATOM);
            this.put(ChemicalSymbol.NOBELIUM, ModItems.NOBELIUM_ATOM);
            this.put(ChemicalSymbol.LAWRENCIUM, ModItems.LAWRENCIUM_ATOM);
            this.put(ChemicalSymbol.RUTHERFORDIUM, ModItems.RUTHERFORDIUM_ATOM);
            this.put(ChemicalSymbol.DUBNIUM, ModItems.DUBNIUM_ATOM);
            this.put(ChemicalSymbol.SEABORGIUM, ModItems.SEABORGIUM_ATOM);
            this.put(ChemicalSymbol.BOHRIUM, ModItems.BOHRIUM_ATOM);
            this.put(ChemicalSymbol.HASSIUM, ModItems.HASSIUM_ATOM);
            this.put(ChemicalSymbol.MEITNERIUM, ModItems.MEITNERIUM_ATOM);
            this.put(ChemicalSymbol.DARMSTADTIUM, ModItems.DARMSTADTIUM_ATOM);
            this.put(ChemicalSymbol.ROENTGENIUM, ModItems.ROENTGENIUM_ATOM);
            this.put(ChemicalSymbol.COPERNICIUM, ModItems.COPERNICIUM_ATOM);
            this.put(ChemicalSymbol.NIHONIUM, ModItems.NIHONIUM_ATOM);
            this.put(ChemicalSymbol.FLEROVIUM, ModItems.FLEROVIUM_ATOM);
            this.put(ChemicalSymbol.MOSCOVIUM, ModItems.MOSCOVIUM_ATOM);
            this.put(ChemicalSymbol.LIVERMORIUM, ModItems.LIVERMORIUM_ATOM);
            this.put(ChemicalSymbol.TENNESSINE, ModItems.TENNESSINE_ATOM);
            this.put(ChemicalSymbol.OGANESSON, ModItems.OGANESSON_ATOM);
        }
    };

    public static Item getItemBySymbol(String chemicalSymbol)
    {
        if (ITEMS_TABLE.containsKey(chemicalSymbol))
            return ITEMS_TABLE.get(chemicalSymbol);
        else
            throw new NullPointerException("Cannot get Item for \"" + chemicalSymbol + "\"");
    }

    public static boolean isSubstance(Item item)
    {
        return item != null && item instanceof ItemSubstance;
    }

    public static final class ItemHydrogen extends ItemSubstance {public ItemHydrogen() {super(ChemicalFormula.HYDROGEN);}}
    public static final class ItemHelium extends ItemSubstance {public ItemHelium() {super(ChemicalFormula.HELIUM);}}
    public static final class ItemLithium extends ItemSubstance {public ItemLithium() {super(ChemicalFormula.LITHIUM);}}
    public static final class ItemBeryllium extends ItemSubstance {public ItemBeryllium() {super(ChemicalFormula.BERYLLIUM);}}
    public static final class ItemBoron extends ItemSubstance {public ItemBoron() {super(ChemicalFormula.BORON);}}
    public static final class ItemCarbon extends ItemSubstance {public ItemCarbon() {super(ChemicalFormula.CARBON);}}
    public static final class ItemNitrogen extends ItemSubstance {public ItemNitrogen() {super(ChemicalFormula.NITROGEN);}}
    public static final class ItemOxygen extends ItemSubstance {public ItemOxygen() {super(ChemicalFormula.OXYGEN);}}
    public static final class ItemFluorine extends ItemSubstance {public ItemFluorine() {super(ChemicalFormula.FLUORINE);}}
    public static final class ItemNeon extends ItemSubstance {public ItemNeon() {super(ChemicalFormula.NEON);}}
    public static final class ItemSodium extends ItemSubstance {public ItemSodium() {super(ChemicalFormula.SODIUM);}}
    public static final class ItemMagnesium extends ItemSubstance {public ItemMagnesium() {super(ChemicalFormula.MAGNESIUM);}}
    public static final class ItemAluminium extends ItemSubstance {public ItemAluminium() {super(ChemicalFormula.ALUMINIUM);}}
    public static final class ItemSilicon extends ItemSubstance {public ItemSilicon() {super(ChemicalFormula.SILICON);}}
    public static final class ItemPhosphorus extends ItemSubstance {public ItemPhosphorus() {super(ChemicalFormula.PHOSPHORUS);}}
    public static final class ItemSulfur extends ItemSubstance {public ItemSulfur() {super(ChemicalFormula.SULFUR);}}
    public static final class ItemChlorine extends ItemSubstance {public ItemChlorine() {super(ChemicalFormula.CHLORINE);}}
    public static final class ItemArgon extends ItemSubstance {public ItemArgon() {super(ChemicalFormula.ARGON);}}
    public static final class ItemPotassium extends ItemSubstance {public ItemPotassium() {super(ChemicalFormula.POTASSIUM);}}
    public static final class ItemCalcium extends ItemSubstance {public ItemCalcium() {super(ChemicalFormula.CALCIUM);}}
    public static final class ItemScandium extends ItemSubstance {public ItemScandium(){super(ChemicalFormula.SCANDIUM);}}
    public static final class ItemTitanium extends ItemSubstance {public ItemTitanium() {super(ChemicalFormula.TITANIUM);}}
    public static final class ItemVanadium extends ItemSubstance {public ItemVanadium() {super(ChemicalFormula.VANADIUM);}}
    public static final class ItemChromium extends ItemSubstance {public ItemChromium() {super(ChemicalFormula.CHROMIUM);}}
    public static final class ItemManganese extends ItemSubstance {public ItemManganese() {super(ChemicalFormula.MANGANESE);}}
    public static final class ItemIron extends ItemSubstance {public ItemIron() {super(ChemicalFormula.IRON);}}
    public static final class ItemCobalt extends ItemSubstance {public ItemCobalt() {super(ChemicalFormula.COBALT);}}
    public static final class ItemNickel extends ItemSubstance {public ItemNickel() {super(ChemicalFormula.NICKEL);}}
    public static final class ItemCopper extends ItemSubstance {public ItemCopper() {super(ChemicalFormula.COPPER);}}
    public static final class ItemZinc extends ItemSubstance {public ItemZinc() {super(ChemicalFormula.ZINC);}}
    public static final class ItemGallium extends ItemSubstance {public ItemGallium() {super(ChemicalFormula.GALLIUM);}}
    public static final class ItemGermanium extends ItemSubstance {public ItemGermanium() {super(ChemicalFormula.GERMANIUM);}}
    public static final class ItemArsenic extends ItemSubstance {public ItemArsenic() {super(ChemicalFormula.ARSENIC);}}
    public static final class ItemSelenium extends ItemSubstance {public ItemSelenium() {super(ChemicalFormula.SELENIUM);}}
    public static final class ItemBromine extends ItemSubstance {public ItemBromine() {super(ChemicalFormula.BROMINE);}}
    public static final class ItemKrypton extends ItemSubstance {public ItemKrypton() {super(ChemicalFormula.KRYPTON);}}
    public static final class ItemRubidium extends ItemSubstance {public ItemRubidium() {super(ChemicalFormula.RUBIDIUM);}}
    public static final class ItemStrontium extends ItemSubstance {public ItemStrontium() {super(ChemicalFormula.STRONTIUM);}}
    public static final class ItemYttrium extends ItemSubstance {public ItemYttrium() {super(ChemicalFormula.YTTRIUM);}}
    public static final class ItemZirconium extends ItemSubstance {public ItemZirconium() {super(ChemicalFormula.ZIRCONIUM);}}
    public static final class ItemNiobium extends ItemSubstance {public ItemNiobium() {super(ChemicalFormula.NIOBIUM);}}
    public static final class ItemMolybdenum extends ItemSubstance {public ItemMolybdenum() {super(ChemicalFormula.MOLYBDENUM);}}
    public static final class ItemTechnetium extends ItemSubstance {public ItemTechnetium() {super(ChemicalFormula.TECHNETIUM);}}
    public static final class ItemRuthenium extends ItemSubstance {public ItemRuthenium() {super(ChemicalFormula.RUTHENIUM);}}
    public static final class ItemRhodium extends ItemSubstance {public ItemRhodium() {super(ChemicalFormula.RHODIUM);}}
    public static final class ItemPalladium extends ItemSubstance {public ItemPalladium() {super(ChemicalFormula.PALLADIUM);}}
    public static final class ItemSilver extends ItemSubstance {public ItemSilver() {super(ChemicalFormula.SILVER);}}
    public static final class ItemCadmium extends ItemSubstance {public ItemCadmium() {super(ChemicalFormula.CADMIUM);}}
    public static final class ItemIndium extends ItemSubstance {public ItemIndium() {super(ChemicalFormula.INDIUM);}}
    public static final class ItemTin extends ItemSubstance {public ItemTin() {super(ChemicalFormula.TIN);}}
    public static final class ItemAntimony extends ItemSubstance {public ItemAntimony() {super(ChemicalFormula.ANTIMONY);}}
    public static final class ItemTellurium extends ItemSubstance {public ItemTellurium() {super(ChemicalFormula.TELLURIUM);}}
    public static final class ItemIodine extends ItemSubstance {public ItemIodine() {super(ChemicalFormula.IODINE);}}
    public static final class ItemXenon extends ItemSubstance {public ItemXenon() {super(ChemicalFormula.XENON);}}
    public static final class ItemCesium extends ItemSubstance {public ItemCesium() {super(ChemicalFormula.CESIUM);}}
    public static final class ItemBarium extends ItemSubstance {public ItemBarium() {super(ChemicalFormula.BARIUM);}}
    public static final class ItemLanthanum extends ItemSubstance {public ItemLanthanum() {super(ChemicalFormula.LANTHANUM);}}
    public static final class ItemCerium extends ItemSubstance {public ItemCerium() {super(ChemicalFormula.CERIUM);}}
    public static final class ItemPraseodymium extends ItemSubstance {public ItemPraseodymium() {super(ChemicalFormula.PRASEODYMIUM);}}
    public static final class ItemNeodymium extends ItemSubstance {public ItemNeodymium() {super(ChemicalFormula.NEODYMIUM);}}
    public static final class ItemPromethium extends ItemSubstance {public ItemPromethium() {super(ChemicalFormula.PROMETHIUM);}}
    public static final class ItemSamarium extends ItemSubstance {public ItemSamarium() {super(ChemicalFormula.SAMARIUM);}}
    public static final class ItemEuropium extends ItemSubstance {public ItemEuropium() {super(ChemicalFormula.EUROPIUM);}}
    public static final class ItemGadolinium extends ItemSubstance {public ItemGadolinium() {super(ChemicalFormula.GADOLINIUM);}}
    public static final class ItemTerbium extends ItemSubstance {public ItemTerbium() {super(ChemicalFormula.TERBIUM);}}
    public static final class ItemDysprosium extends ItemSubstance {public ItemDysprosium() {super(ChemicalFormula.DYSPROSIUM);}}
    public static final class ItemHolmium extends ItemSubstance {public ItemHolmium() {super(ChemicalFormula.HOLMIUM);}}
    public static final class ItemErbium extends ItemSubstance {public ItemErbium() {super(ChemicalFormula.ERBIUM);}}
    public static final class ItemThulium extends ItemSubstance {public ItemThulium() {super(ChemicalFormula.THULIUM);}}
    public static final class ItemYtterbium extends ItemSubstance {public ItemYtterbium() {super(ChemicalFormula.YTTERBIUM);}}
    public static final class ItemLutetium extends ItemSubstance {public ItemLutetium() {super(ChemicalFormula.LUTETIUM);}}
    public static final class ItemHafnium extends ItemSubstance {public ItemHafnium() {super(ChemicalFormula.HAFNIUM);}}
    public static final class ItemTantalum extends ItemSubstance {public ItemTantalum() {super(ChemicalFormula.TANTALUM);}}
    public static final class ItemTungsten extends ItemSubstance {public ItemTungsten() {super(ChemicalFormula.TUNGSTEN);}}
    public static final class ItemRhenium extends ItemSubstance {public ItemRhenium() {super(ChemicalFormula.RHENIUM);}}
    public static final class ItemOsmium extends ItemSubstance {public ItemOsmium() {super(ChemicalFormula.OSMIUM);}}
    public static final class ItemIridium extends ItemSubstance {public ItemIridium() {super(ChemicalFormula.IRIDIUM);}}
    public static final class ItemPlatinum extends ItemSubstance {public ItemPlatinum() {super(ChemicalFormula.PLATINUM);}}
    public static final class ItemGold extends ItemSubstance {public ItemGold() {super(ChemicalFormula.GOLD);}}
    public static final class ItemMercury extends ItemSubstance {public ItemMercury() {super(ChemicalFormula.MERCURY);}}
    public static final class ItemThallium extends ItemSubstance {public ItemThallium() {super(ChemicalFormula.THALLIUM);}}
    public static final class ItemLead extends ItemSubstance {public ItemLead() {super(ChemicalFormula.LEAD);}}
    public static final class ItemBismuth extends ItemSubstance {public ItemBismuth() {super(ChemicalFormula.BISMUTH);}}
    public static final class ItemPolonium extends ItemSubstance {public ItemPolonium() {super(ChemicalFormula.POLONIUM);}}
    public static final class ItemAstatine extends ItemSubstance {public ItemAstatine() {super(ChemicalFormula.ASTATINE);}}
    public static final class ItemRadon extends ItemSubstance {public ItemRadon() {super(ChemicalFormula.RADON);}}
    public static final class ItemFrancium extends ItemSubstance {public ItemFrancium() {super(ChemicalFormula.FRANCIUM);}}
    public static final class ItemRadium extends ItemSubstance {public ItemRadium() {super(ChemicalFormula.RADIUM);}}
    public static final class ItemActinium extends ItemSubstance {public ItemActinium() {super(ChemicalFormula.ACTINIUM);}}
    public static final class ItemThorium extends ItemSubstance {public ItemThorium() {super(ChemicalFormula.THORIUM);}}
    public static final class ItemProtactinium extends ItemSubstance {public ItemProtactinium() {super(ChemicalFormula.PROTACTINIUM);}}
    public static final class ItemUranium extends ItemSubstance {public ItemUranium() {super(ChemicalFormula.URANIUM);}}
    public static final class ItemNeptunium extends ItemSubstance {public ItemNeptunium() {super(ChemicalFormula.NEPTUNIUM);}}
    public static final class ItemPlutonium extends ItemSubstance {public ItemPlutonium() {super(ChemicalFormula.PLUTONIUM);}}
    public static final class ItemAmericium extends ItemSubstance {public ItemAmericium() {super(ChemicalFormula.AMERICIUM);}}
    public static final class ItemCurium extends ItemSubstance {public ItemCurium() {super(ChemicalFormula.CURIUM);}}
    public static final class ItemBerkelium extends ItemSubstance {public ItemBerkelium() {super(ChemicalFormula.BERKELIUM);}}
    public static final class ItemCalifornium extends ItemSubstance {public ItemCalifornium() {super(ChemicalFormula.CALIFORNIUM);}}
    public static final class ItemEinsteinium extends ItemSubstance {public ItemEinsteinium() {super(ChemicalFormula.EINSTEINIUM);}}
    public static final class ItemFermium extends ItemSubstance {public ItemFermium() {super(ChemicalFormula.FERMIUM);}}
    public static final class ItemMendelevium extends ItemSubstance {public ItemMendelevium() {super(ChemicalFormula.MENDELEVIUM);}}
    public static final class ItemNobelium extends ItemSubstance {public ItemNobelium() {super(ChemicalFormula.NOBELIUM);}}
    public static final class ItemLawrencium extends ItemSubstance {public ItemLawrencium() {super(ChemicalFormula.LAWRENCIUM);}}
    public static final class ItemRutherfordium extends ItemSubstance {public ItemRutherfordium() {super(ChemicalFormula.RUTHERFORDIUM);}}
    public static final class ItemDubnium extends ItemSubstance {public ItemDubnium() {super(ChemicalFormula.DUBNIUM);}}
    public static final class ItemSeaborgium extends ItemSubstance {public ItemSeaborgium() {super(ChemicalFormula.SEABORGIUM);}}
    public static final class ItemBohrium extends ItemSubstance {public ItemBohrium() {super(ChemicalFormula.BOHRIUM);}}
    public static final class ItemHassium extends ItemSubstance {public ItemHassium() {super(ChemicalFormula.HASSIUM);}}
    public static final class ItemMeitnerium extends ItemSubstance {public ItemMeitnerium() {super(ChemicalFormula.MEITNERIUM);}}
    public static final class ItemDarmstadtium extends ItemSubstance {public ItemDarmstadtium() {super(ChemicalFormula.DARMSTADTIUM);}}
    public static final class ItemRoentgenium extends ItemSubstance {public ItemRoentgenium() {super(ChemicalFormula.ROENTGENIUM);}}
    public static final class ItemCopernicium extends ItemSubstance {public ItemCopernicium() {super(ChemicalFormula.COPERNICIUM);}}
    public static final class ItemNihonium extends ItemSubstance {public ItemNihonium() {super(ChemicalFormula.NIHONIUM);}}
    public static final class ItemFlerovium extends ItemSubstance {public ItemFlerovium() {super(ChemicalFormula.FLEROVIUM);}}
    public static final class ItemMoscovium extends ItemSubstance {public ItemMoscovium() {super(ChemicalFormula.MOSCOVIUM);}}
    public static final class ItemLivermorium extends ItemSubstance {public ItemLivermorium() {super(ChemicalFormula.LIVERMORIUM);}}
    public static final class ItemTennessine extends ItemSubstance {public ItemTennessine() {super(ChemicalFormula.TENNESSINE);}}
    public static final class ItemOganesson extends ItemSubstance {public ItemOganesson() {super(ChemicalFormula.OGANESSON);}}

    // compound
    public static final class ItemWater extends ItemSubstance {public ItemWater() {super(ChemicalFormula.WATER);}}

    private String name;
    private String chemicalFormula;
    private List<ItemStack> composition;

    public ItemSubstance(String chemicalFormula)
    {
        this.name = ChemicalFormula.getNameByChemicalFormula(chemicalFormula);
        this.chemicalFormula = chemicalFormula;

        SubstanceHelper substance = new SubstanceHelper(chemicalFormula);
        this.composition = substance.getComposition();

        this.setRegistryName(name);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", name));
        this.setCreativeTab(ModCreativeTabs.SUBSTANCES);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("null")
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String name = MainUtil.combinedStrings(TextFormatting.GOLD.toString(), this.getItemStackDisplayName(stack));

        if (!tooltip.isEmpty()) tooltip.remove(tooltip.size() - 1);
        if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips)
            name += MainUtil.combinedStrings(TextFormatting.WHITE.toString(), " (#", Integer.toString(Item.getIdFromItem(this)), ")");

        tooltip.add(name);
        tooltip.add(MainUtil.combinedStrings(TextFormatting.BLUE.toString(), I18n.format(Constant.CHEMICAL_FORMULA_TEXT), ": ", this.chemicalFormula));
        tooltip.add(MainUtil.combinedStrings(TextFormatting.GREEN.toString(), I18n.format(Constant.MOLECULAR_MASS_TEXT), ": ", Double.toString(this.getMolecularMass())));
    }

    public double getMolecularMass()
    {
        double totalMass = 0.0;

        for (ItemStack itemStack : this.composition)
        {
            ItemElement temp = ((ItemElement)(itemStack.getItem()));
            totalMass += temp.atomicMass * itemStack.getCount();
        }

        return totalMass;
    }

    public double getTotalEnergy()
    {
        return this.getMolecularMass() * PhysicsConstant.c_square / 1000.0;
    }

    public ItemStack[] getComposition()
    {
        return this.composition.toArray(new ItemStack[this.composition.size()]);
    }

    public int timeForDecompose()
    {
        int number = 0;
        
        for (ItemStack itemStack : this.composition)
            number += itemStack.getCount();
        
        return number * TIME_PER_MOLECULAR;
    }
}