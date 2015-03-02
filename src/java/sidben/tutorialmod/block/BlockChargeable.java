package sidben.tutorialmod.block;


import java.util.List;
import java.util.Random;
import sidben.tutorialmod.helper.LogHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/*
 * Sample block that changes meta when the player right-clicks with a certain item in hand.
 * 
 * ref: crops, redstone lamp
 */
public class BlockChargeable extends BlockBasic
{

	private static final int MidCharge = 3;
	private static final int MaxCharge = 5;
	public static final PropertyInteger	CHARGE	= PropertyInteger.create("charge", 0, MaxCharge); // Block state"charge", can go from 0 to 5


	public BlockChargeable() {
		super(Material.rock); 			// Need a pick to break

		this.setHardness(1.5F); 		// same as stone
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);

		this.setUnlocalizedName("chargeable");

		// Block State (old metadata)
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHARGE, Integer.valueOf(0)));
	}



	//----------------------------------------------------
	// Block state stuff
	//----------------------------------------------------

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(CHARGE, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer) state.getValue(CHARGE)).intValue();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { CHARGE });
	}



	//----------------------------------------------------
	// Charging the block
	//----------------------------------------------------

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
    	int myCharge = ((Integer)state.getValue(CHARGE)).intValue();
    	
		if (playerIn.getCurrentEquippedItem() != null) {
			final Item item = playerIn.getCurrentEquippedItem().getItem();

			// check if the player right-clicked with redstone
			if (item == Items.redstone && myCharge < MaxCharge) {
				this.charge(worldIn, pos, state); 							// charge the block
				--playerIn.getCurrentEquippedItem().stackSize; 				// Remove from player inventory
				return true;
			}
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
	}


	// based on IGrowable.grow
	public void charge(World worldIn, BlockPos pos, IBlockState state)
	{
		int i = ((Integer) state.getValue(CHARGE)).intValue() + 1;
		if (i > MaxCharge) i = MaxCharge;
		
		LogHelper.info("Charging block to value [" + i + "].");
		
		worldIn.setBlockState(pos, state.withProperty(CHARGE, Integer.valueOf(i)), 2);
	}
	
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	int myCharge = ((Integer)state.getValue(CHARGE)).intValue();
    	
    	if (myCharge == MaxCharge) {
            this.spawnParticles(worldIn, pos);
        }
    }
    
    
    // same as redstone ore
    private void spawnParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).getBlock().isOpaqueCube())
            {
                d2 = (double)pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).getBlock().isOpaqueCube())
            {
                d2 = (double)pos.getY() - d0;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).getBlock().isOpaqueCube())
            {
                d3 = (double)pos.getZ() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).getBlock().isOpaqueCube())
            {
                d3 = (double)pos.getZ() - d0;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).getBlock().isOpaqueCube())
            {
                d1 = (double)pos.getX() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).getBlock().isOpaqueCube())
            {
                d1 = (double)pos.getX() - d0;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }
	


	
	
	//----------------------------------------------------
	// Block drops
	//----------------------------------------------------
	
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
    	return ((Integer)state.getValue(CHARGE)).intValue() == MaxCharge ? random.nextInt(5) + 1 : 1;
    }

    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	int myCharge = ((Integer)state.getValue(CHARGE)).intValue();
    	
    	if (myCharge < MidCharge) {
    		// low charge, drops the block
    		return Item.getItemFromBlock(this);
    	}
    	else if (myCharge >= MidCharge && myCharge < MaxCharge) {
    		// medium charge, can drop redstone
    		return rand.nextInt(10) <= 2 ? Items.redstone : Item.getItemFromBlock(this);
    	}
    	else if (myCharge == MaxCharge) {
    		// full charge, drops redstone
    		return Items.redstone;
    	} 
    	else {
    		return null;
    	}
    }

    
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
    	// Get the original drops
        List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
        
        
        // adds some bonus quartz
    	int myCharge = ((Integer)state.getValue(CHARGE)).intValue();
   	
    	if (myCharge == MaxCharge) {
	        Random rand = world instanceof World ? ((World)world).rand : new Random();
	        int bonus = rand.nextInt(3);
			LogHelper.info("Dropping bonus [" + bonus + "] quartz.");
	        
			for(int i = 0; i < bonus; i++)
	        {
				Item item = Items.quartz;
				ret.add(new ItemStack(item, 1, 0));
	        }
    	}

        return ret;
    }
	

}
