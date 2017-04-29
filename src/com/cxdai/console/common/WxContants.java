package com.cxdai.console.common;

/**
 * 微信常量
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title WxContants.java
 * @package com.cxdai.console.wxpush.vo
 * @author Wang Bo
 * @version 0.1 2015年1月20日
 */
public class WxContants {
	public static String PUSH_URL = WxBase.WX_URL + "/entry/pushMessage/pushKefuMessage";
	
	public static Integer WX_PUSH_TYPE_TEST=3;  //微信文本推送
	public static Integer WX_PUSH_TYPE_IMG=1;  //微信单图文推送
	

	public static Integer WX_PAGE_SIZE=10;  //微信单图文推送
}
