package tombenpotter.emt.common.tile.solar.water;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityDoubleWaterSolar extends TileEntityWaterSolar {

	public TileEntityDoubleWaterSolar() {
		output = EMTConfigHandler.doubleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 4);
	}
}
