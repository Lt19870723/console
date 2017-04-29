<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—菜单管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="memberForm" action="" method="post"  >
		<div id="customerCard"  style="padding-top:10px;">
			<div>				
				<input name="sortName" id="sortName" style="display: none;"
					value="${memberCnd.sortName }" /> <input name="sortMode"
					id="sortMode" style="display: none;" value="${memberCnd.sortMode }" />
				&nbsp;&nbsp;&nbsp;&nbsp;用户名： <input name="username" id="username" style="width: 150px;"
					class="input1" value="${memberCnd.username }" /> &nbsp; 注册日期： <input
					name="addtimeBegin" id="addtimeBegin" style="width: 150px;" onclick="WdatePicker()"
					styleClass="Wdate" value="${memberCnd.addtimeBegin }">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input> &nbsp;~&nbsp; <input name="addtimeEnd" id="addtimeEnd" style="width: 150px;"
					onclick="WdatePicker()" styleClass="Wdate"
					value="${memberCnd.addtimeEnd }">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input> 用户类型： <select id="usersType" name="usersType" value="${memberCnd.usersType }"
					class="bigselect" style="width: 120px;">
					<option value="1">理财用户</option>
					<option value="0">借款用户</option>
					<option value="">所有类型</option>
				</select>
				      用户来源 ：
				 <select name="memberSource"
					id="memberSource"
					class="bigselect" style="width:120px;">
					<option value="">--请选择--</option>
					<c:forEach items="${sources}" var="source">
					  <option value="${source.name}">${source.value}</option>
					</c:forEach>
				 </select>
				 <br />
				<br />
				真实姓名： <input name="realName" id="realName" class="input1" style="width: 150px;"
					value="${memberCnd.realName }" /> &nbsp; 邮箱： <input name="email" style="width: 150px;"
					id="email" class="input1" value="${memberCnd.email }" /> 手机号: <input
					name="mobileNum" id="mobileNum" class="input1" style="width: 150px;"
					value="${memberCnd.mobileNum }" /> &nbsp;&nbsp;用户状态： <select
					id="status" name="status" value="${memberCnd.status }" class="bigselect"
					style="width: 100px;">
					<option value="0">正常状态</option>
					<option value="-1">账号注销</option>
					<option value="-2">待审核</option>
					<option value="-3">审核不通过</option>
					<option value="">所有状态</option>
				</select>
                开立存管：
                <select id="isCustody" name="isCustody" value="${memberCnd.isCustody}"
                     class="bigselect" style="width: 100px;">
                    <option value="-1">--请选择--</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
                <br />
				<br />
				 资产总额：<input id="accountTotalStatrt" class="input1" name="accountTotalStatrt" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')" />至
				 <input id="accountTotalEnd" class="input1" name="accountTotalEnd" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')" />
				  可用余额：<input id="useTotalStatrt" class="input1" name="useTotalStatrt" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')" />至
				 <input id="useTotalEnd" class="input1" name="useTotalEnd" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')" />
				  活期宝总额：<input id="curTotalStatrt" class="input1" name="curTotalStatrt" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')" />至
				 <input id="curTotalEnd" class="input1" name="curTotalEnd" style="width: 130px;" onkeyup="value=value.replace(/[^\d\.]/g,'')" onblur="value=value.replace(/[^\d\.]/g,'')" />
				 <br/><br/>
				    <%--  红包金额：<input id="redmoney" class="input1" name="redmoney" />&nbsp;&nbsp;
		                         红包来源 ：
				 <select name="redsource"
					id="redsource"
					class="bigselect" style="width:130px;">
					<option value="">--请选择--</option>
					<c:forEach items="${redtypes}" var="redtype">
					  <option value="${redtype.name}">${redtype.value}</option>
					</c:forEach>
				 </select>&nbsp;&nbsp;
		                      红包状态 ：
				 <select name="redstatus"
					id="redstatus"
					class="bigselect" style="width:130px;">
					<option value="">--请选择--</option>
					<option value="1">未开启</option>
					<option value="2">未使用</option>
					<option value="3">已冻结</option>
					<option value="4">已使用</option>
					<option value="5">已过期</option>
				 </select>
				 <br/><br/>
		                   红包发放日期：
				<input
					name="beginTime" id="beginTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			至
				<input
					name="endTime" id="endTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
		   &nbsp;&nbsp;&nbsp;  红包使用日期：
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
				</input> <br/>
			</div>
			<div>
				<br /> --%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:serachCustomer();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:exportCallBackData();" style="width: 140px;"
					type="button" name="cancel" id="cancel" class="b_buts"
					value="客户回访导出Excel" /> <br />
				<br />
			</div>
		</div>
	</form>

	<div id="customerList" class="main_cent"></div>

