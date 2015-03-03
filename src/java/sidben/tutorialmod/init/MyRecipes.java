package sidben.tutorialmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class MyRecipes
{
    
    public static void register() {

        // Basic recipe
        GameRegistry.addRecipe(new ItemStack(MyBlocks.chargeable), "rrr", "rsr", "rrr", 'r', new ItemStack(Items.redstone, 1, 0), 's', new ItemStack(Blocks.stone, 1, 0));      // 3x3 recipe, one stone surrounded by 8 redstone dust = Charged Stone 
        
        // Shapeless recipe, returns item with metadata
        GameRegistry.addShapelessRecipe(new ItemStack(MyItems.cheese, 1, 1), new ItemStack(MyItems.cheese, 1, 0), new ItemStack(Items.experience_bottle, 1, 0));    // Cheese piece (metadata 0) + XP Bottle = Magic Cheese (metadata 1) 
        GameRegistry.addShapelessRecipe(new ItemStack(MyItems.cheese, 4, 0), new ItemStack(MyItems.bucketOfCheese, 1, 0)); // Cheese bucket = 4 Cheese pieces

        // Furnace recipe
        GameRegistry.addSmelting(Items.milk_bucket, new ItemStack(MyItems.bucketOfCheese, 1, 0), 1.0F); // Smelt 1 milk bucket and get 1 cheese bucket
    }

}
