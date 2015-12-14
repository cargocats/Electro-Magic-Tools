package tombenpotter.emt.common.tile.generator;

import ic2.api.energy.prefab.BasicSource;
import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTEssentiasOutputs;

public class TileEntityPotentiaGenerator extends TileEntityBaseGenerator {

	public TileEntityPotentiaGenerator() {
		aspect = Aspect.ENERGY;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
