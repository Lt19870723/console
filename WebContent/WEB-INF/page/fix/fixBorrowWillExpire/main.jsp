<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>定期宝-即将到期定期宝</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			还款日期：
			<input type="text"
				name="beginDate" id="beginDate" onclick="WdatePicker()" value="<fmt:formatDate value='${fixBorrowCnd.beginDate}' pattern='yyyy-MM-dd'/>"
				class="Wdate"  />
			至
			<input type="text"
				name="endDate" id="endDate" onclick="WdatePicker()" value="<fmt:formatDate value='${fixBorrowCnd.endDate}' pattern='yyyy-MM-dd'/>"
				class="Wdate"  />
			&nbsp;		
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
		</div>
	</form>
	<div id="dataTable" class="main_cent"></div>
	
		
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
		var urlPath = '${path}/fix/fixBorrowWillExpire/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				//$('#dataTable tbody').remove();
				//$('#dataTable').append(result);
				$("#dataTable").html(result);
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

	function detail(id) {
		var _url = '${path}/fix/fixBorrowWillExpire/detail/' + id + '.html';
		$.layer({
			type : 2,
			title : '定期宝投标日志',
			area : [ '1000px', '500px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
	function toAddModifyQuickPub(id) {
		var _url = '${path}/fix/fixBorrowWillExpire/quickPub/' + id + '.html';
		$.layer({
			type : 2,
			title : '一键发宝',
			area : [ '860px', '270px' ],
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