package com.wangdi.util.chemistry;

import java.util.ArrayList;
import java.util.List;

import com.wangdi.molecularcraft.item.ItemElement;
import com.wangdi.molecularcraft.item.ItemSubstance;

import net.minecraft.item.ItemStack;

public final class SubstanceHelper
{
    private String chemicalFormula;
    private List<ItemStack> composition;

    private void init(List<ItemStack> composition)
    {
        this.chemicalFormula = "";
        this.composition = composition;

        for (ItemStack itemStack : composition)
        {
            ItemElement element = (ItemElement)(itemStack.getItem());
            chemicalFormula += element.chemicalSymbols;
            if (itemStack.getCount() > 0) chemicalFormula += itemStack.getCount();
        }
    }

    public SubstanceHelper(String chemicalFormula)
    {
        String temp = "";
        List<String> splitString = new ArrayList<>();

        for (int i = 0; i < chemicalFormula.length(); i++)
        {
            char currentChar = chemicalFormula.charAt(i);

            if (Character.isUpperCase(currentChar) && !temp.isEmpty())
            {
                splitString.add(temp);
                temp = "";
            }

            temp += currentChar;
        }

        if (!temp.isEmpty())
            splitString.add(temp);

        this.composition = new ArrayList<>();

        for (int i = 0; i < splitString.size(); i++)
        {
            String string = splitString.get(i);
            String symbol = "";
            String number = "";

            for (Character c : string.toCharArray())
            {
                if (Character.isLetter(c))
                    symbol += c;
                else if (Character.isDigit(c))
                    number += c;
            }
            
            if (number.isEmpty()) number = "1";

            this.composition.add(new ItemStack(ItemSubstance.getItemBySymbol(symbol), Integer.parseInt(number)));
        }
    }

    public SubstanceHelper(List<ItemStack> composition)
    {
        this.init(composition);
    }

    public List<ItemStack> getComposition()
    {
        return new ArrayList<>(this.composition);
    }

    @Override
    public String toString()
    {
        return this.chemicalFormula;
    }
}