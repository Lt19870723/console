<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>日志记录-国诚金融后台管理系统</title>
</head>
<body>
	<table>
		<tr>
			<td width="100px">备注说明：</td>
			<td width="550px">
				${firstTenderLogVo.remark}
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="closeBtn" name="closeBtn" onclick="closeMe();" value="关闭" />	
			</td>					
		</tr>
	</table>
</body>
<script type="text/javascript">
	function closeMe() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
</script>
</html>