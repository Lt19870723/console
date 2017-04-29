<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—借款终审</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
		<input type="hidden" value="${borrowId}" id="borrowId" name="borrowId" /> <input type="hidden" value="<fmt:formatNumber value="${userMoney}" pattern="#,##0.00" />" id="userMoney" />
		<div id="loginLogCard" style="margin-left: 20px; line-height: 50px;">
			定期宝编号：
			<input id="contractNo" class="input1" name="contractNo" /> <br /> 
			开放日期： 
			<input name="beginTime" id="beginDate" onclick="WdatePicker()" styleClass="Wdate">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input> 
			&nbsp;至&nbsp; 
			<input name="endTime" id="endDate" onclick="WdatePicker()" styleClass="Wdate">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input>  
			<input type="button" onclick="javascript:query();" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
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
			url : '${path}/fix/handleFixTender/fixlist/' + pageNo + '.html',
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
	//弹出提示
	function fixTender(fixUseMoney, contractNo, fixBorrowId, borrowMoney) {
		var _load;
		var borrowUseMoney = $('#userMoney').val();
		var borrowId = $('#borrowId').val();
		layer.confirm("确认要定期宝" + contractNo + "投该借款标？  定期宝可用余额：" + fixUseMoney
				+ "借款标剩余余额" + borrowMoney, function() {
			_load = layer.load('处理中..');
			$.ajax({
				url : '${path}/fix/handleFixTender/handleFixTender.html',
				data : {
					'fixBorrowId' : fixBorrowId,
					'borrowId' : borrowId
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

		});
	}
</script>
</html>
