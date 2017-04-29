package com.cxdai.console.finance.virement.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.finance.virement.entity.AccountBalance;
import com.cxdai.console.finance.virement.entity.BankAccountInfo;
import com.cxdai.console.finance.virement.service.AccountBalanceService;
import com.cxdai.console.finance.virement.service.BankAccountService;
import com.cxdai.console.finance.virement.vo.AccountBalanceResponseCnd;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ViewExcel;

/***
 * 充值核对Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/finance/balance")
public class AccountBalanceController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(AccountBalanceController.class);
	
	@Autowired
	private AccountBalanceService accountBalanceService;
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
		String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		return new ModelAndView("/finance/balance/main").addObject("nowDate",nowDate);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查询余额信息列表<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-25
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/queryBalanceByTime")
	public ModelAndView queryAccountBalance(HttpServletRequest requestm,String date){
		ModelAndView mv = new ModelAndView("/finance/balance/list");
		try{
			List<AccountBalanceResponseCnd> companyList = accountBalanceService.queryAccountInfo(date,"1");
			List<AccountBalanceResponseCnd> emplayeeList = accountBalanceService.queryAccountInfo(date,"2");
			List<AccountBalanceResponseCnd> otherList = accountBalanceService.queryAccountInfoOther(date);
			mv.addObject("companyList", companyList).addObject("emplayeeList", emplayeeList).addObject("otherList", otherList);
		}catch(Exception e){
			logger.error("查询账户余额信息失败！" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="/exportBalanceByTime")
	public ModelAndView exportCheckWithList(HttpServletRequest requestm,String date, ModelMap model)
			throws Exception {
		List<AccountBalanceResponseCnd> companyList = accountBalanceService.queryAccountInfo(date,"1");
		List<AccountBalanceResponseCnd> emplayeeList = accountBalanceService.queryAccountInfo(date,"2");
		List<AccountBalanceResponseCnd> otherList = accountBalanceService.queryAccountInfoOther(date);
		int comNum = companyList.size();
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal allTotal = BigDecimal.ZERO;
		if(comNum > 0){
			for(AccountBalanceResponseCnd  company : companyList){
				total = total.add(company.getBalance());
			}
			AccountBalanceResponseCnd response = new AccountBalanceResponseCnd();
			response.setPayName("合计");
			response.setBalance(total);
			companyList.add(response);
		}
		if(emplayeeList.size() > 0){
			companyList.addAll(emplayeeList);
			for(AccountBalanceResponseCnd  company : emplayeeList){
				allTotal = allTotal.add(company.getBalance());
			}
			allTotal=allTotal.add(total);
			AccountBalanceResponseCnd response = new AccountBalanceResponseCnd();
			response.setPayName("总合计");
			response.setBalance(allTotal);
			companyList.add(response);
		}
		if(otherList.size() > 0){
			companyList.addAll(otherList);
		}
		
		String[] headData = new String[] { "开户名", "账号", "银行", "日期", "余额" };
		List<String[]> dataList = new ArrayList<String[]>();
		String[] endData = null;
		if (companyList != null && comNum > 0) {
			for(AccountBalanceResponseCnd res : companyList){
				String[] data = new String[headData.length];
				if(res.getAccId() == null){
					data[0] = res.getPayName();
				}else{
					if(res.getAccId() == -1){
						data[0] = "线上充值";
					}else if(res.getAccId() == -2){
						data[0] = "客户提现";
					}else if(res.getAccId() == -3){
						data[0] = "网银在线";
					}	
				}
				data[1] = res.getCardNo() == null ? "" : res.getCardNo();
				data[2] = res.getBankName() == null ? "" : res.getBankName();
				data[3] = res.getEndTime()==null ? "" :DateUtils.format(res.getEndTime(), DateUtils.DATETIME_YMD_DASH)+"止";
				data[4] = res.getBalance() != null ? res.getBalance()+"" : "";
				dataList.add(data);
			}
		} else {
			String[] data = new String[1];
			data[0] = "暂无数据";
			dataList.add(data);
		}
		return new ModelAndView(
				new ViewExcel("余额结算列表" + DateUtils.formatDateYmd(new Date()), headData, dataList, endData), model);
	
	}
	
	/**
	 * 
	 * <p>
	 * Description:添加余额信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-25
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView toapplyFor(Integer id,String endTime) {
		List<Configuration> list = null;
		List<BankAccountInfo> accountList = null;
		ModelAndView mv = new ModelAndView("finance/balance/add");
		mv.addObject("endTime", endTime);
		try {
			list = accountLogService.queryConfigurationListByType(80706);
			accountList = bankAccountService.selectAll();
			if (id != null) {
				AccountBalance accountBalance = accountBalanceService.selectByPrimaryKey(id);
				mv.addObject("accountBalance", accountBalance);
				mv.addObject("accId", accountBalance.getAccId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金记录获取交易类型列表错误：" + e.getMessage());
		}
		return mv.addObject("bankList", list).addObject("accountList", accountList);

	}
	
	/**
	 * 
	 * <p>
	 * Description:新增/变更余额信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-25
	 * @param record
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value="/saveBalcanceInfo")
	@ResponseBody
	public MessageBox saveBalanceInfo(@ModelAttribute AccountBalance record){
		try{
			if(record.getId() != null){
				record.setUpdateUser(currentUser().getUserId());
				record.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			}else{
				record.setAddUser(currentUser().getUserId());
				record.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				record.setUpdateUser(currentUser().getUserId());
				record.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			}
			accountBalanceService.saveBalance(record);
			return new MessageBox("true", "保存成功！");
		}catch(Exception e){
			logger.error("保存银行卡余额信息出错：" + e.getMessage());
			e.printStackTrace();
		}
		return new MessageBox("false", "保存失败！");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:作废余额信息数据<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-26
	 * @param record
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value="/deleteBalcance")
	@ResponseBody
	public MessageBox deleteBalcance(@ModelAttribute AccountBalance record){
		try{
			if(record.getId() == null){
				return new MessageBox("false", "参数错误！");
			}
			record.setUpdateUser(currentUser().getUserId());
			record.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			accountBalanceService.deleteBalance(record);
			return new MessageBox("true", "操作成功！");
		}catch(Exception e){
			logger.error("作废银行卡余额信息出错：" + e.getMessage());
			e.printStackTrace();
		}
		return new MessageBox("false", "操作失败！");
	}
	
}
