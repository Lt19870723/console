package com.cxdai.console.account.risk.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.risk.entity.RiskMargin;
import com.cxdai.console.account.risk.service.RiskMarginService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.HttpTookit;

/**
 * 风险备用金
 * @author Administrator
 */
@Controller
@RequestMapping(value="/account/riskMargin")
public class RiskMarginController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RiskMarginController.class);
	
	@Autowired
	private RiskMarginService riskMarginService;
	
	@RequestMapping("/main")
	public ModelAndView forRiskMarginMain(){
		RiskMargin riskMargin = new RiskMargin();
		try {
			riskMargin = riskMarginService.queryRiskMargin();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询风险备用金错误信息："+e);
		}
		return new ModelAndView("/account/risk/riskmargin/list").addObject("riskMargin", riskMargin);
	}
	
	/**
	 *  没有用到
	 *  Description:查询风险备用金
	 */
	@RequestMapping("/riskMarginAccount")
	public ModelAndView searchRiskMargin(){
		RiskMargin riskMargin = new RiskMargin();
		try {
			riskMargin = riskMarginService.queryRiskMargin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/risk/riskmargin/list").addObject("riskMargin", riskMargin);
	}
	
	/**
	 * 
	 * <p>
	 * Description:进入风险备用金修改页面.<br />
	 * </p>
	 */
	@RequestMapping("/initRisk")
	public ModelAndView initRiskMargin(){
		RiskMargin riskMargin = new RiskMargin();
		try {
			riskMargin = riskMarginService.queryRiskMargin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/risk/riskmargin/update").addObject("riskMargin", riskMargin);
	}
	
	/**
	 * 
	 * <p>
	 * Description:修改风险备用金.<br />
	 * </p>
	 * @return
	 * int
	 */
	@RequestMapping("/updateRisk")
	@ResponseBody
	public MessageBox updateRiskMargin(HttpServletRequest request){
		String result = null;
		UserVo userVo = getCurrentUserVo();		
		try {
			RiskMargin  riskMargin = riskMarginService.queryRiskMargin();
			String ip = HttpTookit.getRealIpAddr(request);//返回发出请求的IP地址
			riskMargin.setAccount(new BigDecimal(request.getParameter("account")));
			result = riskMarginService.updateRiskMargin(riskMargin,userVo,ip);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统出错，请稍后重试");
		}
		return new MessageBox("0", result);
	}
	

}
