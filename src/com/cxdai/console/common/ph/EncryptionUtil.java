package com.cxdai.console.common.ph;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cxdai.console.borrow.approve.service.CGUtilService;
import com.cxdai.console.borrow.ph.controller.PhBorrowController;
import com.cxdai.console.common.ph.encryption.RSAUtils;
import com.cxdai.console.common.ph.encryption.SecretKey;
import com.cxdai.console.common.ph.entity.BaseSend;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.StringUtils;

/**
 * 
 * <p>
 * Description:加密工具类<br />
 * </p>
 * @title EncryptionUtil.java
 * @package com.gcjr.loan.common.controller 
 * @author xiaofei.li
 * @version 0.1 2016-6-14
 */
@Component
public class EncryptionUtil extends HttpClientUtil{
		private static final Logger logger = Logger.getLogger(PhBorrowController.class);
		private static final String CHARSET = "utf-8";  
	
		@Autowired  
	    private CGUtilService cGUtilService;  
	    private static EncryptionUtil encryptionUtil;  
	    
	  /*  public void setUserInfo(MessageRecordService messageRecordService) {  
	        this.messageRecordService = messageRecordService;  
	    }*/
	  
	    @PostConstruct  
	    public void init() {  
	    	encryptionUtil = this;  
	    	encryptionUtil.cGUtilService = this.cGUtilService;  
	  
	    }  
	
	/**
	 * 
	 * <p>
	 * Description:发送接口请求<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-15
	 * @param request
	 * @param url
	 * @param returnObj
	 * @return
	 * Res
	 */
	public static <Req,Res> Res SendRequest(String requestJsonStr,String url,Class<Res> returnObj)throws Exception{
		//1:加密
        	byte[] encodedData = RSAUtils.encryptByPrivateKey(requestJsonStr.getBytes(CHARSET), SecretKey.GW_PRIVATE_KEY);  
    	//2：签名
        	 String sign = RSAUtils.sign(encodedData, SecretKey.GW_PRIVATE_KEY); 
    	//3：发送
        	 BaseSend build = new BaseSend();
        	 build.setJsonContent(encodedData);
        	 build.setSign(sign);
        	 String sendJson = JsonUtils.bean2Json(build);
	         String responseJsonStr = httpPostJSON(url,sendJson,CHARSET);
	    	 logger.info("发送报文:"+sendJson);
	    	 logger.info("返回报文:"+responseJsonStr);
	    	  if(StringUtils.isEmpty(responseJsonStr)){
		        throw new Exception("接口无响应"); 
		      }
	    //保存接口记录
	    	// encryptionUtil.messageRecordService.saveMessageRecord(sendJson, responseJsonStr, type);
	    	 Res res = JsonUtils.json2Bean(responseJsonStr,returnObj); 
	    return res;
	} 
	
	
	/**
	 * 
	 * <p>
	 * Description:公共接口返回时加密签名方法<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-15
	 * @param request
	 * @return
	 * @throws Exception
	 * BaseSend
	 */
	public static <Req> BaseSend baseReceive(Req request){
			BaseSend build = new BaseSend();
			String requestJsonStr=JsonUtils.bean2Json(request); 
				try {
					//1:加密
					byte[] encodedData = RSAUtils.encryptByPrivateKey(requestJsonStr.getBytes(CHARSET), SecretKey.GW_PRIVATE_KEY);
					  if(null == encodedData){
						  throw new Exception("加密出错！");
					  }
					//2：签名
		        	 String sign = RSAUtils.sign(encodedData, SecretKey.GW_PRIVATE_KEY); 
		        	 build.setJsonContent(encodedData);
		        	 build.setSign(sign);
				} catch (Exception e) {
					logger.error("返回结果加密出错："+e);
				}  
	    return build;
	} 
	/**
	 * 
	 * <p>
	 * Description:解密   验签<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-15
	 * @param jsonStr
	 * @param returnObj
	 * @return
	 * @throws Exception
	 * T
	 */
	public static <T> List<T> checkResultList(BaseSend jsonStr,Class<T> returnObj)throws Exception{
			//1:验签
			 boolean status = RSAUtils.verify(jsonStr.getJsonContent(), SecretKey.GW_PUBLIC_KEY, jsonStr.getSign());
			 if(status){
				//2:解密
				  byte[] decodedData = RSAUtils.decryptByPublicKey(jsonStr.getJsonContent(),  SecretKey.GW_PUBLIC_KEY);  
				  if(null == decodedData){
					  throw new Exception("解密出错！");
				  }
				  String target = new String(decodedData,CHARSET); 
				     return JsonUtils.jsonToList(target, returnObj); 
			  }else{
				  throw new Exception("验签失败！");
			  }
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:解密   验签返回bean<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-15
	 * @param jsonStr
	 * @param returnObj
	 * @return
	 * @throws Exception
	 * T
	 */
	public static <T> T checkResultBean(BaseSend jsonStr,Class<T> returnObj){
		try {
			//1:验签
			 boolean status = RSAUtils.verify(jsonStr.getJsonContent(), SecretKey.GW_PUBLIC_KEY, jsonStr.getSign());
			 if(status){
				//2:解密
				  byte[] decodedData = RSAUtils.decryptByPublicKey(jsonStr.getJsonContent(),  SecretKey.GW_PUBLIC_KEY);  
				  if(null == decodedData){
					  throw new Exception("解密出错！");
				  }
				  String target = new String(decodedData,CHARSET); 
				     return JsonUtils.json2Bean(target, returnObj); 
			  }else{
				  throw new Exception("验签失败！");
			  }
		} catch (Exception e) {
			logger.error("解密验签出错："+e);
		}  
		  return null;
	}	
}
