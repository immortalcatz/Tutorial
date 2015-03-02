package sidben.tutorialmod.handler;

import java.io.File;
import sidben.tutorialmod.helper.LogHelper;
import sidben.tutorialmod.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class ConfigurationHandler {

	
	
	public static Configuration config;

	public static int minDrops = 1;
	public static int maxDrops = 7;
	public static boolean test = true;

	
	
	
	public static void init(File configFile) {

		// Create configuration object from config file
		if (config == null) {
			config = new Configuration(configFile);
			loadConfig();
		}

	}
	
	
	
	private static void loadConfig() 
	{
		String randomDropsCategory = "random_drops";

		// Load properties
		minDrops = config.get(randomDropsCategory, "minDrops", 1, "Minimum ammount of item drops").getInt(1);
		maxDrops = config.get(randomDropsCategory, "maxDrops", 7, "Max ammount of item drops").getInt(7);
		test = config.getBoolean("test", Configuration.CATEGORY_GENERAL, true, "Test value");

		// saving the configuration to its file
    	if (config.hasChanged()) {
            config.save();
    	}
	}
	

	
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.ModID))
		{
			// Resync configs
			loadConfig();
		}
	}
	
}
