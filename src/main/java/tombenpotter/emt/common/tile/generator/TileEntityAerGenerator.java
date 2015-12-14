package tombenpotter.emt.common.tile.generator;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTEssentiasOutputs;

public class TileEntityAerGenerator extends TileEntityBaseGenerator {

	public TileEntityAerGenerator() {
		aspect = Aspect.AIR;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}