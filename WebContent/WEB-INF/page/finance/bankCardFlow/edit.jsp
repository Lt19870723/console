<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${path }/js/formValid.js?${version}"></script>
</head>
<body>
   <form id="approForm" name="approForm" method="post">
     	<input name="id" value="${flow.id }" type="hidden">
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
			<tr>
				<td align="right" width="25%"><font color="red">*</font>日期：</td>
				<td colspan="3">
					<input class="input1" id="endTime"  name="endTime" dataType="Require" msg="请输入时间"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
					styleClass="Wdate" placeholder="请输入时间" readonly="readonly" value='<fmt:formatDate value="${flow.endTime}" pattern="yyyy-MM-dd"/>'/>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>收支类型：</td>
				<td class="J_transferOut">
					<select class="input1" dataType="Require" msg="请选择收支类型" class="" id="moneyType" name="moneyType">
					  	 <option value="">请选择</option>
					  	 <option value="1" <c:if test="${flow.moneyType==1}">selected="selected"</c:if>>收入</option>
					  	 <option value="-1"<c:if test="${flow.moneyType==-1}">selected="selected"</c:if>>支出</option>
					 </select>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>金额：</td>
				<td class="J_transferOut"><input class="input1 chengeMoney" dataType="Require" msg="请输入金额" type="text" id="money" name="money" value="${flow.money}"/></td>
			</tr>
			
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>分类：</td>
				<td class="J_transferOut">
					<select class="input1" dataType="Require" msg="请选择分类" id="type" name="type">
					  	 <option value="" >请选择</option>
					  	  <c:forEach items="${incomeList}" var="incomeList">
					   		<c:if test="${incomeList.id == flow.type}">
					 			<option value="${incomeList.id}" selected>${incomeList.value}</option>
					 		</c:if>
					 		<c:if test="${incomeList.id != flow.type}">
					 			<option value="${incomeList.id}" >${incomeList.value}</option>
					 		</c:if>
					   	</c:forEach>
					 </select>
					 <input type="button" onclick="addType();" value="新增" />
				</td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>性质：</td>
				<td class="J_transferOut">
					<input type="text" class="input1" name="namure" dataType="Require" msg="请输入性质" id="namure" value="${flow.namure}">
				</td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>明细：</td>
				<td class="J_transferOut">
					<input type="text" class="input1" name="detailed" dataType="Require" msg="请输入明细" id="detailed" value="${flow.detailed}">
				</td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>选择账户：</td>
				<td >
					<select class="input1" dataType="Require" msg="请选择账户" class="input1" id="accountName" name="cardId" onchange="getInfoByPayerId()">
					   <option value="">请选择</option>
					   <c:forEach items="${accountList}" var="accountList">
					   		<c:if test="${accountList.id == moneyOperation.outBankAccountId}">
					 			<option value="${accountList.id}" selected>${accountList.payName}</option>
					 		</c:if>
					 		<c:if test="${accountList.id != moneyOperation.outBankAccountId}">
					 			<option value="${accountList.id}" >${accountList.payName}</option>
					 		</c:if>
					   </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%"  class="J_transferOut">银行卡号：</td>
				<td class="J_transferOut"><input class="input1" type="text" id="bankCard" name="bankCard" value="" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut"><font color="red">*</font>是否有发票：</td>
				<td class="J_transferOut">
					<select class="input1" dataType="Require" msg="请选择是否有发票" id="isInvoice" name="isInvoice" >
					  	 <option value="">请选择</option>
					  	 <option value="1" <c:if test="${flow.isInvoice == 1 }">selected</c:if>>是</option>
					  	 <option value="-1" <c:if test="${flow.isInvoice == -1 }">selected</c:if>>否</option>
					 </select>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%" class="J_transferOut">申请人：</td>
				<td class="J_transferOut">
					<input  type="text" class="input1"  id="applicant" value="${flow.applicant}"/>
					<input  type="hidden" class=""  name="applicant" value="${flow.applicant}"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<input type="button" id="saveBtn" name="saveBtn" onclick="add();" value="保存" />
				</td>
			</tr>	
		</table>
</form>

<div style="display: none;width: 500px;height: 300px;" id="hiddenDiv">
		类型名称:<input type="text" id="typeName" name="typeName" value="">
</div>
</body>
<script type="text/javascript">
$(function(){
	var accId = '${cardId}';
	if(accId != ''){
		$("#accountName option[value='"+accId+"']").attr("selected","selected");
		getInfoByPayerId();
	}
	
	  var isInvoice = '${flow.isInvoice}';
		if(isInvoice==1){
			$("#applicant").attr("disabled","disabled");
		}else{
			$("#applicant").removeAttr("disabled");
		}
	
	$("#isInvoice").change(function(){
		if($(this).val()==1){
			$("#applicant").val('');
			$("#applicant").attr("disabled","disabled");
		}else{
			$("#applicant").val('');
			$("#applicant").removeAttr("disabled");
		}
		
	});
	
});
function add() {
	if (Validator.Validate('approForm', 4) == false) return;
	var _load = parentLayer.load('处理中..');
	var isInvoice = $("#isInvoice").val();
	var applicant = $("#applicant").val();
	if(isInvoice == -1){
		$("input[name=applicant]").val(applicant);
	}else if(isInvoice == 1){
		$("input[name=applicant]").val("");
	}
	$("#approForm").ajaxSubmit({
		url : '${path}/finance/flow/saveFlow.html',
		type : 'post',
		dataType : 'html',
		success : function(result) {
			parentLayer.close(_load);
			var dataObj=eval("("+result+")");
			if(dataObj.isSuccess){
				/* layer.msg("操作成功！", 1, 1);
				parent.layer.closeAll();
				parent.pageGo(1);
				 */
				layer.msg("操作成功！", 1, 1, function() {
					window.parent.location.href = window.parent.location.href;
				});
				
				
			}else{
				layer.msg('保存失败！'+dataObj.message, 1, 5);
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function getInfoByPayerId() {
	var payer = $("#accountName").find("option:selected").val();
	if(payer != ''){
		var urlPath = '${path}/finance/interTransfer/getInfoById/' + payer + '.html';
		$.ajax({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#bankCard").val(result.cardNo);
			},
			error : function(data) {
				layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
			}
		});
	}
}

	$(".chengeMoney").on("keyup", function() {
		  this.value= this.value.replace(/,/g,'');
		  this.value = this.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
		  this.value = this.value.replace(/^\./g, "");  //验证第一个字符是数字而不是.
		  this.value = this.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
		  this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, "$1$2.$3");  //清除小数点后2位之外的数
		  this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	});
	
	
	addType=function(){
		var id= $("input[name=id]").val();
			var _url = "${path}/finance/flow/addType.html?id="+id;
			$.layer({
				type : 2,
				fix : false,
				shade : [ 0 ],
				title : '添加分类',
				area : [ '400px', '300px' ],
				offset : [ '7px', '' ],
				shade : [ 0.1, '#000' ],
				maxmin : true,
				iframe : {
					src : _url
				}
			});

		}
</script>
</html>
