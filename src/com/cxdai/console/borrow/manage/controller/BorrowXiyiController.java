package com.cxdai.console.borrow.manage.controller;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCheck;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCnd;
import com.cxdai.console.borrow.emerg.vo.TenderRecordVo;
import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.service.TenderRecordService;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.customer.information.mapper.BorrowBusinessMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.BorrowBusinessVo;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.freemarker.BaseFreemarkerService;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.EnumerationUtil;
import com.cxdai.console.util.FileUtil;
import com.cxdai.console.util.PdfManager;
import com.cxdai.console.util.PropertiesUtil;
import com.itextpdf.text.pdf.PdfReader;
/**
 * 
 * <p>
 * Description:借款标协议业务 <br />
 * </p>
 * @title BorrowXiyiController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
/**
 * 
 * <p>
 * Description:借款标协议业务 <br />
 * </p>
 * @title BorrowXiyiController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Controller
@RequestMapping(value = "/borrow/manage/borrowXiyi")
public class BorrowXiyiController extends BaseController{
	private final static Logger logger=Logger.getLogger(BorrowXiyiController.class);
	@Autowired
	private BorrowManageService borrowManageService;
	@Autowired
	private TenderRecordService tenderRecordService;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private MemberService memberService;
    @Autowired
	public BaseFreemarkerService baseFreemarkerService;
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private BorrowBusinessMapper borrowBusinessMapper;
	 
	/**
	 * 
	 * <p>
	 * Description:进入借款标协议查<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		
		return  new ModelAndView("/borrow/manage/borrowXiyi/main");
	}
	/**
	 * 
	 * <p>
	 * Description:借款标协议查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchRepayingBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	 
	try {
	   		page =borrowManageService.queryBorrowListForRepay(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);	 
	} catch (Exception e) {
		
	   		logger.error(e);
	}
	  return new ModelAndView("/borrow/manage/borrowXiyi/list").addObject("page", page);
   }
   /**
    * 
    * <p>
    * Description:查看借款标投标记录<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年7月1日
    * @param autoInvestConfigReocrdVo
    * @return
    * ModelAndView
    */
   @RequestMapping("/toTenderRecord")
	public  ModelAndView toTenderRecord(@RequestParam(value = "id", required = false) Integer borrowId) {
	     
		ModelAndView mv = new ModelAndView("/borrow/manage/borrowXiyi/borrow_tender");
		try {
			BorrowVo borrow = borrowService.selectByPrimaryKey(borrowId);
			List<TenderRecordVo> tenderRecordVoList = tenderRecordService.queryListByBorrowId(borrowId);
			mv.addObject("tenderRecordVoList",tenderRecordVoList).addObject("borrow",borrow);
			 
		} catch (Exception e) {
			stackTraceError(logger, e);
		}
	    return mv;
	}
   /**
    * 
    * <p>
    * Description:查看协议<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年7月1日
    * @param borrowId
    * @return
    * ModelAndView
    */
	@RequestMapping("/toBorrowXiyi")
	public  ModelAndView toBorrowXiyi(@RequestParam(value = "id", required = false) Integer borrowId) {
	     
		ModelAndView mv = new ModelAndView("/borrow/manage/borrowXiyi/borrow_xiyi");
	 
		try {
			BorrowVo borrow= borrowService.selectByPrimaryKey(borrowId);
			MemberVo memberVo = memberService.queryMemberById(borrow.getUserId());
			TenderRecordCnd tenderRecordCnd = new TenderRecordCnd();
			tenderRecordCnd.setBorrowId(borrowId);
			// 查询原始投标记录
			tenderRecordCnd.setRecordType(0);
			List<TenderRecordVo> tenderRecordVoList = tenderRecordService.queryListByTenderRecordCnd(tenderRecordCnd);
		 
			// 计算到期应还本息总额
			BigDecimal totalRepaymentAccount = new BigDecimal("0");
			if (null != tenderRecordVoList && tenderRecordVoList.size() > 0) {
				for (TenderRecordVo tenderRecordVo : tenderRecordVoList) {
					totalRepaymentAccount = totalRepaymentAccount.add(tenderRecordVo.getRepaymentAccount());
				}
			}
			
			//查询借款标还款计划
			List<BRepaymentRecordVo> repayList=bRepaymentRecordService.queryRepaymentByBorrowId(borrowId);			
			
			String templateName = EnumerationUtil.getProtocolPathByBorrowType(borrow.getBorrowtype().toString());			
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("borrowVo", borrow);
			templateMap.put("tenderRecordVoList", tenderRecordVoList);
			templateMap.put("username", memberVo.getUsername());
			templateMap.put("totalRepaymentAccount", totalRepaymentAccount);
			templateMap.put("contextPath",currentRequest().getContextPath());
			templateMap.put("recordType", 0);
			templateMap.put("repayList", repayList);
			templateMap.put("businessName", getBusinessName(borrowId));
			// 得到协议内容
			String  xiyiContent = baseFreemarkerService.generateEmailContentByFreeMarker(templateName, templateMap);
		 
			mv.addObject("xiyiContent", xiyiContent).addObject("borrowId", borrowId); 
		} catch (Exception e) {
			stackTraceError(logger, e);
		}
	    return mv;
	}
	
	/**
	 * 根据借款标ID或获取业务员
	 * @author WangQianJin
	 * @title @param borrowId
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月25日
	 */
	private String getBusinessName(Integer borrowId) throws Exception{
		String bussinesName="";
		BorrowBusinessVo borrowBusinessVo = borrowBusinessMapper.selectBorBusByBorrowId(borrowId);
		if(borrowBusinessVo!=null){
			if("其他".equals(borrowBusinessVo.getUsername())){
				bussinesName="国诚金融";
			}else{
				bussinesName=borrowBusinessVo.getUsername();
			}
		}
		return bussinesName;
	}
	/**
	 * <p>
	 * Description:根据借款标Id导出投标记录<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordCnd
	 * @param request
	 * @param response void
	 */
	@RequestMapping("/export")
	public void exprtTenderRecordExcel(@RequestParam(value = "id", required = false) Integer borrowId,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TenderRecordCheck> list = tenderRecordService.queryTenderRecordForExcelByBorrowId(borrowId);
			Map dto = new HashMap();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fTenderRecord.jasper");
			java.io.InputStream is = currentSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is,request, response,
					"投标记录报表" + DateUtils.format(new Date(), DateUtils.YMD));
			 
		} catch (Exception e) {
			stackTraceError(logger, e);
		}
	} 
	/**
	 * 
	 * <p>
	 * Description:下载借款标协议<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月1日
	 * @param borrowId
	 * @param request
	 * @param response
	 * void
	 */
	@RequestMapping("/exprtBorrowXiYiDoc")
	public void exprtBorrowXiYiDoc(@RequestParam(value = "borrowId", required = false) Integer borrowId,
			                       @RequestParam(value = "recordType", required = false) Integer recordType,
			                       HttpServletRequest request, HttpServletResponse response) {
		try{
		BorrowVo borrow = borrowService.selectByPrimaryKey(borrowId);
		MemberVo memberVo = memberService.queryMemberById(borrow.getUserId());
		TenderRecordCnd tenderRecordCnd = new TenderRecordCnd();
		tenderRecordCnd.setBorrowId(borrowId);
		// 查询原始投标记录
		tenderRecordCnd.setRecordType(recordType);
		List<TenderRecordVo> tenderRecordVoList = tenderRecordService.queryListByTenderRecordCnd(tenderRecordCnd);
		// 计算到期应还本息总额
		BigDecimal totalRepaymentAccount = new BigDecimal("0");
		if (null != tenderRecordVoList && tenderRecordVoList.size() > 0) {
			if (null != recordType && recordType == 1) {
				totalRepaymentAccount = tenderRecordService.queryTotalRepaymentAccount(tenderRecordCnd);
			} else {
				for (TenderRecordVo tenderRecordVo : tenderRecordVoList) {
					// 如果是财务部专用，用于垫付取得备用金，且用户是非vip，只取本金
					totalRepaymentAccount = totalRepaymentAccount.add(tenderRecordVo.getRepaymentAccount());
				}
			}
		}
		
		//查询借款标还款计划
		List<BRepaymentRecordVo> repayList=bRepaymentRecordService.queryRepaymentByBorrowId(borrowId);
		
		String templateName = EnumerationUtil.getProtocolPathByBorrowType(borrow.getBorrowtype().toString());
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("borrowVo", borrow);
		templateMap.put("tenderRecordVoList", tenderRecordVoList);
		templateMap.put("username", memberVo.getUsername());
		templateMap.put("totalRepaymentAccount", totalRepaymentAccount);
		templateMap.put("contextPath", request.getContextPath());
		templateMap.put("recordType", recordType);
		templateMap.put("repayList", repayList);
		templateMap.put("businessName", getBusinessName(borrowId));
		// 得到协议内容
	 
		String path = currentRequest().getContextPath();
		String port =currentRequest().getServerPort() + "";
		 
		if (PropertiesUtil.getValue("console_port") != null && !"".equals(PropertiesUtil.getValue("console_port").trim())) {
			port = PropertiesUtil.getValue("console_port");
		}
		String basePath = currentRequest().getScheme() + "://" + currentRequest().getServerName() + ":" + port + path + "/";
		String realPath = currentRequest().getRealPath("/");
      
		// 生成临时html文件
		String htmlPathName = "temp\\borrowXiyi" + System.currentTimeMillis() + ".html";
		String pdfPathName = "temp\\borrowXiyi" + System.currentTimeMillis() + ".pdf";
		baseFreemarkerService.createHtml(templateName, templateMap, realPath + htmlPathName);
		// 页眉图片
		String headerImagePath = basePath + "images/header.png";
		// 生成临时pdf文件
		PdfManager.createPdf(realPath + htmlPathName, realPath + pdfPathName, headerImagePath);
		PdfReader reader = new PdfReader(realPath + pdfPathName);
		int pagecount = reader.getNumberOfPages();

		String finishPdfName = "temp\\borrowXiyi_new" + System.currentTimeMillis() + ".pdf";
		String markImagePath = basePath + "images/tuzhang.png";

		// 给临时pdf文件加水印图片
		PdfManager.addPdfMark(realPath + pdfPathName, realPath + finishPdfName, markImagePath, pagecount);

		// 下载临时pdf文件
		FileUtil.streamDownload(request, finishPdfName, "【" + borrow.getName().trim().replaceAll(" ", "") + "】借款标协议" + DateUtils.format(new Date(), DateUtils.YMD) + ".pdf",
				response);
		//20150701 *******FacesContext.getCurrentInstance().responseComplete();

		// 删除临时html文件
		FileUtil.deleteFile(currentRequest(), htmlPathName);
		// 删除临时pdf文件
		FileUtil.deleteFile(currentRequest(), pdfPathName);
		// 删除临时pdf（加水印）文件
		FileUtil.deleteFile(currentRequest(), finishPdfName);
		} catch (Exception e) {
			e.printStackTrace();
			stackTraceError(logger, e);
		}
	}
}
