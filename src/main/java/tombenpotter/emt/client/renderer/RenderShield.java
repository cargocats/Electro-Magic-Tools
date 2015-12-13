package tombenpotter.emt.client.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.entities.EntityLaser;

public class RenderShield extends Render {
	private static final ResourceLocation shieldTexture = new ResourceLocation(ModInformation.texturePath, "textures/models/shield.png");

	public void doRender(Entity entityLaser, double x, double y, double z, float par8, float par9) {
		this.bindEntityTexture(entityLaser);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glPushAttrib(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		for (float h = 0; h < 360; h += 15) {
			for (float angle = 0; angle < 360; angle += 15) {
				float sin1 = (float) Math.sin(Math.toRadians(angle));
				float sin2 = (float) Math.sin(Math.toRadians(angle + 15));

				float cos1 = (float) Math.cos(Math.toRadians(angle));
				float cos2 = (float) Math.cos(Math.toRadians(angle + 15));
				
				float hsin1 = (float) Math.sin(Math.toRadians(h));
				float hsin2 = (float) Math.sin(Math.toRadians(h + 15));

				float hcos1 = (float) Math.cos(Math.toRadians(h));
				float hcos2 = (float) Math.cos(Math.toRadians(h + 15));

				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(sin1 * 2 * hcos1, 2 * hsin1, cos1 * 2 * hcos1, 1, 1);
				tessellator.addVertexWithUV(sin1 * 2 * hcos2, 2 * hsin2, cos1 * 2 * hcos2, 1, 0);
				tessellator.addVertexWithUV(sin2 * 2 * hcos2, 2 * hsin2, cos2 * 2 * hcos2, 0, 0);
				tessellator.addVertexWithUV(sin2 * 2 * hcos1, 2 * hsin1, cos2 * 2 * hcos1, 0, 1);
				tessellator.draw();
			}
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
