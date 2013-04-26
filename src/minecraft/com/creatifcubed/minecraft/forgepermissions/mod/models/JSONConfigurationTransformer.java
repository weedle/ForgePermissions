package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;

import argo.format.JsonFormatter;
import argo.format.PrettyJsonFormatter;
import argo.jdom.JsonNodeBuilders;
import argo.jdom.JsonObjectNodeBuilder;
import argo.jdom.JsonRootNode;
import argo.jdom.JdomParser;
import argo.saj.InvalidSyntaxException;

import com.creatifcubed.minecraft.forgepermissions.mod.commands.FPCommand;

public class JSONConfigurationTransformer implements ConfigurationTransformer {

	@Override
	public void save(FPConfiguration config, File f) throws IOException {

		JsonObjectNodeBuilder builder = JsonNodeBuilders.anObjectBuilder();
		JsonFormatter formatter = new PrettyJsonFormatter();

		Map<String, ConfigCategory> categories = config.getCategories();

		JsonObjectNodeBuilder catsNode = JsonNodeBuilders.anObjectBuilder();
		builder.withField("categories", catsNode);
		
		JsonRootNode node = builder.build();
		System.out.println(formatter.format(node));
//
//		for (String each : categories.keySet()) {
//			JsonObjectNodeBuilder cat = JsonNodeBuilders.anObjectBuilder();
//			categories.withField(each, cat);
//			for (Property p : categories.get(each).values()) {
//				if(p.getType() == Property.Type.INTEGER) {
//					
//							json.put(p.getName(), p.getInt());
//							//out.write(p.getName() + ":" + p.getInt());
//				}
//				if(p.getType() == Property.Type.BOOLEAN) {
//
//					json.put(p.getName(), p.isBooleanValue());
//					//out.write(p.getName() + ":" + p.isBooleanValue());
//				}
//				if(p.getType() == Property.Type.STRING) {
//
//					json.put(p.getName(), p.getString());
//					//out.write(p.getName() + ": \"" + p.getString() + "\"");
//				}
//				if(p.getType() == Property.Type.DOUBLE) {
//					for (Double d : p.getDoubleList()){
//						json.accumulate(p.getName(), d);
//					}
//
//				}
//			}
//		}
//
//		//Close the output stream
//		out.write(json.toString());
//		out.close();
	}

	@Override
	public void load(FPConfiguration config, File f) throws IOException, InvalidSyntaxException {
		JsonRootNode root = new JdomParser().parse(new FileReader(f));

	}
}
