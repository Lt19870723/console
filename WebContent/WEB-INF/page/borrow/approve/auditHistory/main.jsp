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
				value="${borrowAuditHistoryCnd.name}" />
			&nbsp;
			用户名：<input id="userName" class="input1" name="userName"
				value="${borrowAuditHistoryCnd.userName}" />
			&nbsp;
			借款标类型 ：
				  <select id="borrowtype" name="borrowtype"
					value="${borrowAuditHistoryCnd.borrowtype}"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">信用标</option>
	                    <option value="2">抵押标</option>
	                    <option value="5">担保标</option>
				  </select>
				  <br/>
			发布日期：
				<input
					name="beginPublicTimeStr" id="beginPublicTimeStr" onclick="WdatePicker()"
					styleClass="Wdate" value="${borrowAuditHistoryCnd.beginPublicTimeStr}">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
					 &nbsp;至&nbsp; 
				<input name="endPublicTimeStr" id="endPublicTimeStr"
					onclick="WdatePicker()" styleClass="Wdate"
					value="${borrowAuditHistoryCnd.endPublicTimeStr}">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
				   是否存管 ：
				  <select id="isCustody" name="isCustody"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="0">非存管</option>
	                    <option value="1">存管</option>
				  </select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			url : '${path}//approve/auditHistory/list/' + pageNo + '.html',
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
	
	 
	  
</script>
</html>
