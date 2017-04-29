<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—用户账户详情</title>
<style type="text/css">
#checkBorrowPopupform td{
	height: 40px;
}
</style>
</head>
<body>
	<form id="checkBorrowPopupform" action="" method="post">
		<table width="99%" border="1" style="margin: 5px;">
			<tr>
				<td width="10%">用户名</td>
				<td bgcolor="#FFFFFF" width="22%">${MemberVo.username}</td>
				<td width="14%">真实姓名</td>
			    <td bgcolor="#FFFFFF" width="22%">${MemberVo.realName}</td>
				<td width="15%">手机号码</td>
				<td bgcolor="#FFFFFF" width="17%">${MemberVo.mobileNum}</td>
			</tr>
			<tr>
				<td>资产总额 </td>
				<td bgcolor="#FFFFFF">${MemberVo.totalStr}</td>
				<td>可用</td>
				<td bgcolor="#FFFFFF" >${MemberVo.useMoneyStr}</td>
				<td>可提</td>
				<td bgcolor="#FFFFFF" >${MemberVo.drawMoneyStr}</td>
			</tr>
			<tr>
				<td>不可提 </td>
				<td bgcolor="#FFFFFF">${MemberVo.noDrawMoneyStr}</td>
				<td>冻结 </td>
				<td bgcolor="#FFFFFF">${MemberVo.noUseMoneyStr}</td>
				<td>待收</td>
				<td bgcolor="#FFFFFF">${MemberVo.collectionStr}</td>
			</tr>
			<tr>
				<td>直通车可用</td>
				<td bgcolor="#FFFFFF">${MemberVo.firstBorrowUseMoneyStr}</td>
				<td>活期宝</td>
				<td bgcolor="#FFFFFF">${MemberVo.curTotalStr}</td>
				<td>定期宝</td>
				<td bgcolor="#FFFFFF">${MemberVo.fixTotalStr}</td>
			</tr>
			<tr>
				<td>注册日期</td>
				<td bgcolor="#FFFFFF" >${MemberVo.addtimeStr}</td>
				<td>最近登录</td>
				<td bgcolor="#FFFFFF">${MemberVo.lastlogintimeStr}</td>
				<td>累计登录次数</td>
				<td bgcolor="#FFFFFF">${MemberVo.logintimes}</td>
			</tr>
		</table>
	</form>
</body>
</html>
