package emt.tile.solar.air;

import emt.init.EMTBlocks;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleAirSolar extends TileEntityAirSolar {

	public TileEntityDoubleAirSolar() {
		super();
		energySource = new BasicSource(this, 100000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars2, 1, 0);
	}
	
	@Override
	public String getInventoryName() {
		return "Double Compressed Aer Solar";
	}
}
