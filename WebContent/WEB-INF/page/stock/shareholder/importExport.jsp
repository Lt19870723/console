<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易-国诚金融后台管理系统</title>
<style type="text/css">
</style> 
</head>
 <body>
 <div style="margin-top: 90px;">
		<form action="" id="subForm"  enctype ="multipart/form-data">
			<table>
				<tr>
					<td>选择文件：</td>
					<td><input value="选择文件" type="file" name="fileName" accept="xls" id="importFile"/></td>
					<td><input value="导入" id="btnSaveBank" type="button" onclick="subBut()"/></td>
				</tr>
				
			</table>
		</form>
	</div>
 </body>
 <script type="text/javascript">
	subBut = function(){
		 var FileListType="xls";
		 var sourceStr =  $("#importFile").val();
		 if(sourceStr == null || sourceStr == ''){
			 layer.msg("请选择导入文件！", 2, 5);
		 	 return false;
		 }
		 var destStr = sourceStr.substring(sourceStr.lastIndexOf(".")+1,sourceStr.length);
		 if(FileListType.indexOf(destStr) == -1){
			 layer.msg("只允许导入xls文件", 2, 5);
		 	 return false;
		 }
		 $("#btnSaveBank").removeAttr("onclick");
		 $('#btnSaveBank').attr('disabled',"true");//添加disabled属性 
		$("#subForm").ajaxSubmit({
			url : '${path}/shareholder/importExport.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$('#btnSaveBank').removeAttr("disabled"); //移除disabled属性 
				$("#btnSaveBank").attr("onclick","subBut()");
				
				var json = eval( "(" + result + ")" );
				if("00000" == json.code){
					layer.msg('导入成功！ ', 2, 1, function() {
						parent.pageGo(1);
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		                parent.layer.close(index);
					});
				}else{
					layer.msg("导入失败！"+json.message , 2, 5);
				}
			},
			error : function(data) {
				layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
			}
		});
	}
 </script>
</html>