package com.wangdi.molecularcraft.item;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;
import com.wangdi.util.chemistry.ChemicalSymbol;
import com.wangdi.util.chemistry.PeriodicTable;
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

public class ItemElement extends Item
{
    public static final int TIME_PER_PARTICLE = 3;
    
    public static final class ItemHydrogen extends ItemElement {public ItemHydrogen() {super(ChemicalSymbol.HYDROGEN);}}
    public static final class ItemHelium extends ItemElement {public ItemHelium() {super(ChemicalSymbol.HELIUM);}}
    public static final class ItemLithium extends ItemElement {public ItemLithium() {super(ChemicalSymbol.LITHIUM);}}
    public static final class ItemBeryllium extends ItemElement {public ItemBeryllium() {super(ChemicalSymbol.BERYLLIUM);}}
    public static final class ItemBoron extends ItemElement {public ItemBoron() {super(ChemicalSymbol.BORON);}}
    public static final class ItemCarbon extends ItemElement {public ItemCarbon() {super(ChemicalSymbol.CARBON);}}
    public static final class ItemNitrogen extends ItemElement {public ItemNitrogen() {super(ChemicalSymbol.NITROGEN);}}
    public static final class ItemOxygen extends ItemElement {public ItemOxygen() {super(ChemicalSymbol.OXYGEN);}}
    public static final class ItemFluorine extends ItemElement {public ItemFluorine() {super(ChemicalSymbol.FLUORINE);}}
    public static final class ItemNeon extends ItemElement {public ItemNeon() {super(ChemicalSymbol.NEON);}}
    public static final class ItemSodium extends ItemElement {public ItemSodium() {super(ChemicalSymbol.SODIUM);}}
    public static final class ItemMagnesium extends ItemElement {public ItemMagnesium() {super(ChemicalSymbol.MAGNESIUM);}}
    public static final class ItemAluminium extends ItemElement {public ItemAluminium() {super(ChemicalSymbol.ALUMINIUM);}}
    public static final class ItemSilicon extends ItemElement {public ItemSilicon() {super(ChemicalSymbol.SILICON);}}
    public static final class ItemPhosphorus extends ItemElement {public ItemPhosphorus() {super(ChemicalSymbol.PHOSPHORUS);}}
    public static final class ItemSulfur extends ItemElement {public ItemSulfur() {super(ChemicalSymbol.SULFUR);}}
    public static final class ItemChlorine extends ItemElement {public ItemChlorine() {super(ChemicalSymbol.CHLORINE);}}
    public static final class ItemArgon extends ItemElement {public ItemArgon() {super(ChemicalSymbol.ARGON);}}
    public static final class ItemPotassium extends ItemElement {public ItemPotassium() {super(ChemicalSymbol.POTASSIUM);}}
    public static final class ItemCalcium extends ItemElement {public ItemCalcium() {super(ChemicalSymbol.CALCIUM);}}
    public static final class ItemScandium extends ItemElement {public ItemScandium() {super(ChemicalSymbol.SCANDIUM);}}
    public static final class ItemTitanium extends ItemElement {public ItemTitanium() {super(ChemicalSymbol.TITANIUM);}}
    public static final class ItemVanadium extends ItemElement {public ItemVanadium() {super(ChemicalSymbol.VANADIUM);}}
    public static final class ItemChromium extends ItemElement {public ItemChromium() {super(ChemicalSymbol.CHROMIUM);}}
    public static final class ItemManganese extends ItemElement {public ItemManganese() {super(ChemicalSymbol.MANGANESE);}}
    public static final class ItemIron extends ItemElement {public ItemIron() {super(ChemicalSymbol.IRON);}}
    public static final class ItemCobalt extends ItemElement {public ItemCobalt() {super(ChemicalSymbol.COBALT);}}
    public static final class ItemNickel extends ItemElement {public ItemNickel() {super(ChemicalSymbol.NICKEL);}}
    public static final class ItemCopper extends ItemElement {public ItemCopper() {super(ChemicalSymbol.COPPER);}}
    public static final class ItemZinc extends ItemElement {public ItemZinc() {super(ChemicalSymbol.ZINC);}}
    public static final class ItemGallium extends ItemElement {public ItemGallium() {super(ChemicalSymbol.GALLIUM);}}
    public static final class ItemGermanium extends ItemElement {public ItemGermanium() {super(ChemicalSymbol.GERMANIUM);}}
    public static final class ItemArsenic extends ItemElement {public ItemArsenic() {super(ChemicalSymbol.ARSENIC);}}
    public static final class ItemSelenium extends ItemElement {public ItemSelenium() {super(ChemicalSymbol.SELENIUM);}}
    public static final class ItemBromine extends ItemElement {public ItemBromine() {super(ChemicalSymbol.BROMINE);}}
    public static final class ItemKrypton extends ItemElement {public ItemKrypton() {super(ChemicalSymbol.KRYPTON);}}
    public static final class ItemRubidium extends ItemElement {public ItemRubidium() {super(ChemicalSymbol.RUBIDIUM);}}
    public static final class ItemStrontium extends ItemElement {public ItemStrontium() {super(ChemicalSymbol.STRONTIUM);}}
    public static final class ItemYttrium extends ItemElement {public ItemYttrium() {super(ChemicalSymbol.YTTRIUM);}}
    public static final class ItemZirconium extends ItemElement {public ItemZirconium() {super(ChemicalSymbol.ZIRCONIUM);}}
    public static final class ItemNiobium extends ItemElement {public ItemNiobium() {super(ChemicalSymbol.NIOBIUM);}}
    public static final class ItemMolybdenum extends ItemElement {public ItemMolybdenum() {super(ChemicalSymbol.MOLYBDENUM);}}
    public static final class ItemTechnetium extends ItemElement {public ItemTechnetium() {super(ChemicalSymbol.TECHNETIUM);}}
    public static final class ItemRuthenium extends ItemElement {public ItemRuthenium() {super(ChemicalSymbol.RUTHENIUM);}}
    public static final class ItemRhodium extends ItemElement {public ItemRhodium() {super(ChemicalSymbol.RHODIUM);}}
    public static final class ItemPalladium extends ItemElement {public ItemPalladium() {super(ChemicalSymbol.PALLADIUM);}}
    public static final class ItemSilver extends ItemElement {public ItemSilver() {super(ChemicalSymbol.SILVER);}}
    public static final class ItemCadmium extends ItemElement {public ItemCadmium() {super(ChemicalSymbol.CADMIUM);}}
    public static final class ItemIndium extends ItemElement {public ItemIndium() {super(ChemicalSymbol.INDIUM);}}
    public static final class ItemTin extends ItemElement {public ItemTin() {super(ChemicalSymbol.TIN);}}
    public static final class ItemAntimony extends ItemElement {public ItemAntimony() {super(ChemicalSymbol.ANTIMONY);}}
    public static final class ItemTellurium extends ItemElement {public ItemTellurium() {super(ChemicalSymbol.TELLURIUM);}}
    public static final class ItemIodine extends ItemElement {public ItemIodine() {super(ChemicalSymbol.IODINE);}}
    public static final class ItemXenon extends ItemElement {public ItemXenon() {super(ChemicalSymbol.XENON);}}
    public static final class ItemCesium extends ItemElement {public ItemCesium() {super(ChemicalSymbol.CESIUM);}}
    public static final class ItemBarium extends ItemElement {public ItemBarium() {super(ChemicalSymbol.BARIUM);}}
    public static final class ItemLanthanum extends ItemElement {public ItemLanthanum() {super(ChemicalSymbol.LANTHANUM);}}
    public static final class ItemCerium extends ItemElement {public ItemCerium() {super(ChemicalSymbol.CERIUM);}}
    public static final class ItemPraseodymium extends ItemElement {public ItemPraseodymium() {super(ChemicalSymbol.PRASEODYMIUM);}}
    public static final class ItemNeodymium extends ItemElement {public ItemNeodymium() {super(ChemicalSymbol.NEODYMIUM);}}
    public static final class ItemPromethium extends ItemElement {public ItemPromethium() {super(ChemicalSymbol.PROMETHIUM);}}
    public static final class ItemSamarium extends ItemElement {public ItemSamarium() {super(ChemicalSymbol.SAMARIUM);}}
    public static final class ItemEuropium extends ItemElement {public ItemEuropium() {super(ChemicalSymbol.EUROPIUM);}}
    public static final class ItemGadolinium extends ItemElement {public ItemGadolinium() {super(ChemicalSymbol.GADOLINIUM);}}
    public static final class ItemTerbium extends ItemElement {public ItemTerbium() {super(ChemicalSymbol.TERBIUM);}}
    public static final class ItemDysprosium extends ItemElement {public ItemDysprosium() {super(ChemicalSymbol.DYSPROSIUM);}}
    public static final class ItemHolmium extends ItemElement {public ItemHolmium() {super(ChemicalSymbol.HOLMIUM);}}
    public static final class ItemErbium extends ItemElement {public ItemErbium() {super(ChemicalSymbol.ERBIUM);}}
    public static final class ItemThulium extends ItemElement {public ItemThulium() {super(ChemicalSymbol.THULIUM);}}
    public static final class ItemYtterbium extends ItemElement {public ItemYtterbium() {super(ChemicalSymbol.YTTERBIUM);}}
    public static final class ItemLutetium extends ItemElement {public ItemLutetium() {super(ChemicalSymbol.LUTETIUM);}}
    public static final class ItemHafnium extends ItemElement {public ItemHafnium() {super(ChemicalSymbol.HAFNIUM);}}
    public static final class ItemTantalum extends ItemElement {public ItemTantalum() {super(ChemicalSymbol.TANTALUM);}}
    public static final class ItemTungsten extends ItemElement {public ItemTungsten() {super(ChemicalSymbol.TUNGSTEN);}}
    public static final class ItemRhenium extends ItemElement {public ItemRhenium() {super(ChemicalSymbol.RHENIUM);}}
    public static final class ItemOsmium extends ItemElement {public ItemOsmium() {super(ChemicalSymbol.OSMIUM);}}
    public static final class ItemIridium extends ItemElement {public ItemIridium() {super(ChemicalSymbol.IRIDIUM);}}
    public static final class ItemPlatinum extends ItemElement {public ItemPlatinum() {super(ChemicalSymbol.PLATINUM);}}
    public static final class ItemGold extends ItemElement {public ItemGold() {super(ChemicalSymbol.GOLD);}}
    public static final class ItemMercury extends ItemElement {public ItemMercury() {super(ChemicalSymbol.MERCURY);}}
    public static final class ItemThallium extends ItemElement {public ItemThallium() {super(ChemicalSymbol.THALLIUM);}}
    public static final class ItemLead extends ItemElement {public ItemLead() {super(ChemicalSymbol.LEAD);}}
    public static final class ItemBismuth extends ItemElement {public ItemBismuth() {super(ChemicalSymbol.BISMUTH);}}
    public static final class ItemPolonium extends ItemElement {public ItemPolonium() {super(ChemicalSymbol.POLONIUM);}}
    public static final class ItemAstatine extends ItemElement {public ItemAstatine() {super(ChemicalSymbol.ASTATINE);}}
    public static final class ItemRadon extends ItemElement {public ItemRadon() {super(ChemicalSymbol.RADON);}}
    public static final class ItemFrancium extends ItemElement {public ItemFrancium() {super(ChemicalSymbol.FRANCIUM);}}
    public static final class ItemRadium extends ItemElement {public ItemRadium() {super(ChemicalSymbol.RADIUM);}}
    public static final class ItemActinium extends ItemElement {public ItemActinium() {super(ChemicalSymbol.ACTINIUM);}}
    public static final class ItemThorium extends ItemElement {public ItemThorium() {super(ChemicalSymbol.THORIUM);}}
    public static final class ItemProtactinium extends ItemElement {public ItemProtactinium() {super(ChemicalSymbol.PROTACTINIUM);}}
    public static final class ItemUranium extends ItemElement {public ItemUranium() {super(ChemicalSymbol.URANIUM);}}
    public static final class ItemNeptunium extends ItemElement {public ItemNeptunium() {super(ChemicalSymbol.NEPTUNIUM);}}
    public static final class ItemPlutonium extends ItemElement {public ItemPlutonium() {super(ChemicalSymbol.PLUTONIUM);}}
    public static final class ItemAmericium extends ItemElement {public ItemAmericium() {super(ChemicalSymbol.AMERICIUM);}}
    public static final class ItemCurium extends ItemElement {public ItemCurium() {super(ChemicalSymbol.CURIUM);}}
    public static final class ItemBerkelium extends ItemElement {public ItemBerkelium() {super(ChemicalSymbol.BERKELIUM);}}
    public static final class ItemCalifornium extends ItemElement {public ItemCalifornium() {super(ChemicalSymbol.CALIFORNIUM);}}
    public static final class ItemEinsteinium extends ItemElement {public ItemEinsteinium() {super(ChemicalSymbol.EINSTEINIUM);}}
    public static final class ItemFermium extends ItemElement {public ItemFermium() {super(ChemicalSymbol.FERMIUM);}}
    public static final class ItemMendelevium extends ItemElement {public ItemMendelevium() {super(ChemicalSymbol.MENDELEVIUM);}}
    public static final class ItemNobelium extends ItemElement {public ItemNobelium() {super(ChemicalSymbol.NOBELIUM);}}
    public static final class ItemLawrencium extends ItemElement {public ItemLawrencium() {super(ChemicalSymbol.LAWRENCIUM);}}
    public static final class ItemRutherfordium extends ItemElement {public ItemRutherfordium() {super(ChemicalSymbol.RUTHERFORDIUM);}}
    public static final class ItemDubnium extends ItemElement {public ItemDubnium() {super(ChemicalSymbol.DUBNIUM);}}
    public static final class ItemSeaborgium extends ItemElement {public ItemSeaborgium() {super(ChemicalSymbol.SEABORGIUM);}}
    public static final class ItemBohrium extends ItemElement {public ItemBohrium() {super(ChemicalSymbol.BOHRIUM);}}
    public static final class ItemHassium extends ItemElement {public ItemHassium() {super(ChemicalSymbol.HASSIUM);}}
    public static final class ItemMeitnerium extends ItemElement {public ItemMeitnerium() {super(ChemicalSymbol.MEITNERIUM);}}
    public static final class ItemDarmstadtium extends ItemElement {public ItemDarmstadtium() {super(ChemicalSymbol.DARMSTADTIUM);}}
    public static final class ItemRoentgenium extends ItemElement {public ItemRoentgenium() {super(ChemicalSymbol.ROENTGENIUM);}}
    public static final class ItemCopernicium extends ItemElement {public ItemCopernicium() {super(ChemicalSymbol.COPERNICIUM);}}
    public static final class ItemNihonium extends ItemElement {public ItemNihonium() {super(ChemicalSymbol.NIHONIUM);}}
    public static final class ItemFlerovium extends ItemElement {public ItemFlerovium() {super(ChemicalSymbol.FLEROVIUM);}}
    public static final class ItemMoscovium extends ItemElement {public ItemMoscovium() {super(ChemicalSymbol.MOSCOVIUM);}}
    public static final class ItemLivermorium extends ItemElement {public ItemLivermorium() {super(ChemicalSymbol.LIVERMORIUM);}}
    public static final class ItemTennessine extends ItemElement {public ItemTennessine() {super(ChemicalSymbol.TENNESSINE);}}
    public static final class ItemOganesson extends ItemElement {public ItemOganesson() {super(ChemicalSymbol.OGANESSON);}}

