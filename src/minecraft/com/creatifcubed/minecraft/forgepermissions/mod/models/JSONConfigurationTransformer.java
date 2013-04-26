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
import static argo.jdom.JsonNodeFactories.*;

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
			JsonObjectNodeBuilder builder = JsonNodeBuilders.anObjectBuilder();
			for (String node : nodes.keySet()){
				if (nodes.get(node) instanceof Integer){
					builder = buildInteger(builder, node, (Integer) nodes.get(node));
				} else if (nodes.get(node) instanceof String){
					builder = buildString(builder, node, (String) nodes.get(node));
				} else if (nodes.get(node) instanceof Boolean){
					builder = buildBoolean(builder, node, (Boolean) nodes.get(node));
				} else if (nodes.get(node) instanceof Integer[]){
					for (int i : (Integer[]) nodes.get(node)){
					builder = buildInteger(builder, node, i);
					}
				} else if (nodes.get(node) instanceof String[]){
					for (String s : (String[]) nodes.get(node)){
					builder = buildString(builder, node, s);
					}
				} else if (nodes.get(node) instanceof Boolean[]){
					for (Boolean b : (Boolean[]) nodes.get(node)){
					builder = buildBoolean(builder, node, b);
					}				
				} else {
					builder = builder.withField(node, JsonNodeBuilders.aNullBuilder());
				} 
			}
			return builder.build();
		}
		
		private JsonObjectNodeBuilder buildBoolean(
				JsonObjectNodeBuilder builder, String key, Boolean value) {
			if(value){
			builder = builder.withField(key, JsonNodeBuilders.aTrueBuilder());
			} else {
				builder = builder.withField(key, JsonNodeBuilders.aFalseBuilder());
			}
			return builder;
		}

		private JsonObjectNodeBuilder buildString(
				JsonObjectNodeBuilder builder, String key, String value) {
			builder = builder.withField(key, JsonNodeBuilders.aStringBuilder(value));
			return builder;
		}

		public JsonObjectNodeBuilder buildInteger(
				JsonObjectNodeBuilder builder, String key, Integer value){
			builder = builder.withField(key, JsonNodeBuilders.aNumberBuilder("\"" + value.toString() + "\""));
			return builder;
		}
	}
}
