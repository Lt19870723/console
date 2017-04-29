<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>元宝商品-审核</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm"  action="" method="post" style="width:100%">
		<div id="loginLogCard">
			<div style="margin-left: 20px; line-height: 50px;">
			    
				商品名称：<input id="name" class="input1" name="name" style="width:150px" /> &nbsp; 
				商品状态：<select name="showFlag" id="type" style="width:100px" class="bigselect">
							<option value="">不限</option>
							<option value="1">上架</option>
							<option value="2">下架</option>
						</select> &nbsp; 
			      一级分类：<select name="firstClass" id="type" style="width:100px" class="bigselect">
				<option value="">不限</option>
				<option value="1">线上</option>
				<option value="2">线下</option>
			   </select>
					
				&nbsp; <input type="button" onclick="pageGo(1);" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
				 
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
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/syceeGoods/list/'+pageNo+'.html?approveStatus=0&opt=${opt}',
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
	 
	//审核处理
	function handle(id){
		var _url = '${path}/account/syceeGoods/layer.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '审核',
			area : [ '50%', '75%' ],
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
	 
</script>
</html>
