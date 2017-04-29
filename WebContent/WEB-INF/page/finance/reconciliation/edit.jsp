<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>提现核对-国诚金融后台管理系统</title>
</head>
<body>
	<form id="approForm" name="" method="post">
		<input type="hidden" id="id" name="id" value="${checkWithdrawals.id}" />
		<input type="hidden" id="difference" name="difference" />
		<input type="hidden" id="isSuccess" name="isSuccess" />
		<div class="listzent"></div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
			<tr>
				<td align="right" width="25%"><font color="red"></font>日期：</td>
				<td><fmt:formatDate value="${checkWithdrawals.billDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red"></font>净提现总额(元)：</td>
				<td><fmt:formatNumber value="${checkWithdrawals.presentSuccessMoney}" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red"></font>提现支出总额(元)：</td>
				<td><fmt:formatNumber value="${checkWithdrawals.takeCashMoney}" pattern="#,##0.00"/></td>
				</tr>
			<tr>
				<td align="right" width="25%"><font color="red"></font>实际打款总额(元)：</td>
				<td><fmt:formatNumber pattern="#,##0.00" value="${checkWithdrawals.actualCashMoney}"/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>网银在线划出金额(元)：</td>
				<td><input class="input1" type="text" id="totalExpenditure" name="totalExpenditure" value="${checkWithdrawals.totalExpenditure}"/></td>
				</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>手续费(元)：</td>
				<td>
					<input class="input1" type="text" id="counterFee" name="counterFee" value="${checkWithdrawals.counterFee}"/>
					<input type="button" value="核对" onclick="updateDifference();">
				</td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red"></font>差异金额(元)：</td>
				<td>
					<span id="J_difference">
						<c:if test="${checkWithdrawals.difference == null}">0.00</c:if>
						<c:if test="${checkWithdrawals.difference != null}">${checkWithdrawals.difference}</c:if>
					</span>
					<span class="J_difference_text" style="color: red;"></span>
				</td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red">*</font>差异说明：</td>
				<td><textarea cols="80" rows="5" style="width:100%;height:80px;" id="remarks" name="remarks">${checkWithdrawals.remarks}</textarea></td>
			</tr>
			
			<tr>
				<td align="center" colspan="4">
					<input id="btn" type="button" style="display: none;" onclick="updateCheckWithdrawals();" value="保存" />
				</td>
			</tr>	
		</table>
	</form>
</body>
<script type="text/javascript">
	$("#totalExpenditure").on('keyup', function(event) {
		document.getElementById('btn').style.display='none'; 
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

	$("#counterFee").on('keyup', function(event) {
		document.getElementById('btn').style.display='none';  
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

	function updateDifference() {
		var result = 0.00;
		var id = '${checkWithdrawals.id}';
		var actualCashMoney = '${checkWithdrawals.actualCashMoney}';// 实际打款金额
		var totalExpenditure = $("#totalExpenditure").val(); // 网银划出金额
		var counterFee = $("#counterFee").val(); // 手续费

		// 差异 = 实际打款总额 - 平台划出金额 - 手续费
		if (totalExpenditure == '' || counterFee == '') {
			layer.alert("网银划出金额或手续费不能为空");
			return;
		}

		if (totalExpenditure != '' && counterFee != '') {
			result = actualCashMoney - totalExpenditure - counterFee;
		}
		$.ajax({
			url : "${path}/finance/checkWithdrawals/checkWithdrawalsById.html",
			type : 'post',
			dataType : 'json',
			data : {
				id : id,
				totalExpenditure : totalExpenditure,
				counterFee : counterFee
			},
			success : function(result) {
				document.getElementById('btn').style.display='';  
				if (result.result == false) {
					$(".J_difference_text").text("差异不为0，对账失败。");
				} else {
					$(".J_difference_text").text("");
				}
				result = result.difference;
			},

		});
		$("#difference").val(result);
		$("#J_difference").html(result.toFixed(2));
	}

	function updateCheckWithdrawals() {
		if (!check()) {
			return;
		}
		var totalExpenditure = $("#totalExpenditure").val(); // 网银划出金额
		var counterFee = $("#counterFee").val(); // 手续费
		var difference = $("#difference").val();
		var status = 2;
		$.ajax({
			url : "${path}/finance/checkWithdrawals/checkWithdrawalsById.html",
			type : 'post',
			dataType : 'json',
			data : {
				id : $("#id").val(),
				totalExpenditure : totalExpenditure,
				counterFee : counterFee
			},
			success : function(result) {
				if (result.result == false) {
					status = 2;
				} else {
					status = 1;
				}
				if (difference == '') {
					difference = result.difference;
				}

				$("#isSuccess").val(status);
				$("#difference").val(difference);
				var _load = layer.load('处理中..');
				var urlPath = '${path}/finance/checkWithdrawals/updateCheckWithById.html';
				layer.confirm("确认保存吗？", function(index){ 
				$("#approForm").ajaxSubmit({
					url : urlPath,
					type : 'post',
					dataType : 'json',
					success : function(result) {
						if (result.code == '0') {
							layer.alert(result.message);
						} else {
							layer.close(_load);
							layer.msg(result.message, 1, 1,function(){
								parent.layer.closeAll();
								parent.window.location.href = "${path}/finance/checkWithdrawals/main.html"; 
							});

						}

					},
					error : function(data) {
						layer.msg(result.message, 1, 5);
					}
				});
				}); 
			},
			error : function(data) {
				layer.msg(result.message, 1, 5);
			}
		});

	}

	//效验数据
	function check() {
		var reg = /^\d+\.?\d{0,2}$/;

		var totalExpenditure = $("#totalExpenditure").val(); // 网银划出金额
		var counterFee = $("#counterFee").val(); // 手续费
		var remarks = $("#remarks").val();

		if ($.trim(totalExpenditure) != '') {
			if (!reg.test(totalExpenditure)) {
				layer.msg('网银在线划出金额不合法', 1, 5);
				$("#totalExpenditure").focus();
				return false;
			}
		}else{
			layer.msg('网银划出金额不能为空!', 1, 5);
			$("#totalExpenditure").focus();
			return false;
		}

		if ($.trim(counterFee) != '') {
			if (!reg.test(counterFee)) {
				layer.msg('手续费不合法', 1, 5);
				$("#counterFee").focus();
				return false;
			}
		}else{
			layer.msg('手续费不能为空!', 1, 5);
			$("#counterFee").focus();
			return false;
		}

		if ($.trim(remarks) == '') {
			layer.msg('差异说明不能为空', 1, 5);
			$("#remarks").focus();
			return false;
		}

		return true;
	}
</script>
</html>
