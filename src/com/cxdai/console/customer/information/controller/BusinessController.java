package com.cxdai.console.customer.information.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.BusinessService;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.BusinessCnd;
import com.cxdai.console.customer.information.vo.BusinessVo;
import com.cxdai.console.customer.information.vo.MemberCnd;

/**
 * @author WangQianJin
 * @title 业务员管理
 * @date 2015年8月17日
 */
@Controller
@RequestMapping(value = "/information/business")
public class BusinessController extends BaseController{
    
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private MemberService memberService;
	
	private final static Logger logger=Logger.getLogger(BusinessController.class);
	
	/**
	 * 业务员主界面
	 * @author WangQianJin
	 * @title @param username
	 * @title @return
	 * @date 2015年8月17日
	 */
	@RequestMapping("/businessMain")
	public ModelAndView businessMain(){		
		return new ModelAndView("/customer/information/business/businessMain");
	}
	
	/**
	 * 业务员列表查询
	 * @author WangQianJin
	 * @title @param memberCnd
	 * @title @param pageNo
	 * @title @return
	 * @date 2015年8月17日
	 */
    @RequestMapping("/businessList/{pageNo}")
	public ModelAndView businessList(@ModelAttribute BusinessCnd businessCnd, @PathVariable Integer pageNo) {
    	Page page = null;	 
		try {
			//业务员状态-正常
			businessCnd.setStatus(0);
	   		page = businessService.selectPageBusinessByCnd(businessCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);	 
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/customer/information/business/businessList").addObject("page", page);
    }
   
    /**
	 * 业务员编辑
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/businessEdit")
	public ModelAndView businessEdit(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView mv = forword("/customer/information/business/businessEdit");		
		if (id != null) {
			BusinessVo businessVo=null;
			try {
				businessVo = businessService.getBusinessById(id);				
			} catch (Exception e) {				
				stackTraceError(logger, e);
			}
			mv.addObject("businessVo", businessVo);
		}
		return mv;
	}
	
	/**
	 * 业务员保存
	 * @author WangQianJin
	 * @title @param houseVo
	 * @title @return
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/businessSave")
	@ResponseBody
	public MessageBox businessSave(@ModelAttribute BusinessVo businessVo,HttpServletRequest request) {		
		try {
			//判断添加或者修改
			if(businessVo.getId()==null){
				int busCount=businessService.getBusinessCountByUserId(businessVo.getUserId());
				if(busCount>0){
					return MessageBox.build("1", "您选择的权证人员已存在");
				}
				businessService.addBusiness(businessVo,request);
			}else{
				businessService.updateBusiness(businessVo,request);
			}			
			return MessageBox.build("0", "保存成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "保存失败");			
		}
	}
	
	/**
	 * 根据ID修改状态
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/updateStatus/{id}&{status}")
	@ResponseBody
	public MessageBox updateStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status,HttpServletRequest request) {
		try {
			BusinessVo businessVo=new BusinessVo();
			businessVo.setId(id);
			businessVo.setStatus(status);
			businessService.updateBusiness(businessVo,request);
			return MessageBox.build("0", "操作成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			e.printStackTrace();
			return MessageBox.build("1", "操作失败");
		}
	}
	
	/**
	 * 查询客户界面
	 * @author WangQianJin
	 * @title @return
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/memberSelect") 
	public ModelAndView memberSelect() {
		return forword("/customer/information/business/memberSelect");
	}

	/**
	 * 客户列表
	 * @author WangQianJin
	 * @title @param pageNo
	 * @title @param borrowerCnd
	 * @title @return
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/memberList/{pageNo}")
	public ModelAndView memberList(@PathVariable("pageNo") Integer pageNo, MemberCnd memberCnd) {
		Page page=null;
		try {
			//状态正常的客户
			memberCnd.setStatus(0);
			page = memberService.getPageMemberVo(memberCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {	
			stackTraceError(logger, e);			
		}
		return forword("/customer/information/business/memberList").addObject("page", page);
	}
	
}
