package tombenpotter.emt.common.items.armor.chestplates;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tombenpotter.emt.ElectroMagicTools;
import tombenpotter.emt.ModInformation;
import tombenpotter.emt.client.model.ModelCpecialArmor;
import tombenpotter.emt.client.model.ModelWings;
import tombenpotter.emt.common.init.ItemRegistry;
import tombenpotter.emt.common.items.armor.wings.ItemNanoWing;
import tombenpotter.emt.common.items.armor.wings.ItemQuantumWing;
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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class ItemInfusedQuantumChestplate extends ItemArmorElectric {

	IIcon iconNONE;
	IIcon iconJETPACK;
	IIcon iconTHAUMIUM;
	IIcon iconNANO;
	IIcon iconQUANTUM;
	Random rnd;

	public final byte NONE = 0;
	public final byte JETPACK = 1;
	public final byte THAUMIUM = 2;
	public final byte NANO = 3;
	public final byte QUANTUM = 4;

	protected static ArrayList<Integer> potionRemovalCost = new ArrayList<Integer>();
	public static AudioSource audioSource;
	private static boolean lastJetpackUsed = false;

	public ItemInfusedQuantumChestplate(InternalName internalName, int armorType) {
		super(internalName, InternalName.quantum, armorType, 2000000, 12000.0D, 4);
		MinecraftForge.EVENT_BUS.register(this);
		this.setCreativeTab(ElectroMagicTools.tabEMT);
		potionRemovalCost.add(Potion.poison.id);
		potionRemovalCost.add(IC2Potion.radiation.id);
		potionRemovalCost.add(Potion.wither.id);
		rnd = new Random();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		iconNONE = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest");
		iconJETPACK = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_jetpack");
		iconTHAUMIUM = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_wing_t");
		iconNANO = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_wing_n");
		iconQUANTUM = iconRegister.registerIcon(ModInformation.texturePath + ":armor/armor_quantum_chest_wing_q");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return "item." + ModInformation.modid + ".chestplates.infusedQuantum";
	}
	
    public String getItemStackDisplayName(ItemStack stack)
    {
        return ("" + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
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

	@Override
	public double getDamageAbsorptionRatio() {
		return 1.1D;
	}

	@Override
	public int getEnergyPerDamage() {
		return 20000;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);

		byte toggleTimer = nbt.getByte("toggleTimer");
		boolean ret = false;
		boolean hoverMode = nbt.getBoolean("hoverMode");
		boolean jetpackUsed = false;
		byte wing = nbt.getByte("wing");

		if (!world.isRemote) {
			
			for (PotionEffect effect : new LinkedList<PotionEffect>(player.getActivePotionEffects())) {
				if(potionRemovalCost.contains(new Integer(effect.getPotionID()))){
					 IC2.platform.removePotion(player, effect.getPotionID());
				}
			}
			
			if (nbt.hasKey("useother")) {
				String useother = nbt.getString("useother");
				if (useother.equals("Jetpack")) {
					nbt.setByte("wing", JETPACK);
				}
				if (useother.equals("TW")) {
					nbt.setByte("wing", THAUMIUM);
				}
				if (useother.equals("NW")) {
					nbt.setByte("wing", NANO);
				}
				if (useother.equals("QW")) {
					nbt.setByte("wing", QUANTUM);
				}
				wing = nbt.getByte("wing");
				nbt.removeTag("useother");
			}

			if (wing == 0) {
				nbt.setByte("wing", NONE);
			}

			if ((IC2.keyboard.isJumpKeyDown(player)) && (IC2.keyboard.isModeSwitchKeyDown(player)) && (toggleTimer == 0)) {
				toggleTimer = 30;
				hoverMode = !hoverMode;
				if (IC2.platform.isSimulating()) {
					nbt.setBoolean("hoverMode", hoverMode);
					if (hoverMode) {
						IC2.platform.messagePlayer(player, "Hover Mode enabled.", new Object[0]);
					}
					else {
						IC2.platform.messagePlayer(player, "Hover Mode disabled.", new Object[0]);
					}
				}
			}

			if (player.inventory.getCurrentItem() != null && player.isSneaking() && toggleTimer == 0 && wing == NONE) {
				if (player.inventory.getCurrentItem().getItem() == IC2Items.getItem("electricJetpack").getItem()) {
					IC2.platform.messagePlayer(player, "Jetpack enabled.", new Object[0]);
					nbt.setByte("wing", JETPACK);
					int charge = (int) ElectricItem.manager.getCharge(itemStack);
					if (ElectricItem.manager.getCharge(player.inventory.getCurrentItem()) < 30000) {
						ElectricItem.manager.use(itemStack, 30000 - ElectricItem.manager.getCharge(player.inventory.getCurrentItem()), player);
					}
					nbt.setInteger("jetpackCharge", (int) (ElectricItem.manager.getCharge(player.inventory.getCurrentItem()) + charge - (int) ElectricItem.manager.getCharge(itemStack)));
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

				}

				else if (player.inventory.getCurrentItem().getItem() == ItemRegistry.thaumiumWing) {
					IC2.platform.messagePlayer(player, "Thaumium wings enabled.", new Object[0]);
					nbt.setByte("wing", THAUMIUM);
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}

				else if (player.inventory.getCurrentItem().getItem() == ItemRegistry.nanoWing) {
					IC2.platform.messagePlayer(player, "Nano wings enabled.", new Object[0]);
					nbt.setByte("wing", NANO);
					int charge = (int) ElectricItem.manager.getCharge(itemStack);
					if (ElectricItem.manager.getCharge(player.inventory.getCurrentItem()) < ItemNanoWing.maxCharge) {
						ElectricItem.manager.use(itemStack, ItemNanoWing.maxCharge - ElectricItem.manager.getCharge(player.inventory.getCurrentItem()), player);
					}
					nbt.setInteger("NWCharge", (int) (ElectricItem.manager.getCharge(player.inventory.getCurrentItem()) + charge - (int) ElectricItem.manager.getCharge(itemStack)));
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}

				else if (player.inventory.getCurrentItem().getItem() == ItemRegistry.quantumWing) {
					IC2.platform.messagePlayer(player, "Quantum wings enabled.", new Object[0]);
					nbt.setByte("wing", QUANTUM);
					int charge = (int) ElectricItem.manager.getCharge(itemStack);
					if (ElectricItem.manager.getCharge(player.inventory.getCurrentItem()) < ItemQuantumWing.maxCharge) {
						ElectricItem.manager.use(itemStack, ItemQuantumWing.maxCharge - ElectricItem.manager.getCharge(player.inventory.getCurrentItem()), player);
					}
					nbt.setInteger("QWCharge", (int) (ElectricItem.manager.getCharge(player.inventory.getCurrentItem()) + charge - (int) ElectricItem.manager.getCharge(itemStack)));
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}

			if (player.inventory.getCurrentItem() == null && nbt.getBoolean("unequip") && player.isSneaking() && toggleTimer == 0 && IC2.platform.isSimulating()) {
				toggleTimer = 30;

				if (wing == JETPACK) {
					IC2.platform.messagePlayer(player, "Jetpack disabled.", new Object[0]);
					nbt.setByte("wing", NONE);
					ItemStack charged = new ItemStack(IC2Items.getItem("electricJetpack").getItem());
					if (nbt.getInteger("jetpackCharge") > 0)
						ElectricItem.manager.charge(charged, nbt.getInteger("jetpackCharge"), 1, true, false);
					else
						ElectricItem.manager.charge(charged, 0, 1, true, false);
					player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
				}

				if (wing == THAUMIUM) {
					IC2.platform.messagePlayer(player, "Thaumium wings disabled.", new Object[0]);
					nbt.setByte("wing", NONE);
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ItemRegistry.thaumiumWing));
				}

				if (wing == NANO) {
					IC2.platform.messagePlayer(player, "Nano wings disabled.", new Object[0]);
					nbt.setByte("wing", NONE);
					ItemStack charged = new ItemStack(ItemRegistry.nanoWing);
					if (nbt.getInteger("NWCharge") > 0)
						ElectricItem.manager.charge(charged, nbt.getInteger("NWCharge"), 3, true, false);
					else
						ElectricItem.manager.charge(charged, 0, 3, true, false);
					player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
				}

				if (wing == QUANTUM) {
					IC2.platform.messagePlayer(player, "Quantum wings disabled.", new Object[0]);
					nbt.setByte("wing", NONE);
					ItemStack charged = new ItemStack(ItemRegistry.quantumWing);
					if (nbt.getInteger("QWCharge") > 0)
						ElectricItem.manager.charge(charged, nbt.getInteger("QWCharge"), 3, true, false);
					else
						ElectricItem.manager.charge(charged, 0, 3, true, false);
					player.inventory.setInventorySlotContents(player.inventory.currentItem, charged);
				}

				nbt.setBoolean("unequip", false);
			}
		}

		if ((IC2.keyboard.isJumpKeyDown(player) && wing == JETPACK) || (hoverMode && player.motionY < -0.029999999329447746D)) {
			jetpackUsed = useJetpack(player, hoverMode, itemStack);
		}

		if (wing == THAUMIUM)
			useWing(player, itemStack, world, 0.15f, 0.7f, 0.5f, 0, false);
		if (wing == NANO)
			useWing(player, itemStack, world, 0.25f, 0.6f, 0.3f, 5, true);
		if (wing == QUANTUM)
			useWing(player, itemStack, world, 0.33f, 0.5f, 0.2f, 7, true);

		if ((IC2.platform.isSimulating()) && (toggleTimer > 0)) {
			toggleTimer = (byte) (toggleTimer - 1);
			nbt.setByte("toggleTimer", toggleTimer);
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
				}
				else if (audioSource != null) {
					audioSource.remove();
					audioSource = null;
				}
				lastJetpackUsed = jetpackUsed;
			}
			if (audioSource != null) {
				audioSource.updatePosition();
			}
		}

		ret = jetpackUsed;

		player.extinguish();

		if (ret) {
			player.inventoryContainer.detectAndSendChanges();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconIndex(ItemStack stack) {
		try {
			byte wing = stack.stackTagCompound.getByte("wing");

			switch (wing) {
			case JETPACK:
				return iconJETPACK;
			case THAUMIUM:
				return iconTHAUMIUM;
			case NANO:
				return iconNANO;
			case QUANTUM:
				return iconQUANTUM;
			default:
				return iconNONE;
			}
		} catch (Exception e) {
			return iconNONE;
		}
	}

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		try {
			byte wing = stack.stackTagCompound.getByte("wing");

			switch (wing) {
			case JETPACK:
				return iconJETPACK;
			case THAUMIUM:
				return iconTHAUMIUM;
			case NANO:
				return iconNANO;
			case QUANTUM:
				return iconQUANTUM;
			default:
				return iconNONE;
			}
		} catch (Exception e) {
			return iconNONE;
		}
	}

	@Override
	public int getItemEnchantability() {
		if (ConfigHandler.enchanting == false)
			return 0;
		else
			return 4;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		byte wing = stack.stackTagCompound.getByte("wing");

		switch (wing) {
		case JETPACK:
			return ModInformation.texturePath + ":textures/models/quantum_jetpack.png";
		case THAUMIUM:
			return ModInformation.texturePath + ":textures/models/quantum_wings_t.png";
		case NANO:
			return ModInformation.texturePath + ":textures/models/quantum_wings_n.png";
		case QUANTUM:
			return ModInformation.texturePath + ":textures/models/quantum_wings_q.png";
		default:
			return ModInformation.texturePath + ":textures/models/quantum.png";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int armorSlot) {
		try {
			if (entity instanceof EntityPlayer) {
				byte wing = stack.stackTagCompound.getByte("wing");
				if (wing == JETPACK) {
					ModelCpecialArmor mbm = new ModelCpecialArmor(1, 1);
					return mbm;
				}
				else if (wing != 0) {
					ModelCpecialArmor mbm = new ModelCpecialArmor(1, 2);
					mbm.isJumping = stack.stackTagCompound.getBoolean("isJumping");
					return mbm;
				}
			}
		} catch (NullPointerException e) {
			new ModelCpecialArmor(1, 0);
		}
		return new ModelCpecialArmor(1, 0);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		try {
			list.add(StatCollector.translateToLocal("ic2.item.tooltip.PowerTier") + " " + tier);
		} catch (NullPointerException e) {}
	}

	public boolean useJetpack(EntityPlayer player, boolean hoverMode, ItemStack stack) {
		int jetpackMaxCharge = 30000;
		ItemStack jetpack = stack;
		if (ElectricItem.manager.getCharge(stack) <= 0)
			return false;
		float power = 1.0F;
		float dropPercentage = 0.05F;
		if (((float) ElectricItem.manager.getCharge(stack) / (float) jetpackMaxCharge) <= dropPercentage) {
			power = (float) (power * (ElectricItem.manager.getCharge(stack) / (float) jetpackMaxCharge) * dropPercentage);
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
		ElectricItem.manager.use(stack, consume, player);
		player.fallDistance = 0.0F;
		player.distanceWalkedModified = 0.0F;
		IC2.platform.resetPlayerInAirTime(player);
		return true;
	}

	void useWing(EntityPlayer player, ItemStack stack, World world, float motionY, float motionXZ, float f1, int amount, boolean isElectric) {
		NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
		boolean isJumping = IC2.keyboard.isJumpKeyDown(player);
		nbtData.setBoolean("isJumping", isJumping);

		if (isJumping) {
			byte f = nbtData.getByte("f");
			nbtData.setBoolean("isHolding", true);
			nbtData.setByte("f", (byte) (f + 1));
			if (f > 7)
				nbtData.setByte("f", (byte) 7);
		}
		else if (nbtData.getBoolean("isHolding")) {
			byte f = nbtData.getByte("f");
			nbtData.setBoolean("isHolding", false);
			player.motionY = motionY * f;
			if(isElectric)
				ElectricItem.manager.use(stack, ((motionY * f) * 10) * amount, player);
			if (player.motionX < 0.5 && player.motionZ < 0.5 && player.motionX > -0.5 && player.motionZ > -0.5) {
				player.motionX /= motionXZ;
				player.motionZ /= motionXZ;
			}
			world.playSoundEffect(player.posX, player.posY, player.posZ, "mob.ghast.fireball", 1, 1);
			for (int i = 0; i < 4; i++) {
				world.spawnParticle("cloud", player.posX - 1 + (rnd.nextInt(100) / 50d), player.posY - 1, player.posZ - 1 + (rnd.nextInt(100) / 50d), 0, -0.5, 0);
			}

			nbtData.setByte("f", (byte) 0);
		}

		if (isJumping && !player.onGround && player.motionY < 0) {
			player.motionY *= f1;
		}

		if (player.isInWater() && !player.capabilities.isCreativeMode) {
			player.motionY += -0.05;
		}

		if (ConfigHandler.impactOfRain) {
			if ((world.getBiomeGenForCoords((int) player.posX, (int) player.posZ)).canSpawnLightningBolt() && world.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ) && world.isRaining() && !player.capabilities.isCreativeMode) {
				player.motionY += -0.05;
			}
		}

		if (player.isSneaking() && !player.onGround && player.motionY < 0) {
			player.motionY *= 0.6;
		}

		if (player.fallDistance > 0.0F) {
			player.fallDistance = 0;
		}
	}
}