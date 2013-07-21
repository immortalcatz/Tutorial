package sidben.tutorial;


import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;




@Mod(modid="sidbentutorial", name="Sidben Tutorial Mod", version="1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ModTutorial {

	

	// The instance of your mod that Forge uses.
	@Instance("sidbentutorial")
	public static ModTutorial instance;
	
	
	
	// Blocks
	public static Block randomDropBlock;
	
	
	
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// Adds the random drop block
		randomDropBlock         = new RandomDropBlock(500).setHardness(1.5F).setResistance(10.0F).setUnlocalizedName("grass").setStepSound(Block.soundGrassFootstep).setCreativeTab(CreativeTabs.tabDecorations).func_111022_d("grass");
		GameRegistry.registerBlock(randomDropBlock, "RandomDropBlock");
		LanguageRegistry.addName(randomDropBlock, "Random Drops Block");
		
	}
	
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {
		
		// Random drop block recipe
		GameRegistry.addRecipe(new ItemStack(randomDropBlock), "aa", "bb", 'a', new ItemStack(Item.dyePowder, 1, 0), 'b', new ItemStack(Item.dyePowder, 1, 15));
		

		// Register my custom event handler
		TutorialEventHandler tutorialEventHandler = new TutorialEventHandler();
		MinecraftForge.EVENT_BUS.register(tutorialEventHandler);
	}
	
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
	}
	
	
	
	

	
	

}