package tombenpotter.emt.common.tile.solars.dark;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.tile.solars.TileEntitySolarBase;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityDarkSolar extends TileEntitySolarBase {

	public TileEntityDarkSolar() {
		output = ConfigHandler.compressedSolarOutput;
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
