package tombenpotter.emt.common.util;

import tombenpotter.emt.common.init.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EMTCreativeTab extends CreativeTabs {

	public EMTCreativeTab(String tabLabel) {
		super(tabLabel);
		setBackgroundImageName("emt.png");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ItemRegistry.rockbreakerDrill);
	}

	@Override
	public Item getTabIconItem() {
		return new Item();
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
