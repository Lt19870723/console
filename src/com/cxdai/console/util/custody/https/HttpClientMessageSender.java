/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2007 All Rights Reserved.
 */
package com.cxdai.console.util.custody.https;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.cxdai.console.security.custody.KeyReader;
import com.cxdai.console.security.custody.SignManagerImpl;
import com.cxdai.console.util.custody.XML;

public class HttpClientMessageSender {
    private HttpConnectionManager connectionManager;
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
    	int Count = 100000;
    	while(Count > 0) {
    		Date date = new Date();
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        	SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMdd");
        	String seqOrderNo = "KFSH";
        	
    		String reqMessage="<Finance><Message id=\"2015110316450021308\"><RCReq id=\"RCReq\"><version>1.0.1</version><instId>PAY_RB</instId><certId>0001</certId><date>"
    						+ sdf.format(date) + "</date><p2pId>P2P0000001</p2pId><depositSerialNo>CST0000001</depositSerialNo><OrderNo>"
    						+ seqOrderNo + Count + "</OrderNo><Amount>"
    						+ Count + "</Amount><Fee>10</Fee><settleDate>"
    						+ sdf2.format(date) + "</settleDate><extension></extension></RCReq></Message></Finance>";
    		
    		PrivateKey privateKey = null;
    		
    		String clientCertFile = "D:\\workspace\\HttpsClientWebDemo\\WebContent\\configFiles\\aliapy.pfx";
    		KeyReader clientCert = new KeyReader();
    		privateKey=clientCert.readPrivateKeyfromPKCS12StoredFile(clientCertFile, "a121212");
    		
    		SignManagerImpl si = new SignManagerImpl();
    		XML xml=XML.readFrom(reqMessage);
    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc=docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
    		reqMessage =si.sign(doc, privateKey, "RCReq");
    		System.out.println("������=["+reqMessage+"]");	    
            HttpClientMessageSender messageSender = new HttpClientMessageSender();
            String rep= messageSender.send(reqMessage, "http://10.0.247.93:9081/P2PDEPOSIT/P2PReq.srv");
            System.out.println("��Ӧ����=["+rep+"]");
            Count --;
    	}
    }

    public String send(String reqXml, String postUrl) throws Exception {

        HttpClient httpClient = new HttpClient(connectionManager);

        PostMethod method = new PostMethod(postUrl);

        method.addRequestHeader("Content-Type", "text/xml; charset=utf-8");
        try {
        	int statusCode = 0;
            method.setRequestEntity(new StringRequestEntity(reqXml, null, "utf-8"));

            statusCode = httpClient.executeMethod(method);
        	if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
    			throw new Exception("请求接口失败，失败码[ " + statusCode + " ]");
    		}
        	
            String resXml = method.getResponseBodyAsString();
            return resXml;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            method.releaseConnection();
        }
    }

    public HttpClientMessageSender() {
        super();

        connectionManager = new MultiThreadedHttpConnectionManager();

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(10000);
        params.setSoTimeout(30000);
        params.setDefaultMaxConnectionsPerHost(10);
        params.setMaxTotalConnections(200);

        connectionManager.setParams(params);
    }

}
