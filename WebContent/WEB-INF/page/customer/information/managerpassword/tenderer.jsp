<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—理财用户密码管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="memberForm" action="" method="post">
		<div id="customerCard" style="padding-left:20px;">
			<div>
			<br/>
				用户名： <input name="username" id="username" class="input1"
					value="${username}" /> 真实姓名： <input name="realName" id="realName"
					class="input1" value="${realName}" /> <br />
				<br /> 邮箱： <input name="email" id="email" class="input1"
					value="${email}" /> &nbsp; 手机号： <input name="mobileNum"
					id="mobileNum" class="input1" value="${mobileNum}" /> &nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
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
			url : '${path}/customer/managerPassword/query/' + pageNo + '.html',
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
	*查询功能
	*/
	function serachCustomer(){
		pageGo(1);
	}
	
	/**
	 *初始化修改密码窗体
	 */
	function initEditPage(passwordType,username){
		
		var _url = '${path}/customer/managerPassword/initEditPage/'+passwordType+'/'+username+'.html';
		if(passwordType == 'login'){
			title = '修改用户登录密码';
		}else {
			title = '修改用户交易密码';
		}
		
		$.layer({
			type : 2,
			title :title,
			area : [ '480px', '163px' ],
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
