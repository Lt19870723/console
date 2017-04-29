package com.cxdai.console.security.custody;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import com.cxdai.console.util.custody.Trace;
import com.cxdai.console.util.custody.XML;
import com.org.jdom.Document;
import com.org.jdom.Element;
/**
 * 客户端常量定义
 * 
 * @author Wangjm
 * 
 */
public class SignManagerConstance {

	private static boolean NoKoal = false; // 是否使用格尔的签名验证机制
	
	private static Hashtable merInfoTable = new Hashtable();
	private static Hashtable serverCertPublicKeyTable = new Hashtable();

	private static PublicKey serverPublicKey = null;
	
	private static Key serverCertPublicKey = null;
	
	private static String serverPublicAlias = null;

	private static int soTimeOut, maxLines;
	private static PrivateKey priKey=null;
	private static PublicKey publicKey=null;

	private static KeyReader clientCert = null;
	
	private static KeyManagerFactory clientSSLCert = null;

	private static String rootConfig = null;
	private static String traceConfigFileName = null;
	private static String serverURL = null;
	private static boolean intialized = false;
	/* 客户证书序列号 */
	private static String clientCertId = null;
	
	private static String merchant = null;
	
//	public static String getMerName(String channelCode){
//		return (String)merInfoTable.get(channelCode);
//	}
	
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
	
	public static KeyReader getClientCert()
	{   
		return clientCert;
	}
	public static PrivateKey getPriKey()
	{   
		return priKey;
	}
	
	public static KeyManagerFactory getSSLClientCert()
	{
		return clientSSLCert;
	}
	
	public static PublicKey getServerPublicKey()
	{
		return serverPublicKey;
	}
	
	public static Key getServerCertPublicKey(String channelCode)
	{
		return (Key)serverCertPublicKeyTable.get(channelCode);
	}
	
	public static int getSoTimeOut()
	{
		return soTimeOut;
	}
	
	public static void initialize(String fileName,String merType)
			throws Exception {
		FileInputStream resourceAsStream = null;
		try {
			intialized = true;
			resourceAsStream = new FileInputStream(fileName);
			Document document = XML.LoadFromStream(resourceAsStream);

			XML xml = new XML(document);
			rootConfig = xml.getChildValue("rootConfig");
			if( rootConfig == null || rootConfig.length() == 0 )
			{
				String separator = System.getProperty("file.separator");
				rootConfig = fileName.substring(0,fileName.lastIndexOf(separator.charAt(0))+1);
//				System.out.println("P2PDepositConstance rootConfig="+rootConfig);
			}
			traceConfigFileName = rootConfig+ xml.getChildValue("traceConfigFileName");
			//判断商户
			Trace.info("开始初始化"+merType+"config文件");
			String serverCertPublicKeyFile="",clientCertFile="",clientCertPassword="";
			
			//初始化商户config文件
			merchant= xml.getChildValue("merChant");
			serverURL = xml.getChildValue("serverURL");
			//从商户公钥读取指定的公钥
			merchant= xml.getChildValue("merChant");
			String[] channelCodes = merchant.split("\\|");
			String merName = xml.getChildValue("merName");
			String[] merNames = merName.split("\\|");
			//加载商户名称和商户证书公钥成功
			for(int i=0;i<channelCodes.length;i++){
//				merInfoTable.put(channelCodes[i], merNames[i]);
				serverCertPublicKeyFile = rootConfig+ xml.getChildValue("serverCertFile_"+channelCodes[i]);
				serverCertPublicKeyTable.put(channelCodes[i], KeyReader.fromCerStoredFile(serverCertPublicKeyFile));
				Trace.info("读取"+merNames[i]+"商户证书公钥成功");
			}

			//每个批次中最多容许的记录条数
			maxLines = Integer.parseInt(xml.getChildValue("maxLines"));
			//银行证书文件
			clientCertFile = rootConfig+ xml.getChildValue("clientCertFile");
			clientCertPassword = xml.getChildValue("clientCertPassword");
			clientCert = new KeyReader();
			priKey=clientCert.readPrivateKeyfromPKCS12StoredFile(clientCertFile, clientCertPassword);
			publicKey=(PublicKey)clientCert.fromPKCS12StoredFile(clientCertFile, clientCertPassword);
			soTimeOut = Integer.parseInt(xml.getChildValue("soTimeOut"));
			Trace.info("结束初始化"+merType+"config文件");
			

		} catch (Exception e) {
			e.printStackTrace();
			Trace.info("初始化商户config.xml文件参数异常:["+e.getMessage()+"]");
		} finally {
			if(resourceAsStream!=null)
				resourceAsStream.close();
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
//			initialize("D:\\zs_bank\\CZBSAA\\WebContent\\WEB-INF\\server\\config.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PublicKey getPublicKey() {
		return publicKey;
	}


}
