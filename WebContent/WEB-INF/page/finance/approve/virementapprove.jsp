<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内部转账-国诚金融后台管理系统</title>
</head>
<body>
   <form id="approForm" name="" method="post">
     <input type="hidden" id="id" name="id" value="${moneyOperation.id}" />
     <div class="listzent">审核(*必填)</div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
		    <tr>
		       <td align="right" width="25%">申请人：</td>
		       <td colspan="3">${moneyOperation.adduser}</td>
		    </tr>
			<tr>
				<td align="right" width="25%">汇款金额：</td>
				<td ><fmt:formatNumber value="${moneyOperation.operationrMoney }" pattern="#,##0.00"></fmt:formatNumber></td>
				<td align="right" width="25%">业务发生时间：</td>
				<td ><fmt:formatDate value="${moneyOperation.businessTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td align="right" width="25%">付款人：</td>
				<td >${moneyOperation.payerName}</td>
				<td align="right" width="25%">收款人：</td>
				<td>${moneyOperation.payeeName}</td>
			</tr>
			<c:if test="${moneyOperation.outType == 2 }">
			<tr>
				<td align="right" width="25%">付款支行：</td>
				<td >${moneyOperation.payerSubbranch}</td>
				<td align="right" width="25%">收款支行：</td>
				<td>${moneyOperation.payeeSubbranch}</td>
			</tr>
			</c:if>
			<tr>
				
				<c:if test="${moneyOperation.outType == 2 }">
					<td align="right" width="25%">付款银行：</td>
					<td>${moneyOperation.payerBankName}</td>
				</c:if>
			<c:if test="${moneyOperation.inType == 2 }">
				<td align="right" width="25%">收款银行：</td>
				<td >${moneyOperation.payeeBankName }</td>
			</c:if>				
			</tr>
			<tr>
				<c:if test="${moneyOperation.outType == 2 }">
					<td align="right" width="25%">付款银行卡号：</td>
					<td>${moneyOperation.payerCardNo}</td>
				</c:if>
				
				
				<c:if test="${moneyOperation.inType == 2 }">
				<td align="right" width="25%">收款银行卡号：</td>
				<td >${moneyOperation.payeeCardNo}</td>
				</c:if>
				</tr>
		
			<tr>
					<td align="right" width="25%">付款方式：</td>
				<td>
					<c:choose>
						<c:when test="${moneyOperation.outType==2}">银行卡转账</c:when>
						<c:when test="${moneyOperation.outType==1}">现金</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td align="right" width="25%">收款方式：</td>
				<td >
					<c:choose>
						<c:when test="${moneyOperation.inType==2}">银行卡转账</c:when>
						<c:when test="${moneyOperation.inType==1}">现金</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
			
			</tr>
			<tr>
				<td align="right" width="25%">备注：</td>
				<td colspan="3">
				 ${moneyOperation.remark }
				</td>
			</tr>
			<tr>
				<td align="right" width="25%">*审核时间：</td>
				<td colspan="3"><input type="text" id="businessTime" name="businessTime"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/></td>
			</tr>
			<tr>
				<td align="right" width="25%">*审核意见：</td>
				<td colspan="3">
				 <textarea cols="100" rows="5" id="remark" style="width:100%;height:80px;" name="remark"></textarea>
				</td>
			</tr>
			
			<tr>
			<td align="right">是否上传凭证:</td>
			<td>
				<input type="radio" name="filename" class="J_ifuploadfile" value="1"/>是
				<input type="radio" name="filename" class="J_ifuploadfile" checked="checked"  value="2"/>否
			</td>
			</tr>
			
			<tr style="display: none;" id="J_fileupload">
			<td align="right">证件类型:</td>
			<td>
				<select name="attachmentType" id="attachmentType" style="width: 100px;">
					<option value="">请选择</option>
					<option value="1">原件</option>
					<option value="2">复印件</option>
					<option value="3">涂改件</option>
				</select>
				<input type="hidden" id="attachmentUrl" name="attachmentUrl" value="">
				<input type="hidden" id="attachmentName" name="attachmentName" value="">
			</td>
			</tr>
		
		</table>			
 </form>

<div style="margin-left: 200px;display: none;" id="J_submitfile">
	<img id="oneimageId" style="width:200px;height:100px;" src=""></img>
	<form  id="myfrom"   name="myfrom" method="post" >
	<div id="fileupload">
		<input  class="rz_input mr5" type="file" name="files" id="files" dataType="Require" msg="请选择图片！"  maxlength="180"/>
		<input  class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadPic('myfrom')" value="上传凭证" />
	</div>	
	</form>
</div>


<div style="margin-top: 50px;margin-left: 300px;">
	<input type="button" id="yesBtn" name="yesBtn" value="通过" onclick="audit(1)" />
	<input type="button" id="noBtn" name="noBtn" value="拒绝" onclick="audit(2)" style="margin-left: 50px;margin-bottom: 50px;"/>
</div>

				

 </body>
 <script type="text/javascript">
 
 	$(".J_ifuploadfile").click(function(){
 		if($(this).val()==1){
 			$("#J_submitfile").show();
 			$("#J_fileupload").show();
 		}
 		if($(this).val()==2){
 			
 			$("#J_submitfile").hide();
 			$("#J_fileupload").hide();
 			$("#attachmentUrl").val('');
 			$("#attachmentName").val('');
 			$("#attachmentType").val('');
 		}
	});
 
 
 function audit(type){
	 var businessTime = $("#businessTime").val();
	 var remark = $("#remark").val();
	 if(businessTime=='' || remark==''){
		 layer.msg('审核时间和审核意见都不能为空！', 1, 5);
 		return false;
	 }
 	var _load = layer.load('处理中..');
 	var urlPath = '${path}/finance/interTransfer/audit/' + type + '.html';
		$("#approForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.close(_load);
				layer.msg(result.message , 1, 1);
				  parent.window.location.href="${path}/finance/interTransfer/toAudit.html";
				 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
              parent.layer.close(index);
			},
			error : function(data) {
				layer.msg(result.message , 1, 5);
			}
		});
 }
 
 
 function uploadPic(form){
		var files = $('#files').val();
		if(null==files || $.trim(files)==""){
			alert("请选择需要上传的图片!!");
			return false;
		}
		
		$("#"+form).ajaxSubmit ({
			url : '${path}/finance/interTransfer/upload.html',
			type : 'post',
			dataType : 'json',
			data :{
			},
			success : function(result) {
				if (result.code == '0') {
					var s=result.message;
					var m =s.split(",");
						$("#attachmentUrl").val(m[0]);
						$("#attachmentName").val(m[1]);
						$('#oneimageId').attr("src", '${path}' + m[0]);
						layer.alert('上传成功!!',1);
				}else{
					layer.msg(result.message,1);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
 
 </script>
</html>