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

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class EMTRandomHelper {

	public static ItemStack getChargedItem(Item item, int charge) {
		ItemStack stack = new ItemStack(item);
		ElectricItem.manager.charge(stack, charge, charge, true, false);
		return stack;
	}

	public static class Bolt {
		Random rnd = new Random();
		public ArrayList<Point> points = new ArrayList<Point>();
		public float angleZ = 0;
		public float angleY = 0;
		float dist = 0;
		float length = 0;
		float height = 0;

		public Bolt(float dist, float length, float height) {
			this.dist = dist;
			this.length = (length / 2f) + (rnd.nextInt((int) (length / 2f)));
			this.height = height;

			regenerate(this.dist, this.length, this.height);
		}

		public void regenerate() {
			regenerate(dist, length, height);
		}

		public void regenerate(float dist, float length, float height) {
			points.clear();

			angleZ = rnd.nextInt(360);
			angleY = rnd.nextInt(360);

			for (int i = 0; i < length / dist; i++) {
				float x = i * dist;
				float prevY = i == 0 ? 0 : points.get(i - 1).y;
				float prevZ = i == 0 ? 0 : points.get(i - 1).z;
				float y = prevY + (rnd.nextInt((int) height) - (height / 2));
				float z = prevZ + (rnd.nextInt((int) height) - (height / 2));

				points.add(new Point(x, y, z));
			}
		}
	}

	public static class Point {
		public float x = 0;
		public float y = 0;
		public float z = 0;

		public Point(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public Point(double x, double y, double z) {
			this.x = (float) x;
			this.y = (float) y;
			this.z = (float) z;
		}
	}
}
