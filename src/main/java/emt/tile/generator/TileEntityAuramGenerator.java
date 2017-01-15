package emt.tile.generator;

import emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityAuramGenerator extends TileEntityBaseGenerator {

	public TileEntityAuramGenerator() {
		aspect = Aspect.AURA;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
