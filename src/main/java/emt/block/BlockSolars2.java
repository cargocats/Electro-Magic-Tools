package emt.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.EMT;
import emt.tile.solar.air.TileEntityDoubleAirSolar;
import emt.tile.solar.air.TileEntityTripleAirSolar;
import emt.tile.solar.earth.TileEntityDoubleEarthSolar;
import emt.tile.solar.earth.TileEntityEarthSolar;
import emt.tile.solar.earth.TileEntityTripleEarthSolar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class BlockSolars2 extends BlockBaseContainer {

	public BlockSolars2(String name) {
		super(name, Material.iron, soundTypeMetal, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ri) {
		this.top[0] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/solartop");
		this.top[1] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/solartop");
		this.top[2] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/solartop");
		this.top[3] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/doublesolartop");
		this.top[4] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/triplesolartop");

		this.bottom[0] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/bottom");
		this.bottom[1] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/bottom");
		this.bottom[2] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/bottom");
		this.bottom[3] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/bottom");
		this.bottom[4] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/bottom");

		this.frontOff[0] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/air/airside");
		this.frontOff[1] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/air/airside");
		this.frontOff[2] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");
		this.frontOff[3] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");
		this.frontOff[4] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");

		this.side[0] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/air/airside");
		this.side[1] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/air/airside");
		this.side[2] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");
		this.side[3] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");
		this.side[4] = ri.registerIcon(EMT.TEXTURE_PATH + ":solars/earth/earthside");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i <= 4; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		if (meta == 0) {
			return new TileEntityDoubleAirSolar();
		}
		if (meta == 1) {
			return new TileEntityTripleAirSolar();
		}
		if (meta == 2) {
			return new TileEntityEarthSolar();
		}
		if (meta == 3) {
			return new TileEntityDoubleEarthSolar();
		}
		if (meta == 4) {
			return new TileEntityTripleEarthSolar();
		}
		return super.createTileEntity(world, meta);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
		world.removeTileEntity(x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}
}
