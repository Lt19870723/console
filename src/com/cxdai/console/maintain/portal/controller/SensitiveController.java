package com.cxdai.console.maintain.portal.controller;

import java.util.List;

import org.apache.log4j.Logger;
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
import com.cxdai.console.maintain.portal.entity.SensitiveCnd;
import com.cxdai.console.maintain.portal.entity.SensitiveVo;
import com.cxdai.console.maintain.portal.service.SensitiveService;

/**
 * <p>
 * Description:关键字<br />
 * </p>
 * 
 * @title SensitiveAction.java
 * @package com.cxdai.console.member.action
 * @author abel.sun
 * @version 0.1 2014年10月15日
 */
@Controller
@RequestMapping(value = "/sensitive")
public class SensitiveController extends BaseController {
	private static final long serialVersionUID = -673628584022127524L;
	
	
	private final static Logger logger = Logger.getLogger(SensitiveController.class);

	@Autowired
	private SensitiveService sensitiveService;

	

	@RequestMapping(value="/toSensitiveMain")
	@ResponseBody
	public ModelAndView toSensitiveMain(){
		return forword("/maintain/portal/sensitiveMain");
	}
	
	@RequestMapping(value="/querySensitiveTypeList")
	@ResponseBody
	public StringBuffer querySensitiveTypeList() {
		StringBuffer selectList = new StringBuffer();
		try {
			List<SensitiveVo> list = sensitiveService.querySensitiveTypeList();
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getId()+","+list.get(i).getTypeStr()+"|");
			}
		} catch (Exception e) {
			logger.error("获取敏感词类型失败",e);
		}
		return selectList;
	}
	

	@RequestMapping(value="/searchAll/{pageNo}")
	@ResponseBody
	public ModelAndView searchAll(@ModelAttribute SensitiveCnd sensitiveCnd,@PathVariable("pageNo") Integer pageNo){
		Page page =new Page();
		try{
			if(sensitiveCnd.getType()==null){
				sensitiveCnd.setType(0);
			}
			page = sensitiveService.querySensitiveList(sensitiveCnd, pageNo, WxContants.WX_PAGE_SIZE);
		}catch (Exception e){
			logger.error("获取敏感词列表失败",e);
		}
		return forword("/maintain/portal/sensitiveList").addObject("page", page);
		
	}
	
	@RequestMapping("/toSensitiveAdd")
	@ResponseBody
	public ModelAndView toSensitiveAdd(){
		return forword("/maintain/portal/sensitiveAdd");
	}

	@RequestMapping("/addSensitive")
	@ResponseBody
	public MessageBox addSensitive(@ModelAttribute SensitiveVo sensitiveVo){
		String msg = "";
		try{
			int cont = sensitiveService.querySensitiveForSave(sensitiveVo);
			if (cont == 0) {
				sensitiveService.saveSensitive(sensitiveVo);
				return MessageBox.build("1", "保存成功");
			} else {
				msg = "该关键字重复";
			}
			
		}catch (Exception e){
			logger.error("添加关键字失败",e);
			msg = "添加失败";
		}
		return MessageBox.build("0", msg);
	}
	
	@RequestMapping(value="/delSensitive")
	@ResponseBody
	public MessageBox delSensitive(@ModelAttribute SensitiveCnd sensitiveCnd) {
		try {
			sensitiveService.deleteSensitive(sensitiveCnd);
			return MessageBox.build("1", "删除成功");
		} catch (Exception e) {
			logger.error("敏感词删除异常",e);
		}
		return MessageBox.build("0", "删除失败，请重新再试");
	}
	

	@RequestMapping(value="/querySensitiveById/{sensitiveId}")
	@ResponseBody
	public ModelAndView querySensitiveById(@PathVariable("sensitiveId") Integer sensitiveId) {
		SensitiveVo sensitiveVo  = null;
		try {
			sensitiveVo = sensitiveService.querySensitiveByid(sensitiveId);
		} catch (Exception e) {
			logger.error("敏感词编辑查询失败",e);
		}
		return forword("/maintain/portal/sensitiveEdit").addObject("SensitiveVo", sensitiveVo);
		
	}

	@RequestMapping(value="/updateSensitive")
	@ResponseBody
	public MessageBox updateSensitive(@ModelAttribute SensitiveVo sensitiveVo) {
		try {
			sensitiveService.updateSensitive(sensitiveVo);
			return MessageBox.build("1", "保存成功");
		} catch (Exception e) {
			logger.error("修改敏感词失败",e);
		}
		return MessageBox.build("0", "保存失败");
	}

}
