package com.cxdai.console.statistics.customer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.maintain.registersource.service.SourceService;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.entity.RecommendCountVo;
import com.cxdai.console.statistics.tender.service.OperationNormalService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.ExportExcelUtils;
/**
 * 推广统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/customer/recommendcount")
public class RecommendCountController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RecommendCountController.class);
	
	@Autowired
	private SourceService sourceService;
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forRecommendCountIndex(){
		List<Configuration> sourceList = new ArrayList<Configuration>();
		try {
			sourceList = sourceService.querySourceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/customer/recommendcount/main").addObject("sourceList", sourceList);
	}
	
	/**
	 * 推广统计结果
	 */
	@RequestMapping("/recommendcount")
	public ModelAndView queryRecommendCount(HttpServletRequest request){
		List<RecommendCountVo> recommendCountList = null;
		OperationCnd operationCnd = new OperationCnd();
		try {
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("source"))){
				operationCnd.setSource(request.getParameter("source"));
			}
			recommendCountList = operationNormalService.queryRecommendCountList(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推广统计结果错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/recommendcount/list").addObject("recommendCountList", recommendCountList);
	}
	
	/**
	 * 推广统计导出excel
	 */
	@RequestMapping("/export")
	public void exportRecommendCount(HttpServletResponse response,HttpServletRequest request) {
		try {
			OperationCnd operationCnd = new OperationCnd();
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("source"))){
				operationCnd.setSource(request.getParameter("source"));
			}
//			response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//			HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			ArrayList<String> realheaders = new ArrayList<String>();
			realheaders.addAll(Arrays.asList("用户来源", "注册人数", "实名数量", "vip数量", "充值人数", "投资人数", "流失人数", "投资金额", "充值金额", "来源渠道开始时间 ", "来源渠道结束时间", "备注"));
			ArrayList<String> propertiesList = new ArrayList<String>();
			propertiesList.addAll(Arrays.asList("sourceName", "registNum", "realNameNum", "vipNum", "rechargeNum", "investNum", "lostNum", "investMoney", "rechargeMoney", "sourceFromStr",
					"sourceEndTimeStr", "remark"));
			String sheetName = "";
			if (operationCnd.getBeginTime() == null && operationCnd.getEndTime() == null) {
				sheetName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			} else if (operationCnd.getBeginTime() == null) {
				sheetName = new SimpleDateFormat("yyyy-MM-dd").format(operationCnd.getEndTime());
			} else if (operationCnd.getEndTime() != null) {
				sheetName = new SimpleDateFormat("yyyy-MM-dd").format(operationCnd.getBeginTime());
			} else {
				sheetName = new SimpleDateFormat("yyyy-MM-dd").format(operationCnd.getBeginTime()) + "-" + new SimpleDateFormat("yyyy-MM-dd").format(operationCnd.getEndTime());
			}
			response.reset();
			ExportExcelUtils.exportExcel("推广统计.xls", sheetName, realheaders, propertiesList, operationNormalService.queryRecommendCountList(operationCnd), response, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据条件查询推广统计出错", e);
		}
	}
	

}
