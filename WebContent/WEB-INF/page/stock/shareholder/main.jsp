<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易系统-内转花名册</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
	<form id="applyform" action="" method="post">
	<input type="hidden" name="type" value="${type}"/>
	<input type="hidden" name="status" value="1"/>
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				用户姓名：<input style="width: 130px;" type="text" name="userRealName"/>
				所属有限合伙：<select name="stockCode" style="width: 80px;">
					<option selected="selected" value="">全部</option>
					<option selected="" value="GY0001">国漾</option>
					<option value="GZ0001">国缁</option>
				</select>
				<!-- 状态：<select name="status" style="width: 80px;">
					<option selected="selected" value="1">正常</option>
					<option value="-1">作废</option>
				</select> -->
				加入日期：<input style="width: 130px;"  name="beginTime" id="beginTime" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" styleClass="Wdate"  readonly="readonly"/>
				~ <input style="width: 130px;"  name="endinTime" id="endinTime" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" styleClass="Wdate"  readonly="readonly"/>
				<input value="查询" type="button" onclick="pageGo(1);"/>
				<input value="数据导出" type="button" onclick="toExport();"/>
				<input value="数据导入" type="button" onclick="importExport();"/>
			</div>
		</div>
	</form> 
	<div id="list" class="main_cent"></div>
</body>
<script type="text/javascript">
	$(function() {
		pageGo(1);
		
	});
	/**
	 *翻页功能
	 */
		function pageGo(pageNo) {
			$("#applyform").ajaxSubmit({
				url : '${path}/shareholder/shareholderList/' + pageNo + '.html',
				type : 'post',
				dataType : 'html',
				success : function(result) {
					$("#list").html(result);
					parentLayer.close(parentLayer.load(2));
				},
				error : function(data) {
					layer.msg('网络连接异常，请刷新页面或稍后重试！', {
						icon : 2,
						time : 1000
					});
				}
			});
		}
	
	
	
		//内转花名册导出
		function toExport() {
			var _url = "${path}/shareholder/toExport.html";
			cxIndex= $.layer({
				type : 2,
				fix : false,
				shade : [ 0 ],
				title : '内转花名册导出',
				area : [ '300px', '200px' ],
				offset : [ '100px', '' ],
				shade : [ 0.1, '#000' ],
				maxmin : true,
				iframe : {
					src : _url
				}
			});

		}
	
		
		
		function queryLogInfo(id,userId) {
			var _url = "${path}/shareholder/shareholderLogList/"+id+"/"+userId+".html";
			cxIndex= $.layer({
				type : 2,
				fix : false,
				shade : [ 0 ],
				title : '内转用户名称变更记录',
				area : [ '600px', '500px' ],
				offset : [ '7px', '' ],
				shade : [ 0.1, '#000' ],
				maxmin : true,
				iframe : {
					src : _url,
					scrolling:'yes'
				}
			});

		}
		
		function importExport() {
			var _url = "${path}/shareholder/inImport.html";
			cxIndex= $.layer({
				type : 2,
				fix : false,
				shade : [ 0 ],
				title : '内转花名册导入',
				area : [ '450px', '350px' ],
				offset : [ '7px', '' ],
				shade : [ 0.1, '#000' ],
				maxmin : true,
				iframe : {
					src : _url,
					scrolling:'no'
				}
			});

		}
</script>
</html>