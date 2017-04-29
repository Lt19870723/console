package com.cxdai.console.account.reward.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.console.account.reward.service.WebAwardService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;

/**
 * 奖励管理
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/account/webaward")
public class WebAwardController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(WebAwardController.class);
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private WebAwardService webAwardService;
	
	@RequestMapping("/main")
	public ModelAndView forWebAwardMain(){
		return new ModelAndView("/account/reward/webaward/main");
	}
	
	/**
	 * 查询会员记录
	 * @param memberCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchWebAward(@ModelAttribute MemberCnd memberCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = memberService.queryMemberVoList(memberCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询会员记录错误信息："+e.getMessage());
		}
		return new ModelAndView("/account/reward/webaward/list").addObject("page", page);
	}
	
	/**
	 * 初始化网站奖励页面
	 */
	@RequestMapping("/intForWeb/{id}")
	public ModelAndView initForWebAward(@PathVariable Integer id){
		MemberVo memberVo = new MemberVo();
		AccountVo accountVo = new AccountVo(); 
		try {
			memberVo = memberService.queryMemberById(id);
			accountVo = accountService.queryAccountByUserId(memberVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/reward/webaward/layer").addObject("memberVo", memberVo).addObject("accountVo", accountVo);
	}
	
	@RequestMapping("/subWebAward/{id}")
	@ResponseBody
	public MessageBox webAward(HttpServletRequest request,@PathVariable Integer id){
		MemberVo memberVo = new MemberVo();
		String money = request.getParameter("money");
		String remark = request.getParameter("verifyRemark");
		String result = null;
		try {
			if (money != null && new BigDecimal(money) != null) {
				memberVo = memberService.queryMemberById(id);
				result = webAwardService.webaward(memberVo.getId(), new BigDecimal(money), remark, request);
			} else {
				return MessageBox.build("1", "金额输入错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统出错，请稍后重试");
		}
		return new MessageBox("0", result);
	}
	
}
