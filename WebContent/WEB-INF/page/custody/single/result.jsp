<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:50px;">
		<c:if test="${err != null}">
			<div class="left1_input_ts" style="float: left;">
				<span class="input_span">请求出错：</span>
				<div style="font-size:16px;color:FF0000">
						${err}
				</div>
			</div>
	    </c:if>

		<c:if test="${err == null}">
			<c:if test="${map.stt != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">流水状态：</span>
					<div style="font-size:14px; ">
							${map.sttMsg}
					</div>
				</div>
			</c:if>
			<c:if test="${map.extensionMsg != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">银行流水号：</span>
					<div style="font-size:14px;">
							${map.extensionMsg}
					</div>
				</div>
			</c:if>
		</c:if>
	</div>
</div>
