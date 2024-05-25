package com.wangdi.molecularcraft.potion;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

public class ModPotionTypes extends PotionType
{
    public static class PotionNuclearContaminatedWater extends PotionTypeBased
    {
        public static String NAME = "nuclear_contaminated_water";

        public PotionNuclearContaminatedWater()
        {
            super(NAME, new PotionEffect(ModPotions.RADIATION, Integer.MAX_VALUE, PotionRadiation.MAX_AMPLIFIED),
                        new PotionEffect(MobEffects.SLOWNESS, Integer.MAX_VALUE, 0),
                        new PotionEffect(MobEffects.WEAKNESS, Integer.MAX_VALUE, 0));
        }
    };

    public static final PotionType RADIATION = new PotionNuclearContaminatedWater();

    public static final PotionType[] POTION_TYPES = new PotionType[]
    {
        RADIATION
    };

    static PotionTypeBased createType(Potion potion)
    {
        return new PotionTypeBased(potion.getName(),
               new PotionEffect(potion, potion.isBadEffect() ? 1800 : 3600));
    }
}