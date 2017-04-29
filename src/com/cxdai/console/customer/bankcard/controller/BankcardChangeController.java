package com.cxdai.console.customer.bankcard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.BankcardChange;
import com.cxdai.console.customer.bankcard.entity.BankcardChangeCnd;
import com.cxdai.console.customer.bankcard.entity.BankcardClick;
import com.cxdai.console.customer.bankcard.entity.BankcardClickCnd;
import com.cxdai.console.customer.bankcard.entity.BankcardPic;
import com.cxdai.console.customer.bankcard.entity.BankcardTimesCnd;
import com.cxdai.console.customer.bankcard.service.BankcardChangeService;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;

/**
 * 银行卡更换
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title BankcardChangeAction.java
 * @package com.cxdai.console.bank.action
 * @author huangpin
 * @version 0.1 2015年3月25日
 */
@Controller
@RequestMapping(value = "/bankCardChange")
public class BankcardChangeController extends BaseController {

	private static final Logger logger = Logger.getLogger(BankcardChangeController.class);

	@Autowired
	private BankcardChangeService changeService;

	@RequestMapping(value = "/toTimesMain")
	@ResponseBody
	public ModelAndView toTimesMain() {
		return forword("/customer/bankcard/timesMain");
	}

	@RequestMapping(value = "/serachAll/{pageNo}")
	@ResponseBody
	public ModelAndView serachAll(@ModelAttribute BankcardTimesCnd bankcardTimesCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, 10);
		try {
			page = changeService.pageQueryTimes(bankcardTimesCnd, page);
		} catch (Exception e) {
			logger.error("换卡信息统计列表获取异常", e);
		}
		return forword("/customer/bankcard/timesList").addObject("page", page);
	}

	/**
	 * 进入审核页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toApproveMain")
	@ResponseBody
	public ModelAndView toApproveMain() {
		return forword("/customer/bankcard/approveMain");
	}

	/**
	 * 进入复审页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRecheckApproveMain")
	public ModelAndView toRecheckApproveMain() {
		return forword("/customer/bankcard/recheckapprovemain");
	}

	/**
	 * 进入换卡审核日志页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toApproveLogMain")
	public ModelAndView toApproveLogMain() {
		return forword("/customer/bankcard/approvelogmain");
	}

	/**
	 * 审核信息列表
	 * 
	 * @param bankcardChangeCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/serachApproveAll/{pageNo}")
	@ResponseBody
	public ModelAndView serachApproveAll(@ModelAttribute BankcardChangeCnd bankcardChangeCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, 10);
		try {
			page = changeService.pageQuery(bankcardChangeCnd, page);
		} catch (Exception e) {
			logger.error("换卡审核列表获取异常", e);
		}
		return forword("/customer/bankcard/approveList").addObject("page", page);
	}

	/**
	 * 复审信息列表
	 * 
	 * @param bankcardChangeCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/serachRecheckApproveAll/{pageNo}")
	@ResponseBody
	public ModelAndView serachRecheckApproveAll(@ModelAttribute BankcardChangeCnd bankcardChangeCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, 10);
		try {
			page = changeService.pageQuery(bankcardChangeCnd, page);
		} catch (Exception e) {
			logger.error("换卡复审列表获取异常", e);
		}
		return forword("/customer/bankcard/recheckapprovelist").addObject("page", page);
	}

	/**
	 * 换卡审核日志列表
	 * 
	 * @param bankcardChangeCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/serachApproveLogAll/{pageNo}")
	public ModelAndView serachApproveLogAll(@ModelAttribute BankcardChangeCnd bankcardChangeCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, 10);
		try {
			page = changeService.pageQuery(bankcardChangeCnd, page);
		} catch (Exception e) {
			logger.error("换卡审核列表获取异常", e);
		}
		return forword("/customer/bankcard/approveloglist").addObject("page", page);
	}

	/**
	 * 审核详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/toApproveBank/{id}")
	@ResponseBody
	public ModelAndView toApproveBank(@PathVariable Integer id) {
		BankcardChange change = new BankcardChange();
		List<BankcardPic> pics = new ArrayList<BankcardPic>();
		String portPath = PropertiesUtil.getValue("portal_path");
		try {
			change = changeService.detail(id);
			pics = changeService.getPics(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/customer/bankcard/approvelayer").addObject("change", change).addObject("pics", pics).addObject("portPath", portPath);
	}

	/**
	 * 复审详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/toRecheckApproveBank/{id}")
	@ResponseBody
	public ModelAndView toRecheckApproveBank(@PathVariable Integer id) {
		BankcardChange change = new BankcardChange();
		List<BankcardPic> pics = new ArrayList<BankcardPic>();
		String portPath = PropertiesUtil.getValue("portal_path");
		try {
			change = changeService.detail(id);
			pics = changeService.getPics(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/customer/bankcard/recheckapprovelayer").addObject("change", change).addObject("pics", pics).addObject("portPath", portPath);
	}

	/**
	 * 点击申请列表
	 * 
	 * @return
	 */
	@RequestMapping("/clickLogs/{userId}")
	public ModelAndView clickLogs(@ModelAttribute BankcardClickCnd clickCnd, @PathVariable Integer userId, HttpServletRequest request) {
		List<BankcardClick> clicks = new ArrayList<BankcardClick>();
		try {
			if (!StringUtils.isEmpty(request.getParameter("beginTimeTotal"))) {
				clickCnd.setBeginTime(DateUtils.parse(request.getParameter("beginTimeTotal"), DateUtils.YMD_DASH));
			}
			if (!StringUtils.isEmpty(request.getParameter("endTimeTotal"))) {
				clickCnd.setEndTime(DateUtils.parse(request.getParameter("endTimeTotal"), DateUtils.YMD_DASH));
			}
			clickCnd.setUserId(userId);
			clicks = changeService.getClicks(clickCnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/customer/bankcard/checklayer").addObject("clicks", clicks);
	}

	/**
	 * 审批更换
	 * 
	 * @return
	 */
	@RequestMapping("/doFirstApprove/{id}/{userId}")
	@ResponseBody
	public MessageBox doFirstApprove(@ModelAttribute BankcardChange change, @PathVariable Integer id, @PathVariable Integer userId, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		String result = null;
		try {
			change.setId(id);
			change.setUserId(userId);
			result = changeService.updateFirstApprove(change, userVo, HttpTookit.getRealIpAddr(request));
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，审批失败，请稍后再试!!");
		}
		if (result == null) {
			result = "操作成功!";
		} else {
			return new MessageBox("2", result);
		}
		return new MessageBox("0", result);
	}

	/**
	 * 复审更换
	 * 
	 * @param change
	 * @param id
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/doRecheckApprove/{id}/{userId}")
	@ResponseBody
	public MessageBox doRecheckApprove(@ModelAttribute BankcardChange change, @PathVariable Integer id, @PathVariable Integer userId, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		String result = null;
		try {
			change.setId(id);
			change.setUserId(userId);
//			if ("".equals(change.getNewBranchNo())) {
//				change.setNewBranchNo(null);
//			}
			result = changeService.updateRecheck(change, userVo, HttpTookit.getRealIpAddr(request));
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，复审失败，请稍后再试!!");
		}
		if (result == null) {
			result = "操作成功!";
		} else {
			return new MessageBox("2", result);
		}
		return new MessageBox("0", result);
	}
	
	@RequestMapping(value = "/toVerify")
	@ResponseBody
	public ModelAndView toVerify() {
		return forword("/customer/bankcard/verifyMain");
	}
	@RequestMapping(value = "/serachVerify/{pageNo}")
	@ResponseBody
	public ModelAndView serachVerify(@ModelAttribute BankcardTimesCnd bankcardTimesCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, 10);
		try {
			page = changeService.pageQueryVerifyInfo(bankcardTimesCnd, page);
		} catch (Exception e) {
			logger.error("银行四要素验证信息统计列表获取异常", e);
		}
		return forword("/customer/bankcard/verifyList").addObject("page", page);
	}
	@RequestMapping("/delVerify")
	@ResponseBody
	public  MessageBox delVerify(@RequestParam(value = "id", required = false) Integer id) {
		try {
			String msg = changeService.delVerify(id,currentUser().getUserId());
			return MessageBox.build("0", msg);  
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "删除四要素验证信息失败！");  
		}
	}
}
