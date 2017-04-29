<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width:99%">
		<tr>
		    <th width="2%">序号</th>
		    <th width="4%">一级分类</th> 
			<th width="4%">二级分类</th>
			<th width="10%">商品名称</th>
			<th width="5%">实际价值</th> 
			
			<th width="5%">原价元宝</th>
			<th width="3%">折扣</th>
			<th width="5%">现价元宝</th>
			<th width="3%">数量</th>
			<th width="4%">商品状态</th>
			
			<th width="5%">审核状态</th>
			<th width="8%">操作</th>
		</tr>
		<c:forEach items="${page.result}" var="s" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			 <td>${sta.index+1}</td>
			 <td>${s.firstClass==1?'线上':'线下'}</td>
			  <td>
				 <c:if test="${s.firstClass==1}">${s.secondClass==1?'红包':'抽奖'}</c:if>
				 <c:if test="${s.firstClass==2}">
				 	<c:if test="${s.secondClass==1}">电影劵</c:if>
					<c:if test="${s.secondClass==2}">实物</c:if>
					<c:if test="${s.secondClass==3}">浦菲优澜健身券</c:if>
				 </c:if>
		     </td>
			 <td>${s.name}</td>
			 <td><fmt:formatNumber value="${s.money }" pattern="###,###.00" /></td>
			 <td><fmt:formatNumber value="${s.oldSycee }" pattern="###,###" /></td>
			 <td><fmt:formatNumber value="${s.discount }" pattern="#.##" />折</td>
			 <td><fmt:formatNumber value="${s.sycee }" pattern="###,###" /></td>
			 <td>${s.num}</td>
			 <td>${s.showFlag==1?'上架':'下架' }</td>
			 <td>${s.approveStatus==-1?'草稿':s.approveStatus==0?'待审核':s.approveStatus==1?'审核通过':'审核不通过'}</td>
		     <td>
			 	<c:if test="${opt=='approve' }"><a href="javascript:;" onclick="view(${s.id});">审核</a></c:if> 
			 	<c:if test="${opt=='all' }">
			 		<a href="javascript:void(0);" onclick="view(${s.id});">查看</a>&nbsp;
			 		<c:if test="${s.approveStatus ==-1 || s.approveStatus == 2 || (s.approveStatus==1 && s.showFlag==1)}"><a href="javascript:;" onclick="add(${s.id});">编辑</a></c:if>
			 		<c:if test="${s.approveStatus==1}">
			 			<a href="javascript:;" onclick="grounding(${s.id},${s.showFlag});">${s.showFlag==2?'上架':'下架'}</a>
			 		</c:if>
			 	</c:if>
			 </td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
<script>
/**
 * 商品详情
 */
function view(id){
	$.layer({
		type : 2,
		title : '元宝商品-详情',
		area : [ '80%', '95%' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : '${path}/account/syceeGoods/view.html?opt=${opt}&id=' + id
		},
		end: function(){
			pageGo(1);
	    }
	});
}
</script>