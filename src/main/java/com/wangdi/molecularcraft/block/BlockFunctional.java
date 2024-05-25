package com.wangdi.molecularcraft.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockFunctional extends BlockContainer
{
    protected static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static boolean KEEP_INVENTORY;

    protected final boolean isWorking;
    protected final String name;
    protected final String description;

    public BlockFunctional(String blockName, boolean isWorking)
    {
        super(Material.IRON);
        
        this.name = isWorking ? "work_" + blockName : blockName;

        this.isWorking = isWorking;
        this.description = MainUtil.combinedStrings(MolecularCraft.MODID, ".", this.name, ".description");

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setSoundType(SoundType.METAL);
        this.setRegistryName(name);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", name));

        if (!isWorking)
            this.setCreativeTab(ModCreativeTabs.BLOCKS);
    }

    @Override
    @SuppressWarnings("null")
    public abstract Item getItemDropped(IBlockState state, Random rand, int fortune);

    @Override
    @SuppressWarnings("null")
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
                enumfacing = EnumFacing.SOUTH;
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
                enumfacing = EnumFacing.NORTH;
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
                enumfacing = EnumFacing.EAST;
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
                enumfacing = EnumFacing.WEST;

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("null")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (this.isWorking)
        {
            EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
            double d0 = (double)(pos.getX() + 0.5);
            double d1 = (double)(pos.getY() + rand.nextDouble() * 6.0 / 16.0);
            double d2 = (double)(pos.getZ() + 0.5);
            double d4 = rand.nextDouble() * 0.6 - 0.3;

            if (rand.nextDouble() < 0.1D)
                worldIn.playSound((double)(pos.getX() + 0.5), (double)(pos.getY()), (double)(pos.getZ() + 0.5), SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);

            switch (enumfacing)
            {
                case WEST:
                {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                }
                case EAST:
                {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                }
                case NORTH:
                {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    break;
                }
                case SOUTH:
                {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
                }
                default:
                {

                }
            }
        }
    }

    @Override
    @SuppressWarnings("null")
    public abstract boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ);

    @Override
    @SuppressWarnings("null")
    public abstract TileEntity createNewTileEntity(World worldIn, int meta);

    @Override
    @SuppressWarnings("null")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    @SuppressWarnings("null")
    public abstract void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack);

    @Override
    @SuppressWarnings("null")
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SuppressWarnings("null")
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    @SuppressWarnings("null")
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    @SuppressWarnings("null")
    public abstract ItemStack getItem(World worldIn, BlockPos pos, IBlockState state);

    @Override
    @SuppressWarnings("null")
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
            enumfacing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    @SuppressWarnings("null")
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)(state.getValue(FACING))).getIndex();
    }

    @Override
    @SuppressWarnings("null")
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)(state.getValue(FACING))));
    }

    @Override
    @SuppressWarnings("null")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)(state.getValue(FACING))));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    @SuppressWarnings("null")
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        Item item =  new ItemBlock(ModBlocks.SUBSTANCE_DECOMPOSER);
        String name = MainUtil.combinedStrings(TextFormatting.GOLD.toString(), I18n.format(stack.getDisplayName()));
        String moreInfo = MainUtil.combinedStrings(TextFormatting.GRAY.toString(), I18n.format(Constant.MORE_INFO_TEXT));
        String description = MainUtil.combinedStrings(TextFormatting.GRAY.toString(), I18n.format(this.description));

        if (!tooltip.isEmpty()) tooltip.remove(tooltip.size() - 1);
        if (Minecraft.getMinecraft().gameSettings.advancedItemTooltips)
            name += MainUtil.combinedStrings(TextFormatting.WHITE.toString(), " (#", Integer.toString(Item.getIdFromItem(item)), ")");

        tooltip.add(name);
        tooltip.add("");
        
        if (GuiScreen.isShiftKeyDown())
            tooltip.set(tooltip.size() - 1, description);
        else
            tooltip.set(tooltip.size() - 1, moreInfo);
    }
}