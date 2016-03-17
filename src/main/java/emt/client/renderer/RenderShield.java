package emt.client.renderer;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL12;

import emt.ModInformation;
import emt.entity.EntityLaser;
import emt.entity.EntityShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderShield extends Render {
	private static final ResourceLocation shieldTexture = new ResourceLocation(ModInformation.texturePath, "textures/models/shield.png");
	private static final float[] values = new float[6912];

	static {
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

				values[i] = sin1 * hcos1 * 2;
				values[i + 1] = hsin1 * 2;
				values[i + 2] = cos1 * hcos1 * 2;

				values[i + 3] = sin1 * hcos2 * 2;
				values[i + 4] = hsin2 * 2;
				values[i + 5] = cos1 * hcos2 * 2;

				values[i + 6] = sin2 * hcos2 * 2;
				values[i + 7] = hsin2 * 2;
				values[i + 8] = cos2 * hcos2 * 2;

				values[i + 9] = sin2 * hcos1 * 2;
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
		Tessellator tessellator = Tessellator.instance;

		glEnable(GL_ALPHA_TEST);
		glAlphaFunc(GL_GREATER, 0);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glPushAttrib(GL_LIGHTING);
		glDisable(GL_LIGHTING);
		glDisable(GL_CULL_FACE);
		glDisable(GL_DEPTH_TEST);

		glPushMatrix();
		if (((EntityShield) entityShield).owner != null && !Minecraft.getMinecraft().thePlayer.getDisplayName().equals(((EntityShield) entityShield).owner.getDisplayName()))
			glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		for (int v = 0; v < values.length; v += 12) {
			tessellator.addVertexWithUV(values[v], values[v + 1], values[v + 2], 1, 1);
			tessellator.addVertexWithUV(values[v + 3], values[v + 4], values[v + 5], 1, 0);
			tessellator.addVertexWithUV(values[v + 6], values[v + 7], values[v + 8], 0, 0);
			tessellator.addVertexWithUV(values[v + 9], values[v + 10], values[v + 11], 0, 1);
		}
		tessellator.draw();

		glPopMatrix();
		glPopAttrib();
		glDisable(GL12.GL_RESCALE_NORMAL);
		glEnable(GL_DEPTH_TEST);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity texture) {
		return shieldTexture;
	}

}
