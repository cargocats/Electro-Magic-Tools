package tombenpotter.emt.common.tile.generators;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.events.EssentiaHandler;
import tombenpotter.emt.common.tile.TileEntityEMT;
import tombenpotter.emt.common.util.ConfigHandler;

public abstract class TileEntityBaseGenerator extends TileEntityEMT {

    public BasicSource energySource = new BasicSource(this, 1000000000, 3);
    public Aspect aspect;
    public int output;
    public int tick = 0;

    public TileEntityBaseGenerator() {
        output = 20;
    }

    @Override
    public void updateEntity() {
    	if(tick > 0)
    		tick--;
    	
    	if(tick == 0){
    		energySource.updateEntity();
        	createEnergy();
        	tick = 20;
    	}
    }

    public void createEnergy() {
        if (!this.worldObj.isRemote && EssentiaHandler.drainEssentia(this, aspect, ForgeDirection.UNKNOWN, 8)) {
            energySource.addEnergy(output * 20);
        }
    }

    @Override
    public void onChunkUnload() {
        energySource.onChunkUnload();
    }

    @Override
    public void invalidate() {
        energySource.invalidate();
        super.invalidate();
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        energySource.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        energySource.writeToNBT(tag);
    }
    
    public int getOutput(){
    	return output;
    }
}
