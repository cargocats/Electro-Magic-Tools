package tombenpotter.emt.common.tile.generator;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTEssentiasOutputs;

public class TileEntityAuramGenerator extends TileEntityBaseGenerator {

	public TileEntityAuramGenerator() {
		aspect = Aspect.AURA;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
