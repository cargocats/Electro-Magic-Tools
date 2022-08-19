package emt.client.gui.container;

import emt.tile.solar.TileEntitySolarBase;
import java.nio.ByteBuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;

public class ContainerSolars extends Container {
    byte[] generating = new byte[8];
    byte[] mpStorage = new byte[8];
    private TileEntitySolarBase tileentity;

    public ContainerSolars(InventoryPlayer inventoryplayer, TileEntitySolarBase tileentity) {
        this.tileentity = tileentity;
    }

    public void detectAndSendChanges() {
        byte[] generating =
                ByteBuffer.allocate(8).putDouble(this.tileentity.generating).array();
        byte[] mpStorage = ByteBuffer.allocate(8)
                .putDouble(this.tileentity.getSourceStored() / 1000)
                .array();
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            for (int j = 0; j < generating.length; j++) {
                icrafting.sendProgressBarUpdate(this, j, generating[j]);
            }

            for (int j = 0; j < mpStorage.length; j++) {
                icrafting.sendProgressBarUpdate(this, 8 + j, mpStorage[j]);
            }
        }
    }

    public void updateProgressBar(int i, int j) {
        if (i < 8) {
            generating[i] = (byte) j;
            if (i == 7) this.tileentity.generating = ByteBuffer.wrap(generating).getDouble();
        }
        if (i > 7 && i < 16) {
            mpStorage[i - 8] = (byte) j;
            if (i == 15)
                this.tileentity.mp_storage = (long) ByteBuffer.wrap(mpStorage).getDouble();
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        this.detectAndSendChanges();
        return this.tileentity.isUseableByPlayer(entityplayer);
    }
}
