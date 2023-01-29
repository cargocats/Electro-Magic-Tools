package emt.tile.generator;

import thaumcraft.api.aspects.Aspect;
import emt.tile.DefinitelyNotAIC2Source;
import emt.util.EMTConfigHandler;

public abstract class TileEntityCompressedGenerator extends TileEntityBaseGenerator {

    public TileEntityCompressedGenerator(Aspect aspect) {
        super();
        this.energySource = new DefinitelyNotAIC2Source(this, 1000000L, 3);
        this.energySource.setCapacity(EMTConfigHandler.EssentiaGeneratorStorage * 10.0D);
        this.maxfuel *= 10;
        this.aspect = aspect;
    }
}
