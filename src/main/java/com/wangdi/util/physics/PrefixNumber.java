package com.wangdi.util.physics;

import java.util.HashMap;

public final class PrefixNumber
{
    private static final char[] symbolMap =
    {
        PhysicsConstant.SYMBOL_PICO, PhysicsConstant.SYMBOL_NANO, 
        PhysicsConstant.SYMBOL_MICRO, PhysicsConstant.SYMBOL_MILLI,
        PhysicsConstant.SYMBOL_CENTI, ' ', PhysicsConstant.SYMBOL_KILO,
        PhysicsConstant.SYMBOL_MEGA, PhysicsConstant.SYMBOL_GIGA
    };

    private static final double[] valueMap =
    {
        PhysicsConstant.PREFIX_PICO, PhysicsConstant.PREFIX_NANO, 
        PhysicsConstant.PREFIX_MICRO, PhysicsConstant.PREFIX_MILLI,
        PhysicsConstant.PREFIX_CENTI, 1.0, PhysicsConstant.PREFIX_KILO,
        PhysicsConstant.PREFIX_MEGA, PhysicsConstant.PREFIX_GIGA
    };
    
    public double number;
    public char prefix;

    public PrefixNumber()
    {
        this(0, ' ');
    }

    public PrefixNumber(int number, char prefix)
    {
        this.setValue((double)(number), prefix);
    }

    public PrefixNumber(int number, int prefix)
    {
        this.setValue((double)(number), (char)(prefix));
    }

    public PrefixNumber(double number, char prefix)
    {
        this.setValue(number, prefix);
    }

    public PrefixNumber(double number, int prefix)
    {
        this.setValue(number, (char)(prefix));
    }

    // iterative diveded the value map and see the first is bigger than 1;
    public PrefixNumber(long value)
    {
        this((double)(value));
    }

    public PrefixNumber(double value)
    {
        if (value / PhysicsConstant.PREFIX_GIGA >= 1)
            this.setValue(value / PhysicsConstant.PREFIX_GIGA, PhysicsConstant.SYMBOL_GIGA);
        else
        {
            double[] valueLocal = new double[valueMap.length];

            for (int i = 0; i < valueLocal.length; i++)
                valueLocal[i] = value / valueMap[i];
            
            for (int i = 0; i < valueLocal.length - 1; i++)
            {
                if (valueLocal[i] >= 1 && valueLocal[i + 1] < 1)
                {
                    this.setValue(valueLocal[i], symbolMap[i]);
                    return;
                }
            }

            this.setValue(valueLocal[valueMap.length - 2], symbolMap[symbolMap.length - 2]);
        }
    }

    // split the number eg: 10000 -> 10,000
    private String splitNumber(int bit)
    {
        String numberString = Integer.toString((int)(this.number + 0.5));
        StringBuilder splitNumber = new StringBuilder();

        int length = numberString.length();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = length - 1, j = 0; i >= 0; i--, j++)
        {
            if (j == bit)
            {
                j = 0;
                hashMap.put(i, 0);
            }
        }

        for (int i = 0; i < length; i++)
            splitNumber.append((hashMap.containsKey(i)) ? (numberString.charAt(i) + ",") : numberString.charAt(i));

        return splitNumber.toString();
    }

    private String splitNumber()
    {
        return this.splitNumber(3);
    }

    public void setValue(double number, char prefix)
    {
        this.number = number;
        this.prefix = prefix;
    }

    public double doubleValue()
    {
        for (int i = 0; i < symbolMap.length; i++)
        {
            if (symbolMap[i] == this.prefix)
                return this.number * valueMap[i];
        }

        return this.number;
    }

    @Override
    public String toString()
    {
        StringBuilder number = new StringBuilder(Integer.toString((int)(this.number + 0.5)));
        number.append(" ");

        if (this.prefix != ' ')
            number.append(this.prefix);

        return number.toString();
    }

    public String toSplitString()
    {
        StringBuilder number = new StringBuilder(this.splitNumber());
        number.append(" ");

        if (this.prefix != ' ')
            number.append(this.prefix);

        return number.toString();
    }
}