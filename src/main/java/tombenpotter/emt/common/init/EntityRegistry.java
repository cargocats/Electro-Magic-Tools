package tombenpotter.emt.common.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.common.entities.EntityArcher;
import tombenpotter.emt.common.entities.EntityLaser;
import tombenpotter.emt.common.entities.EntityShield;

public class EntityRegistry {

	private static int startEID = 300;
	private static int entityIDs = 0;

	private static int getUniqueEntityID() {

		do {
			startEID++;
		}
		while (EntityList.getStringFromID(startEID) != null);
		return startEID;
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int colPrim, int colSec) {
		int id = getUniqueEntityID();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, colPrim, colSec));
		return;
	}

	public static void registerEMTEntities() {

		cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityLaser.class, "laser", entityIDs++, ElectroMagicTools.instance, 80, 3, true);
		cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityArcher.class, "archer", entityIDs++, ElectroMagicTools.instance, 80, 3, true);
		cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityShield.class, "shield", entityIDs++, ElectroMagicTools.instance, 80, 3, true);
		registerEntityEgg(EntityArcher.class, 0x99111F, 0xE5685);
	}
}
