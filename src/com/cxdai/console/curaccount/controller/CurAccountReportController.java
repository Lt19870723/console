package com.cxdai.console.curaccount.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.curaccount.service.CurAccountReportService;
import com.cxdai.console.curaccount.vo.CurInterestDetailReportCnd;
import com.cxdai.console.curaccount.vo.CurInterestDetailReportVo;

/**
 * <p>
 * Description:活期宝 - 活期宝收益对账<br />
 * </p>
 * 
 * @title CurAccountReportController.java
 * @package com.cxdai.console.curaccount.controller
 * @author tongjuxing
 * @version 0.1 2015年7月3日
 */
@Controller
@RequestMapping(value = "/curaccount/curaccountreport")
public class CurAccountReportController extends BaseController {
	private static final Logger logger = Logger.getLogger(CurAccountReportController.class);
	
	@Autowired
	private CurAccountReportService curAccountReportService;
	

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/curaccount/curaccountreport/main","lastEndTime",new Date());
	}
	
	@RequestMapping(value = "/sum")
	@ResponseBody
	public Map<String,Object> querySum(@ModelAttribute CurInterestDetailReportCnd curInterestDetailReportCnd) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("detailCount", "");
		result.put("accountLogCount", "");
		result.put("detailMoneyTotal", "");
		result.put("accountLogMoneyTotal", "");
		result.put("isUserNotHaveInterestDetial", "");
		result.put("isUserNotHaveAccountLog", "");
		result.put("isRepeatDetail", "");
		result.put("isRepeatAccountLog", "");
		result.put("isEnterMoney", "");
		result.put("resultMsg", "");
		try {
			
			CurInterestDetailReportVo curInterestDetailReportVo = 
					curAccountReportService.queryCurInterestReportByDate(curInterestDetailReportCnd);
			
			result.put("detailCount", curInterestDetailReportVo.getDetailCount());
			result.put("accountLogCount", curInterestDetailReportVo.getAccountLogCount());
			result.put("detailMoneyTotal", curInterestDetailReportVo.getDetailMoneyTotal());
			result.put("accountLogMoneyTotal", curInterestDetailReportVo.getAccountLogMoneyTotal());
			result.put("isUserNotHaveInterestDetial", curInterestDetailReportVo.getIsUserNotHaveInterestDetial()==1?"是":"否");
			result.put("isUserNotHaveAccountLog", curInterestDetailReportVo.getIsUserNotHaveAccountLog()==1?"是":"否");
			result.put("isRepeatDetail", curInterestDetailReportVo.getIsRepeatDetail()==1?"是":"否");
			result.put("isRepeatAccountLog", curInterestDetailReportVo.getIsRepeatAccountLog()==1?"是":"否");
			result.put("isEnterMoney", curInterestDetailReportVo.getIsEnterMoney()==1?"是":"否");
			
			boolean isTrue = curInterestDetailReportVo.getDetailCount().intValue() == curInterestDetailReportVo.getAccountLogCount().intValue()
					&& curInterestDetailReportVo.getDetailMoneyTotal().doubleValue() == curInterestDetailReportVo.getAccountLogMoneyTotal().doubleValue()
					&& curInterestDetailReportVo.getIsUserNotHaveInterestDetial().intValue() == 0 
					&& curInterestDetailReportVo.getIsUserNotHaveAccountLog().intValue() == 0
					&& curInterestDetailReportVo.getIsRepeatDetail().intValue() == 0 
					&& curInterestDetailReportVo.getIsRepeatAccountLog().intValue() == 0
					&& curInterestDetailReportVo.getIsEnterMoney().intValue() == 0;
			result.put("resultMsg", isTrue?"结算正确":"结算出错");
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}
	
}