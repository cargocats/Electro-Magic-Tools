package emt.util;

import ic2.api.item.ElectricItem;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Locale;

public class EMTRandomHelper {

	public static ItemStack getChargedItem(Item item, int charge) {
		ItemStack stack = new ItemStack(item);
		ElectricItem.manager.charge(stack, charge, charge, true, false);
		return stack;
	}
}
