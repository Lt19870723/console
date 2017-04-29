/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SerialNumber.java
 * @package com.gcjr.loan.common 
 * @author tanghaitao
 * @version 0.1 2016年1月29日
 */
package com.cxdai.console.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**   
 * <p>
 * Description:流水号<br />
 * </p>
 * @title SerialNumber.java
 * @package com.gcjr.loan.common 
 * @author tanghaitao
 * @version 0.1 2016年1月29日
 */

public class SerialNumber {

	public static String CG_P2P_DATE="CGp2pDate";
	
	public static String ContractSerialNumber(Integer contractId){
		String contractNo=null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = sdf.format(date).replaceAll("-", "");
		String dataStr2=dataStr.substring(2, dataStr.length());
		if(contractId.toString().length()<=1){
			contractNo=dataStr2+"00000"+contractId.toString();
		}else if(contractId.toString().length()<=2){
			contractNo=dataStr2+"0000"+contractId.toString();
		}else if(contractId.toString().length()<=3){
			contractNo=dataStr2+"000"+contractId.toString();
		}else if(contractId.toString().length()<=4){
			contractNo=dataStr2+"00"+contractId.toString();
		}else if(contractId.toString().length()<=5){
			contractNo=dataStr2+"0"+contractId.toString();
		}else{
			contractNo=dataStr2+contractId.toString();
		}
		return contractNo;
		
	}
	

	
	/**
	 * 
	 * <p>
	 * Description:生成P2P业务流水号<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月16日
	 * @param type
	 * @return
	 * String
	 */
	public static String p2pSerialNumber(String type){
		String p2pNumber=type;
		String cgtype = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		p2pNumber= p2pNumber+sdf.format(date);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String p2pDate= sdf2.format(date);
		boolean  keybool = CacheService.getInstance().existKey("CG"+type);
		boolean  cgDate = CacheService.getInstance().existKey(CG_P2P_DATE);
				if(keybool){
					cgtype= CacheService.getInstance().getString("CG"+type);
				}
			    if(!cgDate){
			    	CacheService.getInstance().put(CG_P2P_DATE, p2pDate);
			    }
			    	
				String cgp2pDate= CacheService.getInstance().getString(CG_P2P_DATE);
				if(cgp2pDate.equals(p2pDate)){
					Integer number=SerialNumber.getNumber("CGNumber"+type);
					p2pNumber=p2pNumber+number;
					CacheService.getInstance().put("CG"+type, p2pNumber);
				}else{
					CacheService.getInstance().remove(CG_P2P_DATE);
					CacheService.getInstance().remove("CGNumber"+type);
					Integer number=SerialNumber.getNumber("CGNumber"+type);
					p2pNumber=p2pNumber+number;
					CacheService.getInstance().put("CG"+type, p2pNumber);
				}
				
		if(p2pNumber.equals(cgtype)){
			SerialNumber.p2pSerialNumber(type);
		}		
		return p2pNumber;
		
	}
	
	
	   /**
	    * 
	    * <p>
	    * Description:获取序列号<br />
	    * </p>
	    * @author tanghaitao
	    * @version 0.1 2016年5月16日
	    * @param key
	    * @return
	    * Integer
	    */
	   public static Integer getNumber(String key) {
		   Integer number=100001;
		   boolean  cgDate = CacheService.getInstance().existKey(key);
	       if(cgDate){
	    	   String value= CacheService.getInstance().getString(key);
	    	   number=Integer.parseInt(value)+1;
	       }else{
	    	   CacheService.getInstance().put(key, number.toString());
	       }
	    	return number;
	    	
	    }
	
	
	public static void main(String[] args) {
		String p2pNumber= SerialNumber.p2pSerialNumber("dj");
		System.out.println(p2pNumber);
		
	}
	
	
	
	
}
