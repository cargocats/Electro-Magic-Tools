package emt.init;

public class Registry {
	public static void register() {
		EMTBlocks.registerBlocks();
		EMTItems.registerItems();
		EMTTiles.registerTiles();
		EMTRecipes.registerEarlyRecipes();
	}

	public static void registerLate() {
		EMTBlocks.addAspects();
		EMTRecipes.registerLateRecipes();
		EMTRecipes.registerUUMInfusionRecipes();
	}
}
