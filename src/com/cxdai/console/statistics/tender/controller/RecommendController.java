package com.cxdai.console.statistics.tender.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;

/**
 * 投标分析--推荐情况
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/tender/recommendinfo")
public class RecommendController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RecommendController.class);
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forRecommendIndex(){
		return new ModelAndView("/statistics/tender/recommend/main");
	}
	
	/**
	 * 推荐新注册用户统计结果
	 * @return
	 */
	@RequestMapping("/recommendcount")
	public ModelAndView queryRecommendData(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request){
		Map<String, Object> operationMap = new HashMap<String, Object>();
		try {
			operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			operationMap = operationNormalService.queryRecommendData(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推荐新注册用户统计错误信息："+e);
		}
		return new ModelAndView("/statistics/tender/recommend/list").addObject("operationMap", operationMap);
	}

}
