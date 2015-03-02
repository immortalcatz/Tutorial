package sidben.tutorialmod.creativetab;


import sidben.tutorialmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class CreativeTabTutorial
{

	 public static final CreativeTabs TUTORIAL_TAB = new CreativeTabs(Reference.ModID.toLowerCase()) 
	 {

		@Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
	        return Items.feather;
        }
		 
	 };
	 
	 
}
