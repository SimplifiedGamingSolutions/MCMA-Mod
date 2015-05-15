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

@Mod(modid = "MCMA-Mod", name = "MCMA-Mod", version = "1.0")
public class BaseMod
{
    public static Logger logger;
    private Configuration configs;
    private String HOST = "localhost";
    private int PORT = 39640;
    
	@Instance(value = "com.sgs.mcma.mod")
	public static BaseMod instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        configs = new Configuration(event.getSuggestedConfigurationFile());
    }
    
    @EventHandler
    public void Load(FMLInitializationEvent event){
        configs.load();
    	logInfo("loaded");
    }
    
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
    	configs.get("MCMA Client Settings", "host", "localhost");
    	configs.get("MCMA Client Settings", "port", "39640");
    	configs.save();
		FMLCommonHandler.instance().bus().register(new PlayerConnectionHandler(this, HOST, PORT));
		MinecraftForge.EVENT_BUS.register(new ServerChatHandler(this));
		logInfo("initialized");
	}
	
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            
    }
    
   	public void logInfo(String message) {
   		logger.info(message);
   	}
    
   	public void logWarning(String message) {
   		logger.warn(message);
   	}
    
   	public void logSevere(String message) {
   		logger.error(message);
   	}

}
