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
			用户名称：<input  id="userName"class="input1" name="userName" />
			&nbsp;
			状态：	 
				  <select name="status" id="status" 
					class="bigselect" style="width:130px;">
				  		<option value="10">--请选择--</option>
				  		<option value="0">加入</option>
	                    <option value="1">收益中</option>
	                    <option value="2">已撤销</option>
	                    <option value="3">已退出</option>
				  </select>
			<br>
			加入时间：
				<input
					 name="beginTimeStr" id="beginTime"  onclick="WdatePicker()"
					styleClass="Wdate" >
					 
				</input>
				  &nbsp;至&nbsp; 
				<input name="endTimeStr" id="endTime" 
					onclick="WdatePicker()" styleClass="Wdate">
					 
				</input>
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
			url : '${path}/fix/joinFixBorrow/list/' + pageNo + '.html',
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
