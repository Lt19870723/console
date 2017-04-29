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
</head>
<body style="background: #fff;" >
	<form id="applyBorrowForm" action="" style="background: #fff;">
	    <!-- 借款信息 -->
	    <input class="input1" type="hidden" id="userId" name="userId" value="${userId}"/>
	    <div style="margin:20 auto;  position:relative;  float:none;  padding:0;">
	     <table>
			<tr>
				<td colspan="4">借款信息
				<input class="input1" type="hidden" id="id" name="id" value="${salariatBorrowVo.id}"/>
				</td>
			</tr>
			<tr>
				<td width="150"><font color="red">*</font>借款标题：</td>
				<td colspan="3" ><input class="input1" id="name" name="name" type="text"   /></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td width="150"><font color="red">*</font>借款标编号：</td>
				<td><input class="input1" name="contractNo" id="contractNo" type="text"  /></td>
			</tr>
			<tr>
			    <td><font color="red">*</font>借款介绍：</td>
			    <td colspan="3" ><textarea  name="content" id="content" cols="90" rows="8" class="textarea"  ></textarea>
			    </td>
			</tr> 
			<tr>
			    <td><span><font color="red">*</font>&nbsp;&nbsp;是否抵押：</span> </td>
			    <td colspan="3" >
				  <input   id="isMortgage" name="isMortgage" type="radio" value="1" checked="checked" onclick="displaySet('mortgageDiv1|mortgageDiv2|mortgageTypeDiv','block');changeValue('isMortgage','1');"/>有抵押&nbsp;&nbsp; 
				  <input  id="isMortgage" name="isMortgage"  type="radio" value="0" onclick="displaySet('mortgageDiv1|mortgageDiv2|mortgageTypeDiv','none');changeValue('isMortgage','0');" />无抵押  
			    </td>
			</tr>  
			 
			<tr>
					
			     <td id="mortgageDiv1">抵押类型：<font color="red">*</font></td> 
				 <td id="mortgageDiv2">	
				        <input name="mortgageType" id="mortgageType"  type="radio" value="1" checked="checked" />房抵&nbsp;&nbsp;
						<input name="mortgageType" id="mortgageType"  type="radio" value="2" />车抵&nbsp;&nbsp; 
						<input name="mortgageType" id="mortgageType"  type="radio" value="3" />民品抵
				</td>
					
			    <td>
						 <input type="hidden" id="isGuaranty" value="0" name="isGuaranty" value="${salariatBorrowVo.isGuaranty}"/> 
						 <input   id="isGuarantyCB" name="isGuarantyCB" type="checkbox" onclick="showRepayWays();" />是否机构担保 
				</td>	
				<td>		
						<select id="guarantyOrganization" name="guarantyOrganization" msg="请选择担保机构">
							<option value="">--请选择担保机构--</option>
							<c:forEach items="${organizationOptions }" var="o">
								<option value="${o.id }">${o.name }</option>
							</c:forEach>
						</select>
			   </td> 			
			</tr>
		</table>	 
		<table border="0">
			<tr height="35">
				<td class="rz_font"><font color="red">*</font>借款金额(元)：</td>
				<td><input class="input1" id="account" name="account"  type="text" /></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>年化利率(%)：</td>
				<td><input class="input1" name="apr" id="apr" value="6"  type="text" dataType="Range|Money" /></td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>借款期限：</td>
				<td>
					<input class="input1" id="timeLimit" name="timeLimit"  require="true"   placeholder="借款期限必须为：1到99"/>
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>最低投标金额(元)：</td>
				<td><input class="input1" value="50" id="lowestAccount" name="lowestAccount"  type="text" /></td>
			</tr>
			<tr>
				<td class="rz_font">最高投标金额(元)：</td>
				<td><input class="input1" id="mostAccount" name="mostAccount"  type="text" /></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>有效期限(天)：</td>
				<td>
					<input class="input1" value="3" name="validTime"  id="validTime" type="text" dataType="Integer|Range"   />
				</td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>还款方式：</td>
				<td>
					 
				    <select id="repaymentSel" name="style" class="bigselect">
						<c:forEach items="${repaymentStyleOptions}" var="o">
							<option value="${o.name}">${o.value}</option>
						</c:forEach>
					</select>
				 
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font"><font color="red">*</font>标的类型：</td>
				<td>
					<select name="pledgeType" id="pledgeType" class="bigselect">
						<option value="1">新增</option>
						<option value="2">续贷</option>
						<option value="3">资产处理</option>						
					</select>
				</td>
			</tr>
			<tr>
				<td class="rz_font">定向密码：</td>
				<td><input class="input1" name="bidPassword" id="bidPassword" class="input1"  type="password"  /></td>
			</tr>
		</table>
 
		<!-- 资产信息 -->
		<div id="mortgageTypeDiv">
		<table border="0" >
		     <tr>
		      <td colspan="4">资产信息
				<input class="input1" type="hidden" id="id" name="id" value="${salariatBorrowVo.id}">
			</td>
		    </tr>
			<tr>
				<td class="rz_font"><span>是否拥有房产：</span></td>
				<td>
					<input id="hasHouse" name="hasHouse" type="radio" value="1" checked="checked" onclick="displaySet('houseLocationTr|hasHouseMortgageTr|houseAreaTr','block');changeValue('hasHouse','1');"/> 是&nbsp;&nbsp;
					<input id="hasHouse" name="hasHouse" type="radio" value="0" onclick="displaySet('houseLocationTr|hasHouseMortgageTr|houseAreaTr','none');changeValue('hasHouse','0');"/> 否&nbsp;&nbsp;
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font" id="hasHouseMortgageTr"><span>是否拥有房贷：</span></td>
				<td id="houseAreaTr">
					<input   name="hasHouseMortgage" type="radio" value="1" checked="checked" /> 是&nbsp;&nbsp;
					<input   name="hasHouseMortgage" type="radio" value="0" /> 否&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="houseLocationTr">
				<td class="rz_font">房产位置：</td>
				<td><input class="input1" id="houseLocation" name="houseLocation"  type="text" maxlength="100" /></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font">房产面积：</td>
				<td><input class="input1" id="houseArea" name="houseArea"  type="text" maxlength="100"/></td>
			</tr>
			<tr>
				<td class="rz_font"><span>是否拥有车产：</span></td>
				<td>
					<input  id="hasCar" name="hasCar" type="radio" value="1" checked="checked" onclick="displaySet('carNoTr|hasCarMortgageTr|carValueTr','block');changeValue('hasCar','1');"/> 是&nbsp;&nbsp;
					<input  name="hasCar" type="radio" value="0" onclick="displaySet('carNoTr|hasCarMortgageTr|carValueTr','none');changeValue('hasCar','0');"/> 否&nbsp;&nbsp;
				</td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font" id="hasCarMortgageTr"><span>是否拥有车贷：</span></td>
				<td id="carValueTr">
					<input   name="hasCarMortgage" type="radio" value="1" checked="checked" /> 是&nbsp;&nbsp;
					<input   name="hasCarMortgage" type="radio" value="0" /> 否&nbsp;&nbsp;
				</td>
			</tr>
			<tr id="carNoTr">
				<td class="rz_font">车辆型号：</td>
				<td><input class="input1" id="carNo" name="carNo"  type="text" maxlength="100"/></td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<td class="rz_font">车辆价值：</td>
				<td><input class="input1" id="carValue" name="carValue"  type="text" maxlength="100"/></td>
			</tr>
	 
			 
		</table>
	   </div>
	 
		<!-- 个人信息 -->
		 
		<table border="0">
		     <tr>
		      <td colspan="4">个人信息
				<input class="input1" type="hidden" id="id" name="id" value="${salariatBorrowVo.id}"/>
			  </td>
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
					<c:forEach items="${maritalStatusOptions}" var="o" varStatus="n">
						<input  id="maritalStatus" name="maritalStatus" type="radio" value="${o.name }" <c:if test="${n.count==1 }" >checked="checked"</c:if>/>${o.value}&nbsp;&nbsp; 
					</c:forEach>
				</td>
				<td class="rz_font"><font color="red">*</font>学历：</td>
				<td>
					<select name="education" id="education" class="bigselect"  >
						<option value="">--请选择--</option>
						<c:forEach items="${educationOptions}" var="o">
							<option value="${o.name}">${o.value}</option>
						</c:forEach>
					</select>
				</td>
			<tr>
				<td class="rz_font"><font color="red">*</font>公司行业：</td>
				<td><input class="input1" name="industry" id="industry"  type="text" /></td>
				<td class="rz_font"><font color="red">*</font>公司规模：</td>
				<td>
					<select name="scale" id="scale"  class="bigselect" >
						<option value="">--请选择--</option>
						<c:forEach items="${scaleOptions}" var="o">
							<option value="${o.name}">${o.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>岗位职位：</td>
				<td><input class="input1" name="jobTitle" id="jobTitle" type="text" /></td>
				<td class="rz_font"><font color="red">*</font>工作城市：</td>
				<td><input class="input1" name="workCity" id="workCity" type="text" dataType="Require" min="1" max="100" msg="工作城市不能为空，且不能超过100个字符"/></td>
			</tr>
			<tr>
				<td class="rz_font"><font color="red">*</font>工作时间：</td>
				<td>
					<select name="workYears" id="workYears" class="bigselect" dataType="Require" msg="请选择工作时间">
						<option value="">--请选择--</option>
						<c:forEach items="${workYearsOptions}" var="o">
							<option value="${o.name}">${o.value}</value>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	 
		 
			 
	    <!-- 连带担保 -->
		<table style="width:98%;">
		<tr>
			<td colspan="4">连带担保
				<input class="input1" type="hidden" id="id" name="id" value="${salariatBorrowVo.id}"/>
			 </td>
		</tr>
		<tr>
			<td width="200" align="right">是否有连带担保物或担保人：</td>
			<td>
				<input   name="isJointGuaranty" id="isJointGuaranty" type="radio" value="1" onclick="displaySet('jointGuarantyDiv','block');attrSet('jointGuaranty-Limit','dataType');changeValue('isJointGuaranty','1');" checked="checked" /> 有&nbsp;&nbsp; 
				<input   name="isJointGuaranty" id="isJointGuaranty" type="radio" value="0" onclick="displaySet('jointGuarantyDiv','none');attrSet('jointGuaranty-','dataType');changeValue('isJointGuaranty','0');" /> 无&nbsp;&nbsp; 
			</td>
		</tr>
		<tr id="jointGuarantyDiv">
			<td width="210px">担保信息：</td>
			<td>
			 <textarea id="jointGuaranty" name="jointGuaranty" cols="40" rows="4" class="textarea" require="false" dataType="Limit" min="1" max="1000" msg="担保信息不能超过1000个字符"></textarea>
			</td>					
		</tr>
		
		<tr>
			<td colspan="4" style="text-align:center"> 
				 <input  class="b_buts" type="button" onclick="javascript:save();" value="保存" />
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
 * 赋值
 */
function changeValue(name,value){
	$("#"+name).val(value);
}
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

function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
function save(){
	if(!validateForm()){
		return false;
	} 
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
					parentLayer.close(_load);
		    	});
			}
			
		},
		error : function(data) {
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
* 验证表单数据
*/
function validateForm(){
	//金额的正则表达式
	var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	var numberReg = /^\d+$/;
	var characterReg = /^[a-zA-Z0-9]+$/;
	
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
	
	var isMortgage = $.trim($('#isMortgage').val());
	if(isMortgage == null || isMortgage == ''){
		parent.layer.msg('请选择是否抵押！', 2, 5);
		return false;
	}
	
	var mortgageType = $.trim($('#mortgageType').val());
	if(mortgageType == null || mortgageType == ''){
		parent.layer.msg('请选择抵押类型！', 2, 5);
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
		if(characterReg.test(bidPassword)){
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
</html>
