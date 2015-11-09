package tombenpotter.emt.common.tile.generators;

import ic2.api.energy.prefab.BasicSource;
import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.common.util.ConfigHandler;
import tombenpotter.emt.common.util.EssentiasOutputs;

public class TileEntityPotentiaGenerator extends TileEntityBaseGenerator {

    public TileEntityPotentiaGenerator() {
        aspect = Aspect.ENERGY;
        EssentiasOutputs.outputs.get(aspect.getTag());
        output = ConfigHandler.potentiaGenerator;
    }
}
