package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.creatifcubed.minecraft.forgepermissions.Utils;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class FPConfiguration extends Configuration {
	
	private File file;
	private ConfigurationTransformer transformer;
	private Configuration forgeConfig;
	private Exception lastError;
	private Map<String, ConfigCategory> categories;
	
	public FPConfiguration(File f) {
		this(f, null);
	}
	
	public FPConfiguration(Configuration forgeConfig) {
		this(null, forgeConfig);
	}
	
	public FPConfiguration(File f, Configuration forgeConfig) {
		this.file = f;
		this.forgeConfig = forgeConfig;
		this.transformer = null;
		this.lastError = null;
		this.categories  = new HashMap<String, ConfigCategory>();
	}
	
	public FPConfiguration setTransformer(ConfigurationTransformer transformer) {
		this.transformer = transformer;
		return this;
	}
	
	@Override
	public void save() {
		try {
			if (this.forgeConfig != null) {
				this.forgeConfig.save();
			}
			if (this.file != null) {
				if (this.transformer == null) {
					throw new NullPointerException("FPConfiguration.transformer has not been set");
				}
			}
			this.transformer.save(this, this.file);
			this.lastError = null;
		} catch (IOException ex) {
			this.lastError = ex;
			ex.printStackTrace();
		}
	}
	
	@Override
	public void load() {
		try {
			if (this.forgeConfig != null) {
				this.forgeConfig.load();
			}
			if (this.file != null) {
				if (this.transformer == null) {
					throw new NullPointerException("FPConfiguration.transformer has not been set");
				}
			}
			this.transformer.load(this, this.file);
			this.lastError = null;
		} catch (IOException ex) {
			this.lastError = ex;
			ex.printStackTrace();
		}
	}
	
	public final void defaultForgeSave() {
		super.save();
	}
	
	public final void defaultForgeLoad() {
		super.load();
	}
	
	/* MONOTONOUS */
	/* Get general */
	public Property getGeneral(String key, boolean _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, boolean[] _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, int _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, int[] _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, double _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, double[] _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, String _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	
	public Property getGeneral(String key, String[] _default) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default);
	}
	/* Get general with comment */
	public Property getGeneral(String key, boolean _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, boolean[] _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, int _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, int[] _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, double _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, double[] _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, String _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
	
	public Property getGeneral(String key, String[] _default, String comment) {
		return this.get(Configuration.CATEGORY_GENERAL, key, _default, comment);
	}
}
