package com.cxdai.console.statistics.tender.controller;

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

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.maintain.registersource.service.SourceService;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.vo.RedRecordCnd;
import com.cxdai.console.statistics.tender.entity.MemberTenderRecordVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.mapper.OperationNormalMapper;
import com.cxdai.console.statistics.tender.service.OperationNormalService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;

/**
 * 客户投资记录
 * @author 刘涛
 * @date 2016-04-20
 *
 */
@Controller
@RequestMapping(value="/tender/memberTenderRecord")
public class MemberTenderRecordController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(MemberTenderRecordController.class);
	@Autowired
	private SourceService sourceService;
	@Autowired
	private OperationNormalService operationNormalService;
	@Autowired
	private OperationNormalMapper operationNormal;
	
	@RequestMapping("/main")
	public ModelAndView forMonthlyInvestCountIndex(){
		List<Configuration> list = null;
		try {
		} catch (Exception e) {
			logger.error("客户投资记录查询异常："+e);
		}
		return new ModelAndView("/statistics/tender/memberTenderRecord/main");
	}
	
	/**
	 * 查询结果并封装
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView queryMemberTenderRecordList(@ModelAttribute OperationCnd operationCnd,@PathVariable Integer pageNo,HttpServletRequest request) {
		Page page = null;
		try {
			page = operationNormalService.queryMemberTenderRecordList(operationCnd,  Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/tender/memberTenderRecord/list").addObject("page", page);
	}
	/**
	 * 客户投资记录数
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute OperationCnd operationCnd) {
		int count =0;
		try {
		    count =operationNormal.queryMemberTenderRecordCount(operationCnd);
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
	 *客户投资记录
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping(value = "/export")
	public void exportRedPecord(@ModelAttribute OperationCnd operationCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			int totalCount = operationNormal.queryMemberTenderRecordCount(operationCnd);
			Page page = new Page(1, totalCount);
			List<MemberTenderRecordVo> list = operationNormal.queryMemberTenderRecordList(operationCnd, page);
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/memberTenderRecordexportExcel.jasper");
			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "客户投资记录" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("客户投资记录导出Excel出错", e);
		}
	}
	
	
}
