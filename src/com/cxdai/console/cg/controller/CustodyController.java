package com.cxdai.console.cg.controller;

import com.cxdai.console.borrow.check.service.CGBorrowService;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.entity.SingleBiz;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.custody.CustodyBiz;
import com.cxdai.console.common.custody.Finance;
import com.cxdai.console.common.custody.Message;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 存管相关服务容器
 */
@Controller
@RequestMapping(value = "/custody")
public class CustodyController extends BaseController {

	public Logger logger = Logger.getLogger(CustodyController.class);
	
	@Autowired
	private MessageRecordMapper messageRecordMapper;
	@Autowired
	private CGBorrowService cGBorrowService;

	@RequestMapping(value = "/single/main")
	public ModelAndView main() {
		ModelAndView mv=new ModelAndView("/custody/single/main");
		return  mv;
	}


	@RequestMapping(value = "/single/result")
	public ModelAndView result(@RequestParam(value = "orderNo", required = false) String orderNo,
							   @RequestParam(value = "type", required = false) String type) throws Exception {
		ModelAndView mv=new ModelAndView("/custody/single/result");

		//~~~~~组装报文
		XStream xstream = new XStream();
		xstream = new XStream(new DomDriver());

		Finance finance =new Finance();
		Message message =new Message();
		message.setId(UUIDGenerator.generate("DBJY"));


		SingleBiz custodyBiz =new  SingleBiz();
		custodyBiz.setVersion(PropertiesUtil.getValue("custody_version"));
		custodyBiz.setInstId(PropertiesUtil.getValue("custody_instId"));
		custodyBiz.setCertId(PropertiesUtil.getValue("custody_certId"));
		custodyBiz.setDate(DateUtils.formatDateForCustody());
		custodyBiz.setOrderNo(orderNo);
		custodyBiz.setType(type);
		custodyBiz.setExtension("单笔交易流水查询："+DateUtils.getCurrentTimeStamp());

		message.setMode(custodyBiz);
		finance.setMessage(message);

		xstream.autodetectAnnotations(true);
		xstream.aliasField("TSQReq", Message.class, "Mode");
		xstream.addDefaultImplementation(SingleBiz.class,CustodyBiz.class);
		String reqMessage =  xstream.toXML(finance) ;

		String relateNo = UUIDGenerator.generate(CGBusinessConstants.RELATENO);
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode("16");//场景
		messageRecord.setType(0);//0:主动，1:被动
		messageRecord.setMsg(reqMessage);//报文体
		messageRecord.setOrderNo(relateNo);
		messageRecord.setOptUserid( ShiroUtils.currentUser().getUserId());
		messageRecord.setPlatform(ShiroUtils.currentUser().getPlatform());
		messageRecord.setRemark("单笔交易流水查询-请求");
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecordMapper.insert(messageRecord);


		String rep="";
		try {
			rep= XmlUtil.send(reqMessage, "TSQReq");
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
            mv.addObject("err","请求超时");
			return  mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("err","请求异常");
			return  mv;
		}

		MessageRecord messageRecordRes=new MessageRecord();
		messageRecordRes.setMode("16");//场景
		messageRecordRes.setType(1);//0:主动，1:被动
		messageRecordRes.setMsg(rep);//报文体
		messageRecordRes.setOrderNo(relateNo);
		messageRecordRes.setOptUserid( ShiroUtils.currentUser().getUserId());
		messageRecordRes.setPlatform(ShiroUtils.currentUser().getPlatform());
		messageRecordRes.setRemark("单笔交易流水查询-响应");
		messageRecordRes.setRelateNo(relateNo);//调用关联号
		messageRecordMapper.insert(messageRecordRes);

         //验签
		boolean istrue= XmlUtil.checkYq(rep);
		if(!istrue){
			return mv.addObject("err","验签失败");
		}
		//判断响应报文
		boolean isError=XmlUtil.checkXml(rep);
		if(isError){
			Map<String, Object> maperr = XmlUtil.toError(rep);
			String errorCode=(String)maperr.get("errorCode");
			String errorDetail=(String)maperr.get("errorDetail");
			String err="银行响应异常-["+errorCode+"]"+errorDetail;
			return  mv.addObject("err",err);
		}
		Map<String, Object> mapBiz = XmlUtil.toBiz(rep, "TSQRes");
		if(mapBiz.get("stt")!=null){
			String stt = mapBiz.get("stt").toString();
			if(stt.equals("00")){
				mapBiz.put("sttMsg",stt+":不存在或未处理");
			}else if(stt.equals("10")){
				mapBiz.put("sttMsg",stt+":处理中");
			}else if(stt.equals("20")){
				mapBiz.put("sttMsg",stt+":成功");
			} else if(stt.equals("30")){
				mapBiz.put("sttMsg",stt+":失败");
			}else {
				mapBiz.put("sttMsg",stt+":未知状态");
			}
		}
		if(mapBiz.get("extension") ==null){
			mapBiz.put("extensionMsg","无");
		}else{
			mapBiz.put("extensionMsg",mapBiz.get("extension").toString());
		}

		return  mv.addObject("map",mapBiz);
	}

	
	/**
	 * 
	 * <p>
	 * Description:进入余额查询页面<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月21日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/tofindCGAccount")
	public ModelAndView tofindCGAccount(){
		return new ModelAndView("custody/cgaccount/main");
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:调用余额查询接口<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月21日
	 * @param bindNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/findCGAccount")
	public ModelAndView findCGAccount(String userName){
		ModelAndView mv=new ModelAndView("custody/cgaccount/result");
		Account account = null;
		try {
			String bindNo= cGBorrowService.selectByUserName(userName);
			if(StringUtils.isBlank(bindNo)){
				return mv.addObject("err", "该用户未开通存管账户");
			}
			ShiroUser shiroUser=currentUser();
			String relateNo=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
			String remark="后台余额查询";
			//调用存管余额查询接口
			String rep= cGBorrowService.saveCGAccount(shiroUser, relateNo,remark,bindNo);
			//解析报文
			account= cGBorrowService.parseAQReqXml(rep, shiroUser, remark, relateNo);
			if(account==null){
				logger.error("返回ERROR报文或验签失败");
				return mv.addObject("err", "返回ERROR报文或验签失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv.addObject("account", account);
		
	}
	
}