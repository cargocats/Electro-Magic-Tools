package tombenpotter.emt.common.tile.solar.earth;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.tile.solar.TileEntitySolarBase;
import tombenpotter.emt.common.util.EMTConfigHandler;

public class TileEntityEarthSolar extends TileEntitySolarBase {

	public TileEntityEarthSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public void createEnergy() {
		if (theSunIsVisible && this.yCoord <= 10) {
			energySource.addEnergy(output * 2.5);
		}
		else if (theSunIsVisible) {
			energySource.addEnergy(output);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars2, 1, 2);
	}
}
