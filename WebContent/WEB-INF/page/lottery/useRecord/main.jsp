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
			日期：
					<input name="beginDateStr" id="beginDateStr" onclick="WdatePicker()"
						styleClass="Wdate" >
					 
					</input>
						 &nbsp;至&nbsp; 
					<input name="endDateStr" id="endDateStr"
						onclick="WdatePicker()" styleClass="Wdate">
					 
					</input>
			&nbsp; 
			用户名：<input name="userName" id="userName" class="input1"   />
			<br/>
		         奖品类型 ：
				  <select id="awardType" name="awardType"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">现金</option>
	                    <option value="2">实物</option>
	                    <option value="3">谢谢参与</option>
	                    <option value="4">抽奖机会</option>
	                    <option value="5">红包</option>
	                    <option value="6">元宝</option>
				  </select>	
			来自活动 ：
				  <select id="awardSource" name="awardSource"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<c:forEach items="${chanceRuleInfosInsert }" var="vo">
				  		<option value="${vo.value }">${vo.label }</option>
				  		</c:forEach>                 
				  </select>
		          奖品  ：
				  <select id="lotteryGoodsName" name="lotteryGoodsName"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  	    <c:forEach items="${goodSelectItems }" var="vo">
				  		<option value="${vo.value }">${vo.label }</option>
				  		</c:forEach>         
				  </select>
		        	  
			  	&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					&nbsp;&nbsp;
					<input type="button"
					onclick="javascript:exportCallBackData();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="导出 Excel" />
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">
var _load;
$(function() {
	pageGo(1);
});

/**
*翻页功能
*/
function pageGo(pageNo) {
	  _load = parentLayer.load('处理中..');
	$("#queryForm").ajaxSubmit ({
		url : '${path}/lottery/useRecord/list/' + pageNo + '.html',
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
function exportCallBackData(){
	if(!validateExportCallbackData()){
		return;
	}
	validateExportDataCount();
}
/**
 *校验数据条数是否符合要求
 */
function validateExportDataCount(){
	$("#queryForm").ajaxSubmit ({
		url : '${path}/lottery/useRecord/count.html',
		type : 'post' ,
		dataType : 'json',
		success : function(result) {
			 
			if(result.code == '0'){
				//数据导出
				$("#queryForm").attr("action","${path}/lottery/useRecord/export.html");
		        $("#queryForm").submit();
			}else{
				parentLayer.close(_load);
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			parentLayer.close(_load);
		} 
	});
}
/**
 *校验导出数据的过滤条件
 */
function validateExportCallbackData(){
	var startTime = $('#beginDateStr').val();
	if(null==startTime || $.trim(startTime)==""){
		alert("日期开始时间不能为空。");
		return false;
	}
	var endTime = $('#endDateStr').val();
	if(null==endTime || $.trim(endTime)==""){
		alert("日期截止日期不能为空。");
		return false;
	}
	
	//比较日期，必须是30天之内（含）
	startTime = startTime.replace(/-/g, "/"); 
	endTime = endTime.replace(/-/g, "/"); 
	startTime = new Date(startTime); 
	endTime = new Date(endTime);
	
	var days= endTime.getTime() - startTime.getTime(); 
	var time = parseInt(days / (1000 * 60 * 60 * 24));
	if(isNaN(time) || (time < 0) || time>30){
		alert("日期段天数须在30天以内！");
		return false;
	}	
	
	if(!confirm("确认要导出Excel吗？")){
		return false;
	}
	return true;
} 
   
	  
</script>
</html>
