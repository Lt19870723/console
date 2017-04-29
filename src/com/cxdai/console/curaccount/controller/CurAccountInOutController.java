package com.cxdai.console.curaccount.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.mapper.CurAccountlogMapper;
import com.cxdai.console.curaccount.service.CurAccountLogService;
import com.cxdai.console.curaccount.vo.CurAccountLogCnd;
import com.cxdai.console.curaccount.vo.CurInOutAccountCnd;
import com.cxdai.console.system.entity.Configuration;

/**
 * <p>
 * Description:活期宝 - 活期宝转入转出<br />
 * </p>
 * 
 * @title CurAccountInOutController.java
 * @package com.cxdai.console.curaccount.controller
 * @author tongjuxing
 * @version 0.1 2015年7月2日
 */
@Controller
@RequestMapping(value = "/curaccount/curaccountinout")
public class CurAccountInOutController extends BaseController {
	private static final Logger logger = Logger.getLogger(CurAccountInOutController.class);
	
	@Autowired
	private CurAccountLogService curAccountLogService;
    @Autowired
    private CurAccountlogMapper curAccountlogMapper;
	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		List<Map<String, Object>> selectItems = new ArrayList<Map<String, Object>>();
		
		ModelAndView mv=new ModelAndView("/curaccount/curaccountinout/main");
		try {
			// 转入转出方式-读取数据词典
			Configuration configuration = new Configuration();
			configuration.setType(1103);// 活期宝type
			selectItems = curAccountLogService.selectConfigurationByConn(configuration);
			// 转入累计			
			BigDecimal sumMoneyIn = curAccountlogMapper.queryCurAccountLogSumForIn();
			// 转出累计			
			BigDecimal sumMoneyOut = curAccountlogMapper.queryCurAccountLogSumForOut();
			mv.addObject("sumMoneyIn", sumMoneyIn).addObject("sumMoneyOut", sumMoneyOut);
		} catch (Exception e) {
			logger.error(e);
		}
		return  mv.addObject("selectItems",selectItems);
	}
	
	@RequestMapping(value = "/sum")
	@ResponseBody
	public Map<String,Object> querySumByConn(@ModelAttribute CurInOutAccountCnd curAccountLogCnd) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("sumIn", 0);
		result.put("sumOut", 0);
		try {
			
			// 求和，转入累计，转出累计 getSumMoneyInOut
			result.putAll(curAccountLogService.getSumMoneyInOut(curAccountLogCnd));
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute CurInOutAccountCnd curAccountLogCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = curAccountLogService.queryCurAccountLogByPage(curAccountLogCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/curaccount/curaccountinout/list").addObject("page", page);
	}
	
}