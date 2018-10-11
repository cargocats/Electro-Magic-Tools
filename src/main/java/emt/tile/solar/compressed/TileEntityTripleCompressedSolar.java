package emt.tile.solar.compressed;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityTripleCompressedSolar extends TileEntitySolarBase {

	public TileEntityTripleCompressedSolar() {
		super();
		output = EMTConfigHandler.tripleCompressedSolarOutput;
		energySource = new DefinitelyNotAIC2Source(this, 1000000, 5);
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 2);
	}

	@Override
	public float calc_multi() {
		return 1F;
	}
	
	@Override
	public String getInventoryName() {
		return "Triple Compressed Solar";
	}
}
