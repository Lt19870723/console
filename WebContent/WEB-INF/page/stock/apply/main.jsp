<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易系统-申请审核</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
	<form id="applyform" action="" method="post">
	<input type="hidden" name="type" value="${type}"/>
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				状态：<select name="status">
					<option value="0">全部</option>
					<option selected="selected" value="1">待审核</option>
					<option value="2">审核通过</option>
					<option value="3">审核不通过</option>
					<option value="-1">已作废</option>
				</select>
				申请时间：<input style="width: 130px;"  name="beginTime" id="beginTime" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" styleClass="Wdate"  readonly="readonly"/>
				~ <input style="width: 130px;"  name="endinTime" id="endinTime" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" styleClass="Wdate"  readonly="readonly"/>
				<input value="查询" type="button" onclick="pageGo(1);"/>
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
				url : '${path}/apply/applyList/' + pageNo + '.html',
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
	
	function approve(id,userId){
			var _url='${path}/apply/toApprove/'+id+'/'+userId+'.html'
			 $.layer({
				type : 2,
				title : '审核',
				area : [ '450px', '410px' ],
				offset : [ '10px', '' ],
				shade : [ 0.1, '#000' ],
				maxmin : true,
				iframe : {
					src : _url
				}
			});
		}
	
	function detail(id,targetType){
		var _url='${path}/apply/toDetail/'+id+'/'+targetType+'.html';
		 $.layer({
			type : 2,
			title : '详情',
			area : [ '600px', '400px' ],
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