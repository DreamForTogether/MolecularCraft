package com.wangdi.molecularcraft.item.crafting;

public class ModRecipes
{
    public static final ModBaseRecipes ELEMENT_DECOMPOSER = new ElementDecomposerRecipes();
    public static final ModBaseRecipes FISSION_REACTOR = new FissionReactorRecipes();
    public static final ModBaseRecipes MATERIAL_DECOMPOSER = new MatrialDecomposerRecipes();
    public static final ModBaseRecipes SUBSTANCE_DECOMPOSER = new SubstanceDecomposerRecipes();

    public static final ModBaseRecipes[] RECIPES = new ModBaseRecipes[]
    {
        ELEMENT_DECOMPOSER, FISSION_REACTOR, MATERIAL_DECOMPOSER, SUBSTANCE_DECOMPOSER
    };

    public ModRecipes()
    {

    }
}