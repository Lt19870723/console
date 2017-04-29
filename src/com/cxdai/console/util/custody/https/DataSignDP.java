package com.cxdai.console.util.custody.https;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.codec.binary.Hex;
import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;

import com.cxdai.console.util.custody.DataProcessRule;
import com.cxdai.console.util.custody.StringUtil;

import sun.misc.BASE64Decoder;

public class DataSignDP extends DataProcessRule {
	
	private static X509Certificate bankCert = null;
	/*** 证书私钥 **/
	private static Signature sign = null;
	
	private static Signature verifySign = null;
	/*** 签名数据初始化状态。-1：初始化失败；0：正常，可进行签名操作；1：等待初始化**/
	private static int initStatus = 1;
	
	private static final Object lockObj = new Object();
	private static final String aAlgorithm = "SHA1WithRSA";

	private byte[] pkEncoded = null;

	private PrivateKey prikey;

	private Certificate cert;

	private String encodeCert = null; // base64编码的证书

	private PublicKey pubKey;
	
	private String serialNo = "";
	
	private static final String CanonicalizationMethod = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";	

	public static String getAAlgorithm() {
		return aAlgorithm;
	}
	
	public static String getCanonicalizationMethod() {
		return CanonicalizationMethod;
	}
	
	public Certificate getCert() {
		return cert;
	}

	public void setCert(Certificate cert) {
		this.cert = cert;
	}

	public byte[] getPkEncoded() {
		return pkEncoded;
	}

	public void setPkEncoded(byte[] pkEncoded) {
		this.pkEncoded = pkEncoded;
	}
	
	public String getSerialNo(){
		return serialNo;
	}

	public PrivateKey getPrikey() {
		return prikey;
	}

	public void setPrikey(PrivateKey prikey) {
		this.prikey = prikey;
	}

	public PublicKey getPubKey() {
		return pubKey;
	}

	public void setPubKey(PublicKey pubKey) {
		this.pubKey = pubKey;
	}
	public String getCertCN(){
		String certStr=this.cert.toString();
		return certStr.substring(certStr.indexOf("Subject")+12,certStr.indexOf(",")).trim();
	}
	
	public String getCertFullCN(){
		String certStr=this.cert.toString();
		return certStr.substring(certStr.indexOf("Subject: ")+9,certStr.indexOf("Signature Algorithm")).trim();
	}

