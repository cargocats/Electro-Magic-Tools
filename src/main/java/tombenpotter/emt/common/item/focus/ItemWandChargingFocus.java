package tombenpotter.emt.common.item.focus;

import ic2.api.item.ElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import tombenpotter.emt.common.item.focus.ItemBaseFocus;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class ItemWandChargingFocus extends ItemBaseFocus {

	AspectList visCost = new AspectList().add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10);

	public ItemWandChargingFocus() {
		super(".focus.charge.wand", "focus_chargeWand");
	}

	@Override
	public int getFocusColor(ItemStack stack) {
		return 0xFFFF450;
	}

	@Override
	public String getSortingHelper(ItemStack itemstack) {
		return "ELECTRICCHARGING";
	}

	@Override
	public boolean isVisCostPerTick(ItemStack focusstack) {
		return true;
	}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		return visCost;
	}

	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int integer) {

		if (!player.worldObj.isRemote) {
			ItemWandCasting wandItem = (ItemWandCasting) itemstack.getItem();

			ItemStack armor = player.inventory.armorInventory[1];
			if (armor != null) {
				if ((ElectricItem.manager.use(armor, EMTConfigHandler.wandChargeFocusCost / 4, player) && (ElectricItem.manager.use(armor, EMTConfigHandler.wandChargeFocusCost / 4, player)) && (ElectricItem.manager.use(armor, EMTConfigHandler.wandChargeFocusCost / 4, player)) && (ElectricItem.manager.use(armor, EMTConfigHandler.wandChargeFocusCost / 4, player)))) {
					wandItem.addVis(itemstack, Aspect.ORDER, 1, true);
					wandItem.addVis(itemstack, Aspect.FIRE, 1, true);
					wandItem.addVis(itemstack, Aspect.ENTROPY, 1, true);
					wandItem.addVis(itemstack, Aspect.WATER, 1, true);
					wandItem.addVis(itemstack, Aspect.EARTH, 1, true);
					wandItem.addVis(itemstack, Aspect.AIR, 1, true);
				}
			}
		}
	}
}
