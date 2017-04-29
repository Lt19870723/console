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
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ExportExcelUtils;
/**
 * @author WangQianJin
 * @title 注册表单明细 
 * @date 2016年1月14日
 */
@Controller
@RequestMapping(value="/customer/registerFormDetail")
public class RegisterFormDetailController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RegisterFormDetailController.class);
	
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
		return new ModelAndView("/statistics/customer/registerFormDetail/main").addObject("sourceList", sourceList);
	}
	
	/**
	 * 注册表单明细结果
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
			
			if(operationCnd.getBeginTime()!=null && operationCnd.getEndTime()!=null){
				page = operationNormalService.queryRegisterFormDetailPage(operationCnd, pageNo, Constants.CONSOLE_PAGE_SIZE2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册表单明细结果错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/registerFormDetail/list").addObject("page", page);
	}
	
	/**
	 * 注册表单明细导出excel
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
			ArrayList<String> realheaders = new ArrayList<String>();
			realheaders.addAll(Arrays.asList("用户名", "用户状态", "真实姓名", "邮箱", "手机", "注册时间", "来源", "资产总额", "是否实名认证", 
				"是否手机认证 ", "是否邮箱认证", "是否VIP", "是否充值", "充值金额", "是否投标", "投标金额", "是否提现", "提现金额", "充值和提现差值"));
			ArrayList<String> propertiesList = new ArrayList<String>();
			propertiesList.addAll(Arrays.asList("username", "status", "realname", "email", "mobile", "registerTime", "source", "total", "realPassed", 
				"mobilePassed","emailPassed", "vipPassed", "rechargePassed", "rechargeMoney", "tenderPassed", "tenderMoney", "cashPassed", "cashMoney", "diffMoney"));			
			response.reset();
			ExportExcelUtils.exportExcel("注册表单明细.xls", "注册表单明细", realheaders, propertiesList, operationNormalService.queryRegisterFormDetailListAll(operationCnd), response, false);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据条件查询注册表单明细出错", e);
		}
	}
	

}
