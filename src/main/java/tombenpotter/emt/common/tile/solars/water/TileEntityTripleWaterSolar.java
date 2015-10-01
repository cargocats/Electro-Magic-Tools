package tombenpotter.emt.common.tile.solars.water;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.blocks.BlockRegistry;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityTripleWaterSolar extends TileEntityWaterSolar {

    public TileEntityTripleWaterSolar() {
        output = ConfigHandler.tripleCompressedSolarOutput;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars, 1, 5);
    }
}
