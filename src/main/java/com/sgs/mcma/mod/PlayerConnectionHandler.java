package com.sgs.mcma.mod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import com.sgs.mcma.client.Client;

public class PlayerConnectionHandler
{
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		BaseMod.logger.info("PlayerConnectionHandler received PlayerLoggedInEvent");
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
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
		BaseMod.logger.info("PlayerConnectionHandler handled PlayerLoggedInEvent");
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event)
	{
		BaseMod.logger.info("PlayerConnectionHandler received PlayerLoggedOutEvent");
		final String name = event.player.getName();
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					new Client("localhost", 39640).PlayerLeft(name);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		thread.start();
		BaseMod.logger.info("PlayerConnectionHandler handled PlayerLoggedOutEvent");
	}
}
