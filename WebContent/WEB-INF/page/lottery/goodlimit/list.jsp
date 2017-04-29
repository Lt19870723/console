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
			 	 奖品名称
			 </th>
		     <th>
				 开始时间
			 </th>
		     <th>
				 结束时间
			 </th>
		     <th>
				 总次数
			 </th>
		     <th>
				 已用次数
			 </th>
		      
		     <th>
				 操作
			 </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				 <td>${sta.index+1}</td>
				 <td>${vo.goodName}</td>
			     <td>${vo.startTimeStr}</td>
			     <td>${vo.endTimeStr}</td>
			     <td>${vo.sumNum}</td>
			     <td>${vo.usedNum}</td>
			     <td>
				    <a href="javascript:;" onclick="add(${vo.id});">修改</a>
				    &nbsp;&nbsp;
				    <a href="javascript:;" onclick="deleteGoodLimit(${vo.id});">删除</a>
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