package tombenpotter.emt.common.tile.solars.order;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityDoubleOrderSolar extends TileEntityOrderSolar {

    public TileEntityDoubleOrderSolar() {
        output = ConfigHandler.doubleCompressedSolarOutput;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars, 1, 10);
    }
}
