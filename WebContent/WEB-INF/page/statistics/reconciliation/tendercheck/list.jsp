<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">投资者的投标支出：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.investorTenderMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">借款者的借款入账金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.borrowerSuccuessMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">借款标的借款总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.borrowMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;"></div>
	   	<div class="left1_input_ts">
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>投资者的投标支出:<fmt:formatNumber value="${checkMap.investorTenderMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	借款者的借款入账金额:<fmt:formatNumber value="${checkMap.borrowerSuccuessMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	借款标的借款总额:<fmt:formatNumber value="${checkMap.borrowMoney}" pattern="#,##0.00"/>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'':'注意:投标对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>	
	</div>
	
</div>
