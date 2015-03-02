package sidben.tutorialmod.block;

import java.util.List;
import java.util.Random;
import sidben.tutorialmod.helper.LogHelper;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




/*
 * Sample block that drops random items when broken.
 * 
 * ref: Some ores, crops, silverflish block
 */
public class BlockRandomDrops extends BlockBasic
{



	/*--------------------------------------------------------------------
		Default stuff
	--------------------------------------------------------------------*/

	public BlockRandomDrops() {
		super(Material.clay);								// Makes it like silverfish block, easier to break and don't require a pick
		this.setHardness(0.75F);
		
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setUnlocalizedName("randomDrops");
	}


	


	/*--------------------------------------------------------------------
		Remove default drops
	--------------------------------------------------------------------*/

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(7) + 1;			// Random amount of stuff
	}
	
	/**
	 * Returns the quantity of items to drop with fortune.
	 */
	@Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        int bonus = random.nextInt(fortune + 2) - 1;
        if (bonus < 0) { bonus = 0; }
        return this.quantityDropped(random) * (bonus + 1);
    }


	
	

	/*--------------------------------------------------------------------
		Drops random stuff when broken
	--------------------------------------------------------------------*/
	
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        // Gets how much items this block will drop
        int count = quantityDropped(state, fortune, rand);
        

        // Here I make it drop random items
        int luckDraw = 0;
		Item item = null;
		int itemDamage = 0;
		String debugName;
		
		LogHelper.info("Random drops block broken, randomizing [" + count + "] drops.");
        
		for(int i = 0; i < count; i++)
        {
			// Gets a number between 0 and 4
			luckDraw = rand.nextInt(5);

			// Choose what will be on the list
			item = null;
			itemDamage = 0;
			debugName = "";
			
			if (luckDraw == 0) {
				item = Item.getItemFromBlock(Blocks.sapling);			// Oak Sapling
				itemDamage = BlockPlanks.EnumType.OAK.ordinal();
				debugName = "Oak Sapling";
			}
			if (luckDraw == 1) {
				item = Item.getItemFromBlock(Blocks.sapling);			// Jungle Sapling
				itemDamage = BlockPlanks.EnumType.JUNGLE.ordinal();
				debugName = "Jungle Sapling";
			}
			if (luckDraw == 2) {
				item = Item.getItemFromBlock(Blocks.tallgrass);			// Fern
				itemDamage = BlockTallGrass.EnumType.FERN.ordinal();
				debugName = "Fern";
			}
			if (luckDraw == 3) {
				item = Items.gold_ingot;								// Gold bar
				debugName = "Gold Ingot";
			}
			if (luckDraw == 4) {
				item = Item.getItemFromBlock(Blocks.sand);				// Sand
				debugName = "Sand";
			}
			
			// Adds the block to the return list
            if (item != null)
            {
            	LogHelper.info("   Adding item [" + debugName + "] with dmg value [" + itemDamage + "]");
            	
                ret.add(new ItemStack(item, 1, itemDamage));
            }
        }

		
		return ret;
    }
	
	

}
