package com.cxdai.console.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class PropertyUtil {
	
    public static Logger logger = Logger.getLogger(PropertyUtil.class);  
      
    /** 
     * 读取配置文件信息 
     * @param configFile 配置文件名称 
     * @return 
     */  
    public static CompositeConfiguration getConfig(String configFile){  
    	CompositeConfiguration compositeConfiguration = new CompositeConfiguration(); 
        try {          	
        	try {
				compositeConfiguration.addConfiguration(new PropertiesConfiguration(configFile));
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				logger.warn("初始化配置文件错误:" + e); 
			} 
        }  catch (Exception e) {
        	logger.warn("初始化配置文件错误:" + e); 
		}  
        return compositeConfiguration ;  
    }  
      
  
}  
