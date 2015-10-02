package tombenpotter.emt.common.init;

public class Registry{
	public static void register(){
		BlockRegistry.registerBlocks();
		ItemRegistry.registerItems();
		TileRegistry.registerTiles();
		RecipeRegistry.registerEarlyRecipes();
	}
	
	public static void registerLate(){
        RecipeRegistry.registerLateRecipes();
        RecipeRegistry.registerUUMInfusionRecipes();
	}
}
