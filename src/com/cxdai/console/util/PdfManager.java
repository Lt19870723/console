package com.cxdai.console.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


public class PdfManager {

	/**
	 * 
	 * <p>
	 * Description:根据html转成pdf<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月30日
	 * @param htmlPath
	 * @param pdfPath
	 * @throws IOException
	 * @throws DocumentException void
	 */
	public static void createPdf(String htmlPath, String pdfPath,String headerImagePath) throws IOException, DocumentException {
		
		File file = new File(pdfPath);
		file.getParentFile().mkdirs();
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));		
		document.open();
		//添加页眉
		addPdfHeader(document,writer,headerImagePath);
		System.out.println(htmlPath);		
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath), Charset.forName("UTF-8"));
		document.close();
	}

	/**
	 * 给pdf文件添加水印（借款标协议下载专用）
	 * 
	 * @param InPdfFile 要加水印的原pdf文件路径
	 * @param outPdfFile 加了水印后要输出的路径
	 * @param markImagePath 水印图片路径
	 * @param targetPageSize 目标页数（即在哪页加水印）
	 * @throws Exception
	 */
	public static void addPdfMark(String InPdfFile, String outPdfFile, String markImagePath, int targetPageSize) throws Exception {
		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
		Image img = Image.getInstance(markImagePath);// 插入水印
		img.setAbsolutePosition(350, 100);
		PdfContentByte under = stamp.getUnderContent(targetPageSize);
		under.addImage(img);
		stamp.close();// 关闭
		File tempfile = new File(InPdfFile);
		if (tempfile.exists()) {
			tempfile.delete();
		}
	}
	
	/**
	 * 给PDF文件添加页眉（借款标协议下载专用）
	 * @author WangQianJin
	 * @param InPdfFile 要加页眉的原PDF文件路径
	 * @param outPdfFile 加了页眉后要输出的路径
	 * @param markImagePath 页眉图片路径
	 * @param targetPageSize 目标页数（即在哪页加页眉）
	 * @throws Exception
	 */
	public static void addPdfHeader(Document document,PdfWriter writer,String headerImagePath) {		
		HeaderFooter header=new HeaderFooter(headerImagePath);
        writer.setPageEvent(header);
	}
}