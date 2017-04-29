<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 98%;">
	<thead>
		<tr>	
            <th>文件名称</th>
			<th width="10%">业务类型</th>
			<th width="12%">最后操作类型</th>
			<th width="12%">最后操作结果</th>
            <th width="15%">最后操作时间</th>
			<th width="14%">备注</th>
			<th width="12%">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}">
			<tr>
                <td>${vo.fileName}</td>
				<td>
                    <c:if test="${vo.targetType == 1}">
                        批量扣款对账
                    </c:if>
                    <c:if test="${vo.targetType == 2}">
                        批量还款对账
                    </c:if>
                    <c:if test="${vo.targetType == 3}">
                        开销户对账
                    </c:if>
                    <c:if test="${vo.targetType == 4}">
                        出入账对账
                    </c:if>
                </td>
				<td>
                    <c:if test="${vo.targetName == 'RECON_HEADER_RESULT'}">
                        汇总数据对账
                    </c:if>
                    <c:if test="${vo.targetName == 'RECON_DETAIL_RESULT'}">
                        明细数据对账
                    </c:if>
                    <c:if test="${vo.targetName == 'FILE_DOWNLOAD'}">
                        文件下载
                    </c:if>
                    <c:if test="${vo.targetName == 'FILE_PARSE'}">
                        文件解压
                    </c:if>
                    <c:if test="${vo.targetName == 'SAVE_DATA'}">
                        保存数据
                    </c:if>
                    <c:if test="${vo.targetName == 'SAVE_RESULT'}">
                        保存结果
                    </c:if>
                    <c:if test="${vo.targetName == 'CHECK_DIGEST'}">
                        验证摘要
                    </c:if>
                </td>
				<td>
                    <c:if test="${vo.status == 0}">
                        失败
                    </c:if>
                    <c:if test="${vo.status == 1}">
                        成功
                    </c:if>
                </td>
                <td><fmt:formatDate value="${vo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${vo.remark}</td>
				<td>
		   			<a href="javascript:void(0);" onclick="showDetail('${vo.fileName}')">查看</a>
                    <c:if test="${vo.status == 0}">
                        <a href="javascript:void(0);" onclick="retry('${vo.fileName}')">重试</a>
                    </c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>