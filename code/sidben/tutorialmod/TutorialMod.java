package sidben.tutorialmod;

import sidben.tutorialmod.block.MetaSevenBlock;
import sidben.tutorialmod.block.RandomDropBlock;
import sidben.tutorialmod.handler.ConfigurationHandler;
import sidben.tutorialmod.init.MyItems;
import sidben.tutorialmod.proxy.IProxy;
import sidben.tutorialmod.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
// import net.minecraftforge.fml.common.network.NetworkMod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;


@Mod(modid = Reference.ModID, name = Reference.ModName, version = Reference.ModVersion, guiFactory = Reference.GuiFactoryClass)
public class TutorialMod
{

	// Blocks
	public static Block randomDropBlock;
	public static Block metaSevenBlock;

	
	// The instance of your mod that Forge uses.
	@Mod.Instance(Reference.ModID)
	public static TutorialMod instance;
	
	
	@SidedProxy(clientSide = Reference.ClientProxyClass, serverSide = Reference.ServerProxyClass)
	public static IProxy proxy;

	
	
	
	/*---------------------------------------------------------------------------
		pre-init (load config, network handler, keybinds, items, blocks)
	  ---------------------------------------------------------------------------*/ 
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// Loads config
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		// Loads items
		MyItems.register();
		
		
		// Adds the random drop block
		randomDropBlock         = new RandomDropBlock().setHardness(1.5F).setResistance(10.0F).setUnlocalizedName("grass").setStepSound(Block.soundTypeGrass).setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(randomDropBlock, "RandomDropBlock");
		LanguageRegistry.addName(randomDropBlock, "Random Drops Block");
		
        // Adds the meta seven block
        metaSevenBlock         = new MetaSevenBlock().setHardness(0.8F).setUnlocalizedName("metaseven").setStepSound(Block.soundTypeCloth);
        GameRegistry.registerBlock(metaSevenBlock, "MetaSevenBlock");
        LanguageRegistry.addName(metaSevenBlock, "Metadata Seven Block");
        
	}

	
	/*---------------------------------------------------------------------------
		init (UI hanlders, tile entities, event handlers, recipes)
	  ---------------------------------------------------------------------------*/
	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		

		MyItems.registerRender();
	
		
		// Random drop block recipe
		GameRegistry.addRecipe(new ItemStack(randomDropBlock), "aa", "bb", 'a', new ItemStack(Items.apple, 1, 0), 'b', new ItemStack(Items.book, 1, 0));
		

		// Register my custom event handler
		//TutorialEventHandler tutorialEventHandler = new TutorialEventHandler();
		//MinecraftForge.EVENT_BUS.register(tutorialEventHandler);
	}
	
	
	/*---------------------------------------------------------------------------
		post-init ()
	  ---------------------------------------------------------------------------*/
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	
	
}
