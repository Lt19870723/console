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
				商品名称：	<input id="username" class="input1" name="name" style="width: 150px" /> &nbsp; 
				用户名称：<input id="realname" class="input1" name="realname" style="width: 150px" /> &nbsp; 
				处理状态：
						<select name="dealStatus" id="type" class="bigselect">
							<option value="">不限</option>
							<option value="0">未处理</option>
							<option value="1">已处理</option>			 
						</select>
						<br/>
				兑换时间： 	<input name="exchangeStart" id="exchangeStart" onclick="WdatePicker()" styleClass="Wdate" value="">
							<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
						</input> 
						~
						<input name="exchangeEnd" id="exchangeEnd" onclick="WdatePicker()" styleClass="Wdate" value="">
							<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
						</input> 
				&nbsp; <input type="button" onclick="pageGo(1);" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
				<input type="button" onclick="exportCallBackData();" name="Submit1" id="subbtn" class="b_buts" value="导出  Excel" />
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
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/syceeExchange/list/'+pageNo+'.html',
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
	function exportCallBackData(){
	 
		/**if(!validateExportCallbackData()){
			return;
		}**/
		validateExportDataCount(); 
	}
	/**
	 *校验数据条数是否符合要求
	 */
	function validateExportDataCount(){
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/syceeExchange/count.html',
			type : 'post' ,
			dataType : 'json',
			async:false,
			success : function(result) {
				parentLayer.close(_load);
				if(result.code == '0'){
					//数据导出
					$("#queryForm").attr("action","${path}/account/syceeExchange/exportToExcel.html");
			        $("#queryForm").submit();
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
	function validateExportCallbackData(){
		var startTime = $('#exchangeStart').val();
		if(null==startTime || $.trim(startTime)==""){
			alert("起始兑换时间不能为空。");
			return false;
		}
		var endTime = $('#exchangeEnd').val();
		if(null==endTime || $.trim(endTime)==""){
			alert(" 结束兑换时间不能为空。");
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
	//审核处理
	function handle(id,opt){
		var _url = '${path}/account/syceeExchange/deal.html';
		if (id != '') {
			_url += '?id=' + id+'&opt='+opt;
		}
		$.layer({
			type : 2,
			title : '兑换商品处理',
			area : [ '70%', '85%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
	}
	function sendMsg(exchangeId){
		var _load = layer.load('处理中..');
		layer.confirm("确认发送短信吗？",function(){
			$.ajax({
				url : '${path}/account/syceeExchange/sendMsg.html?exchangeId='+exchangeId,
				type : 'post',
				dataType : 'json',
				success : function(result) {
					layer.close(_load);
					if (result.code == '1') {
						layer.msg(result.message, 1, 1);
					} else {
						layer.alert(result.message);
					}
				},
				error : function(result) {
					layer.msg('网络连接超时,请您稍后重试.', 1, 5);
					layer.close(_load);
				}
			});
		});
	}
</script>
</html>
