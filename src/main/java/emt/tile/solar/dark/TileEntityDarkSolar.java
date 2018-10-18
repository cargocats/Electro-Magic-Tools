package emt.tile.solar.dark;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityDarkSolar extends TileEntitySolarBase {

	public TileEntityDarkSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 10000, 3);
		output = EMTConfigHandler.compressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	};

	@Override
	public void createEnergy() {
	    if ((this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) && (!this.worldObj.isRaining()) && (!this.worldObj.isThundering())) {
	    	isActive=true;
	    	if (side) {
	    		this.generating=output*calc_multi();
	    		this.energySource.addEnergy(this.output*calc_multi());
	    	}
	    }else {
	    	isActive = false;
	    	this.generating=0;
	    }
	}

	@Override
	public float calc_multi() {
		if (this.worldObj.isDaytime())
			 return 0.75F;
		else return 2F;
	}
	
	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 6);
	}
	
	@Override
	public String getInventoryName() {
		return "Compressed Perditio Solar";
	}
}
