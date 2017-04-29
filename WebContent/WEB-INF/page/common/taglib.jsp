<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="el" uri="/WEB-INF/ELTag.tld"%>
<c:if test="${pageContext.request.serverPort=='80' || pageContext.request.serverPort=='443'}">
	<c:set var="path" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
</c:if>
<c:if test="${pageContext.request.serverPort!='80' && pageContext.request.serverPort!='443'}">
	<c:set var="path" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
</c:if>
<c:set var="portalPath" value="http://127.0.0.1:8080/" />
<c:set var="bbsPath" value="http://.../bbs" /> 