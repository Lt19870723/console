<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>元宝商城-商品列表</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action=""  method="post" style="width:100%">
		<div style="margin-left: 20px; line-height: 35px; width: 100%">
			<b title="非折扣时间段内，商品将按原价元宝进行兑换">限时折扣时间：</b>
			${d.beginDate }(0点)~${d.endDate }(0点)
			<input type="button" onclick="show(0,'setTenderBidAll')" class="b_buts" value="设 置" /> 
		</div>
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
				</select> &nbsp; 
				审核状态：
				<select name="approveStatus" id="type" style="width:100px"  class="bigselect">
					<option value="">不限</option>
					<option value="0">待审核</option>
					<option value="1">审核通过</option>
					<option value="2">审核不通过</option> 
				</select>
				&nbsp; <input type="button" onclick="pageGo(1);" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
				&nbsp; <input type="button" onclick="add(0);" name="Submit1" id="subbtn" class="b_buts" value="新  增" />
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
			url : '${path}/account/syceeGoods/list/'+pageNo+'.html?opt=${opt}',
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
	//增加页面
	function add(id){
		var _url = '${path}/account/syceeGoods/add.html?id=' + id;
		var _title='新增';
		if(id>0)_title='编辑';
		
		$.layer({
			type : 2,
			title : '元宝商品-'+_title,
			area : [ '80%', '95%' ],
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
	
	//审核处理
	function handle(id){
		var _url = '${path}/account/syceeGoods/layer.html';
		if (id != '') {
			_url += '?id=' + id;
		}
		$.layer({
			type : 2,
			title : '审核',
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
	function grounding(id,showFlag){
		var show;
		if(showFlag==2){
			show="上架吗？";
		}else{
			show="下架吗？";
		}
	    layer.confirm("确认"+show,function(){
	    	$.ajax({
				url : '${path}/account/syceeGoods/grounding.html?id='+id,
				type : 'post',
				async:false,
				dataType : 'json',
				  
				success : function(result) {
					 
					if (result.code == '1') {			
						 location.href=location.href;
					}else  {
						layer.msg(result.message, 1, 5);
					}
				},
				error : function(result) {
					layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			    }
			});
	    });
		
	}
	
	function show(id, opt) {
		var _title = '元宝商城';
		var _width = '70%';
		var _height = '70%';
		opt = opt || '';
		if (opt == 'setTenderBid' || opt == 'setTenderBidAll') {
			_title = '设置限时折扣时间';
		}
		var _url = '${path}/account/syceeGoods/discountDate.html';
		$.layer({
			type : 2,
			title : _title,
			area : [ _width, _height ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
</script>
</html>
