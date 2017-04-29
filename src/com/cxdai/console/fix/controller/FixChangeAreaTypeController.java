package com.cxdai.console.fix.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixStaticVo;

/**
 * @author WangQianJin
 * @title 新手转普通
 * @date 2015年12月3日
 */
@Controller
@RequestMapping(value = "/fix/changeArea")
public class FixChangeAreaTypeController extends BaseController {

	private static final Logger logger = Logger.getLogger(FixChangeAreaTypeController.class);

	@Autowired
	private FixBorrowService fixBorrowService;
	
	/**
	 * 
	 * <p>
	 * Description:进入新手转普通区页面<br />
	 * </p>
	 * @author WangQianJin
	 * @version 0.1 2015年12月3日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){		
		FixStaticVo fixStaticVo = null;
		try {
			fixStaticVo = fixBorrowService.queryStaticBorrow();
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/fix/changeArea/main").addObject("fixStaticVo", fixStaticVo);		
	}
	/**
	 * 
	 * <p>
	 * Description:查询数据<br />
	 * </p>
	 * @author WangQianJin
	 * @version 0.1 2015年12月3日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchTenderBorrowList(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
    	Page page = null;
		try {
			fixBorrowCnd.setOrderSql(" ORDER BY fixBorrow.ID DESC");
			fixBorrowCnd.setAreaType(1);
			fixBorrowCnd.setStatus("3");
			page = fixBorrowService.queryPageListByCnd(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE2);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/fix/changeArea/list").addObject("page", page);
   }
    /**
     * 
     * <p>
     * Description:新手转普通<br />
     * </p>
     * @author WangQianJin
     * @version 0.1 2015年12月3日
     * @param borrowId
     * @return
     * MessageBox
     */
	@RequestMapping("/changeAreaBorrow")
	@ResponseBody
	public  MessageBox changeAreaBorrow(@RequestParam(value = "id", required = false) Integer fixId) {
		try {
			String msg =fixBorrowService.changeAreaBorrow(fixId);
			return MessageBox.build("0", msg);  
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "转普通区失败!");  
		}
	}
}
