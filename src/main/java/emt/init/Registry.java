package emt.init;

public class Registry {
	public static void register() {
		BlockRegistry.registerBlocks();
		ItemRegistry.registerItems();
		TileRegistry.registerTiles();
		RecipeRegistry.registerEarlyRecipes();
	}

	public static void registerLate() {
		BlockRegistry.addAspects();
		RecipeRegistry.registerLateRecipes();
		RecipeRegistry.registerUUMInfusionRecipes();
	}
}
