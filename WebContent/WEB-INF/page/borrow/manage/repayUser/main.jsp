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
<div style="margin-left:20px;line-height:50px;">
	充值用户名：<input type="text"  name="ReChangeusername" id="ReChangeusername" class="input1" />
	&nbsp;
	充值金额：<input type="text" name="ReChangeMoney" id="ReChangeMoney" class="input1"  />
	&nbsp;
	<input type="button"
		   onclick="javascript:ReChange();" type="button" name="Submit1"
		   id="subbtn" class="b_buts" value="充值" />
</div>
<form id="queryForm" action="" method="post" >
	<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
		标名称：<input id="name" class="input1" name="name" />
		&nbsp;
		用户名：<input id="userName" class="input1" name="userName" />
		应还日期：
		<input name="repaymentTimeBeginStr" id="repaymentTimeBeginStr" onclick="WdatePicker()" styleClass="Wdate" >
		<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
		</input>至<input name="repaymentTimeEndStr" id="repaymentTimeEndStr" onclick="WdatePicker()" styleClass="Wdate">
		<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
		</input>
		<br/>

		借款编号：<input id="contractNo" class="input1" name="contractNo" />
		标类型 ：
		<select id="borrowtype" name="borrowtype"
				class="bigselect" style="width:130px;">
			<option value="">--请选择--</option>
			<option value="1">信用标</option>
			<option value="2">抵押标</option>
			<option value="3">净值标</option>
			<option value="5">担保标</option>
		</select>
		是否存管 ：
		<select id="isCustody" name="isCustody"
				class="bigselect" style="width:130px;">
			<option value="">--请选择--</option>
			<option value="0">非存管</option>
			<option value="1">存管</option>
		</select>
		&nbsp;&nbsp;
		<input type="button"
			   onclick="javascript:query();" type="button" name="Submit1"
			   id="subbtn" class="b_buts" value="查   询" />
	</div>
</form>

<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">
	var _load;
	$(function() {
		pageGo(1);
	});

	/**
	 *翻页功能
	 */
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/borrow/manage/repayUser/list/' + pageNo + '.html',
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

	function ReChange(){
		if(!valiDataReChange()){
			return false;
		}
		$.ajax({
			url : '${path}/borrow/manage/repayUser/doBorrowUserRechange.html',
			data : {
				'userName' :$('#ReChangeusername').val(),
				'reChangeMoney' :$('#ReChangeMoney').val()
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {

				if (result.code == '1') {
					layer.msg(result.message, 1, 1, function() {
						$('#userName').val($('#ReChangeusername').val());
						parentLayer.close(_load);
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
	function  valiDataReChange(){
		var reChangeusername = $.trim($('#ReChangeusername').val());
		if(reChangeusername==""){
			alert("请输入充值用户名");s
			return false;
		}
		//金额的正则表达式
		var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
		var reChangeMoney = $.trim($('#ReChangeMoney').val());
		if(reChangeMoney==""){
			alert("请输入充值金额");
			return false;
		}
		if(!zfdsReg.test(reChangeMoney)){
			alert("充值金额输入有误!");
			return false;
		}
		$('#ReChangeMoney').val(reChangeMoney);
		$('#ReChangeusername').val(reChangeusername);
		if(!confirm("确定要为用户名为【"+reChangeusername+"】充值【"+reChangeMoney+"块钱】吗？")){
			return false;
		}
		return true;
	}

	function confirmWebpay(id,name,money){
		if(!confirm("确定要扣除用户名【"+name+"】的账户【"+money+"块钱】进行还款操作吗？")){
			return false;
		}
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/borrow/manage/repayUser/doReplay.html',
			data : {
				'id' :id,
				'name':name
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '1') {
					layer.msg(result.message, 1, 1, function() {
						pageGo(1);
					});

				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
				parentLayer.close(_load);
			}
		});
	}
</script>
</html>
