package com.cxdai.console.statistics.account.controller;

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
 * 平台标总额
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/statistics/platformstandardcount")
public class PlatformStandardController extends BaseController {

	private static final Logger logger = Logger.getLogger(PlatformStandardController.class);
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forNormalIndex() {
		return new ModelAndView("/statistics/account/platformstandard/main");
	}
	
	/**
	 * 平台标总额统计结果
	 * @param operationCnd
	 * @return
	 */
	@RequestMapping("/normarlcount")
	public ModelAndView queryNormalData(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request) {
		Map<String, Object> operationMap = new HashMap<String, Object>();
		try {
			if(!StringUtils.isEmpty(request.getParameter("beginTimeTotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("beginTimeTotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endTimeTotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endTimeTotal")));
			}
			operationMap = operationNormalService.queryNormalData(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据条件查询平台标总额统计出错", e);
		}
		return new ModelAndView("/statistics/account/platformstandard/list").addObject("operationMap", operationMap);
	}
	
}
