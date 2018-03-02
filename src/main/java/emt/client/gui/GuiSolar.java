package emt.client.gui;

import org.lwjgl.opengl.GL11;

import emt.EMT;
import emt.client.gui.container.ConainerGenerator;
import emt.client.gui.container.ContainerSolars;
import emt.tile.generator.TileEntityBaseGenerator;
import emt.tile.solar.TileEntitySolarBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSolar extends GuiContainer {
	
	private static ResourceLocation tex = new ResourceLocation(EMT.TEXTURE_PATH, "textures/guis/solar.png");
	
	TileEntitySolarBase tileentity;
	private boolean allowUserInput;
	private int xSize;
	private int ySize; 
	private String color;
	private int cColor;
	
	public GuiSolar(InventoryPlayer invPlayer, TileEntitySolarBase entity) {
		super(new ContainerSolars(invPlayer, entity));
		this.tileentity = entity;
		this.allowUserInput = false;
		this.xSize = 194;
		this.ySize = 168;
	}

	@Override
	  protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	  {
		color = this.tileentity.getInventoryName().replaceAll("Solar", "").replaceAll("Compressed", "").replaceAll("Triple", "").replaceAll("Double", "").trim();
		if (color != null)
		switch (color)
		{
		case "Aer": {
			GL11.glColor4f(1.0f, 0.86f, 0.0f, 1.0f);
			break;
			}
		case "Terra": {
			GL11.glColor4f(0.0f, 0.66f, 0.08f, 1.0f);
			cColor=8224255;
			break;
			}
		case "Ordo": {
			GL11.glColor4f(0.8f, 0.8f, 0.8f, 1.0f);
			break;
			}
		case "Perditio": {
			GL11.glColor4f(0.33F, 0.33F, 0.33F, 1.0F);
			break;
			}
		case "Ignis": {
			GL11.glColor4f(0.82f, 0.11f, 0.11f, 1.0f);
			break;
			}
		case "Aqua": {
			GL11.glColor4f(0.27f, 0.65f, 1.0f, 1.0f);
			break;
			}
		default: {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			break;
			}
		}
		else
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
	    
	    this.mc.renderEngine.bindTexture(tex);
	    
	    int h = (this.width - this.xSize) / 2;
	    int k = (this.height - this.ySize) / 2;
	    drawTexturedModalRect(h, k, 0, 0, this.xSize, this.ySize);
	    if (this.tileentity.storage > 0)
	    {
	      int l = this.tileentity.gaugeEnergyScaled(33);
	      drawTexturedModalRect(h + 18, k + 23, 195, 0, l, 14);
	    }
	    if (this.tileentity.generating>0)
	    	drawTexturedModalRect(h + 18, k + 42, 195, 20, 11, 14);
	  }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	  {
	    String formatPanelName = I18n.format(this.tileentity.getInventoryName(), new Object[0]);
	    int nmPos = (this.xSize - this.fontRendererObj.getStringWidth(formatPanelName)) / 2;
	    if(!color.equals("Terra"))
	    	this.fontRendererObj.drawStringWithShadow(formatPanelName, 0, 7, 6618118);
	    else
	    	this.fontRendererObj.drawStringWithShadow(formatPanelName, 0, 7, cColor);
	    String storageString = I18n.format("emt.Storage", new Object[0]);
	    if (this.tileentity.maxstorage<1000)
	    this.fontRendererObj.drawString(storageString + this.tileentity.storage + "/" + this.tileentity.maxstorage +"EU", 36, 22, 0);
	    else if (10000 <= this.tileentity.maxstorage && this.tileentity.maxstorage < 10000000)
	    	this.fontRendererObj.drawString(storageString + (this.tileentity.storage/1000) + "/" + (this.tileentity.maxstorage/1000) +"kEU", 36, 22, 0);
	    else if (10000000 <= this.tileentity.maxstorage && this.tileentity.maxstorage < 1000000000)
	    	this.fontRendererObj.drawString(storageString + (this.tileentity.storage/1000000) + "/" + (this.tileentity.maxstorage/1000000) +"MEU", 36, 22, 0);
	    else if (1000000000 <= this.tileentity.maxstorage)
	    	this.fontRendererObj.drawString(storageString + (this.tileentity.storage/1000000000) + "/" + (this.tileentity.maxstorage/1000000000) +"GEU", 36, 22, 0);
	    if(this.tileentity.isActive)
	    	if (Double.toString(this.tileentity.generating).length()>5)
	    		this.fontRendererObj.drawString(I18n.format("emt.Generating",new Object[0])+Double.toString(this.tileentity.generating).substring(0, 4)+" EU/t", 36, 35, 0);
	    	else
	    		this.fontRendererObj.drawString(I18n.format("emt.Generating",new Object[0])+Double.toString(this.tileentity.generating)+" EU/t", 36, 35, 0);
	    else
	    	this.fontRendererObj.drawString(I18n.format("emt.Generating",new Object[0])+"0 EU/t",36, 35, 0);
	  }
}
