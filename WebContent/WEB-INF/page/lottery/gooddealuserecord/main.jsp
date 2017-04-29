<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—借款终审</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" >
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			用户名：<input name="userName" id="userName" class="input1"   />
		 
		          状态 ：
				  <select id="status" name="status"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="0">未领取</option>
	                    <option value="1">已领取</option>
	                    <option value="2">待处理</option>
	                    <option value="3">派发中</option>
	                    <option value="4">已过期</option>
				  </select>	     	  
			  	&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
		</div>
		 
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">
var _load;
$(function() {
	pageGo(1);
});

/**
*翻页功能
*/
function pageGo(pageNo) {
	  _load = parentLayer.load('处理中..');
	$("#queryForm").ajaxSubmit ({
		url : '${path}/lottery/gooddealuserecord/list/' + pageNo + '.html',
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
function accept(id,name){
     
	var title="物品发放受理";
	var _url = '${path}/lottery/gooddealuserecord/toAccept.html?id='+id+'&name='+name
	$.layer({
		type : 2,
		title : title,
		area : [ '30%', '50%' ],
		offset : [ '200px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			 
	    }
	});
}
function  sureOpeara(id){
	if(!window.confirm("确定此操作?")){
		return false;
	} 
	$.ajax({
		url : '${path}/lottery/gooddealuserecord/updateUseRecordStatus.html',
		data : {
			'id' :id
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.code == '0') {
					parentLayer.close(_load);
					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					parent.layer.close(index); //执行关闭
					layer.msg(result.message,1,1);
					query();
			} else  {
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
	  
</script>
</html>
