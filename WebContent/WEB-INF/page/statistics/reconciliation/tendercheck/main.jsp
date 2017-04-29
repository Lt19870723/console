<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			统计日期：
				<input name="beginTotalTime" id="beginTotalTime" onclick="WdatePicker()" styleClass="Wdate" />
			&nbsp;	
			 <input type="button" onclick="javascript:query();" type="button" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
		</div>
	</form>

	<div id="list" class="main_cent"></div>

</body>
<script type="text/javascript">
	
	function pageGo() {
		
		var beginTime = $('#beginTotalTime').val();
		if(null==beginTime || ""==beginTime){
			alert("请选择日期");
			return false;
		}
		
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/statistics/tendercheckinfo/tendercheckcount.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				parentLayer.close(_load);
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
