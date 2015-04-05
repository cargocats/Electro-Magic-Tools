package tombenpotter.emt.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.items.armor.ItemFeatherWing;
import tombenpotter.emt.common.items.armor.ItemThaumiumReinforcedWing;
import tombenpotter.emt.common.items.armor.boots.ItemElectricBootsTraveller;
import tombenpotter.emt.common.items.armor.boots.ItemNanoBootsTraveller;
import tombenpotter.emt.common.items.armor.boots.ItemQuantumBootsTraveller;
import tombenpotter.emt.common.items.armor.goggles.ItemElectricGoggles;
import tombenpotter.emt.common.items.armor.goggles.ItemNanoGoggles;
import tombenpotter.emt.common.items.armor.goggles.ItemQuantumGoggles;
import tombenpotter.emt.common.items.armor.goggles.ItemSolarHelmetRevealing;
import tombenpotter.emt.common.items.armor.wings.ItemNanoWing;
import tombenpotter.emt.common.items.armor.wings.ItemQuantumWing;
import tombenpotter.emt.common.items.foci.ItemChargeFocus;
import tombenpotter.emt.common.items.foci.ItemChristmasFocus;
import tombenpotter.emt.common.items.foci.ItemExplosionFocus;
import tombenpotter.emt.common.items.foci.ItemShieldFocus;
import tombenpotter.emt.common.items.foci.ItemWandChargingFocus;
import tombenpotter.emt.common.items.tools.*;
import tombenpotter.emt.common.items.tools.chainsaws.ItemDiamondChainsaw;
import tombenpotter.emt.common.items.tools.chainsaws.ItemStreamChainsaw;
import tombenpotter.emt.common.items.tools.chainsaws.ItemThaumiumChainsaw;
import tombenpotter.emt.common.items.tools.drills.ItemRockbreakerDrill;
import tombenpotter.emt.common.items.tools.drills.ItemThaumiumDrill;
import tombenpotter.emt.common.items.tools.omnitools.ItemOmnitoolDiamond;
import tombenpotter.emt.common.items.tools.omnitools.ItemOmnitoolIron;
import tombenpotter.emt.common.items.tools.omnitools.ItemOmnitoolThaumium;

public class ItemRegistry {

    //public static Item woodenDrill, stoneDrill, ironDrill, goldenDrill, diamondDrill, copperDrill, tinDrill, leadDrill, bronzeDrill;
    //public static Item woodenChainsaw, stoneChainsaw, ironChainsaw, goldenChainsaw, diamondChainsaw, copperChainsaw, tinChainsaw, leadChainsaw, bronzeChainsaw;
    //public static Item ironOmnitool, goldenOmnitool, diamondOmnitool, copperOmnitool, tinOmnitool, leadOmnitool, bronzeOmnitool;
    //public static Item woodCarver, stoneCarver, ironCarver, obsidianCarver, diamondCarver;
    public static Item baseBaubles, materials, craftingComponents;

    public static Item focusChristmas;
    
    public static Item thaumiumDrill;
    public static Item thaumiumChainsaw;
    public static Item quantumThaumicHelmet;
    public static Item diamondChainsaw;
    public static Item ironOmnitool;
    public static Item diamondOmnitool;
    public static Item thaumiumOmnitool;
    public static Item nanoThaumicHelmet;
    public static Item explosionFocus;
    public static Item christmasFocus;
    public static Item shieldFocus;
    public static Item electricGoggles;
    public static Item streamChainsaw;
    public static Item rockbreakerDrill;
    public static Item solarHelmetRevealing;
    public static Item thorHammer;
    public static Item taintedThorHammer;
    public static Item electricThorHammer;
    public static Item electricBootsTraveller;
    public static Item quantumBootsTraveller;
    public static Item nanoBootsTraveller;
    public static Item electricScribingTools;
    public static Item itemEMTItems;
    public static Item electricHoeGrowth;
    public static Item chargeFocus;
    public static Item emtBauble;
    public static Item wandChargeFocus;
    public static Item featherWing;
    public static Item thaumiumWing;
    public static Item nanoWing;
    public static Item quantumWing;

    public static ItemArmor.ArmorMaterial featherWingMaterial = EnumHelper.addArmorMaterial("FEATEHRWiNG", 1, new int[]{2, 2, 2, 2}, 6);


