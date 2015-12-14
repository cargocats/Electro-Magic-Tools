package tombenpotter.emt.common.tile.solar.air;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.tile.solar.TileEntitySolarBase;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityAirSolar extends TileEntitySolarBase {

	public TileEntityAirSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public void createEnergy() {
		if (theSunIsVisible && this.yCoord >= 160) {
			energySource.addEnergy(output * 2.5);
		}
		else if (theSunIsVisible) {
			energySource.addEnergy(output);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 15);
	}
}
