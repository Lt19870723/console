package com.cxdai.console.util;

import java.io.IOException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * @author WangQianJin
 * @title 利用PdfPageEventHelper来完成页眉页脚的设置工作
 * @date 2015年8月25日
 */
public class HeaderFooter extends PdfPageEventHelper {
	
	private String headerImagePath;	
	
	public String getHeaderImagePath() {
		return headerImagePath;
	}

	public void setHeaderImagePath(String headerImagePath) {
		this.headerImagePath = headerImagePath;
	}

	public HeaderFooter(String headerImagePath){
		this.headerImagePath=headerImagePath;
	}

	/**
	 * 设置页眉和页脚
	 */
	public void onEndPage (PdfWriter writer, Document document) {
		/*设置页眉*/
		Rectangle rect = new Rectangle(36, 54, 559, 788);
        rect.setBorderColor(BaseColor.BLACK);       
        Phrase phrase=new Phrase();
        PdfContentByte directContent = writer.getDirectContent();
		try {			
			Image headerImage = Image.getInstance(headerImagePath);
			headerImage.scaleToFit(590f, 50f);			
			headerImage.setAbsolutePosition(3, 800);
			document.add(headerImage);
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		//隔页不同的显示
        switch(writer.getPageNumber() % 2) {
        case 0:
            ColumnText.showTextAligned(directContent,Element.ALIGN_RIGHT, phrase,rect.getLeft(), rect.getTop(), 0);                               
            break;
        case 1:
            ColumnText.showTextAligned(directContent,Element.ALIGN_LEFT, phrase,rect.getLeft(), rect.getTop(), 0);                   
            break;
        }
        /*设置页脚*/
//        ColumnText.showTextAligned(writer.getDirectContent(),
//                Element.ALIGN_CENTER, new Phrase(String.format("page %d", writer.getPageNumber())),
//                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);

    }

}
