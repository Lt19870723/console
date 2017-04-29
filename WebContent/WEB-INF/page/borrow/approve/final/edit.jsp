<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
 
</head>
<body style="background: #fff;">
	<form id="form" style="background: #fff;" action="" method="post">
	<table style="margin-left:10px;margin-top:5px;">
	 <tr>
		<td>借款金额：<font color="red">*</font></td>
		<td colspan="3">
		<input type="text"  name="account" id="account" value="${borrowVo.account}" style="width:200px;"/>
		</td>
	</tr>
	<tr>
		<td>借款期限：<font color="red">*</font></td>
		<td><input type="text"  name="timeLimit" id="timeLimit" value="${borrowVo.timeLimit}" style="width:200px;"/></td>
		<td>利率：<font color="red">*</font></td>
		<td><input type="text"  name="apr" id="apr" value="${borrowVo.apr}" style="width:200px;"/></td>
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
		<td>借款介绍：<font color="red">*</font></td>
		<td colspan="3"> 
		    <textarea id="content" name="content" styleClass="contentCls">${borrowVo.content}</textarea>
		 
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" onclick="javascript:save();" value="保存" />
						 &nbsp; 
			<input type="button" onclick="javascript:cancle();" value="关闭"/>
		</td>
	</tr>	
	</table>
	  <input type="hidden"  name="id" id="id" value="${borrowVo.id}"/>
	  <input type="hidden"  name="style" id="style"  value="${borrowVo.style}"/>
	</form>
</body>
<script type="text/javascript">
 
$(function(){
	 
	var modifyUm = UM.getEditor('content', {
		initialFrameWidth:600,
		initialFrameHeight:150,
		autoHeightEnabled:false
	});
});
var _load;

function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}

function validateForm(){
	//金额的正则表达式
	var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	var numberReg = /^\d+$/;
	var account= $('#account').val();
	if(account == null || account == ''){
		alert("借款金额不能为空！");
		return false;
	}
	if(!zfdsReg.test(account)){
		parent.layer.msg("借款金额输入有误!", 2, 5);
		return false;
	}
	if(account < 500){
		parent.layer.msg("借款金额不能少于500元!", 2, 5);
		return false;
	}
	
	var style = $.trim($('#style').val());
	var timeLimit = $.trim($('#timeLimit').val());
	if(timeLimit == null || timeLimit == ''){
		parent.layer.msg('请输入借款期限！', 2, 5);
		return false;
	}
	if(!numberReg.test(timeLimit)){
		parent.layer.msg("借款期限输入有误!", 2, 5);
		return false;
	}
	if(timeLimit <1){
		parent.layer.msg("借款期限不能少于1", 2, 5);
		return false;
	}
	if(timeLimit > 99){
		parent.layer.msg("借款期限不能大于99", 2, 5);
		return false;
	}

	var apr = $.trim($('#apr').val());
	if(apr == null || apr == ''){
		parent.layer.msg('请输入年化利率！', 2, 5);
		return false;
	}
	if(!zfdsReg.test(apr)){
		parent.layer.msg("年化利率输入有误!", 2, 5);
		return false;
	}
	if(apr < 6){
		parent.layer.msg("年化利率不能小于6", 2, 5);
		return false;
	}
	if(apr > 24){
		parent.layer.msg("年化利率不能大于24", 2, 5);
		return false;
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
	
	
	
	var content = $.trim($('#content').val());
	if(content == null || content == ''){
		parent.layer.msg('请输入借款介绍！', 2, 5);
		return false;
	}
	_load = layer.load('处理中..');
	return true;
}
function save(){
	if(!validateForm()){
		return false;
	} 
	$("#form").ajaxSubmit ({
		url : '${path}/approve/final/updateBorrowInfo.html',
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
