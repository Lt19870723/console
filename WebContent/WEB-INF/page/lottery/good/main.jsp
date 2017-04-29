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
			<input type="button"
					onclick="javascript:add(0);" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="新增奖品" />
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
			url : '${path}/lottery/good/list/' + pageNo + '.html',
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
	*删除功能
	*/
   function deleteAward(id){
		
		if(!confirm("确认要删除吗？")){
			return false;
		} 
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/lottery/good/delGood.html',
			data : {
				'id' :id
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
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
   
   function add(id){
	    clearaddData();
		var title="添加奖品";
		var _url = '${path}/lottery/good/add.html?id='+id
		$.layer({
			type : 2,
			title : title,
			area : [ '80%', '80%' ],
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
   function update(id){
	   
		var title="修改奖品";
		var _url = '${path}/lottery/good/add.html?id='+id
		$.layer({
			type : 2,
			title : title,
			area : [ '80%', '80%' ],
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
   function clearaddData(){
		$('#awardType').val("");
		$('#name').val("");
		$('#awardMoney').val("");
		$('#chance').val("");
		$('#chirldChanceStr').val("");
		$('#turntablePosition').val("");
		disabledInput();
	}
	function  disabledInput(){
		var awardType= $('#awardType').val();
		if (awardType=="") {
			$('#chirldChanceStr').removeAttr("disabled");
			$('#name').removeAttr("disabled");
	    	$('#awardMoney').removeAttr("disabled");
			return  ;
		}
		
		if(awardType==1||awardType==2){
			$("#ci").hide();
			$("#yuan2").show();
			if (awardType==1) {
				$("#yuan1").show();
			}else{
				$("#yuan1").hide();
			}
			$('#chirldChanceStr').attr("disabled","true");
			$('#name').removeAttr("disabled");
	    	$('#awardMoney').removeAttr("disabled");
		} 
		
		if(awardType==3){
			$("#ci").hide();
			$("#yuan2").hide();
			$("#yuan1").hide();
	    	$('#name').attr("disabled","true");
	    	$('#chirldChanceStr').attr("disabled","true");
	    	$('#awardMoney').attr("disabled","true");
		}
		
		if(awardType==4){
			$("#ci").show();
			$("#yuan2").hide();
			$("#yuan1").hide();
			$('#awardMoney').attr("disabled","true");
			$('#chirldChanceStr').removeAttr("disabled");
			$('#name').removeAttr("disabled");
		}
		
	}
</script>
</html>
