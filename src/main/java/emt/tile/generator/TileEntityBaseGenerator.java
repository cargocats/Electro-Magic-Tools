package emt.tile.generator;

import java.util.Random;

import emt.tile.TileEntityEMT;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;

public abstract class TileEntityBaseGenerator extends TileEntityEMT {

	public BasicSource energySource = new BasicSource(this, 1000000000, 3);
	public Aspect aspect;
	public double output;
	public int tick = 0;
	public boolean isActive = false;
	Random rnd = new Random();

	public TileEntityBaseGenerator() {
		output = 2000;
	}

	@Override
	public void updateEntity() {
		if (tick > 0)
			tick--;

		if (tick == 0) {
			energySource.updateEntity();
			isActive = false;
			createEnergy();
			tick = 20;
		}
	}

	public void createEnergy() {
		int count = 0;
		for (int x = this.xCoord - 4; x < this.xCoord + 4; x++) {
			for (int y = this.yCoord - 4; y < this.yCoord + 4; y++) {
				for (int z = this.zCoord - 4; z < this.zCoord + 4; z++) {
					TileEntity tile = this.worldObj.getTileEntity(x, y, z);
					if (tile != null && tile instanceof IAspectSource) {
						IAspectSource as = (IAspectSource) tile;
						if (as.doesContainerContainAmount(aspect, 1)) {
							if (as.takeFromContainer(aspect, 1)) {
								isActive = true;
								PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(xCoord, yCoord, zCoord, (byte) (xCoord - x), (byte) (yCoord - y), (byte) (zCoord - z), aspect.getColor()), new cpw.mods.fml.common.network.NetworkRegistry.TargetPoint(this.getWorldObj().provider.dimensionId, xCoord, yCoord, zCoord, 32.0D));
								energySource.addEnergy(output / 30D);
							}
						}
					}
				}
			}
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
}
