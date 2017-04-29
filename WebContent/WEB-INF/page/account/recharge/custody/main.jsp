<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/page/common/taglib.jsp" %>
<%@ include file="/WEB-INF/page/common/public.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>国诚金融—充值管理</title>
</head>
<body style="background: #f9f9f9;">
<form id="queryForm" action="" method="post" style="width: 100%;">
    <div id="loginLogCard">
        <div>
            <div style="margin-left:20px;line-height:50px;">
                充值订单号：<input id="tradeNo" class="input1" name="tradeNo"
                             value="${rechargeRecordCnd.tradeNo}"/>
                &nbsp;
                充值日期：
                <input
                        name="beginTime" id="beginTime" onclick="WdatePicker()"
                        styleClass="Wdate" value="${rechargeRecordCnd.beginTime }">
                <f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
                </input>
                &nbsp;~&nbsp;
                <input name="endTime" id="endTime"
                       onclick="WdatePicker()" styleClass="Wdate"
                       value="${rechargeRecordCnd.endTime }">
                <f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
                </input>
                <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                用户名：<input id="username" class="input1" name="username"
                           value="${rechargeRecordCnd.username}"/>
                &nbsp;
                充值类型 ：
                <select id="type" name="type" value="${rechargeRecordCnd.type }"
                        class="bigselect" style="width: 130px;">
                    <option value="">--请选择--</option>
                    <option value="1">在线充值</option>
                    <option value="2">线下转账</option>

                </select>
                &nbsp;
                充值状态 ：
                <select id="status" name="status"
                        value="${rechargeRecordCnd.status}"
                        class="bigselect" style="width:130px;">
                    <option value="">--请选择--</option>
                    <option value="2">在线充值待付款</option>
                    <option value="10">处理中</option>
                    <option value="40">可疑</option>
                </select>
                &nbsp;
                <!--
                &nbsp;
                用户类型 ：
                <select id="userType" name="userType"
                        value="${rechargeRecordCnd.userType}"
                        class="bigselect" style="width:130px;">
                    <option value="">--请选择--</option>
                    <option value="1">理财用户</option>
                    <option value="0">借款用户</option>
                </select>-->
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
                                                      onclick="javascript:query();" type="button" name="Submit1"
                                                      id="subbtn" class="b_buts" value="查   询"/>
            </div>
        </div>
    </div>
</form>

<div id="loginLogList" class="main_cent"></div>

</body>
<script type="text/javascript">

    $(function () {
        pageGo(1);
    });

    /**
     *翻页功能
     */
    function pageGo(pageNo) {
        var _load = parentLayer.load('处理中..');
        $("#queryForm").ajaxSubmit({
            url: '${path}/account/custodyrechargerecords/list/' + pageNo + '.html',
            type: 'post',
            dataType: 'html',
            success: function (result) {
                parentLayer.close(_load);
                $("#loginLogList").html(result);
            },
            error: function (data) {
                layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
            }
        });
    }

    /**
     *查询功能
     */
    function query() {
        pageGo(1);
    }

    function toLoseOrder(id) {
        if (confirm('确定要进行补单？')) {
            var _load = parentLayer.load('处理中..');
            $.ajax({
                type: "get",
                async: false,
                url: '${portalPath}/account/czbank/loseOrder.html?id=' + id + '&type=1',
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
