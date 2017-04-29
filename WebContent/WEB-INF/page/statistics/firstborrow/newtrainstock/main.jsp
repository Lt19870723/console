<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%;">
	<div style="margin-left:20px;line-height:40px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">直通车存量总额：</span>
			<div style="font-size:14px;">
				<label  name="changeFormat"  >${informationStatisticsVO.stockTotalAmount}</label>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="">直通车可用余额：</span>
			<div style="font-size:14px;">
				<label name="firstUseMoneyFormat">${informationStatisticsVO.firstUseMoney}</label>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">直通车下车总额：</span>
			<div style="font-size:14px;">
				<label  name="changeFormat"  >${informationStatisticsVO.getoffTotalAmount}</label>
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">投标直通车发布总金额：</span>
			<div style="font-size:14px;">
				<label  name="changeFormat"  >${informationStatisticsVO.publishTotalAmount}</label>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">新上车总额：</span>
			<div style="font-size:14px;">
				<label  name="changeFormat"  >${informationStatisticsVO.newgetonTotalAmount}</label>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"></span>
			<div style="font-size:14px;">
			</div>
		</div>
	
		<div class="left1_input_ts"  >
			<span style="width: 900px;text-align: left;" class="input_span">
		    <font color="${informationStatisticsVO.stockTotalAmount-(informationStatisticsVO.publishTotalAmount+informationStatisticsVO.newgetonTotalAmount-informationStatisticsVO.getoffTotalAmount)==0?'black':'red'}" style="font-size: 12px;"> 直通车存量总额( <label  name="changeFormat"  >${informationStatisticsVO.stockTotalAmount}</label>) =  投标直通车发布总金额（ <label  name="changeFormat"  >${informationStatisticsVO.publishTotalAmount}</label>）+ 新上车总额（ <label  name="changeFormat"  >${informationStatisticsVO.newgetonTotalAmount}</label>）- 直通车下车总额（ <label  name="changeFormat"  >${informationStatisticsVO.getoffTotalAmount}</label>）   </font></span>
		    <font color="${informationStatisticsVO.stockTotalAmount-(informationStatisticsVO.publishTotalAmount+informationStatisticsVO.newgetonTotalAmount-informationStatisticsVO.getoffTotalAmount)==0?'black':'red'}" style="font-size: 16px;">${informationStatisticsVO.stockTotalAmount-(informationStatisticsVO.publishTotalAmount+informationStatisticsVO.newgetonTotalAmount-informationStatisticsVO.getoffTotalAmount)==0?'':'注意:以上表达式没有形成等量关系'}</font>
		</div>
</div>
	</form>

</body>
<script language="javascript">
	$(document).ready(function(){
		 //改变页面万 显示  
		var  changeFormats= $("[name='changeFormat']");
		 $.each(changeFormats,function (){
			 $(this).text(parseFloat(($(this).text())/10000) +"万") ;
		 });
		 //直通车可用余额
		 $("[name='firstUseMoneyFormat']").text(parseFloat(($($("[name='firstUseMoneyFormat']")[0]).text())/10000).toFixed(2) +"万") ;
	});
 
</script>
</html>
