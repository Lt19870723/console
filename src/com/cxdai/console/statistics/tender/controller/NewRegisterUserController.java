package com.cxdai.console.statistics.tender.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;
/**
 * 投标分析--新用户注册信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/tender/newregisterinfo")
public class NewRegisterUserController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(NewRegisterUserController.class);
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forNewRegisterIndex(){
		return new ModelAndView("/statistics/tender/newregister/main");
	}
	
	/**
	 * 新用户注册信息结果
	 * @param operationCnd
	 * @return
	 */
	@RequestMapping("/newregistercount")
	public ModelAndView queryNewRegisterData(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request){
		Map<String, Object> operationMap = new HashMap<String, Object>();
		try {
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			operationMap = operationNormalService.queryNewRegisterData(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新用户注册信息错误信息："+e);
		}
		return new ModelAndView("/statistics/tender/newregister/list").addObject("operationMap", operationMap);
	}

}
