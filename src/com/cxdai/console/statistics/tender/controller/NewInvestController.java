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
import com.cxdai.console.statistics.tender.entity.NewInvestCountVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;

/**
 * 新增投资人数统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/tender/newinvest")
public class NewInvestController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(NewInvestController.class);
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forNewInvestCountIndex(){
		return new ModelAndView("/statistics/tender/newinvest/main");
	}
	
	/**
	 * 新增投资人数统计
	 * @param operationCnd
	 * @return
	 */
	@RequestMapping("/newinvestcount")
	public ModelAndView queryNewInvestCount(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request){
		NewInvestCountVo newInvestCountVo = new NewInvestCountVo();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(format.parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(format.parse(request.getParameter("endtotal")));
			}
			newInvestCountVo = operationNormalService.queryNewInvestCount(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增投资人数统计错误信息:"+e);
		}
		return new ModelAndView("/statistics/tender/newinvest/list").addObject("newInvestCountVo", newInvestCountVo);
	}

}
