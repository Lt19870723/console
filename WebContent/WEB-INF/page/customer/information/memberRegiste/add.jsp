<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
<script type="text/javascript" src="${path}/js/util.js"></script>
<script type="text/javascript">
   $(function(){
	   var id=$("#id").val();
	   
	   if(id==null||id==''){
		    $('#isMortgage').val(1);
			$('#mortgageType').val(1);
			$('#hasHouse').val(1);
			$('#hasHouseMortgage').val(1);
			$('#hasCar').val(1);
			$('#hasCarMortgage').val(1);
			$('#isJointGuaranty').val(1);
			$('#lowestAccount').val(50);
			$('#validTime').val(3);
			$('#isGuaranty').val(0);
	   }else{
		  
		   if($('#isMortgage').val() == 0){
				displaySet('mortgageDiv1|mortgageDiv2|mortgageTypeDiv','none');
				$("#isMortgage_2").attr("checked","checked");
			}else if($('#isMortgage').val() == 1){
				displaySet('mortgageDiv1|mortgageDiv2|mortgageTypeDiv','block');
				$("#isMortgage_1").attr("checked","checked");
			}else{
				$('#isMortgage').val(1);
				$("#isMortgage_1").attr("checked","checked");
			}

			if($('#mortgageType').val() == 1){
				$("#mortgageType_1").attr("checked","checked");
			}else if($('#mortgageType').val() == 2){
				$("#mortgageType_2").attr("checked","checked");
			}else if($('#mortgageType').val() == 3){
				$("#mortgageType_3").attr("checked","checked");
			}else{
				$('#mortgageType').val(1);
				$("#mortgageType_1").attr("checked","checked");
			}
			
			if($('#isGuaranty').val() == 1){
				$("#isGuarantyCB").attr("checked", true);
			}else if($('#isGuaranty').val() == 0){
				$("#isGuarantyCB").attr("checked", false);
			}else{
				$('#isGuaranty').val(0);
				$("#isGuarantyCB").attr("checked","checked");
			}
			
			if($('#hasHouse').val() == 1){
				displaySet('houseLocationTr|hasHouseMortgageTr|houseAreaTr','block');
				$("#hasHouse_1").attr("checked","checked");
			}else if($('#hasHouse').val() == 0){
				displaySet('houseLocationTr|hasHouseMortgageTr|houseAreaTr','none');
				$("#hasHouse_2").attr("checked","checked");
			}else{
				$('#hasHouse').val(1);
				$("#hasHouse_1").attr("checked","checked");
			}
			
			if($('#hasHouseMortgage').val() == 1){
				$("#hasHouseMortgage_1").attr("checked","checked");
			}else if($('#hasHouseMortgage').val() == 0){
				$("#hasHouseMortgage_2").attr("checked","checked");
			}else{
				$('#hasHouseMortgage').val(1);
				$("#hasHouseMortgage_1").attr("checked","checked");
			}
		
			if($('#hasCar').val() == 1){
				displaySet('carNoTr|hasCarMortgageTr|carValueTr','block');
				$("#hasCar_1").attr("checked","checked");
			}else if($('#hasCar').val() == 0){
				displaySet('carNoTr|hasCarMortgageTr|carValueTr','none');
				$("#hasCar_2").attr("checked","checked");
			}else{
				$('#hasCar').val(1);
				$("#hasCar_1").attr("checked","checked");
			}
			
			if($('#hasCarMortgage').val() == 1){
				$("#hasCarMortgage_1").attr("checked","checked");
			}else if($('#hasCarMortgage').val() == 0){
				$("#hasCarMortgage_2").attr("checked","checked");
			}else{
				$('#hasCarMortgage').val(1);
				$("#hasCarMortgage_1").attr("checked","checked");
			}
			
			var maritalStatus_index = $('#maritalStatus').val();
			$("#maritalStatus_"+maritalStatus_index).attr("checked","checked");
			
			if($('#isJointGuaranty').val() == 1){
				$("#isJointGuaranty_1").attr("checked","checked");
				displaySet('jointGuarantyDiv','block');
			}else if($('#isJointGuaranty').val() == 0){
				$("#isJointGuaranty_2").attr("checked","checked");
				displaySet('jointGuarantyDiv','none');
			}else{
				$('#isJointGuaranty').val(1);
				$("#isJointGuaranty_1").attr("checked","checked");
			}
			
			if($('#lowestAccount').val() == null || $('#lowestAccount').val() == ''){
				$('#lowestAccount').val(1);
			}
			if($('#validTime').val() == null || $('#validTime').val() == ''){
				$('#validTime').val(1);
			}
			$('#bidPassword').val('mimamima');
			if($('#oldBidPassword').val() != ''){
				$('#bidPasswordNew').val('111111');
			}

	   }
   });
