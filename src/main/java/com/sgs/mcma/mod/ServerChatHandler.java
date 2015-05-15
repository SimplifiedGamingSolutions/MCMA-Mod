package com.sgs.mcma.mod;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ServerChatHandler
{
	BaseMod base;
	public ServerChatHandler(BaseMod base)
	{
		this.base = base;
	}
	@SubscribeEvent
	public void onChatReceived(ServerChatEvent event) {
		FMLLog.getLogger().info("[CHAT]: "+ event.component.getUnformattedText());
	}
}
