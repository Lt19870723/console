<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—奖励管理</title>
</head>
<body style="background: #f9f9f9;">
 
	<form id="loginLogForm" action="" method="post">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
					<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号  </th>
			<th>风险备用金金额   </th>
			<th>操作 </th>
		</tr>
		<tr class='tr_1'>
			<td>1</td>
			<td>${riskMargin.account}</td>
			<td>
				<a href="javascript:showAuditLayer();">修改</a>
			</td>
		</tr>
	</table>
				</div>
		</div>
		</div>
	</form>

</body>
<script type="text/javascript">

	function showAuditLayer(){
		var _url = '${path}/account/riskMargin/initRisk.html';
		$.layer({
			type : 2,
			title : '风险备用金修改',
			area : [ '500px', '300px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
	}
	
</script>
</html>
