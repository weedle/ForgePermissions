package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.ConfigCategory;
import argo.format.JsonFormatter;
import argo.format.PrettyJsonFormatter;
import argo.jdom.JdomParser;
import argo.jdom.JsonArrayNodeBuilder;
import argo.jdom.JsonNodeBuilders;
import argo.jdom.JsonObjectNodeBuilder;
import argo.jdom.JsonRootNode;
import argo.saj.InvalidSyntaxException;

import com.creatifcubed.minecraft.forgepermissions.ForgePermissions;

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
				throw new IllegalArgumentException(
						"JSONConfigurationTransformer.JsonTree.put(String, Object) string param 0 is empty");
			}
			Class valueClass = value.getClass().getComponentType() == null ? value.getClass() : value.getClass().getComponentType();
			if (!Number.class.isAssignableFrom(valueClass) && !String.class.isAssignableFrom(valueClass) && !Number.class.isAssignableFrom(valueClass)) {
				throw new IllegalArgumentException(String.format("JSONConfigurationTransformer.JsonTree.put(String, Object) string param 0 value class invalid type {%s}", value.getClass().getName()));
			}
			String[] parts = key.split("\\.");
			Map<String, Object> container = this.ensurePathExists(parts);
			container.put(parts[parts.length - 1], value);
		}

		private Map<String, Object> ensurePathExists(String[] parts) {
			Map<String, Object> last = this.nodes;
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
			JsonObjectNodeBuilder root = JsonNodeBuilders.anObjectBuilder();
			for (String node : this.nodes.keySet()) {
				Object nodeVal = this.nodes.get(node);
				
				if (nodeVal instanceof Integer) {
					root = this.buildNumber(root, node, (Integer) nodeVal);
				} else if (nodeVal instanceof String) {
					root = this.buildString(root, node, (String) nodeVal);
				} else if (nodeVal instanceof Boolean) {
					root = this.buildBoolean(root, node, (Boolean) nodeVal);
				} else if (nodeVal instanceof Number[] || nodeVal instanceof String[] || nodeVal instanceof Boolean[]) {
					JsonArrayNodeBuilder array = JsonNodeBuilders.anArrayBuilder();
					if (nodeVal instanceof Integer[]) {
							array = this.buildArrayNumber(array, node, (Integer[]) nodeVal);
					} else if (nodeVal instanceof String[]) {
						array = this.buildArrayString(array, node, (String[]) nodeVal);
					} else if (nodeVal instanceof Boolean[]) {
						array = this.buildArrayBoolean(array, node, (Boolean[]) nodeVal);
					}
					root.withField(node, array);
				} else if (nodeVal == null) {
					ForgePermissions.log.info(String.format("Got null key {%s}", node));
					root = root.withField(node, JsonNodeBuilders.aNullBuilder());
				}
			}
			return root.build();
		}

		private JsonObjectNodeBuilder buildBoolean(JsonObjectNodeBuilder builder, String key, Boolean value) {
			if (value) {
				builder = builder.withField(key, JsonNodeBuilders.aTrueBuilder());
			} else {
				builder = builder.withField(key, JsonNodeBuilders.aFalseBuilder());
			}
			return builder;
		}

		private JsonObjectNodeBuilder buildString(JsonObjectNodeBuilder builder, String key, String value) {
			builder = builder.withField(key, JsonNodeBuilders.aStringBuilder(value));
			return builder;
		}

		private JsonObjectNodeBuilder buildNumber(JsonObjectNodeBuilder builder, String key, Number value) {
			builder = builder.withField(key, JsonNodeBuilders.aNumberBuilder(value.toString()));
			return builder;
		}

		private JsonArrayNodeBuilder buildArrayBoolean(
				JsonArrayNodeBuilder array, String node, Boolean[] b) {
			for (Boolean value : b){
				if (value) {
					array = array.withElement(JsonNodeBuilders.aTrueBuilder());
				} else {
					array = array.withElement(JsonNodeBuilders.aFalseBuilder());
				}
			}
			return null;
		}

		private JsonArrayNodeBuilder buildArrayString(
				JsonArrayNodeBuilder array, String node, String[] nodeVal) {
			// TODO Auto-generated method stub
			return null;
		}

		private JsonArrayNodeBuilder buildArrayNumber(
				JsonArrayNodeBuilder array, String node, Integer[] nodeVal) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
