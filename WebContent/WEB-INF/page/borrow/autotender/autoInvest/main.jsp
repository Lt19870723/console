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
<div class="navigation">借款管理>自动投资>自动投标规则</div>
	<form id="queryForm" action="" method="post" >
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
		 
			用户名：<input name="username" id="username" class="input1"   />
			&nbsp;
			投标方式： 
			<select id="autoTenderType" name="autoTenderType" class="bigselect">
				<option value="">--请选择--</option>
				<option value="1">按金额投标</option>
				<!-- <option value="2">按比例投标</option> -->
				<option value="3">按余额投标</option>
			</select>
		   &nbsp;
		   	状态：
				 <select id="status" name="status"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="0">未启用</option>
				  		<option value="1">已启用</option>
				  		<option value="2">已删除</option>
				  </select>		  
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
				<input type="button"	onclick="javascript:query();" type="button" name="Submit1"
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
			url : '${path}/borrow/autotender/autoInvest/list/' + pageNo + '.html',
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
	  借款标反欺诈审核
	*/
	function showAuditLayer(id){
	 
		var _url = '${path}//borrow/approve/antifraudeCheck.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '审核',
			area : [ '50%', '50%' ],
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
