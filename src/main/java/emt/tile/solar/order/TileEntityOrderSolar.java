package emt.tile.solar.order;

import emt.init.EMTBlocks;
import emt.tile.DefinitelyNotAIC2Source;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityOrderSolar extends TileEntitySolarBase {

	public TileEntityOrderSolar() {
		super();
		energySource = new DefinitelyNotAIC2Source(this, 10000, 3);
		output = EMTConfigHandler.compressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public void createEnergy() {
	    if ((this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) && (!this.worldObj.isRaining()) && (!this.worldObj.isThundering())) {
	    	isActive = true;
	    	if (side) {
	    		this.energySource.addEnergy(this.output*calc_multi());
	    		this.generating=output*calc_multi();
	    	}
	    }else {
	    	isActive = false;
	    	this.generating=0;
	    }
	}
	
	@Override
	public float calc_multi() {
		if (this.worldObj.isDaytime())
			 return 2F;
		else return 0.75F;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 9);
	}
	
	@Override
	public String getInventoryName() {
		return "Compressed Ordo Solar";
	}
}
