package com.creatifcubed.minecraft.forgepermissions.mod;

import java.io.File;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;

public class FPService {
	
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
