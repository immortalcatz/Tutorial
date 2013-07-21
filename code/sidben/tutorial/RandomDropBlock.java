package sidben.tutorial;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class RandomDropBlock extends Block {

	
	
	/*--------------------------------------------------------------------
		Default stuff
	--------------------------------------------------------------------*/
	
    @SideOnly(Side.CLIENT)
    private Icon myIcon;

	
	public RandomDropBlock(int blockId) {
		super(blockId, Material.cloth);
	}

	
    @SideOnly(Side.CLIENT)
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	return Block.grass.getIcon(1, 0);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	return Block.grass.getBlockTextureFromSide(1);
    }
    
    
	
	
	
	/*--------------------------------------------------------------------
		Remove default drops
	--------------------------------------------------------------------*/
	
    /**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	// Makes the block drop nothing at first
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random par1Random)
    {
		// Random amount of stuff
		return 5 + par1Random.nextInt(6);
    }

	

	
	/*--------------------------------------------------------------------
		Drops random stuff when broken
	--------------------------------------------------------------------*/
    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param metadata Current metadata
     * @param fortune Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        // Gets how much items this block will drop
        int count = quantityDropped(metadata, fortune, world.rand);
        
        /*
         * Below is the default vanilla code
        for(int i = 0; i < count; i++)
        {
            int id = idDropped(metadata, world.rand, fortune);
            if (id > 0)
            {
                ret.add(new ItemStack(id, 1, damageDropped(metadata)));
            }
        }
        */
        
        
        // Debug
        System.out.println("===================================================");
        System.out.println("BlockDropDye.getBlockDropped()");
		System.out.println("	Side:       " + FMLCommonHandler.instance().getEffectiveSide());
		System.out.println("    Coords:     " + x + ", " + y + ", " + z);
		System.out.println("");
		System.out.println("    How many items will drop: " + count);

        

        // Here I make it drop random items
        int luckDraw = 0;
        int idDropped = 0;
        int metaDropped = 0;
        
        for(int i = 0; i < count; i++)
        {
        	// Gets a number between 0 and 4
        	luckDraw = world.rand.nextInt(5);
        	idDropped = 0;
        	metaDropped = 0;
        	
        	
        	// Choose what will be on the list
        	if (luckDraw == 0) { 
        		idDropped = Block.sapling.blockID;
        		metaDropped = 1;
        		
        		System.out.println("    Adding spruce sapling.");			// Debug
        	}
        	if (luckDraw == 1) { 
        		idDropped = Block.sapling.blockID;
        		metaDropped = 3;
        		
        		System.out.println("    Adding jungle sapling.");			// Debug
        	}
        	if (luckDraw == 2) { 
        		idDropped = Block.tallGrass.blockID;
        		metaDropped = 2;
        		
        		System.out.println("    Adding fern.");						// Debug
        	}
        	if (luckDraw == 3) { 
        		idDropped = Block.plantYellow.blockID;
        		metaDropped = 0;
        		
        		System.out.println("    Adding yellow flower.");			// Debug
        	}
        	if (luckDraw == 4) { 
        		idDropped = Block.plantRed.blockID;
        		metaDropped = 0;
        		
        		System.out.println("    Adding red flower.");				// Debug
        	}
        	
        	// Adds the block to the return list
        	if (idDropped > 0) {
        		ret.add(new ItemStack(idDropped, 1, metaDropped));
        	}
        }

        
        // Debug
        System.out.println("===================================================");

        
        
        return ret;
    }
	
    
}
