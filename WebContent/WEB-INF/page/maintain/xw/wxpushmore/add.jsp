<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js?${version}"></script>
<title>新增-图文消息</title>
</head>
<body>
<div >
	<div class="left1_input_tst" style="margin-bottom:210px;">
		<font class="red">&nbsp;图片建议像素为360*200</font>
		<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>图片：</span>
		<div class="input_IDs">
			<img id="oneimageId" style="width:360px;height:200px;"
				src="${consoleWxImagePath}${wxArticles.picurl}"></img>
			<form  id="myfrom"   name="myfrom" method="post" >
				<input  class="rz_input mr5" type="file" name="files" id="btnFile"  onchange="forupload('myfrom','picurl','oneimageId')" dataType="Require" msg="请选择图片！" style="width:85px"/>
			</form>
		</div>
	</div>
	<form id="addOneNewsForm" name="addOneNewsForm" method="post">
		<input type="hidden" id="id" name="wxArticles.id" value='${wxArticles.id}' />
		<input type="hidden" id="picurl" name="wxArticles.picurl" value='${wxArticles.picurl}' />
		<div class="left1_input_tst"  style="margin-bottom:5px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>标题：</span>
			<div class="input_IDs">
				<input type="text" id="title" name="wxArticles.title" value="${wxArticles.title}" class="input1" dataType="Require" msg="请输入标题！" style="width:90%" maxlength="64" />
			</div>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>超链接：</span>
			<div class="input_IDs">
				<input type="text" id="url" name="wxArticles.url" value="${wxArticles.url}" dataType="Require|Url" msg="请输入超链接！|请输入正确的超链接！" class="input1" style="width:90%" maxlength="255" />
			</div>
		</div>
		<div style="clear:both;"></div>
		<font class="red">小图文图片建议像素为200*200</font>
		<table id="moreTable" align="center" style="width:100%;">
			<tr class="tr_head">
				<th>排序</th>
				<th>标题</th>
				<th colspan="2">图片</th>
				<th>超链接</th>
				<th>
					<input type="button" id="addonelineBtn" name="addonelineBtn" onclick="addoneline(this);" value="增加" />
					<input type="hidden" id="newsCount" name="newsCount" value='${newsCount}' />
				</th>
			</tr>
			<c:forEach var="f" items="${newsList}" varStatus="status" >
			<tr class='tr_${status.index%2}' id="tr${status.index}" align="center" style="${newsCount>status.index?'':'display:none'}">
				<td width="10%">
					<input type="text" id="sort${status.index}" name="" value="${f.sort}" class="input1"  dataType="Require|Integer" msg="请输入排序！|请输入正确的排序！" style="width:30px;" />
				</td>
				<td width="30%">
					<input type="text" id="title${status.index}" name="" value="${f.title}" class="input1"  dataType="Require" msg="请输入标题！" style="width:90%;height:50px" maxlength="64" />
				</td>
				<td width="10%">
					<input type="hidden" id="picurl${status.index}" name="" value='${f.picurl}' />
					<img id="mainImg_${status.index}" src="${consoleWxImagePath}${f.picurl}" style="width:50px;height:50px;padding: 0px;margin: 0px;" />
				</td>
				<td width="10%" align="left">
					<input  type="file" name="files" id="btnFile${status.index}"  onchange="foruploadMore('btnFile${status.index}','picurl${status.index}','mainImg_${status.index}')"  style="width:85px;" />
				</td>
				<td>
					<input type="text" id="url${status.index}" name="" value="${f.url}" class="input1"  dataType="Require|Url" msg="请输入超链接！|请输入正确的超链接！" style="width:90%;height:50px" maxlength="255" />
				</td>
				<td>
					<input type="button" onclick="deloneline(this);" style="${status.index>1?'':'display:none'}" value="删除" />
				</td>
			</tr>
			</c:forEach>
		</table>
			
		<div class="savebutton">
			<input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" />
		</div>
	</form>
</div>
	<form  id="myfrom1"   name="myfrom1" method="post"  style="position:absolute;top:-1200px;left:-1200px;">
	</form>
