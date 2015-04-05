package tombenpotter.emt.common.tile;

import tombenpotter.emt.common.tile.generators.TileEntityAerGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityArborGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityAuramGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityEssentiaGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityIgnisGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityPotentiaGenerator;
import tombenpotter.emt.common.tile.solars.air.TileEntityAirSolar;
import tombenpotter.emt.common.tile.solars.air.TileEntityDoubleAirSolar;
import tombenpotter.emt.common.tile.solars.air.TileEntityTripleAirSolar;
import tombenpotter.emt.common.tile.solars.compressed.TileEntityCompressedSolar;
import tombenpotter.emt.common.tile.solars.compressed.TileEntityDoubleCompressedSolar;
import tombenpotter.emt.common.tile.solars.compressed.TileEntityTripleCompressedSolar;
import tombenpotter.emt.common.tile.solars.dark.TileEntityDarkSolar;
import tombenpotter.emt.common.tile.solars.dark.TileEntityDoubleDarkSolar;
import tombenpotter.emt.common.tile.solars.dark.TileEntityTripleDarkSolar;
import tombenpotter.emt.common.tile.solars.earth.TileEntityDoubleEarthSolar;
import tombenpotter.emt.common.tile.solars.earth.TileEntityEarthSolar;
import tombenpotter.emt.common.tile.solars.earth.TileEntityTripleEarthSolar;
import tombenpotter.emt.common.tile.solars.fire.TileEntityDoubleFireSolar;
import tombenpotter.emt.common.tile.solars.fire.TileEntityFireSolar;
import tombenpotter.emt.common.tile.solars.fire.TileEntityTripleFireSolar;
import tombenpotter.emt.common.tile.solars.order.TileEntityDoubleOrderSolar;
import tombenpotter.emt.common.tile.solars.order.TileEntityOrderSolar;
import tombenpotter.emt.common.tile.solars.order.TileEntityTripleOrderSolar;
import tombenpotter.emt.common.tile.solars.water.TileEntityDoubleWaterSolar;
import tombenpotter.emt.common.tile.solars.water.TileEntityTripleWaterSolar;
import tombenpotter.emt.common.tile.solars.water.TileEntityWaterSolar;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileRegistry {

    public static void registerTiles() {
        GameRegistry.registerTileEntity(TileEntityPortableNode.class, "tileentityportablenode");
        GameRegistry.registerTileEntity(TileEntityPotentiaGenerator.class, "tileentitypotentiagenerator");
        GameRegistry.registerTileEntity(TileEntityIgnisGenerator.class, "tileentityignisgenerator");
        GameRegistry.registerTileEntity(TileEntityAuramGenerator.class, "tileentityauramgenerator");
        GameRegistry.registerTileEntity(TileEntityArborGenerator.class, "tileentityarborgenerator");
        GameRegistry.registerTileEntity(TileEntityAerGenerator.class, "tileentityaergenerator");
        GameRegistry.registerTileEntity(TileEntityIndustrialWandRecharge.class, "tileentityindustrialwandrecharge");
        GameRegistry.registerTileEntity(TileEntityCompressedSolar.class, "tileentitycompressedsolar");
        GameRegistry.registerTileEntity(TileEntityDoubleCompressedSolar.class, "tileentitydoublecompressedsolar");
        GameRegistry.registerTileEntity(TileEntityTripleCompressedSolar.class, "tileentitytriplecompressedsolar");
        GameRegistry.registerTileEntity(TileEntityEtherealMacerator.class, "tileentityetherealmacerator");
        GameRegistry.registerTileEntity(TileEntityWaterSolar.class, "tileentitywatersolar");
        GameRegistry.registerTileEntity(TileEntityDoubleWaterSolar.class, "tileentitydoublewatersolar");
        GameRegistry.registerTileEntity(TileEntityTripleWaterSolar.class, "tileentitytriplewatersolar");
        GameRegistry.registerTileEntity(TileEntityDarkSolar.class, "tileentitydarksolar");
        GameRegistry.registerTileEntity(TileEntityDoubleDarkSolar.class, "tileentitydoubledarksolar");
        GameRegistry.registerTileEntity(TileEntityTripleDarkSolar.class, "tileentitytripledarksolar");
        GameRegistry.registerTileEntity(TileEntityOrderSolar.class, "tileentityordersolar");
        GameRegistry.registerTileEntity(TileEntityDoubleOrderSolar.class, "tileentitydoubleordersolar");
        GameRegistry.registerTileEntity(TileEntityTripleOrderSolar.class, "tileentitytripleordersolar");
        GameRegistry.registerTileEntity(TileEntityFireSolar.class, "tileentityfiresolar");
        GameRegistry.registerTileEntity(TileEntityDoubleFireSolar.class, "tileentitydoublefiresolar");
        GameRegistry.registerTileEntity(TileEntityTripleFireSolar.class, "tileentitytriplefiresolar");
        GameRegistry.registerTileEntity(TileEntityAirSolar.class, "tileentityairsolar");
        GameRegistry.registerTileEntity(TileEntityDoubleAirSolar.class, "tileentitydoubleairsolar");
        GameRegistry.registerTileEntity(TileEntityTripleAirSolar.class, "tileentitytripleairsolar");
        GameRegistry.registerTileEntity(TileEntityEarthSolar.class, "tileentityearthsolar");
        GameRegistry.registerTileEntity(TileEntityDoubleEarthSolar.class, "tileentitydoubleearthsolar");
        GameRegistry.registerTileEntity(TileEntityTripleEarthSolar.class, "tileentitytripleearthsolar");
        GameRegistry.registerTileEntity(TileEntityEssentiaGenerator.class, "tileentityessentiagenerator");
    }
}
