package emt.item.armor.goggles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.EMT;
import emt.EMT;
import emt.util.EMTConfigHandler;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemNanoGoggles extends ItemElectricGoggles {

	public ItemNanoGoggles(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(EMT.TAB);
		this.setMaxDamage(27);
		this.setMaxStackSize(1);
		visDiscount = 6;
		tier = 3;
		maxCharge = 1000000;
		energyPerDamage = 5000;
		transferLimit = 1600;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(EMT.TEXTURE_PATH + ":armor/goggles_nano");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return EMT.TEXTURE_PATH + ":textures/models/thaumicnanohelmet.png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (EMTConfigHandler.nightVisionOff == false) {
			if (ElectricItem.manager.canUse(itemStack, 1 / 1000)) {

				int x = MathHelper.floor_double(player.posX);
				int z = MathHelper.floor_double(player.posZ);
				int y = MathHelper.floor_double(player.posY);

				int lightlevel = player.worldObj.getBlockLightValue(x, y, z);
				if (lightlevel >= 0)
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, -3));
				ElectricItem.manager.use(itemStack, 1 / 1000, player);
			}
			else {
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 300, 0, true));
			}
		}
	}
}
