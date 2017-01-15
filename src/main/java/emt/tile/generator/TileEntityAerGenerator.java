package emt.tile.generator;

import emt.util.EMTConfigHandler;
import emt.util.EMTEssentiasOutputs;
import thaumcraft.api.aspects.Aspect;

public class TileEntityAerGenerator extends TileEntityBaseGenerator {

	public TileEntityAerGenerator() {
		aspect = Aspect.AIR;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}