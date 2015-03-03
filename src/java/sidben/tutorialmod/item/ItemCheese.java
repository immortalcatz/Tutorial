package sidben.tutorialmod.item;

import java.util.*;
import org.apache.commons.lang3.ArrayUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sidben.tutorialmod.helper.LogHelper;
import sidben.tutorialmod.reference.Reference;


/*
 * Cheese icon from http://www.minecraftforum.net/forums/minecraft-discussion/suggestions/77112-cheese
 * Used just to try out custom textures. All credits goes to the icon creator.
 * "Magic" effect based on golden apple.
 */

public class ItemCheese extends ItemBasicFood
{

    public ItemCheese() {
        super(3, 0.8F, false);
        this.setHasSubtypes(true);
        this.setMaxStackSize(16);
        this.setAlwaysEdible();
    }


    // -----------------------------------------------------
    // Allows sub-items based on metadata (0 = normal, 1 = magic)
    // -----------------------------------------------------

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        final int i = stack.getMetadata();
        final String name = "item." + Reference.ModID.toLowerCase() + ":";

        switch (i) {
            case 1:
                return name + "cheese_magic";
            default:
                return name + "cheese";
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }



    // -----------------------------------------------------
    // Make it shiny if it's "magic" (metadata 1)
    // -----------------------------------------------------

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return stack.getMetadata() > 0;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getMetadata() == 0 ? EnumRarity.COMMON : EnumRarity.EPIC;
    }



    // -----------------------------------------------------
    // Adds/removes special effects when eaten
    // -----------------------------------------------------

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        LogHelper.info("Eating cheese...");

        if (stack.getMetadata() == 0 && !worldIn.isRemote) {
            this.cureBadPotionEffects(player, 1);
        } else if (stack.getMetadata() == 1 && !worldIn.isRemote) {
            this.cureBadPotionEffects(player, 3);
            player.addPotionEffect(new PotionEffect(Potion.healthBoost.id, 1200, 0));   // gives health boost for 1 minute
        } else {
            super.onFoodEaten(stack, worldIn, player);
        }
    }


    /*
     * Removes up to [maxAmount] of bad status effects from the player.
     * Based on [EntityLivingBase.curePotionEffects].
     */
    @SuppressWarnings("rawtypes")
    private void cureBadPotionEffects(EntityPlayer player, int maxAmount)
    {
        int currentEffectsAmount = player.getActivePotionEffects().size();
        LogHelper.info("    This player has [" +currentEffectsAmount+ "] potion effects.");
        
        
        // Potion effects that can be cured
        int[] validEffects = {                      
                Potion.blindness.id,
                Potion.confusion.id,
                Potion.digSlowdown.id,
                Potion.hunger.id,
                Potion.moveSlowdown.id,
                Potion.poison.id,
                Potion.weakness.id,
                Potion.wither.id
                };
        
        if (currentEffectsAmount > 0)
        {
            // Get the current effects
            ArrayList<Integer> currentEffects = new ArrayList<Integer> ();
            final Iterator iterator = player.getActivePotionEffects().iterator();
    
            while (iterator.hasNext()) {
                final PotionEffect effect = (PotionEffect)iterator.next();
                int potionID = effect.getPotionID();
                
                if (ArrayUtils.contains(validEffects, potionID)) {
                    // Only adds bad potions to the target list
                    currentEffects.add(potionID);
                }
            }
            
            LogHelper.info("    This player has [" +currentEffects.size()+ "] BAD potion effects.");

            if (currentEffects.size() > 0) Collections.shuffle(currentEffects);     // makes the item removes random effects

            
            // Removes some of the effects
            int targetID = 0;
            
            if (currentEffects.size() > 0) {
                for(int i = 0; i < currentEffects.size(); i++) {
                    if (i >= maxAmount && maxAmount > -1) break;

                    targetID = currentEffects.get(i);
                    LogHelper.info("    Cheese is removing potion effect ID [" + targetID + "]");
                    player.removePotionEffect(targetID);
                }
            }
            

        }
        
    }



}
