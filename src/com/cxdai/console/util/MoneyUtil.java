package com.cxdai.console.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtil {
	/**
	 * <p>
	 * Description:用千位分隔金融，保留两位小数，不进行四舍五入<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月12日
	 * @param text
	 * @return String
	 */
	public static String fmtMoneyByDot(BigDecimal money) {
		String result = "0";
		if (null == money) {
			return result;
		}
		money = money.setScale(2, BigDecimal.ROUND_DOWN);
		DecimalFormat d = new DecimalFormat(",##0.00");
		return d.format(money);

	}

	/**
	 * 
	 * <p>
	 * Description:用千位分隔金融，保留两位小数，进行四舍五入<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年8月13日
	 * @param money
	 * @return String
	 */
	public static String roundMoney(BigDecimal money) {
		String result = "0";
		if (null == money) {
			return result;
		}
		money = money.setScale(2, BigDecimal.ROUND_HALF_UP);
		DecimalFormat d = new DecimalFormat(",##0.00");
		return d.format(money);
	}

	/**
	 * 
	 * <p>
	 * Description:金额格式化<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月16日
	 * @param s 金额
	 * @return 格式后的金额 String
	 */
	public static String insertComma(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int index = s.indexOf(".");
		StringBuffer buff1 = new StringBuffer();
		StringBuffer buff2 = new StringBuffer();
		if (index > 0) {
			String str1 = s.substring(0, index);
			int m = 1;
			for (int i = str1.length() - 1; i >= 0; i--) {
				buff1.append(str1.charAt(i));
				if (i > 0 && m % 3 == 0) {
					buff1.append(",");
				}
				m++;
			}
			for (int i = buff1.length() - 1; i >= 0; i--) {
				buff2.append(buff1.charAt(i));
			}
			buff2.append(s.substring(index));
			return buff2.toString();
		}
		return s;
	}

	/**
	 * 
	 * <p>
	 * Description:金额格式化并保留2为小数<br />
	 * </p>
	 * 
	 * @author 杨仕金
	 * @version 0.1 2013年10月29日
	 * @param s
	 * @return String
	 */
	public static String insertComma2(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int index = s.indexOf(".");
		StringBuffer buff1 = new StringBuffer();
		StringBuffer buff2 = new StringBuffer();
		if (index > 0) {
			String str1 = s.substring(0, index);
			int m = 1;
			for (int i = str1.length() - 1; i >= 0; i--) {
				buff1.append(str1.charAt(i));
				if (i > 0 && m % 3 == 0) {
					buff1.append(",");
				}
				m++;
			}
			for (int i = buff1.length() - 1; i >= 0; i--) {
				buff2.append(buff1.charAt(i));
			}
			buff2.append(s.substring(index, 3 + index));
			return buff2.toString();
		}
		return s;
	}
}
