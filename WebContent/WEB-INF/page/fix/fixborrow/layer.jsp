<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #fff;">
	 
	<table style="margin-left:10px;margin-top:5px;" border="1">
	<tr>
		<td class="firstWidth">开放额度：<font color="red">*</font></td>
		<td class="secondWidth">
		<input type="text" value="${fixBorrowVo.planAccount}"  onblur="getDefaultKted1(this.value);" id="planAccount" maxlength="12" styleClass="deailtModify"
			size="20"/>&nbsp;万元
		</td>						
		<td class="firstWidth">锁定期限：<font color="red">*</font></td>
		<td class="secondWidth">
			<select class="deailtModify"  id="lockLimit"  value="${fixBorrowVo.lockLimit}"  onchange="getDefaultYqsy1(this.value);"  style="height:24px;width:199px">
			   <option value="">请选择</option>
			   <option value="1">1月</option>
			   <option value="3">3月</option>
			   <option value="6">6月</option>
			   <option value="12">12月</option>
			</select>
		 </td>
		
	</tr>
	<tr>
	
	<td class="firstWidth">预期收益：<font color="red">*</font></td>
		<td class="secondWidth"><input type="text" value="${fixBorrowVo.apr}" id="apr" maxlength="50" styleClass="deailtModify" 
			size="20"/>&nbsp;%
		</td>
		
		<td class="firstWidth">最低开通额度：<font color="red">*</font></td>
		<td class="secondWidth"><input type="text" value="${fixBorrowVo.lowestAccount}" id="lowestAccount" maxlength="12" 
			size="20"  disabled="true"/>&nbsp;万元
		</td>
		</tr>
		<tr>
		<td class="firstWidth">最高开通额度：<font color="red">*</font></td>
		<td class="secondWidth"><input type="text" value="${fixBorrowVo.mostAccount}" id="mostAccount" maxlength="12" styleClass="deailtModify" 
			size="20"/>&nbsp;万元
		</td>	
		<td class="firstWidth">定时开通时间：<font color="red">*</font></td>
		<td class="secondWidth">
			 <input type="text" id="firstPublishTime"   styleClass="Wdate" value="<fmt:formatDate value="${fixBorrowVo.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
				    class="deailtModify"/>	 
		</td>	
	</tr>
	<tr>
		
	</tr>					
	 
	<tr>
		<td class="firstWidth">满标时间：</td>
		<td class="secondWidth"><input type="text" value="${fixBorrowVo.successTimeStr}" maxlength="50"  
	        size="20" disabled="true" />
		</td>
		<td class="firstWidth">投标次数：</td>
		<td class="secondWidth"><input type="text" value="${fixBorrowVo.tenderTimes}" maxlength="50"  
			size="20" disabled="true"/>
		</td>					
	</tr>					
 
	<tr>
		<td class="firstWidth">有效期限：<font color="red">*</font></td>
		<td colspan="5">
		<input type="radio" id="validTime_style_1" name="validTime_style2" value="1" class="deailtModify" onchange="getEndTime2()"/>按天&nbsp;
		<input type="radio" id="validTime_style_2" name="validTime_style2" value="2" class="deailtModify" onchange="getEndTime2()"/>按小时&nbsp;
		<input type="radio" id="validTime_style_3" name="validTime_style2" value="3" class="deailtModify" onchange="getEndTime2()"/>按分钟&nbsp;
		<input type="text" value="${fixBorrowVo.validTime}" maxlength="5"  styleClass="deailtModify" 
		      size="20" id="validTime" style="width:80px;" onchange="getEndTime2()"/>
		&nbsp;&nbsp;认购截止时间： <span id="endTimeTip2" style ="white-space:nowrap;"></span>
		</td>					
	</tr>
	<tr >

     <td colspan="6"   >
				<!--审核列表 -->
			<c:if test="${!empty fixBorrowApprList}">
			<c:if test="${fn:length(fixBorrowApprList)>0 }">
			<label>审核情况：</label>
			<table class="fulltable">
					<thead>
						<tr>
							<th width="80"  align="center">
								序号
							</th>
							<th width="100" align="center">
								审核类型
							</th>
							<th width="120" align="center">
								审核人
							</th>
							<th width="150"  align="center">
								审核时间
							</th>
							<th width="80"  align="center">
								状态
							</th>
							<th width="150"  align="center">
								审核备注
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${fixBorrowApprList}" var="fixBorrowAppr" varStatus="i">
					<tr>
							<td align="center" >
								${i+1}
							</td>
							<td align="center" >
								发布定期宝审核
							</td>
							<td align="center" >
								<c:if  test="${fixBorrowAppr.userId!=-1}">${fixBorrowAppr.userName }</c:if>
								<c:if  test="${fixBorrowAppr.userId==-1}">系统自动审核</c:if>
								<c:if  test="${fixBorrowAppr.userId==null}">系统自动审核</c:if>
							</td>
							<td align="center" >
								${fixBorrowAppr.addTimeStr }
							</td>
							<td align="center" >
								${fixBorrowAppr.operTypeStr }
							</td>
							<td align="center" >
								${fixBorrowAppr.remark }
							</td>
					</tr>
				 </c:forEach>
				</tbody>						
			</table>
			    </c:if>
		</c:if>	
      </td>
   </tr>
   <tr>
		<td colspan="6" align="center">
			 <a href="javascript:;" onclick="close();">关闭</a>    
		</td>					
   </tr>
</table>
</body>
<script type="text/javascript">
function getEndTime2(){
	var firstPublishTime = $('#firstPublishTime').val();
	var validTimeStyle= $("input[name='validTime_style2']:checked").val();
	var validTime = $('#validTime').val();
	 
	if(firstPublishTime.length > 0 && validTimeStyle.length > 0 && validTime.length > 0){
		// 转成时间戳
		var pubishTimeStamp = transdate(firstPublishTime);
		if(validTimeStyle==1){
			var newStamp = parseInt(pubishTimeStamp) + validTime*24*60*60;
			$("#endTimeTip2").html(getDate(newStamp));
		}else if(validTimeStyle==2){
			var newStamp = parseInt(pubishTimeStamp) + validTime*60*60;
			$("#endTimeTip2").html(getDate(newStamp));
		}else{
			var newStamp = parseInt(pubishTimeStamp) + validTime*60;
			$("#endTimeTip2").html(getDate(newStamp));
		}
		if(newStamp-pubishTimeStamp>60*60*24*3){
			alert("认购截止日期不能超过三天！");
			$("#endTimeTip2").html('');
			$('#validTime').val('');
		}
		
 
	}
}
</script>
</html>
