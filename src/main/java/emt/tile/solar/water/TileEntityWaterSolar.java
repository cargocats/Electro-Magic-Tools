package emt.tile.solar.water;

import cpw.mods.fml.common.FMLCommonHandler;
import emt.init.EMTBlocks;
import emt.tile.solar.TileEntitySolarBase;
import emt.util.EMTConfigHandler;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TileEntityWaterSolar extends TileEntitySolarBase {

	public TileEntityWaterSolar() {
		super();
		energySource = new BasicSource(this, 10000, 3);
		output = EMTConfigHandler.compressedSolarOutput;
		this.maxstorage=this.getEUCapacity();
	}

	@Override
	public void createEnergy() {
		if (theSunIsVisible || (this.worldObj.isRaining() && this.worldObj.isDaytime()) || this.worldObj.isThundering()){
			isActive = true;
			if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
				this.generating=output*calc_multi();
				energySource.addEnergy(output*calc_multi());
			}
		}
		else { 
			isActive = false;
			this.generating=0D;
		}
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(EMTBlocks.emtSolars, 1, 3);
	}

	@Override
	public float calc_multi() {
		if (worldObj.isThundering())
			return 6F;
		else if (worldObj.isRaining())
			return 3F;
		else if (!worldObj.isThundering() && !worldObj.isRaining() && worldObj.getBlock(xCoord, yCoord+1, zCoord).equals(Blocks.water))
			return 2F;
		else
			return 1F;
		
	}
	
	@Override
	public String getInventoryName() {
		return "Compressed Aqua Solar";
	}
}
