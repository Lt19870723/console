package com.cxdai.console.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Description:数据常量<br />
 * 协议模板文件<br/>
 * </p>
 * 
 * @title EnumerationUtil.java
 * @package com.cxdai.portal.statics
 * @author justin.xu
 * @version 0.1 2014年11月24日
 */
public class EnumerationUtil {
	/********* 协议模板文件 */
	public static Map<String, String> protocolMap = new HashMap<String, String>();
	static {
		// 1：信用标，2：抵押标，3：净值标，4：秒标 5：担保标 9 直通车已解锁 10定期宝服务协议
		protocolMap.put("1", "protocol/recommendBorrow.ftl");
		protocolMap.put("2", "protocol/pledge.ftl");
		protocolMap.put("3", "protocol/netBorrow.ftl");
		protocolMap.put("4", "protocol/netBorrow.ftl");
		protocolMap.put("5", "protocol/pledge.ftl");
		protocolMap.put("6", "protocol/transfer.ftl");
		protocolMap.put("7", "protocol/borrowTransfer.ftl");
		protocolMap.put("8", "protocol/firstTransfer.ftl");
		protocolMap.put("9", "protocol/firstBorrowUnLock.ftl");
		protocolMap.put("10", "protocol/fixBorrow.ftl");

	}

	/**
	 * <p>
	 * Description:根据标的类型查找模板位置<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月19日
	 * @param borrowType
	 * @return String
	 */
	public static String getProtocolPathByBorrowType(String borrowType) {
		String protocolPath = "";
		if (protocolMap.containsKey(borrowType)) {
			protocolPath = protocolMap.get(borrowType);
		}
		return protocolPath;
	}
}
