package com.cxdai.console.borrow.approve.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowAuditHistoryCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:借款标日志记录<br />
 * </p>
 * @title AuditHistoryController.java
 * @package com.cxdai.console.borrow.approve.controller 
 * @author yubin
 * @version 0.1 2015年6月26日
 */
@Controller
@RequestMapping(value = "/approve/auditHistory")
public class AuditHistoryController extends BaseController {
     
	private final static Logger logger=Logger.getLogger(BorrowController.class);
	private String path=PropertiesUtil.getValue("portal_path");
	@Autowired
	private BorrowService borrowService;
	     
	   /**
	    * 
	    * <p>
	    * Description:借款标终审主界面<br />
	    * </p>
	    * @author yubin
	    * @version 0.1 2015年6月23日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView auditHistoryRecordsMain() {
		 
			return new ModelAndView("/borrow/approve/auditHistory/main");
		}
	   /**
	    * 
	    * <p>
	    * Description:查询终审列表<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @param rechargeRecordCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchPageauditHistoryList(@ModelAttribute BorrowAuditHistoryCnd borrowAuditHistoryCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	    
	   	try {
	   	 
	   		page =borrowService.searchPageborrowAuditHistoryList(borrowAuditHistoryCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/approve/auditHistory/list").addObject("page", page).addObject("portal_path", path);
	   }
	 
}
