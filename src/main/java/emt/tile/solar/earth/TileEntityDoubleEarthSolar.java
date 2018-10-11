package emt.tile.solar.earth;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleEarthSolar extends TileEntityEarthSolar {

	public TileEntityDoubleEarthSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 100000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars2, 1, 3);
	}
	

	@Override
	public String getInventoryName() {
		return "Double Compressed Terra Solar";
	}
}
