package com.cxdai.console.statistics.customer.controller;

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
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.statistics.account.mapper.TReportMapper;
import com.cxdai.console.statistics.account.service.TReportService;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;
import com.cxdai.console.statistics.account.vo.TotalReportVo;
import com.cxdai.console.statistics.customer.entity.NewInterestMember;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;

/**
 * 用户人数统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/customer/peopcount")
public class PeopNumberController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(PeopNumberController.class);
	@Autowired
	private TReportService tRepotrService;
	@Autowired
	private TReportMapper tReportMapper;
	
	@RequestMapping("/main")
	public ModelAndView forTotalReport(){
		return new ModelAndView("/statistics/customer/peopnumber/main");
	}
	
	/**
	 * 用户人数统计结果
	 * @param reportStatementCnd
	 * @return
	 */
	@RequestMapping("/reportcount")
	public ModelAndView TotalReport(@ModelAttribute ReportStatementCnd reportStatementCnd) {
		TotalReportVo totalReportVo = new TotalReportVo();
		try {
			totalReportVo = tRepotrService.queryTReport(reportStatementCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台收支统计错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/peopnumber/list").addObject("reportVo", totalReportVo);
	}
	@RequestMapping("/newmain")
	public ModelAndView fornewMember(){
		Collection<Configuration> sources = Dictionary.getValues(1101);// 来源
		return new ModelAndView("/statistics/customer/peopnumber/newmain").addObject("sources",sources);
	}
	/**
	 * 用户分析-查询新投资人列表
	 * @author liutao
	 * @Date 2016-03-22
	 */
	@RequestMapping("/newlist/{pageNo}")
	public ModelAndView searchnewMemberList(@ModelAttribute ReportStatementCnd reportStatementCnd, @PathVariable Integer pageNo) {
		Page page = null;
		Collection<Configuration> sources = Dictionary.getValues(1101);// 来源
		try {
			page = tRepotrService.queryNewInterestMember(reportStatementCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询新投资人记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/statistics/customer/peopnumber/newlist").addObject("page", page).addObject("sources",sources);
	}
	/**
	 * 用户分析-查询新投资人导出
	 * @author liutao
	 * @Date 2016-03-22
	 */
	@RequestMapping(value = "/export")
	public void searchnewMemberExport(@ModelAttribute ReportStatementCnd reportStatementCnd,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<NewInterestMember> list = tRepotrService.queryNewInterestMemberExport(reportStatementCnd);
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/newMemberinterEstExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "新用户投资" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("新投资人导出导出Excel出错", e);
	     }
	}
	/**
	 * <p>
	 * Description:计算要导出的数据数量<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @param request
	 * @param response
	 * @return MessageBox
	 */
	@RequestMapping(value = "/count", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public MessageBox count(@ModelAttribute ReportStatementCnd reportStatementCnd, HttpServletRequest request, HttpServletResponse response) {
		Integer count = null;
		try {
			count = tReportMapper.queryNewInterestMemberCount(reportStatementCnd);
		} catch (Exception e) {
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (null == count || count == 0) {
			return MessageBox.build("1", "没有数据");
		}
		if (count > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return MessageBox.build("0", String.valueOf(count));

	}

}
