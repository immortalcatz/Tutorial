package sidben.tutorialmod.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class TutorialEventHandler {
	
	
	private static final int HELMET_SLOT = 3;
	
	
	
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {

		// Check if it is a player
		if (event.entity instanceof EntityPlayer) {
		
			EntityPlayer player = (EntityPlayer) event.entity;

			// Check if it is on water
			if (player.isInWater()) {
				
				// Ask what the player is wearing (in a non-creepy way...)
				ItemStack armorHead = player.getCurrentArmor(HELMET_SLOT);
				
				// Check if have the right helmet (MUST CHECK FOR NULL OR WILL CRASH!!!)
				if (armorHead != null && armorHead.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
					
					// Makes air 100%  all the time
					player.setAir(300);
					
				}
				
			}
			
		}
	
	}	
	
	
}