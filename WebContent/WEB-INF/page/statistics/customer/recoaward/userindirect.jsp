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
		<table align="center" border="1" bordercolor="black" style="width: 95%">
			<tr>
				<td colspan="2">间接推荐共享奖明细(统计日期:${year}-${invetedUserVo.month })</td>
				<td colspan="3">推荐人：${invetedUserVo.userName }</td>
			</tr>
			<tr>
				<td >
					序号
				</td>
				<td >
					被推荐人
				</td>
				<td >
					利息收益
				</td>
				<td >
					所得利息
				</td>
			</tr>
			<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1 }</td>
				<td>${vo.invitedUsername}</td>
				<td><a href="javascript:queryUserCollLayer('${id}','${vo.invitedUserId }','${vo.invitedUsername }','${year}')">${vo.monthInterest }</a></td>
				<td>${vo.getInterest }</td>
			</tr>
		</c:forEach>
		</table>
	<div align="center">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
	</form>
</body>
	<script type="text/javascript">
	
	function queryUserCollLayer(id,invitedUserId,invitedUsername,year){
		showUserCollLayer(id,invitedUserId,invitedUsername,1,year);
	}
	
function showUserCollLayer(id,invitedUserId,invitedUsername,pageNo,year){
	var _url = '${path}/customer/recommendaward/usercollection/'+id+'/'+invitedUserId+'/'+invitedUsername+'/'+year+'/'+pageNo+'.html';
	$.layer({
		type : 2,
		title : '上月利息净收益明细',
		area : [ '600px', '500px' ],
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
	
	function pageGo(pageNo){
		window.location.href = '${path}/customer/recommendaward/userIndirectlist/${id}/'+pageNo+'.html';
	}
</script>
</html>
