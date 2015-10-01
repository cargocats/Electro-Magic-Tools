package tombenpotter.emt.common.tile.generators;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EssentiasOutputs;

public class TileEntityIgnisGenerator extends TileEntityBaseGenerator {

    public TileEntityIgnisGenerator() {
        aspect = Aspect.FIRE;
        EssentiasOutputs.outputs.get(aspect.getTag());
    }
}
