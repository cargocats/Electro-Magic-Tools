package tombenpotter.emt.common.tile.generators;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EssentiasOutputs;

public class TileEntityAerGenerator extends TileEntityBaseGenerator {

    public TileEntityAerGenerator() {
        aspect = Aspect.AIR;
        EssentiasOutputs.outputs.get(aspect.getTag());
    }
}
