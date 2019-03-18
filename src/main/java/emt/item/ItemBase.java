package emt.item;

import emt.EMT;
import emt.util.EMTTextHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBase extends Item {

    public ItemBase(String unlocName, String textureName) {
        super();
        setUnlocalizedName(EMT.MOD_ID + unlocName);
        setTextureName(EMT.TEXTURE_PATH + ":" + textureName);
        setCreativeTab(EMT.TAB);
    }

    public ItemBase(String unlocName) {
        super();
        setUnlocalizedName(EMT.MOD_ID + ".item." + unlocName);
        setCreativeTab(EMT.TAB);
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return (EMTTextHelper.GREEN + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}
