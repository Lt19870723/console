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
	<form id="memberForm" action="" method="post">
		<div id="customerCard" style="padding-top:10px;">
			<div>
				&nbsp;&nbsp;&nbsp;&nbsp;用户名：<input name="username" id="username"
					class="input1" value="${memberCnd.username }" /> &nbsp; 注册日期：<input
					name="addtimeBegin" id="addtimeBegin" onclick="WdatePicker()"
					styleClass="Wdate" value="${memberCnd.addtimeBegin }">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input> ~<input name="addtimeEnd" id="addtimeEnd" onclick="WdatePicker()"
					styleClass="Wdate" value="${memberCnd.addtimeEnd }">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input> <br /><br /> 真实姓名：<input name="realName" id="realName" class="input1"
					value="${memberCnd.realName }" /> &nbsp; 邮箱：<input name="email"
					id="email" class="input1" value="${memberCnd.email }" /> &nbsp;
				手机号：<input name="mobileNum" id="mobileNum" class="input1"
					value="${memberCnd.mobileNum }" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button"
					onclick="javascript:serachCustomer();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" /> <br /><br />
			</div>
			
		</div>
	</form>

	<div id="customerList" class="main_cent"></div>

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
		$("#memberForm").ajaxSubmit ({
			url : '${path}/customer/memberclear/query/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#customerList").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	/**
	 *用户注销响应事件
	 */
	function clean(memberId){
		if(!confirm('确认注销该客户吗？')){
			return false;
		}
		var _load = parentLayer.load('处理中..');
		$.ajax ({
			url : '${path}/customer/memberclear/clear/' + memberId + '.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result.code == '0'){
					parentLayer.close(_load);
					layer.msg(result.message, 1, 1);
					pageGo(1);
				}else{
					parentLayer.close(_load);
					layer.msg(result.message, 1, 5);
				}
				
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！' , 1, 5);
				parentLayer.close(_load);
			}
		});
	}
	
	/**
	*查询功能
	*/
	function serachCustomer(){
		pageGo(1);
	}
	
</script>
</html>
