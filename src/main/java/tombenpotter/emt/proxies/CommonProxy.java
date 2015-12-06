package tombenpotter.emt.proxies;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tombenpotter.emt.client.gui.GuiEtherealMacerator;
import tombenpotter.emt.client.gui.GuiIndustrialWandRecharger;
import tombenpotter.emt.client.gui.container.ContainerEtheralMacerator;
import tombenpotter.emt.client.gui.container.ContainerIndustrialWandRecharge;
import tombenpotter.emt.common.tile.TileEntityEtherealMacerator;
import tombenpotter.emt.common.tile.TileEntityIndustrialWandRecharge;

public class CommonProxy implements IGuiHandler {

	public void load() {
		registerRenders();
	}

	public void registerRenders() {
		/* Empty in base proxy */
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			if (entity != null && entity instanceof TileEntityIndustrialWandRecharge) {
				return new ContainerIndustrialWandRecharge(player.inventory, (TileEntityIndustrialWandRecharge) entity);
			}
		case 1:
			if (entity != null && entity instanceof TileEntityEtherealMacerator) {
				return new ContainerEtheralMacerator(player.inventory, (TileEntityEtherealMacerator) entity);
			}
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		switch (ID) {
		case 0:
			if (entity != null && entity instanceof TileEntityIndustrialWandRecharge) {
				return new GuiIndustrialWandRecharger(player.inventory, (TileEntityIndustrialWandRecharge) entity);
			}
		case 1:
			if (entity != null && entity instanceof TileEntityEtherealMacerator) {
				return new GuiEtherealMacerator(player.inventory, (TileEntityEtherealMacerator) entity);
			}
		default:
			return null;
		}
	}
}
