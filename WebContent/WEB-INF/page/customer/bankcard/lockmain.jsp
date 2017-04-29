<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>菜单管理-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 50px;">
	 
	 <form id="bankCardLockform">
		<!-- 查询条件 -->
			<div style="margin-left:20px;line-height:50px;">
			用户名：<input type="text"  id="username"   class="input1"/>
				  &nbsp;
				  是否锁定：
			<select class="bigselect" id="status"  >
						<option label="--请选择状态--" value="-1"></option>
						<option label="锁定" value="0"></option>
						<option label="未锁定" value="1"></option>
			</select>	
	        <br/>
			用户资产总额：
				<input type="text"id="totalStart" 	  class="input1"/>
			至
				<input type="text"  id="totalEnd" 	   class="input1"/>
				&nbsp;	
				
			<input id="btnSearch" name="btnSearch" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
            <input id="exportToExcel" name="exportToExcel" value="导出" type="button" onclick="exportData();" />
			<br/>
			 
			 当前总共锁定银行卡的用户总数为：${bcLockNum}
			</div>
			</form>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				 		<th>
							序号
						</th>
					     <th>
							用户名
						 </th>
						 
						 <th>
							邮箱
						 </th>
						 
						 <th>
							手机
						 </th>
						 
						 <th>
							真实姓名
						 </th>
						 
						 <th>
							是否已经锁定银行卡
						 </th>
						 
						 <th>
						    已设置银行卡数量
						 </th>
						<th>
							 资产总额
						</th>
						<th>
							 待收总额
						</th>
						<th>
							可用金额
						</th>
						<th>
							 冻结金额
						</th>
					</tr>
		</thead>
	</table>
</body>

<script type="text/javascript">
$(function() {
	pageGo(1);
});

function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/customer/bankCardLock/list/' + pageNo + '.html',
		data : {
			'username' : $.trim($('#username').val()),
			'lockStatus' : $.trim($('#status').val()),
			'totalStart' : $.trim($('#totalStart').val()),
			'totalEnd' : $.trim($('#totalEnd').val())
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

 
/**
 * 验证导出数据
 */
function validateExportData(){
	var totalStart = $('[id="totalStart"]').val();
	var totalEnd = $('[id="totalEnd"]').val();
	var username =$('[id="username"]').val();
	var status = $('[id="status"]').val();
	if((totalStart.length == 0 || totalEnd.length == 0) && username.length==0 && status==-1){
		layer.msg('您没有选择任何条件，至少需要选择一个条件进行导出。', 1, 5);
		return false;
	}
	return true;
}


function  exportData(){
	validateExportData();
	
	 
	
}







  
</script>
</html>