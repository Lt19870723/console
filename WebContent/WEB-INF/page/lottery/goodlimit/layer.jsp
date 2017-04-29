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
	<table style="margin-left:50px;margin-top:5px;align-text:center;" border="1">
	  <tr>
		<td  align="right">奖品：</td>
		<td>
		     <select id="goodName" name="lotteryGoodsId"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  	    <c:forEach items="${chanceRuleInfosInsert }" var="vo">
				  	     <c:if test="${goodsLimit.lotteryGoodsId==vo.value  }">
				  		 <option value="${vo.value }" selected="selected">${vo.label }</option>
				  		 </c:if>
				  		 <c:if test="${goodsLimit.lotteryGoodsId!=vo.value  }">
				  		 <option value="${vo.value }">${vo.label }</option>
				  		 </c:if>
				  		</c:forEach>         
		     </select>  
		 </td>    
	   </tr>
 
	 <tr>
		<td align="right">日期：</td>
		<td>
			<input type="text" name="startTimeStr" id="startTimeStr" value="<fmt:formatDate value="${goodsLimit.startTime}"
			 pattern="yyyy-MM-dd"/>" onclick="WdatePicker()" styleClass="Wdate"/>
			到
			<input type="text" name="endTimeStr" id="endTimeStr" value="<fmt:formatDate value="${goodsLimit.endTime}"
			 pattern="yyyy-MM-dd"/>" onclick="WdatePicker()" styleClass="Wdate"/>
		</td>
	 </tr>

	  <tr>
		<td align="right">总次数：</td>
		<td>
		    <input type="text"   id="sumNum"   name="sumNum"    maxlength="4" 	value="${goodsLimit.sumNum}"   />  
		</td>
	 </tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="button"  class="b_buts"  onclick="javascript:save();" value="提交" />     
		</td> 
	</tr>	
	</table>
	 <input type="hidden" value="${goodsLimit.id }" name="id" id="id"/> 
	</form>
</body>
<script type="text/javascript">
 
function save(){
	if(!beforeChecksavegoodLimit()){
		return false;
	} 
	var _load = layer.load('处理中..');
	
	$("#checkBorrowPopupform").ajaxSubmit({
		url : '${path}/lottery/goodlimit/saveGoodLimit.html',
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
function  beforeChecksavegoodLimit(){
	 
	 var awardType= $.trim($('#goodName').val());
	 if (awardType=="") {
		alert("请选择奖品");
		return false;
	}
	 
	 var addtimeBegin= $.trim($('#startTimeStr').val());
	 if (addtimeBegin=="") {
		alert("请填写开始时间");
		return false;
	}
	 
	 var addtimeEnd= $.trim($('#endTimeStr').val());
	 if (addtimeEnd=="") {
		alert("请填写结束时间");
		return false;
	}
	 
	 return true;
}
</script>
</html>
