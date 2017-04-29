package com.cxdai.console.activity.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.activity.mapper.BillionActivityMapper;
import com.cxdai.console.activity.vo.BillionActivity;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;
import com.cxdai.console.util.DateUtils;

/**
 * 活动数据查询-50亿活动数据
 * @author liutao
 * @Date 2016-05-10
 */
@Controller
@RequestMapping(value = "/activity/fiveBillion")
public class BillionActivityController extends BaseController {
	private static final Logger logger = Logger.getLogger(BillionActivityController.class);
	@Autowired
	private BillionActivityMapper billionActivityMapper;
	@RequestMapping("/main")
	public ModelAndView forRedRecordMain() {
		ModelAndView modelAndView=new ModelAndView("/activity/fiveBillion/main");
		return modelAndView;
	}

	/**
	 * 活动数据查询-50亿活动数据记录数
	 * @author liutao
	 * @Date 2016-05-10
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute ReportStatementCnd reportStatementCnd) {
		int count =0;
		try {
		    count =billionActivityMapper.queryFiveBillionCount(reportStatementCnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (count==0) {
			return MessageBox.build("1", "没有数据");
		}
		if (count > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(count));
	}
	/**
	 * 活动数据查询-50亿活动数据记录
	 * @author liutao
	 * @Date 2016-05-10
	 */
	@RequestMapping(value = "/export")
	public void export(@ModelAttribute ReportStatementCnd reportStatementCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<BillionActivity> list = billionActivityMapper.queryFiveBillionList(reportStatementCnd);
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fiveBillionExportExcel.jasper");
			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "自动投标成功用户名单" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("活动数据导出Excel出错", e);
		}
	}
	/**
	 * 活动数据查询-50亿活动数据记录数
	 * @author liutao
	 * @Date 2016-05-10
	 */
	@RequestMapping("/count1")
	@ResponseBody
	public MessageBox count1(@ModelAttribute ReportStatementCnd reportStatementCnd) {
		int count =0;
		try {
		    count =billionActivityMapper.queryFiveBillionCount1(reportStatementCnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (count==0) {
			return MessageBox.build("1", "没有数据");
		}
		if (count > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(count));
	}
	/**
	 * 活动数据查询-50亿活动数据记录
	 * @author liutao
	 * @Date 2016-05-10
	 */
	@RequestMapping(value = "/export1")
	public void export1(@ModelAttribute ReportStatementCnd reportStatementCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<BillionActivity> list = billionActivityMapper.queryFiveBillionList1(reportStatementCnd);
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fiveBillionExportExcel.jasper");
			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "欢乐50颂奖励名单" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("活动数据导出Excel出错", e);
		}
	}
}
