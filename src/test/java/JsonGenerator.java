import java.io.File;
import java.io.FileWriter;

public class JsonGenerator
{
    public static void main(String[] args) throws Exception
    {
        String path = "D:\\Coding\\projects\\Minecraft\\MolecularCraft\\src\\main\\resources\\assets\\molecularcraft\\models\\item\\";
        //path = "C:\\Users\\wd200\\desktop\\a\\";
        String[] symbols =
        {
            "HYDROGEN", "HELIUM", "LITHIUM", "BERYLLIUM", "BORON", "CARBON", "NITROGEN", "OXYGEN", "FLUORINE", "NEON",
            "SODIUM", "MAGNESIUM", "ALUMINIUM", "SILICON", "PHOSPHORUS", "SULFUR", "CHLORINE", "ARGON", "POTASSIUM", "CALCIUM",
            "SCANDIUM", "TITANIUM", "VANADIUM", "CHROMIUM", "MANGANESE", "IRON", "COBALT", "NICKEL", "COPPER", "ZINC",
            "GALLIUM", "GERMANIUM", "ARSENIC", "SELENIUM", "BROMINE", "KRYPTON", "RUBIDIUM", "STRONTIUM", "YTTRIUM", "ZIRCONIUM",
            "NIOBIUM", "MOLYBDENUM", "TECHNETIUM", "RUTHENIUM", "RHODIUM", "PALLADIUM", "SILVER", "CADMIUM", "INDIUM", "TIN",
            "ANTIMONY", "TELLURIUM", "IODINE", "XENON", "CESIUM", "BARIUM", "LANTHANUM", "CERIUM", "PRASEODYMIUM", "NEODYMIUM",
            "PROMETHIUM", "SAMARIUM", "EUROPIUM", "GADOLINIUM", "TERBIUM", "DYSPROSIUM", "HOLMIUM", "ERBIUM", "THULIUM", "YTTERBIUM",
            "LUTETIUM", "HAFNIUM", "TANTALUM", "TUNGSTEN", "RHENIUM", "OSMIUM", "IRIDIUM", "PLATINUM", "GOLD", "MERCURY",
            "THALLIUM", "LEAD", "BISMUTH", "POLONIUM", "ASTATINE", "RADON", "FRANCIUM", "RADIUM", "ACTINIUM", "THORIUM",
            "PROTACTINIUM", "URANIUM", "NEPTUNIUM", "PLUTONIUM", "AMERICIUM", "CURIUM", "BERKELIUM", "CALIFORNIUM", "EINSTEINIUM", "FERMIUM",
            "MENDELEVIUM", "NOBELIUM", "LAWRENCIUM", "RUTHERFORDIUM", "DUBNIUM", "SEABORGIUM", "BOHRIUM", "HASSIUM", "MEITNERIUM", "DARMSTADTIUM",
            "ROENTGENIUM", "COPERNICIUM", "NIHONIUM", "FLEROVIUM", "MOSCOVIUM", "LIVERMORIUM", "TENNESSINE", "OGANESSON"
        };

        String[] smaller = new String[symbols.length];
        for (int i = 0; i < symbols.length; i++)
            smaller[i] = capitalizeFirstLetter(symbols[i].toLowerCase());

        for (int i = 0; i < symbols.length; i++)
        {
            String fileName = smaller[i] + "_atom.json";
            String filePath = path + fileName;
            File a = new File(filePath);
            a.createNewFile();

            writeToFile("{\n", filePath);
            writeToFile("    \"parent\": \"minecraft:item/generated\",\n", filePath);
            writeToFile("    \"textures\":\n", filePath);
            writeToFile("    {\n", filePath);
            writeToFile("        \"layer0\": \"molecularcraft:items/" + smaller[i] + "_atom\"\n", filePath);
            writeToFile("    }\n", filePath);
            writeToFile("}", filePath);
        }
    }

    public static String capitalizeFirstLetter(String str)
    {
        if (str == null || str.isEmpty())
            return str;
        return str.substring(0).toLowerCase();
    }

    public static void writeToFile(String content, String fileName) throws Exception
    {
        FileWriter writer = null;

        try
        {
            writer = new FileWriter(fileName, true);
            writer.write(content);
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
    }
}