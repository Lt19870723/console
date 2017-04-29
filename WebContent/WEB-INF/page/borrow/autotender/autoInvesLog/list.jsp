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
		<!-- 	<th>标的类型  </th> -->
			<th>修改时间 </th>
			<th>记录类型  </th>
			<th>所投标  </th>
			<th>详情 </th>
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
			<%--     <td>
				<c:if   test="${vo.autoType==1 and vo.addtimeStamp >= 1434535200}">按抵押标、担保标、信用标投标</c:if>
				<c:if  test="${vo.autoType==1 and vo.addtimeStamp < 1434535200}">按抵押标、担保标投标</c:if>
				<c:if   test="${vo.autoType==2 and vo.addtimeStamp >= 1433088000}">按信用标投标</c:if>
				<c:if  test="${vo.autoType==2 and vo.addtimeStamp < 1433088000}">按净值标、信用标投标</c:if>
				<c:if   test="${vo.autoType==null}">历史类型</c:if>
				</td> --%>
				<td>
				 
				<c:if  test="${vo.tender_type==1}">按金额投标</c:if>
				<c:if  test="${vo.tender_type==2}">按比例投标</c:if>
				<c:if  test="${vo.tender_type==3}">按余额投标</c:if>
				</td>
				<td>
		        <c:if   test="${vo.record_type==2}">${vo.tender_record_accout}</c:if>
				<c:if   test="${vo.record_type!=2 and vo.tender_type==1}">${vo.tender_account_auto}</c:if>
				<c:if   test="${vo.record_type!=2 and vo.tender_type==2}">${vo.tender_scale}%</c:if>
				<c:if   test="${vo.record_type!=2 and vo.tender_type==3}">${vo.useMoney}</c:if>
			
				</td>
				 
			<%-- 	<td>
			 
				<c:if   test="${vo.borrow_type3_status==1}">净值标&nbsp;&nbsp;</c:if>
				<c:if  test="${vo.borrow_type2_status==1}">抵押标&nbsp;&nbsp;</c:if>
				<c:if   test="${vo.borrow_type1_status==1}">用标&nbsp;&nbsp;</c:if>
				<c:if   test="${vo.borrow_type5_status==1}">担保标&nbsp;&nbsp;</c:if>
				</td> --%>
				 
				<td>
				  <fmt:formatDate value="${vo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${vo.record_type==1}">修改</c:if>
				<c:if test="${vo.record_type==2}">投标成功</c:if>
				<c:if test="${vo.record_type==3}">删除</c:if>
				 
				</td>
				<td><a href="${portalPath}/toubiao/showBorrowDetail/${vo.borrowId}.html" target="_blank">${vo.borrowName}</a></td>
				<td>
				    <a href="javascript:;" onclick="fordetail(${vo.id});">详情</a>
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