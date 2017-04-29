<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>定期宝管理-定期宝列表-设置限制自动投标</title>
</head>
<body style="background: #fff;">
<table style="width: 98%; margin: 10 auto; position: relative; float: none; padding: 0; text-align: left;" border="1">
	<tr>
		<td width="35%" align="right">是否限制自动投标：</td>
		<td>
			<input type="radio" value="1" name="tenderBidFlag" onclick="$('#tenderBidDate').removeAttr('disabled');" checked="checked">限制
			<input type="radio" value="0" name="tenderBidFlag" onclick="$('#tenderBidDate').attr('disabled','disabled');"
				<c:if test="${cf.name!=1 }">checked="checked"</c:if>>不限制
		</td>
	</tr>
	<tr>
		<td align="right">限制自动投标截止日期：</td>
		<td>
			<fmt:parseDate var="dateObj" value="${cf.value}"></fmt:parseDate>
			<input id="tenderBidDate" name="tenderBidDate" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})" styleClass="Wdate" 
				value="<fmt:formatDate value='${dateObj}' pattern='yyyy-MM-dd' />" <c:if test="${cf.name!=1 }">disabled="disabled"</c:if>>
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input>
		</td>
	</tr>
	<tr align="center">
		<td colspan="4">
			<input type="button" class="b_buts" value="提 交" onclick="setTenderBidSub()" />
			<input type="button" class="b_buts" value="关 闭" onclick="cancel()" />
		</td>
	</tr>
</table>

</body>
<script type="text/javascript">
	//关闭
	function cancel() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
	//提交
	function setTenderBidSub(){
		var _tenderBidFlag= $("input[name='tenderBidFlag']:checked").val();
		var _tenderBidDate= $("#tenderBidDate").val();
		if(_tenderBidFlag==1 && _tenderBidDate==''){
			layer.alert('请填写限制自动投标截止日期');
			return;
		}
		layer.confirm("确认提交吗？",function(){
			var _load; 
			_load = layer.load('处理中..');
			$.ajax({
				url : '${path}/fix/fixborrow/setTenderBidSub.html',
				data : {
					'tenderBidFlag':_tenderBidFlag,
					'tenderBidDate':_tenderBidDate,
					'fixId':'-1'
				},
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if (result.code == '1') {
						layer.msg(result.message, 1, 1, function() {
							window.parent.location=window.parent.location;
						});
					} else {
						layer.close(_load);
						layer.msg(result.message, 1, 5);
					}
				},
				error : function(result) {
					layer.msg('网络连接超时,请您稍后重试.', 1, 5);
					layer.close(_load);
				}
			});
		});
	}
</script>
</html>
