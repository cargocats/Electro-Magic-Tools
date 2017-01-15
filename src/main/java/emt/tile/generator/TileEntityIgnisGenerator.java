package emt.tile.generator;

import emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityIgnisGenerator extends TileEntityBaseGenerator {

	public TileEntityIgnisGenerator() {
		aspect = Aspect.FIRE;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
