package com.cxdai.console.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpClientUtils {
	private static final Logger logger = Logger.getLogger(HttpClientUtils.class);

	public static String getRomoteString(HttpClient httpclient, String url, Object params) throws Exception {
		System.out.println("访问地址:" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		int statusCode = 0;
		if (params != null) {
			List<NameValuePair> paramsList = getParamsList(params);
			NameValuePair[] nvps = new NameValuePair[paramsList.size()];
			postMethod.setRequestBody(paramsList.toArray(nvps));
		}
		statusCode = httpclient.executeMethod(postMethod);
		if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
			throw new Exception("请求接口失败，失败码[ " + statusCode + " ]");
		}
		return inputStream2String(postMethod.getResponseBodyAsStream());
	}

	/**
	 * 传入参数对象直接发送请求
	 * <p>
	 * 传入的参数需要与属性个数一致，并附上对应的get方法，返回的是集合
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月20日
	 * @param httpclient
	 *            从内存中取出来的http客户端
	 * @param url
	 *            要访问的路径
	 * @param params
	 *            访问url时要携带的参数对象
	 * @param clazz
	 *            返回对象的名称
	 * @return
	 * @throws Exception
	 *             Object
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, ?> getRomoteObject(HttpClient httpclient, String url, Object params) throws Exception {
		System.out.println("访问地址:" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		int statusCode = 0;
		List<NameValuePair> paramsList = getParamsList(params);
		NameValuePair[] nvps = new NameValuePair[paramsList.size()];
		postMethod.setRequestBody(paramsList.toArray(nvps));
		statusCode = httpclient.executeMethod(postMethod);
		if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
			throw new Exception("请求接口失败，失败码[ " + statusCode + " ]");
		}
		Object obj = WxUtils.json2Bean(inputStream2String(postMethod.getResponseBodyAsStream()), Object.class);
		if (obj == null) {
			return null;
		}
		return (LinkedHashMap<String, ?>) obj;
	}

	/**
	 * 传入参数对象直接发送请求
	 * <p>
	 * 传入的参数需要与属性个数一致，并附上对应的get方法，返回的是集合
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月20日
	 * @param httpclient
	 *            从内存中取出来的http客户端
	 * @param url
	 *            要访问的路径
	 * @param params
	 *            访问url时要携带的参数对象
	 * @param clazz
	 *            返回对象的名称
	 * @return
	 * @throws Exception
	 *             Object
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, ?> getRomoteObjectByUUID(HttpClient httpclient, String url, Object params, String uuid) throws Exception {
		System.out.println("访问地址:" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		int statusCode = 0;
		List<NameValuePair> paramsList = getParamsList(params);
		paramsList.add(new NameValuePair(uuid, uuid));
		NameValuePair[] nvps = new NameValuePair[paramsList.size()];
		postMethod.setRequestBody(paramsList.toArray(nvps));
		statusCode = httpclient.executeMethod(postMethod);
		if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
			throw new Exception("请求接口失败，失败码[ " + statusCode + " ]");
		}
		Object obj = WxUtils.json2Bean(inputStream2String(postMethod.getResponseBodyAsStream()), Object.class);
		if (obj == null) {
			return null;
		}
		return (LinkedHashMap<String, ?>) obj;
	}

	/**
	 * 传入参数对象直接发送请求
	 * <p>
	 * 传入的参数需要与属性个数一致，并附上对应的get方法，返回的是集合
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月20日
	 * @param httpclient
	 *            从内存中取出来的http客户端
	 * @param url
	 *            要访问的路径
	 * @param params
	 *            访问url时要携带的参数对象
	 * @param clazz
	 *            返回对象的名称
	 * @return
	 * @throws Exception
	 *             Object
	 */
	public static Object getRomoteObject(HttpClient httpclient, String url, Object params, Class<?> clazz) throws Exception {
		System.out.println("访问地址:" + url);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		int statusCode = 0;
		List<NameValuePair> paramsList = getParamsList(params);
		NameValuePair[] nvps = new NameValuePair[paramsList.size()];
		postMethod.setRequestBody(paramsList.toArray(nvps));
		statusCode = httpclient.executeMethod(postMethod);
		if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
			throw new Exception("请求接口失败，失败码[ " + statusCode + " ]");
		}
		Object obj = WxUtils.json2Bean(inputStream2String(postMethod.getResponseBodyAsStream()), clazz);
		if (obj == null) {
			return clazz.newInstance();
		}
		return obj;
	}

	/**
	 * 传入参数对象直接发送请求
	 * <p>
	 * 传入的参数需要与属性个数一致，并附上对应的get方法，返回的是集合
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月20日
	 * @param httpclient
	 *            从内存中取出来的http客户端
	 * @param url
	 *            要访问的路径
	 * @param params
	 *            访问url时要携带的参数对象
	 * @param classname
	 *            返回对象的名称
	 * @return
	 * @throws Exception
	 *             Object
	 */
	public static List<?> getRomoteList(HttpClient httpclient, String url, Object params) throws Exception {
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		int statusCode = 0;
		List<NameValuePair> paramsList = getParamsList(params);
		NameValuePair[] nvps = new NameValuePair[paramsList.size()];
		postMethod.setRequestBody(paramsList.toArray(nvps));
		statusCode = httpclient.executeMethod(postMethod);
		if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
			throw new Exception("请求接口失败，失败码[ " + statusCode + " ]");
		}
		return WxUtils.json2List(inputStream2String(postMethod.getResponseBodyAsStream()));
	}

	/**
	 * 获取图片的字节数组
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月20日
	 * @param httpclient
	 * @param url
	 * @return
	 * @throws Exception
	 *             byte[]
	 */
	public static byte[] getImage(HttpClient httpclient, String url) throws Exception {
		try {
			PostMethod postMethod = new PostMethod(url);
			postMethod.setRequestBody(new NameValuePair[0]);
			httpclient.executeMethod(postMethod);
			BufferedInputStream in = new BufferedInputStream(postMethod.getResponseBodyAsStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[2500];
			int size;
			while ((size = in.read(buf)) != -1) {
				out.write(buf, 0, size);
			}
			in.close();
			out.close();
			return out.toByteArray();
		} catch (Exception e) {
			logger.error("获取图片异常", e);
			JOptionPane.showMessageDialog(null, "获取验证码错误" + e.getMessage());
		}
		return null;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月18日
	 * @param params
	 * @param list
	 *            void
	 */
	private static List<NameValuePair> getParamsList(Object obj) {
		Object o;
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (obj == null)
			return list;
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			String name;
			String value;
			for (Field field : fields) {
				name = field.getName();
				if (name.equals("token"))
					continue;
				try {
					o = obj.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)).invoke(obj, new Object[] {});
				} catch (NoSuchMethodException e) {
					continue;
				}
				if (o == null)
					continue;
				value = String.valueOf(o);

				list.add(new NameValuePair(name, value));
			}
		} catch (Exception e) {
			logger.error("参数获取异常：" + e);
		}
		return list;
	}

	/**
	 * 读返回值
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ZHUCHEN
	 * @version 0.1 2014年10月16日
	 * @param is
	 * @return
	 * @throws IOException
	 *             String
	 */
	private static String inputStream2String(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line + "\n");
		}
		int i = buffer.lastIndexOf("\n");
		if (i != -1)
			buffer.deleteCharAt(i);
		System.out.println("返回：--------------" + buffer.toString());
		return buffer.toString();
	}
}
