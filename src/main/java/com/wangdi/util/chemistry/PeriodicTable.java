package com.wangdi.util.chemistry;

import java.util.Map;

import java.util.HashMap;

public final class PeriodicTable
{
    private Map<String, String> elementNameTable;
    private Map<String, Integer> protonNumberTable;
    private Map<String, Integer> neutronNumberTable;
    private Map<String, Double> atomicMassTable;

    public PeriodicTable()
    {
        this.elementNameTable = new HashMap<>();
        this.protonNumberTable = new HashMap<>();
        this.neutronNumberTable = new HashMap<>();
        this.atomicMassTable = new HashMap<>();

        this.addElement(ChemicalSymbol.HYDROGEN, "hydrogen_atom", 1, 0, 1.008);
        this.addElement(ChemicalSymbol.HELIUM, "helium_atom", 2, 2, 4.0026);
        this.addElement(ChemicalSymbol.LITHIUM, "lithium_atom", 3, 4, 6.94);
        this.addElement(ChemicalSymbol.BERYLLIUM, "beryllium_atom", 4, 5, 9.0122);
        this.addElement(ChemicalSymbol.BORON, "boron_atom", 5, 6, 10.81);
        this.addElement(ChemicalSymbol.CARBON, "carbon_atom", 6, 6, 12.011);
        this.addElement(ChemicalSymbol.NITROGEN, "nitrogen_atom", 7, 7, 14.007);
        this.addElement(ChemicalSymbol.OXYGEN, "oxygen_atom", 8, 8, 15.999);
        this.addElement(ChemicalSymbol.FLUORINE, "fluorine_atom", 9, 10, 18.998);
        this.addElement(ChemicalSymbol.NEON, "neon_atom", 10, 10, 20.180);
        this.addElement(ChemicalSymbol.SODIUM, "sodium_atom", 11, 12, 22.990);
        this.addElement(ChemicalSymbol.MAGNESIUM, "magnesium_atom", 12, 12, 24.305);
        this.addElement(ChemicalSymbol.ALUMINIUM, "aluminium_atom", 13, 14, 26.982);
        this.addElement(ChemicalSymbol.SILICON, "silicon_atom", 14, 14, 28.085);
        this.addElement(ChemicalSymbol.PHOSPHORUS, "phosphorus_atom", 15, 16, 30.974);
        this.addElement(ChemicalSymbol.SULFUR, "sulfur_atom", 16, 16, 32.06);
        this.addElement(ChemicalSymbol.CHLORINE, "chlorine_atom", 17, 18, 35.45);
        this.addElement(ChemicalSymbol.ARGON, "argon_atom", 18, 22, 39.948);
        this.addElement(ChemicalSymbol.POTASSIUM, "potassium_atom", 19, 20, 39.098);
        this.addElement(ChemicalSymbol.CALCIUM, "calcium_atom", 20, 20, 40.080);
        this.addElement(ChemicalSymbol.SCANDIUM, "scandium_atom", 21, 24, 44.956);
        this.addElement(ChemicalSymbol.TITANIUM, "titanium_atom", 22, 26, 47.867);
        this.addElement(ChemicalSymbol.VANADIUM, "vanadium_atom", 23, 28, 50.942);
        this.addElement(ChemicalSymbol.CHROMIUM, "chromium_atom", 24, 28, 51.996);
        this.addElement(ChemicalSymbol.MANGANESE, "manganese_atom", 25, 30, 54.938);
        this.addElement(ChemicalSymbol.IRON, "iron_atom", 26, 30, 55.845);
        this.addElement(ChemicalSymbol.COBALT, "cobalt_atom", 27, 32, 58.933);
        this.addElement(ChemicalSymbol.NICKEL, "nickel_atom", 28, 31, 58.693);
        this.addElement(ChemicalSymbol.COPPER, "copper_atom", 29, 35, 63.546);
        this.addElement(ChemicalSymbol.ZINC, "zinc_atom", 30, 35, 65.380);
        this.addElement(ChemicalSymbol.GALLIUM, "gallium_atom", 31, 39, 69.723);
        this.addElement(ChemicalSymbol.GERMANIUM, "germanium_atom", 32, 41, 72.63);
        this.addElement(ChemicalSymbol.ARSENIC, "arsenic_atom", 33, 42, 74.922);
        this.addElement(ChemicalSymbol.SELENIUM, "selenium_atom", 34, 45, 78.971);
        this.addElement(ChemicalSymbol.BROMINE, "bromine_atom", 35, 45, 79.904);
        this.addElement(ChemicalSymbol.KRYPTON, "krypton_atom", 36, 48, 83.798);
        this.addElement(ChemicalSymbol.RUBIDIUM, "rubidium_atom", 37, 48, 85.468);
        this.addElement(ChemicalSymbol.STRONTIUM, "strontium_atom", 38, 50, 87.62);
        this.addElement(ChemicalSymbol.YTTRIUM, "yttrium_atom", 39, 50, 88.906);
        this.addElement(ChemicalSymbol.ZIRCONIUM, "zirconium_atom", 40, 51, 91.224);
        this.addElement(ChemicalSymbol.NIOBIUM, "niobium_atom", 41, 52, 92.906);
        this.addElement(ChemicalSymbol.MOLYBDENUM, "molybdenum_atom", 42, 54, 95.95);
        this.addElement(ChemicalSymbol.TECHNETIUM, "technetium_atom", 43, 55, 98);
        this.addElement(ChemicalSymbol.RUTHENIUM, "ruthenium_atom", 44, 57, 101.07);
        this.addElement(ChemicalSymbol.RHODIUM, "rhodium_atom", 45, 58, 102.91);
        this.addElement(ChemicalSymbol.PALLADIUM, "palladium_atom", 46, 60, 106.42);
        this.addElement(ChemicalSymbol.SILVER, "silver_atom", 47, 61, 107.87);
        this.addElement(ChemicalSymbol.CADMIUM, "cadmium_atom", 48, 64, 112.41);
        this.addElement(ChemicalSymbol.INDIUM, "indium_atom", 49, 66, 114.82);
        this.addElement(ChemicalSymbol.TIN, "tin_atom", 50, 69, 118.71);
        this.addElement(ChemicalSymbol.ANTIMONY, "antimony_atom", 51, 71, 121.76);
        this.addElement(ChemicalSymbol.TELLURIUM, "tellurium_atom", 52, 76, 127.6);
        this.addElement(ChemicalSymbol.IODINE, "iodine_atom", 53, 78, 126.904);
        this.addElement(ChemicalSymbol.XENON, "xenon_atom", 54, 77, 131.293);
        this.addElement(ChemicalSymbol.CESIUM, "cesium_atom", 55, 78, 132.905);
        this.addElement(ChemicalSymbol.BARIUM, "barium_atom", 56, 81, 137.327);
        this.addElement(ChemicalSymbol.LANTHANUM, "lanthanum_atom", 57, 82, 138.905);
        this.addElement(ChemicalSymbol.CERIUM, "cerium_atom", 58, 82, 140.116);
        this.addElement(ChemicalSymbol.PRASEODYMIUM, "praseodymium_atom", 59, 82, 140.907);
        this.addElement(ChemicalSymbol.NEODYMIUM, "neodymium_atom", 60, 84, 144.242);
        this.addElement(ChemicalSymbol.PROMETHIUM, "promethium_atom", 61, 84, 145);
        this.addElement(ChemicalSymbol.SAMARIUM, "samarium_atom", 62, 88, 150.36);
        this.addElement(ChemicalSymbol.EUROPIUM, "europium_atom", 63, 89, 151.964);
        this.addElement(ChemicalSymbol.GADOLINIUM, "gadolinium_atom", 64, 93, 157.25);
        this.addElement(ChemicalSymbol.TERBIUM, "terbium_atom", 65, 94, 158.925);
        this.addElement(ChemicalSymbol.DYSPROSIUM, "dysprosium_atom", 66, 97, 162.5);
        this.addElement(ChemicalSymbol.HOLMIUM, "holmium_atom", 67, 98, 164.930);
        this.addElement(ChemicalSymbol.ERBIUM, "erbium_atom", 68, 99, 167.259);
        this.addElement(ChemicalSymbol.THULIUM, "thulium_atom", 69, 100, 168.934);
        this.addElement(ChemicalSymbol.YTTERBIUM, "ytterbium_atom", 70, 103, 173.045);
        this.addElement(ChemicalSymbol.LUTETIUM, "lutetium_atom", 71, 175, 174.9668);
        this.addElement(ChemicalSymbol.HAFNIUM, "hafnium_atom", 72, 159, 178.49);
        this.addElement(ChemicalSymbol.TANTALUM, "tantalum_atom", 73, 180, 180.94788);
        this.addElement(ChemicalSymbol.TUNGSTEN, "tungsten_atom", 74, 184, 183.84);
        this.addElement(ChemicalSymbol.RHENIUM, "rhenium_atom", 75, 125, 186.207);
        this.addElement(ChemicalSymbol.OSMIUM, "osmium_atom", 76, 114, 190.23);
        this.addElement(ChemicalSymbol.IRIDIUM, "iridium_atom", 77, 115, 192.217);
        this.addElement(ChemicalSymbol.PLATINUM, "platinum_atom", 78, 117, 195.084);
        this.addElement(ChemicalSymbol.GOLD, "gold_atom", 79, 118, 196.966569);
        this.addElement(ChemicalSymbol.MERCURY, "mercury_atom", 80, 120, 200.592);
        this.addElement(ChemicalSymbol.THALLIUM, "thallium_atom", 81, 123, 204.38);
        this.addElement(ChemicalSymbol.LEAD, "lead_atom", 82, 125, 207.2);
        this.addElement(ChemicalSymbol.BISMUTH, "bismuth_atom", 83, 126, 208.98040);
        this.addElement(ChemicalSymbol.POLONIUM, "polonium_atom", 84, 125, 209);
        this.addElement(ChemicalSymbol.ASTATINE, "astatine_atom", 85, 125, 210);
        this.addElement(ChemicalSymbol.RADON, "radon_atom", 86, 136, 222);
        this.addElement(ChemicalSymbol.FRANCIUM, "francium_atom", 87, 136, 223);
        this.addElement(ChemicalSymbol.RADIUM, "radium_atom", 88, 138, 226);
        this.addElement(ChemicalSymbol.ACTINIUM, "actinium_atom", 89, 138, 227);
        this.addElement(ChemicalSymbol.THORIUM, "thorium_atom", 90, 142, 232.0377);
        this.addElement(ChemicalSymbol.PROTACTINIUM, "protactinium_atom", 91, 140, 231.03588);
        this.addElement(ChemicalSymbol.URANIUM, "uranium_atom", 92, 146, 238.02891);
        this.addElement(ChemicalSymbol.NEPTUNIUM, "neptunium_atom", 93, 147, 237);
        this.addElement(ChemicalSymbol.PLUTONIUM, "plutonium_atom", 94, 146, 244);
        this.addElement(ChemicalSymbol.AMERICIUM, "americium_atom", 95, 148, 243);
        this.addElement(ChemicalSymbol.CURIUM, "curium_atom", 96, 151, 247);
        this.addElement(ChemicalSymbol.BERKELIUM, "berkelium_atom", 97, 150, 247);
        this.addElement(ChemicalSymbol.CALIFORNIUM, "californium_atom", 98, 153, 251);
        this.addElement(ChemicalSymbol.EINSTEINIUM, "einsteinium_atom", 99, 153, 252);
        this.addElement(ChemicalSymbol.FERMIUM, "fermium_atom", 100, 157, 257);
        this.addElement(ChemicalSymbol.MENDELEVIUM, "mendelevium_atom", 101, 157, 258);
        this.addElement(ChemicalSymbol.NOBELIUM, "nobelium_atom", 102, 157, 259);
        this.addElement(ChemicalSymbol.LAWRENCIUM, "lawrencium_atom", 103, 159, 266);
        this.addElement(ChemicalSymbol.RUTHERFORDIUM, "rutherfordium_atom", 104, 160, 267);
        this.addElement(ChemicalSymbol.DUBNIUM, "dubnium_atom", 105, 161, 268);
        this.addElement(ChemicalSymbol.SEABORGIUM, "seaborgium_atom", 106, 163, 269);
        this.addElement(ChemicalSymbol.BOHRIUM, "bohrium_atom", 107, 165, 270);
        this.addElement(ChemicalSymbol.HASSIUM, "hassium_atom", 108, 169, 269);
        this.addElement(ChemicalSymbol.MEITNERIUM, "meitnerium_atom", 109, 169, 278);
        this.addElement(ChemicalSymbol.DARMSTADTIUM, "darmstadtium_atom", 110, 171, 281);
        this.addElement(ChemicalSymbol.ROENTGENIUM, "roentgenium_atom", 111, 173, 282);
        this.addElement(ChemicalSymbol.COPERNICIUM, "copernicium_atom", 112, 173, 285);
        this.addElement(ChemicalSymbol.NIHONIUM, "nihonium_atom", 113, 174, 286);
        this.addElement(ChemicalSymbol.FLEROVIUM, "flerovium_atom", 114, 175, 289);
        this.addElement(ChemicalSymbol.MOSCOVIUM, "moscovium_atom", 115, 176, 289);
        this.addElement(ChemicalSymbol.LIVERMORIUM, "livermorium_atom", 116, 177, 293);
        this.addElement(ChemicalSymbol.TENNESSINE, "tennessine_atom", 117, 177, 294);
        this.addElement(ChemicalSymbol.OGANESSON, "oganesson_atom", 118, 176, 294);
    }

