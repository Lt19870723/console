<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>活期宝账户资金对账-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post" >
		<div style="margin-left: 20px; line-height: 50px;">
				&nbsp;
		</div>
		<div class="fulltable" style="width: 60%;">
			<div style="width: 590px;border-bottom-width: 1">&nbsp;&nbsp;&nbsp;统计结果</div>
			<div>
				账户总额:&nbsp;&nbsp;${curAccountRepportVo.sumTotal}
			</div>

			<div>
				转入总额:&nbsp;&nbsp;${curAccountRepportVo.sumInTotal}
			</div>

			<div>
				转出总额:&nbsp;&nbsp;${curAccountRepportVo.sumOutTotal}
			</div>
			
			<div>
				收益入账总额:&nbsp;&nbsp;${curAccountRepportVo.sumAccountLogInterestTotal}
			</div>
			
			<div>
				累计:&nbsp;&nbsp;${curAccountRepportVo.sumInterestTotal}
			</div>
			<div>
				计算:&nbsp;&nbsp;${curAccountRepportVo.sumInterestDetailTotal}
			</div>
			
			<div>
				转入总额  + 收益入账总额 - 转出总额是否等于账户总额：&nbsp;&nbsp;
				${curAccountRepportVo.isEqual == 1?"是":"否"}
			</div>
			<div style="color:red;"><br/>
				公式：&nbsp;&nbsp;<br/>
				账户总额  = 转入总额  +  - 转出总额<br/>
				收益入账总额 = 累计 = 计算 
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">

</script>
</html>