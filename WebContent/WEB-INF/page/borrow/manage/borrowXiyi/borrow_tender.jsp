<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="checkBorrowPopupform" action="" method="post">
	<table style="margin-left:10px;margin-top:5px;" border="1">
	<tr height="35">
		      <td colspan="10" bgcolor="#f1f1f1" height="20" style="text-indent:0.8em"><span style="color:#666; font-weight:bolder;">投标记录</span>
		      <span style="float:right;">
		      <a href="javascript:;" onclick="fordetail(${borrow.id});">导出投标记录</a>
		      &nbsp;&nbsp;&nbsp;&nbsp;
		      </span>
		      </td>
		    </tr>
		    <tr height="30px;">
		        <td bgcolor="#eeeeee" width="18%"><div align="center">投标人</div></td>
		        <td bgcolor="#eeeeee" width="8%"><div align="center">年化利率</div></td>
		        <td bgcolor="#eeeeee" width="16%"><div align="center">借款金额</div></td>
		        <td bgcolor="#eeeeee" width="14%"><div align="center">投标金额</div></td>
		        <td bgcolor="#eeeeee" width="18%"><div align="center">投标时间</div></td>
		        <td bgcolor="#eeeeee" width="18%"><div align="center">投资方式</div></td>
		        <td bgcolor="#eeeeee" width="8%"><div align="center">状态</div></td>
		     </tr>
		       
		     <c:forEach items="${tenderRecordVoList }" var="tenderRecordVo" varStatus="sta">
		      <tr class='tr_${i%2}'>
		        <td width="16%" align="center">
		        	<c:if   test="${tenderRecordVo.tenderType==3}">（定期宝）</c:if>
		        	<c:if   test="${tenderRecordVo.tenderType!=3}">${tenderRecordVo.username}</c:if>
		        </td>
		        <td width="8%" align="center">${borrow.apr}%</td>
		        <td width="16%" align="center">${borrow.account}</td>
		        <td width="14%" align="center">${tenderRecordVo.account}</td>
		        <td width="16%" align="center">${tenderRecordVo.addtimeStr}</td>
		        <td width="16%" align="center">
		          
		          <c:if   test="${tenderRecordVo.tenderType==0}">手动投标</c:if>
		          <c:if   test="${tenderRecordVo.tenderType==1}">自动投标（第${tenderRecordVo.autotenderOrder}位）</c:if>
		          <c:if   test="${tenderRecordVo.tenderType==2 and tenderRecordVo.autotenderOrder != null}">直通车投标（第${tenderRecordVo.autotenderOrder}位）</c:if>
		          <c:if   test="${tenderRecordVo.tenderType==2 and tenderRecordVo.autotenderOrder == null}">${tenderRecordVo.firstBorrowName}</c:if>
		          <c:if   test="${tenderRecordVo.tenderType==3}">定期宝投标</c:if>
		          <c:if   test="${tenderRecordVo.tenderType==4}">权证人员投标</c:if>
		          
		        </td>
		        <td width="8%" align="center">
		        	<c:if test="${tenderRecordVo.status>0}">全部通过</c:if>
		        </td>
		      </tr>
		     </c:forEach> 
	</table>
	 
	</form>
</body>
<script type="text/javascript">
 
function fordetail(id){
	if(!confirm("确认导出投标记录吗？")){
		return false;
	}
	$("#checkBorrowPopupform").attr("action","${path}/borrow/manage/borrowXiyi/export.html?id="+id);
    $("#checkBorrowPopupform").submit(); 
	 
}
 
</script>
</html>
