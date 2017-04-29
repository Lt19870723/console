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
		    <br/>
			项目登记ID：<input id="projectName" class="input1" name="projectName" />
			文件名：<input id="fileName" class="input1" name="fileName" />
			<input type="button"
				   onclick="javascript:reconSubmit();" type="button" name="Submit1"
				   id="subbtn" class="b_buts" value="对账测试" />
			<br/>
				  
			借款编号：<input id="contractNo" class="input1" name="contractNo" />	
			标类型 ：
				  <select id="borrowtype" name="borrowtype"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">信用标</option>
	                    <option value="2">抵押标</option>
	                    <option value="3">净值标</option>
	                    <option value="5">担保标</option>
				  </select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			     <input type="button"
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
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/borrow/manage/repayCollection/list/' + pageNo + '.html',
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


	function showFailCollection(repaymentId,pageNo){
		var _url = '${path}/borrow/manage/repayCollection/fail/'+repaymentId+'/'+pageNo+'.html';
		$.layer({
			type : 2,
			title : '明细',
			area : [ '700px', '500px' ],
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
	
	/**
	*查询功能
	*/
	function query(){
		pageGo(1);
	}



	function reconSubmit() {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/borrow/manage/repayCollection/recon.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#loginLogList").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				parentLayer.close(_load);
			}

		});
	}
</script>
</html>
