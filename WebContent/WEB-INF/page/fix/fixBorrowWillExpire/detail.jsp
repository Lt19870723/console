<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>定期宝投标日志</title>
</head>
<body>
	<input type="hidden" id="id" name="id" value="${id}" />
	<table id="dataTable" class="fulltable" style="width:99%;">
		<thead>
			<tr>
				<th width="40"  align="center">
					序号
				</th>
				<th width="120"  align="center">
					所投借款标
				</th>
				<th width="150"  align="center">
					借款标编号
				</th>
				<th width="150"  align="center">
					定期宝转让ID
				</th>
				<th width="150"  align="center">
					投标金额(元)
				</th>
				<th width="150"  align="center">
					投标时间
				</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
	$(function() {
		query();
	});

	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/fix/fixBorrowWillExpire/detaillist/' + pageNo + '.html';
		$.ajax ({
			url : urlPath,
			data : {
				'id' : $.trim($('#id').val()),
			},
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
</script>
</html>