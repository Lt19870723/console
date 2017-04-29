package com.cxdai.console.fix.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixTenderRecordService;
import com.cxdai.console.fix.vo.TenderRecordCnd;
/**
 * 
 * <p>
 * Description:定期宝日志<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/fixTenderUser")
public class FixTenderUserController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(FixTenderUserController.class);
    
	@Autowired
	private  FixTenderRecordService fixTenderRecordService;
	/**
	 * 
	 * <p>
	 * Description:进入定期宝业务<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/fixTenderUser/main");
	}
	/**
	 * 
	 * <p>
	 * Description:定期宝业务查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchTotalForFixTenderUserList(@ModelAttribute TenderRecordCnd tenderRecordCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	 
	try {
		page = fixTenderRecordService.queryTenderRecordVoList(tenderRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE2);
	} catch (Exception e) {
		
		logger.error("定期宝投标日志列表查询出错", e);
	}
	  return new ModelAndView("/fix/fixTenderUser/list").addObject("page", page);
   }
}
