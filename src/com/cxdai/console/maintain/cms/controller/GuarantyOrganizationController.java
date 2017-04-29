package com.cxdai.console.maintain.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.GuarantyOrganization;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.GuarantyOrganizationCnd;
import com.cxdai.console.maintain.cms.service.GuarantyOrganizationService;
import com.cxdai.console.util.HttpTookit;

@Controller
@RequestMapping(value="/guarantyOrganization")
public class GuarantyOrganizationController extends BaseController {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -8652796402164159345L;

	private static final Logger logger = Logger.getLogger(GuarantyOrganizationController.class);

	@Autowired
	private GuarantyOrganizationService organizationService;
	

	@RequestMapping(value="/toGuaOrgMain")
	@ResponseBody
	public ModelAndView toGuaOrgMain(){
		return forword("/maintain/cms/guaOrgMain");
	}
	
	
	@RequestMapping(value="/searchAll/{pageNo}")
	@ResponseBody
	public ModelAndView searchAll(@ModelAttribute GuarantyOrganizationCnd organizationCnd,@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = organizationService.pageQuery(organizationCnd, page);
		} catch (Exception e) {
			logger.error("担保机构列表获取异常", e);
		}
		return forword("/maintain/cms/guaOrgList").addObject("page", page);
	}

	@RequestMapping(value="/delGuaOrg")
	@ResponseBody
	public MessageBox delGuaOrg(Integer guaOrgId) {
		String msg = "删除失败";
		try {
			msg = organizationService.delete(guaOrgId);
			if("".equals(msg)){
				return MessageBox.build("1", "删除成功");
			}
		} catch (Exception e) {
			logger.error("担保机构删除异常", e);
		}
		return MessageBox.build("0", msg);
	}

	@RequestMapping(value="/toGuaOrgAdd")
	@ResponseBody
	public ModelAndView toGuaOrgAdd(){
		return forword("/maintain/cms/guaOrgAdd");
	}
	

	@RequestMapping(value="/addGuaOrg")
	@ResponseBody
	public MessageBox addGuaOrg(@ModelAttribute GuarantyOrganization organization,HttpServletRequest request){
		String msg = "添加失败";
		try {
			String addip = HttpTookit.getRealIpAddr(request);
			organization.setAddip(addip);
			msg = organizationService.add(organization);
			if("".equals(msg)){
				return MessageBox.build("1", "添加成功");
			}
		} catch (Exception e) {
			logger.error("担保机构保存异常", e);
		}
		return MessageBox.build("0", msg);
	}
	

	@RequestMapping(value="/toGuaOrgEdit/{guaOrgId}")
	@ResponseBody
	public ModelAndView toGuaOrgEdit(@PathVariable Integer guaOrgId) {
		GuarantyOrganization  organization = null;
		try {
			organization = organizationService.initEdit(guaOrgId);
		} catch (Exception e) {
			logger.error("担保机构编辑初始化异常", e);
		}
		return forword("/maintain/cms/guaOrgEdit").addObject("GuarantyOrganization",organization);
	}
	

	@RequestMapping(value="/editGuaOrg")
	@ResponseBody
	public MessageBox editGuaOrg(@ModelAttribute GuarantyOrganization organization,HttpServletRequest request) {
		String msg="保存失败";
		try {
			msg = organizationService.update(organization.getId(), organization);
			if("".equals(msg)){
				return MessageBox.build("1", "保存成功");
			}
		} catch (Exception e) {
			logger.error("担保机构保存异常", e);
		}
		return MessageBox.build("0", "保存失败");
	}
	
}
