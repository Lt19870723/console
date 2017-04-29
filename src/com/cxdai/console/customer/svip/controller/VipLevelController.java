package com.cxdai.console.customer.svip.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.Staff;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.svip.entity.VipLevelCnd;
import com.cxdai.console.customer.svip.service.VipLevelService;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;

@Controller
@RequestMapping(value="/vipLevel")
public class VipLevelController extends BaseController {

	private final static Logger logger = Logger.getLogger(VipLevelController.class);
	
	
	@Autowired
	private VipLevelService vipLevelService;
	
	@RequestMapping(value="/toVipLevelMain")
	@ResponseBody
	public ModelAndView toVipLevelMain(){
		return forword("/customer/svip/sVipMain");
	}
	
	
	@RequestMapping(value="/serachAll/{pageNo}")
	@ResponseBody
	public ModelAndView serachAll(@ModelAttribute VipLevelCnd vipLevelCnd,@PathVariable("pageNo") Integer pageNo){
		Page page = new Page();
		try{
			page = vipLevelService.queryTopSvipForPage(vipLevelCnd, WxContants.WX_PAGE_SIZE, pageNo);
		}catch (Exception e){
			logger.error("获取vip列表失败",e);
		}
		return forword("/customer/svip/sVipList").addObject("page", page);
	}
	

	@RequestMapping(value="/forSetSvip/{userId}")
	@ResponseBody
	public ModelAndView forSetSvip(@PathVariable("userId") Integer userId){
		return forword("/customer/svip/forsetSvip","userId", userId);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:设置终身顶级会员<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月16日 void
	 */

	@RequestMapping(value="/setSvipForUserId")
	@ResponseBody
	public MessageBox setSvipForUserId(@ModelAttribute VipLevelCnd vipLevelCnd,HttpServletRequest request) {
		String msg  ="";
		try {
			String ip = HttpTookit.getRealIpAddr(request);
			ShiroUser shiroUser = currentUser();
			msg  = vipLevelService.setSvipForUserId(vipLevelCnd.getUserId(), ip, vipLevelCnd.getRemark(), shiroUser);
			if("设置成功！".equals(msg)){
				return MessageBox.build("1", "设置成功");
			}
		} catch (Exception e) {
			logger.error("设置顶级会员失败",e);
		}
		return MessageBox.build("0", msg);
	}
	
}
