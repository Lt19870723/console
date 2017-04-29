<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚官网--红包管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post" enctype="multipart/form-data"  onsubmit="return check();">
		<table width="100%"  class="solidTable" style="width:100%;">
			<tr>
				<td align="right"><font color="red">*</font>excel：</td>
				<td align="left">
					<input id="excel_file" type="file" name="filename"  accept="xls" align="right"/>
					<input type="hidden" id="importMsg" value="${msg}" align="right"/>  
				</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>红包类型：</td>
				<td align="left">
					<select name="redType" id="redType">
					<c:forEach items="${redtypes }" var="t">
						<option value="${t.name }">${t.value }</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">备注：</td>
		    	<td align="left">
		    		<input type="text" name="info" id="info" size="20px"/>
				</td>
			</tr>
			<tr>
			    <td align="center" colspan="2">
			     <input type="button"  onclick="javascript:uploadExcel()" type="button" name="Submit1" id="subbtn" class="b_buts" value="提交" />
			    </td>
			</tr>
		</table>
		<div style="padding-left: 10px;">
			<strong>上传说明：</strong><br />
			1.只支持xls文件(2003版本)格式，如果是xlsx格式(2007版本)请先另存为xls文件再上传<br/>
			2.上传文件最大5M，必须为EXCEL格式<br/>
			<p>
			<strong>EXCEL填写说明：</strong><br/>
			1.红包类型：必选<br/>
			2.红包金额：<br/>
				贵宾特权红包：50<br/>
				新人活动红包：20、30、50<br/>
				活动红包：10、20、30、50、100、200<br/>
			3.备注：超过190个字系统会自动截取<br/>
			</p>
		</div>
		<table class="fulltable" style="width:100%;">
			<tr><th colspan="2">模板下载</th></tr>
			<tr><td>红包导入</td><td><a href="${path}/report/redImport.xls">下载</a></td></tr>
		</table>
	</form>
	<form  id="myfrom"   name="myfrom" method="post"  style="position:absolute;top:-1200px;left:-1200px;">
	</form>
</body>
<script type="text/javascript">
		function uploadExcel(){
			   $("#approForm").attr("action","${path}/vipredImport/importRed.html");
			   $("#approForm").submit();
		}
	     function check() {  
	      var excel_file = $("#excel_file").val();  
	      if (excel_file == "" || excel_file.length == 0) {  
	          alert("请选择文件路径！");  
	          return false;  
	      } else {  
	         return true;  
	     }  
	 } 
     $(document).ready(function () {  
           var msg="";  
           if($("#importMsg").val()!=null){  
               msg=$("#importMsg").val();  
           }  
           if(msg!=""){  
               alert(msg);  
               parent.window.location.href=parent.window.location.href;
			   var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
			   parent.layer.close(index); //执行关闭
           }  
         });  
	</script>
</html>
