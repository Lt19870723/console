package com.cxdai.console.util.httputil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liucy IO流工具类
 */
public class BufStreamUtil {
	/**
	 * 读取字符串流，返回字符串
	 * 
	 * @param in
	 * @param codeType GBK、UTF-8
	 * @return
	 * @throws IOException
	 */
	public static String readStrStream(InputStream in, String codeType) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedInputStream buf = null;
		try {
			buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;
			while ((iRead = buf.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, iRead, codeType));
			}
		} finally {
			if (buf != null)
				buf.close();
		}

		return sb.toString();
	}
}
