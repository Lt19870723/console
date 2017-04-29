<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户管理-元宝商场-商品兑 换记录</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%">
		<div id="loginLogCard">
			<div style="margin-left: 20px; line-height: 50px;">
			          用户名：	<input id="username" class="class1"  name="username" style="width: 150px" /> &nbsp; 
		    	手机号码：	<input id="mobile" class="class1"  name="mobile" style="width: 150px" /> &nbsp; 
				短信内容：	<input id="smscontent" class="class1"  name="content" style="width: 150px" /> &nbsp; 
				发送状态：<select id="invokingStatus" class="class1"  name="invokingStatus" style="width: 150px">
				         <option value="">请选择</option>
				         <option value="成功">发送成功</option>
				         <option value="失败">发送失败</option>
				    </select>
				</br>
				 模板编号：	<input id="type" class="class1"  name="type" style="width: 150px" /> &nbsp; 
				发送时间：
				<input name="addtimeStart" id="addtimeStart" onclick="WdatePicker()" styleClass="Wdate" value=""><f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime></input>
			至
			   	<input name="addtimeEnd" id="addtimeEnd" onclick="WdatePicker()" styleClass="Wdate" value=""><f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime></input>
			供应商类型：<select id="vendorType" class="class1"  name="vendorType" style="width: 150px">
				         <option value="">请选择</option>
				         <option value="0">港奥资迅</option>
				         <option value="1">漫道</option>
				    </select>
				&nbsp; <input type="button" onclick="pageGo(1);" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
	</form>
	<div id="loginLogList" class="main_cent"></div>
</body>
<script type="text/javascript">
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/sms/smsRecord/list/'+pageNo+'.html',
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
</script>
</html>
