package emt.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityElectroBall extends EntityFireball {

	public EntityElectroBall(World world) {
		super(world);
	}

	public EntityElectroBall(World world, EntityLivingBase player, double rotX, double rotY, double rotZ) {
		super(world, player, rotX, rotY, rotZ);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {

	}

}
