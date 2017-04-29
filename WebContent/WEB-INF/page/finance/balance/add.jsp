<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>
<body>
   <form id="approForm" name="" method="post">
     	<input type="hidden"  value="${endTime }" id="mainTime"/>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
			<tr>
				<td align="right" width="25%"><font color="red">*</font>日期：</td>
				<td colspan="3">
					<input style="width: 183px;height: 24px;" name="endTime" id="endTime"  
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
					styleClass="Wdate" placeholder="请输入开始日期" readonly="readonly" value='<fmt:formatDate value="${accountBalance.endTime}" pattern="yyyy-MM-dd HH:mm"/>'/>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>开户名：</td>
				<td >
					<select class="input1" id="userName" name="userName" onchange="getInfoByPayerId()">
					   <option value="">请选择</option>
					   <c:forEach items="${accountList}" var="accountList">
					   		<c:if test="${accountList.id == moneyOperation.outBankAccountId}">
					 			<option value="${accountList.id}" selected>${accountList.payName}</option>
					 		</c:if>
					 		<c:if test="${accountList.id != moneyOperation.outBankAccountId}">
					 			<option value="${accountList.id}" >${accountList.payName}</option>
					 		</c:if>
					   </c:forEach>
					    <option value="-1">线上充值</option>
				        <option value="-2">客户提现</option>
				        <option value="-3">网银在线</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut">银行卡：</td>
				<td class="J_transferOut"><input class="input1" type="text" id="cardNo" name="cardNo" value="${moneyOperation.payerCardNo}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut">开户行：</td>
				<td class="J_transferOut"><select class="input1" id="bankName" name="bankName">
				    <option value="">请选择</option>
				   <c:forEach items="${bankList}" var="bankList">
				   <c:if test="${bankList.id == moneyOperation.payerBankId}">
				 		<option value="${bankList.id}" selected>${bankList.value}</option>
				 	</c:if>
				 	<c:if test="${bankList.id != moneyOperation.payerBankId}">
				 		<option value="${bankList.id}" >${bankList.value}</option>
				 	</c:if>
				 </c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>余额：</td>
				<td class="J_transferOut"><input class="input1" type="text" id="balance" name="balance" value="${accountBalance.balance}"/></td>
			</tr>
		<%-- 	<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>转出支行：</td>
				<td class="J_transferOut"><input class="input1" type="text" id="payerSubbranch"  name="payerSubbranch" value="${moneyOperation.payerSubbranch}"/></td>
			</tr> --%>
		
			
		<%-- 	<tr>
				<td align="right" width="25%"><font color="red">*</font>备注：</td>
				<td colspan="3">
				 <textarea cols="100" rows="5" style="width:100%;height:80px;" id="remark" name="remark">${moneyOperation.remark}</textarea>
				</td>
			</tr> --%>
			
			<tr>
				<td align="center" colspan="4">
					<input type="button" id="saveBtn" name="saveBtn" onclick="add(${accountBalance.id});" value="保存" />
				</td>
			</tr>	
		</table>
</form>
</body>
<script type="text/javascript">
$(function(){
	var accId = '${accId}';
	if(balance != ''){
		$("#userName option[value='"+accId+"']").attr("selected","selected");
		getInfoByPayerId();
	}
});
	function getInfoByPayerId() {
		var payer = $("#userName").find("option:selected").val();
		if(payer != ''){
			var urlPath = '${path}/finance/interTransfer/getInfoById/' + payer + '.html';
			$.ajax({
				url : urlPath,
				type : 'post',
				dataType : 'json',
				success : function(result) {
					$("#bankName").attr("value", result.bankId);
					$("#cardNo").val(result.cardNo);
				},
				error : function(data) {
					layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
				}
			});
		}
	}


	function add(id) {
		if ($.trim($("#endTime").val()) == "") {
			layer.msg('请选择开始日期', 1, 5);
			return;
		}
		if ($.trim($("#userName").val()) == "") {
			layer.msg('请选择开户名', 1, 5);
			return;
		}
		if ($.trim($("#balance").val()) == "") {
			layer.msg('请输入余额', 1, 5);
			return;
		}
		
		var payer = $("#userName").find("option:selected").val();
		var endTime = $("input[name=endTime]").val();
		var balance = $("#balance").val();
		var _load = layer.load('处理中..');
		var urlPath = '${path}/finance/balance/saveBalcanceInfo.html?accId='+payer+'&endTime='+endTime+':00&balance='+balance;
		if(id != null && id != ''){
			urlPath += '&id='+id;
		}
		$.ajax({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.close(_load);
				if(result.code == 'true'){
					parent.pageGo(1);
					parent.layer.closeAll();
					layer.msg("操作成功！", 1, 1);
					
				}else{
					layer.msg("保存失败!", 1, 5);
				}
			},
			error : function(data) {
				layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
			}
		});

    }	
	$("#balance").on("keyup", function() {
		  this.value= this.value.replace(/,/g,'');
		  this.value = this.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
		  this.value = this.value.replace(/^\./g, "");  //验证第一个字符是数字而不是.
		  this.value = this.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
		  this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, "$1$2.$3");  //清除小数点后2位之外的数
		  this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		 /* var re =/^[0-9]+([.]{1}[0-9]+){0,1}$/;
		 if(re.exec(this.value)){
			 $("#wcreditlinesCn").val(toCn(this.value));
		 } */
	});
</script>
</html>
