package tombenpotter.emt.common.items;

import net.minecraft.item.Item;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;

public class ItemBase extends Item {

    public ItemBase(String unlocName, String textureName) {
        super();
        setUnlocalizedName(ModInformation.modid + ".item." + unlocName);
        setTextureName(ModInformation.texturePath + ":" + textureName);
        setCreativeTab(ElectroMagicTools.tabEMT);
    }

    public ItemBase(String unlocName) {
        super();
        setUnlocalizedName(ModInformation.modid + ".item." + unlocName);
        setCreativeTab(ElectroMagicTools.tabEMT);
    }
}
