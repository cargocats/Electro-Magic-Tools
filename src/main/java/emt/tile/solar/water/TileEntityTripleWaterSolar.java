package emt.tile.solar.water;

import emt.init.EMTBlocks;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleWaterSolar extends TileEntityWaterSolar {

	public TileEntityTripleWaterSolar() {
		super();
		output = EMTConfigHandler.tripleCompressedSolarOutput;
		energySource = new BasicSource(this, 40000, 5);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 5);
	}
}
