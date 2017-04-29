<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<style type="text/css">
.input1{
	width: 100px;
}

</style>
<title>提现核对-国诚金融后台管理系统</title>
</head>
<body>
	<form id="approForm" name="" method="post">
		<div class="listzent"></div>
		<table  class="fulltable" style="width: 100%;" id="accTbale">
			<thead>
		<tr >
			<td rowspan="2" colspan="11" align="center" style="font-size: 30px;" ><fmt:formatDate value="${checkWithdrawals[0].date }" pattern="yyyy-MM-dd"/>充值信息对账</td>
		</tr>
		</thead>
	<thead>
		<tr>	
			<!-- <th style="width: ;">日期</th> -->
			<th style="width: ;">充值端口</th>
			<th style="width: ;">充值总额(元)</th>
			<th style="width: ;">充值成功总额(元)</th>
			<th style="width: ;">实际充值总额(元)</th>
			<th style="width: ;">计算手续费(元)</th>
			<th style="width: ;">实际手续费(元)</th>
			<th style="width: ;">手续费差异(元)</th>
			<th style="width: ;">第三方到账总额(元)</th>
			<th style="width: ;">差异(元)</th>
			<th style="width: ;">虚拟充值总额(元)</th>
			<th style="width: 70px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vType" items="${checkWithdrawals }" varStatus="vIndex"> 
				<%-- <c:forEach var="vType" items="${vo }" varStatus="vIndex" > --%>
						<tr>
							<%-- <c:if test="${vIndex.index == 0}"><td rowspan="6" align="center"><fmt:formatDate value="${vType.date}" pattern="yyyy-MM-dd"/></td></c:if> --%>
							<td>
								<c:if test="${vType.type == 1}"><span style="color: ;">网银在线</span></c:if>
								<c:if test="${vType.type == 4}"><span style="color: ;">新浪支付</span></c:if>
								<c:if test="${vType.type == 5}"><span style="color: ;">连连支付</span></c:if>
								<c:if test="${vType.type == 6}"><span style="color: ;">富友支付</span></c:if>
								<c:if test="${vType.type == 7}"><span style="color: ;">支付宝</span></c:if>
							</td>
							<td align="right"><fmt:formatNumber value="${vType.totalMoney}" pattern="#,##0.00"/></td>
							<td align="right"><fmt:formatNumber value="${vType.successMoney}" pattern="#,##0.00"/></td>
							<td align="right">
									<input class="input1" type="text"  name="checkMoney" value="${vType.checkSuccessMoney}"/>
							</td>
							<td align="right"><fmt:formatNumber value="${vType.calculationFee}" pattern="#,##0.00"/></td>
							<td align="right">
								<c:if test="${vIndex.index == 0 || vIndex.index == 2}">
									<input class="input1" type="text" name="checkMoney" value="${vType.checkFee}"/>
								</c:if>
								<c:if test="${vIndex.index != 0 && vIndex.index != 2}">
									0.00
								</c:if>
							</td>
							<td align="right">
								<c:if test="${vType.differenceFee == null}">--</c:if>
								<c:if test="${vType.differenceFee != null}"><fmt:formatNumber pattern="#,##0.00" value="${vType.differenceFee}"/></c:if>
							</td>
							<td align="right">
								<input class="input1" type="text" name="checkMoney" value="${vType.receiveMoney}"/>
							</td>
							<td align="right">
								<c:if test="${vType.differenceTotal == null}">--</c:if>
								<c:if test="${vType.differenceTotal != null}"><fmt:formatNumber pattern="#,##0.00" value="${vType.differenceTotal}"/></c:if>
							</td>
							<td>
								<c:if test="${vIndex.index != 4}">--</c:if>
								<c:if test="${vIndex.index == 4}">
									 <input class="input1" type="text" id="fictitiousRecharge" name="checkMoney" value="${vType.fictitiousRecharge}"/>
								</c:if>
							</td>
							<c:if test="${vIndex.index == 0}">
								<td rowspan="5">
										<input type="button" value="核对" onclick="updateDifference();">
								</td>
							</c:if>
						</tr>
				<%-- </c:forEach> --%>
		</c:forEach>
		<tr >
			<td rowspan="1" colspan="11" align="center" style="font-size: 30px;" >
				<textarea cols="40" rows="4" style="width:100%;"id="remarks" name="remarks" ></textarea>
			</td>
		</tr>
	</tbody>	
</table>
	<div style="margin-left: auto;margin-right: auto;">
		<input  type="button" style="display: ;" onclick="updateCheckWithdrawals(1);" value="对账成功" />
		<input type="button" style="display: ;" onclick="updateCheckWithdrawals(2);" value="保存草稿" />
		<span id="checkDate" style="display: none;" ><fmt:formatDate value="${checkWithdrawals[0].date }" pattern="yyyy-MM-dd"/></span>
	</div>
	</form>
