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
	<form id="queryForm" action="" method="post" >
	   
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			定期宝编号：<input id="fixContractNo" class="input1" name="fixContractNo" />
			&nbsp;
			借款标名称：<input  id="borrowName"class="input1" name="borrowName" />
			&nbsp;
			借款标编号：<input  id="contractNo"class="input1" name="contractNo" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
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
		$("#queryForm").ajaxSubmit ({
			url : '${path}/fix/fixTenderUser/list/' + pageNo + '.html',
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
	/**
	  借款标初审审核
	*/
	function showDetail(id){
	 
		var _url = '${path}/approve/first/firstCheck.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '详情',
			area : [ '50%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
	} 
	 
	  
</script>
</html>
