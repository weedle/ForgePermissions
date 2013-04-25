package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.IOException;

import org.bouncycastle.util.Arrays;

import com.creatifcubed.minecraft.forgepermissions.Utils;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class FPConfiguration extends Configuration {
	
	private File file;
	private ConfigurationTransformer transformer;
	private Configuration parent;
	private Exception lastError;
	
	public FPConfiguration(File f) {
		this(f, null);
	}
	
	public FPConfiguration(File f, Configuration parent) {
		this.file = f;
		this.parent = parent;
		this.transformer = null;
		this.lastError = null;
	}
	
	public FPConfiguration setTransformer(ConfigurationTransformer transformer) {
		this.transformer = transformer;
		return this;
	}
	
	@Override
	public void save() {
		try {
			if (this.parent != null) {
				this.parent.save();
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
	
	@Override
	public void load() {
		try {
			if (this.parent != null) {
				this.parent.load();
			}
			if (this.transformer == null) {
				throw new NullPointerException("FPConfiguration.transformer has not been set");
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
	/* Get strong */
	public Property getStrong(String cat, String key, String _default) {
		Property prop = this.get(cat, key, _default);
		return prop;
	}
	public Property getStrong(String cat, String key, String[] _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isList()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, int _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isIntValue()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, int[] _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isIntList()) {
			prop.set(Utils.convertStringArray(_default));
		}
		return prop;
	}
	public Property getStrong(String cat, String key, boolean _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isBooleanValue()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, boolean[] _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isBooleanList()) {
			prop.set(Utils.convertStringArray(_default));
		}
		return prop;
	}
	public Property getStrong(String cat, String key, double _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isDoubleValue()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, double[] _default) {
		Property prop = this.get(cat, key, _default);
		if (!prop.isDoubleList()) {
			prop.set(Utils.convertStringArray(_default));
		}
		return prop;
	}
	/* Get strong with commnet */
	public Property getStrong(String cat, String key, String _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		return prop;
	}
	public Property getStrong(String cat, String key, String[] _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isList()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, int _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isIntValue()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, int[] _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isIntList()) {
			prop.set(Utils.convertStringArray(_default));
		}
		return prop;
	}
	public Property getStrong(String cat, String key, boolean _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isBooleanValue()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, boolean[] _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isBooleanList()) {
			prop.set(Utils.convertStringArray(_default));
		}
		return prop;
	}
	public Property getStrong(String cat, String key, double _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isDoubleValue()) {
			prop.set(_default);
		}
		return prop;
	}
	public Property getStrong(String cat, String key, double[] _default, String comment) {
		Property prop = this.get(cat, key, _default, comment);
		if (!prop.isDoubleList()) {
			prop.set(Utils.convertStringArray(_default));
		}
		return prop;
	}
}
