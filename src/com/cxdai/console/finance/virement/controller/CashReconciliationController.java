package com.cxdai.console.finance.virement.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.CheckWithdrawals;
import com.cxdai.console.finance.virement.entity.WithdrawalsStatus;
import com.cxdai.console.finance.virement.service.CheckWithDrawalsService;
import com.cxdai.console.finance.virement.vo.CheckWithCnd;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;
import com.cxdai.console.finance.virement.vo.UpdateCheckWithCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ViewExcel;

/***
 * 提现核对Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/finance/checkWithdrawals")
public class CashReconciliationController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(CashReconciliationController.class);

	@Autowired
	private CheckWithDrawalsService checkWithService;

	/***
	 * 提现核对主界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView interTransferMain() {
		return new ModelAndView("/finance/reconciliation/main");
	}

	/**
	 * 分页列表查询每日提现对账信息
	 * 
	 * @param requestCnd
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageInterTransferList(@ModelAttribute QueryPageCheckWithCnd requestCnd,
			@PathVariable Integer pageNo) {
		ModelAndView mv = new ModelAndView("/finance/reconciliation/list");
		try {
			Page page = checkWithService.queryPageCheckWithdrawals(requestCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error("分页查询每日提现汇总列表出错" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	/***
	 * 提现核对输入页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public ModelAndView eidt(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("/finance/reconciliation/edit");
		if (id != null) {
			CheckWithdrawals checkWithdrawals = checkWithService.selectByPrimaryKey(id);
			mv.addObject("checkWithdrawals", checkWithdrawals);
		}
		return mv;
	}

	@RequestMapping(value = "/updateCheckWithById")
	@ResponseBody
	public MessageBox updateCheckWith(@ModelAttribute UpdateCheckWithCnd requestCnd) {
		try {
			requestCnd.setUpdateUser(currentUser().getUserId());
			requestCnd.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			int count = checkWithService.updateCheckWithById(requestCnd);
			if (count == 1) {
				return new MessageBox("1", "更新成功!");
			} else {
				return new MessageBox("0", "更新失败!");
			}
		} catch (Exception e) {
			logger.error("提现对账保存结果出错" + e.getMessage());
			e.printStackTrace();
			return new MessageBox("0", "更新异常!");
		}
	}

	/**
	 * 导出提现对账信息
	 * 
	 * @param requestCnd
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public ModelAndView exportCheckWithList(@ModelAttribute QueryPageCheckWithCnd requestCnd, ModelMap model)
			throws Exception {
		String[] headData = new String[] { "日期", "净提现总额(元)", "提现支出总额(元)", "实际打款总额(元)", "网银在线划出金额(元)", "手续费(元)", "差异(元)", "状态", "差异说明","" };
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("##,###.00");
		List<String[]> dataList = new ArrayList<String[]>();
		List<CheckWithdrawals> list = checkWithService.exportCheckWithList(requestCnd);
		String[] endData = null;
		if (list != null && list.size() > 0) {
			for (CheckWithdrawals che : list) {
				String[] data = new String[headData.length];
				data[0] = DateUtils.formatDateYmd(che.getBillDate());
				data[1] = che.getPresentSuccessMoney() != null ?  che.getPresentSuccessMoney() + "" : "0";
				data[2] = che.getTakeCashMoney() != null ? che.getTakeCashMoney() + "" : "0";
				data[3] = che.getActualCashMoney() != null ? che.getActualCashMoney() + "" : "0";
				data[4] = che.getTotalExpenditure() != null ? che.getTotalExpenditure() + "" : "";
				data[5] = che.getCounterFee() != null ? che.getCounterFee() + "" : "";
				data[6] = che.getDifference() != null ? che.getDifference() + "" : "";
				data[7] = WithdrawalsStatus.getByCode(che.getIsSuccess()).getDesc();
				data[8] = che.getRemarks() != null ?  che.getRemarks() : "";
				data[9] = "";
				dataList.add(data);
			}
		} else {
			String[] data = new String[1];
			data[0] = "暂无数据";
			dataList.add(data);
		}
		return new ModelAndView(
				new ViewExcel("提现对账列表" + DateUtils.formatDateYmd(new Date()), headData, dataList, endData), model);
	}

	/**
	 * 提现 实际打款总额 与 实际网银打款 数据校对
	 * 
	 * @param requestCnd
	 * @return
	 */
	@RequestMapping(value = "/checkWithdrawalsById")
	@ResponseBody
	public Map<String, Object> checkWithdrawals(@ModelAttribute CheckWithCnd requestCnd) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = checkWithService.checkWithdrawals(requestCnd);
		} catch (Exception e) {
			logger.error("提现对账出错" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	/****
	 * 当净提现总额、提现支出总额、实际打款总额三者不相等时，手动更新该天数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateWithdrawal/{id}")
	@ResponseBody
	public MessageBox updateWithdrawal(@PathVariable("id") Integer id) {
		try {
			checkWithService.updateWithdrawal(id, currentUser().getUserId(),
					HttpTookit.getRealIpAddr(currentRequest()));
		} catch (Exception e) {
			logger.error("更新提现对账出错" + e.getMessage());
			e.printStackTrace();
			return new MessageBox("0", "更新失败!");
		}
		return new MessageBox("1", "更新成功!");
	}

	/**
	 * 手动根据日期对账对账
	 * 
	 * @return
	 */
	@RequestMapping(value = "/checkWithdrawalsByDate")
	@ResponseBody
	public Map<String, Object> checkWithdrawalsAgain(@ModelAttribute CheckWithdrawals requestCnd) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != requestCnd.getBillDateStr() && "".equals(requestCnd.getBillDateStr())
				&& null != requestCnd.getId()) {
			map.put("result", false);
			map.put("msg", "参数非法！请重新验证！");
			return map;
		}
		try {
			requestCnd.setBillDate(new SimpleDateFormat("yyyy-MM-dd").parse(requestCnd.getBillDateStr()));
			requestCnd.setUpdateUser(currentUser().getUserId());
			requestCnd.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			boolean flag = checkWithService.updateCheckWithdrawals(requestCnd);
			map.put("result", flag);
			map.put("msg", "数据更新成功!");
		} catch (Exception e) {
			logger.error("手工提现对账出错" + e.getMessage());
			e.printStackTrace();
			map.put("msg", "数据更新异常!");
		}
		return map;
	}

}