</body>
<script type="text/javascript">
	$("#totalExpenditure").on('keyup', function(event) {
		document.getElementById('btn').style.display='none'; 
		var $amountInput = $(this);
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		$amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
		//只允许一个小数点              
		replace(/^\./g, "").replace(/\.{2,}/g, ".").
		//只能输入小数点后两位
		replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
	});

	$("#counterFee").on('keyup', function(event) {
		document.getElementById('btn').style.display='none';  
		var $amountInput = $(this);
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		$amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
		//只允许一个小数点              
		replace(/^\./g, "").replace(/\.{2,}/g, ".").
		//只能输入小数点后两位
		replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
	});

	function updateDifference() {
		if (!check()) {
			return;
		}
		var date = $("#checkDate").text();
		var tab = $("#accTbale").find("tr");
		var tab1 = tab[2].children;
		var tab2 = tab[3].children;
		var tab3 = tab[4].children;
		var tab4 = tab[5].children;
		var tab5 = tab[6].children;
		var list = [];
		var online = {"type":1, "date" : date ,"checkFee" : tab1[5].children[0].value.replace(/,/g,''), "receiveMoney": tab1[7].children[0].value.replace(/,/g,'')};
		var sina = {"type":4, "date" : date , "checkFee" : 0, "receiveMoney": tab2[7].children[0].value.replace(/,/g,'')};
		var lianlian = {"type":5, "date" : date , "checkFee" : tab3[5].children[0].value.replace(/,/g,''), "receiveMoney": tab3[7].children[0].value.replace(/,/g,'')};
		var rich = {"type":6, "date" : date , "checkFee" : 0, "receiveMoney": tab4[7].children[0].value.replace(/,/g,'')};
		var alipay = {"type":7, "date" : date , "checkFee" : 0, "receiveMoney": $("#fictitiousRecharge").val().replace(/,/g,'')};
		list.push(online);
		list.push(sina);
		list.push(lianlian);
		list.push(rich);
		list.push(alipay);
		$.ajax({
			url : "${path}/finance/checkRecharge/checkAccount.html",
			type : 'post',
			dataType : 'json',
			data : JSON.stringify(list),
			success : function(result) {
				if (result.status == false) {
					layer.msg(result.msg, 1, 5);
				} else {
					var msg = result.msg;
						msg[0].differenceFee == 0 ? tab1[6].innerHTML='<font color="green">0.00</font>' : tab1[6].innerHTML='<font color="red">'+formatCash(msg[0].differenceFee,2)+'</font>';
						msg[0].differenceTotal == 0 ? tab1[8].innerHTML='<font color="green">0.00</font>' : tab1[8].innerHTML='<font color="red">'+formatCash(msg[0].differenceTotal,2)+'</font>';
						
						msg[1].differenceFee == 0 ? tab2[6].innerHTML='<font color="green">0.00</font>' : tab2[6].innerHTML='<font color="red">'+formatCash(msg[1].differenceFee,2)+'</font>';
						msg[1].differenceTotal == 0 ? tab2[8].innerHTML='<font color="green">0.00</font>' : tab2[8].innerHTML='<font color="red">'+formatCash(msg[1].differenceTotal,2)+'</font>';
						
						msg[2].differenceFee == 0 ? tab3[6].innerHTML='<font color="green">0.00</font>' : tab3[6].innerHTML='<font color="red">'+formatCash(msg[2].differenceFee,2)+'</font>';
						msg[2].differenceTotal == 0 ? tab3[8].innerHTML='<font color="green">0.00</font>' : tab3[8].innerHTML='<font color="red">'+formatCash(msg[2].differenceTotal,2)+'</font>';
						
						msg[3].differenceFee == 0 ? tab4[6].innerHTML='<font color="green">0.00</font>' : tab4[6].innerHTML='<font color="red">'+formatCash(msg[3].differenceFee,2)+'</font>';
						msg[3].differenceTotal == 0 ? tab4[8].innerHTML='<font color="green">0.00</font>' : tab4[8].innerHTML='<font color="red">'+formatCash(msg[3].differenceTotal,2)+'</font>';
						
						msg[4].differenceFee == 0 ? tab5[6].innerHTML='<font color="green">0.00</font>' : tab5[6].innerHTML='<font color="red">'+formatCash(msg[4].differenceFee,2)+'</font>';
						msg[4].differenceTotal == 0 ? tab5[8].innerHTML='<font color="green">0.00</font>' : tab5[8].innerHTML='<font color="red">'+formatCash(msg[4].differenceTotal,2)+'</font>';
				}
			},
			error : function(data) {
				layer.msg("操作失败！请检查网络环境", 1, 5);
			}

		});
	}

	function updateCheckWithdrawals(status) {
		 if (!check()) {
			return;
		} 
		var date = $("#checkDate").text();
		var tab = $("#accTbale").find("tr");
		var remarks= $("#remarks").val().trim();
		var tab1 = tab[2].children;
		var tab2 = tab[3].children;
		var tab3 = tab[4].children;
		var tab4 = tab[5].children;
		var tab5 = tab[6].children;
		
		var list = [];
		var online = {"type":1, "date" : date ,"checkFee" : tab1[5].children[0].value.replace(/,/g,''), "receiveMoney": tab1[7].children[0].value.replace(/,/g,''), "checkSuccessMoney" : tab1[3].children[0].value.replace(/,/g,''), "fictitiousRecharge" : 0, "differenceFee" : tab1[6].innerText.replace(/,/g,''), "differenceTotal" : tab1[8].innerText.replace(/,/g,'') ,"isSuccess" : status, "remarks" : remarks};
		var sina = {"type":4, "date" : date , "checkFee" : 0, "receiveMoney": tab2[7].children[0].value.replace(/,/g,''), "checkSuccessMoney" : tab2[3].children[0].value.replace(/,/g,''), "fictitiousRecharge" : 0, "differenceFee" : 0, "differenceTotal" : tab2[8].innerText.replace(/,/g,'') ,"isSuccess" : status, "remarks" : remarks};
		var lianlian = {"type":5, "date" : date , "checkFee" : tab3[5].children[0].value.replace(/,/g,''), "receiveMoney": tab3[7].children[0].value.replace(/,/g,''), "checkSuccessMoney" : tab3[3].children[0].value.replace(/,/g,''), "fictitiousRecharge" : 0, "differenceFee" : tab3[6].innerText.replace(/,/g,''), "differenceTotal" : tab3[8].innerText.replace(/,/g,'') ,"isSuccess" : status, "remarks" : remarks};
		var rich = {"type":6, "date" : date , "checkFee" : 0, "receiveMoney": tab4[7].children[0].value.replace(/,/g,'') ,"checkSuccessMoney" : tab4[3].children[0].value.replace(/,/g,''), "fictitiousRecharge" : 0, "differenceFee" : 0, "differenceTotal" : tab4[8].innerText.replace(/,/g,'') ,"isSuccess" : status, "remarks" : remarks};
		var alipay = {"type":7, "date" : date , "checkFee" : 0, "receiveMoney": tab5[7].children[0].value.replace(/,/g,''), "checkSuccessMoney" : tab5[3].children[0].value.replace(/,/g,''), "fictitiousRecharge" : tab5[9].children[0].value.replace(/,/g,''), "differenceFee" : 0, "differenceTotal" : tab1[8].innerText.replace(/,/g,''),"isSuccess" : status , "remarks" : remarks};
		list.push(online);
		list.push(sina);
		list.push(lianlian);
		list.push(rich);
		list.push(alipay);
		
		var urlPath = '${path}/finance/checkRecharge/saveRecharge.html';
		var _load = layer.load('处理中..');
		layer.confirm("确认保存吗？", function(index){ 
		$.ajax({
			url : urlPath,
			type : 'post',
			data : JSON.stringify(list),
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.close(_load);
					layer.msg("操作成功！", 1, 1,function(){
						parent.window.location.href = "${path}/finance/checkRecharge/main.html"; 
						parent.layer.closeAll();
					});

				}

			},
			error : function(data) {
				layer.msg(result.message, 1, 5);
			}
		});
		}); 

	}

	//效验数据
	function check() {
		var list = $("input[name='checkMoney']");
		 for(var i=0;i < list.length; i++){
			 var money = list[i].value;
			 if(money == null || money == ''){
				 layer.msg("请检查表单填写内容!", 1, 5);
				 return false;
			 }
		} 
		return true;
	}
	
	$(".input1").on("keyup", function() {
		  this.value= this.value.replace(/,/g,'');
		  this.value = this.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
		  this.value = this.value.replace(/^\./g, "");  //验证第一个字符是数字而不是.
		  this.value = this.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
		  this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, "$1$2.$3");  //清除小数点后2位之外的数
		  this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		 /* var re =/^[0-9]+([.]{1}[0-9]+){0,1}$/;
		 if(re.exec(this.value)){
			 $("#wcreditlinesCn").val(toCn(this.value));
		 } */
	});
</script>
</html>
