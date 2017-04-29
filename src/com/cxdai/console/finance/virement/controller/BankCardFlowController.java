package com.cxdai.console.finance.virement.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.AccountFlow;
import com.cxdai.console.finance.virement.entity.BankAccountInfo;
import com.cxdai.console.finance.virement.service.BankAccountService;
import com.cxdai.console.finance.virement.service.BankCardFlowService;
import com.cxdai.console.finance.virement.vo.AccountFlowResponse;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;
import com.cxdai.console.finance.virement.vo.ViewExcel;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

/***
 * 充值核对Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/finance/flow")
public class BankCardFlowController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(BankCardFlowController.class);
	
	@Autowired
	private BankCardFlowService bankCardFlowService;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private BankAccountService bankAccountService;
	

	/***
	 * 提现核对主界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView interTransferMain() {
		return new ModelAndView("/finance/bankCardFlow/main");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查询银行卡流水信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-26
	 * @param requestCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView queryPageList(@ModelAttribute QueryPageCheckWithCnd requestCnd,
			@PathVariable Integer pageNo) {
		ModelAndView mv = new ModelAndView("/finance/bankCardFlow/list");
		try {
			Page page = bankCardFlowService.queryPageList(requestCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error("分页查询银行卡流水信息列表出错" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:跳转添加流水信息页面<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-26
	 * @param date
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/edit")
	public ModelAndView eidt(String date) {
		ModelAndView mv = new ModelAndView("/finance/bankCardFlow/edit");
		List<Configuration> list = null;
		List<Configuration> incomeList = null;
		List<BankAccountInfo> accountList = null;
		try {
			list = accountLogService.queryConfigurationListByType(80706);
			incomeList = accountLogService.queryConfigurationListByType(1800);
			accountList = bankAccountService.selectAll();
			mv.addObject("incomeList", incomeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金记录获取交易类型列表错误：" + e.getMessage());
		}
		return mv.addObject("bankList", list).addObject("accountList", accountList);
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存银行卡流水信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-26
	 * @param record
	 * @return
	 * Map<String,Object>
	 */
	@RequestMapping(value="/saveFlow")
	@ResponseBody
	public Map<String,Object> saveFlow(AccountFlow record){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isSuccess", false);
		try {
			if(record.getId() != null){
				record.setUpdateUser(currentUser().getUserId());
				record.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			}else{
				record.setAddUser(currentUser().getUserId());
				record.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
			}
			bankCardFlowService.saveFlow(record);
			map.put("isSuccess", true);
		} catch (Exception e) {
			map.put("message", "保存信息出错;"+e.getMessage());
			logger.error("保存银行卡流水信息错误：" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * <p>
	 * Description:修改流水信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-27
	 * @param date
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/updateFlow/{id}")
	public ModelAndView updateFlow(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/finance/bankCardFlow/edit");
		List<Configuration> list = null;
		List<Configuration> incomeList = null;
		List<BankAccountInfo> accountList = null;
		try {
			list = accountLogService.queryConfigurationListByType(80706);
			incomeList = accountLogService.queryConfigurationListByType(1800);
			accountList = bankAccountService.selectAll();
			if(id != null){
				AccountFlow flow = bankCardFlowService.queryById(id);
				mv.addObject("flow", flow);
				mv.addObject("cardId", flow.getCardId());
			}
			mv.addObject("incomeList", incomeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金记录获取交易类型列表错误：" + e.getMessage());
		}
		return mv.addObject("bankList", list).addObject("accountList", accountList);
	} 
	
	
	@RequestMapping(value="/deleteFlow")
	@ResponseBody
	public Map<String,Object> deleteFlow(AccountFlow record){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isSuccess", false);
		try {
			if(record.getId() != null){
				record.setUpdateUser(currentUser().getUserId());
				record.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
				bankCardFlowService.deleteFlow(record);
				map.put("isSuccess", true);
			}
		} catch (Exception e) {
			map.put("message", "保存信息出错;"+e.getMessage());
			logger.error("保存银行卡流水信息错误：" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	
	@RequestMapping("/addType")
	public ModelAndView addType(Integer id) {
		ModelAndView mv = new ModelAndView("/finance/bankCardFlow/add");
		mv.addObject("id", id);
		return mv;
	}
	/**
	 * 
	 * <p>
	 * Description:保存流水分类信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-28
	 * @param record
	 * @return
	 * Map<String,Object>
	 */
	@RequestMapping(value="/saveType")
	@ResponseBody
	public Map<String,Object> saveType(Configuration record){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isSuccess", false);
		try {
			bankCardFlowService.saveType(record);
			map.put("isSuccess", true);
		} catch (Exception e) {
			map.put("message", "新增流水类型出错;"+e.getMessage());
			logger.error("新增流水类型错误：" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	

	/**
	 * 
	 * <p>
	 * Description: 导出流水对账信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-27
	 * @param requestCnd
	 * @param model
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@RequestMapping("/export")
	public ModelAndView exportCheckWithList(String date, ModelMap model)
			throws Exception {
		List<AccountFlowResponse> list = bankCardFlowService.export(date);
		return new ModelAndView(
				new ViewExcel("流水信息列表" + DateUtils.formatDateYmd(new Date()), list), model);
	}
  
}
