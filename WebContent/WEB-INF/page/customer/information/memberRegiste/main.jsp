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
			用户名：<input   class="input1" name="username" value="${username}" id="username" />
			&nbsp;
			注册日期：
				<input
					name="addtimeBegin" id="addtimeBegin" onclick="WdatePicker()"
					styleClass="Wdate" />
					 &nbsp;至&nbsp; 
				<input name="addtimeEnd" id="addtimeEnd"
					onclick="WdatePicker()" styleClass="Wdate"/>
			</br>		
			真实姓名：<input   class="input1" name="realName" id="realName" />
			&nbsp;
			手机号码：<input class="input1" name="mobileNum" id="mobileNum" />	
			邮箱：<input    class="input1" name="email" id="email" />	
			状态：
				  <select   name="status"
					class="bigselect" style="width:130px;">
				  		<option value="0">正常状态</option>
				  		<option value="-1">账号注销</option>
	                    <option value="-2">待审核</option>
	                    <option value="-3">审核不通过</option>
	                    <option value="10000">所有状态</option>
				  </select>
			 <br>
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					 					 
			 <input type="button"
					onclick="javascript:register();" type="button" style="width:130px;" name="Submit1"
					id="subbtn" class="b_buts" value="借款用户注册" />
		 
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
			url : '${path}/information/memberRegiste/list/' + pageNo + '.html',
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
	function register(){
		var title="用户注册";
		var _url = '${path}/information/memberRegiste/register.html'; 
		$.layer({
			type : 2,
			title : title,
			area : [ '30%', '50%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	} 
	function forApplyBorrowListMain(id){
	    location.href="${path}/information/memberRegiste/applyBorrow.html?userId="+id;
	} 
	  
</script>
</html>
