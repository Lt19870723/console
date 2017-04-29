<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>活期宝用户收益-国诚金融后台管理系统</title>
</head>
<body>
	<input type="hidden" id="userId" name="userId" value="${curAccountVo.userId}" />
	<div id="tabView" style="height:180px;">
		<div class="listzent"> 我的账户详情信息 </div>
		
		<div class="left1_input_ts" style="float: left;margin-left: 10px;">
			用户名：  ${curAccountVo.userName}
		</div>
		
		
		<div class="left1_input_ts" style="float: left;">
			昨日收益：  ${curAccountVo.interestYesterday}
		</div>
		
		<div class="left1_input_ts" style="float: left;margin-left: 10px;">
			累计收益：  ${curAccountVo.interestTotal}
		</div>
		
		
		<div class="left1_input_ts" style="float: left;">
			活期宝资产：  ${curAccountVo.total}
		</div>
		

		<div class="left1_input_ts" style="float: left;margin-left: 10px;">
			未产生收益金额：  ${curAccountVo.noUseMoney}
		</div>
		
		<div class="left1_input_ts" style="float: left;">
			产生收益金额：  ${curAccountVo.useMoney}
		</div>
		
	</div>
	
	<div id="tabSymx" style="height:430px;">
		<div class="listzent"> 我的收益明细列表 </div>
		<span style="display: block; padding-top: 10px;">
		<table id="dataTable" class="fulltable" style="width:99%;">
			<thead>
				<tr>
				<th width="5%">序号</th>
				<th>日期 </th>
				<th>昨日收益</th>
				<th>未产生收益的金额</th>
				<th>活期宝资产</th>
				</tr>
			</thead>
		</table>
		</span>
	</div>
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
		var urlPath = '${path}/curaccount/userinterest/detaillist/' + pageNo + '.html';
		$.ajax ({
			url : urlPath,
			data : {
				'userId' : $.trim($('#userId').val()),
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