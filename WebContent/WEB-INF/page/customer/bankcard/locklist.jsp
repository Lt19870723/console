<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="bankCardLock" items="${page.result}" varStatus="i">
		 <tr>
						<td>${i.index+1}</td>
						     <td>${bankCardLock.vusername}</td>
							 <td>${bankCardLock.vemial}</td>
						     <td>${bankCardLock.vmobile}</td>
							 <td>${bankCardLock.vrealname}</td>
							 <td>${bankCardLock.vlockStatus}</td>
							 <td>${bankCardLock.vbCardNum}</td>
							 <td>${bankCardLock.vtotal}</td>
							 <td>${bankCardLock.vcollection}</td>
							 <td>${bankCardLock.vuseMoney}</td>
							 <td>${bankCardLock.vnoUseMoney}</td>
		 </tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="11">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>