<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>添加现金日记账-国诚金融后台管理系统</title>
</head>
<body>
	<form id="approForm" name="" method="post">
		<input type="hidden" id="id" name="id" value="${journal.id}" />
		<div class="listzent">现金日记账(*必填)</div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
			<tr>
				<td align="right" width="15%"><font color="red">*</font>操作日期：</td>
				<td><input class="input1" type="text" id="operationDate" name="operationDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', maxDate:'%y-%M-%d'});" value='<fmt:formatDate value="${journal.operationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'/></td>
				<td align="right" width="15%"><font color="red">*</font>借/贷：</td>
				<td>
					<input type="radio" id="debitRadio" name="debitOrCredit" checked="checked" value="1" onclick="changeAttr(this);"/>借
					<input type="radio" id="creditRadio" name="debitOrCredit" value="2" onclick="changeAttr(this);"/>贷
				</td>
			</tr>
			<tr>
				<td align="right" width="15%" class="J_debitAmount"><font color="red">*</font>借(收入)方(元)：</td>
				<td class="J_debitAmount"><input class="input1" type="text" id="debitAmount" name="debitAmount" value="${journal.debitAmount}"/></td>
				<td align="right" width="15%" class="J_creditAmount"><font color="red">*</font>贷(支出)方(元)：</td>
				<td class="J_creditAmount"><input class="input1" type="text" id="creditAmount" name="creditAmount" value="${journal.creditAmount}"/></td>
			</tr>
			<tr>
				<td align="right" width="15%"><font color="red">*</font>摘要：</td>
				<td colspan="3"><textarea cols="100" rows="5" style="width:100%;height:80px;" id="remark" name="remark">${journal.remark}</textarea></td>
			</tr>
			
			<tr>
				<td align="center" colspan="4"><input type="button" id="saveBtn" name="saveBtn" onclick="saveJournal();" value="保存" /></td>
			</tr>	
		</table>
	</form>
</body>
<script type="text/javascript">
	
	$(function() {
		var id = '${journal.id}';
		var debitAmount = '${journal.debitAmount}';
		var creditAmount = '${journal.creditAmount}';
		if (id != '') {
			if (debitAmount != null && debitAmount != '') {
				$(".J_debitAmount").show();
				$(".J_creditAmount").hide();
				$("#creditAmount").val('');
				$("#creditRadio").removeAttr("checked");
				$("#debitRadio").attr('checked', true);
			} else if (creditAmount != null && creditAmount != '') {
				$(".J_debitAmount").hide();
				$(".J_creditAmount").show();
				$("#debitAmount").val('');
				$("#debitRadio").removeAttr("checked");
				$("#creditRadio").attr('checked', true);
			}
		} else {
			if ($("input[name='debitOrCredit']:checked").val() == 1) {
				$(".J_debitAmount").show();
				$(".J_creditAmount").hide();
				$("#creditAmount").val('');
			} else {
				$(".J_debitAmount").hide();
				$(".J_creditAmount").show();
				$("#debitAmount").val('');
			}
		}

	});

	function changeAttr(dom) {

		if ($(dom).val() == 1) {
			$(".J_debitAmount").show();
			$(".J_creditAmount").hide();
			$("#creditAmount").val('');
		} else {
			$(".J_debitAmount").hide();
			$(".J_creditAmount").show();
			$("#debitAmount").val('');
		}

	}

	$("#debitAmount").on('keyup', function(event) {
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

	$("#creditAmount").on('keyup', function(event) {
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

	function saveJournal() {
		if (!check()) {
			return;
		}
		var _load = layer.load('处理中..');
		var urlPath = '${path}/finance/journal/saveOrUpdateJournal.html';
		$("#approForm").ajaxSubmit({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.close(_load);
					layer.msg(result.message, 1, 1);
					parent.window.location.href = "${path}/finance/journal/journalMain.html";
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
				}

			},
			error : function(data) {
				layer.msg(result.message, 1, 5);
			}
		});
	}
	//效验数据
	function check() {
		var reg = /^\d+\.?\d{0,2}$/;
		var regNum = /^\d*$/;

		var operationDate = $("#operationDate").val();
		var debitAmount = $("#debitAmount").val();
		var creditAmount = $("#creditAmount").val();
		var remark = $("#remark").val();

		if ($.trim(operationDate) == "") {
			layer.alert('请输入操作时间');
			$("#operationDate").focus();
			return false;
		}

		if($("input[name='debitOrCredit']:checked").val() == 1){
			if ($.trim(debitAmount) != '') {
				if (!reg.test(debitAmount)) {
					layer.alert('借(收入)方金额不合法');
					$("#debitAmount").focus();
					return false;
				}
			} else {
				layer.alert("借方金额不能为空");
				return false;
			}
		} else if($("input[name='debitOrCredit']:checked").val() == 2){
			if ($.trim(creditAmount) != '') {
				if (!reg.test(creditAmount)) {
					layer.alert('贷(支出)方金额不合法');
					$("#creditAmount").focus();
					return false;
				}
			} else {
				layer.alert("贷方金额不能为空");
				return false;
			}
		}

		if ($.trim(remark) == "") {
			layer.alert('请输入备注');
			$("#remark").focus();
			return false;
		}
		return true;
	}
</script>
</html>
