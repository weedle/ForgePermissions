package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigurationPlus extends Configuration {
	public ConfigurationPlus(File f) {
		super(f);
	}
	
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
