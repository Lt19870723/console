<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>活期宝用户收益-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post" >
		<div style="margin-left:20px;line-height:50px;">
			 用户名：<input type="text" id="userName" name="userName" class="input1"/>
			&nbsp;					
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;
			<span id="preDataTip" style="color:red;"></span>
			<br/>
			昨日： <span id="sumYesterday"></span>
			元
			&nbsp;
			累计： <span id="sumInTotal"></span>
			元
			&nbsp;
			活期宝总额: <span id="sumTotal"></span>
			元
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="5%">序号  </th>
				<th>用户名 </th>
				<th>累计收益  </th>
				<th>昨日收益    </th>
				<th>未产生收益的金额    </th>
				<th>产生收益的金额</th>
				<th>活期宝资产  </th>
				<th>详情 </th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
    var _load;
	$(function() {
		query();
	});

	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		 
		var urlPath = '${path}/curaccount/userinterest/list/' + pageNo + '.html';
		_load=layer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				layer.close(_load);
				$('#dataTable tbody').remove();
				$('#dataTable').append(result);
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}

	/**
	*查询功能
	*/
	function query(){
		sum();
		pageGo(1);
	}
	
	function sum() {
		
		$.ajax({
			url : '${path}/curaccount/userinterest/sum.html',
			data : {
				'userName' : $.trim($('#userName').val()),
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {

				$("#preDataTip").html(result["preDataTip"]);
				$("#sumYesterday").html(result["retCurAccountVo"].sumYesterday);
				$("#sumInTotal").html(result["retCurAccountVo"].sumInTotal);
				$("#sumTotal").html(result["retCurAccountVo"].sumTotal);
				
				layer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	function detail(id) {
		var _url = '${path}/curaccount/userinterest/detail.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '查看我的账户详情',
			area : [ '1000px', '500px' ],
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