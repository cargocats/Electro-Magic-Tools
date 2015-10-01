package tombenpotter.emt.common.tile.generators;

import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.EssentiasOutputs;

public class TileEntityAuramGenerator extends TileEntityBaseGenerator {

    public TileEntityAuramGenerator() {
        aspect = Aspect.AURA;
        EssentiasOutputs.outputs.get(aspect.getTag());
    }
}
