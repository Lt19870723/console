<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—自动投宝记录</title>
</head>
<body style="background: #f9f9f9;">
	<div class="navigation">借款管理>自动投资>自动投宝日志</div>
	<form id="queryForm" action="" method="post">
		<div id="loginLogCard" class="queryDiv">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名：<input name="username" id="username" class="input1" /> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态： 
			<select id="status" name="status" class="bigselect" >
				<option value="">--请选择--</option>
				<option value="0">未启用</option>
				<option value="1">已启用</option>
				<option value="-1">已删除</option>
			</select> 
			投宝方式： 
			<select id="autoTenderType" name="autoTenderType" class="bigselect" >
				<option value="">--请选择--</option>
				<option value="1">按金额投宝</option>
				<option value="2">按账户余额投宝</option>
			</select>
			记录类型： 
			<select id="recordType" name="recordType" class="bigselect">
				<option value="">--请选择--</option>
				<option value="1">设置</option>
				<option value="2">修改</option>
				<option value="3">停用</option>
				<option value="4">启用</option>
				<option value="5">删除</option>
				<option value="6">投宝</option>
				<option value="7">流宝</option>
				<option value="8">撤宝</option>
				<option value="9">重新排队</option>
			</select>
			<br/>
			所投宝编号：<input id="fixNo" class="input1" name="fixNo" />
			记录时间：
				<input name="addTimeStart" id="addTimeStart" onclick="WdatePicker()" styleClass="Wdate" value="" />
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				~
				<input name="addTimeEnd" id="addTimeEnd" onclick="WdatePicker()" styleClass="Wdate" value="" />
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			实际投宝（元）：
			 <select id="autoTenderMoney" name="autoTenderMoney" class="bigselect" >
			    <option value="">--请选择--</option>
				<option value="1">等于0</option>
				<option value="2">大于0</option>
			</select>
			<input type="button" onclick="javascript:query();" type="button" name="Submit1" id="subbtn" class="b_buts" value="查   询" /> <br />
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
		$("#queryForm").ajaxSubmit({
			url : '${path}/borrow/autoInvestConfig/fixAutoInvestRecord/list/'
					+ pageNo + '.html',
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
	function query() {
		pageGo(1);
	}
	function fordetail(id) {
		var _url = '${path}/borrow/autoInvestConfig/fixAutoInvestRecord/findAutoInvestConfigRecordDetail.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '显示自动投宝信息',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end : function() {
				parentLayer.close(_load);
			}
		});
	}
</script>
</html>
