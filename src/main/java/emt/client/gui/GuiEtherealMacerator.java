package emt.client.gui;

import emt.EMT;
import emt.client.gui.container.ContainerEtheralMacerator;
import emt.tile.TileEntityEtherealMacerator;
import emt.util.EMTTextHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiEtherealMacerator extends GuiContainer {

    public static final ResourceLocation texture =
            new ResourceLocation(EMT.TEXTURE_PATH, "textures/guis/etherealmacerator.png");

    public GuiEtherealMacerator(InventoryPlayer inventory, TileEntityEtherealMacerator te) {
        super(new ContainerEtheralMacerator(inventory, te));
        xSize = 176;
        ySize = 165;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(EMTTextHelper.localize("gui.EMT.etherealMacerator.title"), 6, 6, 4210752);
        this.fontRendererObj.drawString(EMTTextHelper.localize("gui.EMT.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
}
