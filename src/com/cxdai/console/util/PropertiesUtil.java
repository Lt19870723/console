package com.cxdai.console.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <p>
 * Description: Properties文件操作类<br />
 * </p>
 * 
 * @title PropertiesUtil.java
 * @package com.shtic.shared.util
 * @author justin.xu
 * @version 0.1 2013-3-18
 */
public class PropertiesUtil {

	private PropertiesUtil() {
	}

	private static Properties propertie = new Properties();
	private String[] fileNames;

	protected Logger logger = Logger.getLogger(getClass());

	/**
	 * @Title: load
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void load() throws Exception {
		if (fileNames.length == 0) {
			return;
		}
		InputStream inStream = null;
		for (String filename : fileNames) {
			inStream = PropertiesUtil.class.getResourceAsStream("/" + filename);
			propertie.load(inStream);
		}
		inStream.close();
	}

	/**
	 * @Title: getValue
	 * @Description: 通过key获取属性文件的值
	 * @param String
	 *            key
	 * @return String 返回类型
	 * @throws
	 */
	public static String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);// 得到某一属性的值
			return value;
		} else {
			return "";
		}
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

}
