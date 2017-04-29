package com.cxdai.console.statistics.tender.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.tender.entity.MoreInvestCountVo;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.service.OperationNormalService;

/**
 * 复投分析
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/tender/moreinvestcount")
public class MoreInvestController extends BaseController {

	private static final Logger logger = Logger.getLogger(MoreInvestController.class);
	
	@Autowired
	private OperationNormalService operationNormalService;
	
	/**
	 * 进入复投分析页面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView forMoreInvestCountIndex(){
		return new ModelAndView("/statistics/tender/moreinvest/main");
	}
	
	/**
	 * 复投统计结果
	 * @return
	 */
	@RequestMapping("/morecount")
	public ModelAndView queryMoreInvestCount(@ModelAttribute OperationCnd operationCnd,HttpServletRequest request){
		List<MoreInvestCountVo> moreInvestCountList = null;
		Object total = new MoreInvestCountVo();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtils.isEmpty(request.getParameter("begintotal"))){
				operationCnd.setBeginTime(format.parse(request.getParameter("begintotal")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endtotal"))){
				operationCnd.setEndTime(format.parse(request.getParameter("endtotal")));
			}
			moreInvestCountList = operationNormalService.querymoreInvestCount(operationCnd);
			if(moreInvestCountList!=null){
				for(MoreInvestCountVo vo:moreInvestCountList){
					((MoreInvestCountVo) total).setNum(((MoreInvestCountVo) total).getNum() + vo.getNum());
					((MoreInvestCountVo) total).setTimes(((MoreInvestCountVo) total).getTimes() + vo.getNum() * vo.getTimes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("复投统计结果错误信息："+e);
		}
		return new ModelAndView("/statistics/tender/moreinvest/list").addObject("total", total).addObject("moreInvestCountList", moreInvestCountList);
	}
	
}
