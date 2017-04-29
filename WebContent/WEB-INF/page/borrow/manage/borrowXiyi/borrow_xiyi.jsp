<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
 <style type="text/css">
html { overflow-y:hidden;}
 </style>
</head>
<body style="background: #f9f9f9;">
	<form id="borrowXiyiform" action="" method="post">
	<div style="margin-left:10px;margin-top:5px;">
	<table style="margin-left:10px;margin-top:5px;">
	   &nbsp;&nbsp;
	   <input type="button"
					onclick="javascript:changeRecordType(0);" type="button" name="Submit1"
					id="subbtn"  value="下载借款协议" />
	   &nbsp;&nbsp;&nbsp;
	   <input type="button"
					onclick="javascript:changeRecordType(1);" type="button" name="Submit1"
					id="subbtn" value="下载最新债权人借款协议[财务部使用]" />				
	   
	</table>
	</div>
	<div>
	    ${xiyiContent}
	</div>
	<input type="hidden" id="id" name="id" value="${borrowId}"/>
	<input type="hidden" id="recordType" name="recordType" value="${recordType}"/>
	</form>
</body>
<script type="text/javascript">
  $(function(){
	   var xiyiContent = $('#xiyiContent').val();
		$("#xiyiDiv").html(xiyiContent);
		//设置图片样式，否则图片不出来
		$('[id="borrowXiyiform:borrowXiyiPopup_content_scroller"]').attr("style","width: 1000px; height: 473px;position:absolute;right:10%;z-index:999;top:5%");
  });
  /**
   * 更换协议类型数据
   */
  function changeRecordType (recordType){
	  var id=$("#id").val();
	  $("#borrowXiyiform").attr("action","${path}/borrow/manage/borrowXiyi/exprtBorrowXiYiDoc.html?borrowId="+id+"&recordType="+recordType);
      $("#borrowXiyiform").submit();
  }
  
</script>
</html>
