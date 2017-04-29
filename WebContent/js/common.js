/*$(function() {
	$('#datetimepicker1').datetimepicker({
		language : 'zh-CN',
		autoclose : true,
		format : 'yyyy-mm-dd',
		weekStart : 1,
		minView : 'month',
		todayHighlight : true
	});
	$('#datetimepicker2').datetimepicker({
		language : 'zh-CN',
		autoclose : true,
		format : 'yyyy-mm-dd',
		weekStart : 1,
		minView : 'month',
		todayHighlight : true
	});
	$('#selectmore').on('click', function() {
		$('.selectbox').slideToggle(1000);
		$(this).toggleClass('dropup');
	});
	$('.sorting').on('click', function() {
		$(this).toggleClass('dropup');
	});

	var gotop = new goTop({
		showQrcode : false,
		showPhone : false,
		showQQ : false
	});

	// header
	$('.subMenu span').click(function() {
		$(this).parent('li').addClass('cur').siblings().removeClass('cur');
		$('.subMenu .on').parent('li').addClass('cur');
	})
	$('.nav .nav_a').mouseenter(function() {
		$(this).addClass('hover');
		$(this).find('.dropDown').show();
	}).mouseleave(function() {
		$(this).removeClass('hover');
		$(this).find('.dropDown').hide();
	});
})*/


function toCn(n){
	var _this = this;
	if(n == 0){
		return;
	}
    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
        return "数据非法";
    var unit = "千百拾亿千百拾万千百拾元角分", str = "";
    n += "00";
    var p = n.indexOf('.');
    if (p >= 0)
        n = n.substring(0, p) + n.substr(p + 1, 2);
    unit = unit.substr(unit.length - n.length);
    for (var i = 0; i < n.length; i++)
        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
}

//格式化金额，实现每几位一个逗号,其中n为参数每几位的值
function formatCash(s,n){
	if(!s){
		 return '0.00';
	 }
	   n = n > 0 && n <= 20 ? n : 2;   
	   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	   var l = s.split(".")[0].split("").reverse(),   
	   r = s.split(".")[1];   
	   t = "";   
	   for(i = 0; i < l.length; i ++ )   
	   {   
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	   }   
		//可能会出现-,100.00这样的错误格式
		rslt = t.split("").reverse().join("") + "." + r;
		return rslt.replace("-,","-")   

}

/**
 * 获取x下面时间格式的字符串 yyyy-mm-dd HH:SS
 * @param val
 * @returns {String}
 */
function formatDateTimeYmdhs(val){
	 if(!val){
		 return '';
	 }
	 var dateTime = new Date(val);
		var year = dateTime.getFullYear();
		var month = dateTime.getMonth() + 1;
		var date = dateTime.getDate();
		var hour = dateTime.getHours();
		var minutes = dateTime.getMinutes();
		return year
			+ "-"
			+ (month >= 10 ? month : ("0" + month))
			+ "-"
			+ (date >= 10 ? date : ("0" + date))
			+ " "
			+ (hour >= 10 ? hour : ("0" + hour))
			+ ":"
			+ (minutes >= 10 ? minutes
					: ("0" + minutes)) ;

}

function formatDateYmdhMs(val){
	 if(!val){
		 return '';
	 }
	 var dateTime = new Date(val);
		var year = dateTime.getFullYear();
		var month = dateTime.getMonth() + 1;
		var date = dateTime.getDate();
		var hour = dateTime.getHours();
		var minutes = dateTime.getMinutes();
		var sec = dateTime.getSeconds();
		return year
				+ "-"
				+ (month >= 10 ? month : ("0" + month))
				+ "-"
				+ (date >= 10 ? date : ("0" + date))
				+ " "
				+ (hour >= 10 ? hour : ("0" + hour))
				+ ":"
				+ (minutes >= 10 ? minutes
						: ("0" + minutes)) + ":"
				+ (sec >= 10 ? sec : ("0" + sec));

}


