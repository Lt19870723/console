package com.cxdai.console.account.cash.controller;

import java.util.ArrayList;
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

import com.cxdai.console.account.cash.service.DrawLogRecordService;
import com.cxdai.console.account.cash.vo.DrawableRecordCnd;
import com.cxdai.console.account.cash.vo.TurnDrawLogVO;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:资金管理 - 提现管理 - 转可提查询<br />
 * </p>
 * 
 * @title DrawableRocordController.java
 * @package com.cxdai.console.account.cash.controller
 * @author hujianpan
 * @version 0.1 2015年4月7日
 */
@Controller
@RequestMapping(value = "/account/drawablerocord")
public class DrawableRocordController {
	private static final Logger logger = Logger.getLogger(DrawableRocordController.class);
	@Autowired
	private DrawLogRecordService drawLogRecordServiceImpl;

	@RequestMapping("/main")
	public ModelAndView CashRecordMain() {
		return new ModelAndView("/account/cash/query/draw/main");
	}

	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageDrawableRecordList(@ModelAttribute DrawableRecordCnd drawableRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = drawLogRecordServiceImpl.queryDrawPageListByCnd(drawableRecordCnd, new Page(pageNo, Constants.CONSOLE_PAGE_SIZE4));
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/account/cash/query/draw/list").addObject("page", page);
	}

	@RequestMapping("/count")
	@ResponseBody
	public MessageBox countExport(@ModelAttribute DrawableRecordCnd drawableRecordCnd) {
		List<TurnDrawLogVO> list = null;
		try {
			list = drawLogRecordServiceImpl.queryDrawListByCnd(drawableRecordCnd);
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

	@RequestMapping("/export")
	public void exportDrawableRecordToExcel(@ModelAttribute DrawableRecordCnd drawableRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		List<TurnDrawLogVO> exportList = new ArrayList<TurnDrawLogVO>();
		try {
			exportList = drawLogRecordServiceImpl.queryDrawListByCnd(drawableRecordCnd);
			if (null != exportList) {
				Map<Object, Object> dto = new HashMap<Object, Object>();

				ReportData reportData = new ReportData();
				reportData.setParametersDto(dto);
				reportData.setFieldsList(exportList);
				reportData.setReportFilePath("/report/exportExcel/fDrawLogExcel.jasper");

				java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
				// 这里可以传入pdf,excel,word,html,print导出各种类型文件
				JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "转可提记录" + DateUtils.format(new Date(), DateUtils.YMD));
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
