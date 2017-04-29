<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>菜单管理-国诚金融后台管理系统</title>
</head>
<body>
	 <div style="margin-left:20px;line-height:50px;">
			 用户名： <input type="text" id="username" name="username" class="input1" style="width: 180px;" />&nbsp;
			&nbsp;
			申请日期：<input id="appBeginTime" class="input1"  onClick="WdatePicker();" style="width: 180px;" />
			至
			<input id="appEndTime"    class="input1"  onClick="WdatePicker();" style="width: 180px;" />
			<br/>
			审核状态：
			<select class="bigselect"  id="isPassed"  name="isPassed" >
						<option label="--请选择--" value="">--请选择--</option>
						<option label="等待审核" value="0">等待审核</option>
						<option label="审核通过" value="1">审核通过</option>
						<option label="审核不通过" value="-1">审核不通过</option>
			</select>
			真实姓名： <input type="text" id="realname" name="realname" class="input1" style="width: 180px;" />&nbsp;	
			&nbsp;			
	       <input id="searchrealNameApprs" name="searchrealNameApprs" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
	 </div>
	
	
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
				<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>真实姓名</th>
						<th>证件类型</th>
						<th>证件号码</th>
						<th>证件照片（正面）</th>
						<th>证件照片（反面）</th>
						<th>申请时间</th>
						<th>审核人</th>
						<th>审核操作</th>						
					</tr>
		</thead>
	</table>
	
	
		<div   id="approveRealNamePopup" width="500" height="200" style="width:98%;height:95%;padding:0px;display: none"  >
			 <input  type="hidden" id="realNameApproVoId" name="realNameApproVoId" value="${realNameApproAction.realNameApproVo.id}" />
		     <input  type="hidden"  id="realNameApproVoVersion" name="realNameApproVoVersion" value="${realNameApproAction.realNameApproVo.version}" />
					
			<table>
				<tr>
					<td width="100px">审核备注：</td>
					<td colspan="3" width="350px">
						<textarea id="approReason" style="width:350px;height:150px;">${realNameApproAction.realNameApproVo.reason}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input id="btnApproPass" name="btnApproPass" value="审核通过" type="button" onclick="submitCheck('pass');" />&nbsp;
						<input id="btnApproReject" name="btnApproReject" value="审核不通过" type="button" onclick="submitCheck('reject');" />&nbsp;
				    </td>
				</tr>
			</table>
      </div>	
	 
	
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
});



	function pageGo(pageNo) {
		var  _load = parentLayer.load('处理中..');
		$.ajax({
			url : '${path}/authenticte/realnameappro/list/' + pageNo + '.html',
			data : {
				'username' : $.trim($('#username').val()),
				'appBeginTimeStr' : $.trim($('#appBeginTime').val()),
				'appEndTimeStr' : $.trim($('#appEndTime').val()),
				'isPassed' : $.trim($('#isPassed').val()),
				'realname' : $.trim($('#realname').val())
			},
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$('#dataTable tbody').remove();
				$('#dataTable').append(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}


	
	function submitCheck(approInfo) {
		var remark = $('[id="approReason"]').val();
		if (remark == null || $.trim(remark) == "") {
			layer.msg("请填写备注信息！",1,5);
			return;
		}
		var alertInfo = "确定要审核通过吗?";
		if (approInfo == "reject") {
			alertInfo = "确定要审核不通过吗?";
		}
		layer.confirm(alertInfo,function() {
			var _load = layer.load('处理中..');
			$.ajax({
				url : '${path}/authenticte/realnameappro/approve.html',
				data : {
					'id' : $.trim($('#realNameApproVoId').val()),
					'version' : $.trim($('#realNameApproVoVersion').val()),
					'approReason' : $.trim($('#reason').val()),
					'isPassed' : $.trim($('#isPassed').val()),
					'check' : approInfo
				},
				type : 'post',
				success : function(result) {
					layer.close(_load);
					if (result.code == "1" && result.message == "success") {
						layer.msg("审核成功", 1, 1, function() {
							window.location.href = window.location.href;
						});
					} else {
						layer.msg(result.message, 1, 5);
					}
				},
				error : function(data) {
					layer.close(_load);
					layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				}
			});
		});
	}

	/**
	 * 进入审核页面
	 */
	function forApproveRealName(id, version) {
		//清空备注内容
		$('[id="approReason"]').val("");
		$('[id="realNameApproVoId"]').val(id);
		$('[id="realNameApproVoVersion"]').val(version);
		$.layer({
			type : 1, //0-4的选择,
			title : [ "审核操作", true ],
			closeBtn : [ 0, true ],
			area : [ "500", "300" ],
			offset : [ '100px', '' ],
			page : {
				dom : '#approveRealNamePopup'
			}
		});

	}

	/**
	 * 验证审核的备注内容
	 */
	function validateApprInfo(approInfo) {

		return false;

	}
</script>


</html>