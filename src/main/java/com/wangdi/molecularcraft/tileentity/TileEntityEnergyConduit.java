package com.wangdi.molecularcraft.tileentity;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.BlockEnergyConduit;
import com.wangdi.util.EnergyContainer;
import com.wangdi.util.MainUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class TileEntityEnergyConduit extends TileEntity implements ITickable, EnergyContainer
{
    public static final String CUSTOME_NAME = "container.energy_conduit";

    @SideOnly(Side.CLIENT)
    public static boolean hasStoredEnergy(TileEntityEnergyConduit tileEntity)
    {
        return tileEntity.currentEnergy > 0;
    }

    private double currentEnergy;
    private String customName;

    public TileEntityEnergyConduit()
    {

    }

    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public void setCustomInventoryName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation(CUSTOME_NAME);
    }

    // read the data
    @Override
    @SuppressWarnings("null")
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.currentEnergy = compound.getDouble("CurrentEnergy");
        this.setCustomName(compound.hasKey("CustomName", 8) ? compound.getString("CustomName") : CUSTOME_NAME);
    }

    //save the data
    @Override
    @SuppressWarnings("null")
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setDouble("CurrentEnergy", this.currentEnergy);

        if (this.hasCustomName())
            compound.setString("CustomName", this.customName);

        return compound;
    }

    @Override
    public void update()
    {
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            TileEntity tileEntity = this.world.getTileEntity(this.pos.offset(facing));
            IBlockState iBlockState = this.world.getBlockState(this.pos.offset(facing));

            if (tileEntity != null)
            {
                if (tileEntity instanceof TileEntityEnergyConduit)
                {
                    TileEntityEnergyConduit neighbor = ((TileEntityEnergyConduit)(tileEntity));
                    double neighborEnergy = neighbor.currentEnergy;
                    double delta = this.currentEnergy - neighborEnergy;

                    if (!neighbor.isFullCharged() && delta > 0)
                    {
                        double energyTransmit = MainUtil.min(delta / 2.0, BlockEnergyConduit.TRANSMISSION_RATE, neighbor.getRemainingUncharged());
                        
                        this.currentEnergy -= energyTransmit;
                        neighbor.currentEnergy += energyTransmit;
                    }
                }
            }

            if (iBlockState != null && this.currentEnergy > 0)
            {
                if (BlockEnergyConduit.is_input_block(iBlockState.getBlock()) &&
                    BlockEnergyConduit.canConduitConnectTo(world, pos, facing) &&
                    tileEntity != null)
                {
                    EnergyContainer block = (EnergyContainer)(tileEntity);
                    double currentEnergy = block.getCurrentEnergy();

                    if (!block.isFullCharged())
                    {
                        double energyTransmit = MainUtil.min(this.currentEnergy / 2.0, block.getRemainingUncharged(), BlockEnergyConduit.TRANSMISSION_RATE);

                        this.currentEnergy -= energyTransmit;
                        block.setCurrentEnergy(currentEnergy + energyTransmit);
                    }
                }
            }
        }
    }

    @Override
    public void setCurrentEnergy(double currentEnergy)
    {
        this.currentEnergy = currentEnergy;   
    }

    @Override
    public double getCurrentEnergy()
    {
        return this.currentEnergy;
    }

    @Override
    public double getMaxStoredEnergy()
    {
        return BlockEnergyConduit.MAX_STORE_ENERGY;
    }

    public int getFieldCount()
    {
        return 3;
    }

    public String getGuiID()
    {
        return MolecularCraft.MODID + ":" + BlockEnergyConduit.NAME;
    }
}