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
	<table style="align:center;" border="1">
	 <tr>
		<td  width="120px"  align="right">奖品类型：</td>
		     <td> 
		  
		        <select id="awardType"   ${good.id!= 0 ?'disabled=disabled':''} name="awardType"   class="bigselect"  onchange="disabledInput();"> 
		          <option value="">==请选择==</option>
		          <option value="1" ${good.awardType==1?'selected="selected"':''}>现金</option>
		          <option value="2" ${good.awardType==2?'selected="selected"':''}>实物</option>
		          <option value="3" ${good.awardType==3?'selected="selected"':''}>谢谢参与</option>
		          <option value="4" ${good.awardType==4?'selected="selected"':''}>抽奖机会</option>
		          <option value="5" ${good.awardType==5?'selected="selected"':''}>红包</option>
		          <option value="6" ${good.awardType==6?'selected="selected"':''}>元宝</option>
		        </select>	 
	     </td>  
	 </tr>
			   
	   <tr>
		<td  align="right">奖品：</td>
		     <td>  
				 <input type="text" id="name" name="name" value="${good.name}"  maxlength="20" class="input1"   /><label style="display: none"   id="yuan1"></label><label style="display: none" id="ci">次（如果有多个请用英文逗号“,”分隔）</label>
		    </td>  
	   </tr>
	
	 <tr>
		<td align="right">奖品价值 ：</td>
		<td>
		    <input type="text"    id="awardMoney"   name="awardMoney"class="input1"     maxlength="20" 	value="${good.awardMoney}"  >
	
		    <label style="display: none" id="yuan2">元</label>
		</td>
	 </tr>
	
	  <tr>
		<td align="right">中奖概率：</td>
		<td>
		    <input type="text"    id="chance"   name="chance" class="input1"    maxlength="6" 	value="<fmt:formatNumber type="currency" value="${good.chance}"  pattern="#,##0.00"/>" />
		    %  (请保留一位小数，不能超过
		    <fmt:formatNumber type="currency" value="${100-sumChance}"  pattern="#,##0.00"/>%)

		</td>
	 </tr>
	 
	  <tr>
		<td align="right">奖品领取有效期：</td>
		<td>
		    <input type="text"    id="validDay"   name="validDay" class="input1"   maxlength="3"  	value="${good.validDay}"   /> 天 (不填默认无限期)
		</td>
	 </tr>
	
	 <tr>
		<td align="right">中奖子概率：</td>
		<td>
		
		<input type="text"    id="chirldChanceStr" class="input1"    name="chirldChanceStr" value="${good.chirldChanceStr}" />(每项请保留一位小数)
		 
		</td>
	 </tr>
	
	 <tr>
			<td align="right">转盘位置：</td>
			<td>
			    <select id="turntablePosition" name="turntablePosition" class="bigselect" >
		          <option value="">==请选择==</option>
		          <option value="1" ${good.turntablePosition==1?'selected="selected"':''}>1</option>
		          <option value="2" ${good.turntablePosition==2?'selected="selected"':''}>2</option>
		          <option value="3" ${good.turntablePosition==3?'selected="selected"':''}>3</option>
		          <option value="4" ${good.turntablePosition==4?'selected="selected"':''}>4</option>
		          <option value="5" ${good.turntablePosition==5?'selected="selected"':''}>5</option>
		          <option value="6" ${good.turntablePosition==6?'selected="selected"':''}>6</option>
		          <option value="7" ${good.turntablePosition==7?'selected="selected"':''}>7</option>
		          <option value="8" ${good.turntablePosition==8?'selected="selected"':''}>8</option>
		        </select>
			</td>
	 </tr>
	
	  <tr style="${good.id==0?'display:none':''}"  > 
			<td   colspan="2">
			    <table  align="center"   width="100%"  border="1">
			         <tr>
				         <td>指定活动中奖概率  </td>
				         <td>
					         <input type="button"  class="b_buts"  onclick="javascript:addActiveChance('${good.id}');" value="新 增" />  
				         </td>
			         </tr>
			          <tr>
			          <td  colspan="2">
				            <div style="width:100%;border:0" >   
						          <table  border="0"  width="100%" >
						              <c:forEach items="${sourceTypeChances }"   var="sourceTypeChance">
										   <tr>
										       <td>${sourceTypeChance.ruleName}</td>
										       <td><fmt:formatNumber type="currency" value="${sourceTypeChance.chance}"  pattern="#,##0.00"/>%</td> 
											   <td><input type="button"   class="b_buts"  onclick="javascript:deleteGoodChance('${sourceTypeChance.id}');" value="删除" /></td>
									       </tr>
									   </c:forEach>
									    
								   
						          </table>
				             </div>
			           </td>
			          
			          </tr>
			         
			    </table>
			</td>
	 </tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="button"  class="b_buts"  onclick="javascript:save();" value="提交" />     
		</td> 
	</tr>	
	</table>
	 <input type="hidden" id="id" name="id" value="${good.id }">
	</form>
