package tombenpotter.emt.common.items.armor.wings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.common.util.ConfigHandler;
import tombenpotter.emt.common.util.TextHelper;
import ic2.core.IC2;
import ic2.core.util.StackUtil;

import java.util.List;

public class ItemThaumiumReinforcedWing extends ItemFeatherWing implements IVisDiscountGear, IRepairable {

    public ItemThaumiumReinforcedWing(ArmorMaterial material, int par3, int par4) {
        super(material, par3, par4);
        this.setMaxStackSize(1);
        this.setMaxDamage(250);
        this.setCreativeTab(ElectroMagicTools.tabEMT);
        this.isDamageable();
        visDiscount = 4;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.texturePath + ":armor/wing_thaumium");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.texturePath + ":textures/models/thaumiumwing.png";
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(TextHelper.localize("tooltip.EMT.visDiscount") + ": " + String.valueOf(visDiscount) + "%");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    	this.updateWings(player, stack, world, 0.15f, 0.7f, 0.5f, 0);
    }
}
