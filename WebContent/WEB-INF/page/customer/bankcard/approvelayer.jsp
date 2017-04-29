<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center" border="1" bordercolor="black" style="width:100%">
			
		<tr>
			<td align="right" width="20%">用户名：</td>
			<td width="30%" >${change.userName}</td>
			<td align="right" width="20%">证件类型：</td>
			<td width="30%" >${change.idCardType}</td>
		</tr>
		<tr>
			<td align="right">真实姓名：</td>
			<td>${change.realName}</td>
			<td align="right">证件号码：</td>
			<td>${change.idCard}</td>
		</tr>
		<tr>
			<td align="right">手机号：</td>
			<td>${change.phone}</td>
			<c:if test="${change.newBankcard==null}"> 
			<td align="right">解绑次数：</td>
			</c:if>
			<c:if test="${change.newBankcard!=null}"> 
			<td align="right">换卡次数：</td>
			</c:if>
			<td>${change.changeTimes}</td>
		</tr>
		<tr>
			<td align="right">本次申请时间：</td>
			<td>${change.addTimeStr}</td>
			<td align="right">累积申请次数：</td>
			<td>${change.applyTimes}</td>
		</tr>
		<tr>
			<td align="right">上次申请时间：</td>
			<td>${change.lastAddTimeStr}</td>
			<td align="right">累积点击申请次数：</td>
			<td>
				<a href="javascript:showclick('${change.userId }')">${change.clickTimes }</a>
			</td>
		</tr>
		<tr>
			<td align="right">银行卡号：</td>
			<td>${change.oldBankcard}</td>
			<c:if test="${change.newBankcard==null}"> 
			    <td align="right">解绑原因：</td>
				<td>${change.updateReason}</td>
			</c:if>
			<c:if test="${change.newBankcard!=null}"> 
			   <td align="right">新卡银行：</td>
			   <td>${change.newBank}</td>
			</c:if>
		</tr>
		<c:if test="${change.newBankcard!=null}"> 
		<tr>
		 <td align="right">新卡卡号：</td>
			<td>${change.newBankcard}</td>
			<td align="right">换卡原因：</td>
			<td>${change.updateReason}</td>
		</tr> 
		</c:if>
		<tr>
			<td align="right">备注：</td>
			<td colspan="3">${change.remark}</td>
		</tr>
		<tr>
			<td align="right">图片资料：</td>
			<td colspan="3">
			<c:forEach items="${pics}" var="p" varStatus="i">
				【${i.index+1 }】${p.picType }
				<a href="${portPath}/${p.picUrl}" target="_blank" title="点击查看大图"><img src="${portPath}/${p.picUrl}" width="300" height="100" align="top" style="padding-bottom: 10px;"/></a><br/>
			</c:forEach>
			</td>
		</tr>
		
		</table>
		
		<c:if test="${change.approveStatus != 0}">
			<font>初审信息</font>
			<table width="100%" align="center" class="solidTable" border="1" bordercolor="black">
				<tr>
					<td align="right" width="20%">审核人：</td>
					<td width="30%" >${change.firstAuditUserName}</td>
					<td align="right" width="20%">审核意见：</td>
					<td width="30%" >${change.approveStatus != -1 ? '初审通过':'初审不通过'}</td>
				</tr>
				<tr>
					<td align="right">初审时间：</td>
					<td colspan="3">${change.firstAuditTimeStr}</td>
				</tr>
				<tr>
					<td align="right">初审备注：</td>
					<td colspan="3">${change.approveRemark}</td>
				</tr>
			</table>
		</c:if>
		
		<c:if test="${change.approveStatus == 2 or change.approveStatus == -2}">
			<font>复审信息</font>
				<table width="100%" align="center" class="solidTable" border="1" bordercolor="black">
					<tr>
						<td align="right" width="20%">审核人：</td>
						<td width="30%" >${change.recheckUserName}</td>
						<td align="right" width="20%">审核意见：</td>
						<td width="30%" >${change.approveStatusStr}</td>
					</tr>
					<tr>
						<td align="right">复审时间：</td>
						<td colspan="3">${change.recheckTimeStr}</td>
					</tr>
					<tr>
						<td align="right">复审备注：</td>
						<td colspan="3">${change.recheckRemark}</td>
					</tr>
				</table>
		</c:if>
		
		<c:if test="${change.approveStatus == 0}">
			<table width="100%" align="center" class="solidTable" border="1" bordercolor="black">
				<tr>
					<td align="right">审核意见：<font color="red">*</font></td>
					<td>
						<select id="approveStatus" name="approveStatus" class="bigselect" style="width:130px;">
							<option value="1">通过</option>
							<option value="-1">不通过</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">审核备注：<font color="red">*</font></td>
					<td>
						<textarea rows="5" cols="50" id="approveRemark" name="approveRemark" style="width:90%;height:80px;"></textarea>
					</td>
				</tr>
				<tr>
					<td align="right">认证视频路径：</td>
					<td>
						<input id="verifyVedioPath" name="verifyVedioPath" style="width:90%;height:30px;" value=""/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						 <input type="button" onclick="javascript:validForm('${change.id}','${change.userId }');" type="button" name="Submit1" id="subbtn" class="b_buts" value="提交" />
					</td>
				</tr>
			</table>
		</c:if>
		<input type="hidden" value="${change.userId }" id="userId" name="userId" />
		<input type="hidden" value="${change.newBank }" id="newBank" name="newBank" />
		<input type="hidden" value="${change.newBankcard }" id="newBankcard" name="newBankcard"/>
		<input type="hidden" value="${change.newBankCode }" id="newBankCode" name="newBankCode"/>
		<input type="hidden" value="${change.newBranch }" id="newBranch" name="newBranch"/>
		<input type="hidden" value="${change.newBranchNo }" id="newBranchNo" name="newBranchNo"/>
		<input type="hidden" value="${change.oldBankcard }" id="oldBankcard" name="oldBankcard"/>
	</form>
</body>
<script type="text/javascript">
	function validForm(id,userId){
		var approveStatus = $('#approveStatus').val();
		if(approveStatus==undefined){
			alert("请填写审核意见");
			return false;
		}
		var approveRemark = $('#approveRemark').val();
		if(null==approveRemark || ""==$.trim(approveRemark)){
			alert("请填写审核备注");
			return false;
		}
		if(!confirm("你确定要提交吗?")){
			return false;
		}
		
		var urlPath = '${path}/bankCardChange/doFirstApprove/'+id+'/'+userId+'.html';
		$("#approForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result.code=='2'){
					alert(result.message);
					return false;
				}else{
					alert(result.message);
					window.parent.location.href = window.parent.location.href;
				}
			},
			error : function(data) {
				alert(result.message);
				return false;
			}
		});
	}
	
	function showclick(userId){
		var _url = '${path}/bankCardChange/clickLogs/'+userId+'.html';
		$.layer({
		type : 2,
		title : '累计点击申请详情',
		area : [ '800px', '400px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			//pageGo(1);
	    }
	});
	}
</script>
</html>
