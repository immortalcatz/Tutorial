package sidben.tutorialmod.item;

import sidben.tutorialmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public abstract class ItemBasic extends Item 
{

	
	public ItemBasic() {
		super();
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	 @Override
	 public String getUnlocalizedName() {
		 return String.format("item.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	 }
	 
	 @Override
	 public String getUnlocalizedName(ItemStack itemStack) {
		 return String.format("item.%s%s", Reference.ModID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	 }
	 

	 
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

}
