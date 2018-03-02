package emt.client.gui.container;

import emt.tile.solar.TileEntitySolarBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSolars
  extends Container
{
  private TileEntitySolarBase tileentity;
  public int storage;
  
  public ContainerSolars(InventoryPlayer inventoryplayer, TileEntitySolarBase tileentity)
  {
    this.storage = 0;
    this.tileentity = tileentity;
   /* for (int j = 0; j < 4; j++) {
      addSlotToContainer(new Slot(this.tileentity, j, 17 + j * 18, 59));
    }
    
    for (int i = 0; i < 3; i++) {
      for (int k = 0; k < 9; k++) {
        addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 17 + k * 18, 86 + i * 18));
      }
    }
    for (int j = 0; j < 9; j++) {
      addSlotToContainer(new Slot(inventoryplayer, j, 17 + j * 18, 144));
    }
    
    addSlotToContainer(new Slot(this.tileentity, 5, 17 + 7 * 18, 59));*/
  }
  
  public void addCraftingToCrafters(ICrafting icrafting)
  {
    super.addCraftingToCrafters(icrafting);
    icrafting.sendProgressBarUpdate(this, 0, (int) this.tileentity.generating);
    icrafting.sendProgressBarUpdate(this, 1, this.tileentity.storage);
  }
  
  public void detectAndSendChanges()
  {
    super.detectAndSendChanges();
    float help = (float) (this.tileentity.generating*1000);
    for (int i = 0; i < this.crafters.size(); i++)
    {
      ICrafting icrafting = (ICrafting)this.crafters.get(i);
      
      icrafting.sendProgressBarUpdate(this, 0, (int) help);
      icrafting.sendProgressBarUpdate(this, 1, this.tileentity.storage);
    }
    this.tileentity.storage = ((int)this.tileentity.energySource.getEnergyStored());
  }
  
  public void updateProgressBar(int i, int j)
  {
    if (i == 0) {
    	float help = (float)j;
      this.tileentity.generating = help/1000;
    }
    if (i == 1) {
    	if (j<this.tileentity.maxstorage)
    		this.tileentity.storage = j;
    	else
    		this.tileentity.storage=(int) this.tileentity.maxstorage;
    }
  }
  
  public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
  {
    int slot = par2;
    ItemStack stack = null;
    
    Slot slotObject = (Slot)this.inventorySlots.get(par2);
    if ((slotObject != null) && (slotObject.getHasStack()))
    {
      ItemStack stackInSlot = slotObject.getStack();
      stack = stackInSlot.copy();
      if ((slot >= 0) && (slot <= 3))
      {
        if (!mergeItemStack(stackInSlot, 4, 40, true)) {
          return null;
        }
      }
      else if ((slot >= 4) && (slot < 31))
      {
        if (!mergeItemStack(stackInSlot, 0, 4, false)) {
          if (!mergeItemStack(stackInSlot, 31, 40, false)) {
            return null;
          }
        }
      }
      else if ((slot >= 31) && (slot < 39))
      {
        if (!mergeItemStack(stackInSlot, 0, 30, false)) {
          return null;
        }
      }
      else if (!mergeItemStack(stackInSlot, 0, 30, false)) {
        return null;
      }
      if (stackInSlot.stackSize == 0) {
        slotObject.putStack(null);
      } else {
        slotObject.onSlotChanged();
      }
      if (stack.stackSize != stackInSlot.stackSize) {
        slotObject.onPickupFromSlot(par1EntityPlayer, stackInSlot);
      } else {
        return null;
      }
    }
    return stack;
  }
  
  public boolean canInteractWith(EntityPlayer entityplayer)
  {
	  detectAndSendChanges();
	  return this.tileentity.isUseableByPlayer(entityplayer);
  }
}