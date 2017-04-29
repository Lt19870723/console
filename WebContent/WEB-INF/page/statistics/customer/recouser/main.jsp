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
	<form id="queryForm" action="" method="post" style="width: 1400px;">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
			推荐人：<input id="userName" class="input1" name="userName"/>
			&nbsp;
			被推荐：<input id="invitedUsername" class="input1" name="invitedUsername"/>
			&nbsp;
			<br/>
			是否推荐成功 ：
				 <select id="isRecommendSuccess" name="isRecommendSuccess"
					class="bigselect" style="width: 130px;">
					<option value="">--请选择--</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				<br/>
				
			被推荐人注册时间：
				<input
					name="beginTime" id="beginTime" onclick="WdatePicker()"
					styleClass="Wdate" />
				 &nbsp;至 &nbsp; 
				<input name="endTime" id="endTime"
					onclick="WdatePicker()" styleClass="Wdate" />
				
			<br/>
			推荐成功时间：
			<input name="successbeginTime" id="successbeginTime" onclick="WdatePicker()" styleClass="Wdate" />
				 &nbsp;至 &nbsp; 
			<input name="successendTime" id="successendTime" onclick="WdatePicker()" styleClass="Wdate" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">

	
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/customer/recommentuser/list/' + pageNo + '.html',
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
	
	/**
	*查询功能
	*/
	function query(){
		pageGo(1);
	}
	
</script>
</html>
