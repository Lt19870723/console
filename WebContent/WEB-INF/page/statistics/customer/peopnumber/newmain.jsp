<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—新投资用户查询</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
		推广来源： <select id="source" name="source"
					class="bigselect" style="width: 120px;">
					 <option value="">--请选择--</option>
					<c:forEach items="${sources}" var="source">
					  <option value="${source.name}">${source.value}</option>
					</c:forEach>
				</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
			统计日期：
				<input name="beginTime" id="beginTime" onclick="WdatePicker()" styleClass="Wdate" />&nbsp;至&nbsp;
				<input name="endTime" id="endTime" onclick="WdatePicker()" styleClass="Wdate" />
			&nbsp;	
			 <input type="button" onclick="javascript:query();" type="button" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:exportnewMemberData();" style="width: 180px;"
					type="button" name="cancel" id="cancel" class="b_buts"
					value="新用户投资导出Excel" /> 
		</div>
	</form>

		<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">
	
	function query() {
		
		var beginTime = $('#beginTime').val();
		if(null==beginTime || ""==beginTime){
			alert("请选择查询开始时间");
			return false;
		}
		
		var endTime = $('#endTime').val();
		if(null==endTime || ""==endTime){
			alert("请选择查询结束时间");
			return false;
		}
		
		 var d1 = new Date(beginTime.replace(/\-/g, "\/"));  
		 var d2 = new Date(endTime.replace(/\-/g, "\/"));  
		 if( d1 >d2)  
		 {
			  alert("开始时间不能大于结束时间！");  
			  return false;  
		 }
		 pageGo(1);
	}
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/statistics/customer/peopcount/newlist/' + pageNo + '.html',
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
	 *导出数据
	 */
	function exportnewMemberData(){
		var beginTime = $('#beginTime').val();
		if(null==beginTime || ""==beginTime){
			alert("请选择查询开始时间");
			return false;
		}
		
		var endTime = $('#endTime').val();
		if(null==endTime || ""==endTime){
			alert("请选择查询结束时间");
			return false;
		}
		
		 var d1 = new Date(beginTime.replace(/\-/g, "\/"));  
		 var d2 = new Date(endTime.replace(/\-/g, "\/"));  
		 if( d1 >d2)  
		 {
			  alert("开始时间不能大于结束时间！");  
			  return false;  
		 }
		$("#queryForm").ajaxSubmit ({
			url : '${path}/statistics/customer/peopcount/count.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				if(result.code == '0'){
					$("#queryForm").attr("action","${path}/statistics/customer/peopcount/export.html");
			        $("#queryForm").submit();
				}else{
					alert(result.message);
				}
			},
			error : function(result) {
				alert(result.message);
			} 
		});
	}
</script>
</html>
