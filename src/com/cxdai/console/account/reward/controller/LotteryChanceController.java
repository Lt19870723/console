package com.cxdai.console.account.reward.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.lottery.service.ChanceInfoService;
import com.cxdai.console.lottery.service.ChanceRuleInfoService;
import com.cxdai.console.lottery.vo.ChanceRuleInfo;
/**
 * 奖励抽奖机会
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/account/lotteryChance")
public class LotteryChanceController extends BaseController {
	private static final Logger logger = Logger.getLogger(PointAwardController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private ChanceRuleInfoService chanceRuleInfoService;
	@Autowired
	private ChanceInfoService chanceInfoService;

	@RequestMapping("/main")
	public ModelAndView forPointsAwardMain() {
		return new ModelAndView("/account/reward/lotteryChance/main");
	}

	/**
	 * 查询会员记录
	 * 
	 * @param memberCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPointsAward(@ModelAttribute MemberCnd memberCnd,
			@PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = memberService.queryMemberVoList(memberCnd,Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询会员记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/account/reward/lotteryChance/list")
				.addObject("page", page);
	}

	/**
	 * 初始化抽奖机会奖励页面
	 * @throws Exception 
	 */
	@RequestMapping("/intForLottery/{id}")
	public ModelAndView initForWebAward(@PathVariable Integer id) throws Exception {
		MemberVo memberVo = new MemberVo();
		List<ChanceRuleInfo> chanceRuleInfoList = new ArrayList();
		Integer sumNum = 0;
		try {
			chanceRuleInfoList = chanceRuleInfoService.queryAllChanceRuleInfos();
			memberVo = memberService.queryMemberById(id);
			sumNum = chanceInfoService.querySumLotteryNum(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/reward/lotteryChance/layer").addObject("memberVo", memberVo).addObject("chanceRuleInfoList",chanceRuleInfoList).addObject("sumNum",sumNum);
	}

	 @RequestMapping("/addOrUpdateLottery")
	 @ResponseBody
	 public MessageBox webAward(HttpServletRequest request){
		 String result = "奖励抽奖机会成功";
		 
		 String lotteryChanceRuleInfo= request.getParameter("lotteryChanceRuleInfoId");  //抽奖机会类型
		 Integer lotteryChanceRuleInfoId;
		 if(StringUtils.isNotEmpty(lotteryChanceRuleInfo)&&lotteryChanceRuleInfo.matches("[0-9]+")){
			 lotteryChanceRuleInfoId = Integer.parseInt(lotteryChanceRuleInfo);
		 } else {
			 return MessageBox.build("1", "抽奖机会类型输入错误,请重新输入！");
		 }
		 
		 String lotteryNumStr = request.getParameter("lotteryNum"); //奖励抽奖次数
		 Integer lotteryNum;
		 if(StringUtils.isNotEmpty(lotteryNumStr) && lotteryNumStr.matches("[0-9]+")){
			 lotteryNum = Integer.parseInt(lotteryNumStr);
		 } else {
			 return MessageBox.build("1", "奖励抽奖次数输入错误,请重新输入！");
		 }		 
		 
		 String remark = request.getParameter("remark"); //备注 
		 if(StringUtils.isEmpty(remark)){
			 return MessageBox.build("1", "备注不能为空,请重新输入！");
		 }
		 
		 String username = request.getParameter("username"); //用户姓名
		 if(StringUtils.isEmpty(username)){
			 return MessageBox.build("1", "系统出错，请稍后重试！");
		 }
		 String userIdStr = request.getParameter("userId"); //用户Id
		 Integer userId;
		 if(StringUtils.isNotEmpty(userIdStr)){
			 userId = Integer.parseInt(userIdStr);
		 } else {
			 return MessageBox.build("1", "系统出错，请稍后重试！");
		 }
		 
		 try {
			 result = chanceInfoService.insertLotteryChanceInfo(lotteryChanceRuleInfoId, lotteryNum, remark, username, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageBox.build("1", "系统出错，请稍后重试");
		}
	 	return new MessageBox("0", result);
	 }

}
