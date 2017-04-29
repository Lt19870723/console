package com.cxdai.console.customer.information.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.mapper.RedAccountMapper;
import com.cxdai.console.red.vo.RedRecordCnd;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:客户信息controller<br />
 * </p>
 * 
 * @title MemberController.java
 * @package com.cxdai.console.customer.information.controller
 * @author hujianpan
 * @version 0.1 2015年3月11日
 */
@Controller
@RequestMapping(value = "/customer/baseinfo")
public class BaseInfoController extends BaseController {

	private final static Logger logger = Logger.getLogger(BaseInfoController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private RedAccountMapper redAccountMapper;
	/**
	 * <p>
	 * Description:客户信息-主界面<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView index() {
		ModelAndView modelAndView=new ModelAndView("/customer/information/base/index");
		Collection<Configuration>configurations=Dictionary.getValues(1900);
		if(null!=configurations&&configurations.size()>0){
			modelAndView.addObject("redtypes", configurations);
		}
		Collection<Configuration> sources = Dictionary.getValues(1101);
		if(null!=sources&&sources.size()>0){
			modelAndView.addObject("sources", sources);
		}
		return modelAndView;
	}

	/**
	 * <p>
	 * Description:客户信息 - 查询<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/query/{pageNo}")
	public ModelAndView serachCustomer(@ModelAttribute MemberCnd memberCnd, @PathVariable("pageNo") Integer pageNo) {
		ModelAndView mv = new ModelAndView("/customer/information/base/list");
		Page page = null;
		Collection<Configuration> sources = Dictionary.getValues(1101);// 来源
		try {
			page = memberService.queryMemberVoListForPages(memberCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			logger.error(e);
		}
		return mv.addObject("page", page).addObject("memberCnd", memberCnd).addObject("sources",sources);
	}

	/**
	 * <p>
	 * Description:导出符合条件的数据<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @param request
	 * @param response void
	 */
	@RequestMapping(value = "/export")
	public void exportMemberForCallback(@ModelAttribute MemberCnd memberCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<MemberVo> list = memberService.queryMemberVoListForExport(memberCnd);
			if (null == list || list.size() == 0) {
				return;
			}
			if (list.size() > 50000) {
				return;
			}
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fmemberForCallbackReportExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "客户回访" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("客户回访导出Excel出错", e);
		}
	}

	/**
	 * <p>
	 * Description:计算要导出的数据数量<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月11日
	 * @param memberCnd
	 * @param request
	 * @param response
	 * @return MessageBox
	 */
	@RequestMapping(value = "/count", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public MessageBox count(@ModelAttribute MemberCnd memberCnd, HttpServletRequest request, HttpServletResponse response) {
		List<MemberVo> list = null;
		try {
			list = memberService.queryMemberVoListForExport(memberCnd);
		} catch (Exception e) {
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (null == list || list.size() == 0) {
			return MessageBox.build("1", "没有数据");
		}
		if (list.size() > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return MessageBox.build("0", String.valueOf(list.size()));

	}
	/**
	 * <p>
	 * Description:我的红包-主界面<br />
	 * </p>
	 * 
	 * @author 刘涛
	 * @version 0.1 2016年4月7日
	 * @param memberCnd
	 * @return ModelAndView
	 */
	@RequestMapping("/redMain")
	public ModelAndView redMain(@ModelAttribute MemberCnd memberCnd) {
		ModelAndView modelAndView=new ModelAndView("/customer/information/base/redMain");
		return modelAndView.addObject("userId",memberCnd.getUserId());
	}
	/**
	 * <p>
	 * Description:我的红包-列表<br />
	 * </p>
	 * 
	 * @author 刘涛
	 * @version 0.1 2016年4月7日
	 * @param memberCnd
	 * @return ModelAndView
	 */
	@RequestMapping("/findRedInfoDetail/{pageNo}")
	public ModelAndView findRedInfoDetail(@ModelAttribute MemberCnd memberCnd, @PathVariable("pageNo") Integer pageNo) {
		ModelAndView mv = new ModelAndView("/customer/information/base/detailList");
		List<RedAccount> list=new ArrayList<RedAccount>();
		Page page =null;
		try {
			if (memberCnd != null && memberCnd.getUserId()> 0) {
				RedRecordCnd redRecordCnd=new RedRecordCnd();
				redRecordCnd.setUserId(memberCnd.getUserId());
 				page = new Page(pageNo,Constants.CONSOLE_PAGE_SIZE);
				int totalCount = redAccountMapper.queryRedAccountVoCount(redRecordCnd);
				page.setTotalCount(totalCount);
			    list = redAccountMapper.queryRedAccountVoList(redRecordCnd, page);
			    page.setResult(list);
			}
		} catch (Exception e) {
			logger.error("查询用户账户信息错误信息："+e);
		}
	     return mv.addObject("page", page);
	}

}
