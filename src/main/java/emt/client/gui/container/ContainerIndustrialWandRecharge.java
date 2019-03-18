package emt.client.gui.container;

import emt.tile.TileEntityIndustrialWandRecharge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIndustrialWandRecharge extends Container {

    TileEntityIndustrialWandRecharge charger;

    public ContainerIndustrialWandRecharge(InventoryPlayer invPlayer, TileEntityIndustrialWandRecharge entity) {
        this.charger = entity;

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
            }
        }

        this.addSlotToContainer(new Slot(charger, 0, 80, 35));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return charger.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 36) {
                if (!this.mergeItemStack(itemstack1, 0, 35, false)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (!charger.isItemValidForSlot(0, itemstack1) || !this.mergeItemStack(itemstack1, 36, 37, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
