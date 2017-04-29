<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内部转账-国诚金融后台管理系统</title>
</head>
<body>
   <form id="approForm" name="" method="post">
     <input type="hidden" id="id" name="id" value="${moneyOperation.id }" />
     <div class="listzent">转账申请(* 必填)</div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
		<tr>
				<td align="right" width="25%"><font color="red">*</font>汇款金额：</td>
				<td ><input class="input1" type="text" id="operationrMoney"  name="operationrMoney" value="${moneyOperation.operationrMoney}"/></td>
				<td align="right" width="25%" ><font color="red">*</font>业务发生时间：</td>
				<td ><input class="input1" type="text" id="businessTime" name="businessTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" value='<fmt:formatDate value="${moneyOperation.businessTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>付款人：</td>
				<td ><select class="input1" id="outBankAccountId" name="outBankAccountId" onchange="getInfoByPayerId()">
				   <option value="">请选择</option>
				   <c:forEach items="${accountList}" var="accountList">
				   		<c:if test="${accountList.id == moneyOperation.outBankAccountId}">
				 			<option value="${accountList.id}" selected>${accountList.payName}</option>
				 		</c:if>
				 		<c:if test="${accountList.id != moneyOperation.outBankAccountId}">
				 			<option value="${accountList.id}" >${accountList.payName}</option>
				 		</c:if>
				   </c:forEach>
				</select></td>
				
				<td align="right" width="25%"><font color="red">*</font>收款人：</td>
				<td><select class="input1" id="inBankAccountId" name="inBankAccountId" onchange="getInfoByPayeeId()">
				   <option value="">请选择</option>
				   <c:forEach items="${accountList}" var="accountList">
				   		<c:if test="${accountList.id == moneyOperation.inBankAccountId}">
				 			<option value="${accountList.id}" selected>${accountList.payName}</option>
				 		</c:if>
				 		<c:if test="${accountList.id != moneyOperation.inBankAccountId}">
				 			<option value="${accountList.id}" >${accountList.payName}</option>
				 		</c:if>
				   </c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>付款支行：</td>
				<td class="J_transferOut"><input class="input1" type="text" id="payerSubbranch"  name="payerSubbranch" value="${moneyOperation.payerSubbranch}"/></td>
				<td align="right" width="25%" class="J_transferInto"><font color="red">*</font>收款支行：</td>
				<td class="J_transferInto"><input class="input1" type="text" id="payeeSubbranch" name="payeeSubbranch" value="${moneyOperation.payeeSubbranch}"/></td>
				
			</tr>
			<tr>
			<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>付款银行：</td>
				<td class="J_transferOut"><select class="input1" id="payerBankId" name="payerBankId">
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
			<td align="right" width="25%" class="J_transferInto"><font color="red">*</font>收款银行：</td>
				<td class="J_transferInto"><select class="input1" id="payeeBankId" name="payeeBankId">
				   <option value="">请选择</option>
				   <c:forEach items="${bankList}" var="bankList">
				   	<c:if test="${bankList.id == moneyOperation.payeeBankId}">
				 		<option value="${bankList.id}" selected>${bankList.value}</option>
				 	</c:if>
				 	<c:if test="${bankList.id != moneyOperation.payeeBankId}">
				 		<option value="${bankList.id}" >${bankList.value}</option>
				 	</c:if>
				 </c:forEach>
				</select></td>
			</tr>
			<tr>
			<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>付款银行卡号：</td>
				<td class="J_transferOut"><input class="input1" type="text" id="payerCardNo" name="payerCardNo" value="${moneyOperation.payerCardNo}"/></td>
			<td align="right" width="25%" class="J_transferInto"><font color="red">*</font>收款银行卡号：</td>
				<td class="J_transferInto"><input class="input1" type="text" id="payeeCardNo" name="payeeCardNo" value="${moneyOperation.payeeCardNo}"/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>付款方式：</td>
				<td><select class="input1" id="outType" name="outType" onchange="showTransferOut(this);">
				     <option value="2" <c:if test="${moneyOperation.outType == 2}">selected</c:if>>银行转账</option>
				     <option value="1" <c:if test="${moneyOperation.outType == 1}">selected</c:if>>现金</option>
				</select></td>
					<td align="right" width="25%"><font color="red">*</font>收款方式：</td>
				<td ><select class="input1" name="inType" id="inType"  onchange="showTransferInto(this);">
				     <option value="2" <c:if test="${moneyOperation.inType == 2}">selected</c:if>>银行转账</option>
				     <option value="1" <c:if test="${moneyOperation.inType == 1}">selected</c:if>>现金</option>
				     
				</select></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>备注：</td>
				<td colspan="3">
				 <textarea cols="100" rows="5" style="width:100%;height:80px;" id="remark" name="remark">${moneyOperation.remark}</textarea>
				</td>
			</tr>
			
			<tr>
				<td align="center" colspan="4"><input type="button" id="saveBtn" name="saveBtn" onclick="add(1);" value="保存" />
				<input type="button" id="subBtn" name="subBtn" onclick="add(2);" value="提交" />
				</td>
			</tr>	
		</table>
		<!-- 转入人 -->
		<input type="hidden" id="payeeName" name="payeeName" value="${moneyOperation.payeeName}">
		<!-- 转出人 -->
		<input type="hidden" id="payerName" name="payerName" value="${moneyOperation.payerName}">			
