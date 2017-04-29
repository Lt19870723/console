<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			提现时间：
				<input
					name="beginTime" id="beginTime" onclick="WdatePicker()"
					styleClass="Wdate" />
			至
				<input
					name="endTime" id="endTime" onclick="WdatePicker()"
					styleClass="Wdate" />
			&nbsp;	
			用户名：<input id="username" name="username" class="input1" style="width:180px;"/>
			&nbsp;
			开立存管：
                <select id="isCustody" name="isCustody" value="${rechargeRecordCnd.isCustody}"
                     class="bigselect" style="width: 100px;">
                    <option value="-1">--请选择--</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>	
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
		</div>
	</form>

	<div id="list" class="main_cent"></div>

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
		var urlPath = '${path}/account/cashpaidmain/list/' + pageNo + '.html';
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
		pageGo(1);
	}
	
	function showInvalidCash(id){
		if(!confirm("确认要作废提现吗？")){
			return;
		}
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/cashpaidmain/invalidcash/'+id+'.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				alert(result.message);
				window.location.href = window.location.href;
			},
			error : function(result) {
				alert(result.message);
			} 
		});
	}
	
</script>
</html>
