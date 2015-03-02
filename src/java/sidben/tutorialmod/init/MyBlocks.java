package sidben.tutorialmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sidben.tutorialmod.block.*;
import sidben.tutorialmod.reference.Reference;


@GameRegistry.ObjectHolder(Reference.ModID)
public class MyBlocks
{


	// Blocks instances
	public static final BlockRandomDrops	randomDrops			= new BlockRandomDrops();
	public static final BlockChargeable		chargeable			= new BlockChargeable();



	// register the items
	public static void register()
	{
		GameRegistry.registerBlock(randomDrops, "random_drops");
		GameRegistry.registerBlock(chargeable, "chargeable");
	}


	// register the renderers
	@SideOnly(Side.CLIENT)
	public static void registerRender()
	{
		// ref: fakeores mod - https://github.com/elias54/FakeOres/blob/master/common/fr/elias/fakeores/client/ClientProxy.java
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(randomDrops), 0, new ModelResourceLocation(Reference.ModID + ":" + "random_drops", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(chargeable), 0, new ModelResourceLocation(Reference.ModID + ":" + "chargeable", "inventory"));
	}

}
