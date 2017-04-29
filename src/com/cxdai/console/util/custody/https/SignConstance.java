package com.cxdai.console.util.custody.https;

import java.io.FileInputStream;
import java.security.PublicKey;

import javax.net.ssl.KeyManagerFactory;

import com.cxdai.console.util.custody.XML;
import com.org.jdom.Document;

/**
* 客户端常量定义
* @author YED
*/
public class SignConstance {


	private static PublicKey serverPublicKey = null;
	
	private static PublicKey serverCertPublicKey = null;
	
	private static String serverPublicAlias = null;

	private static int soTimeOut;

	private static DataSignDP clientCert = null;
	
	private static KeyManagerFactory clientSSLCert = null;

	private static String rootConfig = null;
	private static String serverURL = null;
	private static boolean intialized = false;
	/* 客户证书序列号 */
	private static String clientCertId = null;
	
	public static String getClientCertID(){
		return clientCertId;
	}
	
	public static boolean isIntialized()
	{
		return intialized; 
	}
	
	public static String getServerPublicAlias(){
		return serverPublicAlias;
	}
	
	public static String getServerURL()
	{
		return serverURL;
	}
	
	public static DataSignDP getClientCert()
	{
		return clientCert;
	}
	
	public static KeyManagerFactory getSSLClientCert()
	{
		return clientSSLCert;
	}
	
	public static PublicKey getServerPublicKey()
	{
		return serverPublicKey;
	}
	
	public static PublicKey getServerCertPublicKey()
	{
		return serverCertPublicKey;
	}
	
	public static int getSoTimeOut()
	{
		return soTimeOut;
	}

	public static void initialize(String fileName)
			throws Exception {

		try {
			intialized = true;
			FileInputStream resourceAsStream = new FileInputStream(fileName);
			Document document = XML.LoadFromStream(resourceAsStream);

			XML xml = new XML(document);
			rootConfig = xml.getChildValue("rootConfig");
			if( rootConfig == null || rootConfig.length() == 0 ) {
				String separator = System.getProperty("file.separator");
				rootConfig = fileName.substring(0,fileName.lastIndexOf(separator.charAt(0))+1);
				System.out.println("配置信息路径 rootConfig="+rootConfig);
			}
			
			//商户服务器地址
			serverURL = xml.getChildValue("serverURL");
			String serverPublicKeyFile = rootConfig+ xml.getChildValue("serverPublicKeyFile");
			String serverCertPassword = xml.getChildValue("serverPublicPassword");
			serverPublicAlias = xml.getChildValue("serverPublicAlias");
			serverPublicKey = DataSignDP.readPublicKey(serverPublicKeyFile, serverCertPassword, serverPublicAlias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据定义的长度范围
	 * 
	 * @author Wangjm
	 * 
	 */
	class DataLenLimit {
		public int lenMin;

		public int lenMax;

		public DataLenLimit(int lenMin, int lenMax) {
			this.lenMin = lenMin;
			this.lenMax = lenMax;
		}

		public boolean isValid(int len) {
			return (len >= lenMin) && (len <= lenMax);
		}
	}
	
	public static void main(String[] args) {
		try {
			initialize("D:\\workspace\\HttpsClientDemo\\WebContent\\configFile\\config.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
