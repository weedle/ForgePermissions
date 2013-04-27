package com.creatifcubed.minecraft.forgepermissions.mod.models;

import static net.minecraftforge.common.Property.Type.BOOLEAN;
import static net.minecraftforge.common.Property.Type.DOUBLE;
import static net.minecraftforge.common.Property.Type.INTEGER;
import static net.minecraftforge.common.Property.Type.STRING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;

import argo.saj.InvalidSyntaxException;

import com.creatifcubed.minecraft.forgepermissions.Utils;
import com.google.common.collect.ImmutableSet;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.common.Configuration.UnicodeInputStreamReader;

public class FPConfiguration {
	
	public final ConfigurationPlus forgeConfig;
	private File file;
	private ConfigurationTransformer transformer;
	private Exception lastError;
	private Map<String, ConfigCategory> categories;
	
	/**
	 * 
	 * @param f Location to save the world-specific config
	 */
	public FPConfiguration(File f) {
		this(f, (ConfigurationPlus) null); 
	}
	
	/**
	 * 
	 * @param f Location to save the world-specific config
	 * @param forgeConfig
	 */
	public FPConfiguration(File f, File forgeConfig) {
		this(f, new ConfigurationPlus(forgeConfig));
	}
	
	public FPConfiguration(File f, ConfigurationPlus forgeConfig) {
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
	
	protected Map<String, ConfigCategory> getCategories() {
		return Collections.unmodifiableMap(this.categories);
	}
	
	public ConfigCategory addCategory(String name) {
		name = name.toLowerCase();
		ConfigCategory newCat = new ConfigCategory(name);
		this.categories.put(name, newCat);
		return newCat;
	}
	
	public void removeCategory(String name) {
		name = name.toLowerCase();
		this.categories.remove(name);
	}
	
	public boolean hasError() {
		return this.lastError != null;
	}
	
	public void save() {
		try {
			if (this.forgeConfig != null) {
				this.forgeConfig.save();
			}
			if (this.transformer == null) {
				throw new NullPointerException("FPConfiguration.transformer has not been set");
			}
			this.transformer.save(this, this.file);
			this.lastError = null;
		} catch (IOException ex) {
			this.lastError = ex;
			ex.printStackTrace();
		}
	}
	
	public void load() {
		try {
			if (this.forgeConfig != null) {
				this.forgeConfig.load();
			}
			if (this.transformer == null) {
				throw new NullPointerException("FPConfiguration.transformer has not been set");
			}
			this.transformer.load(this, this.file);
			this.lastError = null;
		} catch (IOException ex) {
			this.lastError = ex;
			ex.printStackTrace();
		} catch (InvalidSyntaxException ex) {
			this.lastError = ex;
			ex.printStackTrace();
		}
	}
}
