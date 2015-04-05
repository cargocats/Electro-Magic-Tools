package tombenpotter.emt.common;

import tombenpotter.emt.common.blocks.BlockRegistry;
import tombenpotter.emt.common.RecipeRegistry;
import tombenpotter.emt.common.items.ItemRegistry;
import tombenpotter.emt.common.tile.TileRegistry;

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