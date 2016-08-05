package vazkii.vocation.common.network;

import java.io.IOException;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import vazkii.vocation.common.Vocation;

public class PacketSendMessage implements IMessage, IMessageHandler<PacketSendMessage, IMessage> {

	public String id;

	public PacketSendMessage() { }

	public PacketSendMessage(String id) {
		this.id = id;
	}

	public void toBytes(ByteBuf buffer) {
		PacketBuffer packet = new PacketBuffer(buffer);
		try {
			packet.writeStringToBuffer(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fromBytes(ByteBuf buffer) {
		PacketBuffer packet = new PacketBuffer(buffer);
		try {
			id = packet.readStringFromBuffer(64);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IMessage onMessage(PacketSendMessage message, MessageContext context) {
		Vocation.proxy.showMessage(message.id);
		return null;
	}

}
