package com.cxdai.console.customer.bankcard.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.bankcard.entity.BankcardChange;
import com.cxdai.console.customer.bankcard.entity.BankcardChangeCnd;
import com.cxdai.console.customer.bankcard.entity.BankcardClick;
import com.cxdai.console.customer.bankcard.entity.BankcardClickCnd;
import com.cxdai.console.customer.bankcard.entity.BankcardPic;
import com.cxdai.console.customer.bankcard.entity.BankcardTimes;
import com.cxdai.console.customer.bankcard.entity.BankcardTimesCnd;
import com.cxdai.console.customer.bankcard.entity.BankcardVerification;
import com.cxdai.console.customer.bankcard.entity.BankinfoLog;
import com.cxdai.console.customer.bankcard.entity.LlWapBankcardUnbindRequest;
import com.cxdai.console.customer.bankcard.mapper.BankVerificationMapper;
import com.cxdai.console.customer.bankcard.mapper.BankcardChangeMapper;
import com.cxdai.console.customer.bankcard.mapper.BankcardClickMapper;
import com.cxdai.console.customer.bankcard.mapper.BankcardPicMapper;
import com.cxdai.console.customer.bankcard.mapper.BankcardTimesMapper;
import com.cxdai.console.customer.bankcard.mapper.BankinfoLogMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.system.vo.UserVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BankcardChangeService{

	@Autowired
	private BankcardChangeMapper changeMapper;

	@Autowired
	private BankinfoLogMapper logMapper;

	@Autowired
	private BankcardPicMapper picMapper;

	@Autowired
	private BankcardClickMapper clickMapper;

	@Autowired
	private BankcardTimesMapper timesMapper;

	@Autowired
	private LianlianpayWapService llwappayService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BankVerificationMapper bankVerificationMapper;
	
	public Page pageQuery(BankcardChangeCnd changeCnd, Page page) throws Exception {
		int totalCount = changeMapper.pageQueryCount(changeCnd);
		page.setTotalCount(totalCount);
		List<BankcardChange> list = changeMapper.pageQuery(changeCnd, page);
		page.setResult(list);
		return page;
	}

	public Page pageQueryTimes(BankcardTimesCnd cnd, Page page) throws Exception {
		int totalCount = timesMapper.pageQueryCount(cnd);
		page.setTotalCount(totalCount);
		List<BankcardTimes> list = timesMapper.pageQuery(cnd, page);
		page.setResult(list);
		return page;
	}

	public BankcardChange detail(int id) throws Exception {
		return changeMapper.getById(id);
	}

	public String updateFirstApprove(BankcardChange change,UserVo userVo, String ip) throws Exception {
		int approveStatus = change.getApproveStatus();

		change.setApproveUserId(userVo.getId());
		change.setApproveUserName(userVo.getUserName());

		// 审批通过
		if (approveStatus != 1) {
			// 审批不通过，冻结银行卡
			changeMapper.updateBankinfoStatus(2, change.getUserId());
		}

		// 更新申请，记录审批信息
		changeMapper.updateBankcardChange(change);

		// 添加银行卡操作日志
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(change.getUserId());
		bankinfoLog.setCardNum(change.getOldBankcard());
		bankinfoLog.setType(4);// 4：解绑
		bankinfoLog.setStatus(0);// 0：有效
		bankinfoLog.setAddBy(change.getApproveUserId());
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark((approveStatus == 1) ? "后台初审解绑通过" : "后台初审解绑不通过，银行卡记录冻结");

		logMapper.insert(bankinfoLog);
		return null;
	}

	
	public String updateRecheck(BankcardChange change,UserVo userVo, String ip) throws Exception {
		int approveStatus = change.getApproveStatus();
		BankcardChange bankcardChange = changeMapper.getById(change.getId());
		if (userVo.getId().equals(bankcardChange.getApproveUserId())) {
			return "初审和复审不能为同一人！";
		}

		MemberVo memberVo = memberService.queryMemberById(change.getUserId());
		if (null == memberVo || null == memberVo.getIsFinancialUser()) {
			return "账户异常！";
		}
		change.setRecheckUserId(userVo.getId());
		change.setRecheckUserName(userVo.getUserName());
		change.setRecheckIp(ip);

		// 审批通过
		if (approveStatus == 2) {
			// 更新用户银行卡信息,状态改为-1
			changeMapper.updateBankinfo(change);

			// 换卡次数加1
			timesMapper.addTimes(change.getUserId(), 1, 0, 0);

			String noAgree = changeMapper.getUserCurrentCardNoAgree(change.getUserId());
			if (noAgree != null && !"".equals(noAgree) && memberVo.getIsFinancialUser() == 1) {
				// 调连连支付接口解绑银行卡
				LlWapBankcardUnbindRequest llWapBankcardUnbindRequest = new LlWapBankcardUnbindRequest();
				llWapBankcardUnbindRequest.setUser_id(String.valueOf(change.getUserId()));
				String s = llwappayService.saveBankcardUnbind(llWapBankcardUnbindRequest);
				if (!s.equals(BusinessConstants.SUCCESS)) {
					return "审核解绑失败：" + s;
				}
			}
		} else {
			// 审批不通过，冻结银行卡
			changeMapper.updateBankinfoStatus(2, change.getUserId());
		}

		// 更新申请，记录复审信息
		changeMapper.updateRecheckBankcardChange(change);

		// 添加银行卡操作日志
		BankinfoLog bankinfoLog = new BankinfoLog();
		bankinfoLog.setUserId(change.getUserId());
		bankinfoLog.setCardNum(change.getOldBankcard());
		bankinfoLog.setType(4);// 4:解绑
		bankinfoLog.setStatus(0);// 0：有效
		bankinfoLog.setAddBy(change.getRecheckUserId());
		bankinfoLog.setAddTime(new Date());
		bankinfoLog.setRemark((approveStatus == 2) ? "后台复审解绑通过" : "后台复审解绑不通过，银行卡记录冻结");

		logMapper.insert(bankinfoLog);
		return null;
	}

	public List<BankcardPic> getPics(int bcId) throws Exception {
		return picMapper.getPicsByBcId(bcId);
	}

	public List<BankcardClick> getClicks(BankcardClickCnd clickCnd) throws Exception {
		return clickMapper.getClicksByUserId(clickCnd);
	}
	public Page pageQueryVerifyInfo(BankcardTimesCnd cnd, Page page) throws Exception {
		int totalCount = bankVerificationMapper.pageQueryVerifyInfoCount(cnd);
		page.setTotalCount(totalCount);
		List<BankcardVerification> list = bankVerificationMapper.pageQueryVerifyInfo(cnd, page);
		page.setResult(list);
		return page;
	}
	public String delVerify(Integer id,Integer userId) throws Exception {
		String remark="删除人："+userId;
		if (bankVerificationMapper.delVerify(id,remark) > 0) {
			return "删除四要素验证信息成功！";
		} else {
			return "删除四要素验证信息失败！";
		}
	}
}
