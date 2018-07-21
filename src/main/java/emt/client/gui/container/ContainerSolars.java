package emt.client.gui.container;

import emt.tile.solar.TileEntitySolarBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;

public class ContainerSolars
  extends Container
{
  private TileEntitySolarBase tileentity;
  
  public ContainerSolars(InventoryPlayer inventoryplayer, TileEntitySolarBase tileentity)
  {
    this.tileentity = tileentity;
  }
  
  public void addCraftingToCrafters(ICrafting icrafting)
  { 
	super.addCraftingToCrafters(icrafting);
    icrafting.sendProgressBarUpdate(this, 0, (short) this.tileentity.generating);
    icrafting.sendProgressBarUpdate(this, 1, (short) this.tileentity.mp_storage);
  }
  
  public void detectAndSendChanges()
  {
	super.detectAndSendChanges();
    short help = 0;
    if (this.tileentity.generating < 326D && this.tileentity.generating > 9D)
    	help = (short) (this.tileentity.generating*100*-1);
    else
    	help = (short) (this.tileentity.generating);
    
    for (int i = 0; i < this.crafters.size(); i++)
    {
      ICrafting icrafting = (ICrafting)this.crafters.get(i);
      
      icrafting.sendProgressBarUpdate(this, 0, help);
      icrafting.sendProgressBarUpdate(this, 1, (short) (this.tileentity.energySource.getEnergyStored()/1000));
    }
  }
  
  public void updateProgressBar(int i, int j)
  {

    if (i == 0) {
    	if (j < 0) {
    		double help= j;
    		help=help/100*-1;
    		this.tileentity.generating = help;
    	}
    	else
    		 this.tileentity.generating=j;
    }
    if (i == 1) {
    	this.tileentity.mp_storage = (short) (j);
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