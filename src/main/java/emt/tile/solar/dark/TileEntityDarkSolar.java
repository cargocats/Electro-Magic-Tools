package emt.tile.solar.dark;

import emt.init.BlockRegistry;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDarkSolar extends TileEntitySolarBase {

	public TileEntityDarkSolar() {
		output = EMTConfigHandler.compressedSolarOutput;
	}

	@Override
	public void createEnergy() {
		if (worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord) && !worldObj.isDaytime() && !worldObj.isRaining() && !worldObj.isThundering()) {
			energySource.addEnergy(output);
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 6);
	}
}
