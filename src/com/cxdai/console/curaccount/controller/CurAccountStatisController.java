package com.cxdai.console.curaccount.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.curaccount.service.CurAccountLogService;
import com.cxdai.console.curaccount.vo.CurAccountLogCnd;

/**
 * <p>
 * Description:活期宝 - 活期宝统计<br />
 * </p>
 * 
 * @title CurAccountStatisController.java
 * @package com.cxdai.console.curaccount.controller
 * @author tongjuxing
 * @version 0.1 2015年7月3日
 */
@Controller
@RequestMapping(value = "/curaccount/curaccountstatis")
public class CurAccountStatisController extends BaseController {
	private static final Logger logger = Logger.getLogger(CurAccountStatisController.class);
	
	@Autowired
	private CurAccountLogService curAccountLogService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/curaccount/curaccountstatis/main");
	}
	
	@RequestMapping(value = "/sum")
	@ResponseBody
	public Map<String,Object> querySum(@ModelAttribute CurAccountLogCnd curAccountLogCnd) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("bdSumMoneySy", 0);
		try {
			
			BigDecimal bdSumMoneyIn = BigDecimal.ZERO;
			BigDecimal bdSumMoneyOut = BigDecimal.ZERO;
			BigDecimal bdSumMoneySy = BigDecimal.ZERO;
			bdSumMoneyIn = curAccountLogService.querySumMoneyIn(curAccountLogCnd);
			bdSumMoneyOut = curAccountLogService.querySumMoneyOut(curAccountLogCnd);
			bdSumMoneySy = bdSumMoneyIn.subtract(bdSumMoneyOut);
			result.put("bdSumMoneySy", bdSumMoneySy);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
}