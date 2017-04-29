package com.cxdai.console.borrow.manage.vo;

import com.cxdai.console.common.custody.*;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.security.custody.KeyReader;
import com.cxdai.console.security.custody.SignManagerImpl;
import com.cxdai.console.util.custody.XML;
import com.cxdai.console.util.custody.https.HttpClientMessageSender;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.*;

public class Test {

	
	public static void main(String[] args) throws Exception {
		
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 
		 Finance finance =new Finance();
		 
		 Message message =new Message();
		 message.setId("1650603094424349361");
		 
		 RepayBiz custodyBiz =new RepayBiz();
		 custodyBiz.setVersion("1.0.0");
		 custodyBiz.setInstId("GCJR");
		 custodyBiz.setCertId("0012");
		 custodyBiz.setDate("20160518 14:10:31");
		 custodyBiz.setProjectId("GCJR0000000001");
		 custodyBiz.setProjectName("众筹成功标");
		 custodyBiz.setRealname("loubinbin");
		 custodyBiz.setCount(1);
		 custodyBiz.setComplete("");
		 custodyBiz.setWitnessFee(0);
		 custodyBiz.setFlag("");
		 custodyBiz.setExtension("本息还款");
		 
		 CustodyList custodyList =new CustodyList();
         RepayDetail repayDetail =new RepayDetail();
         repayDetail.setP2PserialNo("P2P20150728003123123146754568788");
         repayDetail.setInvestmentSerialNo("TVS31231231467545");
         repayDetail.setType(0);
         repayDetail.setFlag("");
         repayDetail.setBankBranchNo("");
         repayDetail.setAccountBankNumber("");
         repayDetail.setAccountName("");
         repayDetail.setRepaymentAmount(23340000);
         repayDetail.setFee(0);
         repayDetail.setExtraInterest(0);
         repayDetail.setCurrency("156");
         repayDetail.setPayType("1");

        
        List<Record> recordList=new ArrayList<Record>();
        recordList.add(repayDetail);
        custodyBiz.setList(custodyList);
        xstream.autodetectAnnotations(true);
        xstream.aliasField("FRReq", Message.class, "Mode");
        xstream.addDefaultImplementation(RepayBiz.class,CustodyBiz.class);
        custodyList.setRecordList(recordList);
        
        finance.setMessage(message);
        message.setMode(custodyBiz);
        finance.setMessage(message);
        System.out.println(xstream.toXML(finance));
        String reqMessage=xstream.toXML(finance);
        
	     PrivateKey privateKey = null;
  		 KeyReader clientCert = new KeyReader();
  		 String clientCertFile="D:\\eclipse-jee-kepler\\workspace\\cxdai_console\\resources\\custody\\phjf.pfx";
  		 
  		  privateKey=clientCert.readPrivateKeyfromPKCS12StoredFile(clientCertFile, "a121212");
  		  
  		SignManagerImpl si = new SignManagerImpl();
		XML xml=XML.readFrom(reqMessage);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
       docBuilderFactory.setNamespaceAware(true);
       DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
       Document doc=docBuilder.parse(new InputSource(new StringReader(xml.getXmlStr())));
		reqMessage =si.sign(doc, privateKey, "FRReq");
		System.out.println("请求报文=["+reqMessage+"]");	    
		HttpClientMessageSender messageSender = new HttpClientMessageSender();
       String rep= messageSender.send(reqMessage, "http://60.191.15.92:9081/P2PDEPOSIT/P2PReq.srv");
       System.out.println("响应报文=["+rep+"]");


		SAXReader reader = new SAXReader();
        org.dom4j.Document document = null;
        document = DocumentHelper.parseText(reqMessage);
        try{

            document.accept(new VisitorSupport(){

                public void visit(Element element){
                   // System.out.println(element.getName());
                   // System.out.println(element.getText());
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }

        Element nd=  document.getRootElement();

        Map<String, Object> map = XmlUtil.toBiz(rep,"FRRes");
        List<Map>  list = XmlUtil.toDetail(rep,"FRRes");
        System.out.println("aaa");
      //  System.out.println(nd.getText());
        //List<Node>  nodelist = nd.selectNodes("Recode");

//        List<Element> nList= nd.selectNodes("//List/*");
//        Map<String,Object> map =new HashMap<String,Object>();
//        for ( Element e :nList) {
//
//            System.out.println(e.getText());
//            Iterator<Element> it = e.elementIterator();
//            while (it.hasNext()) {
//                // 获取某个子节点对象
//                Element e1 = it.next();
//                // 对子节点进行遍历
//                System.out.println(e1.getText());
//                map.put(e1.getName(),e1.getText());
//            }
//
//            if(e.getName().equals("List")){
//
//
//            }
//        }

//        RepayDetail rd2=new RepayDetail();
//        transMap2Bean(map,rd2);
//        System.out.println(rd2.toString());

//        for ( Element e :elementList) {
//
//            System.out.println(e.getName());
//        }
        // listNodes(nd);
    }


//    public static  void listNodes(Element node) {
//        System.out.println("当前节点的名称：：" + node.getName());
//        // 获取当前节点的所有属性节点
//        List<Attribute> list = node.attributes();
//        // 遍历属性节点
//        for (Attribute attr : list) {
//            System.out.println(attr.getText() + "-----" + attr.getName()
//                    + "---" + attr.getValue());
//        }
//
//        if (!(node.getTextTrim().equals(""))) {
//            System.out.println("文本内容：：：：" + node.getText());
//        }
//
//        // 当前节点下面子节点迭代器
//        Iterator<Element> it = node.elementIterator();
//        // 遍历
//        while (it.hasNext()) {
//            // 获取某个子节点对象
//            Element e = it.next();
//            // 对子节点进行遍历
//            listNodes(e);
//        }
//    }


    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public static void transMap2Bean(Map<String, Object> map, Object obj) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }

            }

        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }

        return;

    }

}
