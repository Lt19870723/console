package com.cxdai.console.customer.bankcard.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.Bank;
import com.cxdai.console.customer.bankcard.entity.BankCnd;
import com.cxdai.console.customer.bankcard.entity.BankVo;
import com.cxdai.console.customer.bankcard.mapper.BankMapper;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.ShiroUtils;

/**
 * <p>
 * Description:账号接口的实现类<br />
 * </p>
 * 
 * @title AccountServiceImpl.java
 * @package com.cxdai.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BankService{

	@Autowired
	private BankMapper bankMapper;

	
	public String insertOrUpdateBank(BankVo bankVo) throws Exception {
		if (bankVo.getProvince() == null || bankVo.getProvince().equals("")) {
			return "请选择省份";
		}
		if (bankVo.getCity() == null || bankVo.getCity().equals("")) {
			return "请选择市";
		}
		if (bankVo.getDistrict() == null || bankVo.getDistrict().equals("")) {
			return "请选择区/县";
		}
		if (bankVo.getBankName() == null || bankVo.getBankName().equals("")) {
			return "请选择银行名称";
		}
		if (bankVo.getBranchName() == null || bankVo.getBranchName().equals("")) {
			return "请填写支行名称";
		}
		if (bankVo.getCnapsCode() == null || bankVo.getCnapsCode().equals("")) {
			return "请填写联行号";
		}
		ShiroUser user = ShiroUtils.currentUser();
		if (bankVo.getId() == null || bankVo.getId() == 0) {
			if (bankMapper.queryBankByBankNameAndBranch(bankVo.getBankName(), bankVo.getBranchName()) > 0) {
				return "支行名称已存在，请重新输入";
			}
			bankVo.setId(null);
			bankVo.setStatus(0); // 正常
			BankVo bankVo2 = queryBankCode(bankVo.getBankName());
			bankVo.setBankCode(bankVo2.getBankCode());
			bankVo.setAddUserId(user.getUserId());
			bankVo.setAddTime(new Date());
			Bank bank = new Bank();
			BeanUtils.copyProperties(bankVo, bank);
			if (bankMapper.insertEntity(bank) > 0) {
				return "新增成功";
			} else {
				return "新增失败";
			}
		} else {
			BankVo bankVo_old = queryBankVoById(bankVo.getId());
			if (!bankVo_old.getBranchName().equals(bankVo.getBranchName()) || !bankVo_old.getBankName().equals(bankVo.getBankName())) {
				if (bankMapper.queryBankByBankNameAndBranch(bankVo.getBankName(), bankVo.getBranchName()) > 0) {
					return "支行名称已存在，请重新输入";
				}
			}
			bankVo.setAddTime(bankVo_old.getAddTime());
			bankVo.setAddUserId(bankVo_old.getAddUserId());
			BankVo bankVo2 = queryBankCode(bankVo.getBankName());
			bankVo.setBankCode(bankVo2.getBankCode());

			bankVo.setUpdateUserId(user.getUserId());
			bankVo.setUpdateTime(new Date());
			Bank bank = new Bank();
			BeanUtils.copyProperties(bankVo, bank);
			if (bankMapper.updateEntity(bank) > 0) {
				return "修改成功";
			} else {
				return "修改失败";
			}
		}
	}

	
	public String delBank(int id) throws Exception {
		BankVo bankVo = bankMapper.queryBankVoByIdForUpdate(id);
		if (bankVo != null) {
			ShiroUser user = ShiroUtils.currentUser();
			bankVo.setStatus(-1); // 失效
			bankVo.setUpdateUserId(user.getUserId());
			bankVo.setUpdateTime(new Date());
			Bank bank = new Bank();
			BeanUtils.copyProperties(bankVo, bank);
			if (bankMapper.updateEntity(bank) > 0) {
				return "删除成功";
			} else {
				return "删除失败";
			}
		}
		return "记录不存在，请刷新后重试";
	}

	
	public BankVo queryBankVoById(int id) throws Exception {
		return bankMapper.queryBankVoById(id);
	}

	
	public Page queryBankVoListForPage(BankCnd bankCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		if (bankCnd.getStatus() == null || bankCnd.getStatus() == -2) {
			bankCnd.setStatus(null);
		}
		if (bankCnd.getCnapsCode() == null || bankCnd.getCnapsCode() == 0) {
			bankCnd.setCnapsCode(null);
		}
		int totalCount = bankMapper.queryBankVoCount(bankCnd);
		page.setTotalCount(totalCount);
		List<BankVo> list = bankMapper.queryBankVoList(bankCnd, page);
		page.setResult(list);
		return page;
	}

	
	public List<BankVo> queryProvinceList() throws Exception {
		return bankMapper.queryProvinceList();
	}

	
	public List<BankVo> queryCityList(String province) throws Exception {
		return bankMapper.queryCityList(province);
	}

	
	public List<BankVo> queryDistrictList(String city) throws Exception {
		return bankMapper.queryDistrictList(city);
	}

	
	public List<BankVo> queryBankList(String district) throws Exception {
		return bankMapper.queryBankList(district);
	}

	
	public List<BankVo> queryBranchList(String district, String bankName) throws Exception {
		return bankMapper.queryBranchList(district, bankName);
	}

	
	public List<BankVo> queryBranchListLike(String district, String branch) throws Exception {
		return bankMapper.queryBranchListLike(district, branch);
	}

	
	public BankVo queryBankCode(String bankName) throws Exception {
		List<BankVo> list = bankMapper.queryBankCode(bankName);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	public List<BankVo> queryBankListByDistrict(String district) {
		return bankMapper.queryBankListByDistrict(district);
	}
}
