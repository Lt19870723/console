package com.cxdai.console.finance.virement.entity;

import java.util.HashMap;
import java.util.Map;

/*
 * 提现额度状态  （1:网银在线 4：新浪支付 5:连连支付 6：富有支付 7:支付宝)
 */
public enum RechargeType { 

    ONLINE(1,"网银在线"), 
    SINA(4,"新浪支付"), 
    LIANLIAN(5,"连连支付"), 
    RICH(6,"富有支付"), 
    ALIPAY(7,"支付宝"), 
    ;
    
    private RechargeType(Integer code,String desc){
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
        RechargeType[] values =  values();
        for (RechargeType item : values) {
            map.put(item.code, item.desc);
        }
        return map;
    }
    
    public static RechargeType getByCode(Integer code){
    	RechargeType[] values =  values();
        for (RechargeType item : values)
        {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}
