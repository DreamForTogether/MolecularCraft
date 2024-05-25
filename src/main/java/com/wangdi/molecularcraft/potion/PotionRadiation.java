package com.wangdi.molecularcraft.potion;

import javax.annotation.Nonnull;

import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.GameType;

public final class PotionRadiation extends PotionBased
{
    public static final int ICON = 0;
    public static final int LIQUID_COLOR_IN = 1;

    // effect
    public static final int MAX_AMPLIFIED = 2;
    public static final int LONGEST_TIME_INTERVAL = 270000;
    public static final float DAMAGE_AMOUNT = 0.5f;

    public static final float BASIC_SPEED_DECLINE = 0.05f;

    public static final String NAME = "radiation";

    public PotionRadiation()
    {
        super(NAME, true, LIQUID_COLOR_IN, ICON);
    }

    @Override
    public void performEffect(@Nonnull EntityLivingBase entity, int amplified)
    {
        // clear positive effect
        for (PotionEffect effect : entity.getActivePotionEffects())
        {
            Potion potion = effect.getPotion();

            if (!potion.isBadEffect())
                entity.removePotionEffect(potion);
        }

        if (!entity.isDead)
        {
            // if it is player check if he is in creative mode or not
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)(entity);
                if (player.world.getWorldInfo().getGameType() != GameType.CREATIVE)
                {
                    double currentHealth = MainUtil.getMaxHealth(entity);

                    NBTTagCompound playerData = player.getEntityData();

                    if (!playerData.hasKey(Constant.MAX_HEALTH_KEY))
                    {
                        NBTTagCompound data = new NBTTagCompound();
                        data.setDouble(Constant.MAX_HEALTH_KEY, currentHealth);
                        playerData.setTag(Constant.HEALTH_KEY, data);
                    }

                    MainUtil.setMaxHealth(entity, currentHealth - DAMAGE_AMOUNT);

                    if (currentHealth - DAMAGE_AMOUNT <= 0)
                        MainUtil.setMaxHealth(entity, playerData.getCompoundTag(Constant.HEALTH_KEY).getDouble(Constant.MAX_HEALTH_KEY));
                }
            }
            else
            {
                double currentHealth = MainUtil.getMaxHealth(entity);
                MainUtil.setMaxHealth(entity, currentHealth - DAMAGE_AMOUNT);
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        int interval = LONGEST_TIME_INTERVAL / (amplifier + 1);
        return duration % 20 == 0;
    }
}