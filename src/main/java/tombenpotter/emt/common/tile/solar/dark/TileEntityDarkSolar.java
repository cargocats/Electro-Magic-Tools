package tombenpotter.emt.common.tile.solar.dark;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.tile.solar.TileEntitySolarBase;
import tombenpotter.emt.common.util.EMTConfigHandler;

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
