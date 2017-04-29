package com.cxdai.console.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.service.SchedulerService;
import com.cxdai.console.system.vo.SchedulerErrorLogCnd;

/**
 * <p>
 * Description:系统管理-定时任务管理<br />
 * </p>
 * 
 * @title SchedulerController.java
 * @package com.cxdai.console.system.controller
 * @author qiongbiao.zhang
 * @version 0.1 2015年3月2日
 */
@Controller
@RequestMapping(value = "/system/scheduler")
public class SchedulerController extends BaseController {

	@Autowired
	private SchedulerService schedulerService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true)); // true:允许输入空值,false:不能为空值
	}

	@RequestMapping("/log/error/main")
	public ModelAndView error() {
		return forword("/system/scheduler/log/error_main");
	}

	@RequestMapping(value = "/log/error/list/{pageNo}")
	public ModelAndView list(@ModelAttribute SchedulerErrorLogCnd schedulerErrorLogCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = schedulerService.selectErrorLogPage(schedulerErrorLogCnd, new Page(pageNo, 10));
		return forword("/system/scheduler/log/error_list").addObject("page", page);
	}
}
