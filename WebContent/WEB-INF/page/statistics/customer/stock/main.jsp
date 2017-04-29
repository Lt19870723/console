<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—用户分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 104%">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
			用户名：<input id="username" class="input1" name="username" style="width: 150px"/>
			&nbsp;
			真实名称：<input id="realname" class="input1" name="realname" style="width: 150px"/>
			&nbsp;
			期权状态 ：
				 <select id="status" name="status"
					class="bigselect" style="width: 130px;">
					<option value="">--不限--</option>
					<option value="1">期权持有中</option>
					<option value="2">已现金行权</option>
				</select>
				&nbsp;
			减仓超九成：
				 <select id="isReduce" name="isReduce"
					class="bigselect" style="width: 100px;">
					<option value="">--不限--</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
				&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
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
			url : '${path}/statistics/stock/list/'+pageNo+'.html',
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
	
	function showstock(id,isReduce){
		if(null==isReduce || ""==isReduce){
			return;
		}
		if(!confirm("确认现金行权吗？")){
			return;
		}
		
		if(isReduce==0){
			if(!confirm('该用户没有减仓超九成，确认强制现金行权吗？')){
				return;
			}
		}
		
		$("#queryForm").ajaxSubmit ({
			url : '${path}/statistics/stock/exercise/'+id+'.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						pageGo(1);
					});
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			} 
		});
		
	}
	
</script>
</html>
