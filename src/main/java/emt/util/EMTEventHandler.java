package emt.util;

import java.util.Random;

import baubles.api.BaublesApi;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import emt.EMT;
import emt.client.EMTKeys;
import emt.init.EMTBlocks;
import emt.init.EMTItems;
import emt.network.PacketEMTKeys;
import emt.proxy.ClientProxy;
import ic2.api.item.ElectricItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
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
		if (event.modID.equals(EMT.MOD_ID)) {
			EMTConfigHandler.syncConfig();
			EMT.LOGGER.info("Refreshing configuration file.");
		}
	}

	@SubscribeEvent
	public void onSetEntityAttack(LivingSetAttackTargetEvent e) {
		if (e.entityLiving != null && e.target != null && e.entityLiving instanceof EntityLiving && e.target instanceof EntityPlayer) {
			IInventory inventory = BaublesApi.getBaubles((EntityPlayer) e.target);
			
			for(int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlot(i);
				
				if(stack != null && stack.getItem() == EMTItems.onering) {
					((EntityLiving)e.entityLiving).setAttackTarget(null);
				}
			}

		}
	}
	
	@SubscribeEvent
	public void createCloud(PlayerInteractEvent e) {
		if(e.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || e.world.isRemote) {
			return;
		}
		
		Block block = e.world.getBlock(e.x, e.y, e.z);
		int meta = e.world.getBlockMetadata(e.x, e.y, e.z);
		
		if(block != ConfigBlocks.blockAiry || meta != 1) {
			return;
		}
		
		ItemStack currentStack = e.entityPlayer.inventory.getCurrentItem();
		if(currentStack == null) {
			return;
		}
		
		double val = ElectricItem.manager.discharge(currentStack, 256, 4, false, true, false);
		if(val < 256) {
			return;
		}
		
		e.world.setBlock(e.x, e.y, e.z, EMTBlocks.electricCloud);
	}
}
