package tombenpotter.emt.common.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import tombenpotter.emt.ElectroMagicTools;

public class BaseEntityRegistry {

    private static int startEID = 300;
    private static int entityIDs = 0;

    private static int getUniqueEntityID() {

        do {
            startEID++;
        } while (EntityList.getStringFromID(startEID) != null);
        return startEID;
    }

    @SuppressWarnings("unchecked")
    public static void registerEntityEgg(Class<? extends Entity> entity, int colPrim, int colSec) {
        int id = getUniqueEntityID();
        EntityList.IDtoClassMapping.put(id, entity);
        EntityList.entityEggs.put(id, new EntityEggInfo(id, colPrim, colSec));
        return;
    }

    public static void registerEMTEntities() {

        EntityRegistry.registerModEntity(EntityLaser.class, "entityLaser", entityIDs++, ElectroMagicTools.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntityArcher.class, "entityArcher", entityIDs++, ElectroMagicTools.instance, 80, 3, true);
        registerEntityEgg(EntityArcher.class, 0x99111F, 0xE5685);
    }
}
