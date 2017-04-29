package com.cxdai.console.fix.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixAccountService;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.util.DateUtils;
import com.mysql.jdbc.StringUtils;

/**
 * 
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
@RequestMapping(value = "/fix/handleFixTender")
public class HandleFixTenderController extends BaseController {

	private static final Logger logger = Logger.getLogger(HandleFixTenderController.class);
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixAccountService fixAccountService;
	@Autowired
	private BorrowService borrowService;

	/**
	 * 
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

		return new ModelAndView("/fix/handleFixTender/main");
	}

	@RequestMapping("/fixmain")
	public ModelAndView fixmain(@RequestParam(value = "id", required = false) Integer Id, @RequestParam(value = "money", required = false) Double userMoney) {

		return new ModelAndView("/fix/handleFixTender/fixmain").addObject("borrowId", Id).addObject("userMoney", userMoney);
	}

	/**
	 * 
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
	public ModelAndView handleFixBorrowListTender(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
		Page page = null;
		BigDecimal fixUseMoneyTotal = null;
		try {
			fixUseMoneyTotal = fixAccountService.queryFixUseMoneyTotal();
			page = borrowService.queryListForHandFixTender(borrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			logger.error("取符合投标的定期宝列表查询错误", e);
		}
		return new ModelAndView("/fix/handleFixTender/list").addObject("page", page).addObject("fixUseMoneyTotal", fixUseMoneyTotal);
	}

	/**
	 * 
	 * <p>
	 * Description:取符合投标的定期宝列表<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月15日
	 * @param borrowCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/fixlist/{pageNo}")
	public ModelAndView handleFixBorrowListTenders(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
		Page page = null;
		BigDecimal borrowMoney=null;
		try {
			if (!StringUtils.isNullOrEmpty(fixBorrowCnd.getBeginTime())) {
				fixBorrowCnd.setBeginDate(DateUtils.parse(fixBorrowCnd.getBeginTime() + " 00:00:00", DateUtils.YMD_HMS));
			}
			if (!StringUtils.isNullOrEmpty(fixBorrowCnd.getEndTime())) {
				fixBorrowCnd.setEndDate(DateUtils.parse(fixBorrowCnd.getEndTime()+ " 23:59:59", DateUtils.YMD_HMS));
			}
			page = fixBorrowService.getHandleFixBorrowListTender(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			
			if(fixBorrowCnd.getBorrowId()!=null){
				BorrowVo borrowVo = borrowService.selectByPrimaryKey(fixBorrowCnd.getBorrowId());
				if(borrowVo!=null){
					borrowMoney=borrowVo.getAccount().subtract(borrowVo.getAccountYes());
				}
			}			

		} catch (Exception e) {
			logger.error("取符合投标的定期宝列表查询错误", e);
		}
		return new ModelAndView("/fix/handleFixTender/fixlist").addObject("page", page).addObject("borrowMoney",borrowMoney);
	}

	/**
	 * 
	 * <p>
	 * Description:手动投标<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月15日
	 * @param borrowId
	 * @param fixBorrowId
	 * @return MessageBox
	 */
	@RequestMapping("/handleFixTender")
	@ResponseBody
	public MessageBox handleFixTender(@RequestParam(value = "borrowId", required = false) Integer borrowId,
			@RequestParam(value = "fixBorrowId", required = false) Integer fixBorrowId) {
		String result = null;
		try {

			result = fixBorrowService.handleFixTender(borrowId, fixBorrowId);
			// 手动投标 和 满标复审分开两个事物处理 如果手动投标成功，则判断 是否满足满标复审service
			if ("success".equals(result)) {
				result = fixBorrowService.handleFixReCheckTender(borrowId);
			}
			if (result.equals("success")) {
				return MessageBox.build("1", "手动定期宝投标成功！");
			}
		} catch (Exception e) {
			logger.error("手动投标报错", e);
			result = "手动投标报错";
		}
		return MessageBox.build("0", result);

	}
}
