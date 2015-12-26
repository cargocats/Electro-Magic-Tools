package tombenpotter.emt.common.entity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import net.minecraft.client.Minecraft;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityShield extends Entity {
	public EntityPlayer owner;
	public boolean needCheck = true;

	public EntityShield(World world) {
		super(world);
		this.ignoreFrustumCheck = true;
	}

	public EntityShield(World world, EntityPlayer player) {
		super(world);
		this.setSize(4, 4);
		owner = player;
		this.dataWatcher.updateObject(11, owner.getDisplayName());
		this.setPosition(player.posX, player.posY, player.posZ);
		this.ignoreFrustumCheck = true;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(11, "");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
	}

	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	public void setPosition(double x, double y, double z) {
		this.posX = x;
		this.posY = y + 0.5f;
		this.posZ = z;
		float f = this.width / 2.0F;
		float f1 = this.height;
		this.boundingBox.setBounds(x - (double) f, y - (double) this.yOffset - 2 + (double) this.ySize, z - (double) f, x + (double) f, y - (double) this.yOffset - 2 + (double) this.ySize + (double) f1, z + (double) f);
	}

	public void onUpdate() {
		super.onUpdate();
		
		if(needCheck && owner == null) {
			owner = this.worldObj.getPlayerEntityByName(dataWatcher.getWatchableObjectString(11));
			needCheck = false;
		}
		else if(!needCheck && owner == null){
			this.setDead();
			return;
		}
		
		if (!this.worldObj.isRemote && owner != null) {
			this.setPosition(owner.posX, owner.posY, owner.posZ);
			if (!owner.isUsingItem()) {
				this.setDead();
			}
		}
	}
	
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_){
        return 240;
    }

	public void applyEntityCollision(Entity entity) {
		if (entity.riddenByEntity != this && entity.ridingEntity != this) {
			double ePosX = entity.posX - this.posX;
			double ePosZ = entity.posZ - this.posZ;
			double d2 = MathHelper.abs_max(ePosX, ePosZ);
			d2 = (double) MathHelper.sqrt_double(d2);
			ePosX /= d2;
			ePosZ /= d2;
			double d3 = 1.0D / d2;

			if (d3 > 1.0D) {
				d3 = 1.0D;
			}

			ePosX *= d3;
			ePosZ *= d3;
			ePosX *= 0.05000000074505806D;
			ePosZ *= 0.05000000074505806D;
			ePosX *= (double) (1.0F - this.entityCollisionReduction);
			ePosZ *= (double) (1.0F - this.entityCollisionReduction);
			entity.addVelocity(ePosX * 10, 0.0D, ePosZ * 10);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}
}
