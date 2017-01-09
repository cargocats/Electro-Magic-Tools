package emt.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
	public int getMetadata(int meta) {
		return meta;
	}
}
