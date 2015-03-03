package sidben.tutorialmod.block;


import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import sidben.tutorialmod.init.MyItems;


/*
 * Sample block with custom texture.
 */
public class BlockCheese extends BlockBasic
{

    public BlockCheese() {
        super(Material.clay);
        this.setHardness(0.5F);
        this.setStepSound(SLIME_SOUND);
        this.setUnlocalizedName("cheese");
    }


    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.sandColor;
    }



    // ----------------------------------------------------
    // Block drops
    // ----------------------------------------------------

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return random.nextInt(3) + 1;  // returns 1 to 3 items
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MyItems.cheese; // returns cheese, not the cheese block
    }



}
