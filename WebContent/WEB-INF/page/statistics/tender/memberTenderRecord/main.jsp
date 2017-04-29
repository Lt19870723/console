<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post" style="width: 100%;">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
		          用户名：<input id="userName" name="userName" class="input1" style="width: 150px;">&nbsp; 
		          投资类型：<select id="source" name="source" class="bigselect" style="width: 130px;"> 
				<option value="">--请选择--</option>
					<option value="新手宝">新手宝</option>
					<option value="一月宝">一月宝</option>
					<option value="三月宝">三月宝</option>
					<option value="六月宝">六月宝</option>
					<option value="十二月宝">十二月宝</option>
					<option value="散标">散标</option>
					<option value="债权转让">债权转让</option>
					<option value="直通车">直通车</option>
					<option value="直通车转让">直通车转让</option>
			</select>
			<br></div>
			投资日期：
				<input name="beginTime" id="beginTime" onclick="WdatePicker()" styleClass="Wdate" />
				 &nbsp;至 &nbsp; 
				<input name="endTime" id="endTime" onclick="WdatePicker()" styleClass="Wdate" />&nbsp;
			<input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					<input type="button"
					onclick="javascript:exportmemberTenderRecordData();" style="width: 140px;"
					type="button" name="cancel" id="cancel" class="b_buts"
					value="导出Excel" />
			</div>
		</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">
	
	function pageGo(pageNo) {
		var beginTime = $('#beginTime').val();
		if(null==beginTime || ""==beginTime){
			alert("请选择投资开始日期");
			return false;
		}
		var endTime = $('#endTime').val();
		if(null==endTime || ""==endTime){
			alert("请选择投资结束日期");
			return false;
		}
		var _load = parentLayer.load('处理中..');
		$("#loginLogForm").ajaxSubmit ({
			url : '${path}/tender/memberTenderRecord/list/'+pageNo+'.html',
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
	function exportmemberTenderRecordData(){
		var beginTime = $('#beginTime').val();
		if(null==beginTime || ""==beginTime){
			alert("请选择投资开始日期");
			return false;
		}
		var endTime = $('#endTime').val();
		if(null==endTime || ""==endTime){
			alert("请选择投资结束日期");
			return false;
		}
		 $("#loginLogForm").ajaxSubmit ({
			url : '${path}/tender/memberTenderRecord/count.html',
			type : 'post' ,
			dataType : 'json',
			async:false,
			success : function(result) {
				if(result.code == '0'){
					$("#loginLogForm").attr("action","${path}/tender/memberTenderRecord/export.html");
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
