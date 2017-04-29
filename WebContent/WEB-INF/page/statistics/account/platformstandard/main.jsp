<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—资金分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			统计日期：
				<input name="beginTimeTotal" id="beginTimeTotal" onclick="WdatePicker()" styleClass="Wdate" />&nbsp;至&nbsp;
				<input name="endTimeTotal" id="endTimeTotal" onclick="WdatePicker()" styleClass="Wdate" />
			&nbsp;	
			 <input type="button" onclick="javascript:query();" type="button" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
		</div>
	</form>

	<div id="list" class="main_cent"></div>

</body>
<script type="text/javascript">
	
	function pageGo() {
		
		var beginTime = $('#beginTimeTotal').val();
		if(null==beginTime || ""==beginTime){
			alert("请选择查询开始时间");
			return false;
		}
		
		var endTime = $('#endTimeTotal').val();
		if(null==endTime || ""==endTime){
			alert("请选择查询结束时间");
			return false;
		}
		
		 var d1 = new Date(beginTime.replace(/\-/g, "\/"));  
		 var d2 = new Date(endTime.replace(/\-/g, "\/"));  
		 if( d1 >d2)  
		 {
			  alert("开始时间不能大于结束时间！");  
			  return false;  
		 }
		
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/statistics/platformstandardcount/normarlcount.html';
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
