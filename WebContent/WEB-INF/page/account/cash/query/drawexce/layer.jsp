<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center" border="1" bordercolor="black" style="width: 95%">
			 <tr>
			        <td height="40px;" width="50%"><div align="center">当前受限金额</div></td>
			        <td width="50%"><div align="center">${map.noDrawMoney}</div></td>
			    </tr>
			    <tr>
			        <td height="40px;"><div align="center">可转提金额</div></td>
			        <td><div align="center">${map.toDraw}</div></td>
			    </tr>
			  	
		</table>
		<div align="center">
		
		<input id="userId" name="userId" value="${userId}" type="hidden"/>
		<input id="noDrawMoney" name="noDrawMoney" value="${map.noDrawMoney}" type="hidden"/>
		<input id="toDrawMoney" name="toDrawMoney" value="${map.toDraw}" type="hidden"/>
		
		 <input type="button"
					onclick="javascript:validateToDraw('${userId}')" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="确定" />
		</div>
	</form>
</body>

<script type="text/javascript">

	function validateToDraw(id) {
		var noDraw= $('#noDrawMoney').val();
		var toDraw = $('#toDrawMoney').val();
		
		if(toDraw == 0){
			alert("当前受限金额无法转可提！");
			return false;
		}
		
		if(noDraw != toDraw){
			if(!confirm("当前受限金额与可转提金额不相等，确认要转可提吗？")){
				return false;
			}
		}else{
			if(!confirm("确认要转可提吗？")){
				return false;
			}
		}
	
		var urlPath = '${path}/account/cashdrawexcecount/accountToDraw/' + id + '.html';
		$("#approForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				alert(result.message);
				window.parent.location.href = window.parent.location.href;
			},
			error : function(data) {
				alert(result.message);
			}
		});
	}

</script>

</html>
