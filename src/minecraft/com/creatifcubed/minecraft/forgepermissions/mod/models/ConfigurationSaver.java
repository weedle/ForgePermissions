package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.Configuration;

public interface ConfigurationSaver {
	public void save(Configuration config, File f) throws IOException;
}
