<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—借款终审</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" >
	   
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			定期宝编号：<input id="contractNo" class="input1" name="contractNo" />
			&nbsp;
			回款日期：
			<input  name="beginDate" id="beginTime"   onclick="WdatePicker()" styleClass="Wdate" />
					 &nbsp;至&nbsp; 
			<input name="endDate" id="endTime"  onclick="WdatePicker()"  styleClass="Wdate"/>
			状态： <select  name="status" id="status"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
	                    <option value="5">收益中</option>
	                    <option value="7">已退出</option>
				  </select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			&nbsp;
			<input type="button"
				   onclick="javascript:exportCallBackData();" type="button" name="Submit1"
				   id="export" class="b_buts" value="导出EXCEL" />
			<br/>
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
		url : '${path}/fix/interestForFix/list/' + pageNo + '.html',
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
function exportCallBackData(){
	$("#queryForm").ajaxSubmit ({
		url : '${path}/fix/interestForFix/count.html',
		type : 'post' ,
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				exportData();
			}else{
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		}
	});
}

function exportData(){
	$("#queryForm").attr("action","${path}/fix/interestForFix/export.html");
	$("#queryForm").submit();
}
</script>
</html>