</form>
</body>
<script type="text/javascript">
	$(function() {
		showTransferInto("#inType");
		showTransferOut("#outType");
	});
	$("#operationrMoney").on('keyup', function (event) {
	    var $amountInput = $(this);
	    //响应鼠标事件，允许左右方向键移动 
	    event = window.event || event;
	    if (event.keyCode == 37 | event.keyCode == 39) {
	        return;
	    }
	    //先把非数字的都替换掉，除了数字和. 
	    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
	        //只允许一个小数点              
	        replace(/^\./g, "").replace(/\.{2,}/g, ".").
	        //只能输入小数点后两位
	        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
	            });
	$("#amount").on('blur', function () {
	    var $amountInput = $(this);
	    //最后一位是小数点的话，移除
	    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
	});
	function getInfoByPayerId() {
		var payer = $("#outBankAccountId").find("option:selected").val();
		var urlPath = '${path}/finance/interTransfer/getInfoById/' + payer + '.html';
		$.ajax({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#payerBankId").attr("value", result.bankId);
				$("#payerSubbranch").val(result.subbranch);
				$("#payerCardNo").val(result.cardNo);
			},
			error : function(data) {
				layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
			}
		});
	}

	function getInfoByPayeeId() {
		var payee = $("#inBankAccountId").find("option:selected").val();
		var urlPath = '${path}/finance/interTransfer/getInfoById/' + payee + '.html';
		$.ajax({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#payeeBankId").attr("value", result.bankId);
				$("#payeeSubbranch").val(result.subbranch);
				$("#payeeCardNo").val(result.cardNo);
			},
			error : function(data) {
				layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
			}
		});
	}

	function add(type) {

		if (!check()) {
			return;
		}
		var payer = $("#outBankAccountId").find("option:selected").text();
		var payee = $("#inBankAccountId").find("option:selected").text();
		$("#payerName").val(payer);
		$("#payeeName").val(payee);
		var _load = layer.load('处理中..');
		var urlPath = '${path}/finance/interTransfer/applyFor/' + type + '.html';
		$("#approForm").ajaxSubmit({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.close(_load);
				layer.msg(result.message, 1, 1);
				parent.window.location.href = "${path}/finance/interTransfer/main.html";
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				parent.layer.close(index);
			},
			error : function(data) {
				layer.msg(result.message, 1, 5);
			}
		});

    }	
    
    //效验数据
    function check(){
    	var reg = /^\d+\.?\d{0,2}$/; 
    	var regNum = /^\d*$/;
    	var operationrMoney= $("#operationrMoney").val();
    	var payerSubbranch= $("#payerSubbranch").val();
    	var payeeCardNo= $("#payeeCardNo").val();
    	var payeeSubbranch= $("#payeeSubbranch").val();
    	var payerCardNo= $("#payerCardNo").val();
    	var payerName=$("#payerName").val();
    	var payeeName=$("#payeeName").val();
    	var payeeBankId=$("#payeeBankId").val();
    	var payerBankId=$("#payerBankId").val();
    	var remark= $("#remark").val();
    	var businessTime= $("#businessTime").val();
    	var outType= $("#outType").val();
    	var inType= $("#inType").val();
    	
    	
    	if ($.trim(operationrMoney) == "" || operationrMoney<=0) {
    		layer.msg('转账金额不能为空或者必须大于0！', 1, 5);
    		$("#operationrMoney").focus();
    		return false;
    	}
    	
    	if (!reg.test(operationrMoney)) {
    		layer.msg('转账金额不合法', 1, 5);
    		$("#operationrMoney").focus();
    		return false;
    	}
    	
    	if ($.trim(payerName) == "") {
    		layer.msg('请输入转出人', 1, 5);
    		$("#payerName").focus();
    		return false;
    	}
    	   	
    	if(outType==2){
  		
    		if ($.trim(payerBankId) == "") {
        		layer.msg('请输入转出人银行', 1, 5);
        		$("#payerBankId").focus();
        		return false;
        	}
    		if ($.trim(payerSubbranch) == "") {
        		layer.msg('请输入转出支行', 1, 5);
        		$("#payerSubbranch").focus();
        		return false;
        	}
        	if ($.trim(payerCardNo) == "") {
        		layer.msg('请输入转出银行卡号', 1, 5);
        		$("#payerCardNo").focus();
        		return false;
        	}
        	
        	if (!regNum.test(payerCardNo)) {
        		layer.msg('转出银行卡号不合法', 1, 5);
        		$("#payerCardNo").focus();
        		return false;
        	}
		
    	}
    	
    	if ($.trim(payeeName) == "") {
    		layer.msg('请输入转入人', 1, 5);
    		$("#payeeName").focus();
    		return false;
    	} 	   	
    	
    	if(inType==2){
    		if ($.trim(payeeBankId) == "") {
        		layer.msg('请输入转入人银行', 1, 5);
        		$("#payeeBankId").focus();
        		return false;
        	}
    		if ($.trim(payeeSubbranch) == "") {
        		layer.msg('请输入转入支行', 1, 5);
        		$("#payeeSubbranch").focus();
        		return false;
        	}
        	if ($.trim(payeeCardNo) == "") {
        		layer.msg('请输入转入银行卡号', 1, 5);
        		$("#payerCardNo").focus();
        		return false;
        	}
        	
        	if (!regNum.test(payeeCardNo)) {
        		layer.msg('转入银行卡号不合法', 1, 5);
        		$("#payeeCardNo").focus();
        		return false;
        	}
    	}
    	
    	if ($.trim(businessTime) == "") {
    		layer.msg('请输入业务发生时间', 1, 5);
    		$("#businessTime").focus();
    		return false;
    	}
    	if ($.trim(remark) == "") {
    		layer.msg('请输入备注', 1, 5);
    		$("#remark").focus();
    		return false;
    	}
    	return true;
    }
    
	//效验数据
	function check() {
		var reg = /^\d+\.?\d{0,2}$/;
		var regNum = /^\d*$/;
		var operationrMoney = $("#operationrMoney").val();
		var payerSubbranch = $("#payerSubbranch").val();
		var payeeCardNo = $("#payeeCardNo").val();
		var payeeSubbranch = $("#payeeSubbranch").val();
		var payerCardNo = $("#payerCardNo").val();
		var outBankAccountId = $("#outBankAccountId").val();
		var inBankAccountId = $("#inBankAccountId").val();
		var payeeBankId = $("#payeeBankId").val();
		var payerBankId = $("#payerBankId").val();
		var remark = $("#remark").val();
		var businessTime = $("#businessTime").val();
		var outType = $("#outType").val();
		var inType = $("#inType").val();

		if ($.trim(operationrMoney) == "") {
			layer.msg('请输入汇款金额', 1, 5);
			$("#operationrMoney").focus();
			return false;
		}

		if (!reg.test(operationrMoney)) {
			layer.msg('汇款金额不合法', 1, 5);
			$("#operationrMoney").focus();
			return false;
		}

		if ($.trim(outBankAccountId) == "") {
			layer.msg('请输入付款人', 1, 5);
			$("#outBankAccountId").focus();
			return false;
		}

		if (outType == 2) {

			if ($.trim(payerBankId) == "") {
				layer.msg('请输入付款银行', 1, 5);
				$("#payerBankId").focus();
				return false;
			}
			if ($.trim(payerSubbranch) == "") {
				layer.msg('请输入付款支行', 1, 5);
				$("#payerSubbranch").focus();
				return false;
			}
			if ($.trim(payerCardNo) == "") {
				layer.msg('请输入付款银行卡号', 1, 5);
				$("#payerCardNo").focus();
				return false;
			}

			if (!regNum.test(payerCardNo)) {
				layer.msg('付款银行卡号不合法', 1, 5);
				$("#payerCardNo").focus();
				return false;
			}

		}

		if ($.trim(inBankAccountId) == "") {
			layer.msg('请输入收款人', 1, 5);
			$("#inBankAccountId").focus();
			return false;
		}

		if (inType == 2) {
			if ($.trim(payeeBankId) == "") {
				layer.msg('请输入收款银行', 1, 5);
				$("#payeeBankId").focus();
				return false;
			}
			if ($.trim(payeeSubbranch) == "") {
				layer.msg('请输入收款支行', 1, 5);
				$("#payeeSubbranch").focus();
				return false;
			}
			if ($.trim(payeeCardNo) == "") {
				layer.msg('请输入收款银行卡号', 1, 5);
				$("#payerCardNo").focus();
				return false;
			}

			if (!regNum.test(payeeCardNo)) {
				layer.msg('收款银行卡号不合法', 1, 5);
				$("#payeeCardNo").focus();
				return false;
			}
		}

		if ($.trim(businessTime) == "") {
			layer.msg('请输入业务发生时间', 1, 5);
			$("#businessTime").focus();
			return false;
		}
		if ($.trim(remark) == "") {
			layer.msg('请输入备注', 1, 5);
			$("#remark").focus();
			return false;
		}
		return true;
	}

	function showTransferInto(dom) {
		if ($(dom).val() == 1) {
			$(".J_transferInto").hide();
			$("#payeeSubbranch").val('');
			$("#payeeCardNo").val('');
			$("#payeeBankId").val('');
		} else {
			$(".J_transferInto").show();
			getInfoByPayeeId();
		}
	}

	function showTransferOut(dom) {
		if ($(dom).val() == 1) {
			$(".J_transferOut").hide();
			$("#payerSubbranch").val('');
			$("#payerCardNo").val('');
			$("#payerBankId").val('');
		} else {
			$(".J_transferOut").show();
			getInfoByPayerId();
		}
	}
</script>
</html>
