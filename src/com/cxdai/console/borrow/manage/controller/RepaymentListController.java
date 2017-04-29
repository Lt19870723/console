package com.cxdai.console.borrow.manage.controller;

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

import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordCheck;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.util.DateUtils;
/**
 * 
 * <p>
 * Description:官方标还款记录查询<br />
 * </p>
 * @title RepaymentListController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Controller
@RequestMapping(value = "/borrow/manage/repaymentList")
public class RepaymentListController extends BaseController{
	
	private final static Logger logger=Logger.getLogger(RepaymentListController.class);
	 
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	 
	/**
	 * 
	 * <p>
	 * Description:进入官方表记录页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		 
		return  new ModelAndView("/borrow/manage/repaymentList/main");
	}
	/**
	 * 
	 * <p>
	 * Description:官方还款中的借款标集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchRepayingBorrowList(@ModelAttribute BRepaymentRecordCnd bRepaymentRecordCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	 
	try {
	   		page =bRepaymentRecordService.selectRepaymentListForPage(bRepaymentRecordCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE); 		 
	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	}
	  return new ModelAndView("/borrow/manage/repaymentList/list").addObject("page", page);
   } 
    /**
     * 
     * <p>
     * Description:导出还款记录Excel的数量<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年7月1日
     * @param borrowCnd
     * @return
     * MessageBox
     */
    @RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute BRepaymentRecordCnd bRepaymentRecordCnd) {
    	List<BRepaymentRecordCheck>  list= null;
    	 
		try {
			 
			list = bRepaymentRecordService.selectRepaymentListToExcel(bRepaymentRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
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
     * Description:导出还款记录<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年7月1日
     * @param borrowCnd
     * @param request
     * @param response
     * void
     */
	@RequestMapping("/export")
	public void exportToExcel(@ModelAttribute BRepaymentRecordCnd bRepaymentRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<BRepaymentRecordCheck> list = bRepaymentRecordService.selectRepaymentListToExcel(bRepaymentRecordCnd);
			Map dto = new HashMap();

			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fRepaymentRecord.jasper");

			java.io.InputStream is =currentRequest().getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
					"还款记录报表" + DateUtils.format(new Date(), DateUtils.YMD));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
