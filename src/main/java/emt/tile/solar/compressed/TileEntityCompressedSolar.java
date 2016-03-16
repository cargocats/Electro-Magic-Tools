package emt.tile.solar.compressed;

import emt.init.BlockRegistry;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityCompressedSolar extends TileEntitySolarBase {

	public TileEntityCompressedSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 0);
	}
}
