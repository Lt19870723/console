<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		    <th>序号  </th>
			<th>用户名  </th>
			<th>排队号 </th>
			<th>是否启用</th>
		<!-- 	<th>自动类型    </th> -->
			<th>投标方式   </th>
			<th>投标额度 </th>
			<th>最低投标金额 </th>
		<!-- 	<th>标的类型  </th> -->
			<th>还款方式 </th>
			<th>借款期限  </th>
			<th>年化收益率  </th>
		</tr>
	 <c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>
				 <c:if  test="${vo.rownum != null}">第${vo.rownum}位</c:if>
				</td>
				<td>
				  
				<c:if   test="${vo.status==0}">未启用</c:if>
				<c:if    test="${vo.status==1}">已启用</c:if>
				<c:if   test="${vo.status==2}">已删除</c:if>
				</td>
				<%-- <td>
				 
				<c:if  test="${vo.autoType==1}">按抵押标、担保标、信用标投标</c:if>
				</td> --%>
				<td>
				 
				<c:if  test="${vo.tender_type==1}">按金额投标</c:if>
				<c:if  test="${vo.tender_type==2}">按比例投标</c:if>
				<c:if  test="${vo.tender_type==3}">按余额投标</c:if>
				</td>
				<td>
		 
				<c:if   test="${vo.tender_type==1}">${vo.tender_account_auto}</c:if>
				<c:if  test="${vo.tender_type==2}">${vo.tender_scale}%</c:if>
				<c:if   test="${vo.tender_type==3}">${vo.useMoney}</c:if>
				</td>
				<td> ${vo.min_tender_account}</td>
				<%-- <td>
			 
				<c:if   test="${vo.borrow_type3_status==1}">净值标&nbsp;&nbsp;</c:if>
				<c:if  test="${vo.borrow_type2_status==1}">抵押标&nbsp;&nbsp;</c:if>
				<c:if   test="${vo.borrow_type1_status==1}">用标&nbsp;&nbsp;</c:if>
				<c:if   test="${vo.borrow_type5_status==1}">担保标&nbsp;&nbsp;</c:if>
				</td> --%>
				<td>
				 
				
				<c:if  test="${vo.borrow_type==0}">无限定</c:if>
				<c:if   test="${vo.borrow_type==1}">额本息</c:if>
				<c:if   test="${vo.borrow_type==2}">按月付息到期还本</c:if>
				<c:if   test="${vo.borrow_type==3}">到期还本付息</c:if>
				<c:if  test="${vo.borrow_type==4}">按天还款</c:if>
				</td>
				<td>
				<c:if test="${vo.timelimit_status==1}">无限定</c:if>
				<c:if test="${vo.timelimit_status==0 and vo.min_time_limit > 0}">按月：${vo.min_time_limit}</c:if>
				<c:if test="${vo.timelimit_status==0 and vo.min_time_limit > 0}">~${vo.max_time_limit}个月</c:if>
				<c:if test="${vo.timelimit_status==0 and vo.min_day_limit > 0 and vo.borrow_type2_status == 0 and vo.borrow_type5_status == 0}">按天：${vo.min_day_limit}</c:if>
				<c:if test="${vo.timelimit_status==0 and vo.min_day_limit > 0 and vo.borrow_type2_status == 0 and vo.borrow_type5_status == 0}">~${vo.max_day_limit}天</c:if>
				</td>
				<td>
				<c:if test="${vo.min_apr == null and vo.max_apr==null}">无限定</c:if>
				<c:if test="${vo.min_apr!=null and vo.min_apr > 0}">${vo.min_apr}%</c:if>
				<c:if test="${vo.min_apr!=null and vo.min_apr > 0}">~${vo.max_apr}%</c:if>
				 
				</td>
				 
			</tr>
            </c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="9">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>