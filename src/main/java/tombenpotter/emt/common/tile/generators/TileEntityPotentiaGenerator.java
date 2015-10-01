package tombenpotter.emt.common.tile.generators;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EssentiasOutputs;

public class TileEntityPotentiaGenerator extends TileEntityBaseGenerator {

    public TileEntityPotentiaGenerator() {
        aspect = Aspect.ENERGY;
        EssentiasOutputs.outputs.get(aspect.getTag());
    }
}
