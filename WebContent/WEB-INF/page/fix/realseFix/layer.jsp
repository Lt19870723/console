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
	 <input type="hidden" value="${fixBorrowVo.id}" name="id" id="id"/>
	 <table style="width:900px;  margin:20 auto;  position:relative;  float:none;  padding:0;  text-align:left;" border="1">
		<tr>
			<td width="100px">开放额度：<font color="red">*</font></td>
			<td><input type="text" value="${fixBorrowVo.planAccount}" name="planAccount" id="planAccount" maxlength="12" onblur="getDefaultKted(this.value)"
				style="width: 160px;"	size="20"/>&nbsp;万元
			</td>		
				
			<td width="100px">锁定期限：<font color="red">*</font></td>
			<td>
				<select id="lockLimit" name="lockLimit"   style="height:24px;width:199px">
				  <option value="">==请选择==</option>
				  <option value="1">1月</option>
				  <option value="3">3月</option>
				  <option value="6">6月</option>
				  <option value="12">12月</option>
				</select>
			</td>		
		
		</tr>
		<tr>
		
			<td width="100px">预期收益：<font color="red">*</font></td>
			<td><input type="text" value="${fixBorrowVo.apr}" id="apr" name="apr" maxlength="50"  
			style="width: 160px;"	size="20"/>%
			</td>		
			<td width="100px">最低开通额度：<font color="red">*</font></td>
			<td><input type="text" value="0.01" id="lowestAccount" name="lowestAccount" maxlength="12" 
				size="20" disabled="true"/>&nbsp;万元
			</td>
			</tr>
			<tr>
			<td width="100px">最高开通额度：<font color="red">*</font></td>
			<td><input type="text" value="${fixBorrowVo.mostAccount}" name="mostAccount" id="mostAccount" name maxlength="12"
			style="width: 160px;"	size="20"/>&nbsp;万元
			</td>					
		<td width="100px">定时开通时间：<font color="red">*</font></td>
			<td>
				<input type="hidden"  name="lastEndTime" id="lastEndTime" value="<fmt:formatDate value='${lastEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/> 
				<input type="text"  name="publishTime" id="publishTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})"  
			    class="Wdate" onchange="getEndTime()"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'lastEndTime\')}'})" value="<fmt:formatDate value='${fixBorrowVo.publishTime}'  pattern='yyyy-MM-dd HH:mm:ss' />" /> 
				 					 
			</td>	
		</tr>
		<tr>
			<td width="100px">定向密码：</td>
			<td><input type="password"  value="" name="bidPassword" id="bidPassword"  maxlength="12"
				size="20"/>&nbsp;
			</td>					
		<td width="100px">有效期限：<font color="red">*</font></td>
			<td colspan="4" height="80px;">
				<input type="hidden"  name="validTimeStyle" id="validTimeStyle" value="${fixBorrowVo.validTimeStyle}"/>
				<input type="radio" name="validTime_style" value="1" checked="checked" onchange="getEndTime();"/>按天&nbsp;
				<input type="radio" name="validTime_style" value="2" onchange="getEndTime();"/>按小时&nbsp;
				<input type="radio" name="validTime_style" value="3" onchange="getEndTime();"/>按分钟&nbsp;
				<input type="text" value="${fixBorrowVo.validTime}" maxlength="5" 
			      size="20" id="validTime" name="validTime" onchange="getEndTime();" style="width:80px;"/>
			    &nbsp;&nbsp;</br>认购截止时间： <span id="endTimeTip" ></span>
			</td>	
		</tr>
		<tr>
			<td colspan="4" align="center">
			    <input type="button" onclick="save()" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="保 存" />	
				 
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
//保存
function save(){
	if(!validateAddInfo()){
		return false;
	}
	var _load = parentLayer.load('处理中..');
	$("#firstAddform").ajaxSubmit ({
		url : '${path}/fix/realseFix/saveFixBorrow.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '1'){
				parentLayer.close(_load);
				layer.msg(result.message,1,1,function(){
					window.parent.location=window.parent.location;
		    	});
			}else{
				layer.msg(result.message,1,5,function(){
					parentLayer.close(_load);
		    	});
			}
			
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			parentLayer.close(_load);
		}
	}); 
}
//根据锁定期限取预期利率
function getDefaultYqsy(value){
	if(value=='1'){
		$('#apr').val(8);
	}else if(value=='3'){
		$('#apr').val(9);
	}else{
		$('#apr').val('');
	}
	
}