function formatDateTimeYmd(val){
	 if(!val){
		 return '';
	 }
	 var dateTime = new Date(val);
		var year = dateTime.getFullYear();
		var month = dateTime.getMonth() + 1;
		var date = dateTime.getDate();
		return year
			+ "-"
			+ (month >= 10 ? month : ("0" + month))
			+ "-"
			+ (date >= 10 ? date : ("0" + date)) ;

}

/**
 * 判断传入的值是空或者null 或者未定义，如果满足上面的判断，返回true
 * @param val
 * @returns {Boolean}
 */
function beNullOrEmpty(val){
	
	if (val == null || val == undefined || val == ''  || val == "") { 
		return true;
	} 
	return false;
}

function contains(arr,value){
	for(var i=0;i<arr.length;i++){
        if(arr[i]==value){return true;}
    }
	return false;
}

/**
 * 判断sourceDate 是否早于targetDate
 * 如果早于 返回 1
 * 如果等于 返回 0
 * 如果晚于 返回 -1
 * 如果传入的值的类型不为Date类型 返回-2
 */
function compareDate(sourceDate,targetDate){
	if(!(sourceDate instanceof Date) || !(targetDate instanceof Date))
		return -2;
	if(sourceDate>targetDate)
		return -1;
	else if(sourceDate<targetDate)
		return 1;
	else
		return 0;
}
//清除以0开头的末尾数字
function clearMoreZeroStart(val){
	var zeroReg = /^[0]\d/g;
	if(zeroReg.test(val)){
		val = val.substring(0,val.length-1);
	}
	return val;
}
//清除以0开头的末尾数字
function clearMorePoint(val){
	while(val.indexOf(".")!=val.lastIndexOf(".")){
		val = val.substring(0,val.lastIndexOf("."));
	}
	return val;
}
/**
 * 清除多余的小数
 * @param val 待清除的值
 * @param precision 小数的位数
 */
function clearMoreDecimal(val,precision){
	if(val.indexOf(".")!=-1 && val.length-1>val.indexOf(".")+precision){
		val = val.substring(0,val.indexOf(".")+precision+1);
	}
	return val;
}
function clearMoreNumberLength(val,clearMoreNumberLength){
	if(val.indexOf(".")==-1){
		val = val.substring(0,clearMoreNumberLength);
	}
	return val;
}

/**
 * 在class 中增加positiveNumberClass 用于校验非负数的输入
 * @param positiveNumberClass
 */
function onPositiveNumberInput(positiveNumberClass){
	$("."+positiveNumberClass).on("keyup", function() {
		var val = this.value;
		val = val.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符
		val = val.replace(/^\./g, "");//验证第一个字符是数字而不是.
		val = clearMoreZeroStart(val);
		val = clearMorePoint(val);
	    this.value = val;
	});
}
/**
 * 在class 中增加positiveIntegerClass 用于校验正整数的输入
 * @param positiveNumberClass
 */
function onPositiveIntegerNumberInput (positiveIntegerClass){
	$("."+positiveIntegerClass).on("keyup", function() {
		var val = this.value;
		val = val.replace(/[^\d]/g, "");//清除“数字”以外的字符
		val = clearMoreZeroStart(val);
		this.value = val;  
	});
}
/**
 * 在class 中增加maxDecimalClass 用于校验小数部分的精度
 * @param maxDecimalClass
 */
function onMaxDecimalInput (maxDecimalClass){
	$("."+maxDecimalClass).on("keyup", function() {
		var val = this.value;
		var precision = new Number($(this).attr("maxPrecision"));
		val = clearMoreDecimal(val,precision);
		this.value = val;
	});
}
/**
 * 在class 中增加maxNumberLengthClass 用于校验数字的整数部门的长度
 * @param maxDecimalClass
 */
