package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
	
	private class JsonTree {
		private Map<String, Object> nodes;
		public JsonTree() {
			this.nodes = new HashMap<String, Object>();
		}
		
		public void put(String key, Object value) {
			if (key.isEmpty()) {
				throw new IllegalArgumentException("JSONConfigurationTransformer.JsonTree.put(String, Object) string param 0 is empty");
			}
			String[] parts = key.split("\\.");
			Map<String, Object> container = this.ensurePathExists(parts);
			container.put(parts[parts.length - 1], value);
		}
		
		public Map<String, Object> ensurePathExists(String[] parts) {
			Map<String, Object> last = nodes;
			for (int i = 0; i < parts.length - 1; i++) {
				Object current = ((Map) last).get(parts[i]);
				if (!(current instanceof Map)) {
					current = new HashMap<String, Object>();
					((Map) last).put(parts[i], current);
				}
				last = (Map) current;
			}
			return last;
		}
		
		public JsonRootNode build() {
			return null;
		}
	}
}
