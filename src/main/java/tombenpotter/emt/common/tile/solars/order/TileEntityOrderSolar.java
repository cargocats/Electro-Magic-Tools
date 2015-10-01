package tombenpotter.emt.common.tile.solars.order;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.blocks.BlockRegistry;
import tombenpotter.emt.common.tile.solars.TileEntitySolarBase;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityOrderSolar extends TileEntitySolarBase {

    public TileEntityOrderSolar() {
        output = ConfigHandler.compressedSolarOutput;
    }

    @Override
    public void createEnergy() {
        if (theSunIsVisible) {
            energySource.addEnergy(output * 3);
        }
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars, 1, 9);
    }
}
