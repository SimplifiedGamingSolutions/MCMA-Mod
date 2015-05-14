package com.sgs.mcma.mod;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = MCMACommunicationMod.MODID, version = MCMACommunicationMod.VERSION)
public class MCMACommunicationMod
{
	public static final String MODID = "MCMA-CommunicationMod";
	public static final String VERSION = "1.0";

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if (event.getSide() == Side.SERVER)
		{
			FMLCommonHandler.instance().bus()
					.register(new PlayerConnectionHandler());
		} else if (event.getSide() == Side.CLIENT)
		{
		}
	}

}
