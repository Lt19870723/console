package com.cxdai.console.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class BaseFreemarkerService {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * <p>
	 * Description通过模板产生邮件正文<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年7月1日
	 * @param templateName
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public String generateEmailContentByFreeMarker(String templateName, Map<String, Object> map) throws Exception {
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template t = configuration.getTemplate(templateName);
		return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
	}
    
	/**
	 * <p>
	 * Description:通过模版生成html文件<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年7月1日
	 * @param templateName
	 * @param map
	 * @param targetPath
	 * @throws Exception
	 */
	public void createHtml(String templateName, Map<String, Object> map, String targetPath) throws Exception {
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		configuration.setDefaultEncoding("UTF-8");
		Template t = null;
		try {
			t = configuration.getTemplate(templateName, "UTF-8");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// File outFile = new File("E:/outFilessa" + Math.random() * 10000 + ".html");
		File outFile = new File(targetPath);
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			t.process(map, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
