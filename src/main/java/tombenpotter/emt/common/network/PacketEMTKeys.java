package tombenpotter.emt.common.network;

import tombenpotter.emt.common.init.ItemRegistry;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketEMTKeys implements IMessage, IMessageHandler<PacketEMTKeys, IMessage> {

	@Override
	public IMessage onMessage(PacketEMTKeys message, MessageContext ctx) {
		if (ctx.getServerHandler().playerEntity.inventory.armorInventory[2] != null && ctx.getServerHandler().playerEntity.inventory.armorInventory[2].getItem() == ItemRegistry.quantumArmor) {
			ctx.getServerHandler().playerEntity.inventory.armorInventory[2].stackTagCompound.setBoolean("unequip", true);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
}
