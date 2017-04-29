<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="main_cent">

    	<div style="margin-left:20px;line-height:40px;height:50px;">
		<c:if test="${isOaOk == false}">
			<div class="left1_input_ts" style="float: left;">
				<span class="input_span">请求出错：</span>
				<div style="font-size:16px;color:FF0000">
						${msg}
				</div>
			</div>
	    </c:if>

		<c:if test="${isOaOk == true}">
			<c:if test="${accountName != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">客户姓名：</span>
					<div style="font-size:14px; ">
							${accountName}
					</div>
				</div>
			</c:if>
			<c:if test="${certType != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">证件类型：</span>
					<div style="font-size:14px;">
                        <c:if test="${certType == '1'}">
                            身份证
                        </c:if>
                        <c:if test="${certType == '2'}">
                            户口薄
                        </c:if>
                        <c:if test="${certType == '3'}">
                            军人证
                        </c:if>
                        <c:if test="${certType == '4'}">
                            武警证
                        </c:if>
                        <c:if test="${certType == '5'}">
                            回乡证
                        </c:if>
                        <c:if test="${certType == '6'}">
                            国外居留证
                        </c:if>
                        <c:if test="${certType == '7'}">
                            境外护照
                        </c:if>
                        <c:if test="${certType == '9'}">
                            港澳通行证
                        </c:if>
					</div>
				</div>
			</c:if>
            <c:if test="${certNo != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">证件号码：</span>
					<div style="font-size:14px;">
							${certNo}
					</div>
				</div>
			</c:if>
            <c:if test="${mobile != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">手机号码：</span>
					<div style="font-size:14px;">
							${mobile}
					</div>
				</div>
			</c:if>
            <c:if test="${stt != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">状态：</span>
					<div style="font-size:14px;">
							<c:if test="${stt == '1'}">
                                正常
                            </c:if>
                            <c:if test="${stt == '2'}">
                                解绑
                            </c:if>
					</div>
				</div>
			</c:if>
            <c:if test="${bindSerialNo != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">绑定协议号：</span>
					<div style="font-size:14px;">
                            ${bindSerialNo}
					</div>
				</div>
			</c:if>
            <c:if test="${eCardNo != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">存管账号：</span>
					<div style="font-size:14px;">
                            ${eCardNo}
					</div>
				</div>
			</c:if>
            <c:if test="${otherAccno != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">主账户：</span>
					<div style="font-size:14px;">
                            ${otherAccno}
					</div>
				</div>
			</c:if>
            <c:if test="${bankName != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">主账户开户银行：</span>
					<div style="font-size:14px;">
                            ${bankName}
					</div>
				</div>
			</c:if>
            <c:if test="${extension != null}">
				<div class="left1_input_ts" style="float: left;">
					<span class="input_span">存管账户类型：</span>
					<div style="font-size:14px;">
                            <c:if test="${extension == '0'}">
                                存管e户
                            </c:if>
                            <c:if test="${extension == '1'}">
                                商卡
                            </c:if>
					</div>
				</div>
			</c:if>
		</c:if>
	</div>
	
</div>
