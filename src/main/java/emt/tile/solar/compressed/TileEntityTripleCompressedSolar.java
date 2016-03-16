package emt.tile.solar.compressed;

import emt.init.BlockRegistry;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleCompressedSolar extends TileEntitySolarBase {

	public TileEntityTripleCompressedSolar() {
		output = EMTConfigHandler.tripleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 2);
	}
}
