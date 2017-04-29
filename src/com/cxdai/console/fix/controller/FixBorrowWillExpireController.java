package com.cxdai.console.fix.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.service.FixTenderRecordService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixStaticVo;
import com.cxdai.console.fix.vo.TenderRecordCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:定期宝-即将到期定期宝<br />
 * </p>
 * @title FixBorrowWillExpireController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/fix/fixBorrowWillExpire")
public class FixBorrowWillExpireController extends BaseController {
	private static final Logger logger = Logger.getLogger(FixBorrowWillExpireController.class);

	@Autowired
	private FixBorrowService fixBorrowService;

	@Autowired
	private FixTenderRecordService fixTenderRecordService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
		fixBorrowCnd.setBeginDate(new Date());
		fixBorrowCnd.setEndDate(DateUtils.dayOffset(new Date(), 7));
		return forword("/fix/fixBorrowWillExpire/main", "fixBorrowCnd", fixBorrowCnd);
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		FixStaticVo fixStatic = null;
		try {
			page = fixBorrowService.queryWillExpireFixBorrowList(fixBorrowCnd, pageNo, pageSize);
			// 到期资金总额，统计选取时间内将到期资金的本金与利息总和
			fixStatic = fixBorrowService.sumWillExpireFixBorrow(fixBorrowCnd);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/fix/fixBorrowWillExpire/list").addObject("page", page).addObject("fixStatic", fixStatic);
	}

	/**
	 * <p>
	 * Description:跳转到新增、修改、查看窗口<br />
	 * </p>
	 * @author tongjuxing
	 * @version 0.1 2015年6月30日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		return forword("/fix/fixBorrowWillExpire/detail", "id", id);
	}

	/**
	 * 用户定期宝弹出页面
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日 void
	 */
	@RequestMapping(value = "/detaillist/{pageNo}")
	public ModelAndView detaillist(@PathVariable("pageNo") Integer pageNo, @RequestParam(value = "id") Integer id) {
		Page page = new Page();
		try {
			TenderRecordCnd tenderRecordCnd = new TenderRecordCnd();
			tenderRecordCnd.setFixBorrowId(id);
			page = fixTenderRecordService.queryTenderRecordVoList(tenderRecordCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error("定期宝投标日志列表查询出错", e);
		}
		return forword("/fix/fixBorrowWillExpire/detaillist").addObject("page", page);
	}

	/**
	 * <p>
	 * Description:一键发宝 <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月23日 void
	 */
	@RequestMapping(value = "/quickPub/{id}")
	public ModelAndView toAddModifyQuickPub(@PathVariable("id") Integer id) {
		FixBorrowVo fixBorrowVo = new FixBorrowVo();
		try {
			FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
			fixBorrowCnd.setId(id);
			fixBorrowVo = fixBorrowService.getFixBorrowById(fixBorrowCnd);
			// 为界面绑定处理
			fixBorrowVo.setPublishTime(fixBorrowVo.getDefaultPubTime());
			// 处理有效期限
			if (null != fixBorrowVo.getValidTime()) {
				int validTime = fixBorrowVo.getValidTime().intValue();
				int a = 24 * 60;
				int b = 60;
				if ((validTime % a) == 0) {
					fixBorrowVo.setValidTimeStyle(1); // 按天
					fixBorrowVo.setValidTime(validTime / a);
				} else {
					if ((validTime % b) == 0) {
						fixBorrowVo.setValidTimeStyle(2); // 按小时
						fixBorrowVo.setValidTime(validTime / b);
					} else {
						fixBorrowVo.setValidTimeStyle(3); // 按分钟
					}
				}
			}
			// 处理金额为万元
			if (null != fixBorrowVo.getPlanAccount() && null != fixBorrowVo.getMostAccount() && null != fixBorrowVo.getLowestAccount()) {
				fixBorrowVo.setPlanAccount(new BigDecimal(fixBorrowVo.getPlanAccountStr()));
				fixBorrowVo.setMostAccount(new BigDecimal(fixBorrowVo.getMostAccountStr()));
				fixBorrowVo.setLowestAccount(fixBorrowVo.getLowestAccount().divide(new BigDecimal(10000)));
			}

		} catch (Exception e) {
			logger.error("一键发宝报错", e);
		}

		Date lastEndTime = DateUtils.hoursOffset(new Date(), 1);
		return forword("/fix/fixBorrowWillExpire/quickPub").addObject("fixBorrowVo", fixBorrowVo).addObject("lastEndTime", lastEndTime);
	}

	/**
	 * 保存定期宝信息
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日 void
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute FixBorrowVo fixBorrowVo) {
		String resultMsg = "success";
		try {

			// 执行新增方法，反之则调用更新方法
			if (null != fixBorrowVo.getId()) {
				// 执行更新方法
				fixBorrowVo.setPlanAccount(fixBorrowVo.getPlanAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setLowestAccount(new BigDecimal(100));
				fixBorrowVo.setMostAccount(fixBorrowVo.getMostAccount().multiply(new BigDecimal(10000)));
				/*
				 * // 清除密码 if (null == fixBorrowVo.getPasswordSource() || "".equals(fixBorrowVo.getPasswordSource())) { fixBorrowVo.setCleanPassword("true"); }
				 */
				fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				if (fixBorrowVo.getBidPassword() == null || "".equals(fixBorrowVo.getBidPassword())) {
					fixBorrowVo.setCleanPassword("清空密码");
				}
				resultMsg = fixBorrowService.updateFixBorrow(fixBorrowVo);
			} else {
				fixBorrowVo.setUserId(currentUser().getUserId());
				// 将万元转换成具体的金额
				fixBorrowVo.setPlanAccount(fixBorrowVo.getPlanAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setLowestAccount(new BigDecimal(100));
				fixBorrowVo.setMostAccount(fixBorrowVo.getMostAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				// 密码加密
				if (fixBorrowVo.getBidPassword() != null && !"".equals(fixBorrowVo.getBidPassword())) {
					fixBorrowVo.setBidPassword(MD5.toMD5(fixBorrowVo.getBidPassword()));
				}else{
					fixBorrowVo.setCleanPassword("清空密码");
				}
				resultMsg = fixBorrowService.insertFixBorrow(fixBorrowVo);
			}

		} catch (AppException ae) {
			logger.error("定期宝保存报错", ae);
			resultMsg = ae.getMessage();
		} catch (Exception e) {
			logger.error("定期宝保存报错", e);
			resultMsg = "系统异常，请稍候重试或联系管理员！";
		}

		if ("success".equals(resultMsg)) {
			return MessageBox.build("0", "保存成功");
		} else {
			return MessageBox.build("1", resultMsg);
		}

	}

}