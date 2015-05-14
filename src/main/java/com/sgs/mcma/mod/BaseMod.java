package com.sgs.mcma.mod;


import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "MCMA-Mod", version = "1.0")
public class BaseMod
{
    public static Logger logger;
    
	@Instance(value = "com.sgs.mcma.mod")
	public static BaseMod instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Configuration configs = new Configuration(event.getSuggestedConfigurationFile());
        try {
                configs.load();
        } catch (RuntimeException e) {
                logger.warn(e);
        }
    }
    
    @EventHandler
    public void Load(FMLInitializationEvent event){
    	logger.info("loaded");
    }
    
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new PlayerConnectionHandler());
		if(event.getSide().equals(Side.CLIENT))
		{
			MinecraftForge.EVENT_BUS.register(new ClientChatHandler(this));
		}
    	logger.info("initialized");
	}
	
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            
    }
    
   	public void logInfo(String message) {
   		this.logger.info(message);
   	}
    
   	public void logWarning(String message) {
   		this.logger.warn(message);
   	}
    
   	public void logSevere(String message) {
   		this.logger.error(message);
   	}

}
