package tombenpotter.emt.common.init;

import tombenpotter.emt.common.tile.TileElectricCloud;
import tombenpotter.emt.common.tile.TileEntityEtherealMacerator;
import tombenpotter.emt.common.tile.TileEntityIndustrialWandRecharge;
import tombenpotter.emt.common.tile.TileEntityPortableNode;
import tombenpotter.emt.common.tile.generator.TileEntityAerGenerator;
import tombenpotter.emt.common.tile.generator.TileEntityArborGenerator;
import tombenpotter.emt.common.tile.generator.TileEntityAuramGenerator;
import tombenpotter.emt.common.tile.generator.TileEntityIgnisGenerator;
import tombenpotter.emt.common.tile.generator.TileEntityPotentiaGenerator;
import tombenpotter.emt.common.tile.solar.air.TileEntityAirSolar;
import tombenpotter.emt.common.tile.solar.air.TileEntityDoubleAirSolar;
import tombenpotter.emt.common.tile.solar.air.TileEntityTripleAirSolar;
import tombenpotter.emt.common.tile.solar.compressed.TileEntityCompressedSolar;
import tombenpotter.emt.common.tile.solar.compressed.TileEntityDoubleCompressedSolar;
import tombenpotter.emt.common.tile.solar.compressed.TileEntityTripleCompressedSolar;
import tombenpotter.emt.common.tile.solar.dark.TileEntityDarkSolar;
import tombenpotter.emt.common.tile.solar.dark.TileEntityDoubleDarkSolar;
import tombenpotter.emt.common.tile.solar.dark.TileEntityTripleDarkSolar;
import tombenpotter.emt.common.tile.solar.earth.TileEntityDoubleEarthSolar;
import tombenpotter.emt.common.tile.solar.earth.TileEntityEarthSolar;
import tombenpotter.emt.common.tile.solar.earth.TileEntityTripleEarthSolar;
import tombenpotter.emt.common.tile.solar.fire.TileEntityDoubleFireSolar;
import tombenpotter.emt.common.tile.solar.fire.TileEntityFireSolar;
import tombenpotter.emt.common.tile.solar.fire.TileEntityTripleFireSolar;
import tombenpotter.emt.common.tile.solar.order.TileEntityDoubleOrderSolar;
import tombenpotter.emt.common.tile.solar.order.TileEntityOrderSolar;
import tombenpotter.emt.common.tile.solar.order.TileEntityTripleOrderSolar;
import tombenpotter.emt.common.tile.solar.water.TileEntityDoubleWaterSolar;
import tombenpotter.emt.common.tile.solar.water.TileEntityTripleWaterSolar;
import tombenpotter.emt.common.tile.solar.water.TileEntityWaterSolar;
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
		GameRegistry.registerTileEntity(TileElectricCloud.class, "tileEntityElectricCloud");
	}
}
