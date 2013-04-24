package com.creatifcubed.minecraft.forgepermissions;

import java.util.logging.Logger;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.Property;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = ForgePermissions.MOD_ID, name = ForgePermissions.MOD_NAME, version = ForgePermissions.MOD_VERSION)
public class ForgePermissions {
	
	public static final String MOD_ID = "ForgePermissions";
	public static final String MOD_NAME = "Forge Permissions";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_URL = "http://fp.creatifcubed.com";
	public static final String[] MOD_AUTHOR = new String[] {
		"Weedle",
		"Raekye",
	};
	public static final String MOD_COMMONPROXY = "com.creatifcubed.minecraft.forgepermissions.FPCommonProxy";
	public static final String MOD_CLIENTPROXY = "com.creatifcubed.minecraft.forgepermissions.FPClientProxy";

	public static final Logger log = Logger.getLogger(MOD_ID + ".log");
	public static final String DEFAULT_WORLD_CONFIG_FOLDER = "config";
	
	@Instance
	public static ForgePermissions instance;
	
	// Init
		@PreInit
		public void preInit(FMLPreInitializationEvent event) {
			// Config
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());
			
			Property worldConfigFolder = config.get(Configuration.CATEGORY_GENERAL, "worldConfigFolder", DEFAULT_WORLD_CONFIG_FOLDER);
			
			//Events
			//MinecraftForge.EVENT_BUS.register(new FCLEventHandler());
			
			// Load helper?
		}

		@Init
		public void load(FMLInitializationEvent event) {
			return;
		}

		@PostInit
		public void postInit(FMLPostInitializationEvent event) {
			return;
		}

		@ServerStarting
		public void serverStarting(FMLServerStartingEvent event) {
			// Commands
			//event.registerServerCommand(new FPCommand());
			
			// load world specific config

//			try {
//				log.info("dimension manager: " + DimensionManager.getCurrentSaveRootDirectory().getCanonicalPath());
//				log.info("world: " + event.getServer().getFile("").getCanonicalPath());
//				log.info("world name: " + event.getServer().getFolderName());
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
		}

		@ServerStopping
		public void serverStopping(FMLServerStoppingEvent event) {
			// save world specific config
		}
}
