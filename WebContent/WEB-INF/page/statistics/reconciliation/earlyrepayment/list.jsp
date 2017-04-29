<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><font color='red'>提前还款对账</font></span>
			<div style="font-size:14px;">
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"></span>
			<div style="font-size:14px;">
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">投资人扣除总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.subtractInterestTotal}" pattern="#,##0.00" />
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">投资人应扣总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.collectionSubtractTotal}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">小账户进出资金：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.websiteEarlyTotal}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">借款人增加总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.addInterestTotal}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">借款人实际增加总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.repaymentAddTotal}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"></span>
			<div style="font-size:14px;">
			</div>
		</div>
		
	   	<div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.repayEarlyCheckVO.compareResult=='true' || checkMap.repayEarlyCheckVO==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	投资人扣除总额:<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.subtractInterestTotal}" pattern="#,##0.00"/> +
		    	小账户进出资金:<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.websiteEarlyTotal}" pattern="#,##0.00"/><font color="blue">=</font>
		    	投资人应扣总额:<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.collectionSubtractTotal}" pattern="#,##0.00"/> +
		    	小账户进出资金:<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.websiteEarlyTotal}" pattern="#,##0.00"/> <font color="blue">=</font> 
		    	借款人增加总额:<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.addInterestTotal}" pattern="#,##0.00" /><font color="blue">=</font>
		    	借款人实际增加总额：<fmt:formatNumber value="${checkMap.repayEarlyCheckVO.repaymentAddTotal}" pattern="#,##0.00"/>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.repayEarlyCheckVO.compareResult=='true' || checkMap.repayEarlyCheckVO==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.repayEarlyCheckVO.compareResult=='true' || checkMap.repayEarlyCheckVO==null)?'':'注意:提前还款对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>	
	</div>
	
</div>