    public int protonNumber;
    public int neutronNumber;
    public double atomicMass;
    public String chemicalSymbols;
    public String elementName;

    public static boolean isElement(Item item)
    {
        return item != null && item instanceof ItemElement;
    }

    public ItemElement(String chemicalSymbols)
    {
        this.protonNumber = PeriodicTable.getProtonNumber(chemicalSymbols);
        this.neutronNumber = PeriodicTable.getNeutronNumber(chemicalSymbols);
        this.atomicMass = PeriodicTable.getAtomicMass(chemicalSymbols);
        this.chemicalSymbols = chemicalSymbols;
        this.elementName = PeriodicTable.getElementName(chemicalSymbols);

        this.setRegistryName(this.elementName);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", this.elementName));
        this.setCreativeTab(ModCreativeTabs.ELEMENTS);
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
        tooltip.add(MainUtil.combinedStrings(TextFormatting.BLUE.toString(), I18n.format(Constant.PROTON_NUMBER_TEXT), ": ", Integer.toString(this.protonNumber)));
        tooltip.add(MainUtil.combinedStrings(TextFormatting.YELLOW.toString(), I18n.format(Constant.NEUTRON_NUMBER_TEXT), ": ", Integer.toString(this.neutronNumber)));
        tooltip.add(MainUtil.combinedStrings(TextFormatting.GREEN.toString(), I18n.format(Constant.ATOMIC_MASS_TEXT), ": ", Double.toString(this.atomicMass)));
    }

