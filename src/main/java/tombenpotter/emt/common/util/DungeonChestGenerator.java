package tombenpotter.emt.common.util;

import tombenpotter.emt.common.init.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonChestGenerator {

	public static void generateLoot() {
		if (!ConfigHandler.thorHammerResearch) {
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.taintedThorHammer), 0, 1, ConfigHandler.chanceTaintedMjolnir));
		}
		if (!ConfigHandler.oneRingSpawn) {
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.onering), 0, 1, ConfigHandler.chanceOneRing));
		}
	}
}
