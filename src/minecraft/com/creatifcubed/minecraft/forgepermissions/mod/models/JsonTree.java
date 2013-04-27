package com.creatifcubed.minecraft.forgepermissions.mod.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import argo.jdom.JsonArrayNodeBuilder;
import argo.jdom.JsonNode;
import argo.jdom.JsonNodeBuilder;
import argo.jdom.JsonNodeBuilders;
import argo.jdom.JsonObjectNodeBuilder;
import argo.jdom.JsonRootNode;
import argo.jdom.JsonStringNode;

import com.creatifcubed.minecraft.forgepermissions.ForgePermissions;
import com.creatifcubed.minecraft.forgepermissions.Utils;

public class JsonTree implements Map<String, Object> {
	private HashMap<String, Object> root;

	public JsonTree() {
		this.root = new HashMap<String, Object>();
	}
	
	public JsonTree(JsonRootNode src) {
		this.root = (HashMap) this.mapJsonNode(src);
	}
	
	@Override
	public Object put(String key, Object value) {
		if (key.isEmpty()) {
			throw new IllegalArgumentException(
					"JsonTree.put(String, Object) string param 0 is empty");
		}
		String[] parts = key.split("\\.");
		Map<String, Object> container = this.findPath(parts, true);
		return container.put(parts[parts.length - 1], value);
	}
	
	private Map<String, Object> findPath(String[] parts, boolean create) {
		Map<String, Object> last = this.root;
		for (int i = 0; i < parts.length - 1; i++) {
			Object current = ((Map) last).get(parts[i]);
			if (!(current instanceof Map)) {
				if (create) {
					current = new HashMap<String, Object>();
					((Map) last).put(parts[i], current);
				} else {
					return null;
				}
			}
			last = (Map) current;
		}
		return last;
	}

	public JsonObjectNodeBuilder construct() {
		return this.buildObject(this.root);
	}
	
	private JsonObjectNodeBuilder buildObject(Map<String, Object> source) {
		JsonObjectNodeBuilder node = JsonNodeBuilders.anObjectBuilder();
		for (String key : source.keySet()) {
			JsonNodeBuilder value = this.buildValue(source.get(key));
			if (key != null) {
				node.withField(key, value);
			}
		}
		return node;
	}
	private JsonArrayNodeBuilder buildArray(Object[] source) {
		JsonArrayNodeBuilder node = JsonNodeBuilders.anArrayBuilder();
		for (int i = 0; i < source.length; i++) {
			JsonNodeBuilder value = this.buildValue(source[i]);
			if (value != null) {
				node.withElement(value);
			}
		}
		return node;
	}
	private JsonNodeBuilder buildBoolean(Boolean source) {
		return source ? JsonNodeBuilders.aTrueBuilder() : JsonNodeBuilders.aFalseBuilder();
	}
	private JsonNodeBuilder buildNumber(Number source) {
		return JsonNodeBuilders.aNumberBuilder(String.valueOf(source));
	}
	private JsonNodeBuilder buildString(String source) {
		return JsonNodeBuilders.aStringBuilder(source);
	}
	private JsonNodeBuilder buildNull() {
		return JsonNodeBuilders.aNullBuilder();
	}
	private JsonNodeBuilder buildValue(Object value) {
		if (value instanceof Boolean) {
			return this.buildBoolean((Boolean) value);
		} else if (value instanceof Number) {
			return this.buildNumber((Number) value);
		} else if (value instanceof String) {
			return this.buildString((String) value);
		} else if (value instanceof Object[]) {
			return this.buildArray((Object[]) value);
		} else if (value instanceof Map) {
			return this.buildObject((Map) value);
		} else if (value == null) {
			return this.buildNull();
		}
		ForgePermissions.log.fine(String.format("JsonTree.buildValue(Object) unknown class {%s}", value.getClass().getName()));
		return null;
	}
	
	private Object mapJsonNode(JsonNode source) {
		if (source.isBooleanValue()) {
			return source.getBooleanValue();
		} else if (source.isNumberValue()) {
			return Utils.parseNumber(source.getNumberValue());
		} else if (source.isStringValue()) {
			return source.getStringValue();
		} else if (source.isArrayNode()) {
			List<JsonNode> srcList = source.getElements();
			Object[] result = new Object[srcList.size()];
			int i = 0;
			for (JsonNode each : srcList) {
				result[i++] = this.mapJsonNode(each);
			}
			return result;
		} else if (source.isObjectNode()) {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<JsonStringNode, JsonNode> srcMap = source.getFields();
			for (JsonStringNode key : srcMap.keySet()) {
				JsonNode value = srcMap.get(key);
				result.put(key.getText(), this.mapJsonNode(value));
			}
			return result;
		} else if (source.isNullNode()) {
			return null;
		}
		throw new IllegalArgumentException("JsonTree.mapJsonNode(JsonNode) invalid node param 0");
	}

	@Override
	public void clear() {
		this.root = new HashMap<String, Object>();
	}

	@Override
	public boolean containsKey(Object o) {
		if (!(o instanceof String)) {
			return false;
		}
		String key = (String) o;
		String[] parts = key.split("\\.");
		Map<String, Object> container = this.findPath(parts, false);
		return container != null && container.containsKey(parts[parts.length - 1]);
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(Object o) {
		if (!(o instanceof String)) {
			return false;
		}
		String key = (String) o;
		String[] parts = key.split("\\.");
		Map<String, Object> container = this.findPath(parts, false);
		return container == null ? null : container.get(parts[parts.length - 1]);
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return this.root.keySet();
	}
	
	public Set<String> keySetComplete() {
		return this.getKeys(this.root, "");
	}
	
	private Set<String> getKeys(Map<String, Object> node, String prefix) {
		Set<String> keys = new HashSet<String>();
		for (String str : node.keySet()) {
			Object value = node.get(str);
			if (value instanceof Map) {
				keys.addAll(this.getKeys((Map) value, prefix + str + "."));
			} else {
				keys.add(prefix + str);
			}
		}
		return keys;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		for (String str : m.keySet()) {
			this.put(str, m.get(str));
		}
	}

	@Override
	public Object remove(Object o) {
		if (!(o instanceof String)) {
			return false;
		}
		String key = (String) o;
		String[] parts = key.split("\\.");
		Map<String, Object> container = this.findPath(parts, false);
		return container == null ? null : container.remove(parts[parts.length - 1]);
	}

	@Override
	public int size() {
		return this.root.size();
	}

	@Override
	public Collection<Object> values() {
		throw new UnsupportedOperationException();
	}
}