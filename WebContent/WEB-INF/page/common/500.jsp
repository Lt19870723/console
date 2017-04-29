<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%response.setStatus(200);%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="国诚金融,金融,投资,理财" />
<meta name="description"
	content="国诚金融提供安全、有担保的互联网理财投资服务。100%本息担保！随时可赎回！上国诚，好收益！">
<meta name="generator" content="国诚金融" />
<meta name="author" content="国诚金融" />
<meta name="copyright" content="2014 上海国诚金融信息服务有限公司" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>

<style>
.network-content {
	margin: 0 auto;
	width: 600px;
	padding-top: 110px
}

.network-wrong {
	float: left;
	padding: 0px;
	margin: 0px;
}

.network-content .headline {
	float: left;
	color: #a2a2a2;
	width: 360px;
	font-size: 36px;
	display: block;
	margin-left: 50px;
}

.network-content .txts {
	float: left;
	line-height: 25px;
	width: 360px;
	display: block;
	margin-left: 50px;
	margin-top: 20px;
}

.network-content .txts a {
	color: #00a7e5;
	margin-left: 10px;
}

.footbg {
	background: #555;
	color: #ccc;
	width: 100%;
	position: absolute;
	bottom: 0;
}

.footer-copyright {
	font-size: 14px;
	line-height: 40px;
	position: relative;
	text-align: center;
	width: 1000px;
	margin: auto;
}
</style>

</head>
<body>

	<div class="clear"></div>
	<!--头部结束-->
	<!--内容开始-->

	<div id="Bmain">
		<div class="network-content">
			<img class="network-wrong" src="${path }/images/bbs/500.png"
				width="176" height="176" />
			<p class="headline">服务器发生错误</p>
			<p class="txts">
				抱歉，服务器发生错误，暂时无法响应您的请求。工作人员正在努力修复中，请您耐心等待，稍后重试。<br /> 返回 <a
					href="${path}/">国诚金融论坛首页</a>
			</p>
		</div>
	</div>


	<!--内容结束-->
	<div class="clearfix"></div>
	<div class="footbg">
		<div class="footer-copyright">Copyright 2014 &copy;
			上海国诚金融信息服务有限公司 版权所有 | 上海市虹口区西江湾路388号凯德龙之梦A座32楼&nbsp; 电话：021-80100999
			| 沪ICP备13021943号-1</div>
	</div>
</body>
</html>
