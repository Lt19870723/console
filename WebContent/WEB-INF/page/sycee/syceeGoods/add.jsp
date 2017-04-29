<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${path }/js/formValid.js?${version}"></script>
<script type="text/javascript" src="${path }/js/util.js?${version}"></script>
<title>内容维护-元宝商城-新增商 品</title>
<style type="text/css">
.bigselect{
	width:200px;
}
.input1{
	width:200px;
}
form{
	background-color: #f9f9f9;
}
textarea{
	width: 562px; 
	height: 50px;
}
</style>
</head>
<body style="background: #f9f9f9; padding-left: 20px;">
	<table width="100%">
		<tr>
			<td width="15%" align="right" height="35"><font color="red">*</font>图片(240*151)：</td>
			<td colspan="3">
				<div id="showPic" ${syceeGoods==null?'style="display: none"':'' }>
					<img width="240" height="151" id="showpic" src="${syceeGoods.imgurl}" />
					<a href="javascript:editPic();">编辑</a>
				</div>
				<form id="myform" ${syceeGoods!=null?'style="display: none"':'' } >
					<input class="rz_input mr5" type="file" name="files" id="files" maxlength="180" /> 
					<input class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadPic();" value="图片上传" />
				</form>
			</td>
		</tr>
	</table>
	<form id="approForm" method="post">
		<table width="100%">
			<tr>
				<td width="15%" align="right"><font color="red">*</font>一级分类：</td>
				<td> 
					<select name="firstClass" id="firstClass" class="bigselect" onchange="setSecondClass();">
						<c:forEach items="${e2}" var="o">
							<option value="${o.name}" ${syceeGoods.firstClass==o.name?'selected="selected"':''}>${o.value}</option>
						</c:forEach>
					</select>
				</td>
				<td width="15%" align="right"><font color="red">*</font>二级分类：</td>
				<td> 
					<select name="secondClass" id="secondClass" class="bigselect"  onchange="classChange()">
						<c:if test="${syceeGoods.firstClass==1 or empty syceeGoods.firstClass}">
						<c:forEach items="${e3}" var="o">
							<option value="${o.name}">${o.value}</option>
						</c:forEach>
						</c:if>
						<c:if test="${syceeGoods.firstClass==2}">
						<c:forEach items="${e4}" var="o">
							<option value="${o.name}">${o.value}</option>
						</c:forEach>
						</c:if>
					</select>
					<select name="hongbao" id="hongbao" class="bigselect" onchange="setHongBao()" style="width: 100px;">
						<c:forEach items="${e1}" var="o">
							<option value="${o.name}">${o.name}元</option>
						</c:forEach>
			 		</select>
			 		<c:forEach items="${e1}" var="o">
			 			<input type="hidden" value="${o.value }" id="${o.name}hbsycee"/>
			 		</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>商品名称：</td>
				<td>
					<input type="hidden" name="id" id="id" value="${syceeGoods.id}" />
					<input id="name" dataType="Require" msg="商品名称不能为空"  name="name" value="${syceeGoods.name}" class="input1" maxlength="30" />
				</td>
				<td align="right"><font color="red">*</font>状态：</td>
				<td> 
					<select name="showFlag" id="showFlag" class="bigselect">
						<option value="1" selected="selected">上架</option>
						<option value="2">下架</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>实际价值(元)：</td>
				<td><input id="money" dataType="Require|Money" msg="实际价值不能为空|实际价值格式错误" value="${syceeGoods.money}" class="input1" name="money" maxlength="10"/></td>
				<td align="right"><font color="red">*</font>元宝(原价)：</td>
				<td><input id="oldSycee" value="${syceeGoods.oldSycee}" dataType="Require|Number" msg="所需元宝不能为空|元宝只能为整数" class="input1" name="oldSycee" maxlength="10" onchange="setDisSycee()"/></td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>折扣(折)：</td>
				<td><input id="discount" dataType="Require|Money" msg="折扣不能为空|折扣格式错误" value="${syceeGoods.discount}" class="input1" name="discount" maxlength="5" onchange="setDisSycee()" /></td>
				<td align="right"><font color="red">*</font>元宝(折扣价)：</td>
				<td><input id="sycee" value="${syceeGoods.sycee}" dataType="Require|Number" msg="所需元宝不能为空|元宝只能为整数" class="input1" name="sycee" maxlength="10" readonly="readonly"/>(根据原价和折扣自动计算)</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>数量：</td>
				<td><input id="num" dataType="Require|Number" msg="数量不能为空|请输入数字" value="1" readonly="readonly" class="input1" name="num"  /></td>
				<td align="right">有效期：</td>
				<td><input id="validDay" value="${syceeGoods.validDay}" class="input1" name="validDay" readonly="readonly" />(为空表示长期有效)</td>
			</tr>
			<tr>
				<td align="right">使用方法：</td>
				<td colspan="3" ><textarea id="useWay" name="useWay">${syceeGoods.useWay}</textarea></td>
			</tr>
			<tr>
				<td align="right">使用说明：</td>
				<td colspan="3" ><textarea id="useExp" name="useExp">${syceeGoods.useExp}</textarea></td>
			</tr>
			<tr>
				<td align="right">兑换方法：</td>
				<td colspan="3" ><textarea id="exchangeExp" name="exchangeExp">${syceeGoods.exchangeExp}</textarea></td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td colspan="3" ><textarea id="remark" name="remark">${syceeGoods.remark}</textarea></td>
			</tr>
			<c:if test="${not empty syceeGoods.id && syceeGoods.approveStatus>0}">
			<tr>
				<td colspan="4">
					<div class="listzent">审核记录</div>
					<table width="100%">
						<tr>
							<th width="15%">审核人</th>
							<th width="15%">审核意见</th>
							<th width="25%">审核时间</th>
							<th>审核备注</th>
						</tr>
						<tr align="center" class="tr_0">
							<td>${syceeGoods.approveUsername}</td>
							<td>${syceeGoods.approveStatus==-1?'草稿':syceeGoods.approveStatus==0?'待审核':syceeGoods.approveStatus==1?'审核通过':'审核不通过'}</td>
							<td><fmt:formatDate value="${syceeGoods.approveTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							<td>${syceeGoods.approveRemark}</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="4" align="center">
					<c:if test="${syceeGoods.approveStatus ne 1}">
						<input type="button" onclick="javascript:save('subSave');" value="保存" /> &nbsp;
					</c:if>
					<input type="button" onclick="javascript:save('subAppr');" value="提交审核" /> &nbsp;
					<input type="button" onclick="javascript:cancleButton();" value="取消" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="imgurl" id="imgurl" value="${syceeGoods.imgurl}" />
	</form>
