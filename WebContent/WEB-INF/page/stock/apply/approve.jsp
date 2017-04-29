<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>
<body>
   <form id="approForm" name="" method="post">
   <input type="hidden" value="${applyInfo.id}" name="id"/> 
   <input type="hidden" value="${applyInfo.type}" name="type"/>
   <input type="hidden" value="${applyInfo.userId}" name="userId"/>
   <input type="hidden" id="J_collectionTotal"  value="${collectionTotal}"/>
   <input type="hidden" id="J_stockApplyNum"  value="${stockApplyNum}"/>
   
     		<table>
				<tr>
					<td align="right">平台用户名：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.userName}"/></td>
					
				</tr>
				<tr>
					<td align="right">姓名：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.userRealName}"/></td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td><input type="text" disabled="disabled" value="<c:if test="${applyInfo.sex==0}">男</c:if><c:if test="${applyInfo.sex==1}">女</c:if>"/></td>
				</tr>
				<tr>
					<td align="right">证件号码：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.idCard}"/></td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.mobile}"/></td>
				</tr>
				
				<c:if test="${applyInfo.type==2}">
				<tr>
					<td align="right">持有内转份额（份）：</td>
					<td><input type="text" disabled="disabled" value="${stockAccount.useStock}"/></td>
				</tr>
				</c:if>
				<c:if test="${applyInfo.type==1}">
				<tr>
					<td align="right">用户平台待收（元）：</td>
					<td><input type="text" disabled="disabled" value="${collectionTotal}"/></td>
				</tr>
				</c:if>
				<tr>
					<td align="right">审核结果：</td>
					<td><select name="status" id="J_status"><option value="2">审核通过</option><option value="3">审核不通过</option></select></td>
				</tr>
				<tr>
					<td align="right"><span style="color: red;">*</span>备注：</td>
					<td><textarea rows="5" id="remark" cols="40" name="remark"></textarea></td>
				</tr>
				
				
			<tr>
				<td align="center" colspan="4">
					<input type="button" id="saveBtn" name="saveBtn" onclick="appro();" value="保存" />
				</td>
			</tr>	
		</table>
</form>
</body>
<script type="text/javascript">
function appro(){
	 var remark = $("#remark").val();
	 var collectionTotal = $("#J_collectionTotal").val();
	 var status = $("#J_status").val();
	 var stockApplyNum = $("#J_stockApplyNum").val();
	 if(remark==''){
		 layer.msg('审核意见都不能为空！', 2, 5);
		return false;
	 }
	 if( status == 2 && parseFloat(collectionTotal) < parseFloat(stockApplyNum)){
		 layer.msg('待收小于20W,不能审核通过！', 2, 5);
		 return false;
	 }
	var _load = layer.load('处理中..');
	var urlPath = '${path}/apply/approveSubmit.html';
	layer.confirm("确认提交审核?", function(index){ 
		$("#approForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result.code==0){
					layer.close(_load);
					layer.msg(result.message , 2, 1);
					parent.pageGo(1);
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	                parent.layer.close(index);
				}else{
					layer.msg(result.message , 2, 5);
				}
			},
			error : function(data) {
				layer.msg(result.message , 1, 5);
			}
		});
		
	});
		
}
	
</script>
</html>
