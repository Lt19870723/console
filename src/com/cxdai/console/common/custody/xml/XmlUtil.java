/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title XmlUtil.java
 * @package com.cxdai.console.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月18日
 */
package com.cxdai.console.common.custody.xml;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.*;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cxdai.console.security.custody.KeyReader;
import com.cxdai.console.security.custody.SignManagerImpl;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.custody.XML;
import com.cxdai.console.util.custody.https.DataSignDP;
import com.cxdai.console.util.custody.https.HttpClientMessageSender;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title XmlUtil.java
 * @package com.cxdai.console.common.custody.xml 
 * @author tanked
 * @version 0.1 2016年5月18日
 */

public class XmlUtil {

	private static SignManagerImpl signManager =new SignManagerImpl();
	private static DocumentBuilderFactory docBuilderFactory =DocumentBuilderFactory.newInstance();
	private static KeyReader clientCer = new KeyReader();
	private static HttpClientMessageSender messageSender = new HttpClientMessageSender();

	/**
	 *
	 * <p>
	 * Description:判断报文是否是ERROR报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月18日
	 * @param xmlStr
	 * @return
	 * boolean
	 */
	public static boolean checkXml(String xmlStr){
		  if(xmlStr.indexOf("</Error>")!=-1 || xmlStr.indexOf("Error 404")!=-1){
		   return true;
		  }else{
			  return false;
		  }
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:校验响应报文签名<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @param rep
	 * @return
	 * @throws Exception
	 * boolean
	 */
	public static boolean checkYq(String rep)  throws Exception{
		 
			 XML repxml=XML.readFrom(rep);
			 SignManagerImpl si = new SignManagerImpl();
			 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	         docBuilderFactory.setNamespaceAware(true);
			 DocumentBuilder docBuilder;
			 docBuilder = docBuilderFactory.newDocumentBuilder();
			 Document repdoc=docBuilder.parse(new InputSource(new StringReader(repxml.getXmlStr())));
			 String czbank = PropertiesUtil.getValue("custody_czbank");
		     String fileUrl=XmlUtil.class.getResource("/").getPath()+czbank;
			 PublicKey pubKey = DataSignDP.readCertPublicKey(fileUrl);
		     boolean istrue=si.check(repdoc,pubKey);
		     return istrue;
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:发送报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @param reqMessage
	 * @param type
	 * @return
	 * String
	 */
	public static String send(String reqMessage,String type) throws Exception{
		
		 PrivateKey privateKey = null;
   		 KeyReader clientCert = new KeyReader();
   		 String phjf = PropertiesUtil.getValue("custody_phjf");
   		 String clientCertFile=XmlUtil.class.getResource("/").getPath()+phjf;
   		 String password = PropertiesUtil.getValue("clientCert_password");
   		privateKey=clientCert.readPrivateKeyfromPKCS12StoredFile(clientCertFile, password);
   		  
   		SignManagerImpl si = new SignManagerImpl();
		XML xml=XML.readFrom(reqMessage);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
		reqMessage =si.sign(doc, privateKey, type);
		System.out.println("请求报文=["+reqMessage+"]");	    
		HttpClientMessageSender messageSender = new HttpClientMessageSender();
		String url = PropertiesUtil.getValue("custody_req_url");
        String rep= messageSender.send(reqMessage, url);
        System.out.println("响应报文=["+rep+"]"); 
		
		return rep;
		
	}


	public static String sign(String reqMessage,String type) throws Exception{
		PrivateKey privateKey = XmlUtil.getPrivateKey();
		String pfx = PropertiesUtil.getValue("custody_phjf");
		String clientCertFile=XmlUtil.class.getResource("/").getPath()+pfx;
		String password = PropertiesUtil.getValue("clientCert_password");
		privateKey=clientCer.readPrivateKeyfromPKCS12StoredFile(clientCertFile, password);
		XML xml=XML.readFrom(reqMessage);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc=docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
		reqMessage =signManager.sign(doc, privateKey, type);
		System.out.println("请求报文=["+reqMessage+"]");
		return reqMessage;
	}

	public static String send(String reqMessage) throws Exception{
		String url = PropertiesUtil.getValue("custody_req_url");
		String responseXML= messageSender.send(reqMessage, url);
		System.out.println("响应报文=["+responseXML+"]");
        return responseXML;
	}




	public static Map<String, Object> toBiz(String reqMessage, String match) throws Exception{
		Map<String,Object> bizMap =new <String,Object>HashMap();

		SAXReader reader = getReader();
		org.dom4j.Document document = null;
		document = DocumentHelper.parseText(reqMessage);
		Element rootElement=  document.getRootElement();

		Element et = (Element) rootElement.selectSingleNode("//"+match);
		Iterator<Element> it = et.elementIterator();

		while (it.hasNext()) {
				// 获取某个子节点对象
				Element e1 = it.next();
			if(e1.getName().equals("List")){
				continue;
			}
				// 对子节点进行遍历
				System.out.println(e1.getText());
				bizMap.put(e1.getName(),e1.getText());
			}

		return bizMap;
	}

	public static List<Map> toDetail(String reqMessage, String match) throws Exception{

        List<Map> detailList =new ArrayList<Map>();

		SAXReader reader = getReader();
		org.dom4j.Document document = null;
		document = DocumentHelper.parseText(reqMessage);
		Element rootElement=  document.getRootElement();

		List<Element> nList= rootElement.selectNodes("//"+match+"/List/Record");

		for ( Element e :nList) {
			System.out.println(e.getText());
			Iterator<Element> it = e.elementIterator();
			Map<String,Object> bizMap =new <String,Object>HashMap();
			while (it.hasNext()) {
				// 获取某个子节点对象
				Element e1 = it.next();
				// 对子节点进行遍历
				System.out.println(e1.getText());
				bizMap.put(e1.getName(),e1.getText());
			}
			detailList.add(bizMap);

		}
		return detailList;
	}


	public static Map<String, Object> toError(String reqMessage) throws Exception{
		Map<String,Object> bizMap =new <String,Object>HashMap();

		SAXReader reader = getReader();
		org.dom4j.Document document = null;
		document = DocumentHelper.parseText(reqMessage);
		Element rootElement=  document.getRootElement();

		Element et = (Element) rootElement.selectSingleNode("//Error");
		Iterator<Element> it = et.elementIterator();

		while (it.hasNext()) {
			// 获取某个子节点对象
			Element e1 = it.next();
			if(e1.getName().equals("List")){
				continue;
			}
			// 对子节点进行遍历
			System.out.println(e1.getText());
			bizMap.put(e1.getName(),e1.getText());
		}

		return bizMap;
	}

	private  static PrivateKey getPrivateKey() throws Exception{
		PrivateKey privateKey = null;
		KeyReader clientCert = new KeyReader();
		String phjf = PropertiesUtil.getValue("custody_phjf");
		String clientCertFile=XmlUtil.class.getResource("/").getPath()+phjf;
		String password = PropertiesUtil.getValue("clientCert_password");
		privateKey=clientCert.readPrivateKeyfromPKCS12StoredFile(clientCertFile, password);
		return  privateKey;
	}


	public static SAXReader  getReader(){
		SAXReader reader = new SAXReader();
		return  reader;
	}

    public static Document getDocument(String xmlstr) throws IOException, SAXException, ParserConfigurationException {
        XML xml = XML.readFrom(xmlstr);
        if (xml != null) {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(true);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            return docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
        }
        return null;
    }

    public static String getFirstElementValue(Document doc, String name) {
        NodeList nodeList = doc.getElementsByTagName(name);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

	
}
