package com.cxdai.console.account.reward.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.reward.service.PointsAwardService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;

/**
 * 积分奖励
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/account/pointaward")
public class PointAwardController extends BaseController {

	private static final Logger logger = Logger.getLogger(PointAwardController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private PointsAwardService pointsAwardService;

	@RequestMapping("/main")
	public ModelAndView forPointsAwardMain() {
		return new ModelAndView("/account/reward/pointaward/main");
	}

	/**
	 * 查询会员记录
	 * @param memberCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPointsAward(@ModelAttribute MemberCnd memberCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = memberService.queryMemberVoList(memberCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询会员记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/account/reward/pointaward/list").addObject("page", page);
	}

	/**
	 * 初始化积分奖励页面
	 */
	@RequestMapping("/intForPoint/{id}")
	public ModelAndView initForPointsAward(@PathVariable Integer id) {
		MemberVo memberVo = new MemberVo();
		try {
			memberVo = memberService.queryMemberById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/reward/pointaward/layer").addObject("memberVo", memberVo);
	}

	/**
	 * 积分确定
	 * @param request
	 * @return
	 */
	@RequestMapping("/subPointAward")
	@ResponseBody
	public MessageBox webAward(HttpServletRequest request) {
		Integer points = new Integer(request.getParameter("points"));
		Integer id = new Integer(request.getParameter("id"));
		MemberVo memberVo = new MemberVo();
		String remark = request.getParameter("verifyRemark");
		String detail = request.getParameter("detail");
		String result = null;
		try {
			memberVo = memberService.queryMemberById(id);
			if (memberVo.getIsFinancialUser().intValue() != 1) {
				return MessageBox.build("1", "非理财用户不可奖励元宝！");
			}
			if (points != null && !"".equals(points)) {
				result = pointsAwardService.pointsAward(memberVo.getId(), points, remark,detail);
			} else {
				return MessageBox.build("1", "元宝输入错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统出错，请稍后重试");
		}
		return new MessageBox("0", result);
	}

}
