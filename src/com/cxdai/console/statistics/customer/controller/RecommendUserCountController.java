package com.cxdai.console.statistics.customer.controller;

import java.text.SimpleDateFormat;

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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.service.ActiveRecommendService;
import com.cxdai.console.statistics.customer.vo.RecommendUserCountCnd;

/**
 * 推荐个人
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/customer/recommentuser")
public class RecommendUserCountController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RecommendUserCountController.class);
	
	@Autowired
	private ActiveRecommendService activeRecommendService;
	
	@RequestMapping("/main")
	public ModelAndView toRecommendUserCount(){
		return new ModelAndView("/statistics/customer/recouser/main");
	}
	
	/**
	 * 推荐人数列表
	 * @param recommendUserCountCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageRecommendUserCountList(@ModelAttribute RecommendUserCountCnd recommendUserCountCnd,@PathVariable Integer pageNo,HttpServletRequest request){
		Page page = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
				recommendUserCountCnd.setInvitedRegistBeginTime(format.parse(request.getParameter("beginTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("endTime"))){
				recommendUserCountCnd.setInvitedRegistEndTime(format.parse(request.getParameter("endTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("successbeginTime"))){
				recommendUserCountCnd.setInvitedSuccessBeginTime(format.parse(request.getParameter("successbeginTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("successendTime"))){
				recommendUserCountCnd.setInvitedSuccessEndTime(format.parse(request.getParameter("successendTime")));
			}
			if(!StringUtils.isEmpty(request.getParameter("isRecommendSuccess"))){
				recommendUserCountCnd.setIsRecommendSuccess(request.getParameter("isRecommendSuccess"));
			}
			page = activeRecommendService.queryPageRecommendUserCountList(recommendUserCountCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询推荐人数错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/recouser/list").addObject("page", page);
	}
	
	

}
