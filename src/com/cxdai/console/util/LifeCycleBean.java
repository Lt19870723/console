package com.cxdai.console.util;


import org.springframework.beans.factory.InitializingBean;

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
public class LifeCycleBean implements InitializingBean{

	private LifeCycleBean() {
	}

	 @Override 
	    public void afterPropertiesSet() throws Exception {  
	        System.out.println("******************************");  
	        System.out.println("afterPropertiesSet is called");  
	        System.out.println("******************************");  
	    }  

	

}
