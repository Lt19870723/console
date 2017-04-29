package com.cxdai.console.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author Administrator
 *
 */
public class WordAndExcelGenerator {

	private static Configuration configuration = null;
	private static Map<String, Template> allTemplates = null;

	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassForTemplateLoading(WordAndExcelGenerator.class,
				"/com/cxdai/console/finance/virement/template");
		allTemplates = new HashMap<>(); // Java 7 钻石语法
		try {
			allTemplates.put("finance", configuration.getTemplate("finance.ftl"));
			allTemplates.put("journal", configuration.getTemplate("journal.ftl"));
			allTemplates.put("withdrawalAnalysis", configuration.getTemplate("withdrawalAnalysis.ftl"));
			allTemplates.put("rechargeAnalysis", configuration.getTemplate("rechargeAnalysis.ftl"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建文档或者是Excel
	 * 
	 * @param dataMap
	 * @param type
	 * @param suffix
	 * @return
	 */
	public static File createDocOrExcel(String fileName, Map<?, ?> dataMap, String type) {

		Writer w = null;
		File f = new File(fileName);
		Template t = allTemplates.get(type);
		try {
			// 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
			w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return f;
	}

	/**
	 * 创建文档或者是Excel
	 * 
	 * @param dataMap
	 * @param type
	 * @param suffix
	 * @return
	 */
	public static File createDocOrExcel(String fileName, List<Map<String, Object>> dataMap, String type) {

		Writer w = null;
		File f = new File(fileName);
		Template t = allTemplates.get(type);
		try {
			// 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
			w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return f;
	}
}
