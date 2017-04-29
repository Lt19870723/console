package com.cxdai.console.curaccount.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.curaccount.service.CurAccountReportService;
import com.cxdai.console.curaccount.vo.CurAccountRepportVo;

/**
 * <p>
 * Description:活期宝 - 活期宝账户资金对账<br />
 * </p>
 * 
 * @title CurAccountTotalReportController.java
 * @package com.cxdai.console.curaccount.controller
 * @author tongjuxing
 * @version 0.1 2015年7月3日
 */
@Controller
@RequestMapping(value = "/curaccount/curaccounttotalreport")
public class CurAccountTotalReportController extends BaseController {
	private static final Logger logger = Logger.getLogger(CurAccountTotalReportController.class);
	
	@Autowired
	private CurAccountReportService curAccountReportService;
	

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		CurAccountRepportVo curAccountRepportVo = new CurAccountRepportVo();
		try {
			curAccountRepportVo = curAccountReportService.queryCurAccountTotalReport();
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/curaccount/curaccounttotalreport/main","curAccountRepportVo",curAccountRepportVo);
	}
	
}