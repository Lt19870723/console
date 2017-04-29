package com.cxdai.console.statistics.finance.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordCnd;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.statistics.finance.service.FinanceChartService;
import com.cxdai.console.util.DateUtils;

/**
 * 实时财务--借款列表
 * @author Administrator
 */
@Controller
@RequestMapping(value="/statistics/finance/week")
public class WeekInfoController extends BaseController {
	
	private final static Logger logger = Logger.getLogger(WeekInfoController.class);
	@Autowired
	private FinanceChartService financeChartService;
	
	//进入借款列表页面
	@RequestMapping("/main")
	public ModelAndView forWeekInfoMain(){
		return new ModelAndView("/statistics/finance/weekinfo/main");
	}
	
	/**
	 * 借款列表
	 * @param repaymentRecordCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchWeekInfoList(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,@PathVariable Integer pageNo){
		Page page = null;
		Map<String, BigDecimal> weekInfoMap = new HashMap<String, BigDecimal>();
		
		try {
			
			page = financeChartService.findRepaymentChartForWeekPages(repaymentRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			weekInfoMap = financeChartService.queryRepaymentChartForWeek(repaymentRecordCnd);
			Map<String, BigDecimal> firstInfoMap = financeChartService.queryFirstRepaymentChartForWeek(repaymentRecordCnd);
			Map<String, BigDecimal> fixInfoMap = financeChartService.queryFixRepaymentChartForWeek(repaymentRecordCnd);			
			weekInfoMap.putAll(firstInfoMap);
			weekInfoMap.putAll(fixInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/finance/weekinfo/list").addObject("page", page).addObject("weekInfoMap", weekInfoMap);
	}
	
	/**
	 * 统计导出数据的数量
	 * @param repaymentRecordCnd
	 * @return
	 */
	@RequestMapping("/weekcount")
	@ResponseBody
	public MessageBox exportToWeekInfoCount(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd){
		List<BRepaymentRecordVo>  list = null;
		try {
		 
			list = financeChartService.queryRepaymentChartForWeekList(repaymentRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试!");
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
	 * Description:导出-借款列表数据<br />
	 * </p>
	 */
	@RequestMapping("/weekexport")
	public void exportToWeekInfoExcel(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<BRepaymentRecordVo> list = financeChartService.queryRepaymentChartForWeekList(repaymentRecordCnd);
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
			reportData.setReportFilePath("/report/exportExcel/fChartRepayRecordExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
					"实时财务-借款报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出借款列表错误信息："+e);
		}
	}

}
