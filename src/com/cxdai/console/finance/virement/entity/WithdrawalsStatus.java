package com.cxdai.console.finance.virement.entity;

import java.util.HashMap;
import java.util.Map;

/*
 * 提现额度状态  （1:对账成功 2：保存草稿 3:未对账 4：信息不匹配)
 */
public enum WithdrawalsStatus { 

    SUCCESS(1,"对账成功"), 
    CUSTOMER(2,"保存草稿"), 
    ORDER(3,"未对账"), 
    APPLY(4,"信息不符"), 
    ;
    
    private WithdrawalsStatus(Integer code,String desc){
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
        WithdrawalsStatus[] values =  values();
        for (WithdrawalsStatus item : values) {
            map.put(item.code, item.desc);
        }
        return map;
    }
    
    public static WithdrawalsStatus getByCode(Integer code){
    	WithdrawalsStatus[] values =  values();
        for (WithdrawalsStatus item : values)
        {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}
