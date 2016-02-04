package tombenpotter.emt.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.util.EMTTextHelper;

public class ItemBlockElectricCloud extends ItemBlock{

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
		icon = ir.registerIcon(ModInformation.texturePath + ":" + "electricCloud");
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return EMTTextHelper.PURPLE + EMTTextHelper.BOLD + StatCollector.translateToLocal(getUnlocalizedName() + ".name");
	}
}
