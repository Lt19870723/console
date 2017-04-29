<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>直通车新增-国诚金融后台管理系统</title>
</head>
<body>
<form id="firstAddform" name="firstAddform" method="post">
	
	<table style="margin-left: 10px; margin-top: 10px;">
		<tr>
			<td width="120px">计划金额：<font color="red">*</font></td>
			<td>
				<input type="text" id="planAccount" name="planAccount" value="${firstBorrowVo.planAccount}" class="input1" maxlength="12" size="20"  />
				&nbsp;万元
			</td>		
			<td width="120px">预期收益：<font color="red">*</font></td>
			<td>
				<input type="text" id="perceivedRate" name="perceivedRate" value="${firstBorrowVo.perceivedRate}" class="input1" maxlength="50" size="20"  />
				&nbsp;%
			</td>					
		</tr>
		<tr>
			<td width="120px">最低开通额度：<font color="red">*</font></td>
			<td>
				<input type="text" id="lowestAccount" value="${firstBorrowVo.lowestAccount/10000}" class="input1" maxlength="12" size="20" disabled="disabled" />
				&nbsp;万元&nbsp;&nbsp;&nbsp;
			</td>
			<td width="120px">最高开通额度：<font color="red">*</font></td>
			<td>
				<input type="text" id="mostAccount" name="mostAccount" value="${firstBorrowVo.mostAccount}" class="input1" maxlength="12" size="20"  />
				&nbsp;万元
			</td>					
		</tr>
		<tr>
			<td width="120px">投标密码：</td>
			<td>
				<input type="text" id="passwordSource" name="passwordSource" value="${firstBorrowVo.passwordSource}" class="input1" maxlength="50" size="20"  />
			</td>	
			<td width="120px">定时开通时间：<font color="red">*</font></td>
			<td>
				<input type="hidden" id="lastEndTime" name="lastEndTime" value='<fmt:formatDate value="${lastEndTime}" pattern="yyyy-MM-dd HH:mm:ss" />' />
				
				<input
					name="publishTime" id="publishTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'lastEndTime\')}'})" onchange="getEndTime()"
					class="Wdate" value="${firstBorrowVo.publishTime}" />
			</td>	
		</tr>
		<tr>
			<td width="120px">有效期限：<font color="red">*</font></td>
			<td colspan="3" height="80px;">
				<input type="hidden" id="validTimeStyle" name="validTimeStyle" value="${firstBorrowVo.validTimeStyle}" />
				<input type="radio" name="validTime_style" value="1" checked="checked" onchange="getEndTime();"/>按天&nbsp;
				<input type="radio" name="validTime_style" value="2" onchange="getEndTime();"/>按小时&nbsp;
				<input type="radio" name="validTime_style" value="3" onchange="getEndTime();"/>按分钟&nbsp;
				<input type="text" id="validTime" name="validTime" value="${firstBorrowVo.validTime}" class="input1" 
					maxlength="50" size="20" onchange="getEndTime();" style="width:80px;" />
				&nbsp;&nbsp;认购截止时间： <span id="endTimeTip"></span>
			</td>	
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" />
				&nbsp;&nbsp;
				<input type="button" id="closeBtn" name="closeBtn" onclick="closeMe();" value="关闭" />	
			</td>					
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">
	
	function getEndTime(){
		var publishTime = $("#publishTime").val();
		var validTimeStyle= $("input[name='validTime_style']:checked").val();
		var validTime = $("#validTime").val();
		if(publishTime.length > 0 && validTimeStyle.length > 0 && validTime.length > 0){
			// 转成时间戳
			var pubishTimeStamp = transdate(publishTime);
			if(validTimeStyle==1){
				var newStamp = parseInt(pubishTimeStamp) + validTime*24*60*60;
				$("#endTimeTip").html(getDate(newStamp));
			}else if(validTimeStyle==2){
				var newStamp = parseInt(pubishTimeStamp) + validTime*60*60;
				$("#endTimeTip").html(getDate(newStamp));
			}else{
				var newStamp = parseInt(pubishTimeStamp) + validTime*60;
				$("#endTimeTip").html(getDate(newStamp));
			}
		}
	}
	
	/**
	* 验证表单数据
	*/
	function validateAddInfo(){
		//金额的正则表达式  正整数 
		var integerReg= /^[1-9]\d*$/;
		//计划金额
		var planAccount = $("#planAccount").val();
		if(!(integerReg.test(planAccount))){
			alert("计划金额必须是正整数！");
			$("#planAccount").focus();
			return false;
		}
		
		if(planAccount > 10000){
			
			alert("计划金额不能大于一亿");
			$("#planAccount").focus();
			return false;
			
		}
		
		//最低开通额度
		var lowestAccount = $("#lowestAccount").val();
		if(parseFloat(lowestAccount) != 0.1){
			alert("最低开通额度只能为1千元");
			$("#lowestAccount").focus();
			return false;
		}
		if(parseInt(lowestAccount) > parseInt(planAccount)){
			alert("最低开通额度不能大于计划金额！");
			$("#lowestAccount").focus();
			return false;
		}
		
		//最高开通额度
		var mostAccount = $("#mostAccount").val();
		if(!integerReg.test(mostAccount)){
			alert("最高开通额度必须是正整数！");
			$("#mostAccount").focus();
			return false;				
		}
		if(parseInt(mostAccount) > parseInt(planAccount)){
			alert("最高开通额度不能大于计划金额！");
			$("#mostAccount").focus();
			return false;
		}
		if(parseInt(mostAccount) < parseInt(lowestAccount)){
			alert("最高开通额度必须大于最低开通额度！");
			$("#mostAccount").focus();
			return false;
		}			
		
		//预期收益
		var perceivedRate = $("#perceivedRate").val();
		if(perceivedRate.length <=0){
			alert("请输入预期收益!");
			$("#perceivedRate").focus();
			return false;
		}
		
		//投标密码
		var passwordSource = $("#passwordSource").val();
		if(passwordSource.length>0){
			var reg2 = /^[a-zA-Z0-9]+$/;
			if(!reg2.test(passwordSource)){
				alert("投标密码必须为字母或数字！");
				$("#passwordSource").focus();
				return false;	
			}
			if(passwordSource.length < 6){
				alert("投标密码长度必须大于或等于6位！");
				$("#passwordSource").focus();
				return false;
			}else if(passwordSource.length > 12){
				alert("投标密码长度不能大于或等于12位！");
				$("#passwordSource").focus();
				return false;
			}
		}
		
		var publishTime = $("#publishTime").val();
		if(publishTime.length <=0){
			alert("请输入定时开通时间!");
			return false;
		}
		
		var validTimeStyle= $("input[name='validTime_style']:checked").val();
		if(validTimeStyle.length == 0 || validTimeStyle == 0){
			alert("请输入有效期限单位!");
			return false;
		}else{
			$("#validTimeStyle").val(validTimeStyle);
		}
		//投标有效期限
		var validTime = $("#validTime").val();
		if(validTime.length <=0){
			alert("请输入有效期限!");
			$("#validTime").focus();
			return false;
		}
		if(!(integerReg.test(validTime))){
			alert("有效期限必须为正整数!");
			return false;
		}
		
		return true;		
	}

	/**
	 * 时间戳转成日期格式字符串：yyyy-MM-dd HH:mm:ss
	 */
	function getDate(tm){ 
		var now = new Date(parseInt(tm) * 1000);
		var year=now.getFullYear();    
		var month=now.getMonth() + 1; 
		if(parseInt(month) < 10){
			month = "0"+month;
		}
		var date=now.getDate();   
		if(parseInt(date) < 10){
			date = "0"+date;
		}
		var hour=now.getHours(); 
		if(parseInt(hour) < 10){
			hour = "0"+hour;
		}
		var minute=now.getMinutes(); 
		if(parseInt(minute) < 10){
			minute = "0"+minute;
		}
		var second=now.getSeconds(); 
		if(parseInt(second) < 10){
			second = "0"+second;
		}
		return  year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;
	}
	
	/**
	 * 日期格式字符串：yyyy-MM-dd HH:mm:ss 转换成时间戳
	 */
	function transdate(dateStr){ 
		var date = new Date(dateStr.replace(/-/g,"/"));
		return Date.parse(date)/1000; 
	}

	function save() {
		$('#saveBtn').attr("onclick", "");
		$('#closeBtn').attr("onclick", "");
		
		if (!validateAddInfo()){
			$('#saveBtn').attr("onclick", "save();");
			$('#closeBtn').attr("onclick", "close();");
			return;
		}
		
		$('#saveBtn').attr("onclick", "save();");
		$('#closeBtn').attr("onclick", "close();");
		var _load = layer.load('处理中..');
		$("#firstAddform").ajaxSubmit({
			url : '${path}/firstborrow/firstborrowlist/save.html',
			type : 'post',
			success : function(result) {
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						window.parent.location.href = window.parent.location.href;
					});
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function closeMe() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
</script>
</html>