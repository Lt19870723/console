package com.cxdai.console.sycee.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.sycee.entity.MemberAccumulatePointsCnd;
import com.cxdai.console.sycee.service.AccountSyceeService;

/**
 * 元宝-用户元宝明细
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AccountSyceeController.java
 * @package com.cxdai.console.sycee.controller
 * @author huangpin
 * @version 0.1 2015年10月19日
 */
@Controller
@RequestMapping(value = "/account/sycee")
public class AccountSyceeController extends BaseController {

	private static final Logger logger = Logger.getLogger(AccountSyceeController.class);

	@Autowired
	private AccountSyceeService accountSyceeService;

	/**
	 * 用户元宝明细
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月19日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView forAccountSyceeList() {
		return new ModelAndView("/sycee/account/main").addObject("syceeTypes", Dictionary.getValues(1001));
	}

	/**
	 * 用户元宝明细-列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月19日
	 * @param cnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView accountSyceeList(@ModelAttribute MemberAccumulatePointsCnd cnd, @PathVariable Integer pageNo) {
		Page page = new Page(pageNo, Constants.CONSOLE_PAGE_SIZE2);
		try {
			page = accountSyceeService.pageQuery(cnd, page);
		} catch (Exception e) {
			logger.error("查询用户元宝明细异常：" + e);
		}
		return new ModelAndView("/sycee/account/list").addObject("page", page);
	}

}
