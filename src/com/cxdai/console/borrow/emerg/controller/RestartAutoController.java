package com.cxdai.console.borrow.emerg.controller;

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
/**
 * 
 * <p>
 * Description:自动投标恢复<br />
 * </p>
 * @title RestartAutoController.java
 * @package com.cxdai.console.borrow.emerg.controller 
 * @author Administrator
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/borrow/restartAuto")
public class RestartAutoController extends BaseController{
	  private final static Logger logger=Logger.getLogger(RestartAutoController.class);
	   
	   @Autowired
	   private EmergService emergService;
	   /**
	    * 
	    * <p>
	    * Description:继续自动投标的借款标主页面<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView restartAutoRecordsMain() {
		 
			return new ModelAndView("/borrow/emerg/restartAuto/main");
		}
       /**
        * 
        * <p>
        * Description:查询需要继续自动投标的借款标<br />
        * </p>
        * @author Administrator
        * @version 0.1 2015年6月25日
        * @param borrowCnd
        * @param pageNo
        * @return
        * ModelAndView
        */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchrestartAutoList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   	try {
	   		page = emergService.queryBorrowListForRestartAutoTender(borrowCnd, pageNo,Constants.CONSOLE_PAGE_SIZE);
			 
	   	} catch (Exception e) {
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/emerg/restartAuto/list").addObject("page", page);
	   }
	   /**
	     * 
	     * <p>
	     * Description:继续自动投标<br />
	     * </p>
	     * @author yubin
	     * @version 0.1 2015年6月27日
	     * @param borrowId
	     * @return
	     * MessageBox
	     */
		@RequestMapping("/restartAutoTender")
		@ResponseBody
		public  MessageBox restartAutoTender(@RequestParam(value = "id", required = false) Integer borrowId) {
			try {
				String msg =emergService.restartAutoTender(borrowId);
				return MessageBox.build("0", msg);  
			} catch (Exception e) {
				stackTraceError(logger, e);
				return MessageBox.build("1", "继续自动投标出错！");  
			}
		}
}
