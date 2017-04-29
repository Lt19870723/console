package com.cxdai.console.red.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.red.mapper.VipRedViewMapper;
import com.cxdai.console.red.service.VipRedViewService;
import com.cxdai.console.red.vo.VipRedImportCnd;
import com.cxdai.console.system.entity.Configuration;

/**
 * 红包管理-贵族特权红包查看
 * @author liutao
 * @Date 2015-11-11
 */
@Controller
@RequestMapping(value = "/vipredview")
public class VipRedViewController extends BaseController {
	private static final Logger logger = Logger.getLogger(VipRedViewController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private VipRedViewService vipRedViewService;
	@Autowired
	private VipRedViewMapper vipRedViewMapper;

	@RequestMapping("/main")
	public ModelAndView forVipRedViewMain() {
		return new ModelAndView("/red/vipredview/main");
	}

	/**
	 * 红包管理-查询贵族特权红包查看
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchVipRedViewList(@ModelAttribute VipRedImportCnd vipRedImportCnd, @PathVariable Integer pageNo) {
		Page page = null;
		Collection<Configuration>configurations=null;
		try {
			page = vipRedViewService.queryVipRedViewVoList(vipRedImportCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
			configurations=Dictionary.getValues(1900);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询红包记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/red/vipredview/list").addObject("page", page).addObject("redTypes",configurations);
	}
}
