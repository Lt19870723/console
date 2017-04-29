package com.cxdai.console.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WxUtils {
	private static final Logger logger = Logger.getLogger(WxUtils.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T json2Bean(String json, Class<T> clazz) {
		if (json == null || "{}".equals(json) || "".equals(json)) {
			return null;
		}
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("WxUtils.json2Bean(1,2)异常", e);
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
			logger.error("WxUtils.json2Bean(1)异常", e);
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
			logger.error("WxUtils.json2Map(1)异常", e);
		}
		return null;
	}

	public static List<?> json2List(String json) {
		if (json == null || "".equals(json)) {
			return null;
		}
		try {
			return objectMapper.readValue(json, List.class);
		} catch (Exception e) {
			logger.error("WxUtils.json2List(1)异常", e);
		}
		return null;
	}

	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest = strDigest + byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4 & 0xF)];
		tempArr[1] = Digit[(mByte & 0xF)];

		String s = new String(tempArr);
		return s;
	}

	//
	// /**
	// * 图片消息对象转换成xml
	// *
	// * @param imageMessage 图片消息对象
	// * @return xml
	// */
	// public static String messageToXml(ImageMessage imageMessage) {
	// xstream.alias("xml", imageMessage.getClass());
	// return xstream.toXML(imageMessage);
	// }
	//
	// /**
	// * 语音消息对象转换成xml
	// *
	// * @param voiceMessage 语音消息对象
	// * @return xml
	// */
	// public static String messageToXml(VoiceMessage voiceMessage) {
	// xstream.alias("xml", voiceMessage.getClass());
	// return xstream.toXML(voiceMessage);
	// }
	//
	// /**
	// * 视频消息对象转换成xml
	// *
	// * @param videoMessage 视频消息对象
	// * @return xml
	// */
	// public static String messageToXml(VideoMessage videoMessage) {
	// xstream.alias("xml", videoMessage.getClass());
	// return xstream.toXML(videoMessage);
	// }
	//
	// /**
	// * 音乐消息对象转换成xml
	// *
	// * @param musicMessage 音乐消息对象
	// * @return xml
	// */
	// public static String messageToXml(MusicMessage musicMessage) {
	// xstream.alias("xml", musicMessage.getClass());
	// return xstream.toXML(musicMessage);
	// }
	//
	// public static String messageToXml(NewsMessage newsMessage) {
	// xstream.alias("xml", newsMessage.getClass());
	// xstream.alias("item", new Article().getClass());
	// return xstream.toXML(newsMessage);
	// }
	//
	// private static XStream xstream = new XStream(new XppDriver() {
	// public HierarchicalStreamWriter createWriter(Writer out) {
	// return new PrettyPrintWriter(out) {
	// boolean cdata = true;
	//
	// protected void writeText(QuickWriter writer, String text) {
	// if (cdata) {
	// writer.write("<![CDATA[");
	// writer.write(text);
	// writer.write("]]>");
	// } else {
	// writer.write(text);
	// }
	// }
	// };
	// }
	// });
	public static String getRealIpAddr() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null)
			return null;
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		String ip = request.getHeader("X-Real-IP");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
