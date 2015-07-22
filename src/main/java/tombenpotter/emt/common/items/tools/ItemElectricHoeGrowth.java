/*******************************************************************************
 * Copyright (c) 2014 Tombenpotter.
 * All rights reserved. 
 *
 * This program and the accompanying materials are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at http://www.gnu.org/licenses/gpl.html
 *
 * This class was made by Tombenpotter and is distributed as a part of the Electro-Magic Tools mod.
 * Electro-Magic Tools is a derivative work on Thaumcraft 4 (c) Azanor 2012.
 * http://www.minecraftforum.net/topic/1585216-
 ******************************************************************************/

package tombenpotter.emt.common.items.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockCustomPlant;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.equipment.ItemElementalHoe;
import thaumcraft.common.lib.utils.Utils;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;

import java.util.List;

public class ItemElectricHoeGrowth extends ItemHoe implements IElectricItem {

	public IIcon icon;
    public int maxCharge = 1000000;
    public int growthCost = 1000;
    public int tillCost = 100;

    public ItemElectricHoeGrowth() {
        super(ToolMaterial.EMERALD);
        this.setCreativeTab(ElectroMagicTools.tabEMT);
        this.setMaxStackSize(1);
        this.growthCost = 10000;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
	    this.icon = iconRegister.registerIcon(ModInformation.texturePath + ":tools/hoe_growth");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1);
        if (getChargedItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
            itemList.add(charged);
        }
        if (getEmptyItem(itemStack) == this) {
            itemList.add(new ItemStack(this, 1, getMaxDamage()));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
    	return this.icon;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
    	if(ElectricItem.manager.getCharge(stack) > 10000){
    		boolean did = false;
    		for (int x1 = -1; x1 <= 1; x1++) {
    			for (int z1 = -1; z1 <= 1; z1++) {
    				if (super.onItemUse(stack, player, world, x + x1, y, z + z1, par7, par8, par9, par10))
    				{
    					ElectricItem.manager.discharge(stack, 250, 2, true, false, false);
    					Thaumcraft.proxy.blockSparkle(world, x + x1, y, z + z1, 8401408, 2);
    					if (!did) {
    						did = true;
    					}
    				}
    			}
    		}
    		if (!did)
    			{
    				did = Utils.useBonemealAtLoc(world, player, x, y, z);
    				if (!did)
    				{
    					Block block = world.getBlock(x, y, z);
    					int meta = world.getBlockMetadata(x, y, z);
    					if ((block == ConfigBlocks.blockCustomPlant) && (meta == 0) && (ElectricItem.manager.getCharge(stack) + 20 <= this.getMaxCharge(stack)))
    					{
    						((BlockCustomPlant)block).growGreatTree(world, x, y, z, world.rand);
    						ElectricItem.manager.discharge(stack, 50, 2, true, false, false);
    						Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
    						did = true;
    					}
    					else if ((block == ConfigBlocks.blockCustomPlant) && (meta == 1) && (ElectricItem.manager.getCharge(stack) + 150 <= this.getMaxCharge(stack)))
    					{
    						((BlockCustomPlant)block).growSilverTree(world, x, y, z, world.rand);
    						ElectricItem.manager.discharge(stack, 250, 2, true, false, false);
    						Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
    						did = true;
    					}
    				}
    				else
    				{
    					ElectricItem.manager.discharge(stack, 1, 2, true, false, false);
    					Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 3);
    				}
    				if (did) {
    					world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 0.75F, 0.9F + world.rand.nextFloat() * 0.2F);
    				}
    			}
    		return did;
      	}
    	else
    		return false;
    }

    /* IC2 API METHODS */

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 1;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return 1000;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack) {
    	return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }
}
