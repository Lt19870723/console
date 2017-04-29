package com.cxdai.console.red.controller;

import java.math.BigDecimal;
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
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.mapper.RedAccountMapper;
import com.cxdai.console.red.service.RedRecordService;
import com.cxdai.console.red.vo.RedRecordCnd;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;

/**
 * 红包管理-红包记录
 * @author liutao
 * @Date 2015-11-11
 */
@Controller
@RequestMapping(value = "/red")
public class RedAccountController extends BaseController {
	private static final Logger logger = Logger.getLogger(RedAccountController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private RedRecordService redAccountService;
	@Autowired
	private RedAccountMapper redAccountMapper;
	@Autowired
	private MemberMapper memberMapper;
	
	@RequestMapping("/main")
	public ModelAndView forRedRecordMain() {
		ModelAndView modelAndView=new ModelAndView("/red/redrecord/main");
		Collection<Configuration>configurations=Dictionary.getValues(1900);
		if(null!=configurations&&configurations.size()>0){
			modelAndView.addObject("redtypes", configurations);
		}
		return modelAndView;
	}

	/**
	 * 红包管理-查询红包记录
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchRedRecord(@ModelAttribute RedRecordCnd redRecordCnd, @PathVariable Integer pageNo) {
		Page page = null;
		BigDecimal redMoneyTotal=BigDecimal.ZERO;
		try {
			page = redAccountService.queryRedAccountVoList(redRecordCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
			  //查询红包金额合计
			redMoneyTotal = redAccountMapper.queryRedMoneyTotal(redRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询红包记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/red/redrecord/list").addObject("page", page).addObject("redMoneyTotal",redMoneyTotal);
	}
	/**
	 * 红包管理-查询红包记录数
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute RedRecordCnd redRecordCnd) {
		int count =0;
		try {
		    count =redAccountMapper.queryRedAccountVoCount(redRecordCnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (count==0) {
			return MessageBox.build("1", "没有数据");
		}
		if (count > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(count));
	}
	/**
	 * 红包管理-导出红包记录
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping(value = "/export")
	public void exportRedPecord(@ModelAttribute RedRecordCnd redRecordCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			int totalCount = redAccountMapper.queryRedAccountVoCount(redRecordCnd);
			Page page = new Page(1, totalCount);
			List<RedAccount> list = redAccountMapper.queryRedAccountVoList(redRecordCnd, page);
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/redRecordexportExcel.jasper");
			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "红包记录" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("红包记录导出Excel出错", e);
		}
	}
	 /**
	    * 
	    * <p>
	    * Description:查询用户账户信息<br />
	    * </p>
	    * @author liutao
	    * @date 2016年2月29日
	    * @param borrowId
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/findUseAccountDetail")
		public  ModelAndView findUseAccountDetail(@ModelAttribute RedRecordCnd redRecordCnd) {
		     
			ModelAndView mv = new ModelAndView("/red/redrecord/layer");
			List<MemberVo> list=new ArrayList<MemberVo>();
			try {
				if (redRecordCnd != null && redRecordCnd.getUserId()> 0) {
					MemberCnd memberCnd=new MemberCnd();
					memberCnd.setUserId(redRecordCnd.getUserId());
					memberCnd.set_offset(0);
					memberCnd.set_limit(1);
					list = memberMapper.queryMemberVoListForPages(memberCnd);
				}
				if(null!=list&&list.size()>0){
					mv.addObject("MemberVo",list.get(0));
				}
			} catch (Exception e) {
				logger.error("查询用户账户信息错误信息："+e);
			}
		    return mv;
		}
}
