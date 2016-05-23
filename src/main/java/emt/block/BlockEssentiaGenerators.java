package emt.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.EMT;
import emt.tile.generator.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

public class BlockEssentiaGenerators extends BlockBaseContainer {

	public BlockEssentiaGenerators(String name) {
		super(name, Material.iron, soundTypeMetal, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ri) {
		this.blockIcon = ri.registerIcon(EMT.TEXTURE_PATH + ":machines/top");

		for (int i = 0; i <= 5; i++) {
			this.top[i] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/top");
		}

		for (int i = 0; i <= 5; i++) {
			this.bottom[i] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/top");
		}

		for (int i = 0; i <= 5; i++) {
			this.side[i] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/side");
		}

		this.frontOff[0] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/potentiafront");
		this.frontOff[1] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/ignisfront");
		this.frontOff[2] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/auramfront");
		this.frontOff[3] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/arborfront");
		this.frontOff[4] = ri.registerIcon(EMT.TEXTURE_PATH + ":essentiagenerator/aerfront");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
		list.add(new ItemStack(id, 1, 2));
		list.add(new ItemStack(id, 1, 3));
		list.add(new ItemStack(id, 1, 4));
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		switch (meta) {
			case 0:
				return new TileEntityPotentiaGenerator();
			case 1:
				return new TileEntityIgnisGenerator();
			case 2:
				return new TileEntityAuramGenerator();
			case 3:
				return new TileEntityArborGenerator();
			case 4:
				return new TileEntityAerGenerator();
		}
		return super.createTileEntity(world, meta);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityBaseGenerator te = (TileEntityBaseGenerator) world.getTileEntity(x, y, z);
		if (te.isActive) {
			int color = te.aspect.getColor();
			float r = (float) (color >> 16 & 0xff) / 255F;
			float g = (float) (color >> 8 & 0xff) / 255F;
			float b = (float) (color & 0xff) / 255F;

			EntitySmokeFX fx = new EntitySmokeFX(world, x + 0.5f, y + 1.1, z + 0.5f, 0, 0.1, 0, 3);
			fx.setRBGColorF(r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		}
	}
}
