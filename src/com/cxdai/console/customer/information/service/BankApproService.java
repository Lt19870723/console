package com.cxdai.console.customer.information.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.BankcardVerification;
import com.cxdai.console.customer.bankcard.entity.BankinfoLog;
import com.cxdai.console.customer.bankcard.mapper.BankVerificationMapper;
import com.cxdai.console.customer.bankcard.mapper.BankinfoLogMapper;
import com.cxdai.console.customer.information.mapper.BankApproMapper;
import com.cxdai.console.customer.information.mapper.MobileApproMapper;
import com.cxdai.console.customer.information.vo.BankApproCnd;
import com.cxdai.console.customer.information.vo.BankApproVo;
import com.cxdai.console.customer.information.vo.MobileApproCnd;
import com.cxdai.console.customer.information.vo.MobileApproVo;
import com.cxdai.console.customer.information.vo.RealNameApproVo;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.SendAuthReqUtil;
/**
 * @author WangQianJin
 * @title 银行卡审核
 * @date 2016年6月7日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BankApproService {

	@Autowired
	private BankApproMapper bankApproMapper;
	
	@Autowired
	private BankinfoLogMapper logMapper;
	
	@Autowired
	private MobileApproMapper mobileApproMapper;
	
	@Autowired
	private BankVerificationMapper bankVerificationMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryPageListByCnd(BankApproCnd bankApproCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = bankApproMapper.queryBankApproCount(bankApproCnd);
		page.setTotalCount(totalCount);
		List<BankApproVo> list = bankApproMapper.queryBankApproList(bankApproCnd, page);
		page.setResult(list);
		return page;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveApprovePass(BankApproVo bankApproVo, UserVo user) throws Exception {
		String reuslt = "success";
		// 验证审核数据是否正确
		BankApproVo bank = bankApproMapper.queryById(bankApproVo.getId());
		if (null == bank || bank.getStatus().intValue() != 3) {
			return "数据已变更，请刷新页面或稍或重试！";
		}
		//获取用户手机号
		MobileApproCnd mobileApproCnd = new MobileApproCnd();
		mobileApproCnd.setUserId(bank.getUserId());		
		List<MobileApproVo>	list = mobileApproMapper.queryMobileApproList(mobileApproCnd);		
		String mobile="";
		if (null != list && list.size() > 0) {
			MobileApproVo m = list.get(0);
			if(m.getMobileNum()!=null){
				mobile=m.getMobileNum();
			}
		}
		//银行卡三要素验证
		try {
			Map<?, ?> resut = SendAuthReqUtil.sendAuthBankCodeValid(bank.getRealName(), bank.getIdCardNo(), bank.getCardNum(), mobile);
			RealNameApproVo realNameAppro = new RealNameApproVo();
			realNameAppro.setRealName(bank.getRealName());
			realNameAppro.setIdCardNo(bank.getIdCardNo());
			if (null != resut && !resut.get("auResultCode").equals("SUCCESS")) {
				// 如果验证失败，记录验证日志
				BankcardVerification bankcardVerification = new BankcardVerification();
				bankcardVerification.setType("1");
				insertBankVerification(bank.getUserId().toString(), resut, realNameAppro, bank.getCardNum(), mobile, bankcardVerification);
				return resut.get("auResultInfo").toString();
			} else {
				BankcardVerification bankcardVerification = new BankcardVerification();
				bankcardVerification.setType("1");
				insertBankVerification(bank.getUserId().toString(), resut, realNameAppro, bank.getCardNum(), mobile, bankcardVerification);
			}
		} catch (Exception e) {
			return "调用银行卡三要素验证接口失败！";
		}
		
		//修改银行卡状态
		bank.setStatus(0);
		bank.setVerifyStatus(1);
		bank.setBankVerif(1);
		bankApproMapper.updateBankInfo(bank);
		// 添加银行卡操作日志
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(bank.getUserId());
		bankinfoLog.setCardNum(bank.getCardNum());
		bankinfoLog.setType(1);// 3:更换
		bankinfoLog.setStatus(0);// 0：有效
		bankinfoLog.setAddBy(user.getId());
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark(bankApproVo.getRemark());
		bankinfoLog.setBankVerif(1);
		//添加银行卡日志
		logMapper.insertBankInfoLog(bankinfoLog);
		//修改用户信息绑定标识
		Map map=new HashMap();
		map.put("id", bank.getUserId());
		map.put("bankVerif",1);
		bankApproMapper.updateMemberById(map);
		
		return reuslt;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveApproveReject(BankApproVo bankApproVo, UserVo user) throws Exception {
		String reuslt = "success";
		
		// 验证审核数据是否正确
		BankApproVo bank = bankApproMapper.queryById(bankApproVo.getId());
		if (null == bank || bank.getStatus().intValue() != 3) {
			return "数据已变更，请刷新页面或稍或重试！";
		}

		//修改银行卡状态
		bank.setStatus(-1);
		bank.setVerifyStatus(0);
		bank.setBankVerif(0);
		bankApproMapper.updateBankInfo(bank);
		// 添加银行卡操作日志
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(bank.getUserId());
		bankinfoLog.setCardNum(bank.getCardNum());
		bankinfoLog.setType(1);// 1:添加
		bankinfoLog.setStatus(1);// 1：无效
		bankinfoLog.setAddBy(user.getId());
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark(bankApproVo.getRemark());
		bankinfoLog.setBankVerif(0);
		//添加银行卡日志
		logMapper.insertBankInfoLog(bankinfoLog);
		
		return reuslt;
	}
	
	/**
	 * 添加银行卡四要素返回信息
	 * @author WangQianJin
	 * @title @param userId
	 * @title @param resut
	 * @title @param realNameAppro
	 * @title @param bankCardNum
	 * @title @param bankPhone
	 * @title @param bankcardVerification
	 * @date 2016年6月6日
	 */
	private void insertBankVerification(String userId,Map<?, ?> resut,RealNameApproVo realNameAppro,String bankCardNum,String bankPhone,BankcardVerification bankcardVerification){
	  	   bankcardVerification.setUserId(userId.toString());
	       if(null!=resut.get("auSuccessTime")){
	      	   bankcardVerification.setVerifyTime(resut.get("auSuccessTime").toString());
	  	   }
	  	   if(null!=resut.get("auResultCode")){
	  		   bankcardVerification.setAuResultCode(resut.get("auResultCode").toString());
	  	   }
	  	   if(null!=resut.get("auResultInfo")){
	  		   bankcardVerification.setAuResultInfo(resut.get("auResultInfo").toString());
	  	   }
	  	   if(null!=resut.get("errorCode")){
	      	   bankcardVerification.setErrorCode(resut.get("errorCode").toString());
	  	   }
	  	   if(null!=resut.get("errorMsg")){
	  		   bankcardVerification.setErrorMsg(resut.get("errorMsg").toString());
	  	   }
	  	   bankcardVerification.setAdd_time(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	  	   bankcardVerification.setBankCardNum(bankCardNum);
	  	   bankcardVerification.setBankPhone(bankPhone);
	  	   bankcardVerification.setRealName(realNameAppro.getRealName());
	  	   bankcardVerification.setIdCardNo(realNameAppro.getIdCardNo());
	  	   bankVerificationMapper.insert(bankcardVerification);
	}

}
