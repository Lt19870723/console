<script src="${path}/js/json/city.js" type="text/javascript" charset="UTF-8"></script>
<script src="${path}/js/json/province.js" type="text/javascript" charset="UTF-8"></script>
<script src="${path}/js/json/district.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
//省市区公用方法
function addrCommon(jsonId,jsonName,value,objId){
	var option = "<option value='" + jsonId + "'>" + jsonName + "</option>";
    if(jsonId==value){
    	option = "<option value='" + jsonId + "' selected>" + jsonName + "</option>";
 	}
    $("#"+objId).append(option);
}
//默认加载上海
function defaultSHcityList(selProvince,selCity){
	$("#"+selProvince).val('310000');
	$.each(city, function (k, v) {
		if (Math.floor(v.code / 10000) == (310000 / 10000)) {
			var option = "<option value='" + v.code + "'>" + v.name + "</option>";
			$("#"+selCity).append(option);
		}
	});
}
/**
 * 加载省市区
 * pid,cid,aid 表单对象的ID
 * pvalue,cvalue,avalue 数据库字段储存的值，初始化编辑用，新增时可不传
 */
function initPCA(pid,cid,aid,pvalue,cvalue,avalue){
	 
 	var provinceId = pvalue;
 	var cityId = cvalue;	
 	var areaId = avalue;
 	//----------------------------初始化------------------------------
 	//循环加载省 	
    $.each(province, function (k, v) {     	
    	addrCommon(v.code,v.name,provinceId,pid);
    }); 	
    //修改省触发事件循环加载市
    $("#"+pid).change(function () {    	
        var selVal = $(this).val();         
        $("#"+cid+" option:gt(0)").remove();
        $("#"+aid+" option:gt(0)").remove(); 
        $.each(city, function (k, v) {         	
            if (Math.floor(v.code / 10000) == (selVal / 10000)) {
            	addrCommon(v.code,v.name,cityId,cid);               
            }
        });             
    });     
    //修改市触发事件循环加载区
    $("#"+cid).change(function () {
        var selVal = $(this).val();
        $("#"+aid+" option:gt(0)").remove();
        $.each(district, function (k, v) {
            if (Math.floor(v.code / 100) == (selVal / 100)) {
            	addrCommon(v.code,v.name,areaId,aid);               
            }
        }); 
    });
     
    
}

/**
 * 省市区拼接
 */
function pinAddr(_p,_c){
 
	_p= $("#"+_p).find("option:selected").text();
	_c= $("#"+_c).find("option:selected").text();
    $("#provinceName").val(_p);
	$("#cityName").val(_c);
}
</script>