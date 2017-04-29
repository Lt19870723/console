<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—用户分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%;">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			&nbsp;
			用户名：<input id="username" name="username" class="input1" style="width:150px;"/>
			&nbsp;
			交易日期：<input
					name="beginTimeStr" id="beginTimeStr" onclick="WdatePicker()"
					styleClass="Wdate" >
				</input>
			至
			<input
					name="endTimeStr" id="endTimeStr" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			交易类型:
			<select
				id="type" name="type"
				class="bigselect" style="width:130px;">
				<option value="">--请选择--</option>
				<c:forEach var="vo" items="${list}">
					<option value="${vo.name }">${vo.value }</option>
				</c:forEach>
			</select>
			<br/>
			注册日期：<input
					name="registTimeStart" id="registTimeStart" onclick="WdatePicker()"
					styleClass="Wdate" >
				</input>
			至
			<input
					name="registTimeEnd" id="registTimeEnd" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
		     <input type="button" onclick="exportCallBackData();" name="Submit1" 
		     id="subbtn" class="b_buts" value="导出  Excel" />
			</div>
	</form>

	<div id="list" class="main_cent"></div>

</body>
<script type="text/javascript">

	$(function() {
		  var myDate = new Date();  
		  var strYear = myDate.getFullYear();    
		  var strDay =myDate.getDate()>9?myDate.getDate().toString():'0' + myDate.getDate(); 
		  var strMonth = myDate.getMonth()+1;
		  strMonth=strMonth>9?strMonth.toString():'0' + strMonth;
		  var currentDate=strYear+"-"+strMonth+"-"+strDay;
		  $('#beginTimeStr').val(getPreMonth(currentDate));
		  $('#endTimeStr').val(currentDate);
		  
	});
	/**
     * 获取上一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */
    function getPreMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var day = arr[2]; //获取当前日期的日
        var days = new Date(year, month, 0);
        days = days.getDate(); //获取当前日期中月的天数
        var year2 = year;
        var month2 = parseInt(month) - 1;
        if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
        var day2 = day;
        var days2 = new Date(year2, month2, 0);
        days2 = days2.getDate();
        if (day2 > days2) {
            day2 = days2;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        var t2 = year2 + '-' + month2 + '-' + day2;
        return t2;
    }
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load;
		var urlPath = '${path}/customer/accoutlogtrading/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
		    beforeSend:function(){
		    	_load = parentLayer.load('处理中..');
		    },
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
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
	function exportCallBackData(){
		 
		validateExportDataCount(); 
	}
	/**
	 *校验数据条数是否符合要求
	 */
	function validateExportDataCount(){
		var _load ;
		$("#queryForm").ajaxSubmit ({
			url : '${path}/customer/accoutlogtrading/count.html',
			type : 'post' ,
			dataType : 'json',
			async:false,
			beforeSend:function(){
		    	_load = parentLayer.load('处理中..');
		    },
			success : function(result) {
				parentLayer.close(_load);
				if(result.code == '0'){
					//数据导出
					$("#queryForm").attr("action","${path}/customer/accoutlogtrading/exportToExcel.html");
			        $("#queryForm").submit();
				}else{
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
				parentLayer.close(_load);
			} 
		});
	}
	
</script>
</html>
