<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		 
		 
		    <tr>
			     <th>
					 用户
				 </th>
			     <th>
					 获奖物品
				 </th>
			     <th>
					 申领时间
				 </th>
			     <th>
					 发放时间
				 </th>
				  <th>
					 处理人
				 </th>
				 <th>
					 处理时间
				 </th>
			     <th>
					 状态
				 </th>
			     <th>
					 操作
				 </th>
			     <th>
					 备注
				 </th>
		    </tr>
	 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			 
		     <td>${vo.userName} </td>
		     <td>${vo.goodName} </td>
		     <td>${vo.addtimeStr}</td>
		     <td>${vo.drawTimeStr} </td>
		     <td>${vo.dealUserName} </td>
		     <td>${vo.dealTimeStr} </td>
		     <td> 
			     ${vo.status==0?"未领取":""}
		         ${vo.status==1?"已领取":""}
			     ${vo.status==2?"待处理":""}
			     ${vo.status==3?"派发中":""}
			     ${vo.status==4?"已过期":""}
		     </td>
		     <td>   
		            
				    <a href="javascript:;" onclick="accept('${vo.id}','${vo.userName}');" style='${vo.status!=2?"display:none":""}'>受理</a>
				  
				    &nbsp;&nbsp;
				   
				    <a href="javascript:;" onclick="sureOpeara('${vo.id}');" style='${vo.status!=3?"display:none":""}'>已领取</a>
		             
		      </td>
		     <td>${vo.remark}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>