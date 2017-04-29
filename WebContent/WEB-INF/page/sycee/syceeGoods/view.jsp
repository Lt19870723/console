<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${path }/js/formValid.js?${version}"></script>
<title>内容维护-元宝商城-商品详情</title>
</head>
<body>
		<table width="100%">
			<tr>
				<td align="right">图片(240*151)：</td>
				<td colspan="3">
					<img alt=""  src="${syceeGoods.imgurl}" width="240" height="151" />
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">一级分类：</td>
				<td> 
					${syceeGoods.firstClass==1?'线上':'线下'}
				</td>
				<td width="20%" align="right">二级分类：</td>
				<td> 
					<c:if test="${syceeGoods.firstClass==1}">
				    	${syceeGoods.secondClass==1?'红包':'抽奖'}
					</c:if>
					<c:if test="${syceeGoods.firstClass==2}">
						<c:if test="${syceeGoods.secondClass==1}">电影劵</c:if>
						<c:if test="${syceeGoods.secondClass==2}">实物</c:if>
						<c:if test="${syceeGoods.secondClass==3}">浦菲优澜健身券</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="right">商品名称：</td>
				<td>
					${syceeGoods.name}
				</td>
				<td align="right">状态：</td>
				<td> 
					${syceeGoods.showFlag==1?'上架':'下架' }
				</td>
			</tr>
			<tr>
				<td align="right">实际价值(元)：</td>
				<td><fmt:formatNumber value="${syceeGoods.money }" pattern="###,###.00" /></td>
				<td align="right">所需元宝(原价)：</td>
				<td><fmt:formatNumber value="${syceeGoods.oldSycee }" pattern="###,###" /></td>
			</tr>
			<tr>
				<td align="right">折扣(折)：</td>
				<td><fmt:formatNumber value="${syceeGoods.discount }" pattern="##.##" /></td>
				<td align="right">所需元宝(折扣价)：</td>
				<td><fmt:formatNumber value="${syceeGoods.sycee }" pattern="###,###" /></td>
			</tr>
			<tr>
				<td align="right">数量：</td>
				<td>${syceeGoods.num}</td>
				<td align="right">有效期：</td>
				<td>${syceeGoods.validDay==0?'长期有效':syceeGoods.validDay}</td>
			</tr>
			<tr>
				<td align="right">使用方法：</td>
				<td colspan="3" >${syceeGoods.useWay}</td>
			</tr>
			<tr>
				<td align="right">使用说明：</td>
				<td colspan="3" >${syceeGoods.useExp}</td>
			</tr>
			<tr>
				<td align="right">兑换方法：</td>
				<td colspan="3" >${syceeGoods.exchangeExp}</td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td colspan="3" >${syceeGoods.remark}</td>
			</tr>
			<tr>
				<td align="right">审核状态：</td>
				<td>${syceeGoods.approveStatus==-1?'草稿':syceeGoods.approveStatus==0?'待审核':syceeGoods.approveStatus==1?'审核通过':'审核不通过'}</td>
				<td align="right">添加时间：</td>
				<td><fmt:formatDate value="${syceeGoods.addtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
			<c:if test="${syceeGoods.approveStatus!=0 }">
			<tr>
				<td align="right">审核人：</td>
				<td>${syceeGoods.approveUsername}</td>
				<td align="right">审核时间：</td>
				<td><fmt:formatDate value="${syceeGoods.approveTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
			<tr>
				<td align="right">审核备注：</td>
				<td colspan="3">${syceeGoods.approveRemark}</td>
			</tr>
			</c:if>
			<c:if test="${syceeGoods.approveStatus==0 && opt=='approve' }">
			<form id="approForm" method="post">
				<tr>
					<td align="right">审核意见：</td>
					<td colspan="3">
						<select name="approveStatus" id="approveStatus" class="bigselect">
							<option value="1" selected="selected">审核通过</option>
							<option value="2">审核不通过</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right"><font color="red">*</font>审核备注：</td>
					<td colspan="3">
						<textarea id="approveRemark" name="approveRemark" style="width: 350px; height: 100px;"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3">
						<input type="button" onclick="subApprove();" value="提交" /> &nbsp; 
						<input type="button" onclick="cancleButton();" value="取消" />
					</td>
				</tr>
			</form>
			</c:if>
		</table>
</body>
<script type="text/javascript">
function cancleButton(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}


/**
 * 审核-提交
 */
function subApprove(){
	var _load;
	var verifyRemark = $("#approveRemark").val();
	 
	if(null==verifyRemark || $.trim(verifyRemark)==""){
		layer.msg("审核备注不能为空。",1,5);
		return false;
	}
	layer.confirm("确认提交吗？",function(){
		$.ajax({
			url : '${path}/account/syceeGoods/approve.html',
			data : {
				'id' :'${syceeGoods.id}',
				'approveStatus' : $.trim($("#approveStatus").val()),
				'approveRemark' : verifyRemark
			},
			type : 'post',
			async:false,
			dataType : 'json',
			beforeSend:function(){
				 _load = parentLayer.load('处理中..');
			}, 
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '1') {
					layer.msg(result.message, 1, 1, function() {
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						parent.layer.close(index); //执行关闭
						layer.msg(result.message,1,1);
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
	});
	
}
</script>
</html>
