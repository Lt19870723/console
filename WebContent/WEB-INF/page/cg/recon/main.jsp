<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>现金日记账-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
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
                &nbsp;
                &nbsp;
                &nbsp;
				<input placeholder="请选择查询时间" id="J_searchDay" onclick="WdatePicker()" styleClass="Wdate"><f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime></input>
				<input placeholder="请选择查询时间" id="J_searchMonth" onclick="WdatePicker({dateFmt:'yyyy-MM'})" styleClass="Wdate"><f:convertDateTime pattern="yyyy-MM"></f:convertDateTime></input>
				<input placeholder="请选择查询时间" id="J_searchYear" onclick="WdatePicker({dateFmt:'yyyy'})" styleClass="Wdate"><f:convertDateTime pattern="yyyy"></f:convertDateTime></input>
                &nbsp;
                &nbsp;
                &nbsp;
                操作类型：<select name="targetType" class="bigselect" style="width: 100px;">
                            <option value="-1">--请选择--</option>
                            <option value="1">批量扣款对账</option>
                            <option value="2">批量还款对账</option>
                            <option value="3">开销户对账</option>
                            <option value="4">出入账对账</option>
                        </select>
                &nbsp;
                &nbsp;
                &nbsp;
                操作结果：<select name="status" class="bigselect" style="width:100px;">
                            <option value="-1">--请选择--</option>
                            <option value="1">成功</option>
                            <option value="0">失败</option>
                         </select>
                &nbsp;
                &nbsp;
                &nbsp;

				<input id="searchDetailList" name="searchDetailList" value="查询" type="button" onclick="query();" />&nbsp;
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
	});

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
			url : '${path}/cg/reconrecords/list/' + pageNo + '.html',
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

	function showDetail(id) {

		var _url = '${path}/cg/reconrecords/detail/' + id + '.html'
		$.layer({
			type : 2,
			title : '详情',
			area : [ '800px', '400px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});

	}

    function retry(id) {
        var _load = parentLayer.load('处理中..');
		$.ajax({
			url : '${path}/cg/reconrecords/retry/' + id + '.html',
			type : 'post',
			dataType : 'json',
			success : function(data) {
                if (data.code == '0') {
                    layer.msg(data.message, 1, 5);
                } else {
                    layer.msg(data.message, 2, 1);
                    query();
                }
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
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
	
</script>
</html>