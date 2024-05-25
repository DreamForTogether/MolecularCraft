import os
from PIL import Image

element_list = [
    "", "hydrogen", "helium", "lithium", "beryllium", "boron", "carbon", "nitrogen",
    "oxygen", "fluorine", "neon", "sodium", "magnesium", "aluminium", "silicon",
    "phosphorus", "sulfur", "chlorine", "argon", "potassium", "calcium", "scandium",
    "titanium", "vanadium", "chromium", "manganese", "iron", "cobalt", "nickel",
    "copper", "zinc", "gallium", "germanium", "arsenic", "selenium", "bromine",
    "krypton", "rubidium", "strontium", "yttrium", "zirconium", "niobium", "molybdenum",
    "technetium", "ruthenium", "rhodium", "palladium", "silver", "cadmium", "indium",
    "tin", "antimony", "tellurium", "iodine", "xenon", "cesium", "barium", "lanthanum",
    "cerium", "praseodymium", "neodymium", "promethium", "samarium", "europium", "gadolinium",
    "terbium", "dysprosium", "holmium", "erbium", "thulium", "ytterbium", "lutetium",
    "hafnium", "tantalum", "tungsten", "rhenium", "osmium", "iridium", "platinum", "gold",
    "mercury", "thallium", "lead", "bismuth", "polonium", "astatine", "radon", "francium",
    "radium", "actinium", "thorium", "protactinium", "uranium", "neptunium", "plutonium",
    "americium", "curium", "berkelium", "californium", "einsteinium", "fermium", "mendelevium",
    "nobelium", "lawrencium", "rutherfordium", "dubnium", "seaborgium", "bohrium", "hassium",
    "meitnerium", "darmstadtium", "roentgenium", "copernicium", "nihonium", "flerovium",
    "moscovium", "livermorium", "tennessine", "oganesson"
]

path = os.path.dirname(__file__) +  "\\items\\"

def changeIndexColor():
    liquid_index = [35, 80]
    gas_index = [1, 2, 7, 8, 9, 10, 17, 18, 36, 54, 86]
    for i in range(1, 119):
        image = path + element_list[i] + "_atom.png"
        color = (0, 0, 0, 0)
        
        if i in liquid_index:
            color = (0, 128, 0, 255)
        elif i in gas_index:
            color = (255, 0, 0, 255)
        elif i >= 100:
            color = (105, 105, 105, 255)
        else:
            color = (0, 0, 0, 255)
        
        for x in range(1, 14):
            for y in range(1, 8):
                RGBA = getRGBA(image, (x, y))
                if RGBA == (255, 255, 255, 255):
                    setRGBA(image, (x, y), color)

def changeSymbolColor():
    for i in range(1, 119):
        image = path + element_list[i] + "_atom.png"
        color = (0, 0, 0, 0)
        
        if i == 61 or i == 43 or i >= 84:
            color = (255, 0, 0, 255)
        else:
            color = (0, 0, 0, 255) 
        
        for x in range(4, 27):
            for y in range(12, 28):
                RGBA = getRGBA(image, (x, y))
                if RGBA == (255, 255, 255, 255):
                    setRGBA(image, (x, y), color)
                    
def changeBackgroundColor():
    alkali_metals = [3, 11, 19, 37, 55, 87]
    alkaline_earth_metals = [4, 12, 20, 38, 56, 88]
    poor_metals = [13, 31, 49, 50, 81, 82, 83, 84]
    metaloids = [5, 14, 32, 33, 51, 52]
    nonmetals = [1, 6, 7, 8, 15, 16, 34]
    halogens = [9, 17, 35, 53, 85]
    noble_gases = [2, 10, 18, 36, 54, 86]
    
    for i in range(1, 119):
        image = path + element_list[i] + "_atom.png"
        color = (0, 0, 0, 0)
        
        if i in alkali_metals:
            color = (255, 102, 102, 255)
        elif i in alkaline_earth_metals:
            color = (255, 222, 173, 255)
        elif i in poor_metals:
            color = (204, 204, 204, 255)  
        elif 57 <= i <= 71:
            color = (255, 191, 255, 255)
        elif 89 <= i <= 103:
            color = (255, 153, 204, 255)
        elif i in metaloids:
            color = (204, 204, 153, 255)
        elif i in nonmetals:
            color = (160, 255, 160, 255)
        elif i in halogens:
            color = (255, 255, 153, 255)
        elif i in noble_gases:
            color = (192, 255, 255, 255)
        elif i >= 100:
            color = (232, 232, 232, 255)
        else:
            color = (255, 192, 192, 255)
        
        for x in range(1, 31):
            for y in range(1, 31):
                RGBA = getRGBA(image, (x, y))
                if RGBA[3] == (0):
                    setRGBA(image, (x, y), color)
                    
def changeMargin():
    for i in range(1, 119):
        image = path + element_list[i] + "_atom.png"
        color = (0, 0, 0, 255)

        for x in range(0, 32):
            setRGBA(image, (x, 0), color)
            setRGBA(image, (x, 31), color)
            setRGBA(image, (0, x), color)
            setRGBA(image, (31, x), color)
    
def getRGBA(filePath, pos):
    image = Image.open(filePath)
    return image.convert("RGBA").getpixel((pos[0], pos[1]))

def setRGBA(filePath, pos, RGBA):
    image = Image.open(filePath)
    pixels = image.load()
    x, y = pos
    pixels[x, y] = RGBA
    image.save(filePath)
    
if __name__ == '__main__':
    changeIndexColor()
    changeSymbolColor()
    changeBackgroundColor()
    changeMargin()