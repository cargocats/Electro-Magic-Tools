package emt.tile.generator;

import emt.util.EMTConfigHandler;
import emt.util.EMTEssentiasOutputs;
import ic2.api.energy.prefab.BasicSource;
import thaumcraft.api.aspects.Aspect;

public class TileEntityPotentiaGenerator extends TileEntityBaseGenerator {

	public TileEntityPotentiaGenerator() {
		aspect = Aspect.ENERGY;
		output = EMTEssentiasOutputs.outputs.get(aspect.getTag());
	}
}
