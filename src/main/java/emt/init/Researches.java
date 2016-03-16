package emt.init;

import emt.ModInformation;
import emt.client.EMTKeys;
import emt.util.EMTConfigHandler;
import emt.util.EMTResearchAspects;
import emt.util.EMTResearchItem;
import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class Researches {
	public static void register() {
		addResearchTab();
		addResearch();
	}

	public static void addResearchTab() {
		ResourceLocation background = new ResourceLocation(ModInformation.texturePath, "textures/misc/background.png");
		ResearchCategories.registerCategory("EMT", new ResourceLocation("electromagictools:textures/misc/emt.png"), background);
	}

	public static void addResearch() {
		/** Research that can't be disabled **/
		new EMTResearchItem("Electric Magic Tools", 0, 0, 0, new ResourceLocation("electromagictools:textures/misc/emt.png")).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""));
		new EMTResearchItem("Diamond Chainsaw", 5, -6, 0, new ItemStack(ItemRegistry.diamondChainsaw)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.diamondChainsaw));
		new EMTResearchItem("Thaumium Plate", 6, -6, 0, new ItemStack(ItemRegistry.itemEMTItems, 1, 5)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumiumPlate));
		new EMTResearchItem("Macerating Native Ore Clusters", 5, -7, 0, IC2Items.getItem("smallGoldDust")).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""));
		new EMTResearchItem("The Legend", 5, -5, 0, new ItemStack(ItemRegistry.taintedThorHammer)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""));
		new EMTResearchItem("Lightning Summoner", 6, -5, 0, new ItemStack(ItemRegistry.itemEMTItems, 1, 6)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""));
		new EMTResearchItem("The One Ring", 6, -7, 0, new ItemStack(ItemRegistry.onering)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""));
		new EMTResearchItem("Electric Cloud", 4, -7, 0, new ItemStack(BlockRegistry.electricCloud)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage("2"));
		new EMTResearchItem("Feather Wings", 0, 2, 0, new ItemStack(ItemRegistry.featherWing)).setParents("Electric Magic Tools").setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(RecipeRegistry.cardboard), new ResearchPage(RecipeRegistry.featherWing), new ResearchPage(RecipeRegistry.featherWings));

		/** Research that can be disabled **/
		if (!EMTConfigHandler.thaumiumDrillResearch) {
			new EMTResearchItem("Thaumium Drill", EMTResearchAspects.thaumiumDrillResearch, -1, -2, 1, new ItemStack(ItemRegistry.thaumiumDrill)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumiumDrill));
		}

		if (!EMTConfigHandler.thaumiumChainsawResearch) {
			new EMTResearchItem("Thaumium Chainsaw", EMTResearchAspects.thaumiumChainsawResearch, -2, -2, 1, new ItemStack(ItemRegistry.thaumiumChainsaw)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumiumChainsaw));
		}

		if (!EMTConfigHandler.electricGooglesResearch) {
			new EMTResearchItem("Electric Goggles", EMTResearchAspects.electricGogglesResearch, 1, -2, 1, new ItemStack(ItemRegistry.electricGoggles)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.electricGoggles));
		}

		if (!EMTConfigHandler.electricGooglesResearch) {
			if (!EMTConfigHandler.nanoGooglesResearch) {
				;
				new EMTResearchItem("Nanosuit Goggles of Revealing", EMTResearchAspects.thaumicNanoHelmet, 2, -2, 2, new ItemStack(ItemRegistry.nanoThaumicHelmet)).setParents("Electric Goggles").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumicNanoHelmet));
			}
		}

		if (!EMTConfigHandler.electricGooglesResearch) {
			if (!EMTConfigHandler.nanoGooglesResearch) {
				if (!EMTConfigHandler.quantumGooglesResearch) {
					new EMTResearchItem("Quantum Goggles of Revealing", EMTResearchAspects.thaumicQuantumHelmet, 3, -2, 3, new ItemStack(ItemRegistry.quantumThaumicHelmet)).setParents("Nanosuit Goggles of Revealing").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumicQuantumHelmet));
				}
			}
		}

		if (!EMTConfigHandler.ironOmnitoolResearch) {
			new EMTResearchItem("Iron Omnitool", -2, 0, 0, new ItemStack(ItemRegistry.ironOmnitool)).setParents("Electric Magic Tools").setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.ironOmnitool));
		}

		if (!EMTConfigHandler.ironOmnitoolResearch) {
			if (!EMTConfigHandler.diamondOmnitoolResearch) {
				new EMTResearchItem("Diamond Omnitool", EMTResearchAspects.diamondOmnitoolResearch, -4, 0, 1, new ItemStack(ItemRegistry.diamondOmnitool)).setSecondary().setParents("Iron Omnitool").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.diamondOmnitool), new ResearchPage(RecipeRegistry.ironOmnitoolToDiamond));
			}
		}

		if (!EMTConfigHandler.thaumiumDrillResearch && !EMTConfigHandler.thaumiumChainsawResearch) {
			if (!EMTConfigHandler.thaumiumOmnitoolResearch) {
				new EMTResearchItem("Thaumium Omnitool", EMTResearchAspects.thaumiumOmnitoolResearch, -6, 0, 2, new ItemStack(ItemRegistry.thaumiumOmnitool)).setParentsHidden("Thaumium Drill", "Thaumium Chainsaw").setParents("Diamond Omnitool").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumiumOmnitool),
						new ResearchPage(RecipeRegistry.diamondOmnitoolToThaumium));
			}
		}

		if (!EMTConfigHandler.explosionFocusResearch) {
			new EMTResearchItem("Explosion Focus", EMTResearchAspects.laserFocusResearch, 2, 3, 2, new ItemStack(ItemRegistry.explosionFocus)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.explosionFocus));
		}

		if (!EMTConfigHandler.christmasFocusResearch) {
			new EMTResearchItem("Kris-tmas Focus", EMTResearchAspects.christmasFocusResearch, 3, 3, 1, new ItemStack(ItemRegistry.christmasFocus)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.christmasFocus));
		}

		if (!EMTConfigHandler.shieldFocusResearch) {
			new EMTResearchItem("Shield Focus", EMTResearchAspects.shieldFocusResearch, 4, 3, 2, new ItemStack(ItemRegistry.shieldFocus)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.shieldFocus));
		}

		if (!EMTConfigHandler.shieldFocusResearch) {
			if (!EMTConfigHandler.shieldBlockResearch) {
				new EMTResearchItem("Shield Block", EMTResearchAspects.shieldBlockResearch, 4, 5, 1, new ItemStack(BlockRegistry.shield)).setSecondary().setParents("Shield Focus").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.shieldBlock));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			new EMTResearchItem("Potentia Generator", EMTResearchAspects.potentiaGeneratorResearch, -2, 3, 3, new ItemStack(BlockRegistry.essentiaGens, 1, 0)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.potentiaGenerator));
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.ignisGeneratorResearch) {
				new EMTResearchItem("Ignis Generator", EMTResearchAspects.ignisGeneratorResearch, -3, 4, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 1)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.ignisGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.auramGeneratorResearch) {
				new EMTResearchItem("Auram Generator", EMTResearchAspects.auramGeneratorResearch, -1, 4, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 2)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.auramGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.arborGeneratorResearch) {
				new EMTResearchItem("Arbor Generator", EMTResearchAspects.arborGeneratorResearch, -2, 5, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 3)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.arborGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.aerGeneratorResearch) {
				new EMTResearchItem("Aer Generator", EMTResearchAspects.aerGenerator, -2, 6, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 4)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.aerGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.wandChargingSationResearch) {
				new EMTResearchItem("Industrial Wand Charging Station", EMTResearchAspects.wandCharger, -4, 2, 3, new ItemStack(BlockRegistry.emtMachines, 1, 0)).setParents("Potentia Generator").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.wandRecharger));
			}
		}

		if (!EMTConfigHandler.thaumiumChainsawResearch) {
			if (!EMTConfigHandler.streamChainsawResearch) {
				new EMTResearchItem("Chainsaw of the Stream", EMTResearchAspects.streamChainsawResearch, -3, -4, 3, new ItemStack(ItemRegistry.streamChainsaw)).setParents("Thaumium Chainsaw").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.streamChainsaw));
			}
		}

		if (!EMTConfigHandler.thaumiumDrillResearch) {
			if (!EMTConfigHandler.rockbreakerDrillResearch) {
				new EMTResearchItem("Drill of the Rockbreaker", EMTResearchAspects.rockbreakerDrillResearch, 0, -4, 3, new ItemStack(ItemRegistry.rockbreakerDrill)).setParents("Thaumium Drill").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.rockbreakerDrill));
			}
		}

		if (!EMTConfigHandler.tinyUraniumResearch) {
			new EMTResearchItem("Tiny Uranium", EMTResearchAspects.tinyUraniumResearch, -5, -5, 1, IC2Items.getItem("Uran238")).setSecondary().setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.tinyUranium));
		}

		if (!EMTConfigHandler.thorHammerResearch) {
			new EMTResearchItem("Mjolnir", EMTResearchAspects.thorHammerResearch, -5, 5, 3, new ItemStack(ItemRegistry.thorHammer)).setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thorHammer));
		}

		if (!EMTConfigHandler.thorHammerResearch) {
			if (!EMTConfigHandler.superchargedThorHammerResearch) {
				new EMTResearchItem("Supercharged Mjolnir", EMTResearchAspects.superchargedThorHammerResearch, -6, 6, 3, new ItemStack(ItemRegistry.electricThorHammer)).setParents("Mjolnir").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.superchargedThorHammer));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			new EMTResearchItem("Compressed Solars", EMTResearchAspects.compressedSolars, -4, -5, 2, new ItemStack(BlockRegistry.emtSolars)).setParentsHidden("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.compressedSolar), new ResearchPage(RecipeRegistry.doubleCompressedSolar),
					new ResearchPage(RecipeRegistry.tripleCompressedSolar));
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.solarHelmetResearch) {
				new EMTResearchItem("Solar Helmet of Revealing", EMTResearchAspects.solarHelmetRevealing, -2, -5, 1, new ItemStack(ItemRegistry.solarHelmetRevealing)).setSecondary().setParents("Compressed Solars").setParentsHidden("Quantum Goggles of Revealing").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.solarHelmetRevealing));
			}
		}

		if (!EMTConfigHandler.electricBootsResearch) {
			new EMTResearchItem("Electric Boots of the Traveller", EMTResearchAspects.electricBootsTravel, 2, 0, 1, new ItemStack(ItemRegistry.electricBootsTraveller)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.electricBootsTravel));
		}

		if (!EMTConfigHandler.electricBootsResearch) {
			if (!EMTConfigHandler.nanoBootsResearch) {
				new EMTResearchItem("Nano Boots of the Traveller", EMTResearchAspects.nanoBootsTravel, 4, 0, 2, new ItemStack(ItemRegistry.nanoBootsTraveller)).setParents("Electric Boots of the Traveller").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.nanoBootsTravel));
			}
		}

		if (!EMTConfigHandler.electricBootsResearch) {
			if (!EMTConfigHandler.nanoBootsResearch) {
				if (!EMTConfigHandler.quantumBootsResearch) {
					new EMTResearchItem("Quantum Boots of the Traveller", EMTResearchAspects.quantumBootsTravel, 6, 0, 3, new ItemStack(ItemRegistry.quantumBootsTraveller)).setParents("Nano Boots of the Traveller").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.quantumBootsTravel));
				}
			}
		}

		if (!EMTConfigHandler.electricScribingToolsResearch) {
			new EMTResearchItem("Electric Scribing Tools", EMTResearchAspects.electricScribingTools, -6, -5, 1, new ItemStack(ItemRegistry.electricScribingTools)).setSecondary().setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.electricScribingTools));
		}

		if (!EMTConfigHandler.etherealProcessorResearch) {
			new EMTResearchItem("Etheral Processor", EMTResearchAspects.etherealProcessor, -1, 2, 2, new ItemStack(BlockRegistry.emtMachines, 1, 1)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.etheralProcessor));
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.waterSolarsResearch) {
				new EMTResearchItem("Water Infused Solar Panels", EMTResearchAspects.waterSolars, -7, -7, 1, new ItemStack(BlockRegistry.emtSolars, 1, 3)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.waterSolar), new ResearchPage(RecipeRegistry.doubleWaterSolar),
						new ResearchPage(RecipeRegistry.tripleWaterSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.darkSolarsResearch) {
				new EMTResearchItem("Entropy Infused Solar Panels", EMTResearchAspects.darkSolars, -8, -6, 1, new ItemStack(BlockRegistry.emtSolars, 1, 6)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.darkSolar), new ResearchPage(RecipeRegistry.doubleDarkSolar),
						new ResearchPage(RecipeRegistry.tripleDarkSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.orderSolarsResearch) {
				new EMTResearchItem("Order Infused Solar Panels", EMTResearchAspects.orderSolars, -6, -8, 1, new ItemStack(BlockRegistry.emtSolars, 1, 9)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.orderSolar), new ResearchPage(RecipeRegistry.doubleOrderSolar),
						new ResearchPage(RecipeRegistry.tripleOrderSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.fireSolarsResearch) {
				new EMTResearchItem("Fire Infused Solar Panels", EMTResearchAspects.fireSolars, -5, -7, 1, new ItemStack(BlockRegistry.emtSolars, 1, 12)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.fireSolar), new ResearchPage(RecipeRegistry.doubleFireSolar),
						new ResearchPage(RecipeRegistry.tripleFireSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.airSolarsResearch) {
				new EMTResearchItem("Air Infused Solar Panels", EMTResearchAspects.airSolars, -4, -6, 1, new ItemStack(BlockRegistry.emtSolars, 1, 15)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.airSolar), new ResearchPage(RecipeRegistry.doubleAirSolar), new ResearchPage(RecipeRegistry.tripleAirSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.earthSolarsResearch) {
				new EMTResearchItem("Earth Infused Solar Panels", EMTResearchAspects.earthSolars, -6, -7, 1, new ItemStack(BlockRegistry.emtSolars2, 1, 2)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.earthSolar), new ResearchPage(RecipeRegistry.doubleEarthSolar),
						new ResearchPage(RecipeRegistry.tripleEarthSolar));
			}
		}

		if (!EMTConfigHandler.uuMInfusionResearch) {
			new EMTResearchItem("UU-Matter Infusion", EMTResearchAspects.uuMInfusion, 5, 5, 3, new ItemStack(ItemRegistry.itemEMTItems, 1, 15)).setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.uuMCrystal), new ResearchPage(RecipeRegistry.charcoalToCoal),
					new ResearchPage(RecipeRegistry.glowstoneDustToBlock), new ResearchPage(RecipeRegistry.stoneBricksToIronOre), new ResearchPage(RecipeRegistry.arcaneStoneToCopperOre), new ResearchPage(RecipeRegistry.arcaneStoneBricksToTinOre), new ResearchPage(RecipeRegistry.amberBlockToLeadOre), new ResearchPage(RecipeRegistry.amberBricksToUraniumOre),
					new ResearchPage(RecipeRegistry.shardToResin), new ResearchPage(RecipeRegistry.shardToRedstone), new ResearchPage(RecipeRegistry.shardToLapis), new ResearchPage(RecipeRegistry.ironToGold), new ResearchPage(RecipeRegistry.goldToDiamond), new ResearchPage(RecipeRegistry.diamondToUranium), new ResearchPage(RecipeRegistry.uraniumToIridium));
		}

		if (!EMTConfigHandler.portableNodeResarch) {
			new EMTResearchItem("Portable Node", EMTResearchAspects.portableNode, 2, 5, 3, new ItemStack(BlockRegistry.portableNode)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.portableNode));
		}

		if (!EMTConfigHandler.electricHoeGrowthResearch) {
			new EMTResearchItem("Electric Hoe of Growth", EMTResearchAspects.electricHoeGrowth, -1, -4, 2, new ItemStack(ItemRegistry.electricHoeGrowth)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.electricHoeGrowth));
		}

		if (!EMTConfigHandler.chargeFocusResearch) {
			new EMTResearchItem("Wand Focus: Charging", EMTResearchAspects.chargeFocus, 5, 3, 2, new ItemStack(ItemRegistry.chargeFocus)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.chargeFocus));
		}

		if (!EMTConfigHandler.wandChargeFocusResearch) {
			new EMTResearchItem("Wand Focus: Wand Charging", EMTResearchAspects.wandChargeFocus, 7, 3, 3, new ItemStack(ItemRegistry.wandChargeFocus)).setParents("Wand Focus: Charging").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.wandChargeFocus));
		}

		if (!EMTConfigHandler.inventoryChargingRing) {
			new EMTResearchItem("Inventory Charging Ring", EMTResearchAspects.inventoryChargingRing, 0, -5, 3, new ItemStack(ItemRegistry.emtBauble, 1, 1)).setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.inventoryChargingRing));
		}

		if (!EMTConfigHandler.armorChargingRing) {
			new EMTResearchItem("Armor Charging Ring", EMTResearchAspects.armorChargingRing, 0, -7, 3, new ItemStack(ItemRegistry.emtBauble, 1, 0)).setSecondary().setParents("Inventory Charging Ring").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.armorChargingRing));
		}

		if (!EMTConfigHandler.thaumiumWingResearch) {
			new EMTResearchItem("Thaumium Reinforced Wings", EMTResearchAspects.thaumiumWing, 0, 4, 0, new ItemStack(ItemRegistry.thaumiumWing)).setSecondary().setParents("Feather Wings").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.thaumiumWing), new ResearchPage(RecipeRegistry.thaumiumWings));
		}

		if (!EMTConfigHandler.nanoWingResearch && !EMTConfigHandler.thaumiumWingResearch) {
			new EMTResearchItem("Nanosuit Wings", EMTResearchAspects.nanoWing, 0, 6, 2, new ItemStack(ItemRegistry.nanoWing)).setParents("Thaumium Reinforced Wings").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.nanoWings));
		}

		if (!EMTConfigHandler.quantumWingsResearch && !EMTConfigHandler.thaumiumWingResearch && !EMTConfigHandler.nanoWingResearch) {
			new EMTResearchItem("Quantum Wings", EMTResearchAspects.quantumWing, 0, 8, 3, new ItemStack(ItemRegistry.quantumWing)).setParents("Nanosuit Wings").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.quantumWings));
		}

		if (!EMTConfigHandler.infusedQuantumChestplate && !EMTConfigHandler.thaumiumWingResearch && !EMTConfigHandler.nanoWingResearch && !EMTConfigHandler.quantumWingsResearch) {
			new EMTResearchItem("Infused Quantum Armor", EMTResearchAspects.quantumWing, 0, 10, 4, new ItemStack(ItemRegistry.quantumArmor)).setParents("Quantum Wings").setConcealed().registerResearchItem().setPages(new ResearchPage(""), new ResearchPage(RecipeRegistry.infusedQuantumArmor));
		}
	}
}
