package tombenpotter.emt.common.tile.generators;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EssentiasOutputs;

public class TileEntityArborGenerator extends TileEntityBaseGenerator {

    public TileEntityArborGenerator() {
        aspect = Aspect.TREE;
        EssentiasOutputs.outputs.get(aspect.getTag());
    }
}