</script>
</head>
<body style="background: #fff;" >
	<form id="applyBorrowForm" action="" style="background: #fff;">
	    <!-- 借款信息 -->
	    <input class="input1" type="hidden" id="userId" name="userId" value="${userId}"/>
	    <c:if test="${salariatBorrowVo.businessUserId==null || salariatBorrowVo.businessUserId==''}">
	    	<input type="hidden" id="businessUserId" name="businessUserId" value="0"/>
	    </c:if>
	    <c:if test="${salariatBorrowVo.businessUserId!=null && salariatBorrowVo.businessUserId!=''}">
	    	<input type="hidden" id="businessUserId" name="businessUserId" value="${salariatBorrowVo.businessUserId}"/>
	    </c:if>
	    <div style="margin:20 auto;  position:relative;  float:none;  padding:0;">
	     <table>
			<tr>
				<td colspan="4">借款信息
				<input class="input1" type="hidden" id="id" name="id" value="${salariatBorrowVo.id}"/>
				</td>
			</tr>
			<tr>
				<td width="150"><font color="red">*</font>借款标题：</td>
				<td colspan="3" ><input class="input1" id="name" value="${salariatBorrowVo.name}"  name="name" type="text"   /></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td width="150"><font color="red">*</font>借款标编号：</td>
				<td><input class="input1" name="contractNo" value="${salariatBorrowVo.contractNo}"   id="contractNo" type="text"  /></td>
			</tr>
			<tr>
			    <td><font color="red">*</font>借款介绍：</td>
			    <td colspan="3" ><textarea  name="content"  id="content" cols="90" rows="8" >${salariatBorrowVo.content}</textarea>
			    </td>
			</tr> 
			<tr>
			    <td><span><font color="red">*</font>&nbsp;&nbsp;是否抵押：</span> </td>
			    <td colspan="3">
			      <input type="hidden" id="isMortgage"  value="${salariatBorrowVo.isMortgage}"/> 
				  <input type="radio"  id="isMortgage_1" name="isMortgage"  value="1" checked="checked" onclick="displaySet('mortgageDiv1|mortgageDiv2|mortgageTypeDiv','block');changeValue('isMortgage','1');"/>有抵押&nbsp;&nbsp; 
				  <input type="radio"  id="isMortgage_2" name="isMortgage"  value="0" onclick="displaySet('mortgageDiv1|mortgageDiv2|mortgageTypeDiv','none');changeValue('isMortgage','0');" />无抵押
			    </td>			    
			</tr>  
			 
			<tr>
					
			     <td id="mortgageDiv1">抵押类型：<font color="red">*</font></td> 
				 <td id="mortgageDiv2">
				   	    <input type="hidden" id="mortgageType"  value="${salariatBorrowVo.mortgageType}"/> 
				        <input  id="mortgageType_1"  name="mortgageType"  type="radio" value="1" checked="checked" onclick="changeValue('mortgageType','1')"/>房抵&nbsp;&nbsp;
						<input  id="mortgageType_2"  name="mortgageType"  type="radio" value="2" onclick="changeValue('mortgageType','2')" />车抵&nbsp;&nbsp; 
						<input  id="mortgageType_3"  name="mortgageType"  type="radio" value="3" onclick="changeValue('mortgageType','3')"/>民品抵
				</td>
					
			    <td>
						 <input type="hidden" id="isGuaranty"  name="isGuaranty" value="${salariatBorrowVo.isGuaranty}"/> 
						 <input   id="isGuarantyCB" name="isGuarantyCB" type="checkbox" onclick="showRepayWays();" />是否机构担保 
				</td>	
				<td>		
						<select id="guarantyOrganization"  class="bigselect" name="guarantyOrganization" msg="请选择担保机构">
							<option value="">--请选择担保机构--</option>
							<c:forEach items="${map.organizationOptions }" var="o">
								 
								<option value="${o.id}"  ${salariatBorrowVo.guarantyOrganization==o.id?'selected=selected':''}>${o.name}</option>
							</c:forEach>
						</select>
			   </td> 			
			</tr>
			
			
			 <tr>
				<td><span><font color="red">*</font>&nbsp;&nbsp;是否存管：</span> </td>
			    <td colspan="3"><input type="hidden" id="iscg" value="${salariatBorrowVo.isCustody}">
			    <c:choose>
			    	<c:when test="${salariatBorrowVo.isCustody==0}">
			    	 <input type="radio"  id="isCustody" name="isCustody"  value="0" checked="checked"  onclick="cg();"/>非存管&nbsp;&nbsp; 
				     <input type="radio"  id="isCustody2" name="isCustody"  value="1" onclick="cg2();"/>浙商存管
			    	</c:when>
			    	<c:when test="${salariatBorrowVo.isCustody==1}">
			    	<input type="radio"  id="isCustody" name="isCustody"  value="0"  onclick="cg();"/>非存管&nbsp;&nbsp; 
				    <input type="radio"  id="isCustody2" name="isCustody"  value="1" checked="checked"  onclick="cg2();"/>浙商存管
			    	</c:when>
			    	<c:otherwise>
			    	<input type="radio"  id="isCustody" name="isCustody"  value="0" checked="checked"  onclick="cg();"/>非存管&nbsp;&nbsp; 
				    <input type="radio"  id="isCustody2" name="isCustody"  value="1" onclick="cg2();"/>浙商存管
			    	</c:otherwise>
			    </c:choose>
			    
			    </td>			    
			
			</tr>
			
			
			<tr style="display: none;" id="qz">
					
			     <td class="rz_font"><font color="red">*</font>还款账户名：</td>
				 <td><select id="repayName" name="repayName" class="bigselect"  onchange="findRepayAcct()">
				   <option value="">--请选择--</option>	
				   <c:forEach items="${borrowerUserList}" var="bUser">
							 <c:if test="${bUser.repayName==salariatBorrowVo.repayName}">
							 <option selected="selected" value="${bUser.repayName}">${bUser.repayName}</option>
							 </c:if>
							 <c:if test="${bUser.repayName!=salariatBorrowVo.repayName}">
							 <option value="${bUser.repayName}">${bUser.repayName}</option>
							 </c:if>
					</c:forEach>
					</select>
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>还款账户账号：</td>
				<td>
				<input class="input1" name="repayAcct"   id="repayAcct"  type="hidden"  value="${salariatBorrowVo.repayAcct}"/>
				<input class="input1" disabled="disabled" name="repayAcct2"   id="repayAcct2"  type="text"  value="${salariatBorrowVo.repayAcct}"/>
				</td>
			</tr> 
			
			
		</table>	 
		<table border="0">
			<tr height="35">
				<td class="rz_font"><font color="red">*</font>借款金额(元)：</td>
				<td><input class="input1" id="account" name="account" value="${salariatBorrowVo.account}"   type="text" /></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>年化利率(%)：</td>
				<td><input class="input1" name="apr"   id="apr"  value="${salariatBorrowVo.apr}"  type="text"  /></td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>借款期限：</td>
				<td>
					<input class="input1" id="timeLimit" name="timeLimit" value="${salariatBorrowVo.timeLimit}"  />
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>最低投标金额(元)：</td>
				<td><input class="input1"   id="lowestAccount" name="lowestAccount" value="${salariatBorrowVo.lowestAccount}" type="text" /></td>
			</tr>
			<tr>
				<td class="rz_font">最高投标金额(元)：</td>
				<td><input class="input1" id="mostAccount" value="${salariatBorrowVo.mostAccount}"  name="mostAccount"  type="text" /></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>有效期限(天)：</td>
				<td>
					<input class="input1"  name="validTime" value="${salariatBorrowVo.validTime}"  id="validTime" type="text" dataType="Integer|Range"   />
				</td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>还款方式：</td>
				<td>
					 
				    <select id="repaymentSel" name="style" class="bigselect">
				          
						<c:forEach items="${repaymentStyleOptions}" var="o">
							<c:if test="${o.name!=0 && o.name!=4}">
							 <option value="${o.name}"  ${salariatBorrowVo.style==o.name?'selected=selected':''}>${o.value}</option>
						    </c:if>
						</c:forEach>
					</select>
				 
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>标的类型：</td>
				<td>
					<select name="pledgeType" id="pledgeType" class="bigselect">
						<option value="1"  ${salariatBorrowVo.pledgeType==1?'selected=selected':''}>新增</option>
						<option value="2"  ${salariatBorrowVo.pledgeType==2?'selected=selected':''}>续贷</option>
						<option value="3"  ${salariatBorrowVo.pledgeType==3?'selected=selected':''}>资产处理</option>						
					</select>
				</td>
			</tr>
			<tr>
				<td class="rz_font">定向密码：</td>
				<td>
				<input type="password" id="bidPasswordNew" maxlength="12"   class="input1" onblur="bidPasswordChage();" /> 
				<input type="hidden" name="bidPassword"    id="bidPassword"   value="${salariatBorrowVo.bidPassword}"  /></td>
			    <input type="hidden" name="oldBidPassword" id="oldBidPassword" value="${salariatBorrowVo.oldBidPassword}"/> 
			</tr>
		</table>
 
		<!-- 资产信息 -->
		<div id="mortgageTypeDiv">
		<table border="0" >
		     <tr>
		      <td colspan="4">资产信息 </td>
		    </tr>
			<tr>
				<td class="rz_font"><span>是否拥有房产：</span></td>
				<td>
				    <input type="hidden" value="${mortgage.hasHouse}" id="hasHouse" />
					<input id="hasHouse_1" name="hasHouse" type="radio" value="1" checked="checked" onclick="displaySet('houseLocationTr|hasHouseMortgageTr|houseAreaTr','block');changeValue('hasHouse','1');"/> 是&nbsp;&nbsp;
					<input id="hasHouse_2" name="hasHouse" type="radio" value="0" onclick="displaySet('houseLocationTr|hasHouseMortgageTr|houseAreaTr','none');changeValue('hasHouse','0');"/> 否&nbsp;&nbsp;
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font" id="hasHouseMortgageTr"><span>是否拥有房贷：</span></td>
				<td id="houseAreaTr">
				    <input type="hidden" value="${mortgage.hasHouseMortgage}" id="hasHouseMortgage" />
					<input   id="hasHouseMortgage_1" name="hasHouseMortgage" type="radio" value="1" checked="checked" onclick="changeValue('hasHouseMortgage','1');"/> 是&nbsp;&nbsp;
					<input   id="hasHouseMortgage_2" name="hasHouseMortgage" type="radio" value="0" onclick="changeValue('hasHouseMortgage','0');" /> 否&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="houseLocationTr">
				<td class="rz_font">房产位置：</td>
				<td><input class="input1" id="houseLocation" name="houseLocation"  value="${mortgage.houseLocation}" type="text" maxlength="100" /></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font">房产面积：</td>
				<td><input class="input1" value="${mortgage.houseArea}" id="houseArea" name="houseArea"  type="text" maxlength="100"/></td>
			</tr>
			<tr>
				<td class="rz_font"><span>是否拥有车产：</span></td>
				<td>
				    <input type="hidden" value="${mortgage.hasCar}" id="hasCar" />
					<input  id="hasCar_1" name="hasCar" type="radio" value="1" checked="checked" onclick="displaySet('carNoTr|hasCarMortgageTr|carValueTr','block');changeValue('hasCar','1');"/> 是&nbsp;&nbsp;
					<input  id="hasCar_2" name="hasCar" type="radio" value="0" onclick="displaySet('carNoTr|hasCarMortgageTr|carValueTr','none');changeValue('hasCar','0');"/> 否&nbsp;&nbsp;
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font" id="hasCarMortgageTr"><span>是否拥有车贷：</span></td>
				<td id="carValueTr">
				    <input type="hidden" value="${mortgage.hasCarMortgage}" id="hasCarMortgage" />
					<input  id="hasCarMortgage_1" name="hasCarMortgage" type="radio" value="1" checked="checked" onclick="changeValue('hasCarMortgage','1');" /> 是&nbsp;&nbsp;
					<input  id="hasCarMortgage_2" name="hasCarMortgage" type="radio" value="0" onclick="changeValue('hasCarMortgage','0');"/> 否&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="carNoTr">
				<td class="rz_font">车辆型号：</td>
				<td><input class="input1" id="carNo" name="carNo" value="${mortgage.carNo}"  type="text" maxlength="100"/></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font">车辆价值：</td>
				<td><input class="input1" value="${mortgage.carValue}" id="carValue" name="carValue"  type="text" maxlength="100"/></td>
			</tr>
	 
			 
		</table>
	   </div>
	 
		<!-- 个人信息 -->
		 
		<table border="0">
		     <tr>
		      <td colspan="4">个人信息</td>
		    </tr>
			<tr height="35">
				<td class="rz_font">用户名：</td>
				<td>${map.loginMemName}</td>
				<td class="rz_font">性别：</td>
				<td>${map.loginMemGender }</td>
			</tr>
			<tr>
				<td class="rz_font">出生日期：</td>
				<td>${map.loginMemBirthDay}</td>
				<td class="rz_font">籍贯：</td>
				<td>${map.loginMemNativePlace }</td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>婚姻状况：</td>
				<td>
				    <input type="hidden" id="maritalStatus" value="${borrower.maritalStatus}"/> 
					<c:forEach items="${maritalStatusOptions}" var="o" varStatus="n">
							<c:if test="${n.count==1}">
							<input id="maritalStatus_${o.name }" name="maritalStatus" type="radio" value="${o.name }" checked="checked" onclick="changeValue('maritalStatus','${o.name }');"/>${o.value}&nbsp;&nbsp; 
							<script>
								var maritalStatus = $('#maritalStatus').val();
								if(maritalStatus == null || maritalStatus == ''){
									$('#maritalStatus').val('${o.name }');
								}
							</script>
							</c:if>
							<c:if test="${n.count!=1}">
							<input id="maritalStatus_${o.name }" name="maritalStatus" type="radio" value="${o.name }" onclick="changeValue('maritalStatus','${o.name }');"/>${o.value}&nbsp;&nbsp; 
							</c:if>
				  </c:forEach>
				</td>
				<td class="rz_font"><font color="red">*</font>学历：</td>
				<td>
					<select name="education" id="education" class="bigselect"  >
						<option value="">--请选择--</option>
						<c:forEach items="${educationOptions}" var="o">
							<option value="${o.name}" ${borrower.education==o.name?'selected="selected"':''}>${o.value}</option>
						</c:forEach>
					</select>
				</td>
			<tr>
				<td class="rz_font"><font color="red">*</font>公司行业：</td>
				<td><input class="input1" name="industry" id="industry"  type="text" value="${borrower.industry}"/></td>
				<td class="rz_font"><font color="red">*</font>公司规模：</td>
				<td>
					<select name="scale" id="scale"  class="bigselect" >
						<option value="">--请选择--</option>
						<c:forEach items="${scaleOptions}" var="o">
							<option value="${o.name}" ${borrower.scale==o.name?'selected="selected"':''}>${o.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>岗位职位：</td>
				<td><input class="input1" name="jobTitle" id="jobTitle" type="text" value="${borrower.jobTitle}"/></td>
				<td class="rz_font"><font color="red">*</font>工作城市：</td>
				<td><input class="input1" name="workCity" id="workCity" type="text" value="${borrower.workCity}"/></td>
				
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>工作时间：</td>
				<td>
					<select name="workYears" id="workYears" class="bigselect" dataType="Require" msg="请选择工作时间">
						<option value="">--请选择--</option>
						<c:forEach items="${workYearsOptions}" var="o">
							<option value="${o.name}" ${borrower.workYears==o.name?'selected="selected"':''}>${o.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	 
		 
			 
	    <!-- 连带担保 -->
		<table style="width:98%;">
		<tr>
			<td colspan="4">连带担保
			 </td>
		</tr>
		<tr>
			<td width="200" align="right">是否有连带担保物或担保人：</td>
			<td>
			    <input type="hidden" value="${salariatBorrowVo.isJointGuaranty}" id="isJointGuaranty" />
				<input   name="isJointGuaranty" id="isJointGuaranty_1" type="radio" value="1" onclick="displaySet('jointGuarantyDiv','block');attrSet('jointGuaranty-Limit','dataType');changeValue('isJointGuaranty','1');" checked="checked" /> 有&nbsp;&nbsp; 
				<input   name="isJointGuaranty" id="isJointGuaranty_2" type="radio" value="0" onclick="displaySet('jointGuarantyDiv','none');attrSet('jointGuaranty-','dataType');changeValue('isJointGuaranty','0');" /> 无&nbsp;&nbsp; 
			</td>
		</tr>
		<tr id="jointGuarantyDiv">
			<td width="210px">担保信息：</td>
			<td>
			 <textarea id="jointGuaranty" name="jointGuaranty"  cols="40" rows="4" class="textarea">${salariatBorrowVo.jointGuaranty}</textarea>
			</td>					
		</tr>
		
		<tr>
			<td colspan="4" style="text-align:center"> 
				 <input  class="b_buts" type="button"  id="btn_change_datasource" onclick="javascript:save();" value="保存" />
			     &nbsp;&nbsp;
				 <input  class="b_buts" type="button" onclick="javascript:cancle();" value="关闭"/>
			</td>
		</tr>																																	
	 </table>
	</div> 
	</form>
</body>
<script type="text/javascript">

/**
 * 编辑器样式
 */
$(function(){
	 
	var modifyUm = UM.getEditor('content', {
		initialFrameWidth:600,
		initialFrameHeight:150,
		autoHeightEnabled:false
	});
});
var _load;
function bidPasswordChage(){
	var bidPasswordNew = $('#bidPasswordNew').val();
	 
	$('#bidPassword').val(bidPasswordNew);
}

function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
function save(){
	if(!validateForm()){
		return false;
	}
	$("#btn_change_datasource").attr('disabled',true);//设置disabled属性为true，按钮不可用 
	
	$("#applyBorrowForm").ajaxSubmit ({
		url : '${path}/information/memberRegiste/saveApplyBorrow.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '1'){
				parentLayer.close(_load);
				layer.msg(result.message,1,1,function(){
					window.parent.location=window.parent.location;
		    	});
			}else{
				layer.msg(result.message,1,5,function(){
					$('#btn_change_datasource').attr('disabled',false);  
					parentLayer.close(_load);
		    	});
			}
			
		},
		error : function(data) {
			$('#btn_change_datasource').attr('disabled',false);  
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			parentLayer.close(_load);
		}
	}); 
}
/**
 * 显示还款方式,担保机构控制
 */