</body>
<script type="text/javascript">
	//表格增加一行： 
	function addoneline(thisObj){
		var trs=$(thisObj).parent("th").parent("tr").siblings("tr");
		var result=true;
		for(var i=0;i < trs.size();i++){
			if($(trs[i]).css("display")=='none'){
				$(trs[i]).css("display","");
				result=false;
				break;
			}
		}
		 if(result){
			 alert("最多只支持九条小图文信息");
		 }
	}	
		
	//表格删除一行： 
	function deloneline(thisObj){
		var thistr=$(thisObj).parent("td").parent("tr");
		var nexttr=thistr.next();
		var thischildren;
		var nextchildren;
		while(nexttr[0]!=null){
			thischildren=thistr.children("td");
			nextchildren=nexttr.children("td");
				$(thischildren[0]).find("input").val($(nextchildren[0]).find("input").val());
				$(thischildren[1]).find("textarea").val($(nextchildren[1]).find("textarea").val());
				$(thischildren[2]).find("input").val($(nextchildren[2]).find("input").val());
				$(thischildren[2]).find("img").attr("src",$(nextchildren[2]).find("img").attr("src"));
				$(thischildren[4]).find("textarea").val($(nextchildren[1]).find("textarea").val());
				thistr=nexttr;
				nexttr=thistr.next();
		}
		
		thischildren=thistr.children("td");
		$(thischildren[0]).find("input").val('');
		$(thischildren[1]).find("textarea").val('');
		$(thischildren[2]).find("input").val('');
		$(thischildren[2]).find("img").attr("src",'');
		$(thischildren[4]).find("textarea").val('');
		while(thistr.css("display")=="none"){
			thistr=thistr.prev("tr");
		}
		thistr.css("display","none");
	}
		
	function forupload(form,picurlid,imageid){
		//验证
		if (Validator.Validate(form,4)==false) return;
		
		//提交
		var _load = layer.load('处理中..');
		$("#"+form).ajaxSubmit({
			url : '${path}/maintain/xw/wxpushone/uploadpics.html',
			type : 'post',
			dataType :"json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
						$('#'+picurlid).val(result.message);
						$('#'+imageid).attr("src", '${consoleWxImagePath}' + result.message);
				}else{
					layer.alert(result.message);
				}
			},
			error : function(result) {
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function foruploadMore(btnFileid,picurlid,imageid){
		
		if (!Validator.Require.test($('#'+btnFileid).val())) {
			layer.alert("请选择图片！");
			return;
		}
		
		$('#myfrom1').html("");
		
		var oldElement = $('#' + btnFileid);
		var newElement = $(oldElement).clone();
		$(oldElement).attr('id', 'btnFileMore');
		$(oldElement).before(newElement);
		$(oldElement).appendTo($('#myfrom1'));
		
		forupload('myfrom1',picurlid,imageid);

	}

	function save() {
		
		for(var i=0;i < 9;i++){
			if ($("#tr"+i).css("display")=="none") {
				$("#sort"+i).attr("name","");
				$("#title"+i).attr("name","");
				$("#picurl"+i).attr("name","");
				$("#url"+i).attr("name","");
				
				$("#sort"+i).attr("dataType","");
				$("#title"+i).attr("dataType","");
				$("#url"+i).attr("dataType","");
				
				$("#sort"+i).attr("msg","");
				$("#title"+i).attr("msg","");
				$("#url"+i).attr("msg","");
			} else {
				$("#sort"+i).attr("name","newsList[" + i + "].sort");
				$("#title"+i).attr("name","newsList[" + i + "].title");
				$("#picurl"+i).attr("name","newsList[" + i + "].picurl");
				$("#url"+i).attr("name","newsList[" + i + "].url");
				
				$("#sort"+i).attr("dataType","Require|Integer");
				$("#title"+i).attr("dataType","Require");
				$("#url"+i).attr("dataType","Require|Url");
				
				$("#sort"+i).attr("msg","请输入排序！|请输入正确的排序！");
				$("#title"+i).attr("msg","请输入标题！");
				$("#url"+i).attr("msg","请输入超链接！|请输入正确的超链接！");
			}
		}
		
		//验证
		if (Validator.Validate("addOneNewsForm",4)==false) return;
		
		var _load = layer.load('处理中..');
		$("#addOneNewsForm").ajaxSubmit({
			url : '${path}/maintain/xw/wxpushmore/save.html',
			type : 'post',
			dataType :"json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						window.parent.location.href = window.parent.location.href;
					});
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}

</script>
</html>