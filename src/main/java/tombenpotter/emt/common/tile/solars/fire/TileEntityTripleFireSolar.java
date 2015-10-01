package tombenpotter.emt.common.tile.solars.fire;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.blocks.BlockRegistry;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityTripleFireSolar extends TileEntityFireSolar {

    public TileEntityTripleFireSolar() {
        output = ConfigHandler.tripleCompressedSolarOutput;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars, 1, 14);
    }
}
