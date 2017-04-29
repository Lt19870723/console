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
	<form id="loginLogForm" action="" method="post" style="width: 100%;">
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
				 <br/>
		      发放日期：
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
		   &nbsp;&nbsp;&nbsp;  使用日期：
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
			<input type="button" onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
					onclick="javascript:exportRedRecordData();" style="width: 140px;"
					type="button" name="cancel" id="cancel" class="b_buts"
					value="红包记录导出Excel" />
			</div>
		</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">

	/* $(function() {
		pageGo(1);
	}); */
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#loginLogForm").ajaxSubmit ({
			url : '${path}/red/list/' + pageNo + '.html',
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
			url : '${path}/red/count.html',
			type : 'post' ,
			dataType : 'json',
			async:false,
			success : function(result) {
				if(result.code == '0'){
					$("#loginLogForm").attr("action","${path}/red/export.html");
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
	/**
	 *校验导出数据的过滤条件
	 */
	function validateExportRedRecordData(){
		var beginTime = $('#beginTime').val();
		if(null==beginTime || $.trim(beginTime)==""){
			alert("请选择红包发放开始时间");
			return false;
		}
		var endTime = $('#endTime').val();
		if(null==endTime || $.trim(endTime)==""){
			alert("请选择红包发放截止时间");
			return false;
		}
		
		//比较日期，必须是30天之内（含）
		beginTime = beginTime.replace(/-/g, "/"); 
		endTime = endTime.replace(/-/g, "/"); 
		beginTime = new Date(beginTime); 
		endTime = new Date(endTime);
		
		var days= endTime.getTime() - beginTime.getTime(); 
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
	function forrecorddetail(userId) {
		var _url = '${path}/red/findUseAccountDetail.html';
		if (userId != '') {
			_url += '?userId=' + userId;
		}
		$.layer({
			type :2,
			title : '显示用户账户信息',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end : function() {
				parentLayer.close(_load);
			}
		});
	}
</script>
</html>
