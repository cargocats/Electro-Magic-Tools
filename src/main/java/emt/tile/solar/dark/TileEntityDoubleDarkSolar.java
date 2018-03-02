package emt.tile.solar.dark;

import emt.init.EMTBlocks;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleDarkSolar extends TileEntityDarkSolar {

	public TileEntityDoubleDarkSolar() {
		super();
		energySource = new BasicSource(this, 100000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 7);
	}
	

	@Override
	public String getInventoryName() {
		return "Double Compressed Perditio Solar";
	}
}
