package com.cxdai.console.lottery.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.service.GoodSendInfoService;
import com.cxdai.console.lottery.service.UseRecordService;
import com.cxdai.console.lottery.vo.GoodSendInfo;
import com.cxdai.console.lottery.vo.UseRecord;
import com.cxdai.console.lottery.vo.UseRecordCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.EncodingTool;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:抽奖奖品控制类<br />
 * </p>
 * @title GoodController.java
 * @package com.cxdai.console.lottery.controller 
 * @author yubin
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/lottery/gooddealuserecord")
public class GoodDealUserecordController extends BaseController {
	
  private final static Logger logger=Logger.getLogger(GoodDealUserecordController.class);
  @Autowired
  private UseRecordService  useRecordService;
  @Autowired
  private GoodSendInfoService goodSendInfoService;
	
  /**
    * 
    * <p>
    * Description:抽奖奖品管理主界面<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月23日
    * @return
    * ModelAndView
    */
    @RequestMapping("/main")
	public ModelAndView main() {
	 
		return new ModelAndView("/lottery/gooddealuserecord/main");
	}
   /**
    *  
    * <p>
    * Description:抽奖奖品管理列表<br />
    * </p>
    * @author Administrator
    * @version 0.1 2015年6月24日
    * @param rechargeRecordCnd
    * @param pageNo
    * @return
    * ModelAndVie
    */
   @RequestMapping("/list/{pageNo}")
   public ModelAndView searchUseRecordList(@ModelAttribute UseRecordCnd useRecordCnd, @PathVariable Integer pageNo) {
   	Page page = null;
    
	try { 
		// 获取列表
		page = useRecordService.queryPageUseRecordByCnd(useRecordCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	} catch (Exception e) {
		logger.error(e);
	}
   	return new ModelAndView("/lottery/gooddealuserecord/list").addObject("page", page);
   }
   /**
    * 
    * <p>
    * Description:进入待处理页面<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年7月6日
    * @return
    * ModelAndView
    */
   @RequestMapping("/toAccept")
	public ModelAndView toAccept(@RequestParam(value = "id", required = false) Integer id,
			                     @RequestParam(value = "name", required = false) String name) {
	     
	    GoodSendInfo goodSendInfo = goodSendInfoService.getGoodSendInfoByUseRecordId(id); 
		goodSendInfo.setUserName(EncodingTool.encodeStr(name));
		
		return new ModelAndView("/lottery/gooddealuserecord/layer").addObject("goodSendInfo",goodSendInfo);
	}
   /**
    * 
    * <p>
    * Description:处理功能<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月29日
    * @param borrowId
    * @return
    * MessageBox
    */
   @RequestMapping("/saveGoodSendInfo")
   @ResponseBody
   public  MessageBox saveGoodSendInfo(@ModelAttribute GoodSendInfo goodSendInfo) {
   	String message=null;
   	try {
   		String ip = HttpTookit.getRealIpAddr(currentRequest());
   		ShiroUser shiroUser=ShiroUtils.currentUser();
		goodSendInfo.setDealUser(shiroUser.getName());
		goodSendInfo.setDealTime(new Date());
		goodSendInfo.setDealIp(ip);

		goodSendInfoService.saveGoodSendInfo(goodSendInfo, 2);
        message="操作成功";
        return MessageBox.build("1", message);
  
   	} catch (Exception e) {
   		stackTraceError(logger, e);
   		message = "操作失败！";
   		return  MessageBox.build("0", message);
   	}
   }
   /**
    * 
    * <p>
    * Description:更新操作<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月29日
    * @param borrowId
    * @return
    * MessageBox
    */
   @RequestMapping("/updateUseRecordStatus")
   @ResponseBody
   public  MessageBox updateUseRecordStatus(@RequestParam(value = "id", required = false) Integer id) {
   	String message=null;
   	try {
   		UseRecord useRecord = new UseRecord();
		useRecord.setId(id);
		useRecord.setStatus(1);
		useRecordService.updateUseRecordByEntry(useRecord);
        message="操作成功";
        return MessageBox.build("0", message);
  
   	} catch (Exception e) {
   		stackTraceError(logger, e);
   		message = "操作失败！";
   		return  MessageBox.build("1", message);
   	}
   }
}
