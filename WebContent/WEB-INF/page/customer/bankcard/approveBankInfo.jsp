<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>换卡审核-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<font>申请信息</font>
	<table width="100%" align="center" class="solidTable">
		<tr>
			<td align="right" width="20%">用户名：</td>
			<td width="30%" >#{bankcardChangeAction.change.userName}</td>
			<td align="right" width="20%">证件类型：</td>
			<td width="30%" >#{bankcardChangeAction.change.idCardType}</td>
		</tr>
		<tr>
			<td align="right">真实姓名：</td>
			<td>#{bankcardChangeAction.change.realName}</td>
			<td align="right">证件号码：</td>
			<td>#{bankcardChangeAction.change.idCard}</td>
		</tr>
		<tr>
			<td align="right">手机号：</td>
			<td>#{bankcardChangeAction.change.phone}</td>
			<td align="right">换卡次数：</td>
			<td>#{bankcardChangeAction.change.changeTimes}</td>
		</tr>
		<tr>
			<td align="right">本次申请时间：</td>
			<td>#{bankcardChangeAction.change.addTimeStr}</td>
			<td align="right">累积申请次数：</td>
			<td>#{bankcardChangeAction.change.applyTimes}</td>
		</tr>
		<tr>
			<td align="right">上次申请时间：</td>
			<td>#{bankcardChangeAction.change.lastAddTimeStr}</td>
			<td align="right">累积点击申请次数：</td>
			<td>
				<a4j:commandLink value="#{bankcardChangeAction.change.clickTimes}" 
					action="#{bankcardChangeAction.clickLogs}" execute="@this" data="#{bankcardChangeAction.clicks}"
					oncomplete="#{rich:component('clickPopup')}.show();" render="clickForm" >
					<a4j:param name="userId" value="#{bankcardChangeAction.change.userId}" assignTo="#{bankcardChangeAction.clickCnd.userId}"/>
				</a4j:commandLink>
			</td>
		</tr>
		<tr>
			<td align="right">原银行卡号：</td>
			<td>#{bankcardChangeAction.change.oldBankcard}</td>
			<td align="right">新卡银行：</td>
			<td>#{bankcardChangeAction.change.newBank}</td>
		</tr>
		<tr>
			<td align="right">新卡卡号：</td>
			<td>#{bankcardChangeAction.change.newBankcard}</td>
			<td align="right">换卡原因：</td>
			<td>#{bankcardChangeAction.change.updateReason}</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td colspan="3">#{bankcardChangeAction.change.remark}</td>
		</tr>
		<tr>
			<td align="right">图片资料：</td>
			<td colspan="3">
				<a4j:repeat value="#{bankcardChangeAction.pics}" var="p" rowKeyVar="i">
					【#{i+1}】#{p.picType}
					<a href="#{bankcardChangeAction.portPath}/#{p.picUrl}" target="_blank" title="点击查看大图"><img src="#{bankcardChangeAction.portPath}/#{p.picUrl}" width="300" height="100" align="top" style="padding-bottom: 10px;"/></a><br/>
				</a4j:repeat>
			</td>
		</tr>
	</table>
	<table width="100%" align="center">
		<tr>
			<td align="right">审核意见：<font color="red">*</font></td>
			<td>
				<h:selectOneRadio id="approveStatus" name="approveStatus" required="true" style="border:0px;" value="#{bankcardChangeAction.change.approveStatus}">
					<f:selectItem itemLabel="通过" itemValue="1" style="border:0px;"></f:selectItem>
					<f:selectItem itemLabel="不通过" itemValue="-1" style="border:0px;"></f:selectItem>
				</h:selectOneRadio>
			</td>
		</tr>
		<tr>
			<td align="right">审核备注：<font color="red">*</font></td>
			<td>
				<h:inputTextarea id="approveRemark" name="approveRemark" style="width:90%;height:80px;" value="#{bankcardChangeAction.change.approveRemark}"></h:inputTextarea>
			</td>
		</tr>
		<tr>
			<td align="right">认证视频路径：</td>
			<td><h:inputText id="verifyVedioPath" name="verifyVedioPath" style="width:90%;height:30px;" value="#{bankcardChangeAction.change.verifyVedioPath}"></h:inputText></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a4j:commandButton value="提交" onclick="return validForm();" oncomplete="afterApprove(event.data)" data="#{bankcardChangeAction.msg}" action="#{bankcardChangeAction.doFirstApprove}" execute="@form">
					<a4j:param value="#{bankcardChangeAction.change.id}" assignTo="#{bankcardChangeAction.change.id}" />
				</a4j:commandButton> 
			</td>
		</tr>
	</table>
	
</form>
</body>
<script language="javascript">
</script>
</html>