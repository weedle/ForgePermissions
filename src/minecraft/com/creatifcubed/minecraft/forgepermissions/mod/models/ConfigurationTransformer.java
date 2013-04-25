package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.Configuration;

public interface ConfigurationTransformer {
	public void save(FPConfiguration config, File f) throws IOException;
	public void load(FPConfiguration config, File f) throws IOException;
}
