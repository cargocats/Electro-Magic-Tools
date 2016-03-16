package emt.tile.solar.air;

import emt.init.BlockRegistry;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleAirSolar extends TileEntityAirSolar {

	public TileEntityTripleAirSolar() {
		output = EMTConfigHandler.tripleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars2, 1, 1);
	}
}
