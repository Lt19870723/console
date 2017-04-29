<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>活期宝统计-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post" >
		<div style="margin-left:20px;line-height:50px;">

			截止时间：
			<input type="text"
				name="eaddtime" id="eaddtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
				class="Wdate"  />
			&nbsp;
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;
			<br/>
			累计存量: <span id="bdSumMoneySy"></span>
			&nbsp;元
			<br />
			<hr />
			<div style="color:red;">
				统计说明: <br /> 累计存量 = 转入累计(含活期生息的金额) - 转出累计
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function query() {
		var _load = layer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/curaccount/curaccountstatis/sum.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#bdSumMoneySy").html(result["bdSumMoneySy"]);
				layer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
</script>
</html>