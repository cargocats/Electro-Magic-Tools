package tombenpotter.emt.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.tile.generators.*;

import java.util.List;
import java.util.Random;

public class BlockEssentiaGenerators extends BlockBaseContainer {

	public BlockEssentiaGenerators() {
		super("generator.essentia.2", Material.iron, soundTypeMetal, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ri) {
		this.blockIcon = ri.registerIcon(ModInformation.texturePath + ":machines/top");

		for (int i = 0; i <= 5; i++) {
			this.top[i] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/top");
		}

		for (int i = 0; i <= 5; i++) {
			this.bottom[i] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/top");
		}

		for (int i = 0; i <= 5; i++) {
			this.side[i] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/side");
		}

		this.frontOff[0] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/potentiafront");
		this.frontOff[1] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/ignisfront");
		this.frontOff[2] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/auramfront");
		this.frontOff[3] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/arborfront");
		this.frontOff[4] = ri.registerIcon(ModInformation.texturePath + ":essentiagenerator/aerfront");
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
		world.spawnParticle("largesmoke", x + 0.5f, y + 1.0f, z + 0.5f, 0.0D, 0.0D, 0.0D);
	}
}
