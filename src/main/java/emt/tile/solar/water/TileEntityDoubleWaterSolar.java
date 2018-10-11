package emt.tile.solar.water;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleWaterSolar extends TileEntityWaterSolar {

	public TileEntityDoubleWaterSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 100000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 4);
	}
	

	@Override
	public String getInventoryName() {
		return "Double Compressed Aqua Solar";
	}
}
