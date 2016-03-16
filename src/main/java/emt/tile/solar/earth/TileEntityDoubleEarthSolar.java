package emt.tile.solar.earth;

import emt.init.BlockRegistry;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDoubleEarthSolar extends TileEntityEarthSolar {

	public TileEntityDoubleEarthSolar() {
		output = EMTConfigHandler.doubleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars2, 1, 3);
	}
}
