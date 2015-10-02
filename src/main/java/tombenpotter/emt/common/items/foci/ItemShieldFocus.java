package tombenpotter.emt.common.items.foci;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.common.init.BlockRegistry;

public class ItemShieldFocus extends ItemBaseFocus {
	int y1 = 0;
	ItemStack milk;

    private static final AspectList visCost = new AspectList().add(Aspect.ORDER, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10);

    public ItemShieldFocus() {
        super(".focus.shield", "focus_shield");
    }

    @Override
    public int getFocusColor(ItemStack stack) {
        return 0x1E90FF;
    }

    @Override
    public AspectList getVisCost(ItemStack stack) {
        return visCost;
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "SHIELD";
    }
    
    @Override
    public boolean isUseItem(ItemStack stack) {
        return true;
    }

    @Override
    public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player, int count) {
        ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
        
        if (player.capabilities.isCreativeMode || wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, true)) {
        	
            player.motionX = 0.0D;
            if(!player.capabilities.isCreativeMode && player.motionY < 0)
            	player.motionY = player.motionY / 2;
            player.motionZ = 0.0D;
        	
            int x = MathHelper.floor_double(player.posX);
            int y = MathHelper.floor_double(player.posY);
            int z = MathHelper.floor_double(player.posZ);
            
            if(milk == null)milk = (new ItemStack(Items.milk_bucket));
            player.curePotionEffects(milk);
            
            	
            if(player.motionY > -5){
            	// Player Level
            	if (player.worldObj.isAirBlock(x + 1, y, z) && player.worldObj.isAirBlock(x - 1, y, z) && player.worldObj.isAirBlock(x, y, z + 1) && player.worldObj.isAirBlock(x, y, z - 1)) {
                	player.worldObj.setBlock(x + 1, y, z, BlockRegistry.shield);
                	player.worldObj.setBlock(x - 1, y, z, BlockRegistry.shield);
                	player.worldObj.setBlock(x, y, z + 1, BlockRegistry.shield);
                	player.worldObj.setBlock(x, y, z - 1, BlockRegistry.shield);
            	}

            	// Above the player
            	if (player.worldObj.isAirBlock(x + 1, y + 1, z) && player.worldObj.isAirBlock(x - 1, y + 1, z) && player.worldObj.isAirBlock(x, y + 1, z + 1) && player.worldObj.isAirBlock(x, y + 1, z - 1)) {
                	player.worldObj.setBlock(x + 1, y + 1, z, BlockRegistry.shield);
                	player.worldObj.setBlock(x - 1, y + 1, z, BlockRegistry.shield);
                	player.worldObj.setBlock(x, y + 1, z + 1, BlockRegistry.shield);
                	player.worldObj.setBlock(x, y + 1, z - 1, BlockRegistry.shield);
            	}
            
            	if (y > y1 && (player.worldObj.isAirBlock(x + 1, y - 2, z) && player.worldObj.isAirBlock(x - 1, y - 2, z) && player.worldObj.isAirBlock(x, y - 2, z + 1) && player.worldObj.isAirBlock(x, y - 2, z - 1)) || (player.worldObj.getBlock(x + 1, y - 2, z) == BlockRegistry.shield && player.worldObj.getBlock(x - 1, y - 2, z) == BlockRegistry.shield && player.worldObj.getBlock(x, y - 2, z + 1) == BlockRegistry.shield && player.worldObj.getBlock(x, y - 2, z - 1) == BlockRegistry.shield)){       	
                	player.worldObj.setBlockToAir(x + 1, y - 2, z);
                	player.worldObj.setBlockToAir(x - 1, y - 2, z);
                	player.worldObj.setBlockToAir(x, y - 2, z + 1);
                	player.worldObj.setBlockToAir(x, y - 2, z - 1);
            	}
            
            	if (y < y1 && (player.worldObj.isAirBlock(x + 1, y + 2, z) && player.worldObj.isAirBlock(x - 1, y + 2, z) && player.worldObj.isAirBlock(x, y + 2, z + 1) && player.worldObj.isAirBlock(x, y + 2, z - 1)) || (player.worldObj.getBlock(x + 1, y + 2, z) == BlockRegistry.shield && player.worldObj.getBlock(x - 1, y + 2, z) == BlockRegistry.shield && player.worldObj.getBlock(x, y + 2, z + 1) == BlockRegistry.shield && player.worldObj.getBlock(x, y + 2, z - 1) == BlockRegistry.shield)){       	
                	player.worldObj.setBlockToAir(x + 1, y + 2, z);
                	player.worldObj.setBlockToAir(x - 1, y + 2, z);
                	player.worldObj.setBlockToAir(x, y + 2, z + 1);
                	player.worldObj.setBlockToAir(x, y + 2, z - 1);
            	}
            }
            
            y1 = y;
        }
    }

    @Override
    public void onPlayerStoppedUsingFocus(ItemStack itemstack, World world, EntityPlayer player, int count) {
        int x = MathHelper.floor_double(player.posX);
        int y = MathHelper.floor_double(player.posY);
        int z = MathHelper.floor_double(player.posZ);

        // Player Level
        if ((player.worldObj.getBlock(x + 1, y, z) == BlockRegistry.shield) && (player.worldObj.getBlock(x - 1, y, z) == BlockRegistry.shield) && (player.worldObj.getBlock(x, y, z + 1) == BlockRegistry.shield) && (player.worldObj.getBlock(x, y, z - 1) == BlockRegistry.shield)) {
            player.worldObj.setBlockToAir(x + 1, y, z);
            player.worldObj.setBlockToAir(x - 1, y, z);
            player.worldObj.setBlockToAir(x, y, z + 1);
            player.worldObj.setBlockToAir(x, y, z - 1);
        }

        // Above the player
        if ((player.worldObj.getBlock(x + 1, y + 1, z) == BlockRegistry.shield) && (player.worldObj.getBlock(x - 1, y + 1, z) == BlockRegistry.shield) && (player.worldObj.getBlock(x, y + 1, z + 1) == BlockRegistry.shield) && (player.worldObj.getBlock(x, y + 1, z - 1) == BlockRegistry.shield)) {
            player.worldObj.setBlockToAir(x + 1, y + 1, z);
            player.worldObj.setBlockToAir(x - 1, y + 1, z);
            player.worldObj.setBlockToAir(x, y + 1, z + 1);
            player.worldObj.setBlockToAir(x, y + 1, z - 1);
        }
    }
}
