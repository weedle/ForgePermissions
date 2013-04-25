package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.Configuration;

public class ForgeConfigurationTransformer implements ConfigurationTransformer {

	@Override
	public void save(FPConfiguration config, File f) {
		config.defaultForgeSave();
	}

	@Override
	public void load(FPConfiguration config, File f) {
		config.defaultForgeLoad();
	}

}
