package emt.tile.generator;

import emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityPotentiaGenerator extends TileEntityBaseGenerator {

	public TileEntityPotentiaGenerator() {
		aspect = Aspect.ENERGY;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
