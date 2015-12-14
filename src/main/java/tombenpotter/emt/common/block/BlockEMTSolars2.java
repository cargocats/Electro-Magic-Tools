package tombenpotter.emt.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.tile.solar.air.TileEntityDoubleAirSolar;
import tombenpotter.emt.common.tile.solar.air.TileEntityTripleAirSolar;
import tombenpotter.emt.common.tile.solar.earth.TileEntityDoubleEarthSolar;
import tombenpotter.emt.common.tile.solar.earth.TileEntityEarthSolar;
import tombenpotter.emt.common.tile.solar.earth.TileEntityTripleEarthSolar;

import java.util.List;

public class BlockEMTSolars2 extends BlockBaseContainer {

	public BlockEMTSolars2() {
		super("solars.2", Material.iron, soundTypeMetal, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ri) {
		this.top[0] = ri.registerIcon(ModInformation.texturePath + ":solars/solartop");
		this.top[1] = ri.registerIcon(ModInformation.texturePath + ":solars/solartop");
		this.top[2] = ri.registerIcon(ModInformation.texturePath + ":solars/solartop");
		this.top[3] = ri.registerIcon(ModInformation.texturePath + ":solars/doublesolartop");
		this.top[4] = ri.registerIcon(ModInformation.texturePath + ":solars/triplesolartop");

		this.bottom[0] = ri.registerIcon(ModInformation.texturePath + ":solars/bottom");
		this.bottom[1] = ri.registerIcon(ModInformation.texturePath + ":solars/bottom");
		this.bottom[2] = ri.registerIcon(ModInformation.texturePath + ":solars/bottom");
		this.bottom[3] = ri.registerIcon(ModInformation.texturePath + ":solars/bottom");
		this.bottom[4] = ri.registerIcon(ModInformation.texturePath + ":solars/bottom");

		this.frontOff[0] = ri.registerIcon(ModInformation.texturePath + ":solars/air/airside");
		this.frontOff[1] = ri.registerIcon(ModInformation.texturePath + ":solars/air/airside");
		this.frontOff[2] = ri.registerIcon(ModInformation.texturePath + ":solars/earth/earthside");
		this.frontOff[3] = ri.registerIcon(ModInformation.texturePath + ":solars/earth/earthside");
		this.frontOff[4] = ri.registerIcon(ModInformation.texturePath + ":solars/earth/earthside");

		this.side[0] = ri.registerIcon(ModInformation.texturePath + ":solars/air/airside");
		this.side[1] = ri.registerIcon(ModInformation.texturePath + ":solars/air/airside");
		this.side[2] = ri.registerIcon(ModInformation.texturePath + ":solars/earth/earthside");
		this.side[3] = ri.registerIcon(ModInformation.texturePath + ":solars/earth/earthside");
		this.side[4] = ri.registerIcon(ModInformation.texturePath + ":solars/earth/earthside");
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
