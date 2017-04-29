package com.cxdai.console.statistics.customer.controller;

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

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.vo.AccountLogCnd;
import com.cxdai.console.statistics.account.vo.AccountLogVo;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.PropertiesUtil;

/**
 * 用户资金记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/customer/accoutlogtrading")
public class AccoutLogTradingController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(AccoutLogTradingController.class);
	@Autowired
	private AccountLogService accountLogService;
	
	@RequestMapping("/main")
	public ModelAndView forAccoutLogRecordMain(){
		List<Configuration> list = null;
		try {
			list = accountLogService.queryConfigurationListByType(1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金记录获取交易类型列表错误："+e);
		}
		return new ModelAndView("/statistics/customer/accounttrading/main").addObject("list", list);
	}
	
	/**
	 * 用户资金记录列表
	 * @param accountLogCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchAccoutLogRecord(@ModelAttribute AccountLogCnd accountLogCnd,@PathVariable Integer pageNo,HttpServletRequest request){
		Map<String, BigDecimal> accountLogMap = new HashMap<String, BigDecimal>();
		Page page = null;
	    String portal_path = PropertiesUtil.getValue("portal_path");
		try {
			 
			page = accountLogService.queryAccountLogForPage(accountLogCnd, Constants.CONSOLE_PAGE_SIZE2, pageNo);
			if (accountLogCnd.getUsername() != null && !accountLogCnd.getUsername().equals("")) {
				accountLogMap = accountLogService.queryAccoutLogByUserName(accountLogCnd.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金记录错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/accounttrading/list").addObject("page", page).addObject("accountLogMap", accountLogMap).addObject("portal_path", portal_path);
	}
	/**
	 * 导出Excel数据统计功能
	 * <p>
	 * Description:导出Excel数据统计功能<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @param borrowCnd
	 * @return MessageBox
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute AccountLogCnd accountLogCnd) {
		List<AccountLogVo> list = null;
		try {

			list = accountLogService.queryAccountLogVo(accountLogCnd);
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
	 * 导出Excel数据功能
	 * <p>
	 * Description:导出Excel数据功能<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @param cnd
	 * @param request
	 * @param response void
	 */
	@RequestMapping("/exportToExcel")
	public void exportToExcel(@ModelAttribute AccountLogCnd accountLogCnd, HttpServletResponse response) {
		try {

			List<AccountLogVo> list = accountLogService.queryAccountLogVo(accountLogCnd);
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
			reportData.setReportFilePath("/report/exportExcel/accounttrading.jasper");
			java.io.InputStream is = currentSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, currentRequest(), response, "用户资金记录" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("用户资金记录", e);
		}
	}

}
