package emt.tile.solar.order;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleOrderSolar extends TileEntityOrderSolar {

	public TileEntityDoubleOrderSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 100000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 10);
	}

	@Override
	public String getInventoryName() {
		return "Double Compressed Ordo Solar";
	}
}
