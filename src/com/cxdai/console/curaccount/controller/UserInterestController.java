package com.cxdai.console.curaccount.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.service.CurAccountService;
import com.cxdai.console.curaccount.service.CurInterestDetailService;
import com.cxdai.console.curaccount.vo.CurAccountCnd;
import com.cxdai.console.curaccount.vo.CurAccountVo;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:活期宝 - 活期宝用户收益<br />
 * </p>
 * 
 * @title UserInterestController.java
 * @package com.cxdai.console.curaccount.controller
 * @author tongjuxing
 * @version 0.1 2015年7月2日
 */
@Controller
@RequestMapping(value = "/curaccount/userinterest")
public class UserInterestController extends BaseController {
	private static final Logger logger = Logger.getLogger(UserInterestController.class);
	
	private final String PREDATATIP="(温馨提示:请在每年1月1日前，做好下一年节假日铺底数据到节假日表中)";
	
	@Autowired
	private CurAccountService curAccountService;
	
	@Autowired
	private CurInterestDetailService curInterestDetailService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/curaccount/userinterest/main");
	}

	@RequestMapping(value = "/sum")
	@ResponseBody
	public Map<String,Object> querySumByConn(@ModelAttribute CurAccountCnd curAccountCnd) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		try {
			result.put("preDataTip", "");
			int curMonth = Integer.parseInt(DateUtils.format(new Date(), "MM"));
			if(curMonth==12)
			{
				result.put("preDataTip", this.PREDATATIP);
			}
			
			// 求和
			CurAccountVo retCurAccountVo = new CurAccountVo();
			result.put("retCurAccountVo", retCurAccountVo);
			
			retCurAccountVo = curAccountService.querySumByConn(curAccountCnd);
			result.put("retCurAccountVo", retCurAccountVo);
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute CurAccountCnd curAccountCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = curAccountService.queryCurAccountListPage(curAccountCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/curaccount/userinterest/list").addObject("page", page);
	}
	
	/**
	 * <p>
	 * Description:查看详情 根据id 查询我的账户信息<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月2日
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView viewCurAccountById(@RequestParam(value = "id", required = true) Integer id) {
		CurAccountVo curAccountVo = new CurAccountVo();
		try {
			// 我的账户查看详情
			curAccountVo = curAccountService.queryCurAccountVoById(id);
						
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/curaccount/userinterest/detail").addObject("curAccountVo", curAccountVo);
	}
	
	/**
	 * <p>
	 * Description:查看详情-收益明细列表<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月2日
	 */
	@RequestMapping(value = "/detaillist/{pageNo}")
	public ModelAndView detailList(@ModelAttribute CurAccountCnd curAccountCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			// 查看详情-收益明细列表
			page = curInterestDetailService.queryCurInterestDetailByPage(curAccountCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/curaccount/userinterest/detaillist").addObject("page", page);
	}
	
}