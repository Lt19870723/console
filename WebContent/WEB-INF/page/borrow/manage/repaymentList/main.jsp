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
			标名称：<input id="name" class="input1" name="name" />
			&nbsp;
			用户名：<input id="username" class="input1" name="username" />
			&nbsp;
			垫付状态 ：
				  <select id="webstatus" name="webstatus"
					class="bigselect" style="width:130px;">
				  		<option value="-1">--请选择--</option>
				  		<option value="0">未垫付</option>
	                    <option value="1">已垫付</option>
	                   
				  </select>
		          还款状态  ：
				  <select id="status" name="status"
					class="bigselect" style="width:130px;">
				  		<option value="-1">--请选择--</option>
				  		<option value="0">未还款</option>
	                    <option value="1">已还款</option>
	                     
				  </select>
		        	  
				  <br/>
			应还日期：
					<input
						name="repaymentTimeBeginStr" id="repaymentTimeBeginStr" onclick="WdatePicker()"
						styleClass="Wdate" >
					 
					</input>
						 &nbsp;至&nbsp; 
					<input name="repaymentTimeEndStr" id="repaymentTimeEndStr"
						onclick="WdatePicker()" styleClass="Wdate">
					 
					</input>
			&nbsp;
			  借款标类型 ：
				  <select id="borrowtype" name="borrowtype"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">信用标</option>
	                    <option value="2">抵押标</option>
	                    <option value="5">担保标</option>
				  </select>	
				   是否存管 ：
				  <select id="isCustody" name="isCustody"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="0">非存管</option>
	                    <option value="1">存管</option>
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
		url : '${path}/borrow/manage/repaymentList/list/' + pageNo + '.html',
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
	validateExportDataCount();
}
/**
 *校验数据条数是否符合要求
 */
function validateExportDataCount(){
	$("#queryForm").ajaxSubmit ({
		url : '${path}/borrow/manage/repaymentList/count.html',
		type : 'post' ,
		dataType : 'json',
		success : function(result) {
			 
			if(result.code == '0'){
				//数据导出
				$("#queryForm").attr("action","${path}/borrow/manage/repaymentList/export.html");
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
 
	  
</script>
</html>
