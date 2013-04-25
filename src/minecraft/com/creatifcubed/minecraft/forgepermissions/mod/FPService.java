package com.creatifcubed.minecraft.forgepermissions.mod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.creatifcubed.minecraft.forgepermissions.ForgePermissions;
import com.creatifcubed.minecraft.forgepermissions.mod.models.FPConfiguration;
import com.creatifcubed.minecraft.forgepermissions.mod.models.ForgeConfigurationTransformer;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;

public class FPService {
	
	public static final String FORMAT_FORGE = "cfg";
	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";
	private final Map<String, FPConfiguration> allModConfigs;
	
	public FPService() {
		this.allModConfigs = new HashMap<String, FPConfiguration>();
	}
	
	public Configuration getConfiguration(String modid, String format) {
		modid = modid.toLowerCase();
		if (!this.allModConfigs.containsKey(modid)) {
			FPConfiguration modConfig = new FPConfiguration(null);
			if (format.equals(FORMAT_FORGE)) {
				modConfig.setTransformer(new ForgeConfigurationTransformer());
			} else if (format.equals(FORMAT_JSON)) {
				modConfig.setTransformer(null);
			} else if (format.equals(FORMAT_XML)) {
				modConfig.setTransformer(null);
			} else {
				ForgePermissions.log.info(String.format("FPService.getConfiguration invalid format type {%s}. Using default ForgeConfigurationTransformer", format));
				modConfig.setTransformer(new ForgeConfigurationTransformer());
			}
			this.allModConfigs.put(modid, modConfig);
		}
		return this.allModConfigs.get(modid);
	}
	
	public void saveAll() {
		for (String modid : this.allModConfigs.keySet()) {
			this.allModConfigs.get(modid).save();
		}
	}
	
	public File getWorldConfigFolder() {
		File worldRoot = DimensionManager.getCurrentSaveRootDirectory();
		if (worldRoot == null) {
			return null;
		}
		File config = new File(worldRoot, ".");
		// check if valid path
		return config;
	}
}
