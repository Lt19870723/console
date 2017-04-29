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
   <form id="form"  action="" method="post">
   <input type="hidden"  id="id" name="id"	value="${goodSendInfo.id}" class="input1" />  
   
   <input type="hidden"  id="lotteryUseRecordId" name="lotteryUseRecordId"	value="${goodSendInfo.lotteryUseRecordId}" class="input1" />  
	<table style="margin-left:50px;margin-top:5px;align-text:center;" border="1">
	 <tr>
		<td  width="200px"     align="right">用户名：</td>
		  <td> 
			${goodSendInfo.userName}
	     </td>  
     </tr>
  
	 <tr>
		<td  align="right">奖品：</td>
		     <td>  
		    ${goodSendInfo.goodName}
		    </td>  
	 </tr>
 
	 <tr>
		<td align="right">联系人 ：</td>
		<td>
		  ${goodSendInfo.contact}
		</td>
	 </tr>

	  <tr>
		<td align="right">联系电话：</td>
		<td>
		  ${goodSendInfo.mobile}
		</td>
	 </tr>
 
	 <tr>
		<td align="right">地址：</td>
		<td>
		  ${goodSendInfo.address}
		</td>
	 </tr>
	 
	  <tr>
		<td align="right">邮编：</td>
		<td>
		${goodSendInfo.postcode}
		</td>
	 </tr>
	 
	  <tr>
		<td align="right">物流公司：</td>
		<td>
		     <input type="text" id="logisticscompany"  class="input1"  name="logisticscompany" value="${goodSendInfo.expressCompany}" />
		</td>
	 </tr>
	 
	 
	  <tr>
		<td align="right">物流编号：</td>
		<td>
		    <input type="text" id="expressCode" class="input1"  name ="expressCode" value="${ goodSendInfo.expressCode}" />
		     
		</td>
	 </tr>
	 
 
	<tr>
		<td colspan="2" align="center">
		   <input type="button"  class="b_buts"  onclick="javascript:save();" value="提交" /> 
		   &nbsp;&nbsp;
		   <input type="button"  class="b_buts"  onclick="javascript:cancle();" value="取消" />  
		</td> 
	</tr>	
   </table>
 </form>
</body>
<script type="text/javascript">
function save(){
	if(!beforeChecksaveGoodSendInfo()){
		return false;
	} 
	var _load = layer.load('处理中..');
	
	$("#form").ajaxSubmit({
		url : '${path}/lottery/gooddealuserecord/saveGoodSendInfo.html',
		type : 'post',
		success : function(result) {
			 
			if (result.code == '1') {
				layer.msg(result.message, 1, 1, function() {
					window.parent.location.href = window.parent.location.href;
				});
			} else {
				parentLayer.close(_load);
				layer.msg(result.message, 1, 5);	 
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
	    }
	});
}
function beforeChecksaveGoodSendInfo(){
	if(window.confirm("是否提交?")){
		return true;
	} 
	return false;
}
function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
</script>
</html>
