package tombenpotter.emt.common.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEMTMachines extends ItemBlock {

    public ItemBlockEMTMachines(Block id) {
        super(id);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        String name = "";
        switch (itemstack.getItemDamage()) {
            case 0: {
                name = "wandrecharge";
                break;
            }
            case 1: {
                name = "etheralprocessor";
                break;
            }
            case 2: {
                name = "raindispeller";
                break;
            }
            default:
                name = "nothing";
        }
        return getUnlocalizedName() + "." + name;
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }
}
