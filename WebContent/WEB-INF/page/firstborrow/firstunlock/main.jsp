<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>提前解锁审核-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			&nbsp;	
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="40"  align="center">
					序号
				</th>
				<th width="40"  align="center">
					用户名
				</th>						
				<th width="90"  align="center">
					开通金额
				</th>
				<th width="90"  align="center">
					开通余额
				</th>
				<th width="90"  align="center">
					所占比例
				</th>				
				<th width="150"  align="center">
					操作
				</th>
			</tr>
		</thead>
	</table>
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
		var urlPath = '${path}/firstborrow/firstunlock/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$('#dataTable tbody').remove();
				$('#dataTable').append(result);
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
	
	function showApprovePopup(id,userId) {
		var _url = '${path}/firstborrow/firstunlock/detail/' + id + '/' + userId + '.html';
		$.layer({
			type : 2,
			title : '解锁审核',
			area : [ '500px', '200px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
</script>
</html>