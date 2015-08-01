package tombenpotter.emt.common.items;

import ic2.core.init.InternalName;
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
import tombenpotter.emt.common.items.armor.ItemEMTQuantum;
import tombenpotter.emt.common.items.armor.wings.ItemFeatherWing;
import tombenpotter.emt.common.items.armor.wings.ItemThaumiumReinforcedWing;
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
	
    public static Item onering;
    public static Item materials;
    public static Item craftingComponents;
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
    public static Item quantumArmor;
    public static ItemArmor.ArmorMaterial featherWingMaterial = EnumHelper.addArmorMaterial("FEATEHRWiNG", 1, new int[]{2, 2, 2, 2}, 6);

    public static void registerItems() {
        itemEMTItems = new ItemMaterials().setUnlocalizedName(ModInformation.modid + ".emtitems");
        GameRegistry.registerItem(itemEMTItems, "EMTItems");
        
        emtBauble = new ItemIC2Baubles().setUnlocalizedName(ModInformation.modid + ".emtbauble");
       	GameRegistry.registerItem(emtBauble, "EMTBaubles");
        	
        onering = new ItemOneRing();
        GameRegistry.registerItem(onering, "BaseBaubles");
  
        quantumArmor = new ItemEMTQuantum(InternalName.itemArmorQuantumChestplate, 1).setUnlocalizedName(ModInformation.modid + ".quantum");
        
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
    }
}