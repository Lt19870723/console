package com.cxdai.console.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class SerializeUtil {
	
	private final static Logger logger = Logger.getLogger(SerializeUtil.class);
	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			oos.close();  
			baos.close(); 
			return bytes;
		} catch (IOException e) {  
			logger.error("序列化出错",e);
	    }
		
		return null;
	}
	
	  

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois =null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			bais.close();  
			ois.close();
			return ois.readObject();
		}catch (Exception e) { 
			logger.error("反序列化出错",e);    
	    }
		return null;
		
	}
}
