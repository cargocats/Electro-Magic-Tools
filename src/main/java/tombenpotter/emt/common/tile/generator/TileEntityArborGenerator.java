package tombenpotter.emt.common.tile.generator;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTEssentiasOutputs;

public class TileEntityArborGenerator extends TileEntityBaseGenerator {

	public TileEntityArborGenerator() {
		aspect = Aspect.TREE;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
