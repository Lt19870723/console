<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>一键发宝</title>
</head>
<body>
<form id="formFixQuickPubPop" name="formFixQuickPubPop" method="post">
	<input type="hidden" name="id"  value="${fixBorrowVo.id}" />
	<table style="margin-left: 10px; margin-top: 10px;">
		<tr>
			<td>开放额度：<font color="red">*</font></td>
			<td class="secondWidth">
				<input type="text" value="${fixBorrowVo.planAccount}" name="planAccount" id="planAccount" maxlength="12" onblur="getDefaultKted(this.value);" class="deailtModify"
					size="20" />&nbsp;万元
			</td>
			<td>锁定期限：<font color="red">*</font></td>
			<td class="secondWidth">
				<select class="deailtModify" onchange="getDefaultYqsy(this.value);" name="lockLimit" id="lockLimit" style="height:24px;width:199px">
					<option value="">--请选择--</option>
					<option value="1" >1月</option>
					<option value="3" >3月</option>
					<option value="6" >6月</option>
					<option value="12" >12月</option>
				</select>
			</td>
		</tr>

		<tr>
			<td>预期收益：<font color="red">*</font></td>
			<td class="secondWidth">
			<input type="text" value="${fixBorrowVo.apr}" name="apr" id="apr"  class="deailtModify" size="20" />&nbsp;%</td>

			<td>最低开通额度：<font color="red">*</font></td>
			<td class="secondWidth">
				<input type="text" value="0.01"  id="lowestAccount" maxlength="12" size="20" disabled="disabled" />&nbsp;万元
				<input type="hidden" name="lowestAccount"  value="${fixBorrowVo.lowestAccount}" />
			</td>
		</tr>
		<tr>
			<td>最高开通额度：<font color="red">*</font></td>
			<td class="secondWidth">
				<input type="text" value="${fixBorrowVo.mostAccount}" name="mostAccount" id="mostAccount" maxlength="12" class="deailtModify" size="20" />&nbsp;万元
			</td>
			<td>定时开通时间：<font color="red">*</font></td>

			<td class="secondWidth">
				<input type="hidden" name="lastEndTimeYjfb" id="lastEndTimeYjfb"  value="<fmt:formatDate value='${lastEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" />
				
				<input type="text"
					name="publishTime" id="firstPublishTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" 
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'lastEndTimeYjfb\')}'})" onchange="getEndTime2()"
					class="deailtModify" value="<fmt:formatDate value='${fixBorrowVo.publishTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"  />
			</td>

		</tr>
		<tr>
			<td>有效期限：<font color="red">*</font></td>
			<td colspan="3" style="white-space:nowrap;">
				<input type="hidden" name="validTimeStyle"  id="validTimeStyle" value="${fixBorrowVo.validTimeStyle}" />
				<input type="radio" id="validTime_style_1" name="validTime_style2" value="1" class="deailtModify" onchange="getEndTime2()" />按天&nbsp; 
				<input type="radio" id="validTime_style_2" name="validTime_style2" value="2" class="deailtModify" onchange="getEndTime2()" />按小时&nbsp; 
				<input type="radio" id="validTime_style_3" name="validTime_style2" value="3" class="deailtModify" onchange="getEndTime2()" />按分钟&nbsp;
				<input type="text" value="${fixBorrowVo.validTime}" maxlength="5" class="deailtModify" size="20" name="validTime" id="validTime" style="width:80px;" onchange="getEndTime2()" />
				&nbsp;&nbsp;
				认购截止时间： 
				<span id="endTimeTip2" style="white-space:nowrap;"></span>
			</td>
		</tr>
		<tr>
			<td colspan="1">定向密码：<font color="red">*</font></td>
			<td colspan="3">
			<input type="password" value="" name="bidPassword" id="bidPassword" />&nbsp;</td>
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
	$(document).ready(function (){	
		//日期格式化函数
		Date.prototype.Format = function(fmt)   
		{ 
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		};
		
		//默认初始化
		$('#lockLimit option[value="${fixBorrowVo.lockLimit}"]').attr("selected","selected");
		var validTimeStyle = $('[id="validTimeStyle"]').val();
		if(validTimeStyle == 1){
			$("#validTime_style_1").attr("checked","checked");
		}else if(validTimeStyle == 2){
			$("#validTime_style_2").attr("checked","checked");
		}else{
			$("#validTime_style_3").attr("checked","checked");
		}
		
		var firstPublishTime = $('[id="firstPublishTime"]').val();
		var validTimeStyle= $("input[name='validTime_style2']:checked").val();
		var validTime = $('[id="validTime"]').val();
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
		}
	});

	//取默认的预期收益
	function getDefaultYqsy(value){
		if(value=='1'){
			$('[id="apr"]').val(8);
		}else if(value=='3'){
			$('[id="apr"]').val(9);
		}else{
			$('[id="apr"]').val('');
		}
	}
	
	//定时开通时间，有效期限-时间处理
	function getEndTime2(){
		var numReg= /^[1-9]\d*$/;
		var v_validTime  = $("#validTime").val();
		 if(!(numReg.test(v_validTime))){
		 	alert('有限期限输入错误!');
		 	return ;
		 }
	
		var firstPublishTime = $('[id="firstPublishTime"]').val();
		var validTimeStyle= $("input[name='validTime_style2']:checked").val();
		var validTime = $('[id="validTime"]').val();
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
				alert("有效期限不能超过三天！");
				$("#endTimeTip").html('');
				$('[id="validTime"]').val('');
			}
		}
	}
	
	//开放额度校验
	function getDefaultKted(obj){
	 var re = /^[0-9]*[0-9]$/i;       //校验是否为数字
	 if(re.test(obj)){
		 if(obj%5==0){
			 $('[id="planAccount"]').val(obj);
		 }else{
			 alert('请输入5万的整数倍'); 
			 $('[id="planAccount"]').val('');
		 }
		 
	 } else{
		 alert('请输入有效整数'); 
		 $('[id="planAccount"]').val('');
	 }
		
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
	
	/**
	* 验证表单数据
	*/
	function validateAddInfoQuick(){
		//金额的正则表达式  正整数 
		var integerReg= /^[1-9]\d*$/;
		//计划金额
		var planAccount = $('[id="planAccount"]').val();
		if(!(integerReg.test(planAccount))){
			alert("计划金额必须是正整数！");
			$('[id="planAccount"]').focus();
			return false;
		}
		
		//最低开通额度
		 var lowestAccount = $('[id="lowestAccount"]').val();
		if(parseFloat(lowestAccount) != 0.01){
			alert("最低开通额度只能为1百元");
			$('[id="lowestAccount"]').focus();
			return false;
		}
		if(parseInt(lowestAccount) > parseInt(planAccount)){
			alert("最低开通额度不能大于计划金额！");
			$('[id="lowestAccount"]').focus();
			return false;
		}
		
		//最高开通额度
		var mostAccount = $('[id="mostAccount"]').val();
		if(!integerReg.test(mostAccount)){
			alert("最高开通额度必须是正整数！");
			$('[id="mostAccount"]').focus();
			return false;				
		}
		if(parseInt(mostAccount) > parseInt(planAccount)){
			alert("最高开通额度不能大于计划金额！");
			$('[id="mostAccount"]').focus();
			return false;
		}
		if(parseInt(mostAccount) < parseInt(lowestAccount)){
			alert("最高开通额度必须大于最低开通额度！");
			$('[id="mostAccount"]').focus();
			return false;
		}
		
		//锁定期限
		var v_lockLimit = $('[id="lockLimit"]').val();
		if(v_lockLimit.length==0)
		{
			alert("请选择锁定期限!");
			$('[id="lockLimit"]').focus();
			return false;
		}
		
		//预期收益
		var apr = $('[id="apr"]').val();
		if(apr.length <=0){
			alert("请输入预期收益!");
			$('[id="apr"]').focus();
			return false;
		}
		
	  //判断有效收益为正数
	  var reg = /^\d+(?=\.{0,1}\d+$|$)/
	  if(!reg.test(apr)) {
		  alert("请输入有效的预期收益！");
		  return false;
	  }
		  
		//定时开通时间
		v_publishTime = $("#lastEndTimeYjfb").val();
		v_firstPublishTime = $("#firstPublishTime").val();
		if(v_firstPublishTime.length ==0)
		{
			alert("请输入定时开通日期!");
			$("#firstPublishTime").val(v_publishTime);
			return false;
		}
		
		var validTimeStyle= $("input[name='validTime_style2']:checked").val();
		if(validTimeStyle.length == 0 || validTimeStyle == 0){
			alert("请输入有效期限单位!");
			return false;
		}else{
			$('[id="validTimeStyle"]').val(validTimeStyle);
		}
		
		//定时开通时间>now()
		var v_now  = new Date().Format("yyyy-MM-dd hh:mm:ss");
		if(v_firstPublishTime < v_now)
		{
			alert("定时开通时间应大于系统时间!");
			$("#firstPublishTime").val(v_publishTime);
			return false;
		}
		
		//有效期限
		var validTime = $('[id="validTime"]').val();
		if(validTime.length <=0){
			alert("请输入有效期限!");
			$('[id="validTime"]').focus();
			return false;
		}
		if(!(integerReg.test(validTime))){
			alert("有效期限必须为正整数!");
			return false;
		} 
		//按天
		if(validTimeStyle == 1 )
		{
			if(validTime > 3)
			{	
				alert("有效期限最多3天!");
				return false;
			}
		}
		//按小时
		if(validTimeStyle == 2 )
		{
				if(validTime > 72)
				{	
					alert("有效期限最多3天!");
					return false;
				}
		}
		//按分钟
		if(validTimeStyle == 3 )
		{
				if(validTime > 4320)
				{	
					alert("有效期限最多3天!");
					return false;
				}
		}
		
		
		return true;
	}
	
	function save() {
		$('#saveBtn').attr("onclick", "");
		$('#closeBtn').attr("onclick", "");
		
		if (!validateAddInfoQuick()){
			$('#saveBtn').attr("onclick", "save();");
			$('#closeBtn').attr("onclick", "close();");
			return;
		}

		$('#saveBtn').attr("onclick", "save();");
		$('#closeBtn').attr("onclick", "close();");
		
		$('input:hidden[name="lowestAccount"]').val($("#lowestAccount").val());
		
		var _load = layer.load('处理中..');
		$("#formFixQuickPubPop").ajaxSubmit({
			url : '${path}/fix/fixBorrowWillExpire/save.html',
			type : 'post',
			success : function(result) {
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						parent.$("#selectBtn").trigger("click");
						parent.layer.closeAll();
					});
				} else {
					parent.layer.alert(result.message);
				}
			},
			error : function(result) {
				parent.layer.alert('网络连接超时,请您稍后重试.');
			}
		});
	}
	
	function closeMe() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
</script>
</html>