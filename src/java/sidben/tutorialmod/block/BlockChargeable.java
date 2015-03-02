package sidben.tutorialmod.block;


import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/*
 * Sample block that changes meta when the player right-clicks with a certain item in hand.
 * 
 * ref: crops, redstone lamp
 */
public class BlockChargeable extends BlockBasic
{

	private final boolean isCharged;
	
	

	public BlockChargeable(boolean isCharged) {
		super(Material.rock);								// Need a pick to break
		
		this.setHardness(1.5F);								// same as stone
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);
		
		this.isCharged = isCharged;							// Block State (old metadata)
		
		this.setUnlocalizedName("chargeable");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}


	
    

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	/*
	 * public int idDropped(int metadata, Random par2Random, int par3) { // If
	 * the metadata if < 7, always drop itself, the block is not "full" if
	 * (metadata < 7) { return this.blockID; } // If the metadata is 7, drops
	 * dye else { return Item.dyePowder.itemID; } }
	 */

	/**
	 * Determines the damage on the item the block drops. Used in cloth and
	 * wood.
	 */
	/*
	 * @Override public int damageDropped(int metadata) { // If the metadata if
	 * < 7, always drop meta 0, the block is not "full" if (metadata < 7) {
	 * return 0; } // If the metadata is 7, drops metadata 4 (lapis dye color)
	 * else { return 4; } }
	 */



	/**
	 * Called upon block activation (right click on the block.)
	 */
	/*
	 * @Override public boolean onBlockActivated(World par1World, int x, int y,
	 * int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	 * { // Checks if the player have gunpowder in hand. if
	 * (player.getCurrentEquippedItem().itemID == Item.gunpowder.itemID) {
	 * 
	 * // Gets the current metadata int myData = par1World.getBlockMetadata(x,
	 * y, z);
	 * 
	 * // If below 7, adds 1 to the meta if (myData < 7) { par1World.setBlock(x,
	 * y, z, this.blockID, myData + 1, 2);
	 * 
	 * if (par1World.isRemote) { // Informs the player the block metadata
	 * player.addChatMessage("Almost there, I'm on metadata " + (myData + 1) +
	 * " of 7"); }
	 * 
	 * } else { if (par1World.isRemote) { // Informs the player the block is
	 * "full" player.addChatMessage("I'm full!"); } } }
	 * 
	 * return false; }
	 */


	/*
	 * @Override public void onBlockDestroyedByPlayer(World world, int x, int y,
	 * int z, int meta) { System.out.println("Block destroyed by player - " +
	 * meta);
	 * 
	 * if (!world.isRemote) { if (meta >= 7) { // Resets the metadata
	 * world.setBlock(x, y, z, this.blockID, 0, 2); } } }
	 */

}
