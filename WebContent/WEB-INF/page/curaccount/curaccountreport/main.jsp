<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>活期宝收益对账-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post" >
		<input type="hidden" id="lastEndTime" name="lastEndTime" value='<fmt:formatDate value="${lastEndTime}" pattern="yyyy-MM-dd" />' />
		<div style="margin-left:20px;line-height:50px;">

			时间选项：
			<input type="text"
				name="date" id="date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'lastEndTime\')}'})" class="Wdate"  />
			&nbsp;
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;
			<br/>
		</div>
		
		<div class="fulltable" style="width: 60%;">
			<div style="width: 590px;border-bottom-width: 1">&nbsp;&nbsp;&nbsp;统计结果</div>
			<div>
				计算收益个数：&nbsp;&nbsp;<span id="detailCount"></span>
			</div>

			<div>
				收益入账个数：&nbsp;&nbsp;<span id="accountLogCount"></span>
			</div>

			<div>
				收益总额：&nbsp;&nbsp;<span id="detailMoneyTotal"></span>
			</div>

			<div>
				入账总额：&nbsp;&nbsp;<span id="accountLogMoneyTotal"></span>
			</div>

			<div>
				是否存在未计算收益的用户：&nbsp;&nbsp;<span id="isUserNotHaveInterestDetial"></span>
			</div>
			
			<div>
				是否存在未收益入账的用户：&nbsp;&nbsp;<span id="isUserNotHaveAccountLog"></span>
			</div>
			
			<div>
				是否存在重复计算收益：&nbsp;&nbsp;<span id="isRepeatDetail"></span>
			</div>

			<div>
				是否存在重复收益入账：&nbsp;&nbsp;<span id="isRepeatAccountLog"></span>
			</div>

			<div>
				是否存在收益入账出错：&nbsp;&nbsp;<span id="isEnterMoney"></span>
			</div>
			<div>
				收益结算最终结果：&nbsp;&nbsp;<span id="resultMsg"></span>
			</div>
			<div style="color:red;"><br/>
				对账规则：&nbsp;&nbsp;<br/>
				1、计算收益个数必须等于收益入账个数，否则收益结算出错！<br/>
				2、收益总额必须等于入账总额，否则收益结算出错！<br/>
				3、如果存在未计算收益的用户，则收益结算出错！<br/>
				4、如果存在未收益入账的用户，则收益结算出错！<br/>
				5、如果存在重复计算收益，则收益结算出错！<br/>
				6、如果存在重复收益入账，则收益结算出错！<br/>
				7、如果存在收益入账出错，则收益结算出错！<br/>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function query() {
		var date = $("#date").val();
		if(date.length <=0){
			alert("请选择日期!");
			return false;
		}
		
		var _load = layer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/curaccount/curaccountreport/sum.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#detailCount").html(result["detailCount"]);
				$("#accountLogCount").html(result["accountLogCount"]);
				$("#detailMoneyTotal").html(result["detailMoneyTotal"]);
				$("#accountLogMoneyTotal").html(result["accountLogMoneyTotal"]);
				$("#isUserNotHaveInterestDetial").html(result["isUserNotHaveInterestDetial"]);
				$("#isUserNotHaveAccountLog").html(result["isUserNotHaveAccountLog"]);
				$("#isRepeatDetail").html(result["isRepeatDetail"]);
				$("#isRepeatAccountLog").html(result["isRepeatAccountLog"]);
				$("#isEnterMoney").html(result["isEnterMoney"]);
				$("#resultMsg").html(result["resultMsg"]);
				layer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
</script>
</html>