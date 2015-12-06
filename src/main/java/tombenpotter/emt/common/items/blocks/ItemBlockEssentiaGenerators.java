package tombenpotter.emt.common.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

import tombenpotter.emt.common.tile.generators.TileEntityAerGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityArborGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityAuramGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityIgnisGenerator;
import tombenpotter.emt.common.tile.generators.TileEntityPotentiaGenerator;
import tombenpotter.emt.common.util.ConfigHandler;

public class ItemBlockEssentiaGenerators extends ItemBlock {

	public ItemBlockEssentiaGenerators(Block id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
		case 0:
			name = "potentia";
			break;
		case 1:
			name = "ignis";
			break;
		case 2:
			name = "auram";
			break;
		case 3:
			name = "arbor";
			break;
		case 4:
			name = "aer";
			break;
		default:
			name = "nothing";
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		switch (stack.getItemDamage()) {
		case 0:
			list.add(ConfigHandler.potentiaGenerator + " EU/t");
			break;
		case 1:
			list.add(ConfigHandler.ignisGenerator + " EU/t");
			break;
		case 2:
			list.add(ConfigHandler.auramGenerator + " EU/t");
			break;
		case 3:
			list.add(ConfigHandler.arborGenerator + " EU/t");
			break;
		case 4:
			list.add(ConfigHandler.aerGenerator + " EU/t");
			break;
		}
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
