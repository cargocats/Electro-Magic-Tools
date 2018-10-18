package emt.tile.solar.dark;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleDarkSolar extends TileEntityDarkSolar {

	public TileEntityTripleDarkSolar() {
		super();
		output = EMTConfigHandler.tripleCompressedSolarOutput;
		energySource = new DefinitelyNotAIC2Source(this, 1000000, 5);
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 8);
	}
	

	@Override
	public String getInventoryName() {
		return "Triple Compressed Perditio Solar";
	}
}
