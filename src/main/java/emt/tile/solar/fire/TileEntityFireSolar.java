package emt.tile.solar.fire;

import emt.init.EMTBlocks;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityFireSolar extends TileEntitySolarBase {

	public TileEntityFireSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public void checkConditions() {
		if (!initialized && worldObj != null) {
			canRain = worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0;
			initialized = true;
		}

		if (worldObj.provider.dimensionId == -1) {
			energySource.addEnergy(output * 2);
		}
		else {
			if (tick-- == 0) {
				updateSunState();
				tick = 64;
			}
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 12);
	}
}
