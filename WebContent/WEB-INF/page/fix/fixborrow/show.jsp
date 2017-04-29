<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>定期宝管理-定期宝列表-查看详情</title>
</head>
<body style="background: #fff;">
	<form action="" id="firstAddform" style="background: #fff;">
		<table style="width: 98%; margin: 10 auto; position: relative; float: none; padding: 0; text-align: left;" border="1">
		 	<tr>
				<td width="20%">定期宝ID：</td>
				<td width="30%">${fixBorrowVo.id}</td>
				<td width="20%">定期宝编号：</td>
				<td>${fixBorrowVo.contractNo}</td>
			</tr>
		    <tr>
				<td width="20%">定期宝名称：</td>
				<td width="30%">${fixBorrowVo.name}</td>
				<td width="20%">定期宝状态：</td>
				<td>${fixBorrowVo.speicStatusStr1 }</td>
			</tr>
			 <tr>
				<td width="20%">实际投宝金额：</td>
				<td width="30%">${fixBorrowVo.accountYes/10000}万元</td>
				<td width="20%">计划详细说明：</td>
				<td>${fixBorrowVo.content}</td>
			</tr>
			 <tr>
				<td width="20%">完成后是否发送站内信：</td>
				<td width="30%">
				<c:if test="${fixBorrowVo.sendMessage==0}">不发送</c:if>
				<c:if test="${fixBorrowVo.sendMessage==1}">发送</c:if>
				</td>
				<td width="20%">发宝时间：</td>
				<td><fmt:formatDate value='${fixBorrowVo.addTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			</tr>
			 <tr>
				<td width="20%">最后修改人ID：</td>
				<td width="30%">
				<c:if test="${fixBorrowVo.lastModifyUser==0}">发标人自己撤销</c:if>
				<c:if test="${fixBorrowVo.lastModifyUser==-1}">系统流标</c:if>
				<c:if test="${fixBorrowVo.lastModifyUser!=-1 and fixBorrowVo.lastModifyUser!=0}">${fixBorrowVo.lastModifyUser}</c:if>
				</td>
				<td width="20%">最后修改人时间：</td>
				<td><fmt:formatDate value='${fixBorrowVo.lastModifyTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			</tr>
			 <tr>
				<td width="20%">最后修改人备注：</td>
				<td width="30%">${fixBorrowVo.lastModifyRemark}</td>
				<td width="20%">投宝次数：</td>
				<td>${fixBorrowVo.tenderTimes}</td>
			</tr>
			 <tr>
				<td width="20%">宝来源：</td>
				<td width="30%">
				<c:if test="${fixBorrowVo.sourceFrom==1}">新发宝</c:if>
				<c:if test="${fixBorrowVo.sourceFrom==2}">接手宝</c:if>
				</td>
				<td width="20%">宝类型：</td>
				<td>
				<c:if test="${fixBorrowVo.areaType==0}">普通宝</c:if>
				<c:if test="${fixBorrowVo.areaType==1}">新手宝</c:if>
				</td>
			</tr>
			 <tr>
				<td width="20%">新手专区转普通专区时间：</td>
				<td width="30%"><fmt:formatDate value='${fixBorrowVo.areaChangeTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td width="20%">锁定结束日期：</td>
				<td><fmt:formatDate value='${fixBorrowVo.lockEndTime}' pattern='yyyy-MM-dd' /></td>
			</tr>
			<tr>
				<td>开放额度：</td>
				<td>${fixBorrowVo.planAccount/10000}万元</td>
				<td>锁定期限：</td>
				<td>${fixBorrowVo.lockLimit}月</td>
			</tr>
			<tr>
				<td>预期收益：</td>
				<td>${fixBorrowVo.apr}%</td>
				<td>最低开通额度：</td>
				<td>${fixBorrowVo.lowestAccount}元</td>
			</tr>
			<tr>
				<td>最高开通额度：</td>
				<td>${fixBorrowVo.mostAccount/10000}万元</td>
				<td>定时开通时间：</td>
				<td><fmt:formatDate value='${fixBorrowVo.publishTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			</tr>
			<tr>
				<td>有效期限：</td>
				<td>
					${fixBorrowVo.validTime}
					<c:if test="${fixBorrowVo.validTimeStyle==1}">天</c:if> 
					<c:if test="${fixBorrowVo.validTimeStyle==2}">小时</c:if> 
					<c:if test="${fixBorrowVo.validTimeStyle==3}">分钟</c:if>
				</td>
				<td>认购截止时间：</td>
				<td><fmt:formatDate value='${fixBorrowVo.endTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
			</tr>
			<tr>
				<td>满宝时间：</td>
				<td><fmt:formatDate value='${fixBorrowVo.successTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td>还款日：</td>
				<td>${fixBorrowVo.lockEndtimeStr}</td>
			</tr>
			<c:if test="${null!=fixBorrowVo and fixBorrowVo.status==-2 }">
				<tr>
					<td>撤销时间：</td>
					<td>${fixBorrowVo.lastModifyTimeStr}</td>
					<td>撤销备注：</td>
					<td>${fixBorrowVo.lastModifyRemark}</td>
				</tr>
			</c:if>
			<c:if test="${null!=fixBorrowVo and fixBorrowVo.status==-1 }">
				<tr>
					<td>流宝时间：</td>
					<td>${fixBorrowVo.lastModifyTimeStr}</td>
					<td>流宝备注：</td>
					<td>${fixBorrowVo.lastModifyRemark}</td>
				</tr>
			</c:if>
			<!--审核列表 --> 
			<c:if test="${!empty fixBorrowApprList}">
			<tr>
				<td colspan="4">
					<label>审核情况：</label>
					<table class="fulltable" width="100%">
						<tr>
							<th width="5%">序号</th>
							<th width="15%">审核类型</th>
							<th width="10%">审核人</th>
							<th width="20%">审核时间</th>
<!-- 							<th width="15%">状态</th> -->
							<th>审核备注</th>
						</tr>
						<c:forEach items="${fixBorrowApprList}" var="fixBorrowAppr" varStatus="i">
						<tr>
							<td>${i.index+1}</td>
							<td>发布定期宝审核</td>
							<td>
								<c:if test="${fixBorrowAppr.userId !=-1}">${fixBorrowAppr.userName }</c:if> 
								<c:if test="${fixBorrowAppr.userId ==-1  or fixBorrowAppr.userId ==null}">系统自动审核</c:if> 
							</td>
							<td>${fixBorrowAppr.addTimeStr }</td>
<%-- 							<td>${fixBorrowAppr.operTypeStr }</td> --%>
							<td>${fixBorrowAppr.remark }</td>
						</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			</c:if>
			<tr align="center">
				<td colspan="4">
					<input type="button" onclick="cancle()" type="button" name="Submit1" id="subbtn" class="b_buts" value="关 闭" />
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	//关闭窗口
	function cancle() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
</script>
</html>
