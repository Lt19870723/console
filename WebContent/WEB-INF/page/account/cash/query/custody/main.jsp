<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post"  >
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			提现时间：
				<input
					name="beginTime" id="appBeginTime" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			至
				<input
					name="endTime" id="endTime" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			用户名：<input id="username" name="username" class="input1"/>
			&nbsp;	
			<br/>
			&nbsp;
			状态 ：
			 <select name="status"
				id="status"
				class="bigselect" style="width:130px;">
				<option value="">--请选择--</option>
				<option value="0">申请提现</option>
                <option value="10">处理中</option>
                <option value="40">可疑</option>
			 </select>
			 &nbsp;	
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			 &nbsp;	
			<br/>
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
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/account/custodycashrecords/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
                parentLayer.close(_load);
				$("#list").html(result);
			},
			error : function(data) {
				parentLayer.close(_load);
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

    function toLoseOrder(id) {
        if (confirm('确定要进行补单？')) {
            var _load = parentLayer.load('处理中..');
            $.ajax({
                type: "get",
                async: false,
                url: '${portalPath}/account/czbank/loseOrder.html?id=' + id + '&type=2',
                dataType: "jsonp",
                jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
                jsonpCallback: "callbackHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
                success: function (data) {
                    parentLayer.close(_load);
                    if (data.code == '0') {
                        layer.msg(data.message, 1, 5);
                    } else {
                        layer.msg(data.message, 1, 1, function () {
                            query();
                        });
                    }
                },
                error: function () {
                    layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
                }
            });
        }
    }
	
</script>
</html>
