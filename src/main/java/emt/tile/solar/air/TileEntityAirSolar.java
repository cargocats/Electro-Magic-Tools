package emt.tile.solar.air;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityAirSolar extends TileEntitySolarBase {

	public TileEntityAirSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 10000, 3);
		output = EMTConfigHandler.compressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	};
	
	@Override
  	public float calc_multi() {
  		if (this.yCoord<5)
  			return 1F;
  		else if (this.yCoord>220)
  			return 3F;
  		else
  			return (float) ((41D/43D) + ((2D/215D) * (double) this.yCoord));
  		
  	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 15);
	}

	@Override
	public String getInventoryName() {
		return "Compressed Aer Solar";
	}
}
