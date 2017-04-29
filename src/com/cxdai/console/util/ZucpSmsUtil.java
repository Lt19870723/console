package com.cxdai.console.util;

/**
 * <p>
 * Description:漫道发送短信工具类<br />
 * </p>
 * @title ZucpSmsUtil.java
 * @package com.cxdaiweb.sms.util.zucp
 * @author justin.xu
 * @version 0.1 2014年1月17日
 */
public class ZucpSmsUtil {

	private static String sn = "SDK-BBX-010-19363";// SDK-BBX-010-19363　
	private static String pwd = "02-5960-";

	private static Client client;

	private static Client getClient() throws Exception {
		if (client == null) {
			client = new Client(sn, pwd);
		}
		return client;
	}

	/**
	 * <p>
	 * Description:发送单条短信<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年1月17日
	 * @param content
	 * @param mobile 手机号码
	 * @throws Exception void
	 */
	public static String sendSms(String content, String mobile) throws Exception {
		// 测试环境上 去掉 发送短信 ，免得漫道将我司余额清0
		 Client client = ZucpSmsUtil.getClient();
		 String result_mt = client.mdSmsSend_u(mobile, content, "", "", "");
		 return result_mt;
//		return null;
		   
	}
	 
}
