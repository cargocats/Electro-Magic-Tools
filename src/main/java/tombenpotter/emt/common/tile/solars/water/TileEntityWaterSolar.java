package tombenpotter.emt.common.tile.solars.water;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.blocks.BlockRegistry;
import tombenpotter.emt.common.tile.solars.TileEntitySolarBase;
import tombenpotter.emt.common.util.ConfigHandler;

public class TileEntityWaterSolar extends TileEntitySolarBase {

    public TileEntityWaterSolar() {
        output = ConfigHandler.compressedSolarOutput;
    }

    @Override
    public void createEnergy() {
        if (worldObj.isRaining() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord) || worldObj.isThundering() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)) {
            energySource.addEnergy(output);
        }
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(BlockRegistry.emtSolars, 1, 3);
    }
}
