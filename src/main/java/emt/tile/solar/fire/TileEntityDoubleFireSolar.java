package emt.tile.solar.fire;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleFireSolar extends TileEntityFireSolar {

	public TileEntityDoubleFireSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 1000000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 13);
	}
	

	@Override
	public String getInventoryName() {
		return "Double Compressed Ignis Solar";
	}
}