</body>
<script type="text/javascript">
//如果类型为已审核通过,则只能修改图片、说明文字、折扣
$(function(){
	if(${syceeGoods.approveStatus eq 1}){
		$("input:text").each(function(){
			$(this).attr("disabled","disabled");
		});
		$("select").each(function(){
			$(this).attr("disabled","disabled");
		});
	}
	$('#discount').removeAttr("disabled");
	$('#sycee').removeAttr("disabled");
});

$(function(){
	if($("#id").val()!=''){
		$('#hongbao').val(parseInt('${syceeGoods.money}'));
		$('#firstClass').val('${syceeGoods.firstClass}');
		$('#secondClass').val('${syceeGoods.secondClass}');
		if($('#firstClass').val()==1){
			classChange();
		}else{
			$('#hongbao').hide();
		};
		$('#showFlag').val('${syceeGoods.showFlag}');
		$('#name').val('${syceeGoods.name}');
		$('#money').val('${syceeGoods.money}');
		$('#oldSycee').val('${syceeGoods.oldSycee}');
	}else{
		setSecondClass();
	}
	setDisSycee();
	
});

/**
 * 计算折扣元宝价
 */
function setDisSycee(){
	if($("#discount").val()==''){
		$("#discount").val(10);
	}
	$('#sycee').val(parseInt($('#oldSycee').val()*$("#discount").val()*0.1));
}

/**
 * 根据商品类型，设置商品信息
 */
