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
			IP：<input  name="ip" id="ip" name="name" />
			&nbsp;
			公司名：<input name="company" id="company" class="input1"   /> 					 
		           访问接口类型：
				  <select  name="accessType" id="accessType"
					class="bigselect" style="width:130px;">
					    <option value="">请选择</option>
				  		<c:forEach items="${thirdPlatformList }" var="vo">
				  		<option value="${vo.value }">${vo.label }</option>
				  		</c:forEach> 
				  </select>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			<input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
		    <input type="button"
					onclick="javascript:add();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="新 增" />
			<br />  
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
		$("#queryForm").ajaxSubmit ({
			url : '${path}/system/ipWhile/list/' + pageNo + '.html',
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
	/**
	  添加
	*/
	function add(){
		var _url = '${path}/system/ipWhile/add.html';
		
		$.layer({
			type : 2,
			title : '新增IP白名单',
			area : [ '50%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
	} 
	/**
	  修改
	*/
	function updateIP(id){
	    
		var _url = '${path}/system/ipWhile/initUpdateIPWhile.html';
		if (id != '') {
			_url += '?id=' + id;
		}
	
		$.layer({
			type : 2,
			title : '修改IP白名单',
			area : [ '50%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
	} 
	 
	var _load;

	function deleteIP(id){
		
		if(!confirm("确认要删除吗？")){
			return false;
		} 
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/system/ipWhile/delIPWhile.html',
			data : {
				'id' :id
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						
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
		}); 
	}  
</script>
</html>
