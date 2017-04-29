<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="40">序号</th>
			<th>定期宝编号</th>
			<th>开放额度（元）</th>
			<th width="70">锁定期限</th>
			<th>可用余额（元）</th>
			<th width="110">满宝时间</th>
<!-- 			<th width="110">限制投标<br/>截止日期</th> -->
			<th width="150">操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.contractNo }</td>
				<td><fmt:formatNumber value="${vo.planAccount}" pattern="#,##0.00" /></td>
				<td>${vo.lockLimitStr }</td>
				<td><fmt:formatNumber value="${vo.useMoney}" pattern="#,##0.00" /></td>
				<td><fmt:formatDate value='${vo.successTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
<%-- 				<td><fmt:formatDate value='${vo.tenderBidDate }' pattern='yyyy-MM-dd' /></td> --%>
				<td>
<%-- 					<c:if test="${vo.tenderBidFlag ==0 or (vo.diffTenderDays>0 and vo.tenderBidFlag==1) }"> --%>
						<a href="javascript:fixTender('<fmt:formatNumber value="${vo.useMoney}" pattern="#,##0.00" />','${vo.contractNo}','${vo.id }', '<fmt:formatNumber value="${borrowMoney}" pattern="#,##0.00" />');">投标</a>
<%-- 					</c:if> --%>
<%-- 					<a href="javascript:show(${vo.id },'setTenderBid');">修改限制</a> --%>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="text-align: left;" colspan="7"><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></td>
		</tr>
	</table>
</div>
<script>
function show(id,opt){
	var _title = '定期宝详情';
	opt = opt || '';
	if(opt=='setTenderBid'){
		_title='设置限制自动投标';
	}
	var _url = '${path}/fix/fixborrow/toAddModify.html?id='+id+'&opt='+opt;
	$.layer({
		type : 2,
		title : _title,
		area : [ '80%', '90%' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			 
	    }
	});
}
</script>