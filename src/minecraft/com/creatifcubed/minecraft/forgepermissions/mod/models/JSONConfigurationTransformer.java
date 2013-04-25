package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;


import com.creatifcubed.minecraft.forgepermissions.mod.commands.FPCommand;

public class JSONConfigurationTransformer implements ConfigurationTransformer {
private FPCommand fpCommand;


	@Override
	public void save(FPConfiguration config, File f) throws IOException {
		 {
			 boolean comma = false; //comma is just so we have a comma between every
			 						//key/value pair, but not at the beginning
			 Map<String, ConfigCategory> categories = config.getCategories();

			  FileWriter fstream = new FileWriter(f);
			  BufferedWriter out = new BufferedWriter(fstream);
			  
				 for (ConfigCategory i : categories){
					 for (Property p : i.values()){
						 //this is terrible code, i'll refactor if it works xD
						 if(comma){
							 out.write(",");
						 }
						 if(p.getType() == Property.Type.INTEGER){
							 out.write(p.getName() + ":" + p.getInt());
						 }
						 if(p.getType() == Property.Type.BOOLEAN){
							 out.write(p.getName() + ":" + p.isBooleanValue());
						 }
						 if(p.getType() == Property.Type.STRING){
							 out.write(p.getName() + ": \"" + p.getString() + "\"");
						 }
						 if(p.getType() == Property.Type.DOUBLE){
							 //we're not handling doubles, yes?
						 }
					 }
				 }
				 
		  //Close the output stream
			  out.close();
			  }
	}

	@Override
	public void load(FPConfiguration config, File f) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
