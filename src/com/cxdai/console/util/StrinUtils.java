package com.cxdai.console.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class StrinUtils {

	// 隐藏字符串内容
	public static String stringSecretTo(String str) {

		if (str == null)
			str = "";
		StringBuilder sb2 = new StringBuilder(str);

		if (sb2.length() > 1) {
			sb2.replace(1, sb2.length() - 1, "**");
		} else {
			sb2.replace(0, sb2.length(), "*");
		}

		return sb2.toString();
	}

	// 加密字符串
	public static String stringEncryptEn(String strEn) {
		if (strEn == null)
			strEn = "";
		// 字符串转换为字节
		byte[] bt = strEn.getBytes();

		// base64加密 ---只能加密字节；
		Base64 be = new Base64();
		String str = be.encode(bt);

		// System.out.println(bt);
		// System.out.println(str);

		return str;
	}

	// 解密字符串
	public static String stringEncryptDe(String strDe) {

		// base64解密---解密字符串为字节
		Base64 bd = new Base64();
		byte[] bt2 = bd.decode(strDe);

		// 字节转换为字符串
		String str2 = new String(bt2);

		// System.out.println(bt2);
		// System.out.println(str2);

		return str2;
	}
}