</body>
<script type="text/javascript">
var _load;

$(function(){
	disabledInput();
}); 

function save(){
	if(!beforeChecksaveGood()){
		return false;
	} 	 
	$.ajax({
		url : '${path}/lottery/good/saveGood.html',
		data : {
			'id' :$("#id").val(),
			'name':$('#name').val(),
			'awardMoney':$('#awardMoney').val(),
			'chance':$('#chance').val(),
			'validDay':$('#validDay').val(),
			'turntablePosition':$('#turntablePosition').val(),
			'awardType':$('#awardType').val(),
			'chirldChanceStr':$('#chirldChanceStr').val()
		},
		type : 'post',
		dataType : 'json',
		beforeSend: function(){
			_load = layer.load('处理中..');
	     },
		success : function(result) {
			if(result.code == '1'){
				parentLayer.close(_load);
				layer.msg(result.message,1,1);
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
				 
			}else{
				layer.msg(result.message,1,5);
				parentLayer.close(_load);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			parentLayer.close(_load);
	    }
	}); 
}
function  beforeChecksaveGood(){
	 
	 var awardType= $.trim($('#awardType').val());
	 if (awardType=="") {
		alert("请选择奖品类型");
		return false;
	}
	 
	 var chance= $.trim($('#chance').val());
	 if (chance=="") {
		alert("请输入中奖概率");
		return false;
	}
	 
	 var turntablePosition= $.trim($('#turntablePosition').val());
	 if (turntablePosition=="") {
		alert("请选择转盘位置");
		return false;
	}
	 
	 return true;
}
function addActiveChance(id){
	 
	var title="置顶活动中的概率";
	var _url = '${path}/lottery/good/addActiveChance.html?id='+id
	$.layer({
		type : 2,
		title : title,
		area : [ '80%', '80%' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			 
	    }
	});
	 
}
function deleteGoodChance(id){
	
	if(!confirm("确认要删除吗？")){
		return false;
	} 
	_load = layer.load('处理中..');
	$.ajax({
		url : '${path}/lottery/good/deleteGoodChance.html',
		data : {
			'id' :id
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					window.location.href = window.location.href ;
				});
			}else  {
				parentLayer.close(_load);
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			parentLayer.close(_load);
	    }
	}); 
}
function clearaddData(){
	$('#awardType').val("");
	$('#name').val("");
	$('#awardMoney').val("");
	$('#chance').val("");
	$('#chirldChanceStr').val("");
	$('#turntablePosition').val("");
	disabledInput();
}
function  disabledInput(){
	var awardType= $('#awardType').val();
	if (awardType=="") {
		$('#chirldChanceStr').removeAttr("disabled");
		$('#name').removeAttr("disabled");
    	$('#awardMoney').removeAttr("disabled");
		return  ;
	}
	
	if(awardType==1||awardType==2){
		$("#ci").hide();
		$("#yuan2").show();
		if (awardType==1) {
			$("#yuan1").show();
		}else{
			$("#yuan1").hide();
		}
		$('#chirldChanceStr').attr("disabled","true");
		$('#name').removeAttr("disabled");
    	$('#awardMoney').removeAttr("disabled");
	} 
	
	if(awardType==3){
		$("#ci").hide();
		$("#yuan2").hide();
		$("#yuan1").hide();
    	$('#name').attr("disabled","true");
    	$('#chirldChanceStr').attr("disabled","true");
    	$('#awardMoney').attr("disabled","true");
	}
	
	if(awardType==4){
		$("#ci").show();
		$("#yuan2").hide();
		$("#yuan1").hide();
		$('#awardMoney').attr("disabled","true");
		$('#chirldChanceStr').removeAttr("disabled");
		$('#name').removeAttr("disabled");
	}
	
}
</script>
</html>
