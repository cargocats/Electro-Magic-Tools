package tombenpotter.emt.common.items.armor.wings;

import java.util.Random;

import ic2.core.IC2;
import ic2.core.util.StackUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.client.model.ModelWings;
import tombenpotter.emt.common.util.ConfigHandler;

public class ItemFeatherWing extends ItemArmor {

    public int visDiscount = 0;
    Random rnd;

    public ItemFeatherWing(ArmorMaterial material, int par3, int par4) {
        super(material, par3, par4);
        this.setMaxStackSize(1);
        this.setMaxDamage(120);
        this.setCreativeTab(ElectroMagicTools.tabEMT);
        this.isDamageable();
        rnd = new Random();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(ModInformation.texturePath + ":armor/wing_feather");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ModInformation.texturePath + ":textures/models/featherwing.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int armorSlot) {
    	if(entity instanceof EntityPlayer){
    		ModelWings mw = new ModelWings();
    		mw.isJumping = stack.stackTagCompound.getBoolean("isJumping");
    		return mw;
    	}
    	return new ModelWings();
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    	updateWings(player, stack, world, 0.09f, 0.9f, 0.9f);
    }
    
    public void updateWings(EntityPlayer player, ItemStack stack, World world, float motionY, float motionXZ, float f1){
    	NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
    	
    	nbtData.setBoolean("isJumping", IC2.keyboard.isJumpKeyDown(player));
    	
    		if(nbtData.getBoolean("isJumping")){
        		nbtData.setBoolean("isHolding", true);
        		nbtData.setInteger("f", nbtData.getInteger("f") + 1);
            	if(nbtData.getInteger("f") > 7) nbtData.setInteger("f", 7);
        	}
            else if(nbtData.getBoolean("isHolding")){
            	nbtData.setBoolean("isHolding", false);
            		
            	player.motionY = motionY * nbtData.getInteger("f");
            	if(player.motionX < 0.7 && player.motionZ < 0.7){
            		player.motionX /= motionXZ;
            		player.motionZ /= motionXZ;
            	}
            	world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.ghast.fireball", 1, 1);
            	for(int i = 0; i < 4; i++){
            		world.spawnParticle("cloud", player.posX - 1 + (rnd.nextInt(100) / 50d), player.posY - 1, player.posZ - 1 + (rnd.nextInt(100) / 50d), 0, -0.5, 0);
            	}
            	nbtData.setInteger("f", 0);
            }

        	if (nbtData.getBoolean("isJumping") && !player.onGround && player.motionY < 0)
            	player.motionY *= f1;

        	if (player.isInWater() && !player.capabilities.isCreativeMode) player.motionY += -0.2;
            
        	if(ConfigHandler.impactOfRain){
        		if ((player.worldObj.isRaining() || player.worldObj.isThundering()) && !player.capabilities.isCreativeMode)
            			player.motionY = -0.3;
        	}

        	if (player.isSneaking() && !player.onGround) player.motionY = -0.6;
        
        if (player.fallDistance > 0.0F) player.fallDistance = 0;
    }
}
