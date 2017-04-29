<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			 <th>
				 序号
			 </th>      	 
		     <th>
				 奖品
			 </th>
		     <th>
				 奖品类型
			 </th>
		     <th>
				 中奖概率
			 </th>
		     <th>
				 子概率
			 </th>
		     <th>
				 指定活动中中奖概率
			 </th>
		     <th>
				 转盘位置
			 </th>
		     <th>
				 操作
			 </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				  <td>${sta.index+1}</td>
				  <td> 
			        ${vo.chirldNameStr}
			        <label  style='${vo.awardType==4?"display: none":""}' >${vo.name}</label>
			        
   
		         </td>
			     <td>
			         ${vo.awardType==1?"现金":""}
				     ${vo.awardType==2?"实物":""}
				     ${vo.awardType==3?"谢谢参与":""}
				     ${vo.awardType==4?"抽奖机会":""}
				     ${vo.awardType==5?"红包":""}
				     ${vo.awardType==6?"元宝":""}
			     </td>
			     <td>
			      <fmt:formatNumber   value="${vo.chance}" pattern="#,##0.00"  maxFractionDigits="2"/>%
			     </td>
			     <td>${vo.chirldChanceStr}</td>
			     <td>${vo.activtyAwardChanceStr}</td>
			     <td>${vo.turntablePosition}</td>
			     <td>
			          
				    <a href="javascript:;" onclick="update(${vo.id});">修改</a>
				    &nbsp;&nbsp;
				    <c:if test="${vo.awardType!=3}">
				    <a href="javascript:;" onclick="deleteAward(${vo.id});">删除</a>
		            </c:if>
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