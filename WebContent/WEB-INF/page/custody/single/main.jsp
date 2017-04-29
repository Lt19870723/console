<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—存管单笔流水查询</title>
</head>
<body style="background: #f9f9f9;">
<form id="queryForm" action="" method="post" style="width: 100%;">
		<div>
			<div style="margin-left:20px;line-height:50px;">
				订单号/银行流水号/项目编号：<input id="orderNo" class="input1" name="orderNo" />
				&nbsp;
				类型：
				<select id="type" name="type" class="bigselect" style="width:130px;">
					<option value="1">充值</option>
					<option value="2">提现</option>
					<option value="3">冻结</option>
					<option value="4">解冻</option>
					<option value="5">投资</option>
					<option value="6">还款</option>
					<option value="7">转让</option>
					<option value="8">分账</option>
					<option value="9">批量扣款</option>
					<option value="10">批量还款</option>
					<option value="11">流标解冻</option>
				</select>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
													  onclick="javascript:querySingle();" type="button" name="Submit1"
													  id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
</form>
<div id="single" class="main_cent"></div>

<form id="queryForm2" action="" method="post" style="width: 100%;">
		<div>
			<div style="margin-left:20px;line-height:50px;">
				用户名：<input id="username" name="username" class="input1" name="orderNo" />
				&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
													  onclick="javascript:queryOa();" type="button" name="Submit2"
													  id="btnQueryOa" class="b_buts" value="开户查询" />
			</div>
		</div>
</form>
<div id="oaInfo" class="main_cent"></div>
</body>
<script type="text/javascript">
	function validate(){
		var orderNo = $('#orderNo').val();
		if(null==orderNo || $.trim(orderNo)==""){
			alert("订单号/银行流水号/项目编号不能为空。");
			return false;
		}
		var type = $('#type').val();
		if(null==type || $.trim(type)==""){
			alert("类型不能为空。");
			return false;
		}

		return true;
	}

	function querySingle() {

		if(!validate()){
			return;
		}
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/custody/single/result.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#single").html(result);
				parentLayer.close(_load);
			},
			error : function(result) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}

    function queryOa() {
        var username = $('#username').val();

        if (null == username || $.trim(username) == ""){
			alert("用户名不能为空");
			return;
		}

        var _load = parentLayer.load('处理中..');
		$("#queryForm2").ajaxSubmit ({
			url : '${path}/cgnotify/czbank/queryOa.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#oaInfo").html(result);
				parentLayer.close(_load);
			},
			error : function(result) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
</script>

</html>
