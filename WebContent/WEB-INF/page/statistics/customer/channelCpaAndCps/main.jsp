<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—用户分析-注册表单明细</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%;">
	<div id="loginLogCard">
	<div style="margin-left:20px;line-height:50px;">
		<div align="left"> 
			注册来源：<select id="source" name="source" class="bigselect" style="width: 100px;" onchange="sourceChange()"> 
				<option value="">--请选择--</option>
				<c:forEach items="${sourceList}" var="vo">
					<c:if test="${vo.name==17 or vo.name==13 or vo.name==60 or vo.name==37 or vo.name==38}">
						<option value="${vo.name }">${vo.value }</option>
					</c:if>					
				</c:forEach>
			</select>&nbsp;
			类型：<select id="sourceType" name="sourceType" class="bigselect" style="width: 100px;"> 
				<option value="">--请选择--</option>
				<option value="cpa">CPA</option>
				<option value="cps">CPS</option>
			</select>&nbsp;
			统计日期：
				<input name="begintotal" id="begintotal" onclick="WdatePicker()" styleClass="Wdate" />
				 &nbsp;至 &nbsp; 
				<input name="endtotal" id="endtotal" onclick="WdatePicker()" styleClass="Wdate" />&nbsp;
			<input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			<input type="button"
					onclick="javascript:exportCallBackData();" type="button" name="Submit1"
					id="export" class="b_buts" value="导出EXCEL" />
			</div>
		</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">
	
	function pageGo(pageNo) {
		var source=$("#source").val();
		var begintotal=$("#begintotal").val();
		var endtotal=$("#endtotal").val();
		var sourceType=$("#sourceType").val();		
		if(source==null || source==''){
			alert("请选择注册来源");
			return;
		}
		if(source=='17' || source=='13'){
			if(sourceType==null || sourceType==''){
				alert("请选择类型");
				return;
			}
		}
		if(begintotal==null || begintotal==''){
			alert("请选择开始日期");
			return;
		}
		if(endtotal==null || endtotal==''){
			alert("请选择结束日期");
			return;
		}
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/customer/channelCpaAndCps/list/' + pageNo + '.html',
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
	function exportCallBackData(){
		$("#queryForm").attr("action","${path}/customer/channelCpaAndCps/export.html");
        $("#queryForm").submit();
	}
	
	//渠道变更事件
	function sourceChange() {
		var source=$("#source").val();
		if(source=='17' || source=='13'){
			$("#sourceType").removeAttr("disabled");
		}else{
			$("#sourceType").val("");
			$("#sourceType").attr('disabled','true');			
		}		
	}
	
</script>
</html>
