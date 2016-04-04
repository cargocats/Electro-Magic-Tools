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
		
		if (ball != null && ball.entityPositions != null) {
			Tessellator tessellator = Tessellator.instance;

			glPushMatrix();
			glDisable(GL_LIGHTING);
			glDisable(GL_FOG);
			glDisable(GL_BLEND);
			glDisable(GL_TEXTURE_2D);
			glTranslated(x, y, z);

			for (Point pos : ball.entityPositions) {
				double trX = pos.x - entity.posX;
				double trY = pos.y - entity.posY;
				double trZ = pos.z - entity.posZ;
				
				for (int i = 0; i < ball.bolts.length; i++) {
					Bolt bolt = ball.bolts[i];

					glPushMatrix();
					glTranslated(trX, trY, trZ);
					glRotatef(bolt.angleZ, 0, 0, 1);
					glRotatef(bolt.angleY, 0, 1, 0);
					glLineWidth(2);

					tessellator.startDrawing(GL_LINE_STRIP);
					tessellator.setColorRGBA(255, 255, 255, 255);
					tessellator.addVertex(0, 0, 0);
					for (Point point : bolt.points) {
						tessellator.addVertex((point.x / 10f), (point.y / 10f), (point.z / 10f));
					}
					tessellator.draw();

					glPopMatrix();
				}
			}
			glPopMatrix();
			glEnable(GL_FOG);
			glEnable(GL_TEXTURE_2D);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
