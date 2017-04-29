<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>收支数据分析-国诚金融后台管理系统</title>
</head>
<body>
	<!-- 收入支出图 -->
	<div id="container" style="min-width: 700px; height: 550px"></div>
	
	<!-- 净收益图 -->
	<div id="container1" style="min-width: 700px; height: 550px"></div>
</body>
<script type="text/javascript">
	$(function() {
		var result1 = '${incomepayjson}';
		var result2 = '${netBenefitResult}';
		if (result1 != '') {
			getIncomePayPic(eval("(" + result1 + ")"));//收入支出图
		}

		if (result2 != '') {
			getNetBenefitPic(eval("(" + result2 + ")"));// 净收益图
		}

	});

	// 收入支出图
	function getIncomePayPic(result) {
		console.info(result);
		var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				type : 'column'
			},
			title : {
				text : '收入支出图',
				x : -20
			},
			credits : {
				enabled : false
			},
			xAxis : {
				categories : result.categories,
				labels: {
	                rotation: -90,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			},
			yAxis : {
				max : result.yMax, // 控制纵坐标轴最大值
				tickInterval : 2000,//将图表纵轴间隔设为2000 
				lineWidth : 1,
				tickWidth : 3,
				tickColor : '#000',
				title : {
					text : ''
				}
			},
			plotOptions : {
				series : {
					turboThreshold : 50000
				}
			},
			tooltip : {
				headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
				footerFormat : '</table>',
				shared : true,
				useHTML : true
			},
			series : [{
				name : '收入',
				color: '#5275c3',
				data : result.income
			},{
				name : '支出',
				color: '#ba3437',
				data : result.pay 
			}]
		 
		});
	}
	//净收益图 
	function getNetBenefitPic(result) {
		console.info(result);
		var chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container1'
			},
			title : {
				text : '净收益图',
				x : -20
			},
			credits : {
				enabled : false
			},
			xAxis : {
				categories : result.categories,
				labels: {
	                rotation: -90,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			},
			yAxis : {
				min : result.yMin, // 控制纵坐标最小值
				max : result.yMax, // 控制纵坐标轴最大值
				//tickInterval : 2000,//将图表纵轴间隔设为2000 
				lineWidth : 1,
				tickWidth : 3,
				tickColor : '#000',
				title : {
					text : ''
				}
			},
			legend : {
				align : "right",
				verticalAlign : 'middle',
			},
			plotOptions : {
				series : {
					turboThreshold : 50000
				}
			},
			tooltip : {
				headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' + '<td style="padding:0"><b>{point.y:.2f} 元</b></td></tr>',
				footerFormat : '</table>',
				shared : true,
				useHTML : true
			},
			series : [{
				name : '净收益',
				color: '#99af50',
				data : result.netBenefit
			}]
		 
		});
	}
</script>
</html>