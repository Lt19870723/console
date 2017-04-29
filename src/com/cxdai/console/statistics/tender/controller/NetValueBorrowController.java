package com.cxdai.console.statistics.tender.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.tender.entity.NetValueBorrowCountVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;

/**
 * 净值标详情统计
 * @author Administrator
 */
@Controller
@RequestMapping(value="/tender/netvalueborrow")
public class NetValueBorrowController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(NetValueBorrowController.class);
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forNetValueBorrowIndex(){
		return new ModelAndView("/statistics/tender/netvalue/main");
	}
	
	/**
	 * 净值标详情统计结果
	 * @param operationCnd
	 * @return
	 */
	@RequestMapping("/netvaluecount")
	public ModelAndView queryNetValueBorrowCount(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request){
		NetValueBorrowCountVo netValueBorrowCountVo = new NetValueBorrowCountVo();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(format.parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(format.parse(request.getParameter("endtotal")));
			}
			netValueBorrowCountVo = operationNormalService.queryNetValueBorrowCount(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("净值标详情统计结果错误信息："+e);
		}
		return new ModelAndView("/statistics/tender/netvalue/list").addObject("netValueBorrowCountVo", netValueBorrowCountVo);
	}

}