//开放额度校验
function getDefaultKted(obj){
	 var re = /^[0-9]*[0-9]$/i;       //校验是否为数字
	 if(re.test(obj)){
		 if(obj%5==0){
			 $('#mostAccount').val(obj);
		 }else{
			 alert('请输入5万的整数倍'); 
			 $('#planAccount').val('');
				return false;
		 }
		 
	 } else{
		 alert('请输入有效整数'); 
		 $('#planAccount').val('');
			return false;
	 }
		
}
//取认购截止日期
function getEndTime(){
	var firstPublishTime = $('#publishTime').val();
	var validTimeStyle= $("input[name='validTime_style']:checked").val();
	var validTime = $('#validTime').val();
	if(firstPublishTime.length > 0 && validTimeStyle.length > 0 && validTime.length > 0){
		// 转成时间戳
		var pubishTimeStamp = transdate(firstPublishTime);
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
		 
		if(newStamp-pubishTimeStamp>60*60*24*3){
			alert("认购截止日期不能超过三天！");
			$("#endTimeTip").html('');
			$('#validTime').val('');
		}
	}
}
 

/**
* 验证表单数据
*/
function validateAddInfo(){
	//金额的正则表达式  正整数 
	var integerReg= /^[1-9]\d*$/;
	//开放金额
	var planAccount = $('#planAccount').val();
	if(!(integerReg.test(planAccount))){
		alert("开放额度必须是正整数！");
		$('#planAccount').focus();
		return false;
	}
	if(planAccount > 10000){
		
		alert("开放额度不能大于一亿");
		$('#planAccount').focus();
		return false;
		
	}
	
	//最低开通额度
	var lowestAccount = $('#lowestAccount').val();
	if(parseFloat(lowestAccount) != 0.01){
		alert("最低开通额度只能为1百元");
		$('#lowestAccount').focus();
		return false;
	}
	if(parseInt(lowestAccount) > parseInt(planAccount)){
		alert("最低开通额度不能大于开放金额！");
		$('#lowestAccount').focus();
		return false;
	}
	
	//最高开通额度
	var mostAccount = $('#mostAccount').val();
	if(!integerReg.test(mostAccount)){
		alert("最高开通额度必须是正整数！");
		$('#mostAccount').focus();
		return false;				
	}
	if(parseInt(mostAccount) > parseInt(planAccount)){
		alert("最高开通额度不能大于开放额度！");
		$('#mostAccount').focus();
		return false;
	}
	if(parseInt(mostAccount)< parseInt(lowestAccount)){
		alert("最高开通额度必须大于最低开通额度！");
		$('#mostAccount"]').focus();
		return false;
	}			
	
	//预期收益
	var apr = $('#apr').val();
	if(apr.length <=0){
		alert("请输入预期收益!");
		$('#apr"]').focus();
		return false;
	}
	
	//判断有效收益为正数
	var reg = /^\d+(?=\.{0,1}\d+$|$)/
	  if(!reg.test(apr)) {
		  alert("请输入有效的预期收益！");
		  return false;
	  }
	//预期收益>8
	if(apr<8){
		alert("预期收益不能小于8");
		return false;
	}	  
	//锁定期限
	var lockLimit = $('#lockLimit').val();
	if(lockLimit.length <=0){
		alert("请选择锁定期限!");
		$('#lockLimit"]').focus();
		return false;
	}
 
	var publishTime = $('#publishTime').val();
	if(publishTime.length <=0){
		alert("请输入定时开通时间!");
		return false;
	}
	
	var validTimeStyle= $("input[name='validTime_style']:checked").val();
	if(validTimeStyle.length == 0 || validTimeStyle == 0){
		alert("请输入有效期限单位!");
		return false;
	}else{
		$('#validTimeStyle').val(validTimeStyle);
	}
	//投标有效期限
	var validTime = $('#validTime').val();
	if(validTime.length <=0){
		alert("请输入有效期限!");
		$('#validTime').focus();
		return false;
	}
	if(!(integerReg.test(validTime))){
		alert("有效期限必须为正整数!");
		return false;
	}
	return true;		
}
function transdate(endTime){
	var date = new Date(endTime.replace(/-/g,"/"));
	return Date.parse(date)/1000;
}
function getDate(tm){
	var date = new Date(tm*1000);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = (date.getHours()  < 10? ('0'+date.getHours() ): +date.getHours()) + ':';
	m = (date.getMinutes()< 10? ('0'+date.getMinutes()): +date.getMinutes())+ ':';
	s = (date.getSeconds()< 10? ('0'+date.getSeconds()): +date.getSeconds()); 
	 
	return Y+M+D+h+m+s;
} 
</script>
</html>
