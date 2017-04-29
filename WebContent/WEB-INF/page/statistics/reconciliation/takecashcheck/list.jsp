<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="资金明细中打款成功总额">提现记录总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.paymentSuccessMoney}" pattern="#,##0.00" />
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="资金明细中提现作废总额">提现作废总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.paymentFailedMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="提现流水中作废总额">实际提现作废总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.actualFailedMoney}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="净提现总额 = 提现记录总额-提现作废总额 +实际提现作废总额">净提现总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.paymentRealSuccessMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="提现流水中的总额">提现支出总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.takeCashMoney}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="提现流水中到账总额与手续费总额">实际打款总额：</span>
			<div style="font-size:14px;">
			<fmt:formatNumber value="${checkMap.actualCashMoney}" pattern="#,##0.00" />
			</div>
		</div>
		
	   	<div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>净提现总额:<fmt:formatNumber value="${checkMap.paymentRealSuccessMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	提现支出总额:<fmt:formatNumber value="${checkMap.takeCashMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	实际打款总额:<fmt:formatNumber value="${checkMap.actualCashMoney}" pattern="#,##0.00"/>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'':'注意:提现对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>	
	</div>
	
</div>
