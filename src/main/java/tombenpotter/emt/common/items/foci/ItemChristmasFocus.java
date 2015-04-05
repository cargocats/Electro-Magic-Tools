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

package tombenpotter.emt.common.items.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;

public class ItemChristmasFocus extends ItemBaseFocus {

    private static final AspectList visCost = new AspectList().add(Aspect.ORDER, 500).add(Aspect.AIR, 500);

    public ItemChristmasFocus() {
        super("base.focus.christmas", "focus_christmas" );
    }

    @Override
    public int getFocusColor(ItemStack stack) {
        return 0x00FFFF;
    }

    @Override
    public AspectList getVisCost(ItemStack stack) {
        return visCost;
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "CHRISTMAS";
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
        if ((mop != null) && (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)) {
            int x = mop.blockX;
            int y = mop.blockY + 1;
            int z = mop.blockZ;
            if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, true)) {
                if (!world.isRemote) {
                    EntitySnowman snowman;
                    snowman = new EntitySnowman(world);
                    snowman.setPosition(x, y, z);
                    world.spawnEntityInWorld(snowman);
                }
            }
            player.swingItem();
        }
        return itemstack;
    }
}
