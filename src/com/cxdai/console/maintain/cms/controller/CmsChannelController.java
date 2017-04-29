package com.cxdai.console.maintain.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.search.parser.CustomParseException.Message;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.maintain.cms.entity.CmsChannel;
import com.cxdai.console.maintain.cms.entity.CmsChannelCnd;
import com.cxdai.console.maintain.cms.entity.CmsOperateLog;
import com.cxdai.console.maintain.cms.service.CmsChannelService;
import com.cxdai.console.maintain.cms.service.CmsOperateLogService;
import com.cxdai.console.util.HanyuHelper;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:栏目操作类<br />
 * </p>
 * 
 * @title CmsChannelAction.java
 * @package com.cxdai.console.cms.action
 * @author 邹理享
 * @version 0.1 2015年1月28日
 */
@Controller
@RequestMapping(value="/cmsChanel")
public class CmsChannelController extends BaseController{

	 Logger logger = LoggerFactory.getLogger(CmsChannelController.class);
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	@Autowired
	private CmsChannelService cmsChannelService;

	@Autowired
	private CmsOperateLogService cmsOperateLogService;

	
	
	@RequestMapping(value="/toCmsChannelMain")
	@ResponseBody
	public ModelAndView toCmsChannelMain(){
		return forword("/maintain/cms/cmsChannelMain");
	}

	@RequestMapping(value="/toCmsChannelAdd")
	@ResponseBody
	public ModelAndView toCmsChannelAdd(){
		return forword("/maintain/cms/cmsChannelAdd");
	}
	
	@RequestMapping(value="/searchCmsChannelList/{pageNo}")
	@ResponseBody
	public ModelAndView searchCmsChannelList(@ModelAttribute CmsChannelCnd cmsChannelCnd,@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = cmsChannelService.queryCmsChannelListForPage(cmsChannelCnd, pageNo, WxContants.WX_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/maintain/cms/cmsChannelList").addObject("page", page);
	}
	

	@RequestMapping(value="/delChannel")
	@ResponseBody
	public MessageBox deleteChannel(Integer cmsChannelId,HttpServletRequest request){
		try{
			cmsChannelService.deleteCmsChannelById(cmsChannelId);
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark;
			Integer addBy = currentUser().getUserId();
			remark = "";
			cmsOperateLogService.save(new CmsOperateLog(cmsChannelId, CmsConstant.CHANNELSOURCETYPE, CmsConstant.CMSDELETE, addBy, new Date(), ip, mac, remark));
			return MessageBox.build("1", "删除成功");
		}catch (Exception e){
			logger.error("删除栏目失败",e);
		}
		return MessageBox.build("0", "删除失败");

	}
	
	

	@RequestMapping(value="/saveCmsChannel")
	@ResponseBody
	public MessageBox saveCmsChannel(@ModelAttribute CmsChannel cmsChannel,HttpServletRequest request) {
		// 获取ip
		String ip = HttpTookit.getRealIpAddr(request);
		String mac = HttpTookit.getRealMac(ip);
		String remark = "";
		Integer addBy = currentUser().getUserId();
		cmsChannel.setUpdateBy(addBy);
		try {
			if (cmsChannel.getId().intValue() != 0) {
				cmsChannelService.updateCmsChannel(cmsChannel);
				cmsOperateLogService.save(new CmsOperateLog(cmsChannel.getId(), CmsConstant.CHANNELSOURCETYPE, CmsConstant.CMSUPDATE, addBy, new Date(), ip, mac, remark));
			} else {
				cmsChannel.setUrlCode(getInitials(cmsChannel.getName()));
				cmsChannelService.saveCmsChannel(cmsChannel);
				cmsOperateLogService.save(new CmsOperateLog(cmsChannel.getId(), CmsConstant.CHANNELSOURCETYPE, CmsConstant.CMSADD, addBy, new Date(), ip, mac, remark));
			}
			return MessageBox.build("1", "保存成功");
		} catch (Exception e) {
			logger.error("栏目新增保存失败",e);
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
	

	@RequestMapping(value="/initParentChannelList")
	@ResponseBody
	public StringBuffer initParentChannelList() {
		StringBuffer selectList = new StringBuffer();
		try {
			List<CmsChannel> list = cmsChannelService.queryCmsParentChannelList();
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getId()+","+list.get(i).getName()+"|");
			}
		} catch (Exception e) {
			logger.error("获取父栏目失败",e);
		}
		return selectList;
	}
	
	

	@RequestMapping(value="/toCmsChannelEdit/{cmsChannelId}")
	@ResponseBody
	public ModelAndView toCmsChannelEdit(@PathVariable Integer cmsChannelId) {
		CmsChannel cmsChannel = null;
		try{
			cmsChannel = cmsChannelService.getCmsChannelById(cmsChannelId);
		}catch(Exception e){
			logger.error("跳转栏目编辑页面失败",e);
		}
		return forword("/maintain/cms/cmsChannelEdit").addObject("CmsChannel", cmsChannel);
	}
	

	@RequestMapping(value="/hiddenCmsChannel")
	@ResponseBody
	public MessageBox hiddenCmsChannel(Integer cmsChannelId,Integer status,HttpServletRequest request) {
		try {
			cmsChannelService.updateHiddenById(cmsChannelId, status);
			Integer addBy = currentUser().getUserId();
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark = status == 0 ? "隐藏" : "显示";
			cmsOperateLogService.save(new CmsOperateLog(cmsChannelId, CmsConstant.CHANNELSOURCETYPE, CmsConstant.CMSHIDDEN, addBy, new Date(), ip, mac, remark));
			return MessageBox.build("1", "成功");
		} catch (Exception e) {
			logger.error("栏目隐藏显示失败",e);
		}
		return MessageBox.build("0", "失败");
	}

}
