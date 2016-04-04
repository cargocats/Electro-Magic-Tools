package emt.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.util.EMTRandomHelper.Bolt;
import emt.util.EMTRandomHelper.Point;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EntityElectroBall extends Entity implements IProjectile, IEntityAdditionalSpawnData {
	public EntityLivingBase shootingEntity;
	public Bolt[] bolts = new Bolt[25];
	public Point[] entityPositions;
	public boolean inTile = false;
	private int ticksAlive;
	private int ticksInAir;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;
	public static final int radius = 8;
	public static final float length = 16f;
	public static final float dist = 1f;
	public static final float height = 8f;

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
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeDouble(accelerationX);
		buffer.writeDouble(accelerationY);
		buffer.writeDouble(accelerationZ);
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {
		accelerationX = buffer.readDouble();
		accelerationY = buffer.readDouble();
		accelerationZ = buffer.readDouble();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		/** If in Tile **/
		if (this.inTile) {
			this.ticksAlive++;

			if (this.ticksAlive >= 60) {
				this.setDead();
			}

			/** Generating Coords for every Entity **/
			ArrayList<Entity> entities = new ArrayList();

			int chunkSize = ((radius - 1) >> 4) + 2;

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
								double hyp = MathHelper.sqrt_double((width * width) + (length * length) + (height * height));

								if (hyp <= radius) {
									entities.add(entity);
									/** Closer - Harder */
									entity.attackEntityFrom(DamageSource.magic, (float) (radius - hyp));
									entity.performHurtAnimation();
								}
							}
						}
					}
				}
			}

			entityPositions = new Point[entities.size()];
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				entityPositions[i] = new Point(entity.posX, entity.posY, entity.posZ);
			}

			/************************************************************************/
		}
		/** If not in Tile **/
		else {
			/** Collision detection **/
			Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec3next = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition mop = this.worldObj.rayTraceBlocks(vec3, vec3next);

			this.ticksInAir++;
			if (mop != null) {
				inTile = true;
			}

			/** Updating position */
			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

			for (this.rotationPitch = (float) (Math.atan2((double) f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

			double factor = this.getMotionFactor();
			this.motionX += this.accelerationX;
			this.motionY += this.accelerationY;
			this.motionZ += this.accelerationZ;
			this.motionX *= factor;
			this.motionY *= factor;
			this.motionZ *= factor;
			this.setPosition(this.posX, this.posY, this.posZ);
			/************************************************************************/
		}

		if (this.worldObj.isRemote) {
			for (int i = 0; i < bolts.length; i++) {
				bolts[i].regenerate();
			}
		}

	}

	protected float getMotionFactor() {
		return 0.75F;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
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
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setByte("inTile", (byte) (this.inTile ? 1 : 0));
		nbt.setTag("direction", this.newDoubleNBTList(new double[] { this.motionX, this.motionY, this.motionZ }));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		if (!inTile) {
			this.inTile = nbt.getByte("inTile") == 1;
		}

		if (nbt.hasKey("direction", 9)) {
			NBTTagList nbttaglist = nbt.getTagList("direction", 6);
			this.motionX = nbttaglist.func_150309_d(0);
			this.motionY = nbttaglist.func_150309_d(1);
			this.motionZ = nbttaglist.func_150309_d(2);
		}
		else {
			this.setDead();
		}
	}

	@Override
	public void setThrowableHeading(double x, double y, double z, float p1, float p2) {	
	}
}
