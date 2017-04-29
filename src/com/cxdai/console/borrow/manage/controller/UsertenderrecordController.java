package com.cxdai.console.borrow.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.emerg.vo.UserTenderRecordCnd;
import com.cxdai.console.borrow.manage.service.TenderRecordService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:用户投标记录业务<br />
 * </p>
 * @title UsertenderrecordController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Controller
@RequestMapping(value = "/borrow/manage/usertenderrecord")
public class UsertenderrecordController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(UsertenderrecordController.class);
	@Autowired
	private TenderRecordService tenderRecordService;
	private String path=PropertiesUtil.getValue("portal_path");
	/**
	 * 
	 * <p>
	 * Description:进入借款标协议查<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		
		return  new ModelAndView("/borrow/manage/usertenderrecord/main");
	}
	/**
	 * 
	 * <p>
	 * Description:分页查询还款中的借款标集合 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchUserTenderRecord(@ModelAttribute UserTenderRecordCnd userCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	 
	try {
	   		page =tenderRecordService.searchUserTenderRecord(userCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);	 
	} catch (Exception e) {
		
	   		logger.error(e);
	}
	  return new ModelAndView("/borrow/manage/usertenderrecord/list").addObject("page", page).addObject("portal_path", path);
   }
}
