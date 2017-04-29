<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—定期宝统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
		<div id="loginLogCard">
			<div>
			<div style="margin-left:20px;line-height:50px;">
			          统计日期:
				<input name="beginTime" id="beginTime" onclick="WdatePicker()" styleClass="Wdate" />
				 &nbsp;至 &nbsp;
				<input name="endTime" id="endTime" onclick="WdatePicker()" styleClass="Wdate" />
				&nbsp;
				定期宝期限 ：
				<select id="lockLimit" name="lockLimit" >
					<option value="">--请选择--</option>
					<c:forEach items="1,3,6,12" var="f">
						<option value="${f }">${f }月宝</option>
					</c:forEach>
					<option value="100000000">新手宝</option>
				</select>
			<input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
		</div>
	</form>

	<div id="fixList" class="main_cent"></div>

</body>
<script type="text/javascript">
	
	function pageGo() {	
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/fix/fixAnalysis/list.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#fixList").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	/**
	*查询功能
	*/
	function query(){
		pageGo();
	}
	
</script>
</html>
