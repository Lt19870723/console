<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—菜单管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post">
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			<div>
				&nbsp;&nbsp;&nbsp;&nbsp;手机号： <input id="mobileNum" name="mobileNum"
					class="input1" value="${feedbackInfoCnd.mobileNum}" /> &nbsp; 状态：<select
					id="status" name="status" value="${feedbackInfoCnd.status }"
					class="bigselect" style="width: 90px;">
					<option value="-1">--请选择--</option>
					<option value="0">待解决</option>
					<option value="1">已解决</option>
					<option value="2">不予解决</option>
				</select> &nbsp;				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			</div>
			
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
		$("#loginLogForm").ajaxSubmit ({
			url : '${path}/customer/feedback/query/' + pageNo + '.html',
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
	 *查看反馈内容
	 */
	function lookContact(id){
			var _url = '${path}/customer/feedback/showFeedbackDetail/'+id+'.html';
			$.layer({
				type : 2,
				title : '回访内容详情',
				area : [ '600px', '420px' ],
				offset : [ '10px', '' ],
				shade : [ 0.1, '#000' ],
				maxmin : true,
				iframe : {
					src : _url
				}
			});
	}
	function showFeedback(id){
		var _url = '${path}/customer/feedback/showFeedbackLayer/'+id+'.html';
		$.layer({
			type : 2,
			title : '反馈处理',
			area : [ '600px', '420px' ],
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
