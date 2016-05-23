package emt.block;

import ic2.api.item.IC2Items;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.EMT;
import emt.tile.TileEntityEtherealMacerator;
import emt.tile.TileEntityIndustrialWandRecharge;

public class BlockMachines extends BlockBaseContainer {

	public BlockMachines(String name) {
		super(name, Material.iron, soundTypeMetal, 4.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		this.blockIcon = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/top");

		this.top[0] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/top");
		this.top[1] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/etherealmaceratortop");

		this.bottom[0] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/side");
		this.bottom[1] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/etherealmacerator");

		this.side[0] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/side");
		this.side[1] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/etherealmacerator");

		this.frontOff[0] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/wandcharger");
		this.frontOff[1] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/etherealmaceratorfront");

		this.frontOn[0] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/wandcharger");
		this.frontOn[1] = ir.registerIcon(EMT.TEXTURE_PATH + ":machines/etherealmaceratorfrontactive");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		if (meta == 0) {
			return new TileEntityIndustrialWandRecharge();
		}
		if (meta == 1) {
			return new TileEntityEtherealMacerator();
		}
		return super.createTileEntity(world, meta);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityIndustrialWandRecharge) {
			player.openGui(EMT.instance, 0, world, x, y, z);
		}
		else if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityEtherealMacerator) {
			player.openGui(EMT.instance, 1, world, x, y, z);
		}
		return true;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return IC2Items.getItem("machine").getItem();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
		dropItems(world, x, y, z);
		world.removeTileEntity(x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}
}
