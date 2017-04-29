<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—红包管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post" style="width:  100%;">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
		     用户名：<input id="username" class="input1" name="username"/>&nbsp;&nbsp;
		     红包金额：<input id="redmoney" class="input1" name="redmoney"/>&nbsp;&nbsp;
		     红包来源 ：
				 <select name="redsource"
					id="redsource"
					class="bigselect" style="width:130px;">
					<option value="">--请选择--</option>
					<c:forEach items="${redtypes}" var="redtype">
					  <option value="${redtype.name}">${redtype.value}</option>
					</c:forEach>
				 </select>&nbsp;&nbsp;<br/>
		      使用日期：
				<input
					name="usebeginTime" id="usebeginTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			至
				<input
					name="useendTime" id="useendTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
				
			<input type="button" onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
					onclick="javascript:exportRedRecordData();" style="width: 165px;"
					type="button" name="cancel" id="cancel" class="b_buts"
					value="红包使用记录导出Excel" />
			</div>
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
			url : '${path}/reduse/list/' + pageNo + '.html',
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
	 *导出数据
	 */
	function exportRedRecordData(){
		/* if(!validateExportRedRecordData()){
			return;
		} */
		 $("#loginLogForm").ajaxSubmit ({
			url : '${path}/reduse/count.html',
			type : 'post' ,
			dataType : 'json',
			async:false,
			success : function(result) {
				if(result.code == '0'){
					$("#loginLogForm").attr("action","${path}/reduse/export.html");
			        $("#loginLogForm").submit();
				}else{
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
				parentLayer.close(_load);
			} 
		}); 
		
	}
</script>
</html>
