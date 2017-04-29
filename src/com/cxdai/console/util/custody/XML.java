package com.cxdai.console.util.custody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;


import com.org.jdom.Document;
import com.org.jdom.Element;
import com.org.jdom.input.SAXBuilder;
import com.org.jdom.output.Format;
import com.org.jdom.output.XMLOutputter;

public class XML {

	private Element root = null;

	private XML() {
		super();
	}

	public XML(Document doc) {
		super();
		root = doc.getRootElement();
	}

	public static XML newDocument(String rootName) {
		XML xml = new XML();
		Document doc = new Document();
		xml.root = new Element(rootName);
		doc.setRootElement(xml.root);
		return xml;
	}

	public Element addChild(String name) {
		return addChild(root, name);
		// Element el = new Element(name, value);
		// root.addContent(el);
	}

	public Element addChild(Element elm, String name) {
		Element el = new Element(name);
		elm.addContent(el);
		return el;
	}

	public Element addChild(Element elm, String name, String text) {
		if (text == null || name == null)
			return null;
		Element child = new Element(name);
		child.setText(text);
		elm.addContent(child);
		return child;
	}

	public Element getChild(Element elm, String name) {
		int idx = name.indexOf(".");
		// String tmpName = name;
		Element tmp = elm;
		while (idx != -1) {
			String tmpName = name.substring(0, idx);
			tmp = tmp.getChild(tmpName);
			name = name.substring(idx + 1, name.length());
			idx = name.indexOf(".");
		}
		return tmp.getChild(name);

	}

	public Element getChild(String name) {
		return getChild(root, name);
	}

	public static void setElementText(Element elm, String value) {
		elm.setText(value);
	}

	public static void setElementValueAt(Element elm, String name, String value) {
		elm.setAttribute(name, value);
	}

	static XMLOutputter defOutp = null;
	static {
		defOutp = new XMLOutputter();
		Format format = Format.getRawFormat();// .getCompactFormat();//
		// .getPrettyFormat();
		format.setEncoding("UTF-8");
		defOutp.setFormat(format);
	}

	public Document getDoc() {
		return root.getDocument();
	}

	public String toString() {
		return getXmlStr();
	}

	public String getXmlStr() {
		XMLOutputter outp = defOutp;
		try {
			return outp.outputString(root.getDocument());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getXmlStr(Element element) {
		XMLOutputter outp = defOutp;
		try {
			return outp.outputString(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getXmlStr(String encoding) {
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getCompactFormat();// .getPrettyFormat();
		format.setEncoding(encoding);
		outp.setFormat(format);
		try {
			return (outp.outputString(root.getDocument()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public byte[] getXmlBytes(String encoding) {
		XMLOutputter outp = new XMLOutputter();
		Format format = Format.getCompactFormat();// .getPrettyFormat();
		format.setEncoding(encoding);
		outp.setFormat(format);
		try {
			ByteArrayOutputStream sw = new ByteArrayOutputStream();
			System.out.println("["+outp.outputString(root.getDocument())+"]");
			outp.output(root.getDocument(), sw);
			return sw.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public XML(String xmlPath) {
		try {
			root = LoadFromFile(xmlPath).getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据XML 字符�?建立JDom对象
	 * 
	 * @param xmlString
	 *            XML格式的字符串
	 * @return 返回建立的JDom对象，建立不成功返回null �?
	 */
	public static XML readFrom(String xmlString) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder
					.build(new StringReader(xmlString));
			return new XML(anotherDocument);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Document readFrom(byte[] b) {
		try {
			InputStream ip = new ByteArrayInputStream(b);
			SAXBuilder builder = new SAXBuilder();
			// 解析xml，生成Document
			Document doc = builder.build(ip);
			// 获得根节�?
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 读取XML文件�?��信息
	 */
	public static Document LoadFromFile(String path) throws Exception {
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(path);
			return LoadFromStream(fi);
		} catch (Exception e) {
			System.out.println("文件加载错误: " + e.toString());
		} finally {
			try {
				fi.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 读取XML文件�?��信息
	 */
	public static Document LoadFromStream(InputStream resourceAsStream)
			throws Exception {
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(resourceAsStream);
			return doc;
		} catch (Exception e) {
			System.out.println("流读取错�? " + e.toString());
		} finally {
		}
		return null;
	}
	
	/*
	 * 读取XML文件引用本地dtd
	 */
	

	public static void main(String[] args) 
	throws Exception{
		XML xrw = XML.newDocument("ThreeD");
		Element elm = xrw.addChild("Name");
		xrw.addChild(elm, "Min");
		String info = xrw.getXmlStr();
		
		xrw = readFrom(info);
		xrw.addChild("Gender");

		info = xrw.getXmlStr();
		System.out.println(info);
        SAXBuilder builder = new SAXBuilder();    
        //builder.setValidation(true);
		Document doc = builder.build(new StringReader(info));
		Element rootElement = doc.getRootElement();
		Element childElement = rootElement.getChild("ThreeD");
		System.out.println(rootElement.toString());
		System.out.println(childElement);
	}

	/**
	 * 获得自指定节点开始的某子值，如a.b.c
	 * 
	 * @param el
	 * @param name
	 * @return
	 */
	public String getChildValue(Element el, String name) {
		return getChild(el, name).getValue().trim();

	}

	public String getChildValue(String name) {
		return getChildValue(root, name);
	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}
}