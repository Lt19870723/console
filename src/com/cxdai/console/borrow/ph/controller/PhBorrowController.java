
package com.cxdai.console.borrow.ph.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxdai.console.base.entity.Borrower;
import com.cxdai.console.base.entity.Mortgage;
import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.service.CGUtilService;
import com.cxdai.console.borrow.emerg.vo.SalariatBorrowVo;
import com.cxdai.console.borrow.ph.entity.PhBorrow;
import com.cxdai.console.borrow.ph.service.PhBorrowService;
import com.cxdai.console.common.ph.EncryptionUtil;
import com.cxdai.console.common.ph.entity.BaseSend;
import com.cxdai.console.common.ph.entity.JsonResult;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;

import net.sf.json.JSONArray;

/**   
 * <p>
 * Description:普惠发标接口<br />
 * </p>
 * @title PhBorrowController.java
 * @package com.cxdai.console.borrow.ph 
 * @author tanghaitao
 * @version 0.1 2016年6月23日
 */

@Controller
@RequestMapping(value="/phBorrow")
public class PhBorrowController {

	
	@Autowired
	private PhBorrowService phBorrowService;
	
	
	@RequestMapping(value="/initApplyBorrow" ,method=RequestMethod.POST)
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
			//记录接口调用日志
			String sendJson = JsonUtils.bean2Json(build);
			String mode="30";//场景  普惠接口调用
			String relateNo=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
			String remark="普惠发标接口调用";
			phBorrowService.insertMsg(sendJson, mode, 2, remark, relateNo, 5, -3);
			//3:解密验签 接收对象集合
			List<PhBorrow> receiveBean = EncryptionUtil.checkResultList(build , PhBorrow.class);
			List<PhBorrow> resultBean = new ArrayList<PhBorrow>();
			if(receiveBean == null){
				phJson.setResMessage("请求参数错误！");
				return EncryptionUtil.baseReceive(phJson);
			}
			for(PhBorrow phBorrow : receiveBean){
				try {
					
					phBorrow=phBorrowService.initBorrow(phBorrow);
					
				} catch (Exception e) {
					e.printStackTrace();
					phBorrow.setRespCode("9999");
					phBorrow.setRespMsg("发标失败:"+e);
				}
				
				resultBean.add(phBorrow);
			}
			//4：返回对象集合封装json格式
			phJson.setResultStr(List2Json(resultBean));
			phJson.setResCode("0000");
			phJson.setResMessage("接收成功！");
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
