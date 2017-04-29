<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="checkBorrowPopupform" action="" method="post">
	<table style="margin-left:10px;margin-top:5px;">
	<tr>
		<td>选择专区</td>
		<td>
		<input type="hidden" name="areaType" id="areaType" value="0" />
		<input type="radio" id="areaTypeRadioOne1" name="areaTypeRadioOne" value="0"  checked="checked" onchange="getAreaTypeValue2()"/>普通专区&nbsp;
		<input type="radio" id="areaTypeRadioOne2" name="areaTypeRadioOne" value="1"   onchange="getAreaTypeValue2()"/>新手专区
		</td>
	</tr>
	<tr id="newArea2" style="display:none;">
		<td>定时转普通时间：</td>
		<td><input  name="areaChangeTime" id="areaChangeTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'%y-%M-%d %H:%m:00'})" class="Wdate"  value='<fmt:formatDate value="${areaChangeTime}" pattern="yyyy-MM-dd HH:mm:ss" />'/></td>
	</tr>
	<tr>
		<td>审核意见</td>
		<td><font color="red">*</font></td>
	</tr>
	<tr>
		<td colspan="2">
		    <textarea id="remark" name="remark"   style="width:350px;height:100px;" ></textarea>
		</td>
	</tr>
	
	
	<tr>
				<td><span><font color="red">*</font>&nbsp;&nbsp;是否存管：</span> </td>
			    <td colspan="3"><input type="hidden" id="iscg" value="${borrowVo.isCustody}">
			    <c:choose>
			    	<c:when test="${borrowVo.isCustody==0}">
			    	 <input type="radio"  id="isCustody" name="isCustody"  value="0" checked="checked"  onclick="cg();"/>非存管&nbsp;&nbsp; 
				     <input type="radio"  id="isCustody2" name="isCustody"  value="1" onclick="cg2();"/>浙商存管
			    	</c:when>
			    	<c:when test="${borrowVo.isCustody==1}">
			    	<input type="radio"  id="isCustody" name="isCustody"  value="0"  onclick="cg();"/>非存管&nbsp;&nbsp; 
				    <input type="radio"  id="isCustody2" name="isCustody"  value="1" checked="checked"  onclick="cg2();"/>浙商存管
			    	</c:when>
			    	<c:otherwise>
			    	<input type="radio"  id="isCustody" name="isCustody"  value="0" checked="checked"  onclick="cg();"/>非存管&nbsp;&nbsp; 
				    <input type="radio"  id="isCustody2" name="isCustody"  value="1" onclick="cg2();"/>浙商存管
			    	</c:otherwise>
			    </c:choose>
			    
			    </td>			    
			
			</tr>
			
			
			<tr style="display: none;" id="qz">
					
			     <td class="rz_font"><font color="red">*</font>还款账户名：</td>
				 <td><select id="repayName" name="repayName" class="bigselect" onchange="findRepayAcct()">
				    <option value="">--请选择--</option>	
				   <c:forEach items="${borrowerUserList}" var="bUser">
							 <c:if test="${bUser.repayName==borrowVo.repayName}">
							 <option selected="selected" value="${bUser.repayName}">${bUser.repayName}</option>
							 </c:if>
							 <c:if test="${bUser.repayName!=borrowVo.repayName}">
							 <option value="${bUser.repayName}">${bUser.repayName}</option>
							 </c:if>
					</c:forEach>
					</select>
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>还款账户账号：</td>
				<td>
				<input class="input1" name="repayAcct"   id="repayAcct"  type="hidden"  value="${borrowVo.repayAcct}"/>
				<input class="input1" disabled="disabled" name="repayAcct2"   id="repayAcct2"  type="text"  value="${borrowVo.repayAcct}"/>
				</td>
			</tr> 
	
	
	
	<tr>
		<td colspan="2" align="center">
			<input class="sh" type="button" onclick="javascript:approPass(0);" value="审核通过" />
						 &nbsp; 
			<input class="sh" type="button" onclick="javascript:approPass(1);" value="审核不通过"/>
		</td>
	</tr>	
	</table>
	<input type="hidden"  name="borrowId" id="borrowId" value="${borrowId}"/>
	<input type="hidden"  name="flag" id="flag" value=""/>
	</form>
</body>
<script type="text/javascript">

