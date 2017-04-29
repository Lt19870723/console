package com.cxdai.console.customer.information.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.cxdai.console.common.statics.GetMacAddress;
import com.cxdai.console.customer.information.entity.Member;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.service.MemberClearService;
import com.cxdai.console.customer.information.vo.MemberClearVo;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:客户管理-客户信息-注销<br />
 * </p>
 * 
 * @title MemberClearController.java
 * @package com.cxdai.console.customer.information.controller
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Controller
@RequestMapping(value = "/customer/memberclear")
public class MemberClearController extends BaseController {

	private final static Logger logger = Logger.getLogger(MemberClearController.class);
	@Autowired
	private MemberClearService memberClearService;
	
	@Autowired
	private MemberMapper memberMapper;

	/**
	 * <p>
	 * Description:客户注销主页面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView cleanIndex() {
		return new ModelAndView("/customer/information/clear/index");
	}

	/**
	 * <p>
	 * Description:注销用户-查询用户信息<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/query/{pageNo}")
	public ModelAndView clearList(@ModelAttribute MemberCnd memberCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = memberClearService.queryMemberVoListForPages(memberCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error("查询用户信息失败：", e);
		}
		return new ModelAndView("/customer/information/clear/list").addObject("page", page);
	}

	/**
	 * <p>
	 * Description:注销用户<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberId
	 * @param request
	 * @return MessageBox
	 */
	@RequestMapping(value = "/clear/{memberId}")
	@ResponseBody
	public MessageBox clearMemberInfo(@PathVariable Integer memberId, HttpServletRequest request) {
		try {
			
			Member member=memberMapper.queryById(memberId);
			if(member!=null && member.getStatus()!=null){
				if(member.getStatus().intValue()==-1){
					return new MessageBox("1", "此用户已注销！");
				}
			}
			
			MemberClearVo vo = new MemberClearVo();
			vo.setMEMBERID((memberId));
			Date date = new Date();
			String dateFmt = DateUtils.format(date, DateUtils.YMD_HMS);
			String addtime = DateUtils.dayOffset(DateUtils.parse(dateFmt, DateUtils.YMD_HMS), 0).getTime() / 1000 + "";
			vo.setADD_TIME(addtime);

			String ip = HttpTookit.getRealIpAddr(request);
			String mac = GetMacAddress.getMacAddress();

			vo.setADDIP(ip);
			vo.setMAC(mac);

			UserVo user = getCurrentUserVo();
			vo.setADDPEOPLE(String.valueOf(user.getId()));
			// 注销客户
			memberClearService.clearMemberInfo(vo);

		} catch (Exception e) {
			logger.error("注销失败：", e);
			return new MessageBox("1", "注销失败！");
		}
		return new MessageBox("0", "注销成功！");
	}

}
