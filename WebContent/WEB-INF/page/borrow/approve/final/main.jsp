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
			标题：<input id="name" class="input1" name="name"
				value="${borrowCnd.name}" />
			&nbsp;
			用户名：<input id="userName" class="input1" name="userName"
				value="${borrowCnd.userName}" />
			&nbsp;
			借款标类型 ：
				  <select id="borrowtype" name="borrowtype"
					value="${borrowCnd.borrowtype}"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">信用标</option>
	                    <option value="2">抵押标</option>
	                    <option value="3">净值标</option>
	                    <option value="5">担保标</option>
				  </select>
				     <br>
				   是否存管 ：
				  <select id="isCustody" name="isCustody"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="0">非存管</option>
	                    <option value="1">存管</option>
				  </select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="button"
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
			url : '${path}//approve/final/list/' + pageNo + '.html',
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
	  借款标终审审核
	*/
	function showAuditLayer(id,flag){
		var title="审核";
		var _url;
	    if(flag=="1"){
		   _url = '${path}/approve/final/finalCheck.html';
	    }else if(flag=="2"){
	      _url = '${path}/approve/final/initTimingBorrow.html';
	      title="设置定时发标";
	    }else if(flag=="3"){
	      _url = '${path}/approve/final/initTimingBorrow.html';
		  title="修改定时发标";	
	    }else{
	      _url = '${path}/approve/final/initUpdateBorrowInfo.html';
	      title="修改标";
	    }
	    
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : title,
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
	function delTimingBorrow(id){
		var _load; 
		if(confirm("确认要删除吗？")){
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/approve/final/delTimingBorrow.html',
			data : {
				'id' : id	 
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						pageGo(1);
					});
				} else {
					 
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
				parentLayer.close(_load);
		    }
		  });		
		 
		}
	} 
	  
</script>
</html>
