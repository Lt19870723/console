package com.cxdai.console.customer.information.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.GetMacAddress;
import com.cxdai.console.customer.information.entity.ApproModifyLog;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.mapper.MobileApproMapper;
import com.cxdai.console.customer.information.vo.EmailApproVo;
import com.cxdai.console.customer.information.vo.MemberRepeatCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.customer.information.vo.MobileApproCnd;
import com.cxdai.console.customer.information.vo.MobileApproVo;
import com.cxdai.console.customer.information.vo.RealNameApproCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountLogCnd;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:手机验证业务类<br />
 * </p>
 * 
 * @title MobileApproService.java
 * @package com.cxdai.console.member.service
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class MobileApproService {

	public Logger logger = Logger.getLogger(MobileApproService.class);

	@Autowired
	private MobileApproMapper mobileApproMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private ApproModifyLogService approModifyLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberMapper memberMapper;

	public MobileApproVo queryMobileApproByUserId(Integer memberId) throws Exception {
		MobileApproCnd mobileApproCnd = new MobileApproCnd();
		mobileApproCnd.setUserId(memberId);
		List<MobileApproVo> list = mobileApproMapper.queryMobileApproList(mobileApproCnd);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * <p>
	 * Description:手机与实名认证通过，发放奖励<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月30日
	 * @param userId
	 * @return Account
	 */

	public void saveRealNameMobileAward(int userId, HttpServletRequest request) throws Exception {

		// 12月活动规则改变,此处不再奖励10元(1417363200000L = 2014-12-01)
		if (System.currentTimeMillis() >= 1417363200000L) {
			return;
		}

		// 查询是否已发放过奖励
		AccountLogCnd accountLogCnd = new AccountLogCnd();
		accountLogCnd.setUserId(userId);
		accountLogCnd.setType("web_recharge");
		accountLogCnd.setRemark("实名与手机认证通过奖励");
		Integer count = accountLogService.queryAccountLogCount(accountLogCnd);
		// 已发放直接返回
		if (count > 0) {
			return;
		}
		accountLogCnd.setRemark("邮箱、手机及实名认证通过奖励");
		count = accountLogService.queryAccountLogCount(accountLogCnd);
		// 已发放直接返回
		if (count > 0) {
			return;
		}
		AccountVo accountVo = accountService.queryAccountByUserId(userId);
		// 更新帐号
		accountVo.setTotal(accountVo.getTotal().add(BusinessConstants.APPRO_PASS_MONEY));
		accountVo.setUseMoney(accountVo.getUseMoney().add(BusinessConstants.APPRO_PASS_MONEY));
		accountVo.setNoDrawMoney(accountVo.getNoDrawMoney().add(BusinessConstants.APPRO_PASS_MONEY));
		Account account = new Account();
		BeanUtils.copyProperties(accountVo, account);
		Integer updateCount = accountMapper.updateEntity(account);
		if (null == updateCount || updateCount == 0) {
			throw new RuntimeException("帐号数据已变更,请刷新页面或稍后重试！");
		}
		// 插入日志
		AccountLog accountLog = new AccountLog();
		accountLog.setAddip(HttpTookit.getRealIpAddr(request));
		accountLog.setCollection(accountVo.getCollection());
		accountLog.setUseMoney(accountVo.getUseMoney());
		accountLog.setNoUseMoney(accountVo.getNoUseMoney());
		accountLog.setTotal(accountVo.getTotal());
		accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
		accountLog.setMoney(BusinessConstants.APPRO_PASS_MONEY);// 交易金额
		accountLog.setRemark("邮箱、手机及实名认证通过奖励");
		accountLog.setToUser(userId);
		accountLog.setUserId(accountVo.getUserId());
		accountLog.setType("web_recharge");
		accountLog.setDrawMoney(accountVo.getDrawMoney());
		accountLog.setNoDrawMoney(accountVo.getNoDrawMoney());
		accountLogService.insertAccountLog(accountLog);
	}

	public Page getNoPhoneCheck(RealNameApproCnd realNameApproCnd, int curPage, int pageSize) {
		Page page = new Page(curPage, pageSize);

		int totalCount;

		List<MobileApproVo> list;
		try {
			totalCount = mobileApproMapper.getNoMobileCheckCount(realNameApproCnd);
			page.setTotalCount(totalCount);
			list = mobileApproMapper.getNoMobileCheckList(realNameApproCnd, page);

			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	// 审核通过
	@Transactional(rollbackFor = Throwable.class)
	public String saveNoMobileCheck(MobileApproVo mobileApproVo) throws Exception {
		String result = BusinessConstants.SUCCESS;
		MemberVo memberVo = memberService.queryMemberById(mobileApproVo.getUserId());
		MobileApproCnd mobileApproCnd = new MobileApproCnd();
		mobileApproCnd.setUserId(mobileApproVo.getUserId());
		List<MobileApproVo> list = null;
		list = mobileApproMapper.queryMobileApproList(mobileApproCnd);
		MobileApproVo currentMobileApproVo = null;
		if (null != list && list.size() > 0) {
			currentMobileApproVo = list.get(0);
		}
		// 判断是否是理财用户，理财用户的手机号不能重复
		if (memberVo.getIsFinancialUser().equals(Integer.parseInt(Constants.IS_FINANCIAL_USER))) {
			// 验证手机
			MobileApproCnd mobileApproRepeatCnd = new MobileApproCnd();
			mobileApproRepeatCnd.setMobileNum(currentMobileApproVo.getMobileNum().trim());
			mobileApproRepeatCnd.setId(mobileApproVo.getUserId());
			Integer mobileCount = mobileApproMapper.queryRepeatMobileApproCount(mobileApproRepeatCnd);
			if (null != mobileCount && mobileCount > 0) {
				return "手机号已经存在！";
			}
		}
		mobileApproMapper.updateMobileApper(mobileApproVo);
		// 设置用户为正常用户
		mobileApproMapper.updateMobileMember(mobileApproVo);
		logModifyRecord(BusinessConstants.APPRO_MODIFY_LOG_TYPE_MOBILE, mobileApproVo.getUserId(), ((ShiroUser) (SecurityUtils.getSubject().getPrincipal())).getUserId(),
				currentMobileApproVo.getMobileNum(), 1, "手机审核通过", "####");
		return result;
	}

	private void logModifyRecord(Integer type, Integer userId, Integer staffId, String newContent, Integer isPassed, String remark, String ip) throws Exception {
		ApproModifyLog apprModifyLog = new ApproModifyLog();
		apprModifyLog.setUserId(userId);
		apprModifyLog.setAddIp(ip);
		apprModifyLog.setAddtime(new Date());
		apprModifyLog.setStaffId(staffId);
		apprModifyLog.setAddMac(GetMacAddress.getMacAddress());
		apprModifyLog.setType(type);
		apprModifyLog.setNewContent(newContent);
		apprModifyLog.setIsPassed(isPassed);
		apprModifyLog.setRemark(remark);
		apprModifyLog.setPlatform(1);
		approModifyLogService.saveApproModifyLog(apprModifyLog);
	}

	// 审核不通过
	@Transactional(rollbackFor = Throwable.class)
	public void nopassMobileCheck(MobileApproVo mobileApproVo) {
		MobileApproCnd mobileApproCnd = new MobileApproCnd();
		mobileApproCnd.setUserId(mobileApproVo.getUserId());
		List<MobileApproVo> list = null;
		try {
			list = mobileApproMapper.queryMobileApproList(mobileApproCnd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		MobileApproVo currentMobileApproVo = null;
		if (null != list && list.size() > 0) {
			currentMobileApproVo = list.get(0);
		}
		MemberVo memberVo = null;
		try {
			memberVo = memberService.queryMemberById(mobileApproVo.getUserId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			int returnS = mobileApproMapper.getMobileCheck(mobileApproVo);
			if (memberVo != null && memberVo.getIsFinancialUser() == 0) {// 借款用户
				// 是否初始化
				if (returnS == 0) {
					mobileApproMapper.saveNoMobileCheck(mobileApproVo);
				} else {
					mobileApproMapper.updateMobileAppernopass(mobileApproVo);
				}
			} else if (memberVo != null && memberVo.getIsFinancialUser() == 1) {// 理财用户
				if (currentMobileApproVo != null && !StringUtils.isEmpty(currentMobileApproVo.getMobileNum())) {
					mobileApproVo.setMobileNum(currentMobileApproVo.getMobileNum());
				}
				mobileApproMapper.updateMobileAppernopass(mobileApproVo);
			} else {
				return;
			}
			logModifyRecord(BusinessConstants.APPRO_MODIFY_LOG_TYPE_MOBILE, mobileApproVo.getUserId(), ((ShiroUser) (SecurityUtils.getSubject().getPrincipal())).getUserId(),
					currentMobileApproVo.getMobileNum(), -1, "手机审核不通过", "####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return returnS;

	}

	/**
	 * ************************************邮箱**********************************8
	 */

	public Page getNoEmailCheck(RealNameApproCnd realNameApproCnd, int curPage, int pageSize) {
		Page page = new Page(curPage, pageSize);

		int totalCount;

		List<EmailApproVo> list;
		try {
			totalCount = mobileApproMapper.getNoEmailCheckCount(realNameApproCnd);
			page.setTotalCount(totalCount);
			list = mobileApproMapper.getNoEmailCheckList(realNameApproCnd, page);

			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveNoEmailCheck(EmailApproVo emailApproVo) throws Exception {
		String result = BusinessConstants.SUCCESS;
		MemberVo memberVo = memberService.queryMemberById(emailApproVo.getUserId());
		// 判断是否是理财用户，理财用户的邮箱不能重复
		if (memberVo.getIsFinancialUser().equals(Integer.parseInt(Constants.IS_FINANCIAL_USER))) {
			// 验证邮箱
			MemberRepeatCnd emailCnd = new MemberRepeatCnd();
			emailCnd.setEmail(memberVo.getEmail().trim());
			emailCnd.setId(emailApproVo.getUserId());
			Integer emailCount = memberMapper.queryRepeatMemberCount(emailCnd);
			if (null != emailCount && emailCount > 0) {
				return "邮箱已经存在！";
			}
		}
		// 得到check值
		int returnS = mobileApproMapper.getEmailCheck(emailApproVo);

		if (returnS == 0) {
			mobileApproMapper.saveNoEmailCheck(emailApproVo);
			mobileApproMapper.updateEmail(emailApproVo);
		} else {
			mobileApproMapper.updateEmailApper(emailApproVo);
			mobileApproMapper.updateEmailMember(emailApproVo);
		}
		logModifyRecord(BusinessConstants.APPRO_MODIFY_LOG_TYPE_EMAIL, emailApproVo.getUserId(), ((ShiroUser) (SecurityUtils.getSubject().getPrincipal())).getUserId(), memberVo.getEmail(), 1,
				"邮箱审核通过", "####");
		return result;
	}

	// 审核不通过
	@Transactional(rollbackFor = Throwable.class)
	public void noPassEmailCheck(EmailApproVo emailApproVo) {

		try {
			// 得到check值
			int returnS = mobileApproMapper.getEmailCheck(emailApproVo);

			if (returnS == 0) {
				mobileApproMapper.saveNoEmailCheck(emailApproVo);
				// mobileApproMapper.updateEmail(emailApproVo);
			} else {

				mobileApproMapper.updateEmailApperNoPass(emailApproVo);
				// mobileApproMapper.updateEmailMemberNoPass(emailApproVo);
			}
			MemberVo memberVo = null;
			if (emailApproVo.getUserId() != null) {
				memberVo = memberService.queryMemberById(emailApproVo.getUserId());
			}
			String email = "";
			if (memberVo != null) {
				email = memberVo.getEmail();
			}

			logModifyRecord(BusinessConstants.APPRO_MODIFY_LOG_TYPE_EMAIL, emailApproVo.getUserId(), ((ShiroUser) (SecurityUtils.getSubject().getPrincipal())).getUserId(), email, -1, "邮箱审核不通过",
					"####");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return returnS;

	}

	public String updateEmailCheck(EmailApproVo emailApproVo, ApproModifyLog apprModifyLog) throws Exception {
		String result = BusinessConstants.SUCCESS;
		MemberVo memberVo = memberService.queryMemberById(emailApproVo.getUserId());
		// 判断是否是理财用户，理财用户的邮箱不能重复
		if (memberVo.getIsFinancialUser().equals(Integer.parseInt(Constants.IS_FINANCIAL_USER))) {
			// 验证邮箱
			MemberRepeatCnd emailCnd = new MemberRepeatCnd();
			emailCnd.setEmail(emailApproVo.getEMAIL());
			emailCnd.setId(emailApproVo.getUserId());
			Integer emailCount = memberMapper.queryRepeatMemberCount(emailCnd);
			if (null != emailCount && emailCount > 0) {
				return "邮箱已经存在！";
			}
		}
		mobileApproMapper.updateEmailnew(emailApproVo);
		// 插入修改记录
		apprModifyLog.setOldContent(memberVo.getEmail());
		apprModifyLog.setRemark("邮箱修改成功");
		apprModifyLog.setIsPassed(1);
		apprModifyLog.setPlatform(1);
		approModifyLogService.saveApproModifyLog(apprModifyLog);
		return result;
	}

	public String updateMobileCheck(MobileApproVo mobileApproVo, ApproModifyLog apprModifyLog) throws Exception {
		String result = BusinessConstants.SUCCESS;
		MemberVo memberVo = memberService.queryMemberById(mobileApproVo.getUserId());
		// 判断是否是理财用户，理财用户的手机不能重复
		if (memberVo.getIsFinancialUser().equals(Integer.parseInt(Constants.IS_FINANCIAL_USER))) {
			// 验证手机
			MobileApproCnd mobileApproCnd = new MobileApproCnd();
			mobileApproCnd.setMobileNum(mobileApproVo.getMobileNum());
			mobileApproCnd.setId(mobileApproVo.getUserId());
			Integer mobileCount = mobileApproMapper.queryRepeatMobileApproCount(mobileApproCnd);
			if (null != mobileCount && mobileCount > 0) {
				return "手机号已经存在！";
			}
		}
		MobileApproVo oldMobileAppro = this.queryMobileApproByUserId(mobileApproVo.getUserId());
		mobileApproMapper.updateMobilenew(mobileApproVo);
		// 插入修改记录
		apprModifyLog.setOldContent(oldMobileAppro.getMobileNum());
		apprModifyLog.setRemark("手机修改成功");
		apprModifyLog.setIsPassed(1);
		apprModifyLog.setPlatform(1);
		approModifyLogService.saveApproModifyLog(apprModifyLog);
		return result;
	}
}
