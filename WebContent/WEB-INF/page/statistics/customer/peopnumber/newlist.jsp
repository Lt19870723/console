<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
  <div id="logList" class="main_cent">
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			 <th style="width: 3%">序号</th>      	 
		     <th>用户名</th>
		     <th>推荐人</th>
			 <th>手机号</th>
		     <th>真实姓名</th>
		     <th>注册时间</th>
			 <th>首次投资时间</th>
		     <th>首次投资金额</th>
		     <th>投资总额</th>
			 <th>首次投资种类</th>
		     <th>当前待收</th>
		     <th>推广来源</th>
		</tr>
		<c:forEach items="${page.result }" var="newInterestMember" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			       <td>${sta.index+1}</td>
				   <td>${newInterestMember.userName}</td>
				   <td>${newInterestMember.inviterName}</td>
				   <td>${newInterestMember.mobilenum}</td>
				   <td>${newInterestMember.realName}</td>
				   <td><fmt:formatDate value="${newInterestMember.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				   <td><fmt:formatDate value="${newInterestMember.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				   <td> <fmt:formatNumber value="${newInterestMember.investMoney}" pattern="#,##0.00" /></td>
				   <td> <fmt:formatNumber value="${newInterestMember.investMoneyTotal}" pattern="#,##0.00" /></td>
				   <td>${newInterestMember.type}</td>
				   <td> <fmt:formatNumber value="${newInterestMember.repaymentAccountTotal}" pattern="#,##0.00" /></td>
				   <td>
					   <c:forEach items="${sources}" var="o">
						<c:if test="${o.name==newInterestMember.source}">
						  ${o.value} 
					    </c:if>
					   </c:forEach>
				  </td>
			</tr>
		</c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>