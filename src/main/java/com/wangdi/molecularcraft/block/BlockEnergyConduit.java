package com.wangdi.molecularcraft.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.wangdi.molecularcraft.MolecularCraft;
import com.wangdi.molecularcraft.creativetab.ModCreativeTabs;
import com.wangdi.molecularcraft.tileentity.TileEntityEnergyConduit;
import com.wangdi.util.Constant;
import com.wangdi.util.MainUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class BlockEnergyConduit extends Block implements ITileEntityProvider
{
    public static final String NAME = "energy_conduit";
    public static final String DESCRIPTION = MolecularCraft.MODID + "." + NAME + ".description";
    public static final double MAX_STORE_ENERGY = 1e9;
    public static final double TRANSMISSION_RATE = 1e6;
    public static final double ENERGY_DISPERSION_RATE = 1000;

    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool WEST = PropertyBool.create("west");

    public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875);

    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.3125, 0.3125, 0.3125, 0.6875, 1.0, 0.6875);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.3125, 0.0, 0.3125, 0.6875, 0.6875, 0.6875);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.3125, 0.3125, 0.0, 0.6875, 0.6875, 0.3125);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.3125, 0.3125, 0.6875, 0.6875, 0.6875, 1.0);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.6875, 0.3125, 0.3125, 1.0, 0.6875, 0.6875);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0, 0.3125, 0.3125, 0.3125, 0.6875, 0.6875);

    public static final PropertyBool[] PROPERTY_ARRAY = new PropertyBool[] {UP, DOWN, NORTH, SOUTH, EAST, WEST};
    public static final AxisAlignedBB[] ALIGNED_ARRAY = new AxisAlignedBB[] {UP_AABB, DOWN_AABB, NORTH_AABB, SOUTH_AABB, EAST_AABB, WEST_AABB};

    private static final EnumFacing[] FACINGS = new EnumFacing[] {EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST};

    // value is the field of currentEnergy
    private static final Map<Class<? extends Block>, Integer> INPUT_BLOCKS = new HashMap<Class<? extends Block>, Integer>()
    {
        {
            this.put(ModBlocks.ELEMENT_DECOMPOSER.getClass(), 1);
            this.put(ModBlocks.SUBSTANCE_DECOMPOSER.getClass(), 1);
        }
    };

    private static final Map<Class<? extends Block>, Short> OUTPUT_BLOCKS = new HashMap<Class<? extends Block>, Short>()
    {
        {
            this.put(ModBlocks.FISSION_REACTOR.getClass(), null);
        }
    };

    /**
     * Checks if the given block is an input block.
     *
     * @param  block  the block to check
     * @return        true if the block is an input block, false otherwise
     */
    public static boolean is_input_block(Block block)
    {
        return INPUT_BLOCKS.containsKey(block.getClass());
    }

    /**
     * Checks if the given block is an output block.
     *
     * @param  block  the block to check
     * @return        true if the block is an output block, false otherwise
     */
    public static boolean is_output_block(Block block)
    {
        return OUTPUT_BLOCKS.containsKey(block.getClass());
    }

    /**
     * Checks if a conduit can connect to a block in a given direction.
     *
     * @param  world    the world in which the block exists
     * @param  pos      the position of the block
     * @param  facing   the direction in which the conduit is facing
     * @return          true if the conduit can connect to the block, false otherwise
     */
    public static boolean canConduitConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        return canConnectTo(world, pos.offset(facing), facing.getOpposite());
    }

    /**
     * Checks if a conduit can connect to a block in a given direction.
     *
     * @param  worldIn  the world in which the block exists
     * @param  pos      the position of the block
     * @param  facing   the direction in which the conduit is facing
     * @return          true if the conduit can connect to the block, false otherwise
     */
    private static boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        
        if (block instanceof BlockEnergyConduit)
            return true;
        
        if (is_input_block(block) && facing == iblockstate.getValue(BlockHorizontal.FACING).rotateY())
            return true;

        return is_output_block(block) && facing == iblockstate.getValue(BlockHorizontal.FACING).rotateY().getOpposite();
    }

    public BlockEnergyConduit()
    {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.FALSE).withProperty(EAST, Boolean.FALSE).withProperty(SOUTH, Boolean.FALSE).withProperty(WEST, Boolean.valueOf(false)));
        
        this.setSoundType(SoundType.METAL);
        this.setRegistryName(NAME);
        this.setUnlocalizedName(MainUtil.combinedStrings(MolecularCraft.MODID, ".", NAME));
        this.setCreativeTab(ModCreativeTabs.BLOCKS);
    }

    /**
     * Adds collision boxes to a list based on the given block state, world, position, entity box, and collision boxes.
     *
     * @param  state         the block state
     * @param  worldIn       the world
     * @param  pos           the position
     * @param  entityBox     the entity box
     * @param  collidingBoxes the list of collision boxes
     * @param  entityIn      the entity (nullable)
     * @param  isActualState whether the state is the actual state
     */
    @Override
    @SuppressWarnings("null")
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
            state = state.getActualState(worldIn, pos);

        addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);

        for (int i = 0 ; i < PROPERTY_ARRAY.length; i++)
        {
            if (state.getValue(PROPERTY_ARRAY[i]).booleanValue());
                addCollisionBoxToList(pos, entityBox, collidingBoxes, ALIGNED_ARRAY[i]);
        }
    }

    /**
     * Returns the AxisAlignedBB bounding box for the given block state, world, and position.
     *
     * @param  state      the block state
     * @param  source     the block access
     * @param  pos        the block position
     * @return            the AxisAlignedBB bounding box
     */
    @Override
    @SuppressWarnings("null")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        int[] array = new int[6];
        state = this.getActualState(state, source, pos);

        for (int i = 0; i < FACINGS.length; i++)
        {
            if (state.getValue(PROPERTY_ARRAY[i]).booleanValue())
                array[i] = 1;
        }

        Vec3d start = new Vec3d(0.3125, 0.3125, 0.3125);
        Vec3d end = new Vec3d(0.6875, 0.6875, 0.6875);

        //[y+][y-][z-][z+][x+][x-]
        if (array[0] == 1)
            end = new Vec3d(end.x, 1.0, end.z);
        
        if (array[1] == 1)
            start = new Vec3d(start.x, 0.0, start.z);

        if (array[2] == 1)
            start = new Vec3d(start.x, start.y, 0.0);
        
        if (array[3] == 1)
            end = new Vec3d(end.x, end.y, 1.0);

        if (array[4] == 1)
            end = new Vec3d(1.0, end.y, end.z);
        
        if (array[5] == 1)
            start = new Vec3d(0.0, start.y, start.z);

        return new AxisAlignedBB(start, end);
    }

    /**
     * Returns whether the block is opaque or not.
     *
     * @param  state  the block state
     * @return        true if the block is opaque, false otherwise
     */
    @Override
    @SuppressWarnings("null")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    /**
     * Returns whether the block is a full cube or not.
     *
     * @param  state  the block state
     * @return        true if the block is a full cube, false otherwise
     */
    @Override
    @SuppressWarnings("null")
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Determines if the block at the given position in the given world is passable.
     *
     * @param  worldIn  the world in which the block exists
     * @param  pos      the position of the block
     * @return          true if the block is passable, false otherwise
     */
    @Override
    @SuppressWarnings("null")
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }
    
    /**
     * Determines whether a specific side of a block should be rendered.
     *
     * @param  blockState  the block state
     * @param  blockAccess the block access
     * @param  pos         the block position
     * @param  side        the side to check
     * @return             true if the side should be rendered, false otherwise
     */
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("null")
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    /**
     * Activates the block when it is right-clicked by a player.
     *
     * @param  worldIn    the world in which the block exists
     * @param  pos        the position of the block
     * @param  state      the current state of the block
     * @param  playerIn   the player who activated the block
     * @param  hand       the hand with which the player activated the block
     * @param  facing     the facing direction of the block
     * @param  hitX       the X coordinate of the hit position
     * @param  hitY       the Y coordinate of the hit position
     * @param  hitZ       the Z coordinate of the hit position
     * @return            true if the block was activated successfully, false otherwise
     */
    @Override
    @SuppressWarnings("null")
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
            return ItemLead.attachToFence(playerIn, worldIn, pos);
        else
        {
            ItemStack itemstack = playerIn.getHeldItem(hand);
            return itemstack.getItem() == Items.LEAD || itemstack.isEmpty();
        }
    }

    /**
     * Returns the metadata value for the given block state.
     *
     * @param  state  the block state for which to get the metadata
     * @return        the metadata value for the given block state
     */
    @Override
    @SuppressWarnings("null")
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    /**
     * Returns the actual state of the block, taking into account the connections to other blocks in the given world.
     *
     * @param  state      the current block state
     * @param  worldIn    the world in which the block exists
     * @param  pos        the position of the block
     * @return            the actual state of the block
     */
    @Override
    @SuppressWarnings("null")
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(UP, canConduitConnectTo(worldIn, pos, EnumFacing.UP))
                    .withProperty(DOWN, canConduitConnectTo(worldIn, pos, EnumFacing.DOWN))
                    .withProperty(NORTH, canConduitConnectTo(worldIn, pos, EnumFacing.NORTH))
                    .withProperty(EAST,  canConduitConnectTo(worldIn, pos, EnumFacing.EAST))
                    .withProperty(SOUTH, canConduitConnectTo(worldIn, pos, EnumFacing.SOUTH))
                    .withProperty(WEST,  canConduitConnectTo(worldIn, pos, EnumFacing.WEST));
    }

    /**
     * Returns the block state with the specified rotation applied.
     *
     * @param  state  the original block state
     * @param  rot    the rotation to apply
     * @return        the block state with the rotation applied
     */
    @Override
    @SuppressWarnings("null")
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case CLOCKWISE_180:
                return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
            case CLOCKWISE_90:
                return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
            default:
                return state;
        }
    }

    /**
     * Returns a new block state with the specified mirror transformation applied.
     *
     * @param  state     the original block state
     * @param  mirrorIn  the mirror transformation to apply
     * @return           the new block state with the mirror transformation applied
     */
    @Override
    @SuppressWarnings("deprecation") 
    public IBlockState withMirror(@SuppressWarnings("null") IBlockState state, @SuppressWarnings("null") Mirror mirrorIn)
    {
        switch (mirrorIn)
        {
            case LEFT_RIGHT:
                return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
            case FRONT_BACK:
                return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
            default:
                return super.withMirror(state, mirrorIn);
        }
    }

    /* ======================================== FORGE START ======================================== */

    /**
     * Checks if a block can be connected to another block in a given direction.
     *
     * @param  world    the world in which the blocks exist
     * @param  pos      the position of the block
     * @param  facing   the direction in which the block is facing
     * @return          true if the block can be connected to another block, false otherwise
     */
    @Override
    @SuppressWarnings("null")
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        return canConduitConnectTo(world, pos, facing);
    }

    /* ======================================== FORGE END ======================================== */

    /**
     * Returns the shape of the block face in the given world, state, position, and facing.
     *
     * @param  worldIn  the world in which the block exists
     * @param  state    the state of the block
     * @param  pos      the position of the block
     * @param  face     the facing direction
     * @return          the shape of the block face, either MIDDLE_POLE or CENTER
     */
    @Override
    @SuppressWarnings("null")
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }

    /**
     * Adds information to the tooltip of an item stack.
     *
     * @param  stack    the item stack
     * @param  worldIn  the world in which the item stack exists (nullable)
     * @param  tooltip  the list of strings to which the information will be added
     * @param  flagIn   the tooltip flag
     */
    @Override
    @SuppressWarnings("null") 
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        Item item =  new ItemBlock(ModBlocks.SUBSTANCE_DECOMPOSER);
        String name = MainUtil.combinedStrings(TextFormatting.GOLD.toString(), I18n.format(stack.getDisplayName()));
        String moreInfo = MainUtil.combinedStrings(TextFormatting.GRAY.toString(), I18n.format(Constant.MORE_INFO_TEXT));
        String description = MainUtil.combinedStrings(TextFormatting.GRAY.toString(), I18n.format(DESCRIPTION));

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

    /**
     * Creates a new instance of TileEntityEnergyConduit and returns it.
     *
     * @param  worldIn    the world in which the tile entity will be created
     * @param  meta       the metadata associated with the tile entity
     * @return            a new instance of TileEntityEnergyConduit
     */
    @Override
    @SuppressWarnings("null")
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityEnergyConduit();
    }

    /**
     * Overrides the createBlockState method from the superclass to create a new BlockStateContainer 
     * with the given PROPERTY_ARRAY. This method is used to initialize the block state for the EnergyConduit block.
     *
     * @return         	a new BlockStateContainer object with the PROPERTY_ARRAY
     */
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PROPERTY_ARRAY);
    }
}