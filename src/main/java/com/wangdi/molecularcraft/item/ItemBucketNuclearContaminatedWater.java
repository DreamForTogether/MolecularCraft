package com.wangdi.molecularcraft.item;

import java.util.List;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.block.ModBlocks;
import com.wangdi.molecularcraft.creativetab.ModBaseCreativeTabs;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;
import com.wangdi.util.physics.PhysicsConstant;
import com.wangdi.util.physics.PrefixNumber;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBucketNuclearContaminatedWater extends ItemBucket
{
    public static final String VALUE_TAG = "value";
    public static final String NAME = "bucket_nuclear_contaminated_water";
    public static final double MAX_RADIATION_VALUE = 1e6;

    public ItemBucketNuclearContaminatedWater()
    {
        super(ModBlocks.NUCLEAR_CONTAMINATED_WATER);

        this.setRegistryName(NAME);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", NAME));
        this.setCreativeTab(ModBaseCreativeTabs.MISC);
    }

    @Override
    @SuppressWarnings({"null", "rawtypes", "unchecked"})
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        boolean flag = (ModBlocks.NUCLEAR_CONTAMINATED_WATER == Blocks.AIR);
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, raytraceresult);

        if (ret != null)
            return ret;

        if (raytraceresult == null)
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        else
        {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (!worldIn.isBlockModifiable(playerIn, blockpos))
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
            
            boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
            BlockPos blockpos1 = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos : blockpos.offset(raytraceresult.sideHit);

            if (!playerIn.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemstack))
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

            if (playerIn instanceof EntityPlayerMP)
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, blockpos1, itemstack);

            worldIn.setBlockState(blockpos1, ModBlocks.NUCLEAR_CONTAMINATED_WATER.getDefaultState(), 11);
            playerIn.addStat(StatList.getObjectUseStats(this));
            return !playerIn.capabilities.isCreativeMode ? new ActionResult(EnumActionResult.SUCCESS, new ItemStack(Items.BUCKET)) : new ActionResult(EnumActionResult.SUCCESS, itemstack);
        }
    }

    public double getValue(ItemStack stack)
    {
        NBTTagCompound compound = stack.getTagCompound();

        if (compound == null)
        {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
            return 0.0;
        }

        return compound.getDouble(VALUE_TAG);
    }

    public void setValue(ItemStack stack, double value)
    {
        NBTTagCompound compound = stack.getTagCompound();

        if (compound == null)
        {
            compound = new NBTTagCompound();
            stack.setTagCompound(compound);
        }

        compound.setDouble(VALUE_TAG, value);
    }

    public void grow(ItemStack stack, double value)
    {
        double newValue = this.getValue(stack) + value;

        if (newValue > MAX_RADIATION_VALUE)
            newValue = MAX_RADIATION_VALUE;

        this.setValue(stack, newValue);
    }

    public void shrink(ItemStack stack, double value)
    {
        this.grow(stack, -value);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("null")
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String name = TextFormatting.GOLD + this.getItemStackDisplayName(stack);

        if (!tooltip.isEmpty()) tooltip.remove(tooltip.size() - 1);
        if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips) name += TextFormatting.WHITE + " (#" + Item.getIdFromItem(this) + ")";

        tooltip.add(name);
        tooltip.add(MainUtil.combinedStrings(TextFormatting.BLUE.toString(),
                                             I18n.format(Constant.RADIATION_INTENSITY_TEXT), ": ", 
                                             new PrefixNumber(this.getValue(stack)).toSplitString(),
                                             PhysicsConstant.STANDARD_RADIATION_UNIT));
    }
}