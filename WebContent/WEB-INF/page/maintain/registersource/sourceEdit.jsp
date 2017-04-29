<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>注册来源设置-国诚金融后台管理系统</title>
</head>
<body>
<form id="sourceEditform">
<div id="tab1" style="height:430px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">渠道名称：</span>
			<input type="hidden" id="sourceNo" value="${sourceVo.sourceNo}"/>
			<div style="margin-top:10px;font-size:14px;">
			${sourceVo.sourceName}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">渠道开始时间：</span>
			<div style="margin-top:10px;font-size:14px;">
				<input id="startTime" name="startTime" onclick="WdatePicker()"
						styleClass="Wdate" value="${sourceVo.startTimeView}">
					<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			</div>
		</div>
		<div class="left1_input_ts">
			<span class="input_span">渠道结束时间：</span>
			<div style="margin-top:10px;font-size:14px;">
				<input  id="endTime" name="endTime"  value="${sourceVo.endTimeView}"  onclick="WdatePicker()"
					styleClass="Wdate">
					<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			</div>
		</div>
		<div style="clear:both;"></div>
		 <div class="left1_input_tst"  style="height:50px;">
			<span class="input_span" style="margin-top: 20px;">备注：</span>
				<div style="margin-top: 10px; font-size: 14px;">
					<textarea id="remark" class="remark" style="height:50px;width:80%;">${sourceVo.remark}</textarea>
				</div>
		</div>
			<div style="clear:both;height:50px;" ></div>
			<div class="left1_input_tst">
			<div class="savebutton">
				<input type="button" value="确定" onclick="saveSource()"/>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	function saveSource(){
		var sourceNo = $("#sourceNo").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var remark = $("#remark").val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/source/saveSouces.html',
			data : {
				sourceNo : sourceNo,
				endTimeView : endTime,
				startTimeView : startTime,
				remark :remark
				},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("保存成功",2,1,function(){
						window.parent.location=window.parent.location;
					});
					 
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.alert('网络连接超时,请您稍后重试.');
			}
		});
	}
</script>
</body>
</html>
