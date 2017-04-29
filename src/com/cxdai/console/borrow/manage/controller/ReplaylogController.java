 
package com.cxdai.console.borrow.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:借款者还款操作日志业务<br />
 * </p>
 * @title ReplaylogController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "manage/forborrowrechangeandreplaylogList")
public class ReplaylogController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(ReplaylogController.class);
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	
	private String path=PropertiesUtil.getValue("portal_path");
    /**
     * 
     * <p>
     * Description:进入获取还款操作日志列表<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月28日
     * @return
     * ModelAndView
     */
	@RequestMapping("/main")
	public ModelAndView main(){
		
		return  new ModelAndView("/borrow/manage/replaylogBorrow/main");
	}
	/**
	 * 
	 * <p>
	 * Description:获取还款操作日志<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchTenderBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	  Page page = null;   
	  	try {
	   		page  = bRepaymentRecordService.selectRepayingLog(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	} catch (Exception e) {
	   		logger.error("获取还款操作日志:",e);
	}
	   	return new ModelAndView("/borrow/manage/replaylogBorrow/list").addObject("page", page).addObject("portal_path", path);
   }
}
