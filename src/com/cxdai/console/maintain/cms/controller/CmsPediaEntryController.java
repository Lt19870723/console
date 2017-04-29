package com.cxdai.console.maintain.cms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.Staff;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.maintain.cms.entity.CmsOperateLog;
import com.cxdai.console.maintain.cms.entity.CmsPediaEntry;
import com.cxdai.console.maintain.cms.entity.CmsPediaEntryCnd;
import com.cxdai.console.maintain.cms.service.CmsOperateLogService;
import com.cxdai.console.maintain.cms.service.CmsPediaEntryService;
import com.cxdai.console.util.HanyuHelper;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:词条的操作类<br />
 * 
 * @title CmsPediaEntryAction.java
 * @package com.cxdai.console.cms.action
 * @author 邹理享
 * @version 0.1 2015年1月28日
 */
@Controller
@RequestMapping(value="/cmsEntry")
public class CmsPediaEntryController extends BaseController {

	Logger logger = LoggerFactory.getLogger(CmsPediaEntryController.class);
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	@Autowired
	private CmsPediaEntryService cmsPediaEntryService;
	
	@Autowired
	private CmsOperateLogService cmsOperateLogService;
	
	

	@RequestMapping(value="/toCmsEntryMain")
	@ResponseBody
	public ModelAndView toCmsEntryMain(){
		return forword("/maintain/cms/cmsEntryMain");
	}

	@RequestMapping(value="/toCmsEntryAdd")
	@ResponseBody
	public ModelAndView toCmsEntryAdd(){
		return forword("/maintain/cms/cmsEntryAdd");
	}
	
	@RequestMapping(value="/searchCmsPediaEntryList/{pageNo}")
	@ResponseBody
	public ModelAndView searchCmsPediaEntryList(@ModelAttribute CmsPediaEntryCnd cmsPediaEntryCnd,@PathVariable("pageNo") Integer paegNo) {
		Page page = new Page();
		try {
			page = cmsPediaEntryService.searchCmsPediaEntryPageByCnd(cmsPediaEntryCnd, paegNo, WxContants.WX_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/maintain/cms/cmsEntryList").addObject("page", page);
	}
	
	@RequestMapping(value="/delCmsEntry")
	@ResponseBody
	public void delCmsEntry(Integer cmdEntryId,HttpServletRequest request) {
		try {
			cmsPediaEntryService.deleteCmsPediaEntryByIds(new String[] { String.valueOf(cmdEntryId) });
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark;
			Integer addBy =currentUser().getUserId();
			remark = "";
			cmsOperateLogService.save(new CmsOperateLog(cmdEntryId, CmsConstant.PEDIAENTRYSOURCETYPE, CmsConstant.CMSDELETE, addBy, new Date(), ip, mac, remark));

		} catch (Exception e) {
			logger.error("删除出错", e);
		}
	}
	

	@RequestMapping(value="/saveCmsPediaEntry")
	@ResponseBody
	public MessageBox saveCmsPediaEntry(@ModelAttribute CmsPediaEntry cmsPediaEntry,HttpServletRequest request) {
		try {
			Integer addBy = currentUser().getUserId();
			cmsPediaEntry.setCreateBy(addBy);
			cmsPediaEntry.setUpdateBy(addBy);
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark = "";
			cmsPediaEntry.setInitials(getInitials(cmsPediaEntry.getName()));
			if (cmsPediaEntry.getId().intValue() != 0) {
				cmsPediaEntryService.updateCmsPediaEntry(cmsPediaEntry);
				cmsOperateLogService.save(new CmsOperateLog(cmsPediaEntry.getId(), CmsConstant.PEDIAENTRYSOURCETYPE, CmsConstant.CMSUPDATE, addBy, new Date(), ip, mac, remark));
			} else {
				cmsPediaEntryService.saveCmsPediaEntry(cmsPediaEntry);
				cmsOperateLogService.save(new CmsOperateLog(cmsPediaEntry.getId(), CmsConstant.PEDIAENTRYSOURCETYPE, CmsConstant.CMSADD, addBy, new Date(), ip, mac, remark));
			}
			return MessageBox.build("1", "保存成功");
		} catch (Exception e) {
			logger.error("保存出错", e);
		}
		return MessageBox.build("0", "保存失败");
	}
	private String getInitials(String name) {
		HanyuHelper helper = new HanyuHelper();
		String pinyin = helper.getStringPinYin(name);
		if (StringUtils.isEmpty(pinyin)) {
			return "";
		}
		return pinyin.substring(0, 1).toUpperCase();
	}
	

	@RequestMapping(value="/toCmsEntryEdit/{cmsEntryId}")
	@ResponseBody
	public ModelAndView toSavePediaEntry(@PathVariable("cmsEntryId") Integer cmsEntryId) {
		CmsPediaEntry cmsPediaEntry =null;
		try{
			cmsPediaEntry = cmsPediaEntryService.getCmsPediaEntryById(cmsEntryId);
		}catch(Exception e){
			logger.error("跳转词条编辑页面异常",e);
		}
		return forword("/maintain/cms/cmsEntryEdit").addObject("CmsPediaEntry", cmsPediaEntry);
	}

}
