package com.cxdai.console.statistics.tender.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.registersource.service.SourceService;
import com.cxdai.console.statistics.tender.entity.MonthlyInvestVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;
import com.cxdai.console.system.entity.Configuration;

/**
 * 月投资量分析
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/tender/monthinvestcount")
public class MonthlyInvestController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(MonthlyInvestController.class);
	@Autowired
	private SourceService sourceService;
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forMonthlyInvestCountIndex(){
		List<Configuration> list = null;
		try {
			 list = sourceService.querySourceList(); //初始化下拉框
		} catch (Exception e) {
			logger.error("月投资量统计页面获取source来源下拉框异常信息："+e);
		}
		return new ModelAndView("/statistics/tender/monthlyinvest/main").addObject("list", list);
	}
	
	/**
	 * 查询结果并封装
	 */
	@RequestMapping("/monthlist/{pageNo}")
	public ModelAndView queryMonthlyInvestCountList(@ModelAttribute OperationCnd operationCnd,@PathVariable Integer pageNo,HttpServletRequest request) {
		Page page = new Page(pageNo,10);
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		List<String> headers = new ArrayList<String>();
		List<List<Object>> resultList = new ArrayList<List<Object>>();
		try {
			begin.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-01"));
			/**
			 * 初始化两个参数
			 */
			headers = null;
			resultList = null;
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			if (operationCnd.getBeginTime() == null || operationCnd.getBeginTime().getTime() < begin.getTimeInMillis()) {
				operationCnd.setBeginTime(begin.getTime());
			}
			if (operationCnd.getEndTime() == null || operationCnd.getEndTime().getTime() > end.getTimeInMillis()) {
				operationCnd.setEndTime(end.getTime());
			}
			headers = new ArrayList<String>();
			// 重置开始和结束月份：
			// 重置开始和结束月份：
			begin.setTime(operationCnd.getBeginTime());
			end.setTime(operationCnd.getEndTime());
			begin.set(Calendar.DAY_OF_MONTH, 1);
			end.set(Calendar.DAY_OF_MONTH, 1);
			operationCnd.setBeginTime(begin.getTime());
			operationCnd.setEndTime(end.getTime());
			while (begin.getTimeInMillis() <= end.getTimeInMillis()) {
				headers.add(new SimpleDateFormat("yyyy-MM").format(begin.getTime()));
				begin.add(Calendar.MONTH, 1);
			}
			operationCnd.setHeaders(headers);
			operationCnd.setBeginTimeStr(operationCnd.getBeginTime().getTime() / 1000 + "");
			end.add(Calendar.MONTH, 1);
			operationCnd.setEndTimeStr(end.getTimeInMillis() / 1000 + "");
			List<MonthlyInvestVo> list = operationNormalService.queryMonthlyInvest(operationCnd, page);
			resultList = new ArrayList<List<Object>>();
			long oldId = -1;
			List<Object> tempList = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++) {
				MonthlyInvestVo vo = list.get(i);
				if (vo.getUserId() == oldId) {
					for (int j = tempList.size() - 2; j < headers.size(); j++) {
						if (vo.getMonthStr().equals(headers.get(j))) {
							tempList.add(vo.getMoney());
							break;
						} else {
							tempList.add(0);
							continue;
						}
					}
				} else {
					if (tempList.size() > 0) {
						for (int k = tempList.size() - 2; k < headers.size(); k++) {
							tempList.add(0);
						}
						resultList.add(tempList);
					}
					tempList = new ArrayList<Object>();
					tempList.add("" + vo.getUserId());
					tempList.add(vo.getUserName());
					oldId = vo.getUserId();
					for (int j = 0; j < headers.size(); j++) {
						if (vo.getMonthStr().equals(headers.get(j))) {
							tempList.add(vo.getMoney());
							break;
						} else {
							tempList.add(0);
							continue;
						}
					}
				}
			}

			if (tempList.size() > 0) {
				for (int k = tempList.size() - 2; k < headers.size(); k++) {
					tempList.add(0);
				}
				resultList.add(tempList);
			}
			page.setResult(resultList);
			end.add(Calendar.DAY_OF_MONTH, -1);
			operationCnd.setEndTime(end.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/tender/monthlyinvest/list").addObject("page", page).addObject("headers", headers);
	}
	
	
	
}
