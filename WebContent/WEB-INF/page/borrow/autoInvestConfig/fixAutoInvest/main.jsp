<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—自动投宝规则</title>
</head>
<body style="background: #f9f9f9;">
	<div class="navigation">借款管理>自动投资>自动投宝规则</div>
	<form id="queryForm" action="" method="post">
		<div id="loginLogCard" class="queryDiv">
		   	用户名：<input name="username" id="username" class="input1" /> 
			状态： 
			<select id="status" name="status" class="bigselect">
				<option value="">--请选择--</option>
				<option value="0">未启用</option>
				<option value="1" selected="selected">已启用</option>
				<option value="-1">已删除</option>
			</select>
			投宝方式： 
			<select id="autoTenderType" name="autoTenderType" class="bigselect">
				<option value="">--请选择--</option>
				<option value="1">按金额投宝</option>
				<option value="2">按账户余额投宝</option>
			</select>
			定期宝期限 ：
			<select id="fixLimit" name="fixLimit" class="bigselect" >
				<option value="">--请选择--</option>
				<c:forEach items="1,3,6,12" var="f">
					<option value="[${f }]">${f }月宝</option>
				</c:forEach>
			</select>
			<input type="button" onclick="pageGo(1)" type="button" name="Submit1" id="subbtn" class="b_buts" value="查   询" /> <br />
		</div>
	</form>
	<div id="loginLogList" class="main_cent"></div>
</body>
<script type="text/javascript">
	$(function() {
		pageGo(1);
	});

	/**
	 *翻页功能
	 */
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit({
			url : '${path}/borrow/autoInvestConfig/fixautoInvest/list/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#loginLogList").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
</script>
</html>
