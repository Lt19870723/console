package com.cxdai.console.red.controller;
import java.util.Collection;
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
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.entity.RedAccountLog;
import com.cxdai.console.red.mapper.RedAccountLogMapper;
import com.cxdai.console.red.service.RedRecordLogService;
import com.cxdai.console.red.vo.RedRecordCnd;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;

/**
 * 红包管理-红包使用记录
 * @author liutao
 * @Date 2015-11-11
 */
@Controller
@RequestMapping(value = "/reduse")
public class RedAccountLogController extends BaseController {
	private static final Logger logger = Logger.getLogger(RedAccountController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private RedRecordLogService redAccountLogService;
	@Autowired
	private RedAccountLogMapper redAccountLogMapper;

	@RequestMapping("/main")
	public ModelAndView forRedRecordMain() {
		ModelAndView modelAndView=new ModelAndView("/red/reduserecord/main");
		Collection<Configuration>configurations=Dictionary.getValues(1900);
		if(null!=configurations&&configurations.size()>0){
			modelAndView.addObject("redtypes", configurations);
		}
		return modelAndView;
	}

	/**
	 * 红包管理-查询红包使用记录
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchReduseRecord(@ModelAttribute RedRecordCnd redRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = redAccountLogService.queryReduseAccountVoList(redRecordCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询红包使用记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/red/reduserecord/list").addObject("page", page);
	}
	/**
	 * 红包管理-查询使用红包记录数
	 * @author liutao
	 * @Date 2016-04-19
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute RedRecordCnd redRecordCnd) {
		int count =0;
		try {
			 count = redAccountLogMapper.queryRedAccountLogVoCount(redRecordCnd);
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
	 * 红包管理-导出红包使用记录
	 * @author liutao
	 * @Date 2016-04-19
	 */
	@RequestMapping(value = "/export")
	public void exportRedPecord(@ModelAttribute RedRecordCnd redRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			int totalCount =redAccountLogMapper.queryRedAccountLogVoCount(redRecordCnd);
			Page page = new Page(1, totalCount);
			List<RedAccountLog> list = redAccountLogMapper.queryRedAccountLogVoList(redRecordCnd, page);
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/redUseRecordexportExcel.jasper");
			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "红包记录" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("红包使用记录导出Excel出错", e);
		}
	}
}