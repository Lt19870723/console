package com.cxdai.console.util.custody.https;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Vector;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import com.cxdai.console.util.custody.StringUtil;


public class HttpsConnection {
	private String urlTarget ;

	private HttpsURLConnection urlCon = null;

	private String trustedHostName;

	private String trustedPublicDN;

	private Signature signature;

	private String signalgorithm = "SHA1WithRSA";

	Vector trustedPK = new Vector();

	private byte[] encodedPubKey;

	private int timeOut;

	private boolean keepAlive = false;

	private boolean using = false;
	
	private static HostnameVerifier hnv = new HostnameVerifier() {
		public boolean verify(String hostName, SSLSession session) {
			System.out.println("hostName=[" + hostName + "]");
			try {
				URL aUrl = new URL(SignConstance.getServerURL());
				String host = aUrl.getHost();
				if (host.equals(hostName))
					return true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return false;
		}
	};

	public HttpsConnection(String urlTarget, int timedOut) {
		this.timeOut = timedOut * 1000;
		this.urlTarget = urlTarget;
	}

	static SSLContext sslContext = null;
	
	public void post(String filePath) throws IOException {
		String line;
		String reqMessage="";
		BufferedReader  in=new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),"UTF-8"));
		while ((line = in.readLine()) != null) {
                  reqMessage+=line;
		}   

		try {
			String repMsg = postAndReceive(reqMessage.getBytes());
			System.out.println("HTTPS响应报文=["+repMsg+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  鍒涘缓HttpsConnection
	 * @param postMsg
	 * @return
	 * @throws ApiException
	 */	    
  public void createUrlConnection() throws Exception{
	try{
		if (urlCon == null) {
			try {
				System.out.println("HttpsConnection init static......");
				javax.net.ssl.TrustManager[] trustAllCerts =
					new javax.net.ssl.TrustManager[1];

				javax.net.ssl.TrustManager tm = new miTM();
				trustAllCerts[0] = tm;

				sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustAllCerts, null);
				
				if (sslContext != null)
					HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			} catch (GeneralSecurityException gse) {
				System.out.println("HttpsConnection init error......"+gse.getMessage());
			}
			HttpsURLConnection.setDefaultHostnameVerifier(hnv);
			System.out.println("HttpsConnectionUrl urlTarger=["+this.urlTarget+"]");
			
			URL aURL = new URL(this.urlTarget);
			CustomSSLSocketFactory socketFactory = new CustomSSLSocketFactory(
					HttpsURLConnection.getDefaultSSLSocketFactory(),
					this.timeOut, this.keepAlive);
			
			HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);			
			urlCon = (HttpsURLConnection) aURL.openConnection();				
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setUseCaches(false);
			urlCon.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			urlCon.setRequestProperty("User-Agent", "Java/1.4.2");
		}
		urlCon.setInstanceFollowRedirects(true);
	} catch (MalformedURLException mue) {
		System.out.println(mue);
		throw mue;
	} catch (IOException ioe) {
		System.out.println(ioe);
		throw ioe;
	} catch (Exception e) {
		System.out.println(e);
		throw e;
	}
  }	
	/**
	 * 发送/接受HTTPS请求
	 * @param postMsg
	 * @return
	 * @throws ApiException
	 */
	public String postAndReceive(byte[] postMsg) throws Exception {
		
		try {
			createUrlConnection();
			OutputStream outputStream = urlCon.getOutputStream();
			if (postMsg != null && postMsg.length != 0) {
				outputStream.write(postMsg);
			}
			outputStream.flush();
			outputStream.close();
			
			System.out.println("https请求超时时长=[" + this.timeOut + "]");
			int rcvLen = urlCon.getContentLength();
			System.out.println("https响应报文长度=[" + rcvLen + "]");
			String retContent = null;
			if ( rcvLen > 0 ) {
				
				byte[] rcvBuffer = readFull(rcvLen, urlCon.getInputStream());
				retContent = StringUtil.bytes2String(rcvBuffer, "UTF-8");
				
			} else if ( rcvLen == -1 ) {
				
				retContent = readFull(urlCon.getInputStream());
				
			}
			if( urlCon.getResponseCode() != urlCon.HTTP_OK ) {
				System.out.println("HTTP"+urlCon.getResponseCode() + "HttpsURLConnection.postAndReceive()"+urlCon.getResponseMessage());
			}
			System.out.println( "HTTPS请求响应码=[" + urlCon.getResponseCode() +"]");
			return retContent;
		} catch (MalformedURLException mue) {
			throw mue;
		} catch (IOException ioe) {
			System.out.println(ioe);
			throw ioe;
		} catch (Exception e) {
			System.out.println("https请求异常错误信息=["+e.getStackTrace()+"]["+e.getMessage()+"]");
			throw e;
		} finally {
			if (!keepAlive && urlCon != null)
				urlCon.disconnect();
		}
	}
	/**
	 * @param postMsg
	 * @return
	 * @throws ApiException
	 */	
    public InputStream getResponseBodyAsStream()throws Exception {
    	InputStream input=null;
    	try {
			createUrlConnection();
    	    int rcvLen = urlCon.getContentLength();
		    System.out.println("返回内容长度=["+rcvLen+"]");
			input = urlCon.getInputStream();
		} catch (Exception e) {
			throw e;
		}
		    return input;
}	

    public static int copy(InputStream input, OutputStream output)
    	throws IOException {
	    long count = copyLarge(input, output);
	    if(count > 2147483647L)
	        return -1;
	    else
	    	return (int)count;
    }
 
    public static long copyLarge(InputStream input, OutputStream output)
    throws IOException
{
    byte buffer[] = new byte[4096];
    long count = 0L;
    for(int n = 0; -1 != (n = input.read(buffer));)
    {
        output.write(buffer, 0, n);
        count += n;
    }

    return count;
}   
	/**
	 * @param contentLen
	 * @param in
	 * @return
	 * @throws IOException
	 */
	protected byte[] readFull(int contentLen, InputStream in)
			throws IOException {
		byte buffer[] = new byte[contentLen];
		int off = 0;
		int len = 0;
		while (true) {
			len = in.read(buffer, off, contentLen - off);
			if (len == -1 || len == 0) {
				break;
			}
			off = off + len;
			if (off >= contentLen) {
				break;
			}
		}
		in.close();
		return buffer;
	}
	
	protected String readFull(InputStream in)
			throws IOException {
		final int contentLen = 512 * 1024;
		byte buffer[] = new byte[contentLen];
		int off = 0;
		int len = 0;
		while (true) {
			len = in.read(buffer, off, contentLen - off);
			if (len == -1 || len == 0) {
				break;
			}
			off = off + len;
			if (off >= contentLen) {
				break;
			}
		}
		in.close();
		return new String(buffer,0,off,"utf-8");
	}
	
	public String getUrl() {
		return urlTarget;
	}

	public void setUrl(String url) {
		this.urlTarget = url;
	}

	public byte[] getEncodedPubKey() {
		return encodedPubKey;
	}

	public void setEncodedPubKey(byte[] encodedPubKey) {
		this.encodedPubKey = encodedPubKey;
	}

	public String getSignalgorithm() {
		return signalgorithm;
	}

	public void setSignalgorithm(String signalgorithm) {
		this.signalgorithm = signalgorithm;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	public String getTrustedHostName() {
		return trustedHostName;
	}

	public void setTrustedHostName(String trustedHostName) {
		this.trustedHostName = trustedHostName;
	}

	public Vector getTrustedPK() {
		return trustedPK;
	}

	public void setTrustedPK(Vector trustedPK) {
		this.trustedPK = trustedPK;
	}

	public String getTrustedPublicDN() {
		return trustedPublicDN;
	}

	public void setTrustedPublicDN(String trustedPublicDN) {
		this.trustedPublicDN = trustedPublicDN;
	}

	public boolean isBusy() {
		return false;
	}

	public void setBusy(boolean isBusy) {

	}

	public void stop() {
		if (urlCon != null) {
			urlCon.disconnect();
			urlCon = null;
		}

	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getUrlTarget() {
		return urlTarget;
	}

	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}

	public boolean isUsing() {
		return using;
	}

	public void setUsing(boolean using) {
		this.using = using;
	}

	
	public static class miTM
		implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
		
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		public boolean isServerTrusted(
			java.security.cert.X509Certificate[] certs) {
			return true;
		}
		
		public boolean isClientTrusted(
			java.security.cert.X509Certificate[] certs) {
			return true;
		}
		
		public void checkServerTrusted(
			java.security.cert.X509Certificate[] certs,
			String authType)
			throws java.security.cert.CertificateException {
			
			X509Certificate cert = null;
			boolean verifyPass = false;
			for(int i = 0; i < certs.length; i++){
				cert = certs[i];
				System.out.println("证书序号=["+i+"]证书SN=["+cert.getSerialNumber().toString(16)+"]");
				if (SignConstance.getServerPublicKey().equals(cert.getPublicKey())){
					verifyPass = true;
					break;
				}
				else {
					verifyPass = false;
				}
			}
			
			if (verifyPass) {
				System.out.println("服务器SSL链接验证通过");
			} else {
				System.out.println("服务器SSL链接验证不通过");
				throw new CertificateException("服务器SSL链接验证不通过");
			}
			return;
		}
		
		
		public void checkClientTrusted(
			java.security.cert.X509Certificate[] certs,
			String authType)
			throws java.security.cert.CertificateException {
			return;
		}
	}
}