function onMaxNumberLengthInput(maxNumberLengthClass){
	$("."+maxNumberLengthClass).on("keyup", function() {
		var val = this.value;
		var len = new Number($(this).attr("maxNumberLength"));
		val = clearMoreNumberLength(val,len);
		this.value = val;
	});
}
/**
 * 在class里面增加 lengthClass，属性增加maxlength
 * @param lengthClass
 */
function onMaxTextLengthInput(lengthClass,cnCharLength){
	$("."+lengthClass).on("keyup", function() {
		var len = 0;
		var limit = $(this).attr("maxlength");
		var val = this.value;
		var char ;
		for(var i = 0;i<val.length;i++){
			char   =   val.charCodeAt(i);
			
			  if (char>=0&&char<=128) {
				  len+=1;
			  }else{
				  len+=cnCharLength;
			  }
			 if(len>limit){
				 this.value = val.substring(0,i); 
				 break;
			 } 
		}
	});
}
/**
 * 将数字转化为人民币汉字
 * 参数非法性校验不做，请在其他方法中先校验好
 * @param currencyDigits
 */
function changeMoneyToChinese(money) {
    var cnNums = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); //汉字的数字
    var cnIntRadice = new Array("", "拾", "佰", "仟"); //基本单位
    var cnIntUnits = new Array("", "万", "亿", "兆"); //对应整数部分扩展单位
    var cnDecUnits = new Array("角", "分", "毫", "厘"); //对应小数部分单位
    var cnInteger = "整"; //整数金额时后面跟的字符
    var cnIntLast = "元"; //整型完以后的单位
    var maxNum = 999999999999.99; //最大处理的数字

    var IntegerNum; //金额整数部分
    var DecimalNum; //金额小数部分
    var ChineseStr = ""; //输出的中文金额字符串
    var parts; //分离金额后用的数组，预定义

    if (money == "") {
        return "";
    }
    if(money.indexOf(".")!=-1 && money.indexOf(".")==money.length-1){
    	money = money.substring(0,money.indexOf("."));
    }
    money = parseFloat(money);
    //alert(money);
    if (money >= maxNum) {
        return "超出最大处理数字";
    }
    if (money == 0) {
        ChineseStr = cnNums[0] + cnIntLast + cnInteger;
        //document.getElementById("show").value=ChineseStr;
        return ChineseStr;
    }
    money = money.toString(); //转换为字符串
    if (money.indexOf(".") == -1) {
        IntegerNum = money;
        DecimalNum = '';
    } else {
        parts = money.split(".");
        IntegerNum = parts[0];
        DecimalNum = parts[1].substr(0, 4);
    }
    if (parseInt(IntegerNum, 10) > 0) {//获取整型部分转换
        zeroCount = 0;
        IntLen = IntegerNum.length;
        for (i = 0; i < IntLen; i++) {
            n = IntegerNum.substr(i, 1);
            p = IntLen - i - 1;
            q = p / 4;
            m = p % 4;
            if (n == "0") {
                zeroCount++;
            } else {
                if (zeroCount > 0) {
                    ChineseStr += cnNums[0];
                }
                zeroCount = 0; //归零
                ChineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
            }
            if (m == 0 && zeroCount < 4) {
                ChineseStr += cnIntUnits[q];
            }
        }
        ChineseStr += cnIntLast;
        //整型部分处理完毕
    }
    if (DecimalNum != '') {//小数部分
        decLen = DecimalNum.length;
        for (i = 0; i < decLen; i++) {
            n = DecimalNum.substr(i, 1);
            if (n != '0') {
                ChineseStr += cnNums[Number(n)] + cnDecUnits[i];
            }
        }
    }
    if (ChineseStr == '') {
        ChineseStr += cnNums[0] + cnIntLast + cnInteger;
    }
    else if (DecimalNum == '') {
        ChineseStr += cnInteger;
    }
    return ChineseStr;

};
//格式化金钱，s为decimal,n为保留小数点位数,当t为true时可以返回负数
function decimalFormat(s, n,t) {
	s=s+"";
	s=s.replace(/[^\-\.\d]+/g, "");
	s=(s==""||s=="-")?"0.00":s;
	/*if (!isNaN(s)){
		s=s+"";
		s=s.replace(/[^\-\.\d]+/g, "");
		s=(s==""||s=="-")?"0.00":s;
	}*/
    if (n == 0) {
        s = parseFloat(s);
    }
    else {
        s = parseFloat(s).toFixed(n);
    }
    var floatStr = s + "";
    var result = "";
    var signStr = "";
    if (floatStr.indexOf('-') == 0) {
        signStr = "-";
        floatStr = floatStr.split('-')[1];
    }
    var PointStr = floatStr.split('.');
    var prePointStr = "";
    var endPointStr = "";
    if (PointStr.length > 1) {
        prePointStr = PointStr[0].split("").reverse();
        endPointStr = PointStr[1];
    }
    else {
        prePointStr = PointStr[0].split("").reverse();
    }
    for (var i = 0, len = prePointStr.length; i < len; i++) {
        result += prePointStr[i] + ((i + 1) % 3 == 0 && (i + 1) != len ? "," : "");
    }
    if (n == 0) {
        result = result.split("").reverse().join("");
    }
    else {
        result = result.split("").reverse().join("") + "." + endPointStr;
    }
    if(t){
    	return signStr + result;
    }
    return result;
};
//a为格式为 2,152.25这种格式的数字
function decimalConvert(a) {
    if (isNaN(a)) {
        return a.replace(/[^\-\.\d]+/g, "");
    } else {
        return a * 1;
    }
};
//获取标准格式数字123.56 ,n为小数点后面的位数,a为任意字符串
function getFixedNumber(a, n) {
    a += "";
    a = a.replace(/[^\-\.\d]+/g, "").replace(/^0+(\d+)/, "$1").replace(/[^\.\d]+/g, "").replace(/(\.$)/, "$10");
    var b = "";
    var numbers = a.split(".");
    if (numbers.length > 1) {
        for (var start = 0, l = numbers.length; start < l; start++) {
            if (start == l - 1) {
                b = b + "." + numbers[start];
            }
            else {
                b += numbers[start];
            }
        }
    }
    else {
        b = a;
    }
    a = b;
    return (a * 1).toFixed(n);
};
function changeCNInput(changeSource,changeTarget){
	changeSource.on("keyup", function() {
		var this_money= this.value;
		 changeTarget.val(changeMoneyToChinese(this_money));
	});
}

