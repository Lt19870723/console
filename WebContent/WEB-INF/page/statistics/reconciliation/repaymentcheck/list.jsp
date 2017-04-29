<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><font color='red'>回款对账</font></span>
			<div style="font-size:14px;">
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"></span>
			<div style="font-size:14px;">
			</div>
		</div>			
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">投资者的回款入账：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.repaymentBackMoney}" pattern="#,##0.00" />
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">回款的小账户进出资金：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.webSiteMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">回款入账+小账户：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.allRepaymentMoney}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">借款者的还款支出：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.borrowerPayMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">网站应还总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.webMustPayMoney}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"></span>
			<div style="font-size:14px;">
			</div>
		</div>
		
	   	<div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>回款入账+小账户:<fmt:formatNumber value="${checkMap.allRepaymentMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	借款者的还款支出:<fmt:formatNumber value="${checkMap.borrowerPayMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	网站应还总额:<fmt:formatNumber value="${checkMap.webMustPayMoney}" pattern="#,##0.00"/>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'':'注意:还款对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>	
	</div>
	
</div>
