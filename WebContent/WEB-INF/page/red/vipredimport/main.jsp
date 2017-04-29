<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—红包管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post" style="width:  100%;">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
		     操作人：<input id="optName" class="input1" name="optName"/>&nbsp;&nbsp;
		     备注：<input id="remark" class="input1" name="remark"/>&nbsp;&nbsp;
			<input type="button" onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			 <input type="button" onclick="javascript:showAuditLayer();" type="button" name="Submit1"
				id="subbtn" class="b_buts" value="新增单个" />
			<input type="button" onclick="javascript:importRed();" type="button" name="Submit1"
				id="subbtn" class="b_buts" value="批量导入" />
			</div>
		</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">

	$(function() {
		pageGo(1);
	});
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#loginLogForm").ajaxSubmit ({
			url : '${path}/vipredImport/list/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#loginLogList").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	/**
	*查询功能
	*/
	function query(){
		pageGo(1);
	}
	function showAuditLayer(){
		var _url = '${path}/vipredImport/layer'+'.html';
		$.layer({
			type : 2,
			title : '单个新增',
			area : [ '500px', '300px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	function operate(id){
		var _load;
		if(null==id){
			alert("id不能为空");
			return false;
		}
		layer.confirm("确定发送红包吗?",function(){
		$.ajax({
			
			url : '${path}/vipredImport/grant.html',
			data : {
				'id' : id 
			},
			type : 'post',
			dataType : 'json',
			beforeSend:function(){
				 _load = parentLayer.load('处理中..');
			}, 
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '0') {
					layer.msg('发送成功', 1, 1, function() {
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						parent.layer.close(index); //执行关闭
						layer.msg(result.message,1,1);
						pageGo(1);
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
		});});
	}
	function importRed(){
		var _url = '${path}/vipredImport/import'+'.html';
		$.layer({
			type : 2,
			title : '上传导入文件',
			area : [ '60%', '90%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
			
		});
	} 
	function detail(id) {
		var _url = '${path}/vipredImport/viewmain.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '红包奖励记录',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
function deleteImport(id){
		
		if(null==id){
			alert("id不能为空");
			return false;
		}
		layer.confirm("确定删除吗?",function(){
		$.ajax({
			
			url : '${path}/vipredImport/deleteImport.html',
			data : {
				'id' : id 
			},
			type : 'post',
			async:false,
			dataType : 'json',
			beforeSend:function(){
				 _load = parentLayer.load('处理中..');
			}, 
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '0') {
					layer.msg('删除成功', 1, 1, function() {
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						parent.layer.close(index); //执行关闭
						layer.msg(result.message,1,1);
						pageGo(1);
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
		});});
	}
</script>
</html>
