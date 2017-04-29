package com.cxdai.console.borrow.autotender.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.recharge.service.AutoInvestConfigRecordService;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordCnd;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordVo;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:自动投标规则日志记录<br />
 * </p>
 * @title AutoInvestController.java
 * @package com.cxdai.console.borrow.autotender.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Controller
@RequestMapping(value = "/borrow/autotender/autoInvesLog")
public class AutoInvestRecordController extends BaseController{ 
	
	   private final static Logger logger=Logger.getLogger(AutoInvestRecordController.class);
	   
	   @Autowired
	   private AutoInvestConfigRecordService autoInvestConfigRecordService;
	   
	   private String path = PropertiesUtil.getValue("portal_path");
	   /**
	    * 
	    * <p>
	    * Description:自动投标规则主界面<br />
	    * </p>
	    * @author yubin
	    * @version 0.1 2015年6月23日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView finalRecordsMain() {
		 
			return new ModelAndView("/borrow/autotender/autoInvesLog/main");
		}
	   /**
	    * 
	    * <p>
	    * Description:查询自动投标规则<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @param rechargeRecordCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchPagefinalList(@ModelAttribute AutoInvestConfigRecordCnd autoInvestConfigRecordCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   
	   	try {
	   		page = autoInvestConfigRecordService.queryListForPage(autoInvestConfigRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/autotender/autoInvesLog/list").addObject("page", page).addObject("portal_path", path);
	   }
	   /**
	    * 
	    * <p>
	    * Description:查询记录详情<br />
	    * </p>
	    * @author yubin
	    * @version 0.1 2015年7月1日
	    * @param borrowId
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/findAutoInvestConfigRecordDetail")
		public  ModelAndView findAutoInvestConfigRecordDetail(@ModelAttribute AutoInvestConfigRecordVo autoInvestConfigReocrdVo) {
		     
			ModelAndView mv = new ModelAndView("/borrow/autotender/autoInvesLog/layer");
			try {
				if (autoInvestConfigReocrdVo != null && autoInvestConfigReocrdVo.getId().intValue() > 0) {
					autoInvestConfigReocrdVo = autoInvestConfigRecordService.queryById(autoInvestConfigReocrdVo.getId());
				}
				mv.addObject("autoInvestConfigReocrdVo",autoInvestConfigReocrdVo);
				 
			} catch (Exception e) {
				stackTraceError(logger, e);
			}
		    return mv;
		}
}
