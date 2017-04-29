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
			定期宝编号：<input id="contractNo" class="input1" name="contractNo" />
			&nbsp;
			开放日期：
				<input
					 name="beginTime" id="beginDate"  onclick="WdatePicker()"
					styleClass="Wdate" >
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
					 &nbsp;至&nbsp; 
				<input name="endTime" id="endDate" 
					onclick="WdatePicker()" styleClass="Wdate">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			<br>			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
				onclick="javascript:query();" type="button" name="Submit1"
				id="subbtn" class="b_buts" value="查   询" />
					 					 
			 
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
			url : '${path}/fix/changeArea/list/' + pageNo + '.html',
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
	
	function validateTenderBorrow(){
		if(confirm("确认由新手专区转为普通专区吗？")){
			return true;
		}else{
			return false;
		}
		
	}
	function cancelBorrow(id){
	 var _load;	
     if(!validateTenderBorrow()){
    	return false;
      }		
     _load = layer.load('处理中..');
	 $.ajax({
		url : '${path}/fix/changeArea/changeAreaBorrow.html',
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
	
	/**
	  查看
	*/
	function show(id){
		 
		var _url = '${path}/fix/fixborrow/toAddModify.html?id='+id;
		$.layer({
			type : 2,
			title : '定期宝详情',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	}
</script>
</html>
