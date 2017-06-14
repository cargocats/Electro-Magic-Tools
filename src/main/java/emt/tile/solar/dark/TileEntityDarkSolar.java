package emt.tile.solar.dark;

import emt.init.EMTBlocks;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDarkSolar extends TileEntitySolarBase {

	public TileEntityDarkSolar() {
		super();
		energySource = new BasicSource(this, 10000, 3);
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
		return new ItemStack(EMTBlocks.emtSolars, 1, 6);
	}
}
