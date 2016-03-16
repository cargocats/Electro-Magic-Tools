package emt.item;

import emt.ElectroMagicTools;
import emt.ModInformation;
import emt.util.EMTTextHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBase extends Item {

	public ItemBase(String unlocName, String textureName) {
		super();
		setUnlocalizedName(ModInformation.modid + unlocName);
		setTextureName(ModInformation.texturePath + ":" + textureName);
		setCreativeTab(ElectroMagicTools.tabEMT);
	}

	public ItemBase(String unlocName) {
		super();
		setUnlocalizedName(ModInformation.modid + ".item." + unlocName);
		setCreativeTab(ElectroMagicTools.tabEMT);
	}

	public String getItemStackDisplayName(ItemStack stack) {
		return (EMTTextHelper.GREEN + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
	}
}
