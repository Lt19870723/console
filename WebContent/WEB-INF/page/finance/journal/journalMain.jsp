<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>现金日记账-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
	.text-hide{display: inline-block;text-align: left;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:250px; }
</style>
</head>
<body>
	<form id="form" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				按时间：<select style="width: 100px;" class="bigselect" name="searchType" id="J_searchType" onchange="changeSearchTime(this);">
						<option value="1" selected="selected">--按天--</option>
						<option value="2">--按月--</option>
						<option value="3">--按年--</option>
					  </select>
				<input placeholder="请选择查询时间" id="J_searchDay" onclick="WdatePicker()" styleClass="Wdate"><f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime></input>
				<input placeholder="请选择查询时间" id="J_searchMonth" onclick="WdatePicker({dateFmt:'yyyy-MM'})" styleClass="Wdate"><f:convertDateTime pattern="yyyy-MM"></f:convertDateTime></input>
				<input placeholder="请选择查询时间" id="J_searchYear" onclick="WdatePicker({dateFmt:'yyyy'})" styleClass="Wdate"><f:convertDateTime pattern="yyyy"></f:convertDateTime></input>
				<input id="searchJournalList" name="searchJournalList" value="查询" type="button" onclick="query();" />&nbsp;
				<input id="addJournal" name="addJournal" value="添加" type="button" onclick="addJournal1();" />&nbsp;
				<input value="导出到Excel" type="button" onclick="exportToExcel();">	
				<input type="hidden" name="operationDate" id="operationDate" />
			</div>
		</div>
	</form> 
	<div id="list" class="main_cent"></div>
</body>
<script type="text/javascript">
	$(function() {
		pageGo(1);
		changeSearchTime("#J_searchType");
		// allName(".text-hide");
	});

	// 鼠标经过提示全名
	function allName(e) {
		$(e).hover(function() {
			$(this).attr("title", $(this).text())
		}, function() {
			$(this).attr("title")
		});
	}

	function changeSearchTime(dom) {
		if ($(dom).val() == 1) {
			$("#J_searchDay").show();
			$("#J_searchMonth").hide();
			$("#J_searchYear").hide();
		} else if ($(dom).val() == 2) {
			$("#J_searchDay").hide();
			$("#J_searchMonth").show();
			$("#J_searchYear").hide();
		} else {
			$("#J_searchMonth").hide();
			$("#J_searchDay").hide();
			$("#J_searchYear").show();
		}
	}
	/**
	 *查询功能
	 */
	function query() {
		pageGo(1);
	}

	/**
	 *翻页功能
	 */
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');

		$("#operationDate").val(getSearchTime("#J_searchType"));

		$("#form").ajaxSubmit({
			url : '${path}/finance/journal/journalList/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}

	function addJournal1() {
		var _url = "${path}/finance/journal/addJournal.html";
		$.layer({
			type : 2,
			title : '添加日记账',
			area : [ '1000px', '400px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}

	function showJournal(id) {

		var _url = '${path}/finance/journal/detail/' + id + '.html'
		$.layer({
			type : 2,
			title : '详情',
			area : [ '800px', '300px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});

	}

	function editJournal(id) {
		var _url = "${path}/finance/journal/addJournal.html";
		if (id != null) {
			_url += "?id=" + id;
		}

		$.layer({
			type : 2,
			title : '编辑',
			area : [ '1000px', '400px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}

	function updateStatus(id) {
		layer.confirm("确认 删除?", function() {
			var _load = layer.load('处理中..');
			$.ajax({
				url : '${path}/finance/journal/deleteJournal/' + id + '.html',
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
				error : function(data) {
					layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				}
			});
		});
	}

	function getSearchTime(dom) {
		var str = "";
		if ($(dom).val() == 1) {
			str = $("#J_searchDay").val();
		} else if ($(dom).val() == 2) {
			str = $("#J_searchMonth").val();
		} else {
			str = $("#J_searchYear").val();
		}
		return str;
	}

	function exportToExcel() {
		var _url = '${path}/finance/journal/downloadJournalExcel.html';
		var searchType = $("#J_searchType").val();
		var operationDate = getSearchTime("#J_searchType");

		_url += "?operationDate=" + operationDate + "&searchType=" + searchType;

		window.location.href = _url;
	}
</script>
</html>