function classChange(){
	var _fc = $('#firstClass').val();
	var _sc = $('#secondClass').val();
	if(_fc==1){
		if(_sc==1)setHongBao();
		if(_sc==2)setChouJiang();
		$('#exchangeExp').html('');
		attrSet('name|money|sycee|validDay|oldSycee','readonly','readonly');
	}else{
		$('#useWay').val('');
		$('#useExp').val('');
		$('#exchangeExp').html("兑换成功后系统将在7个工作日之内处理");
		$('#hongbao').hide();
		attrSet('name|money|sycee|validDay|oldSycee|discount','value','');
		attrRemove('name|money|oldSycee','readonly');
	}
}
/**
 * 根据一级分类，设置二级分类
 */
function setSecondClass(){
	var _fc = $('#firstClass').val();
	if(_fc==1){
		$('#secondClass').empty();
		$("#secondClass").append("<c:forEach items='${e3}' var='o'>");
		$("#secondClass").append("<option value='${o.name}'>${o.value}</option>");
	    $("#secondClass").append("</c:forEach>");
	}
	if(_fc==2){
		$('#secondClass').empty();
		$("#secondClass").append("<c:forEach items='${e4}' var='o'>");
		$("#secondClass").append("<option value='${o.name}'>${o.value}</option>");
	    $("#secondClass").append("</c:forEach>");
	}
	classChange();
}
/**
 * 设置抽奖
 */
function setChouJiang(){
	$('#hongbao').hide();
	$('#name').val('1次抽奖机会');
	$('#money').val('1.5'); 
	$('#oldSycee').val(1500);
	$('#validDay').val('');
	$('#useWay').val('');
	$('#useExp').val('兑换后至官网首页顶部点击”抽大奖“进入抽奖活动页，再点击转盘抽奖');
	setDisSycee();
}
/**
 * 设置红包
 */
function setHongBao(){
	$('#hongbao').show();
	var _hb = $('#hongbao').val();
	if(_hb==null){$('#hongbao').val(10);_hb=10;}
	$('#name').val(_hb+'元红包');
	$('#money').val(_hb);
	$('#oldSycee').val($("#"+_hb+"hbsycee").val());
	$('#validDay').val('30');
	$('#useWay').val('兑换后至我的账户->有奖活动查看，投资时选择红包抵扣相应投资金额。');
	$('#useExp').val('单笔投资满'+(_hb*100)+'元可用，仅支持定期宝。');
	setDisSycee();
}

/**
 * 取消
 */
function cancleButton(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}


/**
 * 保存&提交审核
 */
var _load;
function save(_subWay){
	if (Validator.Validate('approForm',4)==false) {
		return;
	}
	if($('#imgurl').val()==''){
		layer.msg('请上传商品图片',1,5);return;
	}
	layer.confirm("确认提交吗？",function(){
		$("#approForm").ajaxSubmit({
			url : '${path}/account/syceeGoods/save.html?subWay='+_subWay,
			type : 'post',
			dataType :"json",
			async:false,
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
				parentLayer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
		    }
		});
	});
} 
/**
 * 上传商品图片
 */
function uploadPic(){
	var files = $('#files').val();
	if(null==files || $.trim(files)==""){
		layer.msg("请选择需要上传的图片!!");
		return false;
	}
	$("#myform").ajaxSubmit({
		url : '${path}/account/syceeGoods/upload.html',
		type : 'post',
		dataType :"json",
		async:false,
		beforeSend:function(){
			 _load = parentLayer.load('处理中..');
		}, 
		success : function(result) {
			parentLayer.close(_load);
			if (result.code == '0') {
				$('#imgurl').val('${slideImagePath}' +result.message);
				$("#showPic").show();
			 	$("#myform").hide();
				$('#showpic').attr("src", '${slideImagePath}' + result.message);
				layer.msg('上传成功',1,1);
			}else  {
				parentLayer.close(_load);
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			parentLayer.close(_load);
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
	    }
	});
}
function editPic(){
 	$("#showPic").hide();
 	$("#myform").show();
}

 
</script>
</html>
