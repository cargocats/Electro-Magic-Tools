package tombenpotter.emt.common.tile.solars.air;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.blocks.BlockRegistry;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityTripleAirSolar extends TileEntityAirSolar {

    public TileEntityTripleAirSolar() {
        output = ConfigHandler.tripleCompressedSolarOutput;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars2, 1, 1);
    }
}
