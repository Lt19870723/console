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
 * Description:资金管理 - 充值管理 - 充值记录初审<br />
 * </p>
 * 
 * @title FirstAuditController.java
 * @package com.cxdai.console.account.recharge.controller
 * @author hujianpan
 * @version 0.1 2015年3月17日
 */
@Controller
@RequestMapping(value = "/account/firstaudit")
public class FirstAuditController extends BaseController {
	private final static Logger logger = Logger.getLogger(FirstAuditController.class);
	@Autowired
	private RechargeRecordService rechargeRecordService;

	/**
	 * <p>
	 * Description:初审主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView firstAuditMain() {
		return new ModelAndView("/account/recharge/firstaudit/main");
	}

	/**
	 * <p>
	 * Description:查询充值记录初审列表<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月16日 void
	 */
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchPageRechargeFirstApprList(@ModelAttribute RechargeRecordCnd rechargeRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {

			// 充值审核中
			rechargeRecordCnd.setStatus(String.valueOf(Constants.RECHARGE_STATUS_APPROVING));
			// 线下充值
			rechargeRecordCnd.setType(String.valueOf(Constants.RECHARGE_TYPE_OFFLINE));

			page = rechargeRecordService.queryPageListByRechargeRecordCnd(rechargeRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/account/recharge/firstaudit/list").addObject("page", page);
	}

	/**
	 * 根据ID查询待审记录详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public ModelAndView showAuditInfo(@PathVariable Integer id) {
		RechargeRecordCnd rechargeRecordCnd = new RechargeRecordCnd();
		rechargeRecordCnd.setId(String.valueOf(id));
		RechargeRecordVo rechargeRecordVo = null;
		try {
			rechargeRecordVo = rechargeRecordService.queryRechargeRecordById(rechargeRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("/account/recharge/firstaudit/layer").addObject("rechargeRecordVo", rechargeRecordVo);
	}

	/**
	 * <p>
	 * Description:初审通过<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordVo
	 * @return MessageBox
	 */
	@RequestMapping("/pass")
	@ResponseBody
	public MessageBox firstAuditPass(@ModelAttribute RechargeRecordVo rechargeRecordVo) {
		UserVo userVo = getCurrentUserVo();
		try {
			rechargeRecordService.saveApprovePass(rechargeRecordVo, userVo);
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("1", e.getMessage());
		}
		return new MessageBox("0", "审核成功！");
	}

	/**
	 * <p>
	 * Description:初审不通过<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordVo
	 * @param request
	 * @return MessageBox
	 */
	@RequestMapping("/reject")
	@ResponseBody
	public MessageBox firstAuditReject(@ModelAttribute RechargeRecordVo rechargeRecordVo, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		try {
			rechargeRecordService.saveApproveReject(rechargeRecordVo, userVo, request);
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("1", e.getMessage());
		}
		return new MessageBox("0", "审核成功！");
	}

}
