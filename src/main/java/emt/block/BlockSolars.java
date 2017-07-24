package emt.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.EMT;
import emt.tile.solar.air.TileEntityAirSolar;
import emt.tile.solar.air.TileEntityDoubleAirSolar;
import emt.tile.solar.air.TileEntityTripleAirSolar;
import emt.tile.solar.compressed.TileEntityCompressedSolar;
import emt.tile.solar.compressed.TileEntityDoubleCompressedSolar;
import emt.tile.solar.compressed.TileEntityTripleCompressedSolar;
import emt.tile.solar.dark.TileEntityDarkSolar;
import emt.tile.solar.dark.TileEntityDoubleDarkSolar;
import emt.tile.solar.dark.TileEntityTripleDarkSolar;
import emt.tile.solar.earth.TileEntityDoubleEarthSolar;
import emt.tile.solar.earth.TileEntityEarthSolar;
import emt.tile.solar.earth.TileEntityTripleEarthSolar;
import emt.tile.solar.fire.TileEntityDoubleFireSolar;
import emt.tile.solar.fire.TileEntityFireSolar;
import emt.tile.solar.fire.TileEntityTripleFireSolar;
import emt.tile.solar.order.TileEntityDoubleOrderSolar;
import emt.tile.solar.order.TileEntityOrderSolar;
import emt.tile.solar.order.TileEntityTripleOrderSolar;
import emt.tile.solar.water.TileEntityDoubleWaterSolar;
import emt.tile.solar.water.TileEntityTripleWaterSolar;
import emt.tile.solar.water.TileEntityWaterSolar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockSolars extends BlockBaseContainer {
	public BlockSolars(String name, int countOfMetas, int instance) {
		super(name, Material.iron, soundTypeMetal, countOfMetas, instance, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		IIcon solarTop = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/solartop");
		IIcon doubleSolarTop = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/doublesolartop");
		IIcon tripleSolarTop = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/triplesolartop");
		IIcon bottom = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/bottom");

		if(instance == 0) {
			iconSets[0].top = solarTop;
			iconSets[1].top = doubleSolarTop;
			iconSets[2].top = tripleSolarTop;
			iconSets[3].top = solarTop;
			iconSets[4].top = doubleSolarTop;
			iconSets[5].top = tripleSolarTop;
			iconSets[6].top = solarTop;
			iconSets[7].top = doubleSolarTop;
			iconSets[8].top = tripleSolarTop;
			iconSets[9].top = solarTop;
			iconSets[10].top = doubleSolarTop;
			iconSets[11].top = tripleSolarTop;
			iconSets[12].top = solarTop;
			iconSets[13].top = doubleSolarTop;
			iconSets[14].top = tripleSolarTop;
			iconSets[15].top = solarTop;
			
			iconSets[0].bottom = bottom;
			iconSets[1].bottom = bottom;
			iconSets[2].bottom = bottom;
			iconSets[3].bottom = bottom;
			iconSets[4].bottom = bottom;
			iconSets[5].bottom = bottom;
			iconSets[6].bottom = bottom;
			iconSets[7].bottom = bottom;
			iconSets[8].bottom = bottom;
			iconSets[9].bottom = bottom;
			iconSets[10].bottom = bottom;
			iconSets[11].bottom = bottom;
			iconSets[12].bottom = bottom;
			iconSets[13].bottom = bottom;
			iconSets[14].bottom = bottom;
			iconSets[15].bottom = bottom;
			
			IIcon side = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/side");
			IIcon waterSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/water/waterside");
			IIcon darkSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/dark/darkside");
			IIcon orderSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/order/orderside");
			IIcon fireSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/fire/fireside");
			IIcon airSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/air/airside");
			
			iconSets[0].side = side;
			iconSets[1].side = side;
			iconSets[2].side = side;
			iconSets[3].side = waterSide;
			iconSets[4].side = waterSide;
			iconSets[5].side = waterSide;
			iconSets[6].side = darkSide;
			iconSets[7].side = darkSide;
			iconSets[8].side = darkSide;
			iconSets[9].side = orderSide;
			iconSets[10].side = orderSide;
			iconSets[11].side = orderSide;
			iconSets[12].side = fireSide;
			iconSets[13].side = fireSide;
			iconSets[14].side = fireSide;
			iconSets[15].side = airSide;
		}
		if(instance == 1) {
			iconSets[0].top = doubleSolarTop;
			iconSets[1].top = tripleSolarTop;
			iconSets[2].top = solarTop;
			iconSets[3].top = doubleSolarTop;
			iconSets[4].top = tripleSolarTop;

			iconSets[0].bottom = bottom;
			iconSets[1].bottom = bottom;
			iconSets[2].bottom = bottom;
			iconSets[3].bottom = bottom;
			iconSets[4].bottom = bottom;

			IIcon airSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/air/airside");
			IIcon earthSide = ir.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");
			
			iconSets[0].side = airSide;
			iconSets[1].side = airSide;
			iconSets[2].side = earthSide;
			iconSets[3].side = earthSide;
			iconSets[4].side = earthSide;
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		if (instance == 0) {
			for (int meta = 0; meta < 16; meta++) {
				list.add(new ItemStack(id, 1, meta));
			}
		}

		if (instance == 1) {
			for (int meta = 0; meta < 5; meta++) {
				list.add(new ItemStack(id, 1, meta));
			}
		}

	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (side < 1) {
			return iconSets[meta].bottom;
		}
		if (side == 1) {
			return iconSets[meta].top;
		}

		return iconSets[meta].side;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		if (instance == 0) {
			switch (meta) {
				case 0:
					return new TileEntityCompressedSolar();
				case 1:
					return new TileEntityDoubleCompressedSolar();
				case 2:
					return new TileEntityTripleCompressedSolar();
				case 3:
					return new TileEntityWaterSolar();
				case 4:
					return new TileEntityDoubleWaterSolar();
				case 5:
					return new TileEntityTripleWaterSolar();
				case 6:
					return new TileEntityDarkSolar();
				case 7:
					return new TileEntityDoubleDarkSolar();
				case 8:
					return new TileEntityTripleDarkSolar();
				case 9:
					return new TileEntityOrderSolar();
				case 10:
					return new TileEntityDoubleOrderSolar();
				case 11:
					return new TileEntityTripleOrderSolar();
				case 12:
					return new TileEntityFireSolar();
				case 13:
					return new TileEntityDoubleFireSolar();
				case 14:
					return new TileEntityTripleFireSolar();
				case 15:
					return new TileEntityAirSolar();
			}
		}

		if (instance == 1) {

			switch (meta) {
				case 0:
					return new TileEntityDoubleAirSolar();
				case 1:
					return new TileEntityTripleAirSolar();
				case 2:
					return new TileEntityEarthSolar();
				case 3:
					return new TileEntityDoubleEarthSolar();
				case 4:
					return new TileEntityTripleEarthSolar();
			}
		}

		return super.createTileEntity(world, meta);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		world.removeTileEntity(x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
}
