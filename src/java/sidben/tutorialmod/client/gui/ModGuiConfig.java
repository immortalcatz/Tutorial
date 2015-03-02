package sidben.tutorialmod.client.gui;

import sidben.tutorialmod.handler.ConfigurationHandler;
import sidben.tutorialmod.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;


public class ModGuiConfig extends GuiConfig {


	public ModGuiConfig(GuiScreen guiScreen)
	{
		super(guiScreen,
				new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Reference.ModID,
				false,
				false,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
	}
	
}
