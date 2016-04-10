package emt.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.ModInformation;
import emt.util.EMTTextHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Random;

public class ItemOneRing extends ItemBase implements IBauble {

	public IIcon[] icon = new IIcon[16];
	public Random random = new Random();

	public ItemOneRing() {
		super("bauble");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		switch (itemstack.getItemDamage()) {
		case 0: {
			name = "oneRing";
			break;
		}
		default:
			name = "nothing";
			break;
		}
		return "item." + ModInformation.MODID + ".bauble." + name;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ri) {
		this.icon[0] = ri.registerIcon(ModInformation.TEXTURE_PATH + ":onering");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
		list.add(new ItemStack(this, 1, 0));
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		if (stack.getItemDamage() <= 0) {
			return BaubleType.RING;
		}
		else {
			return null;
		}
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		if (!player.isInvisible()) {
			player.setInvisible(true);
		}
		NBTTagCompound tag = new NBTTagCompound();
		((EntityPlayer) player).writeToNBT(tag);
		NBTTagCompound forgeTag = tag.getCompoundTag("ForgeData");

		int corruption = 0;

		if (forgeTag.hasKey("MindCorruption"))
			corruption = forgeTag.getInteger("MindCorruption");
		else
			forgeTag.setInteger("MindCorruption", 0);

		((EntityPlayer) player).capabilities.disableDamage = true;

		if (!player.worldObj.isRemote) {
			if (corruption <= 0) {
				((EntityPlayer) player).addChatMessage(new ChatComponentText(EMTTextHelper.PURPLE + "You have worn the Ring. Your soul has now been forever " + EMTTextHelper.PURPLE + "tainted. " + EMTTextHelper.RED + EMTTextHelper.ITALIC + "Beware of wearing the ring. The tainting will only " + EMTTextHelper.RED + EMTTextHelper.ITALIC + "increase, and strange things will start happening."));
			}
			else if (corruption > 6000 && corruption < 24000 && random.nextInt(2000) == 0) {
				player.addPotionEffect(new PotionEffect(Potion.blindness.id, 500, 2, false));
			}
			else if (corruption >= 6000 && corruption < 24000 && random.nextInt(2000) == 0) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 500, 2, false));
			}
			else if (corruption >= 24000 && corruption < 72000 && random.nextInt(2000) == 0) {
				((EntityPlayer) player).capabilities.disableDamage = false;

				player.attackEntityFrom(DamageSource.magic, 5);
			}
			else if (corruption >= 72000 && corruption < 120000 && random.nextInt(4000) == 0) {
				((EntityPlayer) player).capabilities.disableDamage = false;

				player.motionY += 2d;
			}
			else if (corruption >= 120000 && random.nextInt(10000) == 0) {
				((EntityPlayer) player).capabilities.disableDamage = false;

				player.addPotionEffect(new PotionEffect(Potion.wither.id, 5000, 4, false));
			}
			else if (corruption + 100 >= Integer.MAX_VALUE) { // =3333333
				player.isDead = true;
			}
		}
		forgeTag.setInteger("MindCorruption", ++corruption);
		tag.setTag("ForgeData", forgeTag);
		((EntityPlayer) player).readFromNBT(tag);
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {
		NBTTagCompound tag = new NBTTagCompound();
		((EntityPlayer) player).writeToNBT(tag);
		tag.getCompoundTag("ForgeData").setInteger("MindCorruption", 0);
		((EntityPlayer) player).readFromNBT(tag);

	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		player.setInvisible(false);
		if (!((EntityPlayer) player).capabilities.isCreativeMode)
			((EntityPlayer) player).capabilities.disableDamage = false;
		NBTTagCompound tag = new NBTTagCompound();
		((EntityPlayer) player).writeToNBT(tag);
		tag.removeTag("ForgeData");
		((EntityPlayer) player).readFromNBT(tag);
		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 500, 2, false));
	}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase player) {
		return stack.getItemDamage() == 0 && player instanceof EntityPlayer;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
		NBTTagCompound tag = new NBTTagCompound();
		((EntityPlayer) player).writeToNBT(tag);
		return tag.getCompoundTag("ForgeData").getInteger("MindCorruption") > 600 ? true : false;
	}
}
