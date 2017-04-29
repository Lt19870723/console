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
			用户名：<input id="username" class="input1" name="username" />
			&nbsp;
			真实姓名：<input id="realName" class="input1" name="realName" />
			&nbsp;
			
			注册日期：
				<input
					name="addtimeBegin" id="addtimeBegin" onclick="WdatePicker()"
					styleClass="Wdate" >
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
					 &nbsp;至&nbsp; 
				<input name="addtimeEnd" id="addtimeEnd"
					onclick="WdatePicker()" styleClass="Wdate">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
				<br/>
				 
			    &nbsp; 邮箱： <input id="email" class="input1" name="email" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 手机号：<input id="mobileNum" class="input1" name="mobileNum" />	 
		        &nbsp;用户状态 ：
				  <select id="status" name="status"
					class="bigselect" style="width:130px;">
				  		<option value="0">正常状态</option>
				  		<option value="-1">账号注销</option>
	                    <option value="-2">待审核</option>
	                    <option value="-3">审核不通过</option>
	                    <option value="10000">所有状态</option>
				  </select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					 					 
			 
			<br />  
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
			url : '${path}/borrow/manage/repayRecharge/list/' + pageNo + '.html',
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
	function validateTenderBorrow(){
		if(confirm("确定要垫付吗？")){
			return true;
		}else{
			return false;
		}
		
	}
	/**
	  充值页面
	*/
	function showRecharge(id){
		  
		var _url = '${path}/borrow/manage/repayRecharge/initRecharge.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		 
		$.layer({
			type : 2,
			title : '借款用户充值',
			area : [ '50%', '80%' ],
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
	
	
	 
	  
</script>
</html>
