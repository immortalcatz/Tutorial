package sidben.tutorialmod.item;

import net.minecraft.init.Items;


/*
 * "Bucket of Cheese" idea from http://www.minecraftforge.net/forum/index.php?topic=23872.0
 * to solve smelting a Milk Bucket consuming the bucket. There is no way to avoid it without recoding 
 * the TileEntityFurnace [smeltItem] method.
 *  
 *  I added this as a new item so I can set the [MaxStackSize] to 1 instead of 16 from Cheese.
 *  If wasn't for that, I could have made Cheese Bucket a sub-item from Cheese. 
 *  
 *  Cheese Bucket icon from the Lots of Food Mod.
 *  http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/1282670-lots-of-food-1-7-10-v6
 */
public class ItemBucketCheese extends ItemBasic
{

    public ItemBucketCheese() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName("cheese_bucket");
        this.setContainerItem(Items.bucket);
    }


}
