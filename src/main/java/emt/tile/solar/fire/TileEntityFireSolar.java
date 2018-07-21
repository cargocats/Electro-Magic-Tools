package emt.tile.solar.fire;

import cpw.mods.fml.common.FMLCommonHandler;
import emt.init.EMTBlocks;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.VisNetHandler;

public class TileEntityFireSolar extends TileEntitySolarBase {

	public TileEntityFireSolar() {
		super();
		energySource = new BasicSource(this, 10000, 3);
		output = EMTConfigHandler.compressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public void checkConditions() {
		boolean ignis = ((this.worldObj.provider.dimensionId == (-1)) || (theSunIsVisible));
		if(ignis) {
			this.isActive=true;
	    	if (side) {
	    		this.generating=output*calc_multi();
	    		this.energySource.addEnergy(this.output*calc_multi());
	    	}
		}
		else {
			isActive = false;
			this.generating=0;
		}
		/*if (!ignis && VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.FIRE, 10)>=10) {
			if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
				energySource.addEnergy(15F);
				this.generating=15D;
			}
		}*/
		if (tick-- == 0) {
			updateSunState();
			tick = 64;
		}
	}

	@Override
	public float calc_multi() {
		if (VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.FIRE, 10)>=10)
			return 3F;
		else if (this.worldObj.provider.dimensionId == (-1))
			return 2F;
		else 
			return 1F;
	}
	
	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 12);
	}

	@Override
	public String getInventoryName() {
		return "Compressed Ignis Solar";
	}
}
