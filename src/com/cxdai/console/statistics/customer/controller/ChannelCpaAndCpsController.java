package com.cxdai.console.statistics.customer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.registersource.service.SourceService;
import com.cxdai.console.statistics.tender.entity.CpaAndCpsVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ExportExcelUtils;

/**
 * @author WangQianJin
 * @title 各渠道CPA与CPS
 * @date 2016年3月16日
 */
@Controller
@RequestMapping(value="/customer/channelCpaAndCps")
public class ChannelCpaAndCpsController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ChannelCpaAndCpsController.class);
	
	@Autowired
	private SourceService sourceService;
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		List<Configuration> sourceList = new ArrayList<Configuration>();
		try {
			sourceList = sourceService.querySourceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/customer/channelCpaAndCps/main").addObject("sourceList", sourceList);
	}
	
	/**
	 * CPA与CPS查询结果
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView list(HttpServletRequest request,@PathVariable Integer pageNo){
		Page page = null;
		OperationCnd operationCnd = new OperationCnd();
		try {
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
				operationCnd.setEndTime(DateUtils.convert2EndDate(operationCnd.getEndTime()));
			}
			if(!StringUtils.isEmpty(request.getParameter("source"))){
				operationCnd.setSource(request.getParameter("source"));
			}
			if(!StringUtils.isEmpty(request.getParameter("sourceType"))){
				operationCnd.setSourceType(request.getParameter("sourceType"));
			}
			
			if(operationCnd.getBeginTime()!=null && operationCnd.getEndTime()!=null){
				page = operationNormalService.queryCpaAndCpsPage(operationCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CPA与CPS查询结果错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/channelCpaAndCps/list").addObject("page", page).addObject("source", operationCnd.getSource());
	}
	
	/**
	 * CPA与CPS查询结果导出excel
	 */
	@RequestMapping("/export")
	public void export(HttpServletResponse response,HttpServletRequest request) {
		try {
			OperationCnd operationCnd = new OperationCnd();
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
				operationCnd.setEndTime(DateUtils.convert2EndDate(operationCnd.getEndTime()));
			}
			if(!StringUtils.isEmpty(request.getParameter("source"))){
				operationCnd.setSource(request.getParameter("source"));
			}
			if(!StringUtils.isEmpty(request.getParameter("sourceType"))){
				operationCnd.setSourceType(request.getParameter("sourceType"));
			}
			ArrayList<String> realheaders = new ArrayList<String>();
			ArrayList<String> propertiesList = new ArrayList<String>();
			String title="";
			if("17".equals(operationCnd.getSource())){
				//投之家
				realheaders.addAll(Arrays.asList("用户名", "绑定注册时间", "投资时间", "投资金额", "期限", "是否债转"));
				propertiesList.addAll(Arrays.asList("username", "bingTime", "tenderTime", "bidAmount", "timeLimit", "isTransfer"));
				title="投之家"+operationCnd.getSourceType();
			}else if("13".equals(operationCnd.getSource())){
				//网贷天眼
				realheaders.addAll(Arrays.asList("用户名", "绑定注册时间", "投资时间", "投资金额", "期限", "是否债转"));
				propertiesList.addAll(Arrays.asList("username", "bingTime", "tenderTime", "bidAmount", "timeLimit", "isTransfer"));
				title="网贷天眼"+operationCnd.getSourceType();
			}else if("60".equals(operationCnd.getSource())){
				//多麦网
				realheaders.addAll(Arrays.asList("用户ID", "投资时间", "投资金额", "期限", "投资ID", "标名称", "标ID"));
				propertiesList.addAll(Arrays.asList("userId", "tenderTime", "bidAmount", "timeLimit", "tenderId", "borrowName", "borrowId"));
				title="多麦网";
			}else if("37".equals(operationCnd.getSource())){
				//富爸爸
				realheaders.addAll(Arrays.asList("用户名", "注册时间", "投资时间", "投资金额", "期限"));
				propertiesList.addAll(Arrays.asList("username", "bingTime", "tenderTime", "bidAmount", "timeLimit"));
				title="富爸爸";
			}else if("38".equals(operationCnd.getSource())){
				//领克特
				realheaders.addAll(Arrays.asList("来源", "订单编号", "商品编号", "会员名称", "注册时间", "投资时间", "单次投资金额", "单标期限"));
				propertiesList.addAll(Arrays.asList("source", "borrowId", "tenderId", "username", "bingTime", "tenderTime", "bidAmount", "timeLimit"));
				title="领克特";
			}						
			response.reset();			
			List<CpaAndCpsVo> exportList=operationNormalService.queryCpaAndCpsListAll(operationCnd);
			ExportExcelUtils.exportExcel(title+"查询结果.xls", title+"查询结果", realheaders, propertiesList, exportList, response, false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CPA与CPS查询结果导出出错", e);
		}
	}
	

}
