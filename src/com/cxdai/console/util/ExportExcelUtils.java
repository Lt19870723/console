package com.cxdai.console.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcelUtils {

	/**
	 * 导出excel
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月16日
	 * @param fileName：文件名
	 * @param sheetName：sheetName
	 * @param realheaders:列名
	 * @param propertiesList：对应属性名
	 * @param exportData：待导出数据
	 * @param response response
	 * @param flag：是否带序号
	 */
	public static void exportExcel(String fileName, String sheetName, ArrayList<String> realheaders, ArrayList<String> propertiesList, List<?> exportData, HttpServletResponse response, boolean flag) {
		try {

			// 设置下载头
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/msexcel;charset=utf-8");
			// 设置response的Header
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(sheetName);
			sheet.setDefaultRowHeight((short) 300);
			// 生成标题行样式
			HSSFCellStyle titlestyle = workbook.createCellStyle();
			titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 15);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			titlestyle.setFont(font);
			// 生成数据样式
			HSSFCellStyle datastyle = workbook.createCellStyle();
			datastyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			datastyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont font2 = workbook.createFont();
			font2.setFontHeightInPoints((short) 14);
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			datastyle.setFont(font2);

			HSSFRow headRow = sheet.createRow(0);
			if (flag) {
				realheaders.add(0, "序号");
				propertiesList.add(0, "xuhao");
			}
			int len = realheaders.size();
			int properLen = propertiesList.size();
			for (int i = 0; i < len; i++) {
				HSSFCell cell = headRow.createCell(i);
				cell.setCellStyle(titlestyle);
				cell.setCellValue(realheaders.get(i));
			}
			Object dataObj = null;
			for (int i = 0; i < exportData.size(); i++) {
				HSSFRow row = sheet.createRow(i + 1);
				dataObj = exportData.get(i);
				for (int j = 0; j < properLen; j++) {
					HSSFCell cell = row.createCell(j);
					String proName = propertiesList.get(j);
					cell.setCellStyle(datastyle);
					if ("xuhao".equals(proName)) {
						cell.setCellValue(i + 1);
					} else {
						Object val = dataObj.getClass().getMethod("get" + proName.substring(0, 1).toUpperCase() + proName.substring(1)).invoke(dataObj, new Object[0]);
						if (val == null) {
							cell.setCellValue("");
						} else {
							cell.setCellValue(val.toString());
						}
					}
				}
			}
			for (int i = 0; i < len; i++) {
				sheet.autoSizeColumn(i);
			}
			workbook.write(response.getOutputStream());
			response.getOutputStream().close();
//			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}