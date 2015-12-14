package tombenpotter.emt.common.tile.solar.air;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityTripleAirSolar extends TileEntityAirSolar {

	public TileEntityTripleAirSolar() {
		output = EMTConfigHandler.tripleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars2, 1, 1);
	}
}
