<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>直通车转让-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			标题：<input type="text" id="name" name="name" class="input1"/>
			&nbsp;
			发布日期：
			<input type="text"
				name="beginTime" id="beginTime" onclick="WdatePicker()"
				class="Wdate" />
			至
			<input type="text"
				name="endTime" id="endTime" onclick="WdatePicker()"
				class="Wdate" />
			&nbsp;	
			&nbsp;					
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>
					序号
				</th>
				<th>
					借款标题
				</th>
				<th>
					状态
				</th>
				<th>
					开通金额
				</th>
				<th>
					应得利息
				</th>
				<th>
					转让管理费
				</th>
				<th>
					差额利息
				</th>
				<th>
					剩余债权价值
				</th>
				<th>
					转让系数
				</th>
				<th>
					转让价格
				</th>
				<th>
					债权转让人
				</th>
				<th>
					发布时间
				</th>
				<th >
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
		var urlPath = '${path}/firsttransfer/transfercheck/list/' + pageNo + '.html';
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
	
	function status(id) {
		var _url = '${path}/firsttransfer/transfercheck/status.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '复审',
			area : [ '380px', '350px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
	function cancel(id) {
		var _url = '${path}/firsttransfer/transfering/cancel.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '取消转让',
			area : [ '380px', '350px' ],
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