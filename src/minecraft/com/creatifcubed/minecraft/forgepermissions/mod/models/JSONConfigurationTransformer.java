package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;
import net.sf.json.JSONObject;


import com.creatifcubed.minecraft.forgepermissions.mod.commands.FPCommand;

public class JSONConfigurationTransformer implements ConfigurationTransformer {
private FPCommand fpCommand;


	@Override
	public void save(FPConfiguration config, File f) throws IOException {
		 {
			 JSONObject json = new JSONObject();
			 
			 Map<String, ConfigCategory> categories = config.getCategories();

			  FileWriter fstream = new FileWriter(f);
			  BufferedWriter out = new BufferedWriter(fstream);
			  
				 for (ConfigCategory i : categories.values()){
					 for (Property p : i.values()){
						 if(p.getType() == Property.Type.INTEGER){

							 json.put(p.getName(), p.getInt());
							 //out.write(p.getName() + ":" + p.getInt());
						 }
						 if(p.getType() == Property.Type.BOOLEAN){

							 json.put(p.getName(), p.isBooleanValue());
							 //out.write(p.getName() + ":" + p.isBooleanValue());
						 }
						 if(p.getType() == Property.Type.STRING){

							 json.put(p.getName(), p.getString());
							 //out.write(p.getName() + ": \"" + p.getString() + "\"");
						 }
						 if(p.getType() == Property.Type.DOUBLE){
							 for (Double d : p.getDoubleList()){
								 json.accumulate(p.getName(), d);
							 }

						 }
					 }
				 }
				 
		  //Close the output stream
				 out.write(json.toString());
			  out.close();
			  }
	}

	@Override
	public void load(FPConfiguration config, File f) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
