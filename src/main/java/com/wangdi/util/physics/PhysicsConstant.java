package com.wangdi.util.physics;

public final class PhysicsConstant
{
    public static final String STANDARD_MASS_UNIT = "kg";
    public static final String STANDARD_ENERGY_UNIT = "J";
    public static final String STANDARD_RADIATION_UNIT = "Bq";

    public static final double c = 299792458.0; // speed of the light
    public static final double c_square = c * c; // speed of the light square
    public static final double u = 1.6605390666e-27; // standard atom mass (unit kg)
    public static final double NA = 6.02214076e23; // Avogadro constant
    public static final double PROTON_MASS = 1.007276466879; // proton mass (unit u)
    public static final double NEUTRON_MASS = 1.00866491588; // proton mass (unit u)

    public static final char SYMBOL_GIGA = 'G';
    public static final char SYMBOL_MEGA = 'M';
    public static final char SYMBOL_KILO = 'k';
    public static final char SYMBOL_CENTI = 'c';
    public static final char SYMBOL_MILLI = 'm';
    public static final char SYMBOL_MICRO = 'μ';
    public static final char SYMBOL_NANO = 'n';
    public static final char SYMBOL_PICO = 'p';

    public static final double PREFIX_GIGA = Math.pow(10.0, 9.0);
    public static final double PREFIX_MEGA = Math.pow(10.0, 6.0);
    public static final double PREFIX_KILO = Math.pow(10.0, 3.0);
    public static final double PREFIX_CENTI = Math.pow(10.0, -2.0);
    public static final double PREFIX_MILLI = Math.pow(10.0, -3.0);
    public static final double PREFIX_MICRO = Math.pow(10.0, -6.0);
    public static final double PREFIX_NANO = Math.pow(10.0, -9.0);
    public static final double PREFIX_PICO = Math.pow(10.0, -12.0);

    public PhysicsConstant()
    {

    }
}