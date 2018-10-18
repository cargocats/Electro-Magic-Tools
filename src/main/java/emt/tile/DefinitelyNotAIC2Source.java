package emt.tile;

import cpw.mods.fml.common.FMLCommonHandler;
import gregtech.api.enums.GT_Values;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class DefinitelyNotAIC2Source extends TileEntity {
    public final TileEntity parent;
    protected double capacity;
    protected byte tier;
    protected double power;
    protected double energyStored;
    protected boolean addedToEnet;

    public DefinitelyNotAIC2Source(TileEntity parent1, double capacity1, int tier1) {
        double power = (double) GT_Values.V[tier1];
        this.parent = parent1;
        this.capacity = capacity1 < power ? power : capacity1;
        this.tier = (byte) tier1;
        this.power = power;
    }

    public void updateEntity() {
        if (!this.addedToEnet && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {
                this.worldObj = this.parent.getWorldObj();
                this.xCoord = this.parent.xCoord;
                this.yCoord = this.parent.yCoord;
                this.zCoord = this.parent.zCoord;
                this.addedToEnet = true;
            }
    }

    public void invalidate() {
        super.invalidate();
        this.onChunkUnload();
    }

    public void onChunkUnload() {
        if (this.addedToEnet) {
            this.addedToEnet = false;
        }

    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagCompound data = tag.getCompoundTag("EMTGTSource");
        this.energyStored = data.getDouble("energy");
    }

    public void writeToNBT(NBTTagCompound tag) {
        try {
            super.writeToNBT(tag);
        } catch (RuntimeException var3) {
        }

        NBTTagCompound data = new NBTTagCompound();
        data.setDouble("energy", this.energyStored);
        tag.setTag("EMTGTSource", data);
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity1) {
        if (capacity1 < this.power) {
            capacity1 = this.power;
        }

        this.capacity = capacity1;
    }

    public double getEnergyStored() {
        return this.energyStored;
    }

    public void setEnergyStored(double amount) {
        this.energyStored = amount;
    }

    public double addEnergy(double amount) {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            return 0.0D;
        } else {
            if (amount > this.capacity - this.energyStored) {
                amount = this.capacity - this.energyStored;
            }

            this.energyStored += amount;
            return amount;
        }
    }

    public void drawEnergy(double amount) {
        this.energyStored -= amount;
    }

    public byte getSourceTier() {
        return this.tier;
    }
}
