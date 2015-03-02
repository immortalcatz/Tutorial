package sidben.tutorialmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import sidben.tutorialmod.handler.ConfigurationHandler;
import sidben.tutorialmod.handler.TutorialEventHandler;
import sidben.tutorialmod.init.MyBlocks;
import sidben.tutorialmod.init.MyItems;
import sidben.tutorialmod.proxy.IProxy;
import sidben.tutorialmod.reference.Reference;


// import net.minecraftforge.fml.common.network.NetworkMod;


@Mod(modid = Reference.ModID, name = Reference.ModName, version = Reference.ModVersion, guiFactory = Reference.GuiFactoryClass)
public class TutorialMod
{

	// The instance of your mod that Forge uses.
	@Mod.Instance(Reference.ModID)
	public static TutorialMod	instance;


	@SidedProxy(clientSide = Reference.ClientProxyClass, serverSide = Reference.ServerProxyClass)
	public static IProxy	  proxy;



	/*---------------------------------------------------------------------------
		pre-init (load config, network handler, keybinds, items, blocks)
	  ---------------------------------------------------------------------------*/
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		// Loads config
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		// Loads items
		MyItems.register();

		// Loads blocks
		MyBlocks.register();

	}


	/*---------------------------------------------------------------------------
		init (UI hanlders, tile entities, event handlers, recipes)
	  ---------------------------------------------------------------------------*/
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{

		// Item renderers
		MyItems.registerRender();

		// Block renderes
		MyBlocks.registerRender();


		// Random drop block recipe
		/*
		 * GameRegistry.addRecipe(new ItemStack(randomDropBlock), "aa", "bb",
		 * 'a', new ItemStack(Items.apple, 1, 0), 'b', new ItemStack(
		 * Items.book, 1, 0));
		 */

		// Register my custom event handler
		TutorialEventHandler tutorialEventHandler = new TutorialEventHandler();
		MinecraftForge.EVENT_BUS.register(tutorialEventHandler);
	}


	/*---------------------------------------------------------------------------
		post-init ()
	  ---------------------------------------------------------------------------*/
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}



}
