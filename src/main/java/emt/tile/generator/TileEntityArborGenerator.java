package emt.tile.generator;

import emt.util.EMTConfigHandler;
import emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityArborGenerator extends TileEntityBaseGenerator {

	public TileEntityArborGenerator() {
		aspect = Aspect.TREE;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