function getAreaTypeValue2(){
	var areaTypeRadioOne= $("input[name='areaTypeRadioOne']:checked").val();
	$('#areaType').val(areaTypeRadioOne);
	if(areaTypeRadioOne == 1){
		$("#newArea2").attr("style", "diaplay:;");
	}else{
		$("#newArea2").attr("style", "display:none;");
		$("#areaChangeTime").val('');
	}
}
/**
 * 审核通过，批准
 */
var _load;
function approPass(flag){
	$(".sh").removeAttr("onclick");
	var areaType= $('#areaType').val();
	if(areaType == null || areaType == ''){
		alert("请选择专区类型！");
		$(".sh").attr("onclick","approPass("+flag+")");
		return false;
	}
	if(areaType == 1){
	 
		var areaChangeTime = $('#areaChangeTime').val();
	 
		if(areaChangeTime !=null && areaChangeTime != ""){
			var minute = areaChangeTime.substring(14,16);
			if(minute != 0 &&minute != 30){
				alert("定时转普通时间中的分钟必须为0或30！");
				$(".sh").attr("onclick","approPass("+flag+")");
				return false;
			}
		}
	}
	
	
	var remark = $('#remark').val();
	if($.trim(remark)==null || $.trim(remark)==""){
		alert("请输入审核意见。");
		$(".sh").attr("onclick","approPass("+flag+")");
		return false;
	}
	
	
	var iscg=$("#iscg").val();
	if(iscg==1){
		var repayName=$("#repayName").val().trim();
		var repayAcct=$("#repayAcct").val().trim();
		if(repayName.length==0 || repayName == ''){
			parent.layer.msg('请输入还款账户名！', 2, 5);
			$(".sh").attr("onclick","approPass("+flag+")");
			return false;
		}
		if(repayAcct.length==0 || repayAcct == ''){
			parent.layer.msg('请输入还款账户账号！', 2, 5);
			$(".sh").attr("onclick","approPass("+flag+")");
			return false;
		}
		
	}
	
	
	
	$('#flag').val(flag);
	_load = layer.load('处理中..');
	$.ajax({
		url : '${path}/approve/final/pass.html',
		data : {
			'borrowId' : $("#borrowId").val(),
			'flag' : $.trim($("#flag").val()),
			'remark' : $("#remark").val(),
			'areaType' : $('#areaType').val(),
			'areaChangeTime' : $('#areaChangeTime').val(),
			'isCustody':$('#iscg').val(),
			'repayName':$('#repayName').val(),
			'repayAcct':$('#repayAcct').val()
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			layer.close(_load);
			if (result.code == '0') {
				$(".sh").attr("onclick","approPass("+flag+")");
				layer.msg(result.message, 1, 1, function() {
					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					parent.layer.close(index); //执行关闭
					layer.msg(result.message,1,1);
				});
			} else if(result.code == '-1') {
				layer.msg(result.message, 1, 5);				
			} else {
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			$(".sh").attr("onclick","approPass("+flag+")");
			layer.msg('网络连接超时,请您稍后重试.', 1, 5, function() {
				parentLayer.close(_load);
			});
	    }
	}); 
}
$(function(){
	 
	var areaType= $('#areaType').val();
	if(areaType=='0'){
		$("#areaTypeRadio1").attr("checked","checked");
		$("#newArea2").attr("style", "display:none;");
	}
	if(areaType=='1'){
		$("#areaTypeRadio2").attr("checked","checked");
		$("#newArea2").attr("style", "display:;");
	}
	
});
</script>
<script type="text/javascript">
$(function(){
	var isCustody= $("#iscg").val();
	if(isCustody==1){
		$("#qz").attr("style","display:;");
	}
}); 

function cg(){
	$("#repayAcct").val("");
	$("#repayAcct2").val("");
	$("#repayName").val("");
	$("#qz").attr("style","display:none;");
	$("#iscg").val("0");
}
function cg2(){
	$("#qz").attr("style","display:;");
	$("#iscg").val("1");
}

function findRepayAcct(){
	var name=$("#repayName").val();
	if(name==null || name.length<=0){
		$("#repayAcct").val("");
		$("#repayAcct2").val("");
	}else{
	$.ajax({
		url:"${path}/information/memberRegiste/findBorrowerUser.html",
		type:"post",
		data:{repayName:name},
		dataType:"json",
		success : function(result) {
			$("#repayAcct").val(result.repayAcct);
			$("#repayAcct2").val(result.repayAcct);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
		
		
	});
	}
	
}


</script>
</html>
