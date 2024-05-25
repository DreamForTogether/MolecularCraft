package com.wangdi.molecularcraft.block;

import java.util.Random;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.handler.ModGuiHandler;
import com.wangdi.molecularcraft.tileentity.TileEntityFissionReactor;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class BlockFissionReactor extends BlockFunctional
{
    public static final String NAME = "fission_reactor";
    public static final String WORK_NAME = "work_" + NAME;
    public static final String DESCRIPTION = MolecularCraft.MODID + "." + NAME + ".description";
    public static final double MAX_STORE_ENERGY = 1e13;
    public static final double ENERGY_DISPERSION_RATE = 1000;

    private static final PropertyDirection FACING = BlockHorizontal.FACING;
    private static boolean keepInventory;

    /**
     * Sets the state of the block at the given position in the world.
     *
     * @param  active     the state of the block (active or inactive)
     * @param  worldIn    the world in which the block exists
     * @param  pos        the position of the block
     */
    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            worldIn.setBlockState(pos, ModBlocks.WORK_FISSION_REACTOR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.WORK_FISSION_REACTOR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            worldIn.setBlockState(pos, ModBlocks.FISSION_REACTOR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.FISSION_REACTOR.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileEntity != null)
        {
            tileEntity.validate();
            worldIn.setTileEntity(pos, tileEntity);
        }
    }

    public BlockFissionReactor(boolean isWorking)
    {
        super(NAME, isWorking);
    }

    /**
     * Returns the item that is dropped when this block is destroyed.
     *
     * @param  state    the block state
     * @param  rand     a random number generator
     * @param  fortune  the fortune level of the tool used to destroy the block
     * @return          the item that is dropped
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.FISSION_REACTOR);
    }

    /**
     * This method is called when the block is activated by a player. It opens the GUI for the Fission Reactor block if the world is not remote.
     *
     * @param  worldIn    the world in which the block is located
     * @param  pos        the position of the block
     * @param  state      the current state of the block
     * @param  playerIn   the player who activated the block
     * @param  hand       the hand with which the block was activated
     * @param  facing     the facing direction of the block
     * @param  hitX       the x-coordinate of the hit position
     * @param  hitY       the y-coordinate of the hit position
     * @param  hitZ       the z-coordinate of the hit position
     * @return            true if the block was activated successfully, false otherwise
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
            playerIn.openGui(MolecularCraft.INSTANCE, ModGuiHandler.GUI_FISSION_REACTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());

        return true;
    }

    /**
     * Creates a new instance of TileEntityFissionReactor and returns it.
     *
     * @param  worldIn    the world in which the tile entity will be created
     * @param  meta       the metadata associated with the tile entity
     * @return            a new instance of TileEntityFissionReactor
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityFissionReactor();
    }

    /**
     * Sets the block state of the given block position in the specified world to the opposite of the
     * horizontal facing of the placer entity. Also sets the custom inventory name of the tile entity
     * at the block position to the display name of the given item stack if it has a display name.
     *
     * @param  worldIn    the world in which the block is placed
     * @param  pos        the position of the block
     * @param  state      the block state before the placement
     * @param  placer     the entity that placed the block
     * @param  stack      the item stack used to place the block
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileEntityFissionReactor)
                ((TileEntityFissionReactor)(tileEntity)).setCustomInventoryName(stack.getDisplayName());
        }
    }

    /**
     * This function is called when the block is broken. It checks if the inventory should be kept,
     * and if not, it drops the items from the inventory and updates the comparator output level.
     *
     * @param  worldIn    the world in which the block is broken
     * @param  pos        the position of the block
     * @param  state      the block state before the break
     */
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepInventory)
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof TileEntityFissionReactor)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFissionReactor)(tileEntity));
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    /**
     * Returns an ItemStack representing the item obtained when breaking this block in the specified world at the specified position with the specified state.
     *
     * @param  worldIn    the world in which the block is located
     * @param  pos        the position of the block
     * @param  state      the current state of the block
     * @return            an ItemStack representing the item obtained when breaking this block
     */
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.FISSION_REACTOR);
    }
}