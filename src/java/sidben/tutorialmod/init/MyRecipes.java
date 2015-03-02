package sidben.tutorialmod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class MyRecipes
{
    
    public static void register() {

        // Basic recipe
        GameRegistry.addRecipe(new ItemStack(MyBlocks.chargeable), "rrr", "rsr", "rrr", 'r', new ItemStack(Items.redstone, 1, 0), 's', new ItemStack(Blocks.stone, 1, 0));
        
        // Shapeless recipe, returns item with metadata
        GameRegistry.addShapelessRecipe(new ItemStack(MyItems.cheese, 1, 1), new ItemStack(MyItems.cheese, 1, 0), new ItemStack(Items.experience_bottle, 1, 0));

        // Furnace recipe
        GameRegistry.addSmelting(Items.milk_bucket, new ItemStack(MyItems.cheese, 4, 0), 1.0F);
    }

}
