package com.cxdai.console.borrow.autoInvestConfig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.autoInvestConfig.service.FixAutoInvestService;
import com.cxdai.console.borrow.autoInvestConfig.vo.FixAutoInvestCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
/**
 * 
 * <p>
 * Description:自动投宝规则业务<br />
 * </p>
 * @title FixAutoInvestController.java
 * @package com.cxdai.console.borrow.autoInvestConfig.controller 
 * @author liutao
 * @date 2015年11月20日
 */
@Controller
@RequestMapping(value = "/borrow/autoInvestConfig/fixautoInvest")
public class FixAutoInvestController extends BaseController{ 
	
	   private final static Logger logger=Logger.getLogger(FixAutoInvestController.class);
	   
	   @Autowired
	   private FixAutoInvestService fixAutoInvestService;
	   
	   
	   /**
	    * 
	    * <p>
	    * Description:自动投宝规则主界面<br />
	    * </p>
	    * @author liutao
	    * @date 2015年11月20日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView finalFixRecordsMain() {
			return new ModelAndView("/borrow/autoInvestConfig/fixAutoInvest/main");
		}
	   /**
	    * 
	    * <p>
	    * Description:查询自动投宝规则<br />
	    * </p>
	    * @author liutao
	    * @date 2015年11月20日
	    * @param FixAutoInvestCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchPagefinalFixList(@ModelAttribute FixAutoInvestCnd fixAutoInvestCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   	try {
	   		page = fixAutoInvestService.queryListForPage(fixAutoInvestCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   		logger.error("查询自动投宝规则错误信息："+e);
	   	}
	   	return new ModelAndView("/borrow/autoInvestConfig/fixAutoInvest/list").addObject("page", page);
	   }
}
