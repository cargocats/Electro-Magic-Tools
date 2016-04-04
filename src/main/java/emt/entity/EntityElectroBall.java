package emt.entity;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.util.EMTRandomHelper.Bolt;
import emt.util.EMTRandomHelper.Point;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EntityElectroBall extends Entity {
	public EntityLivingBase shootingEntity;
	public Bolt[] bolts = new Bolt[20];
	public Point[] entityPositions;
	public boolean onGround = false;
	private int ticksAlive;
	private int ticksInAir;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;
	public static final int searchRadius = 16;
	public static final float length = 10f;
	public static final float dist = 1f;
	public static final float height = 4f;

	public EntityElectroBall(World world, EntityLivingBase shootingEntity, double rotX, double rotY, double rotZ) {
		this(world, shootingEntity);

		rotX += this.rand.nextGaussian() * 0.4D;
		rotY += this.rand.nextGaussian() * 0.4D;
		rotZ += this.rand.nextGaussian() * 0.4D;
		double len = (double) MathHelper.sqrt_double((rotX * rotX) + (rotY * rotY) + (rotZ * rotZ));
		this.accelerationX = rotX / len * 0.1D;
		this.accelerationY = rotY / len * 0.1D;
		this.accelerationZ = rotZ / len * 0.1D;
	}

	public EntityElectroBall(World world, EntityLivingBase shootingEntity) {
		this(world);

		this.shootingEntity = shootingEntity;
		this.setLocationAndAngles(shootingEntity.posX, shootingEntity.posY, shootingEntity.posZ, shootingEntity.rotationYaw, shootingEntity.rotationPitch);
	}

	public EntityElectroBall(World world) {
		super(world);

		this.setSize(1.0F, 1.0F);
		this.ignoreFrustumCheck = true;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = this.motionY = this.motionZ = 0.0D;

		for (int i = 0; i < bolts.length; i++) {
			bolts[i] = new Bolt(dist, length, height);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		for (int i = 0; i < bolts.length; i++) {
			bolts[i].regenerate();
		}

		ArrayList<Entity> entities = new ArrayList();

		int chunkSize = ((searchRadius - 1) >> 4) + 1;

		for (int chX = -chunkSize; chX < chunkSize; chX++) {
			for (int chZ = -chunkSize; chZ < chunkSize; chZ++) {
				Chunk chunk = worldObj.getChunkFromChunkCoords(chX + (int) (this.posX / 16), chZ + (int) (this.posZ / 16));

				if (chunk.isChunkLoaded && chunk.hasEntities) {
					for (int i = 0; i < chunk.entityLists.length; i++) {
						for (int k = 0; k < chunk.entityLists[i].size(); k++) {
							Entity entity = (Entity) chunk.entityLists[i].get(k);
							double width = entity.posX - this.posX;
							double height = entity.posY - this.posY;
							double length = entity.posZ - this.posZ;

							if (MathHelper.sqrt_double((width * width) + (length * length) + (height * height)) <= searchRadius) {
								entities.add(entity);
							}
						}
					}
				}
			}
		}

		entityPositions = new Point[entities.size()];
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.performHurtAnimation();
			entityPositions[i] = new Point(entity.posX, entity.posY, entity.posZ);
		}

	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float p_70070_1_) {
		return 240;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}
}
