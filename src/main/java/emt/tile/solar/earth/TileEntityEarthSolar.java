package emt.tile.solar.earth;

import emt.init.EMTBlocks;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityEarthSolar extends TileEntitySolarBase {

	public TileEntityEarthSolar() {
		super();
		energySource = new BasicSource(this, 10000, 3);
		output = EMTConfigHandler.compressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public float calc_multi() {
			if (this.yCoord<5)
	  			return 3F;
	  		else if (this.yCoord>220)
	  			return 1F;
	  		else
	  			return (float) ((-2D)/215D*(double)this.yCoord + 131D/43D);
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars2, 1, 2);
	}
	
	@Override
	public String getInventoryName() {
		return "Compressed Terra Solar";
	}
}
