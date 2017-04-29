package com.cxdai.console.curaccount.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.service.CurInService;
import com.cxdai.console.curaccount.vo.CurInCnd;

/**
 * <p>
 * Description:活期宝 - 活期宝异常转入记录<br />
 * </p>
 * 
 * @title CurInCalInterestDayErrorController.java
 * @package com.cxdai.console.curaccount.controller
 * @author tongjuxing
 * @version 0.1 2015年7月3日
 */
@Controller
@RequestMapping(value = "/curaccount/curincalinterestdayerror")
public class CurInCalInterestDayErrorController extends BaseController {
	private static final Logger logger = Logger.getLogger(CurInCalInterestDayErrorController.class);
	
	
	@Autowired
	private CurInService curInService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/curaccount/curincalinterestdayerror/main");
	}
	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute CurInCnd curInCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = curInService.queryCurInListForCalInterestDayErrorList(curInCnd, pageSize, pageNo);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/curaccount/curincalinterestdayerror/list").addObject("page", page);
	}
	
}