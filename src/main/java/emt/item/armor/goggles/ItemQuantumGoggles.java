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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class ItemQuantumGoggles extends ItemNanoGoggles {

	private static final Map<Integer, Integer> potionCost = new HashMap();

	public ItemQuantumGoggles(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(EMT.TAB);
		this.setMaxDamage(27);
		this.setMaxStackSize(1);
		maxCharge = 10000000;
		tier = 4;
		visDiscount = 8;
		transferLimit = 12000;
		energyPerDamage = 20000;

		potionCost.put(Integer.valueOf(Potion.poison.id), Integer.valueOf(10000));
		potionCost.put(Integer.valueOf(Potion.wither.id), Integer.valueOf(15000));
		potionCost.put(Integer.valueOf(Potion.confusion.id), Integer.valueOf(5000));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(EMT.TEXTURE_PATH + ":armor/goggles_quantum");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return EMT.TEXTURE_PATH + ":textures/models/thaumicquantumhelmet.png";
	}

	@Override
	public double getDamageAbsorptionRatio() {
		return 1D;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int refill = player.getAir();
		if (ElectricItem.manager.canUse(itemStack, 1000) && refill < 100) {
			player.setAir(refill + 200);
			ElectricItem.manager.use(itemStack, 1000, null);
		}

		Iterator i$ = (new LinkedList(player.getActivePotionEffects())).iterator();
		do {
			if (!i$.hasNext()) {
				break;
			}
			{
				PotionEffect effect = (PotionEffect) i$.next();
				int id = effect.getPotionID();
				Integer cost = (Integer) potionCost.get(Integer.valueOf(id));
				if (cost != null) {
					cost = Integer.valueOf(cost.intValue() * (effect.getAmplifier() + 1));
					if (ElectricItem.manager.canUse(itemStack, cost.intValue())) {
						ElectricItem.manager.use(itemStack, cost.intValue(), null);
						ItemStack milk = (new ItemStack(Items.milk_bucket));
						player.curePotionEffects(milk);
					}
				}
			}
		}
		while (true);

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
