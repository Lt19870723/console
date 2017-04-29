<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		  
		    <th width="40"  align="center">
				序号
			</th>
			<th width="70"  align="center">
				开放额度(万元)
			</th>
			<th width="90"  align="center">
				预期收益 
			</th>
			<th width="90"  align="center">
				锁定期限 
			</th>
			<th width="90"  align="center">
				最低开通额度(万元)
			</th>
			<th width="90"  align="center">
				最高开通额度(万元)
			</th>				
			<th width="80"  align="center">
				有效期限
			</th>
			<th width="100"  align="center">
				定时开放时间
			</th>	
			<th width="100"  align="center">
				加入截止时间
			</th>	
		     <th width="60"  align="center">
				来源
			</th>		
			 <th width="80"  align="center">
				状态
			</th>		
			<th width="150"  align="center">
				操作
			</th>
				 
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center" >
					${vo.planAccountStr }
				</td>
				<td align="center" >
					${vo.apr }%
				</td>
				<td align="center" >
					${vo.lockLimitStr }
				</td>							
				<td align="center" >
					${vo.lowestAccountStr }
				</td>
				<td align="center" >
					${vo.mostAccountStr }
				</td>										
				<td align="center" >
					${vo.validTimeStr }
				</td>
				<td align="center" >
					${vo.publishTimeStr }
				</td>	
				<td align="center" >
					${vo.endTimeStr}
				</td>
				<td align="center" >
					${vo.sourceFromStr }
				</td>
				<td align="center" >
					${vo.statusStr }
				</td>		
				<td>
			     <a href="javascript:;" onclick="show(${vo.id});">查看</a>
			     &nbsp;
			     <a href="javascript:;" onclick="edit(${vo.id});" style="${vo.status==0?'':'display:none'}">修改</a>
			     &nbsp;
			     <a href="javascript:;" onclick="doDelete(${vo.id});" style="${vo.status==0?'':'display:none'}">删除</a>
			     &nbsp;
			     <a href="javascript:;" onclick="submitApprove(${vo.id});" style="${vo.status==0?'':'display:none'}">提交审核</a>
			     &nbsp;
			     <a href="javascript:;" onclick="toEdit(${vo.id});" style="${vo.status==3 && vo.flagUpdate==1?'':'display:none'}">修改</a>	
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