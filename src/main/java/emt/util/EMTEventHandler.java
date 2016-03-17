package emt.util;

import java.util.Random;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import emt.ElectroMagicTools;
import emt.ModInformation;
import emt.client.EMTKeys;
import emt.init.EMTItems;
import emt.network.PacketEMTKeys;
import emt.proxy.ClientProxy;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumcraft.common.entities.monster.EntityTaintChicken;

public class EMTEventHandler {
	Random rnd = new Random();

	@SubscribeEvent
	public void onEntityLivingDrops(LivingDropsEvent event) {
		if (event.entityLiving instanceof EntityCreeper) {
			EntityCreeper creeper = (EntityCreeper) event.entityLiving;
			if (creeper.getPowered()) {
				event.entityLiving.entityDropItem(new ItemStack(EMTItems.itemEMTItems, 1, 6), 1);
			}
		}
		if (event.entityLiving instanceof EntityTaintChicken) {
			event.entityLiving.entityDropItem(new ItemStack(EMTItems.itemEMTItems, rnd.nextInt(3), 13), 1);
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(ModInformation.modid)) {
			EMTConfigHandler.syncConfig();
			ElectroMagicTools.logger.info("Refreshing configuration file.");
		}
	}

	@SubscribeEvent
	public void clientTick(ClientTickEvent e) {
		if (EMTKeys.keyUnequip.getIsKeyPressed()) {
			ElectroMagicTools.INSTANCE.sendToServer(new PacketEMTKeys());
		}
	}
}
