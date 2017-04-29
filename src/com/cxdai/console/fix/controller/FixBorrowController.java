package com.cxdai.console.fix.controller;

import java.util.List;

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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixOperationLogVo;
import com.cxdai.console.fix.vo.FixStaticVo;
import com.cxdai.console.system.service.ConfigurationService;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:定期宝业务<br />
 * </p>
 * 
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/fixborrow")
public class FixBorrowController extends BaseController {

	private static final Logger logger = Logger.getLogger(FixBorrowController.class);

	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private ConfigurationService configurationService;

	/**
	 * <p>
	 * Description:进入定期宝业务<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("/fix/fixborrow/main");
		FixStaticVo fixStaticVo = null;
		try {
			fixStaticVo = fixBorrowService.queryStaticBorrow();
			mv.addObject("cf", configurationService.getByType(1399));
		} catch (Exception e) {
			logger.error(e);
		}
		return mv.addObject("fixStaticVo", fixStaticVo);
	}

	/**
	 * <p>
	 * Description:定期宝业务查询 <br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageFixBorrowList(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			fixBorrowCnd.setOrderSql(" ORDER BY fixBorrow.ID DESC");
			page = fixBorrowService.queryPageListByCnd(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE2);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/fix/fixborrow/list").addObject("page", page);
	}

	/**
	 * 撤销定期宝
	 * <p>
	 * Description:撤销定期宝<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @param borrowVo
	 * @return MessageBox
	 */
	@RequestMapping("/doCancle")
	@ResponseBody
	public MessageBox doCancle(@RequestParam(value = "id", required = false) Integer Id) {
		String msg = "";

		try {
			msg = fixBorrowService.saveCancelFixBorrow(Id, HttpTookit.getRealIpAddr(currentRequest()));
			if (msg.equals("success")) {
				return MessageBox.build("1", "撤销成功");
			}
			return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "系统异常，请稍候重试或联系管理员");
		}
	}

	/**
	 * <p>
	 * Description:撤销定期宝<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @param borrowVo
	 * @return MessageBox
	 */
	@RequestMapping("/doDelete")
	@ResponseBody
	public MessageBox doDelete(@RequestParam(value = "id", required = false) Integer Id) {
		String msg = "";

		try {
			FixBorrowVo fixBorrowVo = new FixBorrowVo();
			fixBorrowVo.setId(Id);
			fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
			fixBorrowVo.setStatus(Constants.FIX_BORROW_STATUS_DELETED);
			msg = fixBorrowService.updateFixBorrowStatus(fixBorrowVo);
			if (msg.equals("success")) {
				return MessageBox.build("1", "删除成功");
			}
			return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "系统异常，请稍候重试或联系管理员");
		}
	}

	/**
	 * <p>
	 * Description:查看详细信息<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return ModelAndView
	 */
	@RequestMapping("/toAddModify")
	public ModelAndView toAddModify(@RequestParam(value = "id", required = false) Integer Id, @RequestParam(value = "opt", required = false) String opt) {
		FixBorrowVo fixBorrowVo = null;
		List<FixOperationLogVo> fixBorrowApprList = null;
		ModelAndView mv = new ModelAndView("/fix/fixborrow/show");
		try {
			if ("setTenderBid".equals(opt)) {
				mv = new ModelAndView("/fix/fixborrow/" + opt);
			}
			if ("setTenderBidAll".equals(opt)) {
				mv = new ModelAndView("/fix/fixborrow/" + opt);
				return mv.addObject("cf", configurationService.getByType(1399));
			}
			if (null != Id) {
				FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
				fixBorrowCnd.setId(Id);
				fixBorrowVo = fixBorrowService.queryFixBorrowByCnd(fixBorrowCnd);
				// 通过后台存的有效时间，反推有效期限方式和数字
				if (fixBorrowVo.getValidTime() != null) {
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
				fixBorrowApprList = fixBorrowService.queryfixBorrowApprList(Id);
			}
			mv.addObject("fixBorrowVo", fixBorrowVo).addObject("fixBorrowApprList", fixBorrowApprList);

		} catch (Exception e) {
			logger.error("定期宝弹出详细界面查询报错", e);
		}
		return mv;
	}

	@RequestMapping("/setTenderBidSub")
	@ResponseBody
	public MessageBox setTenderBidSub(int fixId, int tenderBidFlag, String tenderBidDate) {
		try {
			if (tenderBidFlag == 1 && (tenderBidDate == null || tenderBidDate.length() < 10)) {
				return MessageBox.build("0", "数据提交错误");
			}
			if (tenderBidFlag == 0) {
				tenderBidDate = null;
			}
			String s = fixBorrowService.updateTenderBid(fixId, tenderBidFlag, tenderBidDate);

			if ("".equals(s)) {
				return MessageBox.build("1", "设置成功");
			}
			return MessageBox.build("0", s);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "系统异常，请稍候重试或联系管理员");
		}
	}
}
