package tombenpotter.emt.common.item.tool.chainsaw;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;

public class ItemThaumiumChainsaw extends ItemDiamondChainsaw {
	public static AudioSource audio;

	public ItemThaumiumChainsaw() {
		this.efficiencyOnProperMaterial = 21F;
		this.setCreativeTab(ElectroMagicTools.tabEMT);
		this.setMaxDamage(27);
		this.setMaxStackSize(1);
		maxCharge = 60000;
		cost = 250;
		hitCost = 350;
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		if (entity instanceof EntityLivingBase) {
			if (IC2.platform.isRendering()) {
				if (flag && !dropped) {
					if (audio == null)
						audio = IC2.audioManager.createSource(entity, PositionSpec.Hand, "Tools/Chainsaw/ChainsawIdle.ogg", true, false, IC2.audioManager.getDefaultVolume());
					if (audio != null) {
						audio.updatePosition();
						audio.play();
					}
				}
				else if (!flag && audio != null && ((((EntityPlayer) entity).inventory.getCurrentItem() != null && ((EntityPlayer) entity).inventory.getCurrentItem().getItem() != this) || (((EntityPlayer) entity).inventory.getCurrentItem() == null))) {
					audio.stop();
					audio.remove();
					audio = null;
					IC2.audioManager.playOnce(entity, PositionSpec.Hand, "Tools/Chainsaw/ChainsawStop.ogg", true, IC2.audioManager.getDefaultVolume());
				}
				dropped = false;
			}
		}
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		if (audio != null) {
			audio.stop();
			audio.remove();
			audio = null;
			dropped = true;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(ModInformation.texturePath + ":tools/chainsaw_thaumium");
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int par4, int par5, int par6, EntityLivingBase entityLiving) {
		ElectricItem.manager.use(stack, cost, entityLiving);
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase attacker) {
		if (ElectricItem.manager.use(itemstack, hitCost, attacker)) {
			entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12F);
		}
		return false;
	}

	/* IC2 API METHODS */
	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return 600;
	}
}
