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

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.service.QueryBorrowRocordService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:失败的借款标业务<br />
 * </p>
 * @title TenderBorrowController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "/manage/forRepaymentFailBorrow")
public class RepayFailBorrowController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(RepayFailBorrowController.class);
	@Autowired
	private BorrowManageService borrowManageService;
	@Autowired
	private QueryBorrowRocordService queryBorrowRocordService;
	private String path=PropertiesUtil.getValue("portal_path");
	/**
	 * 
	 * <p>
	 * Description:进入失败的借款标列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		
		return  new ModelAndView("/borrow/manage/repayFailBorrow/main");
	}
	/**
	 * 
	 * <p>
	 * Description:失败的借款标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchRepayFailBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	  Page page = null;   
	try {   
		    borrowCnd.setStatus("fail");
	   		page =borrowManageService.searchPageBorrowList(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	} catch (Exception e) { 
		
	   		logger.error(e);
	}
	   	return new ModelAndView("/borrow/manage/repayFailBorrow/list").addObject("page", page).addObject("portal_path", path);
   }
    /**
   	 * <p>
   	 * Description:统计导出数据的数量<br />
   	 * </p>
   	 * 
   	 * @author hujianpan
   	 * @version 0.1 2015年3月17日
   	 * @param rechargeRecordCnd
   	 * @return MessageBox
   	 */
   	@RequestMapping("/count")
   	@ResponseBody
   	public MessageBox count(@ModelAttribute BorrowCnd borrowCnd) {
   		List<BorrowVo> list = null;
   		borrowCnd.setStatus("fail");
   		try {
   			list =queryBorrowRocordService.searchListBorrowList(borrowCnd);;
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
   	 * <p>
   	 * Description:导出符合条件的借款标数据<br />
   	 * </p>
   	 * 
   	 * @author hujianpan
   	 * @version 0.1 2015年3月17日
   	 * @param rechargeRecordCnd
   	 * @param request
   	 * @param response void
   	 */
   	@RequestMapping("/export")
   	public void exportToExcel(@ModelAttribute BorrowCnd borrowCnd, HttpServletRequest request, HttpServletResponse response) {
   		try {
   			borrowCnd.setStatus("fail"); 
   			List<BorrowVo> list =  queryBorrowRocordService.searchListBorrowList(borrowCnd);
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
   			reportData.setReportFilePath("/report/exportExcel/repaymentBorrowExcelForQuery.jasper");

   			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
   			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
   			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
   					"还款失败的借款标" + DateUtils.format(new Date(), DateUtils.YMD));	 
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   	} 
}
