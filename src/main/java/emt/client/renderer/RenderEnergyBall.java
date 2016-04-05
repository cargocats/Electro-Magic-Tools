package emt.client.renderer;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import emt.entity.EntityEnergyBall;
import emt.util.EMTRandomHelper.Bolt;
import emt.util.EMTRandomHelper.Point;

public class RenderEnergyBall extends Render {

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p1, float p2) {
		EntityEnergyBall ball = (EntityEnergyBall) entity;

		if (ball.shootingEntity == null) {
			if (ball.worldObj.isRemote) {
				Tessellator tess = Tessellator.instance;

				glPushMatrix();
				glDisable(GL_LIGHTING);
				glDisable(GL_FOG);
				glDisable(GL_BLEND);
				glDisable(GL_TEXTURE_2D);
				glTranslated(x, y, z);

				/*******************************************/
				for (int i = 0; i < ball.bolts.length; i++) {
					Bolt bolt = ball.bolts[i];

					glPushMatrix();
					glRotatef(bolt.angleZ, 0, 0, 1);
					glRotatef(bolt.angleY, 0, 1, 0);
					glLineWidth(2);

					tess.startDrawing(GL_LINE_STRIP);
					for (Point point : bolt.points) {
						tess.setColorRGBA(point.r & 0xff, point.g & 0xff, point.b & 0xff, 255);
						tess.addVertex((point.x / 10), (point.y / 10), (point.z / 10));
					}
					tess.draw();

					glPopMatrix();
				}
				/*******************************************/

				if (ball.inTile) {

					/** Horizontal **/
					for (int i = 0; i < ball.bolts.length; i++) {
						Bolt bolt = ball.bolts[i];

						glPushMatrix();
						glRotatef(bolt.angleY, 0, 1, 0);
						glLineWidth(2);

						tess.startDrawing(GL_LINE_STRIP);
						tess.setColorRGBA(255, 255, 255, 255);
						for (Point point : bolt.points) {
							tess.addVertex(point.x / (60 / ball.ticksAlive), point.y / 4, point.z / (60 / ball.ticksAlive));
						}
						tess.draw();

						glPopMatrix();
					}
					/****************/

					if (ball.entityPositions != null) {
						for (int i = 0; i < ball.entityPositions.length; i++) {
							Point pos = ball.entityPositions[i];
							double trX = pos.x - entity.posX;
							double trY = pos.y - entity.posY;
							double trZ = pos.z - entity.posZ;

							for(int e = 0; e < ball.MAX_COUNT_OF_BOLTS; e++) {
								Bolt bolt = ball.entityBolts[i][e];

								glPushMatrix();
								glTranslated(trX, trY, trZ);
								glRotatef(bolt.angleZ, 0, 0, 1);
								glRotatef(bolt.angleY, 0, 1, 0);
								glLineWidth(2);

								tess.startDrawing(GL_LINE_STRIP);
								for (Point point : bolt.points) {
									tess.setColorRGBA(point.r & 0xff, point.g & 0xff, point.b & 0xff, 255);
									tess.addVertex(point.x / 4 , point.y / 4, point.z / 4);
								}
								tess.draw();

								glPopMatrix();
							}
						}
					}
				}

				glPopMatrix();
				glEnable(GL_FOG);
				glEnable(GL_TEXTURE_2D);
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
