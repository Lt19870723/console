package com.cxdai.console.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class Utils {
	private static final Logger logger = Logger.getLogger(Utils.class);
	
	private static final String default_enc = "UTF-8";
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String encodeURL(String input) {
		return encodeURL(input, default_enc);
	}

	public static String encodeURL(String input, String enc) {
		if (input == null) {
			return null;
		}
		try {
			return URLEncoder.encode(input, enc);
		} catch (UnsupportedEncodingException e) {
			logger.error("encode failed", e);
		}
		return input;
	}

	public static String decodeURL(String input) {
		return decodeURL(input, default_enc);
	}
	
	public static String decodeURL(String input, String enc) {
		if (input == null) {
			return null;
		}
		try {
			return URLDecoder.decode(input, enc);
		} catch (UnsupportedEncodingException e) {
			logger.error("decode failed", e);
		}
		return input;
	}
	

	public static <T> T json2Bean(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			// 忽略在json中有在bean中没有的属性
//			objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
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
	
	public static String getResponseFromServer(String url, String encoding) {
		try {
			return getResponseFromServer(new URL(url), encoding);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String getResponseFromServer(URL constructedUrl, String encoding) {
		return getResponseFromServer(constructedUrl, HttpsURLConnection.getDefaultHostnameVerifier(), encoding);
	}

	public static String getResponseFromServer(URL constructedUrl, HostnameVerifier hostnameVerifier, String encoding) {
		URLConnection conn = null;
		try {
			conn = constructedUrl.openConnection();
			if ((conn instanceof HttpsURLConnection)) {
				((HttpsURLConnection) conn).setHostnameVerifier(hostnameVerifier);
			}
			BufferedReader in;
			if ((encoding == null) || (encoding.length() == 0)) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
			}

			StringBuilder stringBuffer = new StringBuilder(255);
			String line;
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if ((conn != null) && ((conn instanceof HttpURLConnection))) {
				((HttpURLConnection) conn).disconnect();
			}
		}
	}
}
