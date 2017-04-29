package com.cxdai.console.borrow.autotender.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.recharge.service.AutoInvestConfigService;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
/**
 * 
 * <p>
 * Description:自动投标规则业务<br />
 * </p>
 * @title AutoInvestController.java
 * @package com.cxdai.console.borrow.autotender.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Controller
@RequestMapping(value = "/borrow/autotender/autoInvest")
public class AutoInvestController extends BaseController{ 
	
	   private final static Logger logger=Logger.getLogger(AutoInvestController.class);
	   
	   @Autowired
	   private AutoInvestConfigService autoInvestConfigService;
	   
	   
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
		 
			return new ModelAndView("/borrow/autotender/autoInvest/main");
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
	   public ModelAndView searchPagefinalList(@ModelAttribute AutoInvestConfigCnd autoInvestConfigCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   
	   	try {
	   		page = autoInvestConfigService.queryListForPage(autoInvestConfigCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/autotender/autoInvest/list").addObject("page", page);
	   }
}
