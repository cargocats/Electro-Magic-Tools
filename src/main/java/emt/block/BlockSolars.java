package emt.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.ModInformation;
import emt.tile.solar.air.TileEntityAirSolar;
import emt.tile.solar.compressed.TileEntityCompressedSolar;
import emt.tile.solar.compressed.TileEntityDoubleCompressedSolar;
import emt.tile.solar.compressed.TileEntityTripleCompressedSolar;
import emt.tile.solar.dark.TileEntityDarkSolar;
import emt.tile.solar.dark.TileEntityDoubleDarkSolar;
import emt.tile.solar.dark.TileEntityTripleDarkSolar;
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
import net.minecraft.world.World;

import java.util.List;

public class BlockSolars extends BlockBaseContainer {

	public BlockSolars(String name) {
		super(name, Material.iron, soundTypeMetal, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ri) {
		this.top[0] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/solartop");
		this.top[1] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/doublesolartop");
		this.top[2] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/triplesolartop");
		this.top[3] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/solartop");
		this.top[4] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/doublesolartop");
		this.top[5] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/triplesolartop");
		this.top[6] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/solartop");
		this.top[7] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/doublesolartop");
		this.top[8] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/triplesolartop");
		this.top[9] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/solartop");
		this.top[10] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/doublesolartop");
		this.top[11] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/triplesolartop");
		this.top[12] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/solartop");
		this.top[13] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/doublesolartop");
		this.top[14] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/triplesolartop");
		this.top[15] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/solartop");

		this.bottom[0] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[1] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[2] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[3] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[4] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[5] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[6] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[7] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[8] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[9] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[10] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[11] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[12] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[13] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[14] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");
		this.bottom[15] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/bottom");

		this.frontOff[0] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/side");
		this.frontOff[1] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/side");
		this.frontOff[2] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/side");
		this.frontOff[3] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/water/waterside");
		this.frontOff[4] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/water/waterside");
		this.frontOff[5] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/water/waterside");
		this.frontOff[6] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/dark/darkside");
		this.frontOff[7] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/dark/darkside");
		this.frontOff[8] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/dark/darkside");
		this.frontOff[9] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/order/orderside");
		this.frontOff[10] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/order/orderside");
		this.frontOff[11] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/order/orderside");
		this.frontOff[12] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/fire/fireside");
		this.frontOff[13] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/fire/fireside");
		this.frontOff[14] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/fire/fireside");
		this.frontOff[15] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/air/airside");

		this.side[0] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/side");
		this.side[1] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/side");
		this.side[2] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/side");
		this.side[3] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/water/waterside");
		this.side[4] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/water/waterside");
		this.side[5] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/water/waterside");
		this.side[6] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/dark/darkside");
		this.side[7] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/dark/darkside");
		this.side[8] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/dark/darkside");
		this.side[9] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/order/orderside");
		this.side[10] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/order/orderside");
		this.side[11] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/order/orderside");
		this.side[12] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/fire/fireside");
		this.side[13] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/fire/fireside");
		this.side[14] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/fire/fireside");
		this.side[15] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":solars/air/airside");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i <= 15; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		if (meta == 0) {
			return new TileEntityCompressedSolar();
		}
		if (meta == 1) {
			return new TileEntityDoubleCompressedSolar();
		}
		if (meta == 2) {
			return new TileEntityTripleCompressedSolar();
		}
		if (meta == 3) {
			return new TileEntityWaterSolar();
		}
		if (meta == 4) {
			return new TileEntityDoubleWaterSolar();
		}
		if (meta == 5) {
			return new TileEntityTripleWaterSolar();
		}
		if (meta == 6) {
			return new TileEntityDarkSolar();
		}
		if (meta == 7) {
			return new TileEntityDoubleDarkSolar();
		}
		if (meta == 8) {
			return new TileEntityTripleDarkSolar();
		}
		if (meta == 9) {
			return new TileEntityOrderSolar();
		}
		if (meta == 10) {
			return new TileEntityDoubleOrderSolar();
		}
		if (meta == 11) {
			return new TileEntityTripleOrderSolar();
		}
		if (meta == 12) {
			return new TileEntityFireSolar();
		}
		if (meta == 13) {
			return new TileEntityDoubleFireSolar();
		}
		if (meta == 14) {
			return new TileEntityTripleFireSolar();
		}
		if (meta == 15) {
			return new TileEntityAirSolar();
		}
		return super.createTileEntity(world, meta);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
		world.removeTileEntity(x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}
}
