package tombenpotter.emt.common.tile.solar.order;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.tile.solar.TileEntitySolarBase;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityOrderSolar extends TileEntitySolarBase {

	public TileEntityOrderSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public void createEnergy() {
		if (theSunIsVisible) {
			energySource.addEnergy(output * 3);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 9);
	}
}
