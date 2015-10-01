package tombenpotter.emt.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.items.ItemRegistry;

public class CreativeTabEMT extends CreativeTabs {

    public CreativeTabEMT(String tabLabel) {
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
