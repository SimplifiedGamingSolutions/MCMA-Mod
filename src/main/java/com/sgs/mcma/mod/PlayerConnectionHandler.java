package com.sgs.mcma.mod;

import java.io.IOException;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import com.sgs.mcma.client.Client;
import com.sgs.mcma.client.ClientException;

public class PlayerConnectionHandler
{
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		final String name = event.player.getName();
		Thread thread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					new Client("localhost", 39640).PlayerJoined(name);
					// System.out.println(result);
				} catch (ClientException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event)
	{
		final String name = event.player.getName();
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					new Client("localhost", 39640).PlayerLeft(name);
					// System.out.println(result);
				} catch (ClientException e)
				{S
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
