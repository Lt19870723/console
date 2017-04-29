package com.cxdai.console.account.recharge.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.util.DateUtils;


/**
 * <p>
 * Description:资金管理 - 充值管理 - 充值记录查询<br />
 * </p>
 * 
 * @title AdditionalOrderController.java
 */
@Controller
@RequestMapping(value = "/account/rechargerecords")
public class RechargeRecordsController extends BaseController {

	private final static Logger logger = Logger.getLogger(RechargeRecordsController.class);

	@Autowired
	private RechargeRecordService rechargeRecordService;

	/**
	 * <p>
	 * Description:充值记录主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView rechargeRecordsMain() {
		return new ModelAndView("/account/recharge/records/main");
	}

	/**
	 * <p>
	 * Description:查询充值记录列表<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月4日 void
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageRechargeList(@ModelAttribute RechargeRecordCnd rechargeRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		/* 充值总额 */
		BigDecimal rechargeTotal = new BigDecimal(0);
		/* 实际充值总额 */
		BigDecimal realChargeTotal = new BigDecimal(0);
		try {
			page = rechargeRecordService.queryPageListByRechargeRecordCnd(rechargeRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			//充值总额
			BigDecimal result = rechargeRecordService.queryRechargeTotalByCnd(rechargeRecordCnd);
			if (result != null) {
				rechargeTotal = result;
			}
			//充值成功总额
			realChargeTotal = rechargeRecordService.queryRealRechargeTotalByCnd(rechargeRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("/account/recharge/records/list").addObject("page", page)
				.addObject("rechargeTotal", rechargeTotal.setScale(2))
				.addObject("realChargeTotal", realChargeTotal.setScale(2));
	}

	/**
	 * <p>
	 * Description:统计导出数据的数量<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordCnd
	 * @return MessageBox
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute RechargeRecordCnd rechargeRecordCnd) {
		List<RechargeRecordVo> list = null;
		try {
			list = rechargeRecordService.queryRechargeRecordList(rechargeRecordCnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (null == list || list.size() == 0) {
			return MessageBox.build("1", "没有数据");
		}
		if (list.size() > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(list.size()));
	}

	/**
	 * <p>
	 * Description:导出excel<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordCnd
	 * @param request
	 * @param response void
	 */
	@RequestMapping("/export")
	public void exportToExcel(@ModelAttribute RechargeRecordCnd rechargeRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<RechargeRecordVo> list = rechargeRecordService.queryRechargeRecordList(rechargeRecordCnd);
			if (null == list || list.size() == 0) {
				return;
			}
			if (list.size() > 50000) {
				return;
			}
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/frechargeRecordExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "账户充值报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
