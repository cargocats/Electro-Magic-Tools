package tombenpotter.emt.common.tile.solars.earth;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityDoubleEarthSolar extends TileEntityEarthSolar {

    public TileEntityDoubleEarthSolar() {
        output = ConfigHandler.doubleCompressedSolarOutput;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars2, 1, 3);
    }
}
