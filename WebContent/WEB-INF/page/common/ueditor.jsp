<%@page import="com.cxdai.console.util.PropertiesUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var ueditor_image_url = '<%=PropertiesUtil.getValue("www_cms_upload")%>';
</script>
<link href="${path}/js/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${path}/js/ueditor/umeditor.config.js"></script>
<script type="text/javascript" src="${path}/js/ueditor/umeditor.js"></script>
<script type="text/javascript" src="${path}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">window._HOME_URL = '${path}/';</script>
<script type="text/javascript">
<c:if test="${empty isNoteDetail}">
$(document).ready(function (){
	var style= $(".edui-body-container").attr("style"); 
	$(".edui-body-container").attr("style",style+"height:120px;width:98%");
	//解决最大化问题 
	var  style1= $(".edui-container").attr("style");
	$(".edui-container").attr("style",style1+"width:98%");
})
</c:if>
</script>