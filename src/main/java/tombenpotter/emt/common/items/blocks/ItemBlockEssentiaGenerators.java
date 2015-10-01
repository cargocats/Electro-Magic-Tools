package tombenpotter.emt.common.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockEssentiaGenerators extends ItemBlock {

    public ItemBlockEssentiaGenerators(Block id) {
        super(id);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        String name = "";
        switch (itemstack.getItemDamage()) {
            case 0: {
                name = "potentia";
                break;
            }
            case 1: {
                name = "ignis";
                break;
            }
            case 2: {
                name = "auram";
                break;
            }
            case 3: {
                name = "arbor";
                break;
            }
            case 4: {
                name = "aer";
                break;
            }
            default:
                name = "nothing";
        }
        return getUnlocalizedName() + "." + name;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Going away next version.");
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}
