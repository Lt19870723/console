package com.cxdai.console.account.recharge.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;

/**
 * 
 * <p>
 * Description:资金管理 - 充值管理 - 在线充值补单<br />
 * </p>
 * 
 * @title AdditionalOrderController.java
 * @package com.cxdai.console.account.recharge.controller
 * @author hujianpan
 * @version 0.1 2015年4月7日
 */
@Controller
@RequestMapping(value = "/account/additionalorder")
public class AdditionalOrderController extends BaseController {
	private final static Logger logger = Logger.getLogger(AdditionalOrderController.class);

	/**
	 * <p>
	 * Description:在线充值补单主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年4月7日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView AdditionalOrderMain() {
		return new ModelAndView("/account/recharge/additional/main");
	}
}
