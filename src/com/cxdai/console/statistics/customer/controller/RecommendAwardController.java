package com.cxdai.console.statistics.customer.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.service.ActiveRecommendService;
import com.cxdai.console.statistics.customer.vo.InvetedUserVo;
import com.cxdai.console.statistics.customer.vo.RecommendAwardCnd;

/**
 * 推荐奖励
 * @author Administrator
 */
@Controller
@RequestMapping(value="/customer/recommendaward")
public class RecommendAwardController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RecommendAwardController.class);
	
	@Autowired
	private ActiveRecommendService activeRecommendService;
	
	@RequestMapping("/main")
	public ModelAndView toRecommendAward(){
		return new ModelAndView("/statistics/customer/recoaward/main");
	}
	
	/**
	 * 推荐奖励列表
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageRecommendAwardList(@ModelAttribute RecommendAwardCnd recommendAwardCnd,@PathVariable Integer pageNo,HttpServletRequest request){
		Page page = null;
		InvetedUserVo invetedUserVo = new InvetedUserVo();
		try {
			
			Integer month = new Integer(request.getParameter("beginTime"));
			String year = new String(request.getParameter("beginYear"));
			
			Calendar c=Calendar.getInstance();
			c.set(Integer.valueOf(year), month, 1);
			
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
	        String dateStr = sdf.format(c.getTime());  
			
			recommendAwardCnd.setMonth(month);
			recommendAwardCnd.setYear(year);
			recommendAwardCnd.setTjDate(dateStr);
		
			page = activeRecommendService.queryPageRecommendAwardList(recommendAwardCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			//查询累积
			invetedUserVo = activeRecommendService.getRecharge();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询推荐奖励错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/recoaward/list").addObject("page", page).addObject("invetedUserVo", invetedUserVo);
	}
	
	/**
	 * 根据推荐人查询被推荐人明细(直接)
	 * @param recommendAwardCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/inviteuserpagelist/{id}/{year}/{pageNo}")
	public ModelAndView queryInviteUserPageByUser(@ModelAttribute RecommendAwardCnd recommendAwardCnd,@PathVariable Integer id, @PathVariable String year,@PathVariable Integer pageNo){
		Page inviteUserPage = null;
		InvetedUserVo invetedUserVo = new InvetedUserVo();
		try {
			//查询详细
			invetedUserVo = activeRecommendService.queryDetailInviteUserPageById(id);
			recommendAwardCnd.setUserId(invetedUserVo.getUserId());
			recommendAwardCnd.setType(0);
			recommendAwardCnd.setMonth(invetedUserVo.getMonth());
			recommendAwardCnd.setYear(year);
			
			Calendar c=Calendar.getInstance();
			c.set(Integer.valueOf(year), invetedUserVo.getMonth()-1, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    String dateStr = sdf.format(c.getTime()); 
		    recommendAwardCnd.setTjDate(dateStr);
		    
			inviteUserPage = activeRecommendService.queryInviteUserPageByUser(recommendAwardCnd,pageNo, Constants.CONSOLE_PAGE_SIZE2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推荐人查看被推荐人明细（直接）错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/recoaward/inviteuser").addObject("id", id).addObject("page", inviteUserPage).addObject("invetedUserVo", invetedUserVo).addObject("year",year);
	}
	
	/**
	 * 根据推荐人查询被推荐人明细（间接）
	 * @param recommendAwardCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/userIndirectlist/{id}/{year}/{pageNo}")
	public ModelAndView queryIndirectPageByInviteUser(@ModelAttribute RecommendAwardCnd recommendAwardCnd,@PathVariable Integer id,@PathVariable String year,@PathVariable Integer pageNo){
		Page userIndirectPage = null;
		InvetedUserVo invetedUserVo = new InvetedUserVo();
		try {
			invetedUserVo = activeRecommendService.queryDetailInviteUserPageById(id);
			recommendAwardCnd.setUserId(invetedUserVo.getUserId());
			recommendAwardCnd.setType(1);
			recommendAwardCnd.setMonth(invetedUserVo.getMonth());
			Calendar c=Calendar.getInstance();
			c.set(Integer.valueOf(year), invetedUserVo.getMonth()-1, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    String dateStr = sdf.format(c.getTime()); 
		    recommendAwardCnd.setTjDate(dateStr);
		    
			userIndirectPage = activeRecommendService.queryInviteUserPageByUser(recommendAwardCnd,pageNo, Constants.CONSOLE_PAGE_SIZE2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推荐人查询被推荐人明细（间接）错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/recoaward/userindirect").addObject("id", id).addObject("page", userIndirectPage).addObject("invetedUserVo", invetedUserVo);
	}
	
	/**
	 * 根据被推荐人查询代收明细
	 * @param recommendAwardCnd
	 * @param id
	 * @return
	 */
	@RequestMapping("/usercollection/{id}/{invitedUserId}/{invitedUsername}/{year}/{pageNo}")
	public ModelAndView queryUsercollectionPageByInviteUser(@ModelAttribute RecommendAwardCnd recommendAwardCnd,@PathVariable Integer id,@PathVariable String year,@PathVariable Integer invitedUserId,@PathVariable String invitedUsername,@PathVariable Integer pageNo){
		Page usercollectionPage = null;
		InvetedUserVo invetedUserVo = new InvetedUserVo();
		try {
			invetedUserVo = activeRecommendService.queryDetailInviteUserPageById(id);
			recommendAwardCnd.setUserId(invetedUserVo.getUserId());
			recommendAwardCnd.setInvitedUserId(invitedUserId);
			recommendAwardCnd.setMonth(invetedUserVo.getMonth());
			Calendar c=Calendar.getInstance();
			c.set(Integer.valueOf(year), invetedUserVo.getMonth()-1, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    String dateStr = sdf.format(c.getTime()); 
		    recommendAwardCnd.setTjDate(dateStr);
		    
			usercollectionPage = activeRecommendService.queryUsercollectionPageByInviteUser(recommendAwardCnd, pageNo, Constants.CONSOLE_PAGE_SIZE3);
			invitedUsername = new String(invitedUsername.getBytes("ISO8859-1"),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("被推荐人查询代收明细错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/recoaward/usercollection").addObject("page", usercollectionPage).addObject("invitedUsername", invitedUsername).addObject("id", id).addObject("invitedUserId", invitedUserId).addObject("year", year);
	}
	
}
