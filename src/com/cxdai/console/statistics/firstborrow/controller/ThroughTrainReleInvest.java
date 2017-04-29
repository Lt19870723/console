package com.cxdai.console.statistics.firstborrow.controller;

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
import com.cxdai.console.statistics.tender.entity.FirstInfoCountVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;

/**
 * 直通车分析--发布和投资
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/statistics/trainreleinvestcount")
public class ThroughTrainReleInvest extends BaseController {

	private static final Logger logger = Logger.getLogger(ThroughTrainReleInvest.class);
	@Autowired
	private OperationNormalService operationNormalService;
	
	@RequestMapping("/main")
	public ModelAndView forfirstInfoCountIndex(){
		return new ModelAndView("/statistics/firstborrow/releinvest/main");
	}
	
	/**
	 * 发布和投资统计结果
	 */
	@RequestMapping("/firstinfocount")
	public ModelAndView queryfirstInfoCount(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request){
		FirstInfoCountVo firstInfoCountVo = new FirstInfoCountVo();
		try {
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtotal")));
			}
			firstInfoCountVo = operationNormalService.queryfirstInfoCount(operationCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("直通车发布和投资统计错误信息："+e);
		}
		return new ModelAndView("/statistics/firstborrow/releinvest/list").addObject("firstInfoCountVo", firstInfoCountVo);
	}
}
