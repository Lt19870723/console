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
	  <input type="hidden" name="userId" id="userId" value="${userId}"/>
	  <input type="hidden" id="userName" name="userName" value="${username}"/>
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			用户名：<input   class="input1" name="name" />
			&nbsp;
			状态：
				  <select   name="borrowtype"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择类型--</option>
				  		<option value="1">信用标</option>
	                    <option value="2">抵押标</option>
	                    <option value="3">净值标</option>
	                    <option value="5">担保标</option>
				  </select>
			 <br>
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					 					 
			 <input type="button"
					onclick="javascript:initApplyBorrow(0);" type="button" style="width:130px;" name="Submit1"
					id="subbtn" class="b_buts" value="发标" />
		 
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
			url : '${path}/information/memberRegiste/sublist/' + pageNo + '.html',
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
	function initApplyBorrow(borrowId){
		var userId=$('#userId').val();
		if(borrowId=='0'){
			var title="借款表申请";
			var _url = '${path}/information/memberRegiste/initApplyBorrow.html?borrowId=0&userId='+userId;
		}else{
			var title="借款标修改";
			var _url = '${path}/information/memberRegiste/initApplyBorrow.html?borrowId='+borrowId+'&userId='+userId;
		}
		$.layer({
			type : 2,
			title : title,
			area : [ '80%', '80%' ],
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
	function initUpload(borrowId){
		var title="借款标资料上传";
		var userId=$('#userId').val();
		var _url = '${path}/information/memberRegiste/initUpload.html?borrowId='+borrowId+'&userId='+userId;
		$.layer({
			type : 2,
			title : title,
			area : [ '60%', '60%' ],
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
