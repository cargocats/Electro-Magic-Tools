package tombenpotter.emt.common.tile.solar.order;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityDoubleOrderSolar extends TileEntityOrderSolar {

	public TileEntityDoubleOrderSolar() {
		output = EMTConfigHandler.doubleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 10);
	}
}
