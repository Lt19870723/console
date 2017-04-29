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
 * 结清列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/finance/finish")
public class FinishInfoController extends BaseController {
	
	private final static Logger logger = Logger.getLogger(FinishInfoController.class);

	@Autowired
	private FinanceChartService financeChartService;
	
	//进入结清列表页面
	@RequestMapping("/main")
	public ModelAndView forFinishInfoMain(){
		return new ModelAndView("/statistics/finance/finishinfo/main");
	}
	
	/**
	 * 结清列表数据
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchFinishInfoList(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,@PathVariable Integer pageNo,HttpServletRequest request){
		Page page = null;
		Map<String, Object> finishmap = new HashMap<String, Object>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
				repaymentRecordCnd.setRepaymentTimeBegin(format.parse(request.getParameter("beginTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endTime"))){
				repaymentRecordCnd.setRepaymentTimeEnd(format.parse(request.getParameter("endTime")));
			}
			page = financeChartService.findRepaymentChartForFinishPages(repaymentRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			finishmap = financeChartService.queryRepaymentChartMapForFinish(repaymentRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/finance/finishinfo/list").addObject("page", page).addObject("finishmap", finishmap);
	}

	/**
	 * 结清列表导出数据量统计
	 * @param repaymentRecordCnd
	 * @return
	 */
	@RequestMapping("/finishcount")
	@ResponseBody
	public MessageBox exportToFinishInfoCount(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,HttpServletRequest request){
		List<BRepaymentRecordVo> list = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
				repaymentRecordCnd.setRepaymentTimeBegin(format.parse(request.getParameter("beginTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endTime"))){
				repaymentRecordCnd.setRepaymentTimeEnd(format.parse(request.getParameter("endTime")));
			}
			list = financeChartService.queryRepaymentChartForFinishList(repaymentRecordCnd);
		} catch (Exception e) {
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
	 * Description:导出-结清列表数据<br />
	 * </p>
	 */
	@RequestMapping("/finishexport")
	public void exportToFinishInfoExcel(@ModelAttribute BRepaymentRecordCnd repaymentRecordCnd,HttpServletRequest request,HttpServletResponse response) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
				repaymentRecordCnd.setRepaymentTimeBegin(format.parse(request.getParameter("beginTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endTime"))){
				repaymentRecordCnd.setRepaymentTimeEnd(format.parse(request.getParameter("endTime")));
			}
			List<BRepaymentRecordVo> list = financeChartService.queryRepaymentChartForFinishList(repaymentRecordCnd);
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

			java.io.InputStream is =request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
					"实时财务-结清报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出结清列表错误信息："+e);
		}
	}
}
