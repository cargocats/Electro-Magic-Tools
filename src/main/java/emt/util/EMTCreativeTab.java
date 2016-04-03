package emt.util;

import emt.init.EMTBlocks;
import emt.init.EMTItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EMTCreativeTab extends CreativeTabs {

	public EMTCreativeTab(String tabLabel) {
		super(tabLabel);
		setBackgroundImageName("emt.png");
	}


	@Override
	public Item getTabIconItem() {
		return EMTItems.electricThorHammer;
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

	@Override
	public int getSearchbarWidth() {
		return 60;
	}
}
