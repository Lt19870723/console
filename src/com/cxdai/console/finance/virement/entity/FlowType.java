package com.cxdai.console.finance.virement.entity;

import java.util.HashMap;
import java.util.Map;

/*
 * 提现额度状态  （1:收入 -1：支出 )
 */
public enum FlowType { 

    ICOME(1,"收入"), 
    EXPURTE(-1,"支出"), 
    ;
    
    private FlowType(Integer code,String desc){
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
        FlowType[] values =  values();
        for (FlowType item : values) {
            map.put(item.code, item.desc);
        }
        return map;
    }
    
    public static FlowType getByCode(Integer code){
    	FlowType[] values =  values();
        for (FlowType item : values)
        {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}
