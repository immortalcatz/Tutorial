package sidben.tutorialmod.item;

import java.util.List;
import sidben.tutorialmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/*
 * Cheese icon from http://www.minecraftforum.net/forums/minecraft-discussion/suggestions/77112-cheese 
 * Used just to try out custom textures. All credits goes to the icon creator.
 * 
 * "Magic" effect based on golden apple.
 */

public class ItemCheese extends ItemBasic
{

    public ItemCheese() {
        super();
        this.setHasSubtypes(true);
        this.setMaxStackSize(16);
    }


    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        final int i = stack.getMetadata();
        String name = "item." + Reference.ModID.toLowerCase() + ":";
        
        switch (i) {
            case 1:
                return name + "cheese_magic";
            default:
                return name + "cheese";
        }
    }
    
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return stack.getMetadata() > 0;
    }

    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }

}
