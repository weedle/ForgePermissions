package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;
import argo.format.JsonFormatter;
import argo.format.PrettyJsonFormatter;
import argo.jdom.JdomParser;
import argo.jdom.JsonArrayNodeBuilder;
import argo.jdom.JsonNode;
import argo.jdom.JsonNodeBuilder;
import argo.jdom.JsonNodeBuilders;
import argo.jdom.JsonObjectNodeBuilder;
import argo.jdom.JsonRootNode;
import argo.jdom.JsonStringNode;
import argo.saj.InvalidSyntaxException;

import com.creatifcubed.minecraft.forgepermissions.ForgePermissions;
import com.creatifcubed.minecraft.forgepermissions.Utils;

public class JSONConfigurationTransformer implements ConfigurationTransformer {

	@Override
	public void save(FPConfiguration config, File f) throws IOException {
		JsonObjectNodeBuilder root = JsonNodeBuilders.anObjectBuilder();
		JsonFormatter formatter = new PrettyJsonFormatter();

		Map<String, ConfigCategory> categories = config.getCategories();
		
		JsonObjectNodeBuilder catsNode = JsonNodeBuilders.anObjectBuilder();
		root.withField("categories", new JsonTree().construct());
		
		JsonRootNode node = root.build();
		System.out.println(formatter.format(node)); // remove later
		Utils.filePutContents(f, formatter.format(node));
	}

	@Override
	public void load(FPConfiguration config, File f) throws IOException, InvalidSyntaxException {
		JsonRootNode root = new JdomParser().parse(new FileReader(f));
		JsonRootNode categories = root.getRootNode("category");
		
		Map<JsonStringNode, JsonNode> categoriesMap = categories.getFields();
		for (JsonStringNode catKey : categoriesMap.keySet()) {
			JsonNode catValue = categoriesMap.get(catKey);
			ConfigCategory cat = config.addCategory(catKey.getText());
			Map<JsonStringNode, JsonNode> catMap = catValue.getFields();
			for (JsonStringNode topKey : catMap.keySet()) {
				JsonNode topValue = catMap.get(topKey);
				if (topValue.isObjectNode()) {
					JsonTree tree = new JsonTree(topValue.getRootNode());
					for (String key : tree.keySetComplete()) {
						this.addCategoryKeyVal(cat, key, tree.get(key));
					}
				}
			}
		}
	}
	
	private void addCategoryKeyVal(ConfigCategory cat, String key, Object val) {
		if (val instanceof Boolean) {
			cat.put(key, new Property(key, val.toString(), Property.Type.BOOLEAN));
		} else if (val instanceof Integer) {
			cat.put(key, new Property(key, val.toString(), Property.Type.INTEGER));
		} else if (val instanceof Double) {
			cat.put(key, new Property(key, val.toString(), Property.Type.DOUBLE));
		} else if (val instanceof String) {
			cat.put(key, new Property(key, val.toString(), Property.Type.STRING));
		} else if (val instanceof Object[]) {
			cat.put(key, new Property(key, Utils.convertStringArray((Object[]) val), Property.Type.STRING));
		} else if (val == null) {
			ForgePermissions.log.info(String.format("JSONConfigurationTransformer.addCateogryKeyVal(ConfigCategory, String, Object) got null val"));
		} else {
			ForgePermissions.log.info(String.format("JSONConfigurationTransformer.addCategoryKeyVal(ConfigCategory, String, Object) invalid object param 2 class {%s}", val.getClass().getName()));
		}
	}
}
