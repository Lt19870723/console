 package com.cxdai.console.borrow.manage.controller;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.service.BorrowUserRechangeAndReplayService;
import com.cxdai.console.borrow.manage.vo.BorrowUserRechangeVo;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:借款者 充值<br />
 * </p>
 * @title InterestPenaltyController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "/borrow/manage/repayRecharge")
public class RepayChargeController extends BaseController {
	
private final static Logger logger=Logger.getLogger(RepayChargeController.class);
@Autowired
private BRepaymentRecordService bRepaymentRecordService;
@Autowired
private MemberService memberService;
@Autowired
private AccountService accountService;
@Autowired
private BorrowManageService borrowManageService;
@Autowired
private BorrowUserRechangeAndReplayService borrowUserRechangeAndReplayService;
/**
 * 
 * <p>
 * Description:进入借款标罚息列表<br />
 * </p>
 * @author yubin
 * @version 0.1 2015年6月28日
 * @return
 * ModelAndView
 */
@RequestMapping("/main")
public ModelAndView main(){
	
	return  new ModelAndView("/borrow/manage/repayRecharge/main");
}
/**
 * 
 * <p>
 * Description:客户信息查询<br />
 * </p>
 * @author yubin
 * @version 0.1 2015年7月3日
 * @param memberCnd
 * @param pageNo
 * @return
 * ModelAndView
 */
 @RequestMapping("/list/{pageNo}")
	public ModelAndView searchMemberList(@ModelAttribute MemberCnd memberCnd, @PathVariable Integer pageNo) {
    	 
    	Page page=null;
    	try {
			 page=memberService.queryMemberVoListForPages(memberCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	return new ModelAndView("/borrow/manage/repayRecharge/list").addObject("page", page);
   }

/**
 * 
 * <p>
 * Description:初始化充值界面<br />
 * </p>
 * @author yubin
 * @version 0.1 2015年7月3日
 * @param userId
 * @return
 * ModelAndView
 */
@RequestMapping("/initRecharge")
public ModelAndView initRecharge( @RequestParam(value = "id", required = false) Integer userId){
	BorrowUserRechangeVo borrowUserRechangeVo = null;
	AccountVo accountVo = null;
	System.out.println(userId);
	try {
		MemberVo memberVo = memberService.queryMemberById(userId);
		if (memberVo != null) {
			borrowUserRechangeVo=new BorrowUserRechangeVo();
			borrowUserRechangeVo.setUserName(memberVo.getUsername());
		}
		borrowUserRechangeVo.setReChangeMoney(null);
		accountVo = accountService.queryAccountByUserId(userId);
	} catch (Exception e) {
		logger.error("初始化借款者充值失败！", e);
	}
	return  new ModelAndView("/borrow/manage/repayRecharge/borrowUserRechangeAdd")
	                           .addObject("borrowUserRechangeVo", borrowUserRechangeVo)
	                           .addObject("accountVo", accountVo)
	                           .addObject("userId",userId);
}
/**
 * 
 * <p>
 * Description:借款者充值接口<br />
 * </p>
 * @author yubin
 * @version 0.1 2015年7月3日
 * @return
 * MessageBox
 */
@RequestMapping("/doBorrowUserRechange")
@ResponseBody
public  MessageBox doReplay(@ModelAttribute BorrowUserRechangeVo borrowUserRechangeVo,
		                    @RequestParam(value = "id", required = false) Integer userId ) {
	String message=null;
	try { 
		String userName = borrowUserRechangeVo.getUserName();
	 
		if (StringUtils.isEmpty(userName)) {
			message = "用户名不存在";
			
		}
		Integer memberId = memberService.getMemberIdByUserName(userName);
		if (memberId == null) {
			message = "用户名不存在";
		}
		MemberVo memberVo = memberService.queryMemberById(memberId);
		if (memberVo == null) {
			message = "用户名不存在";
			
		}
		if (!memberVo.getUsername().equals(userName)) {
			message = "用户名不存在";
			
		}
		if (memberVo.getIsFinancialUser() == 1) {
			message = "【" + userName + "】是理财用户，无法充值！";
			
		}

		BigDecimal reChangeMoney = borrowUserRechangeVo.getReChangeMoney();
		if (reChangeMoney == null) {
			message = "请输入充值金额";
			
		}
		if (reChangeMoney.compareTo(new BigDecimal(0)) == 0) {
			message = "输入金额有误";
			
		}
		// 获取ip
		ShiroUser shiroUser = ShiroUtils.currentUser();
		String ip = HttpTookit.getRealIpAddr(currentRequest());
		message = borrowUserRechangeAndReplayService.savepay(userId, borrowUserRechangeVo, ip, shiroUser,null);
		if(message.equals("success")){
			return  MessageBox.build("1", "充值成功"); 
		}
		return  MessageBox.build("0", message);
	} catch (Exception e) {
		message = "充值失败！";
		return  MessageBox.build("0", message);
	}
}   	
}
