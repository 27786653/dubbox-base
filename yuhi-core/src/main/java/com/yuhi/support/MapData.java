package com.yuhi.support;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class MapData extends HashMap implements Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4147452455238734845L;
	Map map = null;
	HttpServletRequest request;

	public MapData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			value = null;
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					if (value == null)
						value = (values[i] == null ? "" : values[i]);
					else
						value += "," + (values[i] == null ? "" : values[i]);
				}
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public MapData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr
					: (request.getParameter((String) key) == null ? arr
							: arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return (String) get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

	public Object clone() {
		MapData pd = new MapData();
		for (Iterator keyIt = this.keySet().iterator(); keyIt.hasNext();) {
			Object key = keyIt.next();
			pd.put(key, this.get(key));
		}
		return pd;
	}

	/**
	 * 判断两个对象指定字段值是否一致
	 * 
	 * @param pd
	 * @param fields
	 * @return
	 */
	public boolean equals(MapData pd, List<String> fields) {
		boolean isEqual = true;

		if (pd != null && fields != null && !fields.isEmpty()) {
			for (String field : fields) {
				isEqual = isEqual
						&& this.isEqual(this.getString(field),
								pd.getString(field));
			}
		}

		return isEqual;
	}

	/**
	 * 判断两个字符串是否一致
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private boolean isEqual(String str1, String str2) {
		boolean isAllNull = (str1 == null && str2 == null);
		boolean isAllEmpty = (str1 != null && str1.trim().equals("")
				&& str2 != null && str2.trim().equals(""));

		return isAllNull
				|| isAllEmpty
				|| (str1 != null && str2 != null && str2.trim().equals(
						str1.trim()));
	}

}
