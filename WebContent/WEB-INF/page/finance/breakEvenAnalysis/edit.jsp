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
		<input type="hidden" id="id" name="id" value="${breakEvenAnalysis.id}" />
		<input type="hidden" id="difference" name="difference" />
		<input type="hidden" id="isSuccess" name="isSuccess" />
		<div class="listzent"></div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
			<tr>
				<td align="right" width="25%"><font color="red"></font>日期：</td>
				<td><fmt:formatDate value="${breakEvenAnalysis.time}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red"></font>到期本息（包含直通车）</td>
				<td><fmt:formatNumber value="${breakEvenAnalysis.allPrincipal}" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td align="right" width="25%"><font color="red"></font>预期提现率：</td>
				<td> <input class="input1" type="text" id="totalExpenditure" name="totalExpenditure" value="${breakEvenAnalysis.expectRate}"/>% </td>
				</tr>
			<tr>
				<td align="center" colspan="4">
					<input id="btn" type="button"  onclick="updateCheckWithdrawals();" value="保存" />
				</td>
			</tr>	
		</table>
	</form>
</body>
<script type="text/javascript">
	function updateCheckWithdrawals() {
		if (!check()) {
			return;
		}
		var expectRate = $("#totalExpenditure").val(); // 预期提现率
		var id ="${breakEvenAnalysis.id}";
		var allPrincipal="${breakEvenAnalysis.allPrincipal}";
		var urlPath = '${path}/finance/breakEvenAnalysis/updateExpectRate.html';
		var _load = layer.load('处理中..');
		layer.confirm("确认保存吗？", function(index){ 
			$("#approForm").ajaxSubmit({
				url : urlPath,
				type : 'post',
				dataType : 'json',
				data : {
					id : id,
					expectRate : expectRate,
					allPrincipal : allPrincipal
				},
				success : function(result) {
					if (result.code == '0') {
						layer.alert(result.message);
					} else {
						layer.close(_load);
						layer.msg(result.message, 1, 1,function(){
							parent.window.location.href = "${path}/finance/breakEvenAnalysis/main.html"; 
							parent.layer.closeAll();
						});

					}

				},
				error : function(data) {
					layer.msg(result.message, 1, 5);
				}
			});
			}); 

	}

	//效验数据
	function check() {
		var reg = /^\d+\.?\d{0,2}$/;
		var totalExpenditure = $("#totalExpenditure").val(); 
		if ($.trim(totalExpenditure) != '') {
			if (!reg.test(totalExpenditure)) {
				layer.msg('预期提现率不合法', 1, 5);
				$("#totalExpenditure").focus();
				return false;
			}
		}else{
			layer.msg('预期提现不能为空!', 1, 5);
			$("#totalExpenditure").focus();
			return false;
		}
		return true;
	}
</script>
</html>
