package com.cxdai.console.customer.bankcard.controller;

import java.util.List;

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
import com.cxdai.console.customer.bankcard.entity.BankCnd;
import com.cxdai.console.customer.bankcard.entity.BankVo;
import com.cxdai.console.customer.bankcard.service.BankService;

@Controller
@RequestMapping(value="/bankManager")
public class BankController extends BaseController {

	Logger logger = LoggerFactory.getLogger(BankController.class);
	
	
	@Autowired
	public BankService bankService;
	
	
	@RequestMapping(value="/toBankInfoManagerMain")
	@ResponseBody
	public ModelAndView toBankInfoManagerMain(){
		return forword("/customer/bankcard/bankInfoManagerMain");
	}

	@RequestMapping(value="/serachAll/{pageNo}")
	@ResponseBody
	public ModelAndView serachAll(@ModelAttribute BankCnd bankCnd,@PathVariable("pageNo") Integer pageNo){
		Page page = new Page();
		try{
			page = bankService.queryBankVoListForPage(bankCnd, WxContants.WX_PAGE_SIZE, pageNo);
		}catch(Exception e){
			logger.error("获取银行信息失败", e);
		}
		return forword("/customer/bankcard/bankInfoManagerList").addObject("page", page);
	}
	
	
	@RequestMapping(value="/initProvinceList")
	@ResponseBody
	public StringBuffer initParentChannelList() {
		StringBuffer selectList = new StringBuffer();
		try {
			List<BankVo> list = bankService.queryProvinceList();
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getProvince()+","+list.get(i).getProvince()+"|");
			}
		} catch (Exception e) {
			logger.error("获取省份失败",e);
		}
		return selectList;
	}
	@RequestMapping(value="/initCityList")
	@ResponseBody
	public StringBuffer initCityList(String province) {
		StringBuffer selectList = new StringBuffer();
		try {
			List<BankVo> list = bankService.queryCityList(province);
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getCity()+","+list.get(i).getCity()+"|");
			}
		} catch (Exception e) {
			logger.error("获取城市失败",e);
		}
		return selectList;
	}
	@RequestMapping(value="/initDistrictList")
	@ResponseBody
	public StringBuffer initDistrictList(String city) {
		StringBuffer selectList = new StringBuffer();
		try {
			List<BankVo> list = bankService.queryDistrictList(city);
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getDistrict()+","+list.get(i).getDistrict()+"|");
			}
		} catch (Exception e) {
			logger.error("获取列表失败",e);
		}
		return selectList;
	}
	@RequestMapping(value="/initBankList")
	@ResponseBody
	public StringBuffer initBankList() {
		StringBuffer selectList = new StringBuffer();
		try {
			List<BankVo> list = bankService.queryBankList("");
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getBankName()+","+list.get(i).getBankName()+"|");
			}
		} catch (Exception e) {
			logger.error("获取银行名称失败",e);
		}
		return selectList;
	}

	@RequestMapping(value="/delBankVo")
	@ResponseBody
	public MessageBox delBankVo(Integer bankId) {
		String result = "";
		try {
			result = bankService.delBank(bankId);
			if ("删除成功".equals(result)) {
				return MessageBox.build("1", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageBox.build("0", result);
	}


	@RequestMapping(value="/toBankManagerAdd")
	@ResponseBody
	public ModelAndView toBankManagerAdd(){
		return forword("/customer/bankcard/bankInfoManagerAdd");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:新增或修改支行信息<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日 void
	 */
	@RequestMapping(value="/saveOrUpdateBankVo")
	@ResponseBody
	public MessageBox saveOrUpdateBankVo(@ModelAttribute BankVo bankVo) {
		String result ="";
		try {
			result = bankService.insertOrUpdateBank(bankVo);
			if("新增成功".equals(result) || "修改成功".equals(result)){
				return MessageBox.build("1", "保存成功");
			}
		} catch (Exception e) {
			logger.error("银行卡管理新增或者修改异常",e);
		}
		return MessageBox.build("0", "保存失败");
	}
	
	/**
	 * 
	 * <p>
	 * Description:进入修改页面<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日 void
	 */
	@RequestMapping(value="/initBankVo/{bankId}")
	@ResponseBody
	public ModelAndView initBankVo(@PathVariable("bankId") Integer bankId) {
		BankVo bankVo = null;
		try {
			if (bankId != null && bankId.intValue() > 0) {
				bankVo = bankService.queryBankVoById(bankId);
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/customer/bankcard/bankInfoManagerEdit").addObject("BankVo", bankVo);
	}
}
