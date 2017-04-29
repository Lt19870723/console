package com.cxdai.console.statistics.customer.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.service.StockService;
import com.cxdai.console.statistics.customer.vo.StockCnd;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.HttpTookit;

/**
 * 期权排名
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/statistics/stock")
public class StockController extends BaseController {

	private static final Logger logger = Logger.getLogger(StockController.class);
	@Autowired
	private StockService stockService;

	/**
	 * 期权排名页面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView forList() {
		return new ModelAndView("/statistics/customer/stock/main");
	}

	/**
	 * 期权排名列表
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView list(@ModelAttribute StockCnd stockCnd, @PathVariable Integer pageNo) {
		Page page = new Page(pageNo, Constants.CONSOLE_PAGE_SIZE2);
		try {
			page = stockService.pageQuery(stockCnd, page);
		} catch (Exception e) {
			logger.error("查询期权排名错误信息：" + e);
		}
		return new ModelAndView("/statistics/customer/stock/list").addObject("page", page);
	}

	/**
	 * 后台现金行权
	 * <p>
	 */
	@RequestMapping("/exercise/{id}")
	@ResponseBody
	public MessageBox exerciseStock(@PathVariable Integer id, HttpServletRequest request) {
		String msg = null;
		UserVo userVo = getCurrentUserVo();
		try {
			String addip = HttpTookit.getRealIpAddr(request);
			msg = stockService.updateStock(id, addip, userVo);
		} catch (Exception e) {
			msg = "现金行权异常";
			logger.error("现金行权异常", e);
			return MessageBox.build("1", msg);
		}
		if ("".equals(msg)) {
			return MessageBox.build("0", "现金行权成功");
		}
		return MessageBox.build("1", msg);
	}

}
