<%@page import="com.cxdai.console.util.PropertiesUtil"%>
<%@page import="com.cxdai.console.util.Uploader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	Uploader up = new Uploader(request);
	up.setSavePath(PropertiesUtil.getValue("cms_upload_path"));
	up.setAllowFiles(Uploader.default_img_allowFiles);
	up.setMaxSize(2048); //单位KB
	up.upload();

	String callback = request.getParameter("callback");
	
// 	System.out.println("up.getUrl()----------->"+up.getUrl());

	String url = PropertiesUtil.getValue("www_cms_upload")+PropertiesUtil.getValue("www_ss_upload")+up.getUrl().replace(PropertiesUtil.getValue("cms_upload_path"), "");
	
	
	String result = "{\"name\":\"" + up.getFileName()
					+ "\",\"originalName\":\"" + up.getOriginalName()
					+ "\",\"size\":\"" + up.getSize()
					+ "\",\"state\":\"" + up.getState()
					+ "\",\"type\":\"" + up.getType()
					+ "\",\"url\":\"" + url + "\"}";

	result = result.replaceAll("\\\\", "\\\\");

// 	System.out.println("result----------->"+result);
	
	if (callback == null) {
		response.getWriter().print(result);
	} else {
		response.getWriter().print("<script>" + callback + "(" + result + ")</script>");
	}
%>