	static{
		dpInit();
	}
	/**
	 * 环境初始化
	 *
	 */
	private static void dpInit(){
		System.out.println("初始化签名要素...");
		synchronized (lockObj) {
			System.out.println("初始化客户证书信息【成功】");
		}
	}
	/**
	 * 系统初始化时从指定文件中读取证书
	 * @param pfxFileName
	 * @param password
	 */
	public void readCert(String pfxFileName, String password) {
		char[] pfxPassword = password.toCharArray();
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream in = new FileInputStream(pfxFileName);
			ks.load(in, pfxPassword); // psss为密码
			in.close();
			Enumeration iEnum = ks.aliases();
			String keyAlias = null;
			for (int i=0;iEnum.hasMoreElements();i++) { // 获得别名列表.
				keyAlias = (String) iEnum.nextElement();
	            if( ks.isKeyEntry(keyAlias)) 
	            	break;
			}
			System.out.println("是否为实体Entry: "	+ ks.isKeyEntry(keyAlias));
			prikey = (PrivateKey) ks.getKey(keyAlias, pfxPassword);
			cert = ks.getCertificate(keyAlias);
			pubKey = cert.getPublicKey();
			serialNo = ((X509Certificate)cert).getSerialNumber().toString(16);
			if( serialNo.length() == 31 )
				serialNo = "0" + serialNo;
			try {
				byte[] bs = getCert().getEncoded();
				this.encodeCert = StringUtil.base64EncodeUTF(bs);
			} catch (CertificateEncodingException e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1);
		}
	}	
	/**
	 * 签名
	 * @param toSign
	 * @return
	 * @throws Exception
	 */
	public String signData(String toSign) throws Exception {

		Signature signature = Signature.getInstance(aAlgorithm);
		signature.initSign(this.prikey);
		signature.update(toSign.getBytes());
		byte[] signedData = signature.sign();

		return StringUtil.base64EncodeUTF(signedData);
	}
	
	public String signData(String toSign,PrivateKey pk) throws Exception {
		Signature signature = Signature.getInstance(aAlgorithm);
		signature.initSign(pk);
		signature.update(toSign.getBytes());
		byte[] signedData = signature.sign();
		
		return StringUtil.base64EncodeUTF(signedData);
	}
	/**
	 * 执行数据签名操作
	 */
	public void dataProcess(){
		if(initStatus != 0 ){
			System.out.println("客户证书信息未被正确初始化"	);
		}
		try {
			dataAP = null;
			sign.update(dataBP.getBytes());
			byte[] signature = sign.sign();
			dataAP = new sun.misc.BASE64Encoder().encode(signature);
			System.out.println( "签名前数据："+getDataBP());
			System.out.println( "签名后数据："+getDataAP());
		} catch (SignatureException e) {
			System.out.println( "数据签名失败" + e.getMessage());
		}
	}
	
	public String writePublicKey() throws Exception, IOException {
		return StringUtil.base64EncodeUTF(writePublicKey(this.pubKey));
	}

	public static byte[] writePublicKey(PublicKey pubkey) throws Exception,
			IOException {
		ByteArrayOutputStream bi = new ByteArrayOutputStream();
		java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(bi);
		out.writeObject(pubkey);
		out.close();
		byte[] bs = bi.toByteArray();
		return bs;
	}

	public static void writePublicKey(PublicKey pubkey, String filePath)
			throws Exception, IOException {

		java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
				new java.io.FileOutputStream(filePath));
		out.writeObject(pubkey);
		out.close();
	}

	public static PublicKey readPublicKey(byte[] bytes) {
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			java.io.ObjectInputStream in = new java.io.ObjectInputStream(bi);
			PublicKey pubkey = (PublicKey) in.readObject();
			in.close();
			return pubkey;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
	
	/*** 从指定证书中读公钥 **/
	public static PublicKey readCertPublicKey(String filePath)
	{
		try{
			 CertificateFactory   certFactory=CertificateFactory.getInstance("X.509");
			  FileInputStream   fis=new   FileInputStream(filePath);   
			  X509Certificate   cert=(X509Certificate)certFactory.generateCertificate(fis);   
			  fis.close();   
			  System.out.println(cert.getSerialNumber().toString(16));
			  
			  return (PublicKey)cert.getPublicKey();
		}catch( Exception e )
		{

			System.out.println(e.toString());
		}
		return null;
	}
	
	/** 从指定证书中读KeyStore **/
	public static KeyManagerFactory readJKS(String jksFile,String password)
	{
		try{
			KeyStore ks = KeyStore.getInstance("JKS"/*,"IBMJCE"*/);
			FileInputStream fs = new FileInputStream(jksFile);
			ks.load(fs,password.toCharArray());
			fs.close();
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("IbmX509"/*, "IBMJSSE2"*//*"SunJSSE"*/);
			kmf.init(ks, password.toCharArray());
			return kmf;
		}catch( Exception e )
		{

			System.out.println(e.toString());
		}
		return null;
	}	
	
	public static PublicKey readPublicKey(String filePath, String password) {
		try {
			char[] pfxPassword = password.toCharArray();
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream in = new FileInputStream(filePath);
			ks.load(in, pfxPassword); // psss为密码
			in.close();
			Enumeration iEnum = ks.aliases();
			String keyAlias = null;
			for (int i=0;iEnum.hasMoreElements();i++) { // 获得别名列表.
				keyAlias = (String) iEnum.nextElement();
	            if( ks.isKeyEntry(keyAlias)) break;
			}
			System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));			
			
			Certificate serverCert = ks.getCertificate(keyAlias);
			
			PublicKey pubkey = serverCert.getPublicKey();
			
			return pubkey;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public static PublicKey readPublicKey(String filePath, String password, String alias) {
		try {
			char[] pfxPassword = password.toCharArray();
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream in = new FileInputStream(filePath);
			ks.load(in, pfxPassword); // psss为密码
			in.close();
			Enumeration iEnum = ks.aliases();
			String keyAlias = null;
			while(iEnum.hasMoreElements())
			{
				keyAlias = (String) iEnum.nextElement();
				if( keyAlias.equals(alias) ){
					System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
					
					Certificate serverCert = ks.getCertificate(keyAlias);
					
					PublicKey pubkey = serverCert.getPublicKey();
					
					return pubkey;					
				}
			}
			System.out.println("证书[" + alias + "]不存在！");
			return null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	/**
	 *  为提高效率可以缓存此对象，而不必每次计算 
	 *  @return
	 */
	public String getEncodeCert() {
		return encodeCert;
	}

	public static Certificate readCertFromByte(String base64)
			throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		ByteArrayInputStream bas = new ByteArrayInputStream(StringUtil.base64DecodeUTF(base64));
		return cf.generateCertificate(bas);
	}

	/**
	 * 验证签名
	 * 
	 * @param srcData
	 * @param aVerifyingData
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public boolean verify(byte[] srcData, byte[] aVerifyingData)
			throws NoSuchAlgorithmException, Exception {

		Signature signature = Signature.getInstance(aAlgorithm);
		signature.initVerify(this.pubKey);
		signature.update(srcData);

		if (signature.verify(aVerifyingData)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verify(byte[] srcData, PublicKey aPublicKey, byte[] aVerifyingData)
			throws NoSuchAlgorithmException, Exception {

		Signature signature = Signature.getInstance(aAlgorithm);
		signature.initVerify(aPublicKey);
		signature.update(srcData);
		
		if (signature.verify(aVerifyingData)) {
			return true;
		} else {
			return false;
		}
	}	
	
	/**
	 * 验证签名
	 * @param srcData
	 * @param aVerifyingData
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public boolean verify(String srcData, String aVerifyingData)
		throws NoSuchAlgorithmException, Exception {

		Signature signature = Signature.getInstance(aAlgorithm);
		signature.initVerify(this.pubKey);
		signature.update(srcData.getBytes());

		byte[] verifyData = StringUtil.base64DecodeUTF(aVerifyingData);
		if (signature.verify(verifyData)) {
			return true;
		} else {
			return false;
		}
	}	
    
	public static boolean verifyUseSignatureMethod(String srcData, PublicKey aPublicKey,
			String aVerifyingData) throws NoSuchAlgorithmException, Exception {
		
		Init.init();
	    Canonicalizer canonicalizer = Canonicalizer.getInstance(DataSignDP.getCanonicalizationMethod());
	    byte[] srcByte = canonicalizer.canonicalize(srcData.getBytes("UTF-8"));
		
		Signature signature = Signature.getInstance(DataSignDP.getAAlgorithm());
		signature.initVerify(aPublicKey);
		signature.update(srcByte);

		if (signature.verify(StringUtil.base64DecodeUTF(aVerifyingData))) {
			return true;
		} else {
			return false;
		}
	}
    
	public static boolean verify(String srcData, PublicKey aPublicKey,
			String aVerifyingData) throws NoSuchAlgorithmException, Exception {
		
		Signature signature = Signature.getInstance(DataSignDP.getAAlgorithm());
		signature.initVerify(aPublicKey);
		signature.update(srcData.getBytes());

		if (signature.verify(StringUtil.base64DecodeUTF(aVerifyingData))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean verify(String srcData, Certificate cert,
			String aVerifyingData) throws NoSuchAlgorithmException, Exception {
		return verify(srcData, cert.getPublicKey(), aVerifyingData);
	}
	
    protected static byte[] standard(String s, String s1)
    	throws Exception {
	    byte abyte[]=null;
	    try {
	        Init.init();
	        Canonicalizer canonicalizer = Canonicalizer.getInstance(s);
	        abyte = canonicalizer.canonicalize(s1.getBytes("UTF-8"));
	    } catch(Exception exception) {
	    	System.out.println(exception.toString());
	    }
	    return abyte;
    }	
	/**
	 * 执行摘要操作
	 */   
    public  static String digest(byte[] contents) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(contents);
            return new String(Hex.encodeHex(messageDigest.digest())).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
        	
        }
        return null;
    }   
	/**
	 * 执行数据验签操作
	 */
	public void dataProcessDE() {
		
		if(initStatus != 0 ){
			System.out.println(	"客户证书信息未被正确初始化"	);
		}
		
		try {
			byte[] signature = new BASE64Decoder().decodeBuffer(dataAP);
			verifySign.initVerify(bankCert.getPublicKey());
			verifySign.update(dataBP.getBytes());
			if(verifySign.verify(signature)){
				setPassed(true);
			}else{
				setPassed(false);
			}
		} catch (Exception e) {
			System.out.println("数据签名验证失败"	+ e.getMessage());
		}
	}
}