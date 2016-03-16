package emt.tile.solar.order;

import emt.init.BlockRegistry;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityOrderSolar extends TileEntitySolarBase {

	public TileEntityOrderSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public void createEnergy() {
		if (theSunIsVisible) {
			energySource.addEnergy(output * 3);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 9);
	}
}
