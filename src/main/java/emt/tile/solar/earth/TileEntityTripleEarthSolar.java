package emt.tile.solar.earth;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleEarthSolar extends TileEntityEarthSolar {

	public TileEntityTripleEarthSolar() {
		super();
		output = EMTConfigHandler.tripleCompressedSolarOutput;
		energySource = new DefinitelyNotAIC2Source(this, 1000000, 5);
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars2, 1, 4);
	}
	

	@Override
	public String getInventoryName() {
		return "Triple Compressed Terra Solar";
	}
}
