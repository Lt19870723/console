package com.cxdai.console.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Description:基本Http工具类<br />
 * </p>
 * 
 * @title HttpTookit.java
 * @package com.util
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public final class HttpTookit {
	private static Log log = LogFactory.getLog(HttpTookit.class);

	/**
	 * <p>
	 * Description:获得ip地址<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月16日
	 * @param request
	 * @return String
	 */
	public static String getRealIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 
	 * <p>
	 * Description:获得绝对上下文地址<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月16日
	 * @param request
	 * @return String
	 */
	public static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}

	public static String getRealMac(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}
	
	// 获取MAC地址的方法
	public static String getMACAddress(InetAddress ia) {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = null;
		try {
			mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}

		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}

}