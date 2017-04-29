<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>多图文详情</title>
</head>
<body>
<div >
	<div class="left1_input_tst" style="margin-bottom:210px;">
		<font class="red">&nbsp;图片建议像素为360*200</font>
		<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>图片：</span>
		<div class="input_IDs">
			<img id="oneimageId" style="width:360px;height:200px;"
				src="${consoleWxImagePath}${wxArticles.picurl}"></img>
		</div>
	</div>
		<div class="left1_input_tst"  style="margin-bottom:5px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>标题：</span>
			<font style="font:16px/150% Arial,Helvetica,sans-serif">${wxArticles.title}</font>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>超链接：</span>
			<a href="${wxArticles.url}" target="_blank"><font style="font:16px/150% Arial,Helvetica,sans-serif">${wxArticles.url}</font></a>
		</div>
		<div style="clear:both;"></div>
		<font class="red">小图文图片建议像素为200*200</font>
		<table id="moreTable" align="center" style="width:100%;">
			<tr class="tr_head">
				<th>排序</th>
				<th>标题</th>
				<th>图片</th>
				<th>超链接</th>
			</tr>
			<c:forEach var="f" items="${newsList}" varStatus="status" >
			<tr class='tr_${status.index%2}' id="tr${status.index}" align="center" >
				<td width="10%">
					${f.sort}
				</td>
				<td width="30%">
					${f.title}
				</td>
				<td width="10%">
					<img id="mainImg_${status.index}" src="${consoleWxImagePath}${f.picurl}" style="width:50px;height:50px;padding: 0px;margin: 0px;" />
				</td>
				<td>
					<a href="${f.url}" target="_blank">${f.url}</a>
				</td>
			</tr>
			</c:forEach>
		</table>
</div>
</html>