package tombenpotter.emt.client.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.entity.EntityLaser;
import tombenpotter.emt.common.entity.EntityShield;

public class RenderShield extends Render {
	private static final ResourceLocation shieldTexture = new ResourceLocation(ModInformation.texturePath, "textures/models/shield.png");
	
	private static final float[] values = new float[6912];
	
	static{
		float sin1;
		float cos1;
		
		float sin2;
		float cos2;
		
		float hsin1 = 0;
		float hcos1 = 1;
		
		float hsin2;
		float hcos2;
		
		int i = 0;
		
		for (float h = 0; h < 360; h += 15) {
			sin1 = 0;
			cos1 = 1;
			
			hsin2 = MathHelper.sin((float) Math.toRadians(h + 15));
			hcos2 = MathHelper.cos((float) Math.toRadians(h + 15));
			
			for (float angle = 0; angle < 360; angle += 15) {
				sin2 = MathHelper.sin((float) Math.toRadians(angle + 15));
				cos2 = MathHelper.cos((float) Math.toRadians(angle + 15));
				
				values[i]      = sin1 * hcos1 * 2;
				values[i + 1]  = hsin1 * 2;
				values[i + 2]  = cos1 * hcos1 * 2;
				
				values[i + 3]  = sin1 * hcos2 * 2;
				values[i + 4]  = hsin2 * 2;
				values[i + 5]  = cos1 * hcos2 * 2;
				
				values[i + 6]  = sin2 * hcos2 * 2;
				values[i + 7]  = hsin2 * 2;
				values[i + 8]  = cos2 * hcos2 * 2;
				
				values[i + 9]  = sin2 * hcos1 * 2;
				values[i + 10] = hsin1 * 2;
				values[i + 11] = cos2 * hcos1 * 2;
				
				i += 12;
				
				sin1 = sin2;
				cos1 = cos2;
			}
			
			hsin1 = hsin2;
			hcos1 = hcos2;
		}
	}

	public void doRender(Entity entityShield, double x, double y, double z, float par8, float par9) {
		this.bindEntityTexture(entityShield);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glPushAttrib(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHTING);
		if(!Minecraft.getMinecraft().thePlayer.getDisplayName().equals( ((EntityShield)entityShield).owner.getDisplayName() ))
			GL11.glTranslated(x, y, z);

		for (int v = 0; v < values.length; v += 12) {
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(values[v]     , values[v + 1] , values[v + 2] , 1, 1);
			tessellator.addVertexWithUV(values[v + 3] , values[v + 4] , values[v + 5] , 1, 0);
			tessellator.addVertexWithUV(values[v + 6] , values[v + 7] , values[v + 8] , 0, 0);
			tessellator.addVertexWithUV(values[v + 9] , values[v + 10], values[v + 11], 0, 1);
			tessellator.draw();
		}
		
		GL11.glPopAttrib();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity texture) {
		return shieldTexture;
	}

}
