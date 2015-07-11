package tombenpotter.emt.common.items.armor;

import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IC2Items;
import ic2.api.item.IElectricItemManager;
import ic2.core.IC2;
import ic2.core.IC2Achievements;
import ic2.core.IC2Potion;
import ic2.core.Ic2Items;
import ic2.core.Platform;
import ic2.core.audio.AudioManager;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import ic2.core.init.InternalName;
import ic2.core.init.MainConfig;
import ic2.core.item.ItemTinCan;
import ic2.core.item.armor.ItemArmorElectric;
import ic2.core.util.ConfigUtil;
import ic2.core.util.Keyboard;
import ic2.core.util.StackUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.client.model.ModelCpecialArmor;
import tombenpotter.emt.client.model.ModelWings;
import tombenpotter.emt.common.items.ItemRegistry;
import tombenpotter.emt.common.util.ConfigHandler;
import tombenpotter.emt.common.util.RandomHelper;
import tombenpotter.emt.common.util.TextHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class ItemEMTQuantum extends ItemArmorElectric {
	
	IIcon icon0;
	IIcon icon1;
	IIcon icon2;
	IIcon icon3;
	IIcon icon4;
	public int f = 0;
    boolean isHolding = false;
	protected static final Map<Integer, Integer> potionRemovalCost = new HashMap();
	private float jumpCharge;
	public static AudioSource audioSource;
	private static boolean lastJetpackUsed = false;
	
	public ItemEMTQuantum(InternalName internalName, int armorType1) {
		super(internalName, InternalName.quantum, armorType1, 2000000, 12000.0D, 4);
		
		MinecraftForge.EVENT_BUS.register(this);

		this.setCreativeTab(ElectroMagicTools.tabEMT);

		potionRemovalCost.put(Integer.valueOf(Potion.poison.id), Integer.valueOf(10000));
		potionRemovalCost.put(Integer.valueOf(IC2Potion.radiation.id), Integer.valueOf(10000));
		potionRemovalCost.put(Integer.valueOf(Potion.wither.id), Integer.valueOf(25000));
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icon0 = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest");
		icon1 = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_jetpack");
		icon2 = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_wing_t");
		icon3 = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_wing_n");
		icon4 = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_wing_q");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		return getIconFromDamage(par1);
	}

	@SubscribeEvent
	public void onEntityLivingFallEvent(LivingFallEvent event) {
		if ((IC2.platform.isSimulating()) && ((event.entity instanceof EntityLivingBase))) {
			EntityLivingBase entity = (EntityLivingBase) event.entity;
			ItemStack armor = entity.getEquipmentInSlot(1);
			if ((armor != null) && (armor.getItem() == this)) {
				int fallDamage = Math.max((int) event.distance - 10, 0);
				double energyCost = getEnergyPerDamage() * fallDamage;
				if (energyCost <= ElectricItem.manager.getCharge(armor)) {
					ElectricItem.manager.discharge(armor, energyCost, Integer.MAX_VALUE, true, false, false);
					event.setCanceled(true);
				}
			}
		}
	}
	
	public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase entity, ItemStack armor, DamageSource source, double damage, int slot)
	{
	    return super.getProperties(entity, armor, source, damage, slot);
	}

	public double getDamageAbsorptionRatio() {
		return 1.1D;
	}

	public int getEnergyPerDamage() {
		return 20000;
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(itemStack);
		
		byte toggleTimer = nbtData.getByte("toggleTimer");
		boolean ret = false;

		IC2.platform.profilerStartSection("QuantumBodyarmor");

		boolean jetpack = nbtData.getBoolean("jetpack");
		boolean hoverMode = nbtData.getBoolean("hoverMode");
		boolean jetpackUsed = false;
		if ((IC2.keyboard.isJumpKeyDown(player)) && (IC2.keyboard.isModeSwitchKeyDown(player)) && (toggleTimer == 0)) {
			toggleTimer = 30;
			hoverMode = !hoverMode;
			if (IC2.platform.isSimulating()) 
			{
				nbtData.setBoolean("hoverMode", hoverMode);
				if (hoverMode) 
				{
					IC2.platform.messagePlayer(player, "Hover Mode enabled.", new Object[0]);
				} else 
				{
					IC2.platform.messagePlayer(player, "Hover Mode disabled.", new Object[0]);
				}
			}
		}
		
		if(player.inventory.getCurrentItem() != null){
			if(IC2.keyboard.isSneakKeyDown(player) && (player.inventory.getCurrentItem().getUnlocalizedName().equals("ic2.itemArmorJetpackElectric")) && toggleTimer == 0){
				toggleTimer = 30;
				if (IC2.platform.isSimulating()) {
					player.inventory.armorInventory[2].stackTagCompound.setBoolean("jetpack", true);
					IC2.platform.messagePlayer(player, "Jetpack enabled.", new Object[0]);
					if(player.inventory.armorInventory[2] == itemStack){
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "Jetpack");
						player.inventory.armorInventory[2].stackTagCompound.setInteger("jetpackCharge", (int) ElectricItem.manager.getCharge(player.inventory.getCurrentItem()));
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					}
				}
			}
		}
		
		if(player.inventory.getCurrentItem() != null){
			if(IC2.keyboard.isSneakKeyDown(player) && (player.inventory.getCurrentItem().getUnlocalizedName().equals("item.EMT.wing.thaumium")) && toggleTimer == 0){
				toggleTimer = 30;
				if (IC2.platform.isSimulating()) {
					IC2.platform.messagePlayer(player, "Thaumium wings enabled.", new Object[0]);
					if(player.inventory.armorInventory[2] == itemStack){
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "TW");
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					}
				}
			}
		}
		
		if(player.inventory.getCurrentItem() != null){
			if(IC2.keyboard.isSneakKeyDown(player) && (player.inventory.getCurrentItem().getUnlocalizedName().equals("item.EMT.wing.nano")) && toggleTimer == 0){
				toggleTimer = 30;
				if (IC2.platform.isSimulating()) {
					IC2.platform.messagePlayer(player, "Nano wings enabled.", new Object[0]);
					if(player.inventory.armorInventory[2] == itemStack){
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "NW");
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					}
				}
			}
		}
		
		if(player.inventory.getCurrentItem() != null){
			if(IC2.keyboard.isSneakKeyDown(player) && (player.inventory.getCurrentItem().getUnlocalizedName().equals("item.EMT.wing.quantum")) && toggleTimer == 0){
				toggleTimer = 30;
				if (IC2.platform.isSimulating()) {
					IC2.platform.messagePlayer(player, "Quantum wings enabled.", new Object[0]);
					if(player.inventory.armorInventory[2] == itemStack){
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "QW");
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					}
				}
			}
		}
		
		if(player.inventory.getCurrentItem() == null && IC2.keyboard.isBoostKeyDown(player) && IC2.keyboard.isSneakKeyDown(player) && toggleTimer == 0 && IC2.platform.isSimulating()){
			toggleTimer = 30;
			if(player.inventory.armorInventory[2] == itemStack){
				if(player.inventory.getCurrentItem() == null){
					if(player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("Jetpack")){
						IC2.platform.messagePlayer(player, "Jetpack disabled.", new Object[0]);
						jetpack = false;
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "None");
		            	ItemStack charged = new ItemStack(IC2Items.getItem("electricJetpack").getItem());
		            	ElectricItem.manager.charge(charged, player.inventory.armorInventory[2].stackTagCompound.getInteger("jetpackCharge"), player.inventory.armorInventory[2].stackTagCompound.getInteger("jetpackCharge"), true, false);
		            	player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
					}
					
					if(player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("TW")){
						IC2.platform.messagePlayer(player, "Thaumium wings disabled.", new Object[0]);
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "None");
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ItemRegistry.thaumiumWing));
					}
					
					if(player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("NW")){
						IC2.platform.messagePlayer(player, "Nano wings disabled.", new Object[0]);
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "None");
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ItemRegistry.nanoWing));
					}
					
					if(player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("QW")){
						IC2.platform.messagePlayer(player, "Quantum wings disabled.", new Object[0]);
						player.inventory.armorInventory[2].stackTagCompound.setString("useother", "None");
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ItemRegistry.quantumWing));
					}
				}
			}
		}
		
		if ((jetpack) && ((IC2.keyboard.isJumpKeyDown(player)) && (player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("Jetpack")) || ((hoverMode) && (player.motionY < -0.029999999329447746D)))) {
			jetpackUsed = useJetpack(player, hoverMode, itemStack);
		}
		
		if ((player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("TW"))) {
			useWings(player, itemStack, world, 0.15f, 0.8f, 0.5f);
		}
		
		if ((player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("NW"))) {
			useWings(player, itemStack, world, 0.25f, 0.7f, 0.3f);
		}
		
		if ((player.inventory.armorInventory[2].stackTagCompound.getString("useother").equals("QW"))) {
			useWings(player, itemStack, world,  0.33f, 0.6f, 0.3f);
		}
		
		if ((IC2.platform.isSimulating()) && (toggleTimer > 0)) {
			toggleTimer = (byte) (toggleTimer - 1);

			nbtData.setByte("toggleTimer", toggleTimer);
		}
		if ((IC2.platform.isRendering()) && (player == IC2.platform.getPlayerInstance())) {
			if (lastJetpackUsed != jetpackUsed) {
				if (jetpackUsed) {
					if (audioSource == null) {
						audioSource = IC2.audioManager.createSource(player, PositionSpec.Backpack, "Tools/Jetpack/JetpackLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
					}
					if (audioSource != null) {
						audioSource.play();
					}
				} else if (audioSource != null) {
					audioSource.remove();
					audioSource = null;
				}
				lastJetpackUsed = jetpackUsed;
			}
			if (audioSource != null) {
				audioSource.updatePosition();
			}
		}
		
		if(world.isRemote)
			getIconIndex(itemStack);
		
		ret = jetpackUsed;

		player.extinguish();

		IC2.platform.profilerEndSection();

		if (ret) {
			player.inventoryContainer.detectAndSendChanges();
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconIndex(ItemStack stack){
		if((stack.stackTagCompound.getString("useother")).equals("Jetpack")){
			return icon1;
		}
		if((stack.stackTagCompound.getString("useother")).equals("TW")){
			return icon2;
		}
		if((stack.stackTagCompound.getString("useother")).equals("NW")){
			return icon3;
		}
		if((stack.stackTagCompound.getString("useother")).equals("QW")){
			return icon4;
		}
		return icon0;
	}
	
	@Override
	public int getItemEnchantability() {
        if (ConfigHandler.enchanting == false) {
            return 0;
        } else {
            return 4;
        }
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if((stack.stackTagCompound.getString("useother")).equals("Jetpack")){
			return ModInformation.texturePath + ":textures/models/quantum_jetpack.png";
		}
		if((stack.stackTagCompound.getString("useother")).equals("TW")){
			return ModInformation.texturePath + ":textures/models/quantum_wings_t.png";
		}
		if((stack.stackTagCompound.getString("useother")).equals("NW")){
			return ModInformation.texturePath + ":textures/models/quantum_wings_n.png";
		}
		if((stack.stackTagCompound.getString("useother")).equals("QW")){
			return ModInformation.texturePath + ":textures/models/quantum_wings_q.png";
		}
		return super.getArmorTexture(stack, entity, slot, type);
    }
	
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		if((stack.stackTagCompound.getString("useother")).equals("Jetpack")){
			ModelCpecialArmor mbm = new ModelCpecialArmor(1, 1);
			return mbm;
		}
		if((stack.stackTagCompound.getString("useother")).equals("TW") || (stack.stackTagCompound.getString("useother")).equals("NW") || (stack.stackTagCompound.getString("useother")).equals("QW")){
			ModelCpecialArmor mbm = new ModelCpecialArmor(1, 2);
			mbm.isJumping = stack.stackTagCompound.getBoolean("isJumping");
			return mbm;
		}
		return super.getArmorModel(entityLiving, stack, armorSlot);
    }
	
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(String.valueOf((int)ElectricItem.manager.getCharge(stack)) + " EU");
		if((stack.stackTagCompound.getString("useother")).equals("Jetpack")){
			list.add((stack.stackTagCompound.getInteger("jetpackCharge")) + " EU JETPACK");
		}
    }

	public boolean useJetpack(EntityPlayer player, boolean hoverMode, ItemStack stack) {
		int jetpackMaxCharge = 30000;
		
		ItemStack jetpack = player.inventory.armorInventory[2];
		if (stack.stackTagCompound.getInteger("jetpackCharge") == 0){
			return false;
		}
		float power = 1.0F;
		float dropPercentage = 0.05F;
		if (((float)stack.stackTagCompound.getInteger("jetpackCharge") / (float)jetpackMaxCharge) <= dropPercentage){
			power = (float) (power * (stack.stackTagCompound.getInteger("jetpackCharge") / (float)jetpackMaxCharge) * dropPercentage);
		}
		if (IC2.keyboard.isForwardKeyDown(player)) {
			float retruster = 3.5F;
			if (hoverMode) {
				retruster = 0.5F;
			}
			float forwardpower = power * retruster * 2.0F;
			if (forwardpower > 0.0F) {
				player.moveFlying(0.0F, 0.4F * forwardpower, 0.02F);
			}
		}
		
		int worldHeight = IC2.getWorldHeight(player.worldObj);

		double y = player.posY;
		if (y > worldHeight - 25) {
			if (y > worldHeight) {
				y = worldHeight;
			}
			power = (float) (power * ((worldHeight - y) / 25.0D));
		}
		double prevmotion = player.motionY;
		player.motionY = Math.min(player.motionY + power * 0.2F, 0.6000000238418579D);
		if (hoverMode) {
			float maxHoverY = -0.025F;
			if (IC2.keyboard.isSneakKeyDown(player)) {
				maxHoverY = -0.1F;
			}
			if (IC2.keyboard.isJumpKeyDown(player)) {
				maxHoverY = 0.1F;
			}
			if (player.motionY > maxHoverY) {
				player.motionY = maxHoverY;
				if (prevmotion > player.motionY) {
					player.motionY = prevmotion;
				}
			}
		}
		double consume = 8.0D;
		if (hoverMode) {
			consume = 10.0D;
		}
		stack.stackTagCompound.setInteger("jetpackCharge", (int)(stack.stackTagCompound.getInteger("jetpackCharge") - consume));
		player.fallDistance = 0.0F;
		player.distanceWalkedModified = 0.0F;
		IC2.platform.resetPlayerInAirTime(player);
		return true;
	}
	
	void useWings(EntityPlayer player, ItemStack stack, World world, float motionY, float motionXZ, float f1){
    	NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
    	
    	nbtData.setBoolean("isJumping", IC2.keyboard.isJumpKeyDown(player));
    	if(nbtData.getBoolean("isJumping")){
        	nbtData.setBoolean("isHolding", true);
            f += 1;
            if(f > 7) f = 7;
        }
        else if(nbtData.getBoolean("isHolding")){
            nbtData.setBoolean("isHolding", false);	
            player.motionY = motionY * f;
            player.motionX /= motionXZ;
            player.motionZ /= motionXZ;
            f = 0;
        }

        if (nbtData.getBoolean("isJumping") && !player.onGround && player.motionY < 0 && !player.capabilities.isCreativeMode)
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