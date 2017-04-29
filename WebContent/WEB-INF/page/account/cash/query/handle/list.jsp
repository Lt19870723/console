<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="logList" class="main_cent">
	<div>

		<font color="red">提现金额总计:<fmt:formatNumber value="${resultMap.sumTotal}" pattern="#,##0.00"/></font>
		 &nbsp;&nbsp;
		<font color="red">到账金额总计:<fmt:formatNumber value="${resultMap.sumCredited}" pattern="#,##0.00"/></font>
		 &nbsp;&nbsp;
		<font color="red">手续费总计:<fmt:formatNumber value="${resultMap.sumFee}" pattern="#,##0.00"/></font>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<font color="red">此数据为提现审核通过未打款</font>
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
						<th>
							<a href="javascript:selectAll();"><span id="cashTip" style="color: red">全选</span></a>
						</th>
						<th>
							序号
						</th>
						<th>
							用户名
						</th>
						<th>
						用户类型
						</th>
						<th>
							开户人
						</th>
						<th>
							银行账户
						</th>
						<th>
							银行名称
						</th>
						<th>
							银行支行
						</th>
						<th>
							提现时间
						</th>
						<th>
							提现金额
						</th>
						<th>
							手续费
						</th>
						<th>
							到账金额
						</th>
						<th>
							审核人
						</th>
						<th>
							审核时间
						</th>
						<th>
							审核备注
						</th>
						<th>
							导出人
						</th>
						<th>
							是否已导出
						</th>
						<th>
							状态
						</th>
					</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>
					<input type="checkbox" name="cashId" id="cashId" value="${vo.id}"/>
					<input type="hidden" name="isExport_${vo.id}" value="${vo.isExport}" />
				</td>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.isFinancialUserStr}</td>
				<td>${vo.realname}</td>
				<td>${vo.account}</td>
				<td>${vo.bank}</td>
				<td>${vo.branch}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.totalStr}</td>
				<td>${vo.feeStr}</td>
				<td>${vo.creditedStr}</td>
				<td>${vo.verifyName}</td>
				<td>${vo.verifyTimeYmdhms}</td>
				<td>${vo.verifyRemark}</td>
				<td>${vo.exportUserName}</td>
				<c:if test="${vo.isExport==0}">
				<td>未导出</td>
				</c:if>
				<c:if test="${vo.isExport==1}">
				<td>已导出</td>
				</c:if>
				<td><a href="javascript:showpaycash('${vo.id }')">立即打款</a><br/>
				<a href="javascript:showcancelcash('${vo.id }')">取消提现</a><br/>
				<a href="javascript:showfailcashLayer('${vo.id }')">打款失败</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