function showRepayWays(){
	if("checked"==$("#isGuarantyCB").attr('checked')){
		$('#isGuaranty').val(1);
	}else{
		$('#isGuaranty').val(0);
		$('#guarantyOrganization').val('');
	}
	 
}
/**
 * 赋值
 */
function changeValue(name,value){
	$("#"+name).val(value);
}
/**
* 验证表单数据
*/
function validateForm(){
	//金额的正则表达式
	var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	var numberReg = /^\d+$/;
	var characterReg = /^[a-zA-Z0-9]+$/;
	
	var isMortgage = $.trim($('#isMortgage').val());
	 
	if(isMortgage == null || isMortgage == ''){
		parent.layer.msg('请选择是否抵押！', 2, 5);
		return false;
	}
	
	var businessUserId = $('#businessUserId').val();
	if(businessUserId == '' || businessUserId.length  == 0){
		parent.layer.msg('请选择权证人员！', 2, 5);
		return false;
	}
	
	var mortgageType = $.trim($('#mortgageType').val());
	
	if(mortgageType == null || mortgageType == ''){
			parent.layer.msg('请选择抵押类型！', 2, 5);
			return false;
	}
	var name = $.trim($('#name').val());
	if(name == null || name == ''){
		parent.layer.msg('请输入借款标题！', 2, 5);
		return false;
	}
	if(name.length > 100){
		parent.layer.msg('借款标题不能超过100个字符！', 2, 5);
		return false;
	}
	
	var contractNo = $.trim($('#contractNo').val());
	if(contractNo == null || contractNo == ''){
		parent.layer.msg('请输入借款标编号！', 2, 5);
		return false;
	}
	if(contractNo.length > 30){
		parent.layer.msg('借款标编号不能超过30个字符！', 2, 5);
		return false;
	}
	
	var content = $.trim($('#content').val());
	if(content == null || content == ''){
		parent.layer.msg('请输入借款介绍！', 2, 5);
		return false;
	}
	if(content.length > 3000){
		parent.layer.msg('借款介绍不能超过3000个字符！', 2, 5);
		return false;
	}
	
	var isGuaranty = $('#isGuaranty').val();
	if(isGuaranty == 1){
		var guarantyOrganization = $('#guarantyOrganization').val();
		if(guarantyOrganization == '' || guarantyOrganization.length  == 0 || guarantyOrganization == 0){
			parent.layer.msg('请选择担保机构！', 2, 5);
			return false;
		}
	}
	
	
	var iscg=$("#iscg").val();
	if(iscg==1){
		var repayName=$("#repayName").val().trim();
		var repayAcct=$("#repayAcct").val().trim();
		if(repayName.length==0 || repayName == ''){
			parent.layer.msg('请输入还款账户名！', 2, 5);
			return false;
		}
		if(repayAcct.length==0 || repayAcct == ''){
			parent.layer.msg('请输入还款账户账号！', 2, 5);
			return false;
		}
		
	}
	
	
	
	var account = $.trim($('#account').val());
	if(account == null || account == ''){
		parent.layer.msg('请输入借款金额！', 2, 5);
		return false;
	}
	if(!zfdsReg.test(account)){
		parent.layer.msg("借款金额输入有误!", 2, 5);
		return false;
	}
	if(account < 500){
		parent.layer.msg("借款金额不能少于500元!", 2, 5);
		return false;
	}
	
	var apr = $.trim($('#apr').val());
	if(apr == null || apr == ''){
		parent.layer.msg('请输入年化利率！', 2, 5);
		return false;
	}
	if(!zfdsReg.test(apr)){
		parent.layer.msg("年化利率输入有误!", 2, 5);
		return false;
	}
	if(apr < 6){
		parent.layer.msg("年化利率不能小于6", 2, 5);
		return false;
	}
	if(apr > 24){
		parent.layer.msg("年化利率不能大于24", 2, 5);
		return false;
	}
	
	var repaymentSel = $.trim($('#repaymentSel').val());
	if(repaymentSel == null || repaymentSel == ''){
		parent.layer.msg('请选择还款方式！', 2, 5);
		return false;
	}
	
	var timeLimit = $.trim($('#timeLimit').val());
	if(timeLimit == null || timeLimit == ''){
		parent.layer.msg('请输入借款期限！', 2, 5);
		return false;
	}
	if(!numberReg.test(timeLimit)){
		parent.layer.msg("借款期限输入有误!", 2, 5);
		return false;
	}
	if(timeLimit < 1){
		parent.layer.msg("借款期限不能少于1", 2, 5);
		return false;
	}
	if(repaymentSel == 4){
		if(timeLimit > 99){
			parent.layer.msg("借款期限不能大于99", 2, 5);
			return false;
		}
	}else{
		if(timeLimit > 12){
			parent.layer.msg("借款期限不能大于12", 2, 5);
			return false;
		}
	}
	
	var lowestAccount = $.trim($('#lowestAccount').val());
	if(lowestAccount == null || lowestAccount == ''){
		parent.layer.msg('请输入最低投标金额！', 2, 5);
		return false;
	}
	if(!zfdsReg.test(lowestAccount)){
		parent.layer.msg("最低投标金额输入有误!", 2, 5);
		return false;
	}
	if(lowestAccount < 50){
		parent.layer.msg("最低投标金额不能大于50元！", 2, 5);
		return false;
	}

	if(Number(lowestAccount) > Number(account)){
		parent.layer.msg("最低投标金额不能大于借款金额!", 2, 5);
		return false;
	}
	
	var mostAccount = $.trim($('#mostAccount').val());
	if(mostAccount != null &&mostAccount.length > 0){
		if(!zfdsReg.test(mostAccount)){
			parent.layer.msg("最高投标金额输入有误!", 2, 5);
			return false;
		}
		if(Number(mostAccount) > Number(account)){
			parent.layer.msg("最高投标金额不能大于借款金额!", 2, 5);
			return false;
		}
		if(Number(mostAccount) > Number(account)){
			parent.layer.msg("最高投标金额不能大于借款金额!", 2, 5);
			return false;
		}
		if(Number(mostAccount) > 0 && Number(mostAccount) < Number(lowestAccount)){
			parent.layer.msg("最高投标金额不能小于最低投标金额!", 2, 5);
			return false;
		}
	}
	
	var validTime = $.trim($('#validTime').val());
	if(validTime == null || validTime == ''){
		parent.layer.msg('请输入有效期限！', 2, 5);
		return false;
	}
	if(!numberReg.test(validTime)){
		parent.layer.msg("有效期限输入有误!", 2, 5);
		return false;
	}
	if(validTime < 1){
		parent.layer.msg("有效期限不能小于1", 2, 5);
		return false;
	}
	if(validTime > 3){
		//parent.layer.msg("有效期限不能大于3", 2, 5);
		//return false;
	}
	
	var pledgeType = $.trim($('#pledgeType').val());
	if(pledgeType == null || pledgeType == ''){
		parent.layer.msg('请输入标的类型！', 2, 5);
		return false;
	}
	
	var bidPassword = $.trim($('#bidPassword').val());
	if(bidPassword != null &&bidPassword.length > 0){
		if(bidPassword.length < 6){
			parent.layer.msg("定向密码不能小于6个字符!", 2, 5);
			return false;
		}
		if(bidPassword.length > 12){
			parent.layer.msg("定向密码不能大于12个字符!", 2, 5);
			return false;
		}
		if(!characterReg.test(bidPassword)){
			parent.layer.msg("定向密码必须为字母或数字!", 2, 5);
			return false;
		}
	}

	var maritalStatus = $.trim($('#maritalStatus').val());
	if(maritalStatus == null || maritalStatus == ''){
		parent.layer.msg('请选择婚姻状况！', 2, 5);
		return false;
	}
	
	var education = $.trim($('#education').val());
	if(education == null || education == ''){
		parent.layer.msg('请选择学历！', 2, 5);
		return false;
	}
	
	var industry = $.trim($('#industry').val());
	if(industry == null || industry == ''){
		parent.layer.msg('请输入公司行业！', 2, 5);
		return false;
	}
	if(industry.length > 100){
		parent.layer.msg('公司行业不能超过100个字符！', 2, 5);
		return false;
	}
	
	var scale = $.trim($('#scale').val());
	if(scale == null || scale== ''){
		parent.layer.msg('请选择公司规模！', 2, 5);
		return false;
	}
	
	var jobTitle = $.trim($('#jobTitle').val());
	if(jobTitle == null || jobTitle == ''){
		parent.layer.msg('请输入岗位职位！', 2, 5);
		return false;
	}
	if(jobTitle.length > 100){
		parent.layer.msg('岗位职位不能超过100个字符！', 2, 5);
		return false;
	}
	
	var workCity = $.trim($('#workCity').val());
	if(workCity == null || workCity== ''){
		parent.layer.msg('请输入工作城市！', 2, 5);
		return false;
	}
	if(workCity.length > 100){
		parent.layer.msg('工作城市不能超过100个字符！', 2, 5);
		return false;
	}
	
	var workYears = $.trim($('#workYears').val());
	if(workYears == null || workYears == ''){
		parent.layer.msg('请选择工作时间！', 2, 5);
		return false;
	}

	var isJointGuaranty = $.trim($('#isJointGuaranty').val());
	if(isJointGuaranty == null || isJointGuaranty== ''){
		parent.layer.msg('请选择是否有连带担保物或担保人！', 2, 5);
		return false;
	}
	
	var jointGuaranty = $.trim($('#jointGuaranty').val());
	if(jointGuaranty != null &&jointGuaranty.length > 0){
		if(jointGuaranty.length > 1000){
			parent.layer.msg('担保信息不能超过1000个字符！', 2, 5);
			return false;
		}
	}
	if(window.confirm("是否要发布?")){
		return true;
	}
	return false;
}



</script>


<script type="text/javascript">
 $(function(){
	var isCustody= $("#iscg").val();
	if(isCustody==1){
		$("#qz").attr("style","display:;");
	}
}); 

function cg(){
	$("#repayAcct").val("");
	$("#repayAcct2").val("");
	$("#repayName").val("");
	$("#qz").attr("style","display:none;");
	$("#iscg").val("0");
}
function cg2(){
	$("#qz").attr("style","display:;");
	$("#iscg").val("1");
}

function findRepayAcct(){
	var name=$("#repayName").val();
	if(name==null || name.length<=0){
		$("#repayAcct").val("");
		$("#repayAcct2").val("");
	}else{
	$.ajax({
		url:"${path}/information/memberRegiste/findBorrowerUser.html",
		type:"post",
		data:{repayName:name},
		dataType:"json",
		success : function(result) {
			$("#repayAcct").val(result.repayAcct);
			$("#repayAcct2").val(result.repayAcct);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
		
		
	});
	}
	
}
</script>
</html>
