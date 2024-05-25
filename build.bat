copy D:\Coding\projects\Minecraft\MolecularCraft-1.12.2\src\main\resources\mcmod.info D:\Coding\projects\Minecraft\MolecularCraft-1.12.2\run\resourcepacks\resources
copy D:\Coding\projects\Minecraft\MolecularCraft-1.12.2\src\main\resources\pack.mcmeta D:\Coding\projects\Minecraft\MolecularCraft-1.12.2\run\resourcepacks\resources
xcopy /s /y D:\Coding\projects\Minecraft\MolecularCraft-1.12.2\src\main\resources\assets D:\Coding\projects\Minecraft\MolecularCraft-1.12.2\run\resourcepacks\resources

gradlew build -info