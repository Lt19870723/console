package com.cxdai.console.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtils {
	private static final Logger logger = Logger.getLogger(JsonUtils.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T json2Bean(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			// 忽略在json中有在bean中没有的属性
			objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("unconverted json[" + json + "]", e);
		}
		return null;
	}

	public static String bean2Json(Object bean) {
		if (bean == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(bean);
		} catch (Exception e) {
			logger.error("unconverted bean[" + bean + "]", e);
		}
		return null;
	}

	public static Map<?, ?> json2Map(String json) {
		if (json == null) {
			return null;
		}
		try {
			return objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			logger.error("unconverted json[" + json + "]", e);
		}
		return null;
	}

	public static List<?> json2List(String json) {
		if (json == null) {
			return null;
		}
		try {
			return objectMapper.readValue(json, List.class);
		} catch (Exception e) {
			logger.error("unconverted json[" + json + "]", e);
		}
		return null;
	}

	public static void main(String[] args) {
		String json = "{\"responseStatus\":{\"statusCode\":200,\"reasonPhrase\":\"请求成功完成\",\"family\":\"SUCCESSFUL\"},\"timestamp\":\"20140123091029\"}";
		Map result = JsonUtils.json2Map(json);
		int statusCode = Integer.parseInt(((Map) result.get("responseStatus")).get("statusCode").toString());
		System.out.println(statusCode);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:json 转换 list<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-18
	 * @param jsonArrayStr
	 * @param clazz
	 * @return
	 * @throws Exception
	 * List<T>
	 */
	public static <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz) throws Exception {
		List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
		});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(mapToJavaBean(map, clazz));
		}
		return result;
	}
	
	/**
	 * map convert to javaBean
	 */
	public static <T> T mapToJavaBean(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}
	
	
	
	
}
