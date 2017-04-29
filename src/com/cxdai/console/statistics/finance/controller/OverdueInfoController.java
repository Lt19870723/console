package com.cxdai.console.statistics.finance.controller;

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
 * 实时财务--逾期列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/finance/overdueinfo")
public class OverdueInfoController extends BaseController {

	private final static Logger logger = Logger.getLogger(OverdueInfoController.class);
	
	@Autowired
	private FinanceChartService financeChartService;
	
	//进入逾期列表页面
	@RequestMapping("/main")
	public ModelAndView forOverdueInfoMain(){
		return new ModelAndView("/statistics/finance/overdue/main");
	}
	
	/**
	 * 逾期列表数据
	 * @param repaymentRecordCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchOverdueInfoList(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,@PathVariable Integer pageNo,HttpServletRequest request){
		Page page = null;
		Map<String, Object> voerduemap = new HashMap<String, Object>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
				repaymentRecordCnd.setRepaymentTimeBegin(format.parse(request.getParameter("beginTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endTime"))){
				repaymentRecordCnd.setRepaymentTimeEnd(format.parse(request.getParameter("endTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("statusStr"))){
				repaymentRecordCnd.setStatus(new Integer(request.getParameter("statusStr")));
			}
			page = financeChartService.findRepaymentChartForOverduePages(repaymentRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			voerduemap = financeChartService.queryRepaymentChartMapForOverdue(repaymentRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/finance/overdue/list").addObject("page", page).addObject("voerduemap", voerduemap);
	}
	
	/**
	 * 逾期列表导出数据量
	 * @param repaymentRecordCnd
	 * @return
	 */
	@RequestMapping("/overduecount")
	@ResponseBody
	public MessageBox exportToOverdueInfoCount(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd){
		List<BRepaymentRecordVo> list = null;
		try {
			list = financeChartService.queryRepaymentChartForOvedueList(repaymentRecordCnd);
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
	 * <p>
	 * Description:导出-逾期列表数据<br />
	 * </p>
	 */
	@RequestMapping("/overdueexport")
	public void exportToOverdueInfoExcel(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<BRepaymentRecordVo> list = financeChartService.queryRepaymentChartForOvedueList(repaymentRecordCnd);
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
					"实时财务-逾期报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
