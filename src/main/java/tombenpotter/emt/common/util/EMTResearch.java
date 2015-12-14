package tombenpotter.emt.common.util;

import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.client.EMTKeys;
import tombenpotter.emt.common.init.BlockRegistry;
import tombenpotter.emt.common.init.ItemRegistry;
import tombenpotter.emt.common.init.RecipeRegistry;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTResearchItem;
import tombenpotter.emt.common.util.EMTResearchAspects;

public class EMTResearch {
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
		new EMTResearchItem("Electric Magic Tools", "EMT", new AspectList(), 0, 0, 0, new ResourceLocation("electromagictools:textures/misc/emt.png")).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"));

		new EMTResearchItem("Diamond Chainsaw", "EMT", new AspectList(), 5, -6, 0, new ItemStack(ItemRegistry.diamondChainsaw)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.diamondChainsaw));

		new EMTResearchItem("Thaumium Plate", "EMT", new AspectList(), 6, -6, 0, new ItemStack(ItemRegistry.itemEMTItems, 1, 5)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumiumPlate));

		new EMTResearchItem("Macerating Native Ore Clusters", "EMT", new AspectList(), 5, -7, 0, IC2Items.getItem("smallGoldDust")).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"));

		new EMTResearchItem("The Legend", "EMT", new AspectList(), 5, -5, 0, new ItemStack(ItemRegistry.taintedThorHammer)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"));

		new EMTResearchItem("Lightning Summoner", "EMT", new AspectList(), 6, -5, 0, new ItemStack(ItemRegistry.itemEMTItems, 1, 6)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"));

		new EMTResearchItem("The One Ring", "EMT", new AspectList(), 6, -7, 0, new ItemStack(ItemRegistry.onering)).setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"));

		new EMTResearchItem("Feather Wings", "EMT", new AspectList(), 0, 2, 0, new ItemStack(ItemRegistry.featherWing)).setParents("Electric Magic Tools").setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage(RecipeRegistry.cardboard), new ResearchPage(RecipeRegistry.featherWing), new ResearchPage(RecipeRegistry.featherWings));

		/** Research that can be disabled **/
		if (!EMTConfigHandler.thaumiumDrillResearch) {
			new EMTResearchItem("Thaumium Drill", "EMT", EMTResearchAspects.thaumiumDrillResearch, -1, -2, 1, new ItemStack(ItemRegistry.thaumiumDrill)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumiumDrill));
		}

		if (!EMTConfigHandler.thaumiumChainsawResearch) {
			new EMTResearchItem("Thaumium Chainsaw", "EMT", EMTResearchAspects.thaumiumChainsawResearch, -2, -2, 1, new ItemStack(ItemRegistry.thaumiumChainsaw)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumiumChainsaw));
		}

		if (!EMTConfigHandler.electricGooglesResearch) {
			new EMTResearchItem("Electric Goggles", "EMT", EMTResearchAspects.electricGogglesResearch, 1, -2, 1, new ItemStack(ItemRegistry.electricGoggles)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.electricGoggles));
		}

		if (!EMTConfigHandler.electricGooglesResearch) {
			if (!EMTConfigHandler.nanoGooglesResearch) {
				;
				new EMTResearchItem("Nanosuit Goggles of Revealing", "EMT", EMTResearchAspects.thaumicNanoHelmet, 2, -2, 2, new ItemStack(ItemRegistry.nanoThaumicHelmet)).setParents("Electric Goggles").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumicNanoHelmet));
			}
		}

		if (!EMTConfigHandler.electricGooglesResearch) {
			if (!EMTConfigHandler.nanoGooglesResearch) {
				if (!EMTConfigHandler.quantumGooglesResearch) {
					new EMTResearchItem("Quantum Goggles of Revealing", "EMT", EMTResearchAspects.thaumicQuantumHelmet, 3, -2, 3, new ItemStack(ItemRegistry.quantumThaumicHelmet)).setParents("Nanosuit Goggles of Revealing").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumicQuantumHelmet));
				}
			}
		}

		if (!EMTConfigHandler.ironOmnitoolResearch) {
			new EMTResearchItem("Iron Omnitool", "EMT", new AspectList(), -2, 0, 0, new ItemStack(ItemRegistry.ironOmnitool)).setParents("Electric Magic Tools").setRound().setAutoUnlock().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.ironOmnitool));
		}

		if (!EMTConfigHandler.ironOmnitoolResearch) {
			if (!EMTConfigHandler.diamondOmnitoolResearch) {
				new EMTResearchItem("Diamond Omnitool", "EMT", EMTResearchAspects.diamondOmnitoolResearch, -4, 0, 1, new ItemStack(ItemRegistry.diamondOmnitool)).setSecondary().setParents("Iron Omnitool").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.diamondOmnitool), new ResearchPage(RecipeRegistry.ironOmnitoolToDiamond));
			}
		}

		if (!EMTConfigHandler.thaumiumDrillResearch && !EMTConfigHandler.thaumiumChainsawResearch) {
			if (!EMTConfigHandler.thaumiumOmnitoolResearch) {
				new EMTResearchItem("Thaumium Omnitool", "EMT", EMTResearchAspects.thaumiumOmnitoolResearch, -6, 0, 2, new ItemStack(ItemRegistry.thaumiumOmnitool)).setParentsHidden("Thaumium Drill", "Thaumium Chainsaw").setParents("Diamond Omnitool").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumiumOmnitool),
						new ResearchPage(RecipeRegistry.diamondOmnitoolToThaumium));
			}
		}

		if (!EMTConfigHandler.explosionFocusResearch) {
			new EMTResearchItem("Explosion Focus", "EMT", EMTResearchAspects.laserFocusResearch, 2, 3, 2, new ItemStack(ItemRegistry.explosionFocus)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.explosionFocus));
		}

		if (!EMTConfigHandler.christmasFocusResearch) {
			new EMTResearchItem("Kris-tmas Focus", "EMT", EMTResearchAspects.christmasFocusResearch, 3, 3, 1, new ItemStack(ItemRegistry.christmasFocus)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.christmasFocus));
		}

		if (!EMTConfigHandler.shieldFocusResearch) {
			new EMTResearchItem("Shield Focus", "EMT", EMTResearchAspects.shieldFocusResearch, 4, 3, 2, new ItemStack(ItemRegistry.shieldFocus)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.shieldFocus));
		}

		if (!EMTConfigHandler.shieldFocusResearch) {
			if (!EMTConfigHandler.shieldBlockResearch) {
				new EMTResearchItem("Shield Block", "EMT", EMTResearchAspects.shieldBlockResearch, 4, 5, 1, new ItemStack(BlockRegistry.shield)).setSecondary().setParents("Shield Focus").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.shieldBlock));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			new EMTResearchItem("Potentia Generator", "EMT", EMTResearchAspects.potentiaGeneratorResearch, -2, 3, 3, new ItemStack(BlockRegistry.essentiaGens, 1, 0)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.potentiaGenerator));
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.ignisGeneratorResearch) {
				new EMTResearchItem("Ignis Generator", "EMT", EMTResearchAspects.ignisGeneratorResearch, -3, 4, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 1)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.ignisGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.auramGeneratorResearch) {
				new EMTResearchItem("Auram Generator", "EMT", EMTResearchAspects.auramGeneratorResearch, -1, 4, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 2)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.auramGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.arborGeneratorResearch) {
				new EMTResearchItem("Arbor Generator", "EMT", EMTResearchAspects.arborGeneratorResearch, -2, 5, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 3)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.arborGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.aerGeneratorResearch) {
				new EMTResearchItem("Aer Generator", "EMT", EMTResearchAspects.aerGenerator, -2, 6, 1, new ItemStack(BlockRegistry.essentiaGens, 1, 4)).setSecondary().setParents("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.aerGenerator));
			}
		}

		if (!EMTConfigHandler.potentiaGeneratorResearch) {
			if (!EMTConfigHandler.wandChargingSationResearch) {
				new EMTResearchItem("Industrial Wand Charging Station", "EMT", EMTResearchAspects.wandCharger, -4, 2, 3, new ItemStack(BlockRegistry.emtMachines, 1, 0)).setParents("Potentia Generator").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.wandRecharger));
			}
		}

		if (!EMTConfigHandler.thaumiumChainsawResearch) {
			if (!EMTConfigHandler.streamChainsawResearch) {
				new EMTResearchItem("Chainsaw of the Stream", "EMT", EMTResearchAspects.streamChainsawResearch, -3, -4, 3, new ItemStack(ItemRegistry.streamChainsaw)).setParents("Thaumium Chainsaw").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.streamChainsaw));
			}
		}

		if (!EMTConfigHandler.thaumiumDrillResearch) {
			if (!EMTConfigHandler.rockbreakerDrillResearch) {
				new EMTResearchItem("Drill of the Rockbreaker", "EMT", EMTResearchAspects.rockbreakerDrillResearch, 0, -4, 3, new ItemStack(ItemRegistry.rockbreakerDrill)).setParents("Thaumium Drill").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.rockbreakerDrill));
			}
		}

		if (!EMTConfigHandler.tinyUraniumResearch) {
			new EMTResearchItem("Tiny Uranium", "EMT", EMTResearchAspects.tinyUraniumResearch, -5, -5, 1, IC2Items.getItem("Uran238")).setSecondary().setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.tinyUranium));
		}

		if (!EMTConfigHandler.thorHammerResearch) {
			new EMTResearchItem("Mjolnir", "EMT", EMTResearchAspects.thorHammerResearch, -5, 5, 3, new ItemStack(ItemRegistry.thorHammer)).setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thorHammer));
		}

		if (!EMTConfigHandler.thorHammerResearch) {
			if (!EMTConfigHandler.superchargedThorHammerResearch) {
				new EMTResearchItem("Supercharged Mjolnir", "EMT", EMTResearchAspects.superchargedThorHammerResearch, -6, 6, 3, new ItemStack(ItemRegistry.electricThorHammer)).setParents("Mjolnir").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.superchargedThorHammer));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			new EMTResearchItem("Compressed Solars", "EMT", EMTResearchAspects.compressedSolars, -4, -5, 2, new ItemStack(BlockRegistry.emtSolars)).setParentsHidden("Potentia Generator").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.compressedSolar), new ResearchPage(RecipeRegistry.doubleCompressedSolar),
					new ResearchPage(RecipeRegistry.tripleCompressedSolar));
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.solarHelmetResearch) {
				new EMTResearchItem("Solar Helmet of Revealing", "EMT", EMTResearchAspects.solarHelmetRevealing, -2, -5, 1, new ItemStack(ItemRegistry.solarHelmetRevealing)).setSecondary().setParents("Compressed Solars").setParentsHidden("Quantum Goggles of Revealing").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.solarHelmetRevealing));
			}
		}

		if (!EMTConfigHandler.electricBootsResearch) {
			new EMTResearchItem("Electric Boots of the Traveller", "EMT", EMTResearchAspects.electricBootsTravel, 2, 0, 1, new ItemStack(ItemRegistry.electricBootsTraveller)).setSecondary().setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.electricBootsTravel));
		}

		if (!EMTConfigHandler.electricBootsResearch) {
			if (!EMTConfigHandler.nanoBootsResearch) {
				new EMTResearchItem("Nano Boots of the Traveller", "EMT", EMTResearchAspects.nanoBootsTravel, 4, 0, 2, new ItemStack(ItemRegistry.nanoBootsTraveller)).setParents("Electric Boots of the Traveller").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.nanoBootsTravel));
			}
		}

		if (!EMTConfigHandler.electricBootsResearch) {
			if (!EMTConfigHandler.nanoBootsResearch) {
				if (!EMTConfigHandler.quantumBootsResearch) {
					new EMTResearchItem("Quantum Boots of the Traveller", "EMT", EMTResearchAspects.quantumBootsTravel, 6, 0, 3, new ItemStack(ItemRegistry.quantumBootsTraveller)).setParents("Nano Boots of the Traveller").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.quantumBootsTravel));
				}
			}
		}

		if (!EMTConfigHandler.electricScribingToolsResearch) {
			new EMTResearchItem("Electric Scribing Tools", "EMT", EMTResearchAspects.electricScribingTools, -6, -5, 1, new ItemStack(ItemRegistry.electricScribingTools)).setSecondary().setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.electricScribingTools));
		}

		if (!EMTConfigHandler.etherealProcessorResearch) {
			new EMTResearchItem("Etheral Processor", "EMT", EMTResearchAspects.etherealProcessor, -1, 2, 2, new ItemStack(BlockRegistry.emtMachines, 1, 1)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.etheralProcessor));
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.waterSolarsResearch) {
				new EMTResearchItem("Water Infused Solar Panels", "EMT", EMTResearchAspects.waterSolars, -7, -7, 1, new ItemStack(BlockRegistry.emtSolars, 1, 3)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.waterSolar), new ResearchPage(RecipeRegistry.doubleWaterSolar),
						new ResearchPage(RecipeRegistry.tripleWaterSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.darkSolarsResearch) {
				new EMTResearchItem("Entropy Infused Solar Panels", "EMT", EMTResearchAspects.darkSolars, -8, -6, 1, new ItemStack(BlockRegistry.emtSolars, 1, 6)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.darkSolar), new ResearchPage(RecipeRegistry.doubleDarkSolar),
						new ResearchPage(RecipeRegistry.tripleDarkSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.orderSolarsResearch) {
				new EMTResearchItem("Order Infused Solar Panels", "EMT", EMTResearchAspects.orderSolars, -6, -8, 1, new ItemStack(BlockRegistry.emtSolars, 1, 9)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.orderSolar), new ResearchPage(RecipeRegistry.doubleOrderSolar),
						new ResearchPage(RecipeRegistry.tripleOrderSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.fireSolarsResearch) {
				new EMTResearchItem("Fire Infused Solar Panels", "EMT", EMTResearchAspects.fireSolars, -5, -7, 1, new ItemStack(BlockRegistry.emtSolars, 1, 12)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.fireSolar), new ResearchPage(RecipeRegistry.doubleFireSolar),
						new ResearchPage(RecipeRegistry.tripleFireSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.airSolarsResearch) {
				new EMTResearchItem("Air Infused Solar Panels", "EMT", EMTResearchAspects.airSolars, -4, -6, 1, new ItemStack(BlockRegistry.emtSolars, 1, 15)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.airSolar), new ResearchPage(RecipeRegistry.doubleAirSolar), new ResearchPage(RecipeRegistry.tripleAirSolar));
			}
		}

		if (!EMTConfigHandler.compressedSolarsResearch) {
			if (!EMTConfigHandler.earthSolarsResearch) {
				new EMTResearchItem("Earth Infused Solar Panels", "EMT", EMTResearchAspects.earthSolars, -6, -7, 1, new ItemStack(BlockRegistry.emtSolars2, 1, 2)).setSecondary().setParents("Compressed Solars").registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.earthSolar), new ResearchPage(RecipeRegistry.doubleEarthSolar),
						new ResearchPage(RecipeRegistry.tripleEarthSolar));
			}
		}

		if (!EMTConfigHandler.uuMInfusionResearch) {
			new EMTResearchItem("UU-Matter Infusion", "EMT", EMTResearchAspects.uuMInfusion, 5, 5, 3, new ItemStack(ItemRegistry.itemEMTItems, 1, 15)).setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.uuMCrystal), new ResearchPage(RecipeRegistry.charcoalToCoal),
					new ResearchPage(RecipeRegistry.glowstoneDustToBlock), new ResearchPage(RecipeRegistry.stoneBricksToIronOre), new ResearchPage(RecipeRegistry.arcaneStoneToCopperOre), new ResearchPage(RecipeRegistry.arcaneStoneBricksToTinOre), new ResearchPage(RecipeRegistry.amberBlockToLeadOre), new ResearchPage(RecipeRegistry.amberBricksToUraniumOre),
					new ResearchPage(RecipeRegistry.shardToResin), new ResearchPage(RecipeRegistry.shardToRedstone), new ResearchPage(RecipeRegistry.shardToLapis), new ResearchPage(RecipeRegistry.ironToGold), new ResearchPage(RecipeRegistry.goldToDiamond), new ResearchPage(RecipeRegistry.diamondToUranium), new ResearchPage(RecipeRegistry.uraniumToIridium));
		}

		if (!EMTConfigHandler.portableNodeResarch) {
			new EMTResearchItem("Portable Node", "EMT", EMTResearchAspects.portableNode, 2, 5, 3, new ItemStack(BlockRegistry.portableNode)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.portableNode));
		}

		if (!EMTConfigHandler.electricHoeGrowthResearch) {
			new EMTResearchItem("Electric Hoe of Growth", "EMT", EMTResearchAspects.electricHoeGrowth, -1, -4, 2, new ItemStack(ItemRegistry.electricHoeGrowth)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.electricHoeGrowth));
		}

		if (!EMTConfigHandler.chargeFocusResearch) {
			new EMTResearchItem("Wand Focus: Charging", "EMT", EMTResearchAspects.chargeFocus, 5, 3, 2, new ItemStack(ItemRegistry.chargeFocus)).setParents("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.chargeFocus));
		}

		if (!EMTConfigHandler.wandChargeFocusResearch) {
			new EMTResearchItem("Wand Focus: Wand Charging", "EMT", EMTResearchAspects.wandChargeFocus, 7, 3, 3, new ItemStack(ItemRegistry.wandChargeFocus)).setParents("Wand Focus: Charging").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.wandChargeFocus));
		}

		if (!EMTConfigHandler.inventoryChargingRing) {
			new EMTResearchItem("Inventory Charging Ring", "EMT", EMTResearchAspects.inventoryChargingRing, 0, -5, 3, new ItemStack(ItemRegistry.emtBauble, 1, 1)).setParentsHidden("Electric Magic Tools").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.inventoryChargingRing));
		}

		if (!EMTConfigHandler.armorChargingRing) {
			new EMTResearchItem("Armor Charging Ring", "EMT", EMTResearchAspects.armorChargingRing, 0, -7, 3, new ItemStack(ItemRegistry.emtBauble, 1, 0)).setSecondary().setParents("Inventory Charging Ring").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.armorChargingRing));
		}

		if (!EMTConfigHandler.thaumiumWingResearch) {
			new EMTResearchItem("Thaumium Reinforced Wings", "EMT", EMTResearchAspects.thaumiumWing, 0, 4, 0, new ItemStack(ItemRegistry.thaumiumWing)).setSecondary().setParents("Feather Wings").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.thaumiumWing), new ResearchPage(RecipeRegistry.thaumiumWings));
		}

		if (!EMTConfigHandler.nanoWingResearch && !EMTConfigHandler.thaumiumWingResearch) {
			new EMTResearchItem("Nanosuit Wings", "EMT", EMTResearchAspects.nanoWing, 0, 6, 2, new ItemStack(ItemRegistry.nanoWing)).setParents("Thaumium Reinforced Wings").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.nanoWings));
		}

		if (!EMTConfigHandler.quantumWingsResearch && !EMTConfigHandler.thaumiumWingResearch && !EMTConfigHandler.nanoWingResearch) {
			new EMTResearchItem("Quantum Wings", "EMT", EMTResearchAspects.quantumWing, 0, 8, 3, new ItemStack(ItemRegistry.quantumWing)).setParents("Nanosuit Wings").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.quantumWings));
		}

		if (!EMTConfigHandler.infusedQuantumChestplate && !EMTConfigHandler.thaumiumWingResearch && !EMTConfigHandler.nanoWingResearch && !EMTConfigHandler.quantumWingsResearch) {
			new EMTResearchItem("Infused Quantum Armor", "EMT", EMTResearchAspects.quantumWing, 0, 10, 4, new ItemStack(ItemRegistry.quantumArmor)).setParents("Quantum Wings").setConcealed().registerResearchItem().setPages(new ResearchPage("1"), new ResearchPage(RecipeRegistry.infusedQuantumArmor));
		}
	}
}
