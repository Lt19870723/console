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
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
/**
 * 
 * <p>
 * Description:新手转普通业务<br />
 * </p>
 * @title ChangeAreaTypeController.java
 * @package com.cxdai.console.borrow.emerg.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Controller
@RequestMapping(value="/borrow/emerg/changeArea")
public class ChangeAreaTypeController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(ChangeAreaTypeController.class);
	@Autowired
	private BorrowManageService borrowManageService;
	@Autowired
	private EmergService emergService;
	/**
	 * 
	 * <p>
	 * Description:进入新手转普通区页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		
		return  new ModelAndView("/borrow/emerg/changeArea/main");
	}
	/**
	 * 
	 * <p>
	 * Description:查询数据<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchTenderBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	  Page page = null;   
	try {
		    borrowCnd.setAreaType("1");
	   		page =borrowManageService.queryTenderBorrowList(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	} catch (Exception e) {
	   		 
	   		logger.error(e);
	}
	   	return new ModelAndView("/borrow/emerg/changeArea/list").addObject("page", page);
   }
    /**
     * 
     * <p>
     * Description:撤标<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月29日
     * @param borrowId
     * @return
     * MessageBox
     */
	@RequestMapping("/changeAreaBorrow")
	@ResponseBody
	public  MessageBox changeAreaBorrow(@RequestParam(value = "id", required = false) Integer borrowId) {
		try {
			String msg =emergService.changeAreaBorrow(borrowId);
			return MessageBox.build("0", msg);  
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "转普通区失败!");  
		}
	}
}
