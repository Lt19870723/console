package com.cxdai.console.finance.virement.vo;

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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;


/**
 * 生成excel视图，可用excel工具打开或者保存  
 * 由ViewController的return new ModelAndView(viewExcel, model)生成  
 * 
 * @author xiaofei.li
 * @version $Id: ViewExcel.java, v 0.1 2016-4-12 下午2:42:33 Snow Exp $
 */
public class RechargeViewExcel extends AbstractExcelView {

    public Logger         logger = LoggerFactory.getLogger(RechargeViewExcel.class);

    public String         fileName;
    public Integer        orderType;
    public String[]       headData;
    public List<String[]> dataList;
    public String[]       endData;

    public RechargeViewExcel() {
    }

    /**
     * 传递的参数
     * @param orderType 交易订单类型
     * @param fileName 文件名称
     * @param dataList 文件数据
     * @param headData 文件头数据
     * @param endData  文件尾数据
     */
    public RechargeViewExcel(String fileName, String[] headData, List<String[]> dataList, String[] endData) {
        this.fileName = fileName;
        this.headData = headData;
        this.dataList = dataList;
        this.endData = endData;
    }

    /**
     * 组装excel格式数据
     * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
                                                                                                                                              throws Exception {
        if (isBlank(fileName)) {
            logger.info("传入文件名称不能为空");
            return;
        }
        if (headData.length <= 0) {
            logger.info("传入报表列数必须大于0");
            return;
        }
        if (!fileName.endsWith(".xls")) {
            fileName = fileName + ".xls";
        }
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
            HSSFRow header = sheet.createRow(0); // 第0行   
    		CellStyle style = workbook.createCellStyle();
	    		//style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
	    		//style.setFillForegroundColor(HSSFColor.RED.index);
    		
    	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直    
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平    
            /**字体begin*/
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index); 
            //背景颜色
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            
            //生成一个字体
            HSSFFont font=workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
            font.setFontHeightInPoints((short)12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
            style.setFont(font);
            // excel头部
            if(headData != null && headData.length > 0){
            	for (int i = 0; i < headData.length; i++) {
            		HSSFCell see= header.createCell(i);
            		see.setCellStyle(style);
            		see.setCellValue(headData[i]);
                }
            }
            
            // excel数据   
            int rowNum = 1;
            if(dataList != null && dataList.size() > 0){
            	for (int i = 0; i < dataList.size(); i++) {
                    String[] data = dataList.get(i);
                    int createRowNum =  rowNum + i;
                    HSSFRow row = sheet.createRow(createRowNum); //新增一行
                    for (int j = 0; j < data.length; j++) {
                        HSSFCell dataCell = row.createCell(j);
                        if(createRowNum % 6 == 0){
                        	dataCell.setCellStyle(style);
                        }
                        if(data[j]==null){
                        	 dataCell.setCellValue(""); //往一行添加数据
                        }else{
                        	if (data[j].indexOf(".") != -1) {
                                dataCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                if(isNumber(data[j])){
                                	 dataCell.setCellValue(Double.parseDouble(data[j])); //往一行添加数据
                                }else{
                                	dataCell.setCellValue(data[j]); //往一行添加数据
                                }
                            } else {
                                dataCell.setCellValue(data[j]); //往一行添加数据
                            }
                        }
                        
                    }

                }
            }
            
            // excel尾部
            if (endData != null && endData.length > 0) {
                HSSFRow end = sheet.createRow(dataList.size() + 1);
                for (int i = 0; i < endData.length; i++) {
                    // end.createCell(i).setCellValue(endData[i]);
                    HSSFCell endCell = end.createCell(i);
                    if (endData[i].indexOf(".") != -1) {//如果单元格是小数
                        endCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        if(isNumber(endData[i])){
                        	endCell.setCellValue(Double.parseDouble(endData[i])); //往一行添加数据
                       }else{
                    	   endCell.setCellValue(endData[i]); //往一行添加数据
                       }
                    } else {
                        endCell.setCellValue(endData[i]); //往一行添加数据
                    }
                }
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