    private void addElement(String chemicalSymbol, String elementName, int protonNumber, int neutronNumber, double atomicMass)
    {
        this.elementNameTable.put(chemicalSymbol, elementName);
        this.protonNumberTable.put(chemicalSymbol, protonNumber);
        this.neutronNumberTable.put(chemicalSymbol, neutronNumber);
        this.atomicMassTable.put(chemicalSymbol, atomicMass);
    }

    private String getElementName(String chemicalSymbol, int temp)
    {
        if (this.elementNameTable.containsKey(chemicalSymbol))
            return this.elementNameTable.get(chemicalSymbol);
        else
            throw new NullPointerException("Cannot get element name for \"" + chemicalSymbol + "\"");
    }

    private int getProtonNumber(String chemicalSymbol, int temp)
    {
        if (this.protonNumberTable.containsKey(chemicalSymbol))
            return this.protonNumberTable.get(chemicalSymbol);
        else
            throw new NullPointerException("Cannot get proton number for " + chemicalSymbol);
    }

    private int getNeutronNumber(String chemicalSymbol, int temp)
    {
        if (this.neutronNumberTable.containsKey(chemicalSymbol))
            return this.neutronNumberTable.get(chemicalSymbol);
        else
            throw new NullPointerException("Cannot get neutron number for " + chemicalSymbol);
    }

    private double getAtomicMass(String chemicalSymbol, int temp)
    {
        if (this.atomicMassTable.containsKey(chemicalSymbol))
            return this.atomicMassTable.get(chemicalSymbol);
        else
            throw new NullPointerException("Cannot get atomic mass for " + chemicalSymbol);
    }
    
    private static final PeriodicTable INSTANCE = new PeriodicTable();

    public static String getElementName(String chemicalSymbol)
    {
        return INSTANCE.getElementName(chemicalSymbol, 0);
    }

    public static int getProtonNumber(String chemicalSymbol)
    {
        return INSTANCE.getProtonNumber(chemicalSymbol, 0);
    }

    public static int getNeutronNumber(String chemicalSymbol)
    {
        return INSTANCE.getNeutronNumber(chemicalSymbol, 0);
    }

    public static double getAtomicMass(String chemicalSymbol)
    {
        return INSTANCE.getAtomicMass(chemicalSymbol, 0);
    }

    public static String getChemicalSymbolByAtomicNumber(int number)
    {
        return (ChemicalSymbol.SYMBOLS)[number - 1];
    }
}