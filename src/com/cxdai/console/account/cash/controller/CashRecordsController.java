package com.cxdai.console.account.cash.controller;

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

import com.cxdai.console.account.cash.service.CashRecordService;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.account.cash.vo.CashRecordVo;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:资金管理 - 提现管理 - 提现记录查看<br />
 * </p>
 * 
 * @title CashRecordController.java
 * @package com.cxdai.console.account.cash.controller
 * @author hujianpan
 * @version 0.1 2015年4月7日
 */
@Controller
@RequestMapping(value = "/account/cashrecord")
public class CashRecordsController extends BaseController {
	private final static Logger logger = Logger.getLogger(CashRecordsController.class);

	@Autowired
	private CashRecordService cashRecordService;

	@RequestMapping("/main")
	public ModelAndView CashRecordMain() {
		return new ModelAndView("/account/cash/query/cash/main");
	}

	/**
	 * <p>
	 * Description:查询提现记录<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年4月7日 void
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageCashRecordList(@ModelAttribute CashRecordCnd cashRecordCnd, @PathVariable Integer pageNo,HttpServletRequest request) {
		Map<String, BigDecimal> resultMap = null;
		Page page = null;
		try {

			cashRecordCnd.setCashColumn(3);
			cashRecordCnd.setBeginTime(request.getParameter("beginTime"));
			cashRecordCnd.setEndTime(request.getParameter("endTime"));
			cashRecordCnd.setBeginTime2(request.getParameter("beginTime2"));
			cashRecordCnd.setEndTime2(request.getParameter("endTime2"));
			cashRecordCnd.setVerifyTimeBeginDate(request.getParameter("verifyTimeBeginDate"));
			cashRecordCnd.setVerifyTimeEndDate(request.getParameter("verifyTimeEndDate"));
			page = cashRecordService.queryPageListByCnd(cashRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE4);

			resultMap = cashRecordService.queryCashRecorData(cashRecordCnd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/cash/query/cash/list").addObject("resultMap", resultMap).addObject("page", page).addObject("isCustody",cashRecordCnd.getIsCustody());
	}

	/**
	 * 
	 * <p>
	 * Description:获取导出的数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年2月10日 void
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox getExportCount(@ModelAttribute CashRecordCnd cashRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		List<CashRecordVo> list = null;
		try {
			cashRecordCnd.setCashColumn(3);
			list = cashRecordService.exportToExcel(cashRecordCnd, request, response);
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
	 * 
	 * <p>
	 * Description:导出Excel<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月8日 void
	 */
	@RequestMapping("/export")
	public void exportToExcel(@ModelAttribute CashRecordCnd cashRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			cashRecordCnd.setCashColumn(3);
			List<CashRecordVo> list = cashRecordService.exportToExcel(cashRecordCnd, request, response);
			Map<Object, Object> dto = new HashMap<Object, Object>();

			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fcashReportExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "账户提现报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			// this.setMsg("fail");
			e.printStackTrace();
		}
	}
}
