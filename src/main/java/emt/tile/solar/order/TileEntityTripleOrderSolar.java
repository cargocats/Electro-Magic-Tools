package emt.tile.solar.order;

import emt.init.BlockRegistry;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleOrderSolar extends TileEntityOrderSolar {

	public TileEntityTripleOrderSolar() {
		output = EMTConfigHandler.tripleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 11);
	}
}
