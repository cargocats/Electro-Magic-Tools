package tombenpotter.emt.common.tile.solar.compressed;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.tile.solar.TileEntitySolarBase;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityTripleCompressedSolar extends TileEntitySolarBase {

	public TileEntityTripleCompressedSolar() {
		output = EMTConfigHandler.tripleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 2);
	}
}
