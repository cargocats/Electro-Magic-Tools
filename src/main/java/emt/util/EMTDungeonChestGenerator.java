package emt.util;

import emt.init.EMTItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class EMTDungeonChestGenerator {

    public static void generateLoot() {
        if (!EMTConfigHandler.thorHammerResearch) {
            ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(EMTItems.taintedThorHammer), 0, 1, EMTConfigHandler.chanceTaintedMjolnir));
        }
        if (!EMTConfigHandler.oneRingSpawn) {
            ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(EMTItems.onering), 0, 1, EMTConfigHandler.chanceOneRing));
        }
    }
}
