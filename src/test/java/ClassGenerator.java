import java.io.File;
import java.io.FileWriter;

public class ClassGenerator
{

    public static void main(String[] args) throws Exception
    {
        String path = "D:\\Coding\\projects\\Minecraft\\MolecularCraft\\src\\main\\java\\com\\wangdi\\molecularcraft\\item\\element\\";
        //path = "C:\\Users\\wd200\\desktop\\a\\";
        String[] symbols =
        {
            "PROMETHIUM", "SAMARIUM", "EUROPIUM", "GADOLINIUM", "TERBIUM", "DYSPROSIUM", "HOLMIUM", "ERBIUM", "THULIUM", "YTTERBIUM"
        };

        String[] smaller = new String[symbols.length];
        for (int i = 0; i < symbols.length; i++)
            smaller[i] = capitalizeFirstLetter(symbols[i].toLowerCase());

        for (int i = 0; i < symbols.length; i++)
        {
            String className = "Item" + smaller[i];
            String filePath = path + className + ".java";

            File a = new File(filePath);
            a.createNewFile();

            writeToFile("package com.wangdi.molecularcraft.item.element;\n", filePath);
            writeToFile("\n", filePath);
            writeToFile("import com.wangdi.molecularcraft.item.ItemElement;\n", filePath);
            writeToFile("import com.wangdi.util.chemistry.ChemicalSymbol;\n", filePath);
            writeToFile("\n", filePath);
            writeToFile("public class " + className + " extends ItemElement\n", filePath);
            writeToFile("{\n", filePath);
            writeToFile("    public " + className + "()\n", filePath);
            writeToFile("    {\n", filePath);
            writeToFile("        super(ChemicalSymbol." + symbols[i] + ");\n", filePath);
            writeToFile("    }\n", filePath);
            writeToFile("}", filePath);
        }
    }

    public static String capitalizeFirstLetter(String str)
    {
        if (str == null || str.isEmpty())
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
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