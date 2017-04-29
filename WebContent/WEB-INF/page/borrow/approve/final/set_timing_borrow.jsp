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
	<form id="timingBorrowPopupform" action="" method="post">
	<table style="margin-left:10px;height:200px;margin-top:5px;">
	<tr>
		<td>选择专区</td>
		<td>
		<input type="hidden" name="areaType" id="areaType" value="${areaType}"/>
		<input type="radio" id="areaTypeRadio1" name="areaTypeRadio" value="0" checked="checked"  onchange="getAreaTypeValue()"/>普通专区&nbsp;
		<input type="radio" id="areaTypeRadio2" name="areaTypeRadio" value="1" onchange="getAreaTypeValue()"/>新手专区	</td>
	</tr>
	<tr>
		<td>发标时间：</td>
		<td><input type="text"  name="timingBorrowTime" id="timingBorrowTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'%y-%M-%d %H:%m:00'})" class="Wdate"  value='<fmt:formatDate value="${timingBorrowTime}"  pattern="yyyy-MM-dd HH:mm:ss" />'/></td>
	</tr>
	<tr id="newArea" style="display:none;">
		<td>定时转普通时间：</td>
		<td><input type="text"  name="areaChangeTime" id="areaChangeTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00'})" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'%y-%M-%d %H:%m:00'})" class="Wdate"  value='<fmt:formatDate value="${areaChangeTime}"   pattern="yyyy-MM-dd HH:mm:ss" />'/></td>
	</tr>
	<tr>
	
	
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
	
	
	
	
		<td colspan="2" align="center" >
			<input type="button" onclick="javascript:save();" value="保存" />
		</td>
	</tr>	
	</table>
	<input type="hidden"  name="borrowId" id="borrowId" value="${borrowId}"/>
	</form>
</body>

<script type="text/javascript">
var _load;
function getAreaTypeValue(){
	var areaTypeRadio= $("input[name='areaTypeRadio']:checked").val();
	$('#areaType').val(areaTypeRadio);
	 
	if(areaTypeRadio == 1){
		$("#newArea").attr("style", "diaplay:;");
	}else{
		$("#newArea").attr("style", "display:none;");
		$('#areaChangeTime"]').val('');
	}
	 
}
function beforeTimingBorrow(){
	var areaType= $('#areaType').val();
	 
	if(areaType == null || areaType == ''){
		alert("请选择专区类型！");
		return false;
	}
	var timingBorrowTime = $('#timingBorrowTime').val();
	if(timingBorrowTime==null || timingBorrowTime==""){
		alert("请输入发标时间！");
		return false;
	}else{
		var minute = timingBorrowTime.substring(14,16);
	 
		if(minute != 0 && minute != 30){
			alert("发标时间中的分钟必须为0或30！");
			return false;
		}
	}

	if(areaType == 1){
		var areaChangeTime = $('#areaChangeTime').val();
		if(areaChangeTime!=null && areaChangeTime != ""){
			var minute = areaChangeTime.substring(14,16);
			if(minute != 0 && minute != 30){
				alert("定时转普通时间中的分钟必须为0或30！");
				return false;
			}
			if(new Number(transdate(areaChangeTime)) < new Number(transdate(timingBorrowTime))){
				alert("请输入定时转普通时间必须大于发布时间！");
				return false;
			}
		}
	}
	
	
	var iscg=$("#iscg").val();
	if(iscg==1){
		var repayName=$("#repayName").val().trim();
		var repayAcct=$("#repayAcct").val().trim();
		if(repayName.length==0 || repayName == ''){
			parent.layer.msg('请输入还款账户名！', 2, 5);
			return false;
		}
		if(repayAcct.length==0 || repayAcct == ''){
			parent.layer.msg('请输入还款账户账号！', 2, 5);
			return false;
		}
		
	}
	
 
	return true;
}
function save(){
	if(!beforeTimingBorrow()){
		return false;
	}
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/approve/final/setTimingBorrow.html',
		data : {
			'id' : $("#borrowId").val(),
			'areaType' : $('#areaType').val(),
			'timingBorrowTime' : $('#timingBorrowTime').val(),
			'areaChangeTime': $('#areaChangeTime').val(),
			'isCustody':$('#iscg').val(),
			'repayName':$('#repayName').val(),
			'repayAcct':$('#repayAcct').val()
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					parent.layer.close(index); //执行关闭
					layer.msg(result.message,1,1);
				});
			} else if(result.code == '-1') {
				layer.msg(result.message, 1, 5);
				 			
			}else  {
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			layer.msg("网络异常...", 1, 5);
        }

	});
}

/**
 * 日期格式字符串：yyyy-MM-dd HH:mm:ss 转换成时间戳
 */
function transdate(dateStr){ 
	var date = new Date(dateStr.replace(/-/g,"/"));
	return Date.parse(date)/1000; 
}
$(function(){
	 
	var areaType= $('#areaType').val();
	if(areaType=='0'){
		$("#areaTypeRadio1").attr("checked","checked");
		$("#newArea").attr("style", "display:none;");
	}
	if(areaType=='1'){
		$("#areaTypeRadio2").attr("checked","checked");
		$("#newArea").attr("style", "display:;");
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
