package tombenpotter.emt.common.tile.solars.dark;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityDoubleDarkSolar extends TileEntityDarkSolar {

	public TileEntityDoubleDarkSolar() {
		output = ConfigHandler.doubleCompressedSolarOutput;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(BlockRegistry.emtSolars, 1, 7);
	}
}
