<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
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
	        <td bgcolor="#eeeeee" width="15%"><div align="center">编号</div></td>
	        <td bgcolor="#FFFFFF" width="20%"><div align="center">${autoInvestConfigReocrdVo.auto_tender_id}</div></td>
	        <td bgcolor="#eeeeee" width="15%"><div align="center">是否启用</div></td>
	        <td bgcolor="#FFFFFF" width="15%"><div align="center">
	            <c:if   test="${autoInvestConfigReocrdVo.status==0}">未启用</c:if>
				<c:if    test="${autoInvestConfigReocrdVo.status==1}">已启用</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.status==2}">已删除</c:if>
	        </div></td>
	        <td bgcolor="#eeeeee" width="15%"><div align="center">排队号</div></td>
	        <td bgcolor="#FFFFFF" width="20%"><div align="center">
	         <c:if  test="${autoInvestConfigReocrdVo.rownum != null}">第${autoInvestConfigReocrdVo.rownum}位</c:if>
	       </div></td>
      </tr>
		     <tr>
		        <td bgcolor="#eeeeee"><div align="center">投标额度</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		        <c:if   test="${autoInvestConfigReocrdVo.record_type==2}">${autoInvestConfigReocrdVo.tender_record_accout}</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.record_type!=2 and autoInvestConfigReocrdVo.tender_type==1}">${autoInvestConfigReocrdVo.tender_account_auto}</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.record_type!=2 and autoInvestConfigReocrdVo.tender_type==2}">${autoInvestConfigReocrdVo.tender_scale}%</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.record_type!=2 and autoInvestConfigReocrdVo.tender_type==3}">${autoInvestConfigReocrdVo.useMoney}</c:if>	    
				    </div></td>
		        <td bgcolor="#eeeeee"><div align="center">投标方式</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		       	<c:if  test="${autoInvestConfigReocrdVo.tender_type==1}">按金额投标</c:if>
				<c:if  test="${autoInvestConfigReocrdVo.tender_type==2}">按比例投标</c:if>
				<c:if  test="${autoInvestConfigReocrdVo.tender_type==3}">按余额投标</c:if>
				        </div></td>
		        <td bgcolor="#eeeeee"><div align="center">自动类型 </div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		        <c:if   test="${autoInvestConfigReocrdVo.autoType==1 and autoInvestConfigReocrdVo.addtimeStamp >= 1434535200}">按抵押标、担保标、信用标投标</c:if>
				<c:if  test="${autoInvestConfigReocrdVo.autoType==1 and autoInvestConfigReocrdVo.addtimeStamp < 1434535200}">按抵押标、担保标投标</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.autoType==2 and autoInvestConfigReocrdVo.addtimeStamp >= 1433088000}">按信用标投标</c:if>
				<c:if  test="${autoInvestConfigReocrdVo.autoType==2 and autoInvestConfigReocrdVo.addtimeStamp < 1433088000}">按净值标、信用标投标</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.autoType==null}">历史类型</c:if>	        </div></td>
		     </tr>
		     <tr>
		        <td bgcolor="#eeeeee"><div align="center">最低投标金额</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">${autoInvestConfigReocrdVo.min_tender_account}</div></td>
		        <td bgcolor="#eeeeee"><div align="center">借款期限</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		        <c:if test="${autoInvestConfigReocrdVo.timelimit_status==1}">无限定</c:if>
				<c:if test="${autoInvestConfigReocrdVo.timelimit_status==0 and autoInvestConfigReocrdVo.min_time_limit > 0}">按月：${autoInvestConfigReocrdVo.min_time_limit}</c:if>
				<c:if test="${autoInvestConfigReocrdVo.timelimit_status==0 and autoInvestConfigReocrdVo.min_time_limit > 0}">~${autoInvestConfigReocrdVo.max_time_limit}个月</c:if>
				<c:if test="${autoInvestConfigReocrdVo.timelimit_status==0 and autoInvestConfigReocrdVo.min_day_limit > 0 and autoInvestConfigReocrdVo.borrow_type2_status == 0 and autoInvestConfigReocrdVo.borrow_type5_status == 0}">按天：${autoInvestConfigReocrdVo.min_day_limit}</c:if>
				<c:if test="${autoInvestConfigReocrdVo.timelimit_status==0 and autoInvestConfigReocrdVo.min_day_limit > 0 and autoInvestConfigReocrdVo.borrow_type2_status == 0 and autoInvestConfigReocrdVo.borrow_type5_status == 0}">~${autoInvestConfigReocrdVo.max_day_limit}天</c:if>
	            </div></td>
		        <td bgcolor="#eeeeee"><div align="center">还款方式</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		         <c:if  test="${autoInvestConfigReocrdVo.borrow_type==0}">无限定</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.borrow_type==1}">额本息</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.borrow_type==2}">按月付息到期还本</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.borrow_type==3}">到期还本付息</c:if>
				<c:if  test="${autoInvestConfigReocrdVo.borrow_type==4}">按天还款</c:if>
		        </div></td>
		     </tr>
		     <tr>
		        <td bgcolor="#eeeeee"><div align="center">标的类型</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		       <c:if   test="${autoInvestConfigReocrdVo.borrow_type3_status==1}">净值标&nbsp;&nbsp;</c:if>
				<c:if  test="${autoInvestConfigReocrdVo.borrow_type2_status==1}">抵押标&nbsp;&nbsp;</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.borrow_type1_status==1}">用标&nbsp;&nbsp;</c:if>
				<c:if   test="${autoInvestConfigReocrdVo.borrow_type5_status==1}">担保标&nbsp;&nbsp;</c:if>
				     </div></td>
		        <td bgcolor="#eeeeee"><div align="center">年化收益率</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		      <c:if test="${autoInvestConfigReocrdVo.min_apr == null and autoInvestConfigReocrdVo.max_apr==null}">无限定</c:if>
				<c:if test="${autoInvestConfigReocrdVo.min_apr!=null and autoInvestConfigReocrdVo.min_apr > 0}">${autoInvestConfigReocrdVo.min_apr}%</c:if>
				<c:if test="${autoInvestConfigReocrdVo.min_apr!=null and autoInvestConfigReocrdVo.min_apr > 0}">~${autoInvestConfigReocrdVo.max_apr}%</c:if>
			 </div></td>
		        <td bgcolor="#eeeeee"><div align="center">修改时间</div></td>
		        <td bgcolor="#FFFFFF"><div align="center">
		        <fmt:formatDate value="${autoInvestConfigReocrdVo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		        </div></td>
		     </tr>
		     <tr>
		        <td bgcolor="#eeeeee"><div align="center">记录类型  </div></td>
		        <td bgcolor="#FFFFFF">
			        <div align="center">
			        <c:if test="${autoInvestConfigReocrdVo.record_type==1}">修改</c:if>
					<c:if test="${autoInvestConfigReocrdVo.record_type==2}">投标成功</c:if>
					<c:if test="${autoInvestConfigReocrdVo.record_type==3}">删除</c:if>
			        </div>
		        </td>
		        <td bgcolor="#eeeeee"><div align="center">所投标类型 </div></td>
		        <td bgcolor="#FFFFFF"><div align="center">${autoInvestConfigReocrdVo.borrowTypeStr}</div></td>
		        <td bgcolor="#eeeeee"><div align="center">所投标</div></td>
		        <td bgcolor="#FFFFFF"> <div align="center"><a href="${portalPath}/toubiao/showBorrowDetail/${autoInvestConfigReocrdVo.borrowId}.html" target="_blank">${autoInvestConfigReocrdVo.borrowName}</a></div></div></td>
		     </tr>
		     <tr>
		        <td bgcolor="#eeeeee"><div align="center">备注</div></td>
		        <td bgcolor="#FFFFFF" colspan="5"><div align="center">${autoInvestConfigReocrdVo.remark}</div></td>
		     </tr>
	</table>
	 
	</form>
</body>
 
</html>
