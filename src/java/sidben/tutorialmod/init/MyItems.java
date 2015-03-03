package sidben.tutorialmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sidben.tutorialmod.item.*;
import sidben.tutorialmod.reference.Reference;


@GameRegistry.ObjectHolder(Reference.ModID)
public class MyItems
{


    // Items instances
    public static final ItemCheese cheese = new ItemCheese();
    public static final ItemBucketCheese bucketOfCheese = new ItemBucketCheese();



    // register the items
    public static void register()
    {
        GameRegistry.registerItem(cheese, "cheese");
        GameRegistry.registerItem(bucketOfCheese, "cheese_bucket");
    }


    // register the renderers
    // ref -
    // http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2272349-lessons-from-my-first-mc-1-8-mod
    @SideOnly(Side.CLIENT)
    public static void registerRender()
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cheese, 0, new ModelResourceLocation(Reference.ModID + ":" + "cheese", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cheese, 1, new ModelResourceLocation(Reference.ModID + ":" + "cheese", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bucketOfCheese, 0, new ModelResourceLocation(Reference.ModID + ":" + "cheese_bucket", "inventory"));
    }

}
