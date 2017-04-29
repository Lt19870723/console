<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 1400px;">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
			推荐人：<input id="userName" class="input1" name="userName"/>
			&nbsp;
			年份 ：
				 <select id="beginYear" name="beginYear"
					class="bigselect" style="width: 110px;">
					<option value="2014" selected="selected">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
				</select>
			&nbsp;
			月份 ：
				 <select id="beginTime" name="beginTime"
					class="bigselect" style="width: 130px;" >
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12" selected="selected">12</option>
				</select>
			
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">

	$(function() {
		
		/*  var date = new Date();
		 var year = date.getFullYear();
		 var month = date.getMonth() + 1;
		 $("#beginYear").find("option[value="+year+"]").attr("selected",true);
		 $("#beginTime").find("option[value="+month+"]").attr("selected",true);
		 */
		 
		pageGo(1);
	});
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/customer/recommendaward/list/' + pageNo + '.html',
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
	
	function queryInvituser(id,year){
		showInvituser(id,1,year);
	}
	
	
	
	function showInvituser(id,pageNo,year){
		var _url = '${path}/customer/recommendaward/inviteuserpagelist/'+id+'/'+year+'/'+pageNo+'.html';
		$.layer({
		type : 2,
		title : '明细',
		area : [ '600px', '500px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			//pageGo(1);
	    }
	});
	}
	
	function queryUserIndirect(id,year){
		showUserIndirectLayer(id,1,year);
	}
	
	function showUserIndirectLayer(id,pageNo,year){
	var _url = '${path}/customer/recommendaward/userIndirectlist/'+id+'/'+year+'/'+pageNo+'.html';
	$.layer({
		type : 2,
		title : '明细',
		area : [ '600px', '500px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			//pageGo(1);
	    }
	});
	}
	
</script>
</html>
