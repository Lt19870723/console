package com.cxdai.console.security.custody;

import java.io.FileInputStream;
import java.security.PublicKey;

import javax.net.ssl.KeyManagerFactory;

import com.cxdai.console.util.custody.Trace;
import com.cxdai.console.util.custody.XML;
import com.cxdai.console.util.custody.https.DataSignDP;
import com.org.jdom.Document;
/**
 * 客户端常量定义
 * 
 * @author Wangjm
 * 
 */
public class SignConstance {

	private static boolean NoKoal = false; // 是否使用格尔的签名验证机制

	private static PublicKey serverPublicKey = null;
	
	private static PublicKey serverCertPublicKey = null;
	
	private static String serverPublicAlias = null;

	private static int soTimeOut, maxLines;

	private static DataSignDP clientCert = null;
	//public static KoalSignUtil clientCert = null;
	
	private static KeyManagerFactory clientSSLCert = null;

	private static String rootConfig = null;
	private static String traceConfigFileName = null;
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
			//System.out.println(rootConfig);
			if( rootConfig == null || rootConfig.length() == 0 )
			{
				String separator = System.getProperty("file.separator");
				rootConfig = fileName.substring(0,fileName.lastIndexOf(separator.charAt(0))+1);
				System.out.println("P2PDepositConstance rootConfig="+rootConfig);
			}
			
			traceConfigFileName = rootConfig+ xml.getChildValue("traceConfigFileName");
           
			//商户服务器地址
			serverURL = xml.getChildValue("serverURL");
			
			String serverPublicKeyFile = rootConfig+ xml.getChildValue("serverPublicKeyFile");
			
			String serverCertPassword = xml.getChildValue("serverPublicPassword");
			//System.out.println(serverCertPassword);
			serverPublicAlias = xml.getChildValue("serverPublicAlias");
			serverPublicKey = DataSignDP.readPublicKey(serverPublicKeyFile, serverCertPassword, serverPublicAlias);
			
			//从商户公钥读取指定的公钥
			String serverCertPublicKeyFile = rootConfig+ xml.getChildValue("serverCertFile");
			serverCertPublicKey = DataSignDP.readCertPublicKey(serverCertPublicKeyFile);

			//每个批次中最多容许的记录条数
			maxLines = Integer.parseInt(xml.getChildValue("maxLines"));
			//银行证书文件
			String clientCertFile = rootConfig+ xml.getChildValue("clientCertFile");
			String clientCertPassword = xml.getChildValue("clientCertPassword");
			clientCert = new DataSignDP();
			//clientCert = new KoalSignUtil();
			clientCert.readCert(clientCertFile, clientCertPassword);
			
			clientCertId = clientCert.getSerialNo();
			Trace.trace(true, "->CORE", Trace.Information, Trace.Information,"", "初始化证书序号: "
					+clientCertId);
			//SSL服务证书
			String clientSSLCertFile = rootConfig + xml.getChildValue("clientSSLFile");
			String clientSSLCertPassword = xml.getChildValue("clientSSLPassword");

			clientSSLCert = DataSignDP.readJKS(clientSSLCertFile, clientSSLCertPassword);
			soTimeOut = Integer.parseInt(xml.getChildValue("soTimeOut"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			initialize("D:\\zs_bank\\CZBSAA\\WebContent\\WEB-INF\\server\\config.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