</body>
<script type="text/javascript">

	/* $(function() {
		pageGo(1);
	}); */
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		if($("#username").val()==null||$("#username").val()==''){
			var addtimeBegin=$("#addtimeBegin").val();
			var addtimeEnd=$("#addtimeEnd").val();
			if(addtimeBegin==null || addtimeBegin==''){
				alert("注册起始日期不能为空！");
				return false;
			}
			if(addtimeEnd==null || addtimeEnd==''){
				alert("注册截止日期不能为空！");
				return false;
			}
		}
		var _load = parentLayer.load('处理中..');
		$("#memberForm").ajaxSubmit ({
			url : '${path}/customer/baseinfo/query/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#customerList").html(result);
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
	function serachCustomer(){
		pageGo(1);
	}
	/**
	* 字段排序
	*/
	function clickColumn(obj,v){

		var str = $.trim($($(obj).children('span')[0]).html());
		
		$('#sortName').val(v);
		if(str==undefined || str=='' || str=='↑'){
			$('#sortMode').val('DESC');
		}else  if(str=='↓') {
			$('#sortMode').val('ASC');
		}
		pageGo(1);
		return false;
	}
	
	/**
	 *导出数据
	 */
	function exportCallBackData(){
	/* 	if(!validateExportCallbackData()){
			return;
		} */
		validateExportDataCount();
	}
	
	/**
	 *校验数据条数是否符合要求
	 */
	function validateExportDataCount(){
	 if($("#username").val()==null||$("#username").val()==''){
		var addtimeBegin=$("#addtimeBegin").val();
		var addtimeEnd=$("#addtimeEnd").val();
		if(addtimeBegin==null || addtimeBegin==''){
			alert("注册起始日期不能为空！");
			return false;
		}
		if(addtimeEnd==null || addtimeEnd==''){
			alert("注册截止日期不能为空！");
			return false;
		}
	 }
		$("#memberForm").ajaxSubmit ({
			url : '${path}/customer/baseinfo/count.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				if(result.code == '0'){
					exportData();
				}else{
					alert(result.message);
				}
			},
			error : function(result) {
				alert(result.message);
			} 
		});
	}
	
	/**
	 *数据导出
	 */
	function exportData(){
		$("#memberForm").attr("action","${path}/customer/baseinfo/export.html");
        $("#memberForm").submit();
	}
	/**
	 *校验导出数据的过滤条件
	 */
	function validateExportCallbackData(){
		var startTime = $('#addtimeBegin').val();
		if(null==startTime || $.trim(startTime)==""){
			alert("请选择注册日期开始时间");
			return false;
		}
		var endTime = $('#addtimeEnd').val();
		if(null==endTime || $.trim(endTime)==""){
			alert("请选择注册日期开始时间");
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
	function detail(userId) {
		var _url = '${path}/customer/baseinfo/redMain.html';
		if (userId != '') {
			_url += '?userId=' + userId;
		}
		$.layer({
			type : 2,
			title : '查看我的红包详情',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
</script>
</html>
