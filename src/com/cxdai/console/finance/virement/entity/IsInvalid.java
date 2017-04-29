package com.cxdai.console.finance.virement.entity;

import java.util.HashMap;
import java.util.Map;

/*
 * 提现额度状态  （0:删除 2：正常)
 */
public enum IsInvalid { 

	INVALID(0,"删除"), 
	TAKEEFFECT(1,"正常"), 
    ;
    
    private IsInvalid(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
    
    private Integer code;
    private String desc;
    
    public Integer getCode()
    {
        return code;
    }
    public String getDesc()
    {
        return desc;
    }

    public static Map<Integer,String> getEnumMap(){
        Map<Integer,String> map = new HashMap<Integer,String>();
        IsInvalid[] values =  values();
        for (IsInvalid item : values) {
            map.put(item.code, item.desc);
        }
        return map;
    }
    
    public static IsInvalid getByCode(Integer code){
    	IsInvalid[] values =  values();
        for (IsInvalid item : values)
        {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}
