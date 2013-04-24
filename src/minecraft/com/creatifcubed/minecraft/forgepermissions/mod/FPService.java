package com.creatifcubed.minecraft.forgepermissions.mod;

import java.io.File;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;

public class FPService {
	
	public static final String FORMAT_FORGE = "cfg";
	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";
	
	public Configuration getConfiguration(String modid, String format) {
		return null;
	}
	
	public File getWorldConfigFolder() {
		// something like
		File worldRoot = DimensionManager.getCurrentSaveRootDirectory();
		if (worldRoot == null) {
			return null;
		}
		File config = new File(worldRoot, /*the folder setting*/".");
		// check if valid path
		return config;
	}
}
