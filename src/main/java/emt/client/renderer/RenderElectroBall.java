package emt.client.renderer;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import emt.entity.EntityElectroBall;
import emt.util.EMTRandomHelper.Bolt;
import emt.util.EMTRandomHelper.Point;

public class RenderElectroBall extends Render {

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p1, float p2) {
		EntityElectroBall ball = (EntityElectroBall) entity;

		if (ball.shootingEntity == null) {
			if (ball.worldObj.isRemote) {
				Tessellator tessellator = Tessellator.instance;

				glPushMatrix();
				glDisable(GL_LIGHTING);
				glDisable(GL_FOG);
				glDisable(GL_BLEND);
				glDisable(GL_TEXTURE_2D);
				glTranslated(x, y, z);

				renderBolt(ball, tessellator, 0, 0, 0, 8);

				if (ball.inTile) {

					renderBolt(ball, tessellator, 0, 0, 0, 0.5F);

					if (ball.entityPositions != null) {
						for (Point pos : ball.entityPositions) {
							double trX = pos.x - entity.posX;
							double trY = pos.y - entity.posY;
							double trZ = pos.z - entity.posZ;

							renderBolt(ball, tessellator, trX, trY, trZ, 8);
						}
					}
				}

				glPopMatrix();
				glEnable(GL_FOG);
				glEnable(GL_TEXTURE_2D);
			}
		}
	}

	public void renderBolt(EntityElectroBall ball, Tessellator tess, double x, double y, double z, double scale) {
		for (int i = 0; i < ball.bolts.length; i++) {
			Bolt bolt = ball.bolts[i];

			glPushMatrix();
			glTranslated(x, y, z);
			glRotatef(bolt.angleZ, 0, 0, 1);
			glRotatef(bolt.angleY, 0, 1, 0);
			glLineWidth(2);

			tess.startDrawing(GL_LINE_STRIP);
			tess.setColorRGBA(255, 255, 255, 255);
			for (Point point : bolt.points) {
				tess.addVertex((point.x / scale), (point.y / scale), (point.z / scale));
			}
			tess.draw();

			glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
