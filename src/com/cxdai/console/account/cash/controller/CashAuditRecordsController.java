package com.cxdai.console.account.cash.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.console.account.cash.service.CashRecordService;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.account.cash.vo.CashRecordVo;
import com.cxdai.console.base.entity.Member;
import com.cxdai.console.base.mapper.BaseMemberMapper;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.vo.UserVo;

/**
 * 提现申请
 * @author junt
 */
@Controller
@RequestMapping(value = "/account/cashauditrecord")
public class CashAuditRecordsController extends BaseController{
	
	private final static Logger logger = Logger.getLogger(CashAuditRecordsController.class);
	
	@Autowired
	private CashRecordService cashRecordService;
	@Autowired
	private BaseMemberMapper baseMemberMapper;
	
	/**
	 * 进入提现申请页面
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView cashAuditRecordsMain(){
		return new ModelAndView("/account/cash/query/appro/main");
	}
	
	/**
	 * 查询提现申请审核列表
	 */
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchPageCashRecordApproList(@ModelAttribute CashRecordCnd cashRecordCnd, @PathVariable Integer pageNo,HttpServletRequest request){
		Map<String,BigDecimal> resultMap = null;
		Page page = null;
		try {
			cashRecordCnd.setCashColumn(1);
			cashRecordCnd.setBeginTime(request.getParameter("beginTime"));
			cashRecordCnd.setEndTime(request.getParameter("endTime"));
			page = cashRecordService.queryPageListByCnd(cashRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE4);
			resultMap = cashRecordService.queryCashRecorData(cashRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询提现申请审核列表错误信息："+e);
		}
		return new ModelAndView("/account/cash/query/appro/list").addObject("resultMap", resultMap).addObject("page", page);
	}
	
	/**
	 * 根据id查询数据，进入审核窗口
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public ModelAndView queryCashDataById(@PathVariable String id){
		CashRecordCnd cashRecordCnd = new CashRecordCnd();
		CashRecordVo cashRecordVo = new CashRecordVo();
		/** 某用户最近两次的提现到账记录 **/
		List<CashRecordVo> latelyCashList = new ArrayList<CashRecordVo>();
		/** 用户的最大净值额度 */
	    BigDecimal maxAccount = new BigDecimal("0");
	    Map<String, BigDecimal> cashMap = new HashMap<String, BigDecimal>();
	    /** 用户信息 */
		Member member = new Member();
		cashRecordCnd.setId(id.toString());
		try {
			cashRecordVo = cashRecordService.queryCashRecordById(cashRecordCnd);
			// 某用户最近两次的提现到账记录
			latelyCashList = cashRecordService.queryCashTwoSuccessListByMemberId(cashRecordVo.getUserId());
			// 计算净值额度
			maxAccount = cashRecordService.saveNetMoneyLimit(cashRecordVo.getUserId());
			// 提现审核查询相关统计项
			cashMap = cashRecordService.queryDataForCashApproById(Integer.parseInt(id));
			//用户信息
			member = baseMemberMapper.queryById(cashRecordVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/account/cash/query/appro/layer").addObject("cashRecordVo", cashRecordVo)
		.addObject("latelyCashList", latelyCashList).addObject("maxAccount", maxAccount).addObject("cashMap", cashMap)
		.addObject("member", member).addObject("cashListCount", latelyCashList.size());
	}
	
	/**
	 * 提现审核通过
	 * @return
	 */
	@RequestMapping("/pass")
	@ResponseBody
	public MessageBox approvePass(CashRecordVo cashRecordVo ,HttpServletRequest request){
		UserVo userVo = getCurrentUserVo();
		try {
			String result = cashRecordService.saveApprovePass(cashRecordVo, userVo,request);
			if(!"success".equals(result)){
				return new MessageBox("1", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBox("1", e.getMessage());
		}
		
		return new MessageBox("0", "审核通过!!");
	}
	
	/**
	 * 提现审核不通过
	 * @param request
	 * @return
	 */
	@RequestMapping("/reject")
	@ResponseBody
	public MessageBox firstAuditReject(CashRecordVo cashRecordVo,HttpServletRequest request){
		UserVo userVo = getCurrentUserVo();
		try {
			String result = cashRecordService.saveApproveReject(cashRecordVo, userVo, request);
			if(!"success".equals(result)){
				return new MessageBox("1", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBox("1",e.getMessage());
		}
		return new MessageBox("0","审核通过!!");
	}
}
