package com.sgs.mcma.mod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import com.sgs.mcma.client.Client;

public class PlayerConnectionHandler
{
	BaseMod base;
	Client client;
	public PlayerConnectionHandler(BaseMod base, String host, int port)
	{
		this.base = base;
		this.client = new Client(host, port);
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		base.logInfo("PlayerConnectionHandler received PlayerLoggedInEvent");
		playerJoined(event.player.getName());
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event)
	{
		base.logInfo("PlayerConnectionHandler received PlayerLoggedOutEvent");
		playerLeft(event.player.getName());
	}

	private void playerJoined(String name)
	{
		try
		{
			client.PlayerJoined(name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void playerLeft(String name)
	{
		try
		{
			client.PlayerLeft(name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
