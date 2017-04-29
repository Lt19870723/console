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
	<form action="" id="firstAddform" style="background: #fff;">
	 
	 <table style="width:800px;  margin:20 auto;  position:relative;  float:none;  padding:0;  text-align:left;" border="1">
		<tr>
			<td width="100px">开放额度：<font color="red">*</font></td>
			<td><input type="text" value="${fixBorrowVo.planAccount/10000}" disabled="disabled" name="planAccount" id="planAccount" maxlength="12" onblur="getDefaultKted(this.value)"
				style="width: 160px;"	size="20"/>&nbsp;万元
			</td>		
				
			<td width="100px">锁定期限：<font color="red">*</font></td>
			<td>
				<select id="lockLimit" name="lockLimit"  disabled="disabled"  style="height:24px;width:199px">
				  <option value="" >==请选择==</option>
				  <option value="1" ${fixBorrowVo.lockLimit==1?'selected=selected':'' } >1月</option>
				  <option value="3" ${fixBorrowVo.lockLimit==3?'selected=selected':'' }>3月</option>
				  <option value="6" ${fixBorrowVo.lockLimit==6?'selected=selected':'' }>6月</option>
				  <option value="12" ${fixBorrowVo.lockLimit==12?'selected=selected':'' }>12月</option>
				</select>
			</td>		
		
		</tr>
		<tr>
		
			<td width="100px">预期收益：<font color="red">*</font></td>
			<td><input type="text" value="${fixBorrowVo.apr}" id="apr" name="apr" maxlength="50" disabled="disabled" 
			style="width: 160px;"	size="20"/>%
			</td>		
			<td width="100px">最低开通额度：<font color="red">*</font></td>
			<td><input type="text" value="0.01" id="lowestAccount" name="lowestAccount" maxlength="12" 
				size="20" disabled="true"/>&nbsp;万元
			</td>
			</tr>
			<tr>
			<td width="100px">最高开通额度：<font color="red">*</font></td>
			<td><input type="text" value="${fixBorrowVo.mostAccount/10000}" name="mostAccount" id="mostAccount" name maxlength="12" disabled="disabled"
			style="width: 160px;"	size="20"/>&nbsp;万元
			</td>					
		<td width="100px">定时开通时间：<font color="red">*</font></td>
			<td>
				<input type="hidden"  name="endTime" id="endTime" value="<fmt:formatDate value='${fixBorrowVo.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/> 
				<input type="text" disabled="disabled" name="publishTime" id="publishTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})"
			    class="Wdate"  value="<fmt:formatDate value='${fixBorrowVo.publishTime}'  pattern='yyyy-MM-dd HH:mm:ss' />" /> 
				 					 
			</td>	
		</tr>
		<tr>
			<td width="100px">定向密码：</td>
			<td><input type="password"  value="${fixBorrowVo.bidPassword }" name="bidPassword" id="bidPassword" disabled="disabled"  maxlength="12"
				size="20"/>&nbsp;
			</td>					
		<td width="100px">有效期限：<font color="red">*</font></td>
			<td colspan="4" height="80px;">
				<input type="hidden"  name="validTimeStyle" id="validTimeStyle" value="${fixBorrowVo.validTimeStyle}" />
				<input type="radio" name="validTime_style" value="1" disabled="disabled" ${fixBorrowVo.validTimeStyle==1?'checked=checked':''} />按天&nbsp;
				<input type="radio" name="validTime_style" value="2" disabled="disabled" ${fixBorrowVo.validTimeStyle==2?'checked=checked':''}/>按小时&nbsp;
				<input type="radio" name="validTime_style" value="3" disabled="disabled" ${fixBorrowVo.validTimeStyle==3?'checked=checked':''}/>按分钟&nbsp;
				<input type="text" value="${fixBorrowVo.validTime}" maxlength="5" 
			      size="20" id="validTime" name="validTime" onchange="getEndTime();" style="width:80px;" disabled="disabled"/>
			    &nbsp;&nbsp;认购截止时间： <span id="endTimeTip" ></span>
			</td>	
		</tr>
		<c:if test="${null!=fixBorrowVo and fixBorrowVo.status==-2 }">
			<tr>
				<td class="firstWidth">撤销时间：</td>
				<td class="secondWidth"><input value="${fixBorrowVo.lastModifyTimeStr}" maxlength="50" size="20" disabled="true" />
				</td>
				<td class="firstWidth">撤销备注：</td>
				<td class="secondWidth"><input value="${fixBorrowVo.lastModifyRemark}" maxlength="50" size="20" disabled="true"/>
				</td>					
			</tr>
		</c:if>	
		<tr>

         <td colspan="6">
		<!--审核列表 -->
			 <c:if test="${!empty fixBorrowApprList}">
			 
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
									${i.index+1}
								</td>
								<td align="center" >
									发布定期宝审核
								</td>
								<td align="center" >
									<c:if  test="${fixBorrowAppr.userId !=-1}">${fixBorrowAppr.userName }</c:if>
									<c:if  test="${fixBorrowAppr.userId ==-1}">系统自动审核</c:if>
									<c:if  test="${fixBorrowAppr.userId ==null}">系统自动审核</c:if>
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
	 	
      </td>
   </tr>
		<tr>
			<td colspan="4" align="center"> 
				&nbsp;
				<input type="button" onclick="cancle()" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="关 闭" />
				 				
			</td>					
		</tr>																																	
	</table>
	</form> 
</body>
<script type="text/javascript">
//关闭窗口
function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
$(function(){
	var publishTime = $('#publishTime').val();
	var validTimeStyle= $("input[name='validTime_style']:checked").val();
	var validTime = $('#validTime').val();
	 
	if(publishTime.length > 0 && validTimeStyle.length > 0 && validTime.length > 0){
		// 转成时间戳
		var pubishTimeStamp = transdate(publishTime);
		var temp;
		if(validTimeStyle==1){
			var newStamp = parseInt(pubishTimeStamp) + validTime*24*60*60;
			temp=getDate(newStamp);
			$("#endTimeTip").html(temp);
			$("#endTime").val(temp);
			 
		}else if(validTimeStyle==2){
			var newStamp = parseInt(pubishTimeStamp) + validTime*60*60;
			$("#endTimeTip").html(getDate(newStamp));
			$("#endTime").val(temp); 
		}else{
			var newStamp = parseInt(pubishTimeStamp) + validTime*60;
			$("#endTimeTip").html(getDate(newStamp));
			$("#endTime").val(temp); 
		}
		 
	}
});
function transdate(endTime){
	var date = new Date(endTime.replace(/-/g,"/"));
	return Date.parse(date)/1000;
}
function getDate(tm){
	var date = new Date(tm*1000);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds(); 
	 
	return Y+M+D+h+m+s;
} 
</script>
</html>
