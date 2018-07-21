package emt.client.gui.container;

import emt.tile.generator.TileEntityBaseGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ConainerGenerator
  extends Container
{
  private TileEntityBaseGenerator tileentity;
  
  public ConainerGenerator(InventoryPlayer inventoryplayer, TileEntityBaseGenerator tileentity)
  {
    this.tileentity = tileentity;
  }
  
  public void addCraftingToCrafters(ICrafting icrafting)
  {
  
    super.addCraftingToCrafters(icrafting); 
    icrafting.sendProgressBarUpdate(this, 0, (short) (this.tileentity.generating/400));
    icrafting.sendProgressBarUpdate(this, 1, (byte) this.tileentity.fuel);
    icrafting.sendProgressBarUpdate(this, 2, (short) this.tileentity.mpshownstroage);
  }
  
  public void detectAndSendChanges()
  {
    super.detectAndSendChanges();
    for (int i = 0; i < this.crafters.size(); i++)
    {
      ICrafting icrafting = (ICrafting)this.crafters.get(i);
      
      icrafting.sendProgressBarUpdate(this, 0, (short) (this.tileentity.generating/400));
      icrafting.sendProgressBarUpdate(this, 1, (byte) this.tileentity.fuel);
      icrafting.sendProgressBarUpdate(this, 2, (short) (this.tileentity.storage/1000));
    }
  }
  
  public void updateProgressBar(int i, int j)
  {
    if (i == 0) {
      this.tileentity.generating = (((int)j)*400);
    }
    if (i == 1) {
    	this.tileentity.fuel=((byte) j);
    }
    if (i == 2) {
    	this.tileentity.mpshownstroage=(short) j;
    }
  }
  
  
  
  public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
  {
    return null;
  }
  
  public boolean canInteractWith(EntityPlayer entityplayer)
  {
	this.detectAndSendChanges();
    return this.tileentity.isUseableByPlayer(entityplayer);
  }
}