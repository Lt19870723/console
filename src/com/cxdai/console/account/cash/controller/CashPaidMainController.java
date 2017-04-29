package com.cxdai.console.account.cash.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.cash.service.CashRecordService;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.HttpTookit;

/**
 * 提现打款成功
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/account/cashpaidmain")
public class CashPaidMainController extends BaseController {

	private static final Logger logger = Logger.getLogger(CashPaidMainController.class);

	@Autowired
	private CashRecordService cashRecordService;

	@RequestMapping("/main")
	public ModelAndView forCashPaidMain() {
		return new ModelAndView("/account/cash/query/cashpaid/main");
	}

	/**
	 * 提现打款成功列表
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageCashRecordPaidList(@ModelAttribute CashRecordCnd cashRecordCnd, @PathVariable Integer pageNo, HttpServletRequest request) {
		Page page = null;
		try {
			cashRecordCnd.setCashColumn(4);
//            cashRecordCnd.setIsCustody(0);
			cashRecordCnd.setBeginTime(request.getParameter("beginTime"));
			cashRecordCnd.setEndTime(request.getParameter("endTime"));
			cashRecordCnd.setStatus(String.valueOf(Constants.CASH_RECORD_STATUS_CASH_FINISH));
			page = cashRecordService.queryPageListByCnd(cashRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE4);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提现打款错误信息：" + e);
		}
		return new ModelAndView("/account/cash/query/cashpaid/list").addObject("page", page).addObject("isCustody",cashRecordCnd.getIsCustody());
		
	}

	/**
	 * 提现作废
	 */
	@RequestMapping("/invalidcash/{id}")
	@ResponseBody
	public MessageBox invalidCash(@PathVariable Integer id, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		String result = null;
		try {
			result = cashRecordService.saveInvalidCash(id, userVo, HttpTookit.getRealIpAddr(request));
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，提现作废失败，请稍后再试!!");
		}
		return new MessageBox("0", result);
	}

}
