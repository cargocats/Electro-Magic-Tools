package tombenpotter.emt.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.util.TextHelper;

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
	
    public String getItemStackDisplayName(ItemStack stack)
    {
        return (TextHelper.GREEN + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}
