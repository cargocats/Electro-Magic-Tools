package emt.tile.solar.order;

import emt.init.EMTBlocks;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleOrderSolar extends TileEntityOrderSolar {

	public TileEntityTripleOrderSolar() {
		super();
		output = EMTConfigHandler.tripleCompressedSolarOutput;
		energySource = new BasicSource(this, 1000000, 5);
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 11);
	}
	

	@Override
	public String getInventoryName() {
		return "Triple Compressed Ordo Solar";
	}
}
