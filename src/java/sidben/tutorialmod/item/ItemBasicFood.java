package sidben.tutorialmod.item;

import sidben.tutorialmod.creativetab.CreativeTabTutorial;
import sidben.tutorialmod.reference.Reference;
import net.minecraft.item.*;


public abstract class ItemBasicFood extends ItemFood 
{

	
	public ItemBasicFood(int foodAmount, float saturation, boolean isWolfFood) {
		super(foodAmount, saturation, isWolfFood);
		this.setCreativeTab(CreativeTabTutorial.TUTORIAL_TAB);
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