    // get mass of 1 mol item unit u
    public double getNucleusMass()
    {
        return PhysicsConstant.NA * this.atomicMass;
    }

    // get mass of particles unit u
    public double getParticleMass()
    {
        return PhysicsConstant.NA * (this.protonNumber * PhysicsConstant.PROTON_MASS + this.neutronNumber * PhysicsConstant.NEUTRON_MASS);
    }

    // get mass defection unit u
    public double getMassDefection()
    {
        return this.getParticleMass() - this.getNucleusMass();
    }

    // 1 mol of the item
    public double getTotalEnergy()
    {
        return this.getNucleusMass() * PhysicsConstant.u * PhysicsConstant.c_square;
    }

    public double getParticleEnergy()
    {
        return this.getParticleMass() * PhysicsConstant.u * PhysicsConstant.c_square;
    }

    // the energy change during split atom's particles (1 mol)
    public double getChangeInEnergy()
    {
        return this.getParticleEnergy() - this.getTotalEnergy();
    }

    // the time for decompose (1 mol of item)
    public int timeForDecompose()
    {
        return (this.protonNumber + this.neutronNumber) * TIME_PER_PARTICLE;
    }

    public ItemStack[] getDecompositionResult()
    {
        List<ItemStack> result = new ArrayList<ItemStack>();

        for (int i = 0; i < this.protonNumber / 64; i++)
            result.add(new ItemStack(ModItems.PROTON, 64));

        result.add(new ItemStack(ModItems.PROTON, this.protonNumber % 64));

        for (int i = 0; i < this.neutronNumber / 64; i++)
            result.add(new ItemStack(ModItems.NEUTRON, 64));
        
        result.add(new ItemStack(ModItems.NEUTRON, this.neutronNumber % 64));

        return result.toArray(new ItemStack[result.size()]);
    }
}