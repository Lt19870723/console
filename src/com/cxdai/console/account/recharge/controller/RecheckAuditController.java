package com.cxdai.console.account.recharge.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.recharge.service.RechargeRecordService;
import com.cxdai.console.account.recharge.vo.RechargeRecordCnd;
import com.cxdai.console.account.recharge.vo.RechargeRecordVo;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.vo.UserVo;

/**
 * <p>
 * Description:资金管理 - 充值管理 - 充值记录复审<br />
 * </p>
 * 
 * @title RecheckAuditController.java
 * @package com.cxdai.console.account.recharge.controller
 * @author hujianpan
 * @version 0.1 2015年3月17日
 */
@Controller
@RequestMapping(value = "/account/recheckaudit")
public class RecheckAuditController extends BaseController {
	private final static Logger logger = Logger.getLogger(RecheckAuditController.class);

	@Autowired
	private RechargeRecordService rechargeRecordService;

	/**
	 * <p>
	 * Description:复审主页<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView recheckAuditMain() {
		return new ModelAndView("/account/recharge/recheck/main");
	}

	/**
	 * <p>
	 * Description:查看待审记录详情<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/detail/{id}")
	public ModelAndView showAuditInfo(@PathVariable Integer id) {
		RechargeRecordCnd rechargeRecordCnd = new RechargeRecordCnd();
		rechargeRecordCnd.setId(String.valueOf(id));
		RechargeRecordVo rechargeRecordVo = null;
		try {
			rechargeRecordVo = rechargeRecordService.queryRechargeRecordById(rechargeRecordCnd);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/account/recharge/recheck/layer").addObject("rechargeRecordVo", rechargeRecordVo);
	}

	/**
	 * <p>
	 * Description:查询充值记录终审列表<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月4日 void
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageRechargeFinallyApprList(@ModelAttribute RechargeRecordCnd rechargeRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			// 充值审核中
			rechargeRecordCnd.setStatus(String.valueOf(Constants.RECHARGE_STATUS_FIRST_APPROVE_SUCCESS));
			// 线下充值
			rechargeRecordCnd.setType(String.valueOf(Constants.RECHARGE_TYPE_OFFLINE));
			page = rechargeRecordService.queryPageListByCnd(rechargeRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/account/recharge/recheck/list").addObject("page", page);
	}

	/**
	 * <p>
	 * Description:充值终审通过<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月6日 void
	 */
	@RequestMapping("/pass")
	@ResponseBody
	public MessageBox approveFinallyPass(@ModelAttribute RechargeRecordVo rechargeRecordVo, HttpServletRequest request) {
		try {
			UserVo userVo = getCurrentUserVo();
			rechargeRecordService.saveApproveFinallyPass(rechargeRecordVo, userVo, request);
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("1", e.getMessage());
		}
		return new MessageBox("0", "审核成功！");
	}

	/**
	 * <p>
	 * Description:充值终审不通过<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月6日 void
	 */
	@RequestMapping("/reject")
	@ResponseBody
	public MessageBox approveFinallyReject(@ModelAttribute RechargeRecordVo rechargeRecordVo, HttpServletRequest request) {
		try {
			UserVo userVo = getCurrentUserVo();
			rechargeRecordService.saveApproveFinallyReject(rechargeRecordVo, userVo, request);
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("1", e.getMessage());
		}
		return new MessageBox("0", "审核成功！");
	}
}
