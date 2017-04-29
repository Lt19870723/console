package com.cxdai.console.finance.virement.entity;

import java.util.HashMap;
import java.util.Map;

/*
 * 提现额度状态  （1:公司 2：员工 3:其他)
 */
public enum BalanceType { 

	COMPANY(1,"公司"), 
	STAFF(2,"员工"), 
	OTHER(3,"其他"), 
    ;
    
    private BalanceType(Integer code,String desc){
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
        BalanceType[] values =  values();
        for (BalanceType item : values) {
            map.put(item.code, item.desc);
        }
        return map;
    }
    
    public static BalanceType getByCode(Integer code){
    	BalanceType[] values =  values();
        for (BalanceType item : values)
        {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}
