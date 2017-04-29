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
	<form id="checkBorrowPopupform"  action="" method="post">
	<input type="hidden" name="lotteryGoodsId" id="lotteryGoodsId"  value="${goodId}"/>
	
	<table style="margin-left:50px;margin-top:5px;align-text:center;" border="1">
	 <tr>
		 <td align="right">活动：</td>
		 <td> 
				
				<select id="awardType" name="lotteryChanceRuleInfoId" 
					class="bigselect" >
				  		<option value="">--请选择--</option>
				  		<c:forEach items="${chanceRuleInfosInsert }" var="vo">
				  		<option value="${vo.value }">${vo.label }</option>
				  		</c:forEach>                 
				  </select>
				 
	     </td>  
	</tr>
	<tr>
			<td align="right">中奖概率：</td>
			<td>
			     <input type="text" name="chance" id="chance"   class="input1"  maxlength="6" />
		    %  (请保留一位小数，不能超过
		    <fmt:formatNumber type="currency" value="${sourceChanceLimit}"  pattern="#,##0.00"/>%)
		    
	</tr>	   
	    
	
	<tr>
		<td colspan="2" align="center">
			<input type="button" onclick="javascript:save();" value="提交"  class="b_buts" />
		</td> 
	</tr>	
	</table>
	 
	</form>
</body>
<script type="text/javascript">
 
function save(){
	if(!beforeChecksaveActiveChance()){
		return false;
	} 
	var _load = layer.load('处理中..');
	
	$("#checkBorrowPopupform").ajaxSubmit({
		url : '${path}/lottery/good/saveGoodChance.html',
		type : 'post',
		success : function(result) {
			 
			if (result.code == '1') {
				layer.msg(result.message, 1, 1, function() {
					window.parent.location.href = window.parent.location.href;
				});
			} else {
				layer.msg(result.message, 1, 5);
				 
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
	    }
	});
}
function  beforeChecksaveActiveChance(){
	 var awardType= $.trim($('#awardType').val());
	
	 if (awardType=="") {
		 alert("请选择活动类型");
		 return false;
	}
	 
var sourceTypeChanceChance= $.trim($('#chance').val());
 if (sourceTypeChanceChance=="") {
	  alert("请输入中奖概率");
		 return false;
}
	 
   return true;
	
}
</script>
</html>
