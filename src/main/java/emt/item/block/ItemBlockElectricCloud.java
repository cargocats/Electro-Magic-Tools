package emt.item.block;

import emt.EMT;
import emt.util.EMTTextHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockElectricCloud extends ItemBlock {

    public IIcon icon;

    public ItemBlockElectricCloud(Block block) {
        super(block);
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return icon;
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        icon = ir.registerIcon(EMT.TEXTURE_PATH + ":" + "electric_cloud");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return EMTTextHelper.PURPLE
                + EMTTextHelper.BOLD
                + StatCollector.translateToLocal(getUnlocalizedName() + ".name");
    }
}
