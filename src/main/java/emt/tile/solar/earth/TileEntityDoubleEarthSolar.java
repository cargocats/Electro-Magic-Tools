package emt.tile.solar.earth;

import emt.init.EMTBlocks;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleEarthSolar extends TileEntityEarthSolar {

	public TileEntityDoubleEarthSolar() {
		super();
		energySource = new BasicSource(this, 10000, 4);
		output = EMTConfigHandler.doubleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars2, 1, 3);
	}
}
