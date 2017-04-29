package com.cxdai.console.borrow.emerg.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 

import com.cxdai.console.borrow.emerg.service.EmergService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.service.FirstTenderRealService;
 
/**
 * 
 * <p>
 * Description手动直通车投标<br />
 * </p>
 * @title HandleFirstController.java
 * @package com.cxdai.console.borrow.emerg.controller 
 * @author Administrator
 * @version 0.1 2015年6月24日
 */
@Controller
@RequestMapping(value ="/borrow/handleFirst")
public class HandleFirstController extends BaseController {
 
	   private final static Logger logger=Logger.getLogger(HandleFirstController.class);
	   
	   @Autowired
	   private EmergService emergService;
	   @Autowired
	   private FirstTenderRealService firstTenderRealService;
	  
	   /**
	    * 
	    * <p>
	    * Description:手动直通车主页面<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView antifraudRecordsMain() {
		 
			return new ModelAndView("/borrow/emerg/handleFirst/main");
		}
       /**
        * 
        * <p>
        * Description:手动直通车结果集<br />
        * </p>
        * @author Administrator
        * @version 0.1 2015年6月24日
        * @param borrowCnd
        * @param pageNo
        * @return
        * ModelAndView
        */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchPageRechargeList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   	//直通车可用余额
	   	BigDecimal firstUseMoneyTotal = new BigDecimal(0);
	   	try {
	   		page = emergService.queryListForHandFirstTender(borrowCnd,pageNo, Constants.CONSOLE_PAGE_SIZE);
			firstUseMoneyTotal = firstTenderRealService.queryFirstUseMoneyTotal();
			 
	   	} catch (Exception e) {
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/emerg/handleFirst/list")
	   	          .addObject("page", page)
	   	          .addObject("firstUseMoneyTotal",firstUseMoneyTotal.setScale(2));
	   }
	 
	    /**
	     * 
	     * <p>
	     * Description:开始手动直通投标<br />
	     * </p>
	     * @author yubin
	     * @version 0.1 2015年6月27日
	     * @param borrowId
	     * @return
	     * MessageBox
	     */
		@RequestMapping("/handleFirstTender")
		@ResponseBody
		public  MessageBox handleFirstTender(@RequestParam(value = "borrowId", required = false) Integer borrowId) {
			try {
				String msg =emergService.handleFirstTender(borrowId);
				return MessageBox.build("0", msg);  
			} catch (Exception e) {
				stackTraceError(logger, e);
				return MessageBox.build("1", "删除定时发标失败！");  
			}
		}
}
