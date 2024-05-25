package com.wangdi.util;

public interface EnergyContainer
{
    public void setCurrentEnergy(double currentEnergy);
    
    public default boolean isFullCharged()
    {
        return this.getCurrentEnergy() >= this.getMaxStoredEnergy();
    }

    public default boolean isEnergyOverflow()
    {
        return this.getCurrentEnergy() > this.getMaxStoredEnergy() || this.getCurrentEnergy() < 0;
    }

    public double getCurrentEnergy();
    public double getMaxStoredEnergy();

    public default double getRemainingUncharged()
    {
        return this.getMaxStoredEnergy() - this.getCurrentEnergy();
    }
}