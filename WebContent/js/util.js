/**
 * 批量显示或隐藏对象(有display属性的html元素都可使用)
 * @param objs 参数：id-none/block,多个用|分隔
 * @param displayValue 可不传值
 * 调用示例：
 * 1、displaySet('mortgageDiv-none|mortgageTypeDiv-block')
 * 2、displaySet('mortgageDiv|mortgageTypeDiv','none')
 */
function displaySet(objs,displayValue){
		displayValue = displayValue || 0;
		var objAry = objs.split('|');
		for(var i=0;i<objAry.length;i++){
			var obj = objAry[i].split('-');
			if(displayValue==0){
				$('#'+obj[0]+'').css('display',obj[1]);
			}else{
				if(displayValue == 'block'){
					$('#'+obj[0]+'').css('display',"");
				}else{
					$('#'+obj[0]+'').css('display',displayValue);
				}
			}
		}
	}

/**
 * 批量设置对象属性值
 * @param objs 参数:id-attrName-attrValue 或者 id-attrValue
 * @param attrName 属性名称,可不传值
 * 
 * 调用示例:
 * 1、设置同一属性的值 attrSet('guarantyOrganization-Require|pledgeSel-|creditSel-','dataType');
 * 2、单独指定每个对象的属性值 attrSet('guarantyOrganization-msg-不能为空|guarantyOrganization-dataType-Require||creditSel-dataType-');
 */
function attrSet(objs,attrName){
	attrName = attrName || 0;
	var objAry = objs.split('|');
	for(var i=0;i<objAry.length;i++){
		var obj = objAry[i].split('-');
		if(attrName==0){
			$('#'+obj[0]+'').attr(obj[1],obj[2]);
		}else{
			$('#'+obj[0]+'').attr(attrName,obj[1]);
		}
	}
}
function attrSet(objs,attrName,attrValue){
	var objAry = objs.split('|');
	for(var i=0;i<objAry.length;i++){
		var obj = objAry[i];
		$('#'+obj).attr(attrName,attrValue);
	}
}

/**
 * 批量移除对象属性值
 * @param objs
 * @param attrName
 */
function attrRemove(objs,attrName){
	attrName = attrName || 0;
	var objAry = objs.split('|');
	for(var i=0;i<objAry.length;i++){
		var obj = objAry[i];
		$('#'+obj).removeAttr(attrName);
	}
}