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
			标名称：<input id="name" class="input1" name="name" />
			&nbsp;
			用户名：<input id="userName" class="input1" name="userName" />
			&nbsp;
			标类型 ：
				  <select id="borrowtype" name="borrowtype"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">信用标</option>
	                    <option value="2">抵押标</option>
	                    <option value="3">净值标</option>
	                    <option value="5">担保标</option>
				  </select>
			  还款状态 ：
			  <select id="repaymentStatus" name="repaymentStatus"
				class="bigselect" style="width:130px;">
			  		<option value="">--请选择--</option>
			  		<option value="1">还款中</option>
                    <option value="2">已垫付</option>
			  </select>
				  <br/>
			应还日期：
				<input
					name="repaymentTimeBeginStr" id="repaymentTimeBeginStr" onclick="WdatePicker()"
					styleClass="Wdate" >
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
					 &nbsp;至&nbsp; 
				<input name="repaymentTimeEndStr" id="repaymentTimeEndStr"
					onclick="WdatePicker()" styleClass="Wdate">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			&nbsp;
			借款编号：<input id="contractNo" class="input1" name="contractNo" />	

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			url : '${path}/manage/forRepayingCGBorrow/list/' + pageNo + '.html',
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
		if(confirm("确定要垫付吗？")){
			return true;
		}else{
			return false;
		}
		
	}
	function webpay(id){
	 var _load;	
     if(!validateTenderBorrow()){
    	return false;
      }		
     _load = parentLayer.load('处理中..');
	 $.ajax({
		url : '${path}/manage/forRepayingCGBorrow/webpay.html',
		data : {
			'id' :id
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.code == '0') {
					parentLayer.close(_load);
					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					//parent.layer.close(index); //执行关闭
					layer.msg(result.message,1,1);
					location.href=location.href;
			} else {
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

	//根据条件导出Excel
	function exportToExcel(repaymentId) {
		var _url = "${path}/manage/forRepayingCGBorrow/exportWebpayRecord/"+repaymentId+".html";
		window.location.href = _url;
	}


	function webpayConfirm(repaymentId) {

		if(!confirm("确认银行垫付已执行？")){
			return false;
		}

		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/manage/forRepayingCGBorrow/webpayConfirm/' + repaymentId + '.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '1') {
					layer.msg(result.message, 1, 1, function() {
						parentLayer.close(_load);
						pageGo(1);
					});
				} else {
					parentLayer.close(_load);
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}

		});
	}
	 
	  
</script>
</html>
