<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
	
	<div>
		<c:if test="${err != null}">
			<div class="left1_input_ts" style="float: left;">
				<span class="input_span">请求出错：</span>
				<div style="font-size:16px;color:FF0000">
						${err}
				</div>
			</div>
	    </c:if>

		<c:if test="${err == null}">
				<div style="float: left; margin-left: 30px;">
					<span>存管可用金额：<fmt:formatNumber value="${account.eUseMoney }" pattern="#,##0.00"></fmt:formatNumber> 元</span>
				</div>
				<br>
				<div style="float: left; margin-left: 30px;">
					<span>存管冻结金额：<fmt:formatNumber value="${account.eFreezeMoney }" pattern="#,##0.00"></fmt:formatNumber>元</span>
				</div>
				<br>
				<div style="float: left; margin-left: 30px;">
					<span>存管可提金额：<fmt:formatNumber value="${account.zsWithdrawamount }" pattern="#,##0.00"></fmt:formatNumber>元</span>
				</div>
		</c:if>
	</div>
</div>
