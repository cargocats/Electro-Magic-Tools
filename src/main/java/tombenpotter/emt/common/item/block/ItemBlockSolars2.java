package tombenpotter.emt.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import tombenpotter.emt.common.util.EMTConfigHandler;
import tombenpotter.emt.common.util.EMTTextHelper;

import java.util.List;

import static tombenpotter.emt.common.util.EMTTextHelper.localize;

public class ItemBlockSolars2 extends ItemBlock {

	public ItemBlockSolars2(Block id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
		case 0: {
			name = "doubleair";
			break;
		}
		case 1: {
			name = "tripleair";
			break;
		}
		case 2: {
			name = "earth";
			break;
		}
		case 3: {
			name = "doubleearth";
			break;
		}
		case 4: {
			name = "tripleearth";
			break;
		}
		default:
			name = "nothing";
			break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		switch (itemstack.getItemDamage()) {
		case 0: {
			list.add(EMTTextHelper.BRIGHT_BLUE + localize("tooltip.EMT.normalConditions") + ": " + EMTTextHelper.LIGHT_GRAY + EMTConfigHandler.doubleCompressedSolarOutput + " " + localize("tooltip.EMT.euPerTick"));
			list.add(EMTTextHelper.BRIGHT_GREEN + localize("tooltip.EMT.specialEffect") + ": " + EMTTextHelper.LIGHT_GRAY + localize("tooltip.EMT.output.double.half") + " " + localize("tooltip.EMT.output.double.half.height"));
			break;
		}
		case 1: {
			list.add(EMTTextHelper.BRIGHT_BLUE + localize("tooltip.EMT.normalConditions") + ": " + EMTTextHelper.LIGHT_GRAY + EMTConfigHandler.tripleCompressedSolarOutput + " " + localize("tooltip.EMT.euPerTick"));
			list.add(EMTTextHelper.BRIGHT_GREEN + localize("tooltip.EMT.specialEffect") + ": " + EMTTextHelper.LIGHT_GRAY + localize("tooltip.EMT.output.double.half") + " " + localize("tooltip.EMT.output.double.half.height"));
			break;
		}
		case 2: {
			list.add(EMTTextHelper.BRIGHT_BLUE + localize("tooltip.EMT.normalConditions") + ": " + EMTTextHelper.LIGHT_GRAY + EMTConfigHandler.compressedSolarOutput + " " + localize("tooltip.EMT.euPerTick"));
			list.add(EMTTextHelper.BRIGHT_GREEN + localize("tooltip.EMT.specialEffect") + ": " + EMTTextHelper.LIGHT_GRAY + localize("tooltip.EMT.output.double.half") + " " + localize("tooltip.EMT.output.double.half.low"));
			break;
		}
		case 3: {
			list.add(EMTTextHelper.BRIGHT_BLUE + localize("tooltip.EMT.normalConditions") + ": " + EMTTextHelper.LIGHT_GRAY + EMTConfigHandler.doubleCompressedSolarOutput + " " + localize("tooltip.EMT.euPerTick"));
			list.add(EMTTextHelper.BRIGHT_GREEN + localize("tooltip.EMT.specialEffect") + ": " + EMTTextHelper.LIGHT_GRAY + localize("tooltip.EMT.output.double.half") + " " + localize("tooltip.EMT.output.double.half.low"));
			break;
		}
		case 4: {
			list.add(EMTTextHelper.BRIGHT_BLUE + localize("tooltip.EMT.normalConditions") + ": " + EMTTextHelper.LIGHT_GRAY + EMTConfigHandler.tripleCompressedSolarOutput + " " + localize("tooltip.EMT.euPerTick"));
			list.add(EMTTextHelper.BRIGHT_GREEN + localize("tooltip.EMT.specialEffect") + ": " + EMTTextHelper.LIGHT_GRAY + localize("tooltip.EMT.output.double.half") + " " + localize("tooltip.EMT.output.double.half.low"));
			break;
		}
		default: {
			list.add(localize("tooltip.EMT.forget"));
			break;
		}
		}
	}
}
