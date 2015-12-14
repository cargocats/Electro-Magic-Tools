package tombenpotter.emt.common.item.armor.boots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class ItemNanoBootsTraveller extends ItemElectricBootsTraveller {

	public ItemNanoBootsTraveller(int par3, int par4) {
		super(par3, par4);
		maxCharge = 1000000;
		speedBonus = (float) EMTConfigHandler.nanoBootsSpeed;
		jumpBonus = (float) EMTConfigHandler.nanoBootsJump;
		visDiscount = 4;
		transferLimit = 1000;
		energyPerDamage = 500;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(ModInformation.texturePath + ":armor/boots_nano");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return ModInformation.texturePath + ":textures/models/nanobootstravel.png";
	}
}