    //public static Item.ToolMaterial copperMaterial = EnumHelper.addToolMaterial("copperToolMaterial", 2, 300, 6.0F, 2.0F, 14);
    //public static Item.ToolMaterial tinMaterial = EnumHelper.addToolMaterial("tinToolMatierial", 2, 200, 8.0F, 4F, 18);
    //public static Item.ToolMaterial leadMaterial = EnumHelper.addToolMaterial("leadToolMaterial", 2, 250, 7.0F, 3F, 15);
    //public static Item.ToolMaterial bronzeMaterial = EnumHelper.addToolMaterial("bronzeToolMaterial", 2, 350, 7.0F, 3.0F, 16);

    public static void registerItems() {
        //craftingComponents = new ItemCraftingComponents("components");
        //GameRegistry.registerItem(craftingComponents, "craftingComponents");
        baseBaubles = new ItemBaseBaubles();
        GameRegistry.registerItem(baseBaubles, "BaseBaubles");
        focusChristmas = new ItemChristmasFocus();
        GameRegistry.registerItem(focusChristmas, "FocusChristmas");
        
        itemEMTItems = new ItemMaterials().setUnlocalizedName(ModInformation.modid + ".emtitems");
        GameRegistry.registerItem(itemEMTItems, "EMTItems");
        emtBauble = new ItemIC2Baubles().setUnlocalizedName(ModInformation.modid + ".emtbauble");
        GameRegistry.registerItem(emtBauble, "EMTBaubles");

        electricGoggles = new ItemElectricGoggles(ItemArmor.ArmorMaterial.IRON, 3, 0).setUnlocalizedName(ModInformation.modid + ".goggles.electric");
        GameRegistry.registerItem(electricGoggles, "ElectricGogglesRevealing");
        nanoThaumicHelmet = new ItemNanoGoggles(ItemArmor.ArmorMaterial.DIAMOND, 3, 0).setUnlocalizedName(ModInformation.modid + ".goggles.nano");
        GameRegistry.registerItem(nanoThaumicHelmet, "NanosuitGogglesRevealing");
        quantumThaumicHelmet = new ItemQuantumGoggles(ItemArmor.ArmorMaterial.DIAMOND, 3, 0).setUnlocalizedName(ModInformation.modid + ".goggles.quantum");
        GameRegistry.registerItem(quantumThaumicHelmet, "QuantumGogglesRevealing");
        solarHelmetRevealing = new ItemSolarHelmetRevealing(ItemArmor.ArmorMaterial.DIAMOND, 3, 0).setUnlocalizedName(ModInformation.modid + ".goggles.solar");
        GameRegistry.registerItem(solarHelmetRevealing, "SolarHelmetRevealing");

        electricBootsTraveller = new ItemElectricBootsTraveller(3, 3).setUnlocalizedName(ModInformation.modid + ".boots.traveller.electric");
        GameRegistry.registerItem(electricBootsTraveller, "ElectricBootsTraveller");
        nanoBootsTraveller = new ItemNanoBootsTraveller(3, 3).setUnlocalizedName(ModInformation.modid + ".boots.traveller.nano");
        GameRegistry.registerItem(nanoBootsTraveller, "NanoBootsTraveller");
        quantumBootsTraveller = new ItemQuantumBootsTraveller(3, 3).setUnlocalizedName(ModInformation.modid + ".boots.traveller.quantum");
        GameRegistry.registerItem(quantumBootsTraveller, "QuantumBootsTraveller");

        electricScribingTools = new ItemElectricScribingTools().setUnlocalizedName(ModInformation.modid + ".scribingtools.electric");
        GameRegistry.registerItem(electricScribingTools, "ElectricScribingTools");

        featherWing = new ItemFeatherWing(featherWingMaterial, 7, 1).setUnlocalizedName(ModInformation.modid + ".wing.feather");
        GameRegistry.registerItem(featherWing, "FeatherWing");
        thaumiumWing = new ItemThaumiumReinforcedWing(ThaumcraftApi.armorMatThaumium, 7, 1).setUnlocalizedName(ModInformation.modid + ".wing.thaumium");
        GameRegistry.registerItem(thaumiumWing, "ThaumiumWing");
        nanoWing = new ItemNanoWing(ItemArmor.ArmorMaterial.DIAMOND, 7, 1).setUnlocalizedName(ModInformation.modid + ".wing.nano");
        GameRegistry.registerItem(nanoWing, "NanosuitWing");
        quantumWing = new ItemQuantumWing(ItemArmor.ArmorMaterial.DIAMOND, 7, 1).setUnlocalizedName(ModInformation.modid + ".wing.quantum");
        GameRegistry.registerItem(quantumWing, "QuantumWing");

        thorHammer = new ItemThorHammer().setUnlocalizedName(ModInformation.modid + ".hammer");
        GameRegistry.registerItem(thorHammer, "Mjolnir");
        taintedThorHammer = new ItemThorHammerBroken().setUnlocalizedName(ModInformation.modid + ".hammer.broken");
        GameRegistry.registerItem(taintedThorHammer, "TaintedMjolnir");
        electricThorHammer = new ItemElectricThorHammer().setUnlocalizedName(ModInformation.modid + ".hammer.electric");
        GameRegistry.registerItem(electricThorHammer, "SuperchargedMjolnir");

        thaumiumDrill = new ItemThaumiumDrill().setUnlocalizedName(ModInformation.modid + ".drill.thaumium");
        GameRegistry.registerItem(thaumiumDrill, "ThaumiumDrill");

        diamondChainsaw = new ItemDiamondChainsaw().setUnlocalizedName(ModInformation.modid + ".chainsaw.diamond");
        GameRegistry.registerItem(diamondChainsaw, "DiamondChainsaw");
        thaumiumChainsaw = new ItemThaumiumChainsaw().setUnlocalizedName(ModInformation.modid + ".chainsaw.thaumium");
        GameRegistry.registerItem(thaumiumChainsaw, "ThaumiumChainsaw");

        ironOmnitool = new ItemOmnitoolIron().setUnlocalizedName(ModInformation.modid + ".omnitool.iron");
        GameRegistry.registerItem(ironOmnitool, "Omnitool");
        diamondOmnitool = new ItemOmnitoolDiamond().setUnlocalizedName(ModInformation.modid + ".omnitool.diamond");
        GameRegistry.registerItem(diamondOmnitool, "Diamond Omnitool");
        thaumiumOmnitool = new ItemOmnitoolThaumium().setUnlocalizedName(ModInformation.modid + ".omnitool.thaumium");
        GameRegistry.registerItem(thaumiumOmnitool, "ThaumiumOmnitool");

        streamChainsaw = new ItemStreamChainsaw().setUnlocalizedName(ModInformation.modid + ".chainsaw.stream");
        GameRegistry.registerItem(streamChainsaw, "ChainsawStream");
        rockbreakerDrill = new ItemRockbreakerDrill().setUnlocalizedName(ModInformation.modid + ".drill.rockbreaker");
        GameRegistry.registerItem(rockbreakerDrill, "DrillRockbreaker");
        electricHoeGrowth = new ItemElectricHoeGrowth().setUnlocalizedName(ModInformation.modid + ".hoeofgrowth.electric");
        GameRegistry.registerItem(electricHoeGrowth, "ElectricHoeGrowth");

        explosionFocus = new ItemExplosionFocus();
        GameRegistry.registerItem(explosionFocus, "ExplosionFocus");
        christmasFocus = new ItemChristmasFocus();
        GameRegistry.registerItem(christmasFocus, "ChristmasFocus");
        shieldFocus = new ItemShieldFocus();
        GameRegistry.registerItem(shieldFocus, "ShieldFocus");
        chargeFocus = new ItemChargeFocus();
        GameRegistry.registerItem(chargeFocus, "ChargingFocus");
        wandChargeFocus = new ItemWandChargingFocus();
        GameRegistry.registerItem(wandChargeFocus, "WandChargingFocus");

        //woodCarver = new ItemShardCarver(8).setUnlocalizedName(ModInformation.modid + ".carver.wood");
        //GameRegistry.registerItem(woodCarver, "woodCarver");
        //stoneCarver = new ItemShardCarver(32).setUnlocalizedName(ModInformation.modid + ".carver.stone");
        //GameRegistry.registerItem(stoneCarver, "stoneCarver");
        //ironCarver = new ItemShardCarver(128).setUnlocalizedName(ModInformation.modid + ".carver.iron");
        //GameRegistry.registerItem(ironCarver, "ironCarver");
        //obsidianCarver = new ItemShardCarver(256).setUnlocalizedName(ModInformation.modid + ".carver.obsidian");
        //GameRegistry.registerItem(obsidianCarver, "obsidianCarver");
        //diamondCarver = new ItemShardCarver(1204).setUnlocalizedName(ModInformation.modid + ".carver.diamond");
        //GameRegistry.registerItem(diamondCarver, "diamondCarver");

        //woodenDrill = new ItemVanillaDrill(Item.ToolMaterial.WOOD, 75, "drill_wood", new ItemStack(Item.getItemFromBlock(Blocks.planks))).setUnlocalizedName(ModInformation.modid + ".drill.wood");
        //GameRegistry.registerItem(woodenDrill, "woodenDrill");
        //stoneDrill = new ItemVanillaDrill(Item.ToolMaterial.STONE, 165, "drill_stone", new ItemStack(Item.getItemFromBlock(Blocks.cobblestone))).setUnlocalizedName(ModInformation.modid + ".drill.stone");
        //GameRegistry.registerItem(stoneDrill, "stoneDrill");
        //ironDrill = new ItemVanillaDrill(Item.ToolMaterial.IRON, 400, "drill_iron", new ItemStack(Items.iron_ingot)).setUnlocalizedName(ModInformation.modid + ".drill.iron");
        //GameRegistry.registerItem(ironDrill, "ironDrill");
        //goldenDrill = new ItemVanillaDrill(Item.ToolMaterial.GOLD, 55, "drill_gold", new ItemStack(Items.gold_ingot)).setUnlocalizedName(ModInformation.modid + ".drill.gold");
        //GameRegistry.registerItem(goldenDrill, "goldenDrill");
        //diamondDrill = new ItemVanillaDrill(Item.ToolMaterial.EMERALD, 2015, "drill_diamond", new ItemStack(Items.diamond)).setUnlocalizedName(ModInformation.modid + ".drill.diamond");
        //GameRegistry.registerItem(diamondDrill, "diamondDrill");
        //copperDrill = new ItemVanillaDrill(copperMaterial, 450, "drill_copper", OreDictionary.getOres("ingotCopper").get(0)).setUnlocalizedName(ModInformation.modid + ".drill.copper");
        //GameRegistry.registerItem(copperDrill, "copperDrill");
        //tinDrill = new ItemVanillaDrill(tinMaterial, 350, "drill_tin", OreDictionary.getOres("ingotTin").get(0)).setUnlocalizedName(ModInformation.modid + ".drill.tin");
        //GameRegistry.registerItem(tinDrill, "tinDrill");
        //leadDrill = new ItemVanillaDrill(leadMaterial, 400, "drill_lead", OreDictionary.getOres("ingotLead").get(0)).setUnlocalizedName(ModInformation.modid + ".drill.lead");
        //GameRegistry.registerItem(leadDrill, "leadDrill");
        //bronzeDrill = new ItemVanillaDrill(bronzeMaterial, 650, "drill_bronze", OreDictionary.getOres("ingotBronze").get(0)).setUnlocalizedName(ModInformation.modid + ".drill.bronze");
        //GameRegistry.registerItem(bronzeDrill, "bronzeDrill");

        //woodenChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.WOOD, 75, "chainsaw_wooden", Item.getItemFromBlock(Blocks.planks)).setUnlocalizedName(ModInformation.modid + ".chainsaw.wood");
        //GameRegistry.registerItem(woodenChainsaw, "woodenChainsaw");
        //stoneChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.STONE, 165, "chainsaw_stone", Item.getItemFromBlock(Blocks.cobblestone)).setUnlocalizedName(ModInformation.modid + ".chainsaw.stone");
        //GameRegistry.registerItem(stoneChainsaw, "stoneChainsaw");
        //ironChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.IRON, 315, "chainsaw_iron", Items.iron_ingot, 3F).setUnlocalizedName(ModInformation.modid + ".chainsaw.iron");
        //GameRegistry.registerItem(ironChainsaw, "ironChainsaw");
        //goldenChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.GOLD, 55, "chainsaw_gold", Items.gold_ingot).setUnlocalizedName(ModInformation.modid + ".chainsaw.gold");
        //GameRegistry.registerItem(goldenChainsaw, "goldenChainsaw");
        //diamondChainsaw = new ItemVanillaChainsaw(Item.ToolMaterial.EMERALD, 2015, "chainsaw_diamond", Items.diamond, 6F).setUnlocalizedName(ModInformation.modid + ".chainsaw.diamond");
        //GameRegistry.registerItem(diamondChainsaw, "diamondChainsaw");
        //copperChainsaw = new ItemVanillaChainsaw(copperMaterial, 450, "chainsaw_copper", OreDictionary.getOres("ingotCopper").get(0).getItem(), 3F).setUnlocalizedName(ModInformation.modid + ".chainsaw.copper");
        //GameRegistry.registerItem(copperChainsaw, "copperChainsaw");
        //tinChainsaw = new ItemVanillaChainsaw(tinMaterial, 350, "chainsaw_tin", OreDictionary.getOres("ingotTin").get(0).getItem(), 5F).setUnlocalizedName(ModInformation.modid + ".chainsaw.tin");
        //GameRegistry.registerItem(tinChainsaw, "tinChainsaw");
        //leadChainsaw = new ItemVanillaChainsaw(leadMaterial, 400, "chainsaw_lead", OreDictionary.getOres("ingotLead").get(0).getItem(), 3.5F).setUnlocalizedName(ModInformation.modid + ".chainsaw.lead");
        //GameRegistry.registerItem(leadChainsaw, "leadChainsaw");
        //bronzeChainsaw = new ItemVanillaChainsaw(bronzeMaterial, 650, "chainsaw_bronze", OreDictionary.getOres("ingotBronze").get(0).getItem(), 4F).setUnlocalizedName(ModInformation.modid + ".chainsaw.bronze");
        //GameRegistry.registerItem(bronzeChainsaw, "bronzeChainsaw");

        //ironOmnitool = new ItemVanillaOmnitool(Item.ToolMaterial.IRON, 500, "omnitool_iron", Items.iron_ingot).setUnlocalizedName(ModInformation.modid + ".omnitool.iron");
        //GameRegistry.registerItem(ironOmnitool, "ironOmnitool");
        //goldenOmnitool = new ItemVanillaOmnitool(Item.ToolMaterial.GOLD, 100, "omnitool_gold", Items.gold_ingot).setUnlocalizedName(ModInformation.modid + ".omnitool.gold");
        //GameRegistry.registerItem(goldenOmnitool, "goldenOmnitool");
        //diamondOmnitool = new ItemVanillaOmnitool(Item.ToolMaterial.EMERALD, 3900, "omnitool_diamond", Items.diamond).setUnlocalizedName(ModInformation.modid + ".omnitool.diamond");
        //GameRegistry.registerItem(diamondOmnitool, "diamondOmnitool");
        //copperOmnitool = new ItemVanillaOmnitool(copperMaterial, 850, "omnitool_copper", OreDictionary.getOres("ingotCopper").get(0).getItem()).setUnlocalizedName(ModInformation.modid + ".omnitool.copper");
        //GameRegistry.registerItem(copperOmnitool, "copperOmnitool");
        //tinOmnitool = new ItemVanillaOmnitool(tinMaterial, 640, "omnitool_tin", OreDictionary.getOres("ingotTin").get(0).getItem()).setUnlocalizedName(ModInformation.modid + ".omnitool.tin");
        //GameRegistry.registerItem(tinOmnitool, "tinOmnitool");
        //leadOmnitool = new ItemVanillaOmnitool(leadMaterial, 700, "omnitool_lead", OreDictionary.getOres("ingotLead").get(0).getItem()).setUnlocalizedName(ModInformation.modid + ".omnitool.lead");
        //GameRegistry.registerItem(leadOmnitool, "leadOmnitool");
        //bronzeOmnitool = new ItemVanillaOmnitool(bronzeMaterial, 950, "omnitool_bronze", OreDictionary.getOres("ingotBronze").get(0).getItem()).setUnlocalizedName(ModInformation.modid + ".omnitool.bronze");
        //GameRegistry.registerItem(bronzeOmnitool, "bronzeOmnitool");
    }
}