package com.cxdai.console.common.ph;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxdai.console.common.ph.entity.BaseSend;
import com.cxdai.console.common.ph.entity.JsonResult;
import com.cxdai.console.common.ph.entity.UserInfo;
import com.cxdai.console.util.JsonUtils;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="/test")
public class TestController {

	@RequestMapping(value="/queryUser" ,method=RequestMethod.POST)
	@ResponseBody
	public BaseSend queryUser(HttpServletRequest request){
		BaseSend recviveBuildBuild = null;
		//1:设置返回对象
		JsonResult phJson = new JsonResult();
		phJson.setResCode("9999");
		try {
			System.out.println("请求IP==="+getIpAddr(request));
			//2：转换参数对象
			BaseSend build = getReqObj(request,BaseSend.class);
			//3:解密验签 接收对象集合
			List<UserInfo> receiveBean = EncryptionUtil.checkResultList(build , UserInfo.class);
			List<UserInfo> resultBean = new ArrayList<UserInfo>();
			if(receiveBean == null){
				phJson.setResMessage("请求参数错误！");
				return EncryptionUtil.baseReceive(phJson);
			}
			for(UserInfo user : receiveBean){
				user.setRespCode("0000");
				user.setRespMsg("通知成功");
				System.out.println("接收用户名="+user.getName());
				System.out.println("接收用户编号="+user.getNo());
				System.out.println("接收用户年龄="+user.getAge());
				System.out.println("接收用户性别="+user.getSex());
				resultBean.add(user);
			}
			//4：返回对象集合封装json格式
			phJson.setResultStr(List2Json(resultBean));
			phJson.setResCode("0000");
			phJson.setResMessage("操作成功！");
		} catch (Exception e) {
			phJson.setResMessage(e.getMessage());
			e.printStackTrace();
		}
		return  EncryptionUtil.baseReceive(phJson);
	}
	
	 protected <T> T getReqObj(HttpServletRequest req,Class<T> clazz)throws Exception{
	        String strJsob = IOUtils.toString(req.getInputStream(),"UTF-8");
	        T t = JsonUtils.json2Bean(strJsob, clazz);
	        if(null == t){
	            throw new Exception("非法参数提交！");
	        }
	        return t;
	    }
	 
	 public static <T> String List2Json(List<T> list) {
			if (list.size() == 0) {
				return null;
			}
			try {
				return JSONArray.fromObject(list).toString();
			} catch (Exception e) {
				//logger.error("unconverted bean[" + list + "]", e);
			}
			return null;
		}
	 
	 
	 public String getIpAddr(HttpServletRequest request) {      

	        String ip = request.getHeader("x-forwarded-for");      

	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

	                ip = request.getHeader("Proxy-Client-IP");      

	            }     

	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

	                ip = request.getHeader("WL-Proxy-Client-IP");     

	            }     

	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

	                ip = request.getRemoteAddr();      

	            }   

	       return ip;     

	    }  
}
