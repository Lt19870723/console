package com.cxdai.console.finance.virement.vo;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.cxdai.console.finance.virement.entity.FlowType;
import com.cxdai.console.util.DateUtils;


/**
 * 生成excel视图，可用excel工具打开或者保存  
 * 由ViewController的return new ModelAndView(viewExcel, model)生成  
 * 
 * @author xiaofei.li
 * @version $Id: ViewExcel.java, v 0.1 2016-4-12 下午2:42:33 Snow Exp $
 */
public class ViewExcel extends AbstractExcelView {

    public Logger         logger = LoggerFactory.getLogger(ViewExcel.class);

    public String         fileName;
    public Integer        orderType;
    public List<AccountFlowResponse> dataList;
    public ViewExcel() {
    }

    /**
     * 传递的参数
     * @param orderType 交易订单类型
     * @param fileName 文件名称
     * @param dataList 文件数据
     * @param headData 文件头数据
     * @param endData  文件尾数据
     */
    public ViewExcel(String fileName, List<AccountFlowResponse> dataList) {
        this.fileName = fileName;
        this.dataList = dataList;
    }

    /**
     * 组装excel格式数据
     * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("deprecation")
	public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
                                                                                                                                              throws Exception {
        if (isBlank(fileName)) {
            logger.info("传入文件名称不能为空");
            return;
        }
        if (!fileName.endsWith(".xls")) {
            fileName = fileName + ".xls";
        }
        
    	CellStyle style = workbook.createCellStyle();
		//style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
		//style.setFillForegroundColor(HSSFColor.RED.index);
		
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直    
	  //  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平    
	    /**字体begin*/
	 //   style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); 
	    //背景颜色
	//    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  //  style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  //  style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  //  style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  //  style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  //  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    
	    
	  
	    //生成一个字体
        HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short)13);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
        style.setFont(font);
        try {
            // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开   
            response.setContentType("APPLICATION/OCTET-STREAM");
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) //firefox浏览器
                response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) //IE浏览器
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            else if(request.getHeader("User-Agent").toLowerCase().indexOf("chrome") > 0)
           	 response.setHeader("Content-Disposition", "filename="+ new String(fileName.getBytes("gb2312"), "iso8859-1"));
            // 产生Excel表头   
            // HSSFSheet sheet = workbook.createSheet(createSheet(orderType));
            HSSFSheet sheet = workbook.createSheet(fileName);
            int ic = 0;
    		int ex = 0;
    		for(AccountFlowResponse record : dataList){
    			if( FlowType.ICOME.getCode() == record.getMoneyType()){
    				ic++;
    			}
    			if( FlowType.EXPURTE.getCode() == record.getMoneyType()){
    				ex++;
    			}
    		}
    		int size = dataList.size();
            // excel头部
            if(dataList != null && dataList.size() > 0){
            		//四个参数分别是：起始行，结束行  ,起始列，结束列
            		sheet.addMergedRegion(new CellRangeAddress(0,dataList.size()-1,0,1)); //日期
            		sheet.addMergedRegion(new CellRangeAddress(0,(ic > 0 ? ic-1 : 0),2,3));//收入 
            		for(int i=0;i<ic;i++){
            			sheet.addMergedRegion(new CellRangeAddress(i,i,4,5)); //类型名称	
            			sheet.addMergedRegion(new CellRangeAddress(i,i,6,7));//金额
            		}
            		sheet.addMergedRegion(new CellRangeAddress(ic,ic,2,5));//收入汇总
            		sheet.addMergedRegion(new CellRangeAddress(ic,ic,6,7));//收入汇总
            		sheet.addMergedRegion(new CellRangeAddress(ic+1,(ex>0 ? ic+ex : ic+1),2,3));//支出
            		for(int i=0;i<ex;i++){
            			sheet.addMergedRegion(new CellRangeAddress(ic+1+i,ic+1+i,4,5));//支出类型
            			sheet.addMergedRegion(new CellRangeAddress(ic+1+i,ic+1+i,6,7));//支出类型
            		}
            		sheet.addMergedRegion(new CellRangeAddress(ic+ex+1,ic+ex+1,2,5));//收入汇总
            		sheet.addMergedRegion(new CellRangeAddress(ic+ex+1,ic+ex+1,6,7));//收入汇总


            		sheet.addMergedRegion(new CellRangeAddress(size,size,0,5));//收入汇总
            		sheet.addMergedRegion(new CellRangeAddress(size,size,6,7));//收入汇总

            		
            		for(int i=0;i<ic;i++){
            			 HSSFRow header = sheet.createRow(i); // 第0行   
            			 if( i == 0){
            				 HSSFCell cell1 =  header.createCell(0);
            				 cell1.setCellStyle(style);
            				 cell1.setCellValue(DateUtils.format(dataList.get(i).getEndTime(),DateUtils.YMD_DASH)); 
            				 HSSFCell cell2 =  header.createCell(2);
            				 cell2.setCellStyle(style);
            				 cell2.setCellValue("收入");
            			 }
            			 HSSFCell cell3 = header.createCell(4);
            			 		cell3.setCellStyle(style);
            					cell3.setCellValue(dataList.get(i).getTypeName());
            			HSSFCell cell4 = header.createCell(6);
            					cell4.setCellStyle(style);
            					cell4.setCellValue(dataList.get(i).getMoney()+"");
            			 	
            		}
            		
            		
            		 HSSFRow header1 = sheet.createRow(ic); // 第0行   
            		 if(size > 2 && ic==0){
             			HSSFCell cell1 =  header1.createCell(0);
             			cell1.setCellStyle(style);
             			cell1.setCellValue(DateUtils.format(dataList.get(0).getEndTime(),DateUtils.YMD_DASH)); 
             			}
            		 HSSFCell cell5 = header1.createCell(2);
            		 cell5.setCellStyle(style);
            		 cell5.setCellValue("收入汇总");
            		 HSSFCell cell6 = header1.createCell(6);
            		 cell6.setCellStyle(style);
            		 cell6.setCellValue(dataList.get(size-2).getMoney()+"");
            		 
            		
            		for(int i=ic+1;i<ic+ex+1;i++){
           			 HSSFRow headerEx = sheet.createRow(i); // 第0行   
           			 if( i == ic+1){
           				 HSSFCell cell7 = headerEx.createCell(2);
           						cell7.setCellStyle(style);
           						cell7.setCellValue("支出");
           			 }
           			 HSSFCell cell8 = headerEx.createCell(4);
						cell8.setCellStyle(style);
						cell8.setCellValue(dataList.get(i-1).getTypeName());
						 HSSFCell cell9 =headerEx.createCell(6);
						 cell9.setCellStyle(style);
						 cell9.setCellValue("-"+dataList.get(i-1).getMoney());
           			 	
           		}
            		
            		 HSSFRow header2 = sheet.createRow(ic+ex+1); // 第0行   
            		 HSSFCell cell10 = header2.createCell(2);
            		 	cell10.setCellStyle(style);
            		 	cell10.setCellValue("支出汇总");
            		 HSSFCell cell11 = header2.createCell(6);
            		 	cell11.setCellStyle(style);
            		 	cell11.setCellValue(dataList.get(size-1).getMoney().compareTo(BigDecimal.ZERO)== 0 ? "0" : "-"+dataList.get(size-1).getMoney());
            		 
            		 HSSFRow header3 = sheet.createRow(ic+ex+2); // 第0行   
            		 HSSFCell cell12 = header3.createCell(0);
            		 cell12.setCellStyle(style);
            		 cell12.setCellValue(DateUtils.format(dataList.get(0).getEndTime(),DateUtils.YMD_DASH)+"汇总:");
            		 HSSFCell cell13 =  header3.createCell(6);
            		 cell13.setCellStyle(style);
            		 cell13.setCellValue(dataList.get(size-2).getMoney().subtract(dataList.get(size-1).getMoney())+"");
            	
            }
            

            
        } catch (Exception e) {
            logger.error("报表数据解析异常", e);
        }
    }

    public static boolean isBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
    public static boolean isNumber(String str) {
        try{
        	Double.parseDouble(str);
        	return true;
        }catch(Exception e){
        	return false;
        }
      }
    
}
