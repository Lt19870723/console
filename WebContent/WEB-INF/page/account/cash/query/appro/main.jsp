<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<title>国诚金融—提现管理</title>
  </head>
  
  <body style="background: #f9f9f9;">
    	<form id="cashRecordform" action="">
    		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			提现时间：
				<input
					name="beginTime" id="appBeginTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			至
				<input
					name="endTime" id="endTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			&nbsp;	
			用户名：<input id="username" name="username"
				value="" class="input1"/>
			&nbsp;
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			<br/>
			</div>
    	</form>
    	
    	<div id="list" class="main_cent"></div>
    	
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
		var urlPath = '${path}/account/cashauditrecord/list/' + pageNo + '.html';
		$("#cashRecordform").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
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
	
	function showAuditLayer(id){
		var _url = '${path}/account/cashauditrecord/detail/'+id+'.html';
		$.layer({
			type : 2,
			title : '提现审核',
			area : [ '80%', '100%' ],
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
