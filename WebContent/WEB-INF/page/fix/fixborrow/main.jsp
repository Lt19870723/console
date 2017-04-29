<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>定期宝列表</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
		<div style="margin-left: 20px; line-height: 35px; width: 100%">
			<b>定期宝自动投标：</b>
			<c:if test="${cf.name!=1 }">不限制</c:if>
			<c:if test="${cf.name==1 }">截止至<font color="red">${cf.value}</font>(含)不参与投标</c:if>
			<input type="button" onclick="show(0,'setTenderBidAll')" class="b_buts" value="限制自动投标" /> 
		</div>
		<div style="margin-left: 20px; line-height: 35px; width: 100%">
			<span class="class1">
			<b>定期宝待收余额：</b><fmt:formatNumber type="currency" value="${fixStaticVo.accountCollectMoney}" pattern="#,##0.00" />元
			<b>定期宝待支出总额：</b><fmt:formatNumber type="currency" value="${fixStaticVo.accountRepayMentMoney}" pattern="#,##0.00" />元
			<b>定期宝余额：</b><fmt:formatNumber type="currency" value="${fixStaticVo.accountUserMoney}" pattern="#,##0.00" />元
			<b>定期宝历史已支出利息：</b><fmt:formatNumber type="currency" value="${fixStaticVo.accountHistoryInvest}" pattern="#,##0.00" />元
			</span> 
		</div>
		<div id="loginLogCard" style="margin-left: 20px; line-height: 35px;">
			定期宝编号：<input id="contractNo" class="input1" name="contractNo"  style="width: 110px;"/>
			开放日期： 
			<input name="beginTime" id="beginDate" onclick="WdatePicker()" styleClass="Wdate" style="width: 110px;">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input> 
			~
			<input name="endTime" id="endDate" onclick="WdatePicker()" styleClass="Wdate" style="width: 110px;">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态： 
			<select id="status" name="status" class="bigselect" style="width: 95px;">
				<option value="定期宝列表">--请选择--</option>
				<option value="预发中">预发中</option>
				<option value="开放中">开放中</option>
				<option value="5">收益中</option>
				<option value="7">已退出</option>
				<option value="-2">已撤销</option>
				<option value="-1">已流宝</option>
			</select> </br>
			&nbsp;&nbsp;&nbsp;可用余额：
			<select name="useMoneyFlag" class="bigselect"  style="width:95px;">
				<option>--请选择--</option>
				<option value="eq0">等于0</option>
				<option value="gt0">大于0</option>
			</select>
			&nbsp;&nbsp;定期宝期限：
			<select name="lockLimit" class="bigselect"  style="width:95px;">
				<option value="">--请选择--</option>
				<option value="1">1月</option>
				<option value="3">3月</option>
				<option value="6">6月</option>
				<option value="12">12月</option>
			</select>
			&nbsp;&nbsp;定期宝类型：
			<select name="fixType" class="bigselect"  style="width:95px;">
				<option value="">--请选择--</option>
				<option value="0">普通宝</option>
				<option value="1">新手宝</option>
			</select>
			<input type="button" onclick="query()" name="Submit1" id="subbtn" class="b_buts" value="查询" style="width: 70px;"/> 
		</div>
	</form>
	<div id="loginLogList" class="main_cent"></div>
	<div style="margin-left: 20px; line-height: 50px;">
		<div>定期宝待收总额：当前所有收益中定期宝未到期标的待收本金和利息总和</div>
		<div>定期宝待支出总额：当前所有收益中定期宝待支付给投资用户的本金和利息总和</div>
		<div>定期宝余额：总账户现有的可用金额</div>
		<div>定期宝历史已支出利息：历史所有已退出定期宝已成功支付给用户的利息收入的总和</div>
	</div>
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
			url : '${path}/fix/fixborrow/list/' + pageNo + '.html',
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
	/**
	  查看
	 */
	function show(id, opt) {
		var _title = '定期宝详情';
		var _width = '90%';
		var _height = '90%';
		opt = opt || '';
		if (opt == 'setTenderBid' || opt == 'setTenderBidAll') {
			_title = '设置限制自动投标';
		}
		var _url = '${path}/fix/fixborrow/toAddModify.html?id=' + id + '&opt=' + opt;
		$.layer({
			type : 2,
			title : _title,
			area : [ _width, _height ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	function confirmDoCancel(id) {
		var _load;
		if (confirm("确认要撤销吗？")) {
			_load = layer.load('处理中..');
			$.ajax({
				url : '${path}/fix/fixborrow/doCancle.html',
				data : {
					'id' : id
				},
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if (result.code == '1') {
						layer.msg(result.message, 1, 1, function() {
							pageGo(1);
						});
					} else {
						parentLayer.close(_load);
						layer.msg(result.message, 1, 5);
					}
				},
				error : function(result) {
					layer.msg('网络连接超时,请您稍后重试.', 1, 5);
					parentLayer.close(_load);
				}
			});

		}
	}
	function confirmDoDelete(id) {
		var _load;
		if (confirm("确认要删除吗？")) {
			_load = layer.load('处理中..');
			$.ajax({
				url : '${path}/fix/fixborrow/doDelete.html',
				data : {
					'id' : id
				},
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if (result.code == '1') {
						layer.msg(result.message, 1, 1, function() {
							pageGo(1);
						});
					} else {
						parentLayer.close(_load);
						layer.msg(result.message, 1, 5);
					}
				},
				error : function(result) {
					layer.msg('网络连接超时,请您稍后重试.', 1, 5);
					parentLayer.close(_load);
				}
			});

		}
	}
</script>
</html>
