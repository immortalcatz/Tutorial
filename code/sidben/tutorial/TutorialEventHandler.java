package sidben.tutorial;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;


public class TutorialEventHandler {

	
	@ForgeSubscribe
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		
		// Check if it is a player
		if (event.entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.entity;

			
			// Check if it is on water
			if (player.isInWater()) {
				
				// Ask what the player is wearing (in a non-creepy way...)
				// 4 = Head, 3 = Chest, 2 = Legs, 1 = Foot 				
				ItemStack armorHead = player.getCurrentItemOrArmor(4);
				
				// Check if have the right helmet (MUST CHECK FOR NULL OR WILL CRASH!!!)
				if (armorHead != null && armorHead.itemID == Block.pumpkin.blockID) {
					
					// Makes air 100%  all the time
					player.setAir(300);
					
				}
				
			}
			
		}
		
	}

	
}
