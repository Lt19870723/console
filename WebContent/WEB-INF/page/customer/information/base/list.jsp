<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="menuList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width: 1400px;">
		<tr>
			<th >序号</th>
			<th>用户名</th>
			<th>用户来源</th>
			<th>用户类型</th>
			<th >用户状态</th>
            <th >开立存管</th>
			<th >真实姓名</th>
			<th>邮箱</th>
			<th>手机号码</th>
			<th  style="cursor: pointer;"
				onclick="return clickColumn(this,'total')">资产总额 <span
				class="arrow"> <c:if test="${memberCnd.sortName =='total'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'use_money')">可用 <span
				class="arrow"> <c:if
						test="${memberCnd.sortName =='use_money'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'draw_money')">可提 <span
				class="arrow"> <c:if
						test="${memberCnd.sortName =='draw_money'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'no_draw_money')">不可提 <span
				class="arrow"> <c:if
						test="${memberCnd.sortName =='no_draw_money'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'no_use_money')">冻结 <span
				class="arrow"> <c:if test="${memberCnd.sortName =='total'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'collection')">待收 <span
				class="arrow"> <c:if
						test="${memberCnd.sortName =='collection'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'e_use_money')">存管可用 <span
					class="arrow"> <c:if
					test="${memberCnd.sortName =='e_use_money'}">
				${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
			</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'e_freeze_money')">存管冻结 <span
					class="arrow"> <c:if
					test="${memberCnd.sortName =='e_freeze_money'}">
				${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
			</c:if>
			</span>
			</th>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'e_collection')">存管待收 <span
					class="arrow"> <c:if
					test="${memberCnd.sortName =='e_collection'}">
				${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
			</c:if>
			</span>
			</th>
			<th  style="cursor: pointer;"
				onclick="return clickColumn(this,'first_borrow_use_money')">直通车可用
				<span class="arrow"> <c:if
						test="${memberCnd.sortName =='first_borrow_use_money'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'cur_total')">活期宝
				<span class="arrow"> <c:if
						test="${memberCnd.sortName =='cur_total'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			<th style="cursor: pointer;"
				onclick="return clickColumn(this,'fix_total')">定期宝
				<span class="arrow"> <c:if
						test="${memberCnd.sortName =='fix_total'}">
									${memberCnd.sortMode == 'ASC' ? '&#8593;':'&#8595;' }
								</c:if>
			</span>
			</th>
			<th >注册日期</th>
			<th >最近登录</th>
			<th >累计登录次数</th>
		</tr>
		<c:forEach items="${page.result }" var="memberVo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td><%-- <a href="javascript:detail(${memberVo.userId});"></a> --%>${memberVo.username}</td>
				<td>
				<c:forEach items="${sources}" var="o">
					<c:if test="${o.name==memberVo.source}">
					  ${o.value} 
				    </c:if>
				</c:forEach>
				</td>
				<td><c:if test="${memberVo.isFinancialUser == 1}">理财用户</c:if> <c:if
						test="${memberVo.isFinancialUser == 0}">借款用户</c:if></td>
				<td><c:if test="${memberVo.status == -1}">账号注销</c:if> <c:if
						test="${memberVo.status == -2}">待审核</c:if> <c:if
						test="${memberVo.status == -3}">审核不通过</c:if> <c:if
						test="${memberVo.status == 0}">正常状态</c:if></td>
                <td><c:if test="${memberVo.isCustody == null or memberVo.isCustody == 0}">否</c:if> <c:if
						test="${memberVo.isCustody == 1}">是</c:if></td>
				<td>${memberVo.realName}</td>
				<td>${memberVo.email}</td>
				<td>${memberVo.mobileNum}</td>
				<td>${memberVo.totalStr}</td>
				<td>${memberVo.useMoneyStr}</td>
				<td>${memberVo.drawMoneyStr}</td>
				<td>${memberVo.noDrawMoneyStr}</td>
				<td>${memberVo.noUseMoneyStr}</td>
				<td>${memberVo.collectionStr}</td>
				<td>${memberVo.eUseMoneyStr}</td>
				<td>${memberVo.eFreezeMoneyStr}</td>
				<td>${memberVo.eCollectionStr}</td>
				<td>${memberVo.firstBorrowUseMoneyStr}</td>
				<td>${memberVo.curTotalStr}</td>
				<td>${memberVo.fixTotalStr}</td>
				<td>${memberVo.addtimeStr}</td>
				<td>${memberVo.lastlogintimeStr}</td>
				<td>${memberVo.logintimes}</td>
			</tr>
		</c:forEach>

	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>