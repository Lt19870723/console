<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" >
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			用户名：<input type="text" id="userName" name="userName">
			
			&nbsp;
			处理结果 ：
				  <select id="status" name="status"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="0">未处理</option>
	                    <option value="1">已处理</option>
				  </select>
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				发送时间：  <input name="beginDate" id="beginDate" onclick="WdatePicker()" styleClass="Wdate" />
				 &nbsp;至 &nbsp; 
				<input name="endDate" id="endDate" onclick="WdatePicker()" styleClass="Wdate" />
				<br>
				业务发送场景: <input name="scene" type="text" id="scene">
				 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					 					 
			 
			<br />  
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
	$("#queryForm").ajaxSubmit ({
		url : '${path}/accountError/findAccountError/' + pageNo + '.html',
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


function showAuditLayer(id){
	var _url = '${path}/accountError/toDispose.html';
	if (id != '') {
		_url += '?id=' + id;
	}
	$.layer({
		type : 2,
		title : '审核',
		area : [ '50%', '50%' ],
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