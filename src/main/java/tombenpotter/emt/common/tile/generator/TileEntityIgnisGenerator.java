package tombenpotter.emt.common.tile.generator;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTEssentiasOutputs;

public class TileEntityIgnisGenerator extends TileEntityBaseGenerator {

	public TileEntityIgnisGenerator() {
		aspect = Aspect.FIRE;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
