package com.sgs.mcma.mod;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientChatHandler {
	BaseMod base;
	public ClientChatHandler(BaseMod base)
	{
		this.base = base;
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onChatMessageReceived(ClientChatReceivedEvent event) {
		base.logInfo("ClientChatHandler received ClientChatReceivedEvent");
		base.logInfo("ClientChatHandler handled ClientChatReceivedEvent");
	}
}
 