package com.cxdai.console.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.activity.service.EssayZoneService;
import com.cxdai.console.activity.vo.BbsItems;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;

/**
 * 
 * <p>
 * Description:活动数据查询-论坛三周年征文<br />
 * </p>
 * @title EssayZoneController.java
 * @package com.cxdai.console.activity.controller 
 * @author likang
 * @version 0.1 2016年6月27日
 */
@Controller
@RequestMapping(value = "/activity/essay")
public class EssayZoneController {
	
	@Autowired
	private EssayZoneService essayZoneService;

	@RequestMapping("/main")
	public ModelAndView forEssayZone(){
		return new ModelAndView("/activity/EssayZone/main");
	}
	
	/**
	 * 帖子列表
	 * @param repaymentRecordCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView queryPost(@ModelAttribute BbsItems bbsItems,@PathVariable Integer pageNo){
		Page page = null;
		
		try {
			page = essayZoneService.findBbsItemsPages(bbsItems, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/activity/EssayZone/list").addObject("page", page);
	}
	
	
	@RequestMapping("/queryIntegral")
	public ModelAndView queryIntegral(@RequestParam(value = "id", required = false) Integer itemId){
		List<BbsItems> list = essayZoneService.queryIntegral(itemId);
		return new ModelAndView("/activity/EssayZone/integral").addObject("list", list);
	}
	
}