function loadData(id,targetTag,data){
	if(!data)return;
	for (var name in data) {
		var value = data[name];
		var target = $("#"+id).find(targetTag+'[name='+name+']:first');
		var pattern = target.attr("pattern");
		value = format(pattern, value);
		if(typeof(target.attr("value"))!="undefined"){
			target.attr("value",value);
		}else{
			target.html(value);
		}
	}
}

function format(pattern, value){
	var formated = value;
	switch (pattern) {
		case "yyyy-MM-dd HH:mm:ss":{
			formated = formatDateYmdhMs(formated);
			break;
		}
		case "0.00":{
			formated = '￥'+formatCash(formated);
			break;
		}
		default:
			break;
	}
	return formated;
}




function syntaxHighlight(json) {
	if (typeof json != 'string') {
		json = JSON.stringify(json, undefined, 2);
	}
	json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g,
			'&gt;').replace(/{/g,'{\r\n').replace(/,/g,',\r\n').replace(/}/g,'\r\n}');
	return json
			.replace(
					/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g,
					function(match) {
						var cls = 'number';
						if (/^"/.test(match)) {
							if (/:$/.test(match)) {
								cls = 'key';
							} else {
								cls = 'string';
							}
						} else if (/true|false/.test(match)) {
							cls = 'boolean';
						} else if (/null/.test(match)) {
							cls = 'null';
						}
						return '<span class="' + cls + '">' + match
								+ '</span>';
					});
}