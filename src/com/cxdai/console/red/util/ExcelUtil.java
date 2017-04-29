package com.cxdai.console.red.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

	public static final String EMPTY = "";
	public static final String POINT = ".";
	public static final String LIB_PATH = "lib";
	public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
	public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
	public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
	public static final String PROCESSING = "Processing...";

	public List<?> readExcel(String path) throws IOException {
		return readXls(path);
	}

	public List<?> readXls(String path) throws IOException {
		System.out.println(this.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// Student student = null;
		List<?> list = new ArrayList();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null && hssfRow.getCell(0) != null) {
					// student = new Student();
					HSSFCell no = hssfRow.getCell(0);
					HSSFCell name = hssfRow.getCell(1);
					HSSFCell age = hssfRow.getCell(2);
					HSSFCell score = hssfRow.getCell(3);
					System.out.println(rowNum);
					System.out.println(getValue(no));
					System.out.println(getValue(name));
					System.out.println(getValue(age));
					System.out.println(getValue(score));
					// student.setNo(getValue(no));
					// student.setName(getValue(name));
					// student.setAge(getValue(age));
					// student.setScore(Float.valueOf(getValue(score)));
					// list.add(student);
				}
			}
		}
		return list;
	}

	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue()).trim();
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue()).trim();
		} else {
			return String.valueOf(hssfCell.getStringCellValue()).trim();
		}
	}

	public static String getStringValue(HSSFCell c) {
		if (c == null) {
			return "";
		} else {
			return c.getStringCellValue().trim();
		}
	}
}
