<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/addr.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单管理-国诚金融后台管理系统</title>
</head>
<body>
	 <div style="margin-left:20px;margin-top:10px;">
     <form id="queryForm" action="" method="post" >
			联系电话： <input type="text" id="tel" name="tel" class="input1" style="width:150px;" />&nbsp;
			&nbsp;
			抵押类型：
			<select class="bigselect" style="width:100px;"  id="mortgageType"  name="mortgageType" >
						<option  value="">--请选择--</option>
						<option  value="1">房产抵押</option>
						<option  value="2">车产抵押</option>
						<option  value="3">民间抵押</option>
			</select>
			&nbsp;
			所属省：
			<select id="residenceProvince" name="provinceCode" 
			      class="bigselect" style="width:100px;" >
			      <option value="">--请选择--</option>
		    </select>
			&nbsp;
			所属市：
			<select id="residenceCity" name="cityCode"
			      class="bigselect" style="width:100px;"  >
					<option value="">--请选择--</option>
		    </select> 
			&nbsp;
			审核状态：
			<select class="bigselect" style="width:100px;"  id="approveStatus"  name="approveStatus" >
			            <option  value="">--请选择--</option>
						<option  value="1">待审核</option>
						<option  value="2">审核通过</option>
						<option  value="3">审核不通过</option>
			</select>	
			&nbsp;	
	       <input id="searchrealNameApprs" class="b_buts" name="searchrealNameApprs" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
	</form>		
	 </div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
				<tr>
						<th width="4%">序号</th>
						<th width="6%">联系人</th>
						<th width="12%">联系电话</th>
						<th width="8%">QQ</th>
						<th width="8%">抵押类型</th>
						<th width="10%">融资金额</th>
						<th width="4%">融资期限</th>
						<th width="8%">所属省</th>
						<th width="8%">所属市</th>
						<th width="6%">审核状态</th>
						<th width="16%">提交时间</th>
						<th width="6%">操作</th>
					</tr>
		</thead>
	</table>
	
	
		<div  id="approveRealNamePopup" width="500" height="200" style="width:98%;height:95%;padding:0px;display: none;"  >
		    <input type="hidden" id="id"/>   	
			<table>
				<tr>
					<td width="100px">审核备注：</td>
					<td colspan="3" width="350px">
						<textarea id="approReason"   style="width:350px;height:150px;"></textarea>
					</td>
				</tr>
				<tr align="center">
					<td colspan="3">
						<input id="btnApproPass" name="btnApproPass" value="审核通过" type="button" onclick="submitCheck(2);" />&nbsp;
						<input id="btnApproReject" name="btnApproReject" value="审核不通过" type="button" onclick="submitCheck(3);" />&nbsp;
				    </td>
				</tr>
			</table>
      </div>
      <div   id="viewPopup" width="500" height="200" style="width:98%;height:95%;padding:0px;display: none"  >
			 <input  type="hidden" id="realNameApproVoId" name="realNameApproVoId" value="${realNameApproAction.realNameApproVo.id}" />
		     <input  type="hidden"  id="realNameApproVoVersion" name="realNameApproVoVersion" value="${realNameApproAction.realNameApproVo.version}" />
					
			<table>
				<tr>
					<td width="100px">审核备注：</td>
					<td colspan="3" width="350px">
						<textarea    id="approReason1" style="width:350px;height:150px;">
						  
						</textarea>
					</td>
				</tr>
				<tr>
					<td width="100px">审核人：</td>
					<td colspan="3" width="350px">
						  <span id="approName"></span>
					</td>
				</tr>
				<tr>
					<td width="100px">审核时间：</td>
					<td colspan="3" width="350px">
						 <span id="approTime"></span>
					</td>
				</tr>
			</table>
      </div>	
	 
	
</body>
<script type="text/javascript">
$(function() {
	//初始化省市区
	initPCA('residenceProvince','residenceCity'); 
	pageGo(1);
});

function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$("#queryForm").ajaxSubmit ({
		url : '${path}/authenticte/financing/list/' + pageNo + '.html',
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
 
function submitCheck(result1){
		if(validateApprInfo(result1)){
		var  _load = parentLayer.load('处理中..');
			$.ajax({
				url : '${path}/authenticte/financing/approve.html',
				data : {
					'id' : $.trim($('#id').val()),
					'remark' : $.trim($('#approReason').val()),
					'status':result1
				},
				type : 'post',
				success : function(result) {
				     parentLayer.close(_load);
					 if (result.code=="1") {
						 $("#approveRealNamePopup").hide();
						 layer.closeAll('page'); //关闭所有页面层
						 pageGo(1);
					}else{
						layer.msg(result.message, 1, 5);
					}
				},
				error : function(data) {
					layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				}
			});
		}
	}




	/**
	 * 进入审核页面
	 */
	function forApproveRealName(id){
		//清空备注内容
		$('[id="approReason"]').val("");
		$('[id="id"]').val(id);
		$.layer({
			type: 1,   //0-4的选择,
			title:  ["审核操作" , true],
			closeBtn: [0, true],
			area: ["500","300"],
			offset : [ '100px', '' ],
			page: {
				dom : '#approveRealNamePopup'
			}
		});
		
	}
	/**
	 * 进入查看页面
	 */
	function viewRealName(id){
		
		$.ajax({
			url : '${path}/authenticte/financing/view.html',
			data : {
				'id' : id
			},
			type : 'post',
			success : function(result) {
				 
				 $.layer({
						type: 1,   //0-4的选择,
						title:  ["审核信息" , true],
						closeBtn: [0, true],
						area: ["500","300"],
						offset : [ '100px', '' ],
						page: {
							dom : '#viewPopup'
						}
				  });
			      $('#approReason1').text(result.approveRemark);
			      $('#approName').text(result.name);
			      $('#approTime').text(result.approveTime);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
		 
		
		
	}

	/**
	 * 验证审核的备注内容
	 */
	function validateApprInfo(approInfo){
	
		var remark = $('[id="approReason"]').val();
		if(remark==null || $.trim(remark)==""){
			layer.msg("请填写备注信息！");
			return false;
		}
		var alertInfo = "确定要审核通过吗?";
		if(approInfo==3){
			alertInfo = "确定要审核不通过吗?";
		}
		if(!confirm(alertInfo)){
			return false;
		}
		return true;
	}
</script>


</html>