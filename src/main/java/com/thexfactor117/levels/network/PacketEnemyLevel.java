package com.thexfactor117.levels.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * @author TheXFactor117
 *
 */
public class PacketEnemyLevel implements IMessage
{
	private int level;
	
	public PacketEnemyLevel() {}
	
	public PacketEnemyLevel(int level)
	{
		this.level = level;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.level = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(level);
	}
	
	public static class Handler implements IMessageHandler<PacketEnemyLevel, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketEnemyLevel message, MessageContext ctx) 
		{			
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(new Runnable()
			{
				@Override
				public void run() 
				{	
					//Levels.LOGGER.info("Packet Level: " + message.level);
				}
			});
			
			return null;
		}
	}
}
