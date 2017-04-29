package com.cxdai.console.customer.information.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.GetMacAddress;
import com.cxdai.console.customer.information.entity.ApproModifyLog;
import com.cxdai.console.customer.information.entity.Member;
import com.cxdai.console.customer.information.entity.MemberAuditLog;
import com.cxdai.console.customer.information.entity.RealNameAppro;
import com.cxdai.console.customer.information.mapper.MemberAuditLogMapper;
import com.cxdai.console.customer.information.mapper.MemberAuditMapper;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.mapper.RealNameApproMapper;
import com.cxdai.console.customer.information.vo.IntegralVo;
import com.cxdai.console.customer.information.vo.MemberAuditVo;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.customer.information.vo.MobileApproVo;
import com.cxdai.console.customer.information.vo.RealNameApproCnd;
import com.cxdai.console.customer.information.vo.RealNameApproVo;
import com.cxdai.console.lottery.service.ChanceInfoService;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:实名认证业务类<br />
 * </p>
 * 
 * @title RealNameApproService.java
 * @package com.cxdai.console.member.service
 * @author justin.xu
 * @version 0.1 2014年5月6日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class RealNameApproService {

	@Autowired
	private RealNameApproMapper realNameApproMapper;

	@Autowired
	private IntegralService integralService;
	@Autowired
	private MobileApproService mobileApproService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private ApproModifyLogService approModifyLogService;
	@Autowired
	private MemberAuditMapper MemberAuditMapper;

	@Autowired
	MemberAuditLogMapper memberAuditLogMapper;
	
	@Autowired
	private ChanceInfoService chanceInfoService;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryPageListByCnd(RealNameApproCnd realNameApproCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		// 转换成秒数
		if (null != realNameApproCnd.getAppBeginTime()) {
			realNameApproCnd.setAppBeginTime(DateUtils.convert2StartDate(realNameApproCnd.getAppBeginTime()));
			realNameApproCnd.setAppBeginTimeStr(realNameApproCnd.getAppBeginTime().getTime() / 1000 + "");
		}
		if (null != realNameApproCnd.getAppEndTime()) {
			realNameApproCnd.setAppEndTime(DateUtils.convert2EndDate(realNameApproCnd.getAppEndTime()));
			realNameApproCnd.setAppEndTimeStr(realNameApproCnd.getAppEndTime().getTime() / 1000 + "");
		}
		int totalCount = realNameApproMapper.queryRealNameApproCount(realNameApproCnd);
		page.setTotalCount(totalCount);
		List<RealNameApproVo> list = realNameApproMapper.queryRealNameApproList(realNameApproCnd, page);
		page.setResult(list);
		return page;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveApprovePass(RealNameApproVo realNameApproVo, UserVo user, HttpServletRequest request) throws Exception {
		String reuslt = "success";
		// 验证审核数据是否正确
		List<RealNameApproVo> realNameApproVoList = this.queryApproInfoVersionList(realNameApproVo);
		if (null == realNameApproVoList || realNameApproVoList.size() != 1) {
			return "数据已变更，请刷新页面或稍或重试！";
		}
		RealNameApproVo updateRealNameApproVo = realNameApproVoList.get(0);
		if (updateRealNameApproVo.getIsPassed() != 0) {
			return "实名认证已审核，无法再次审核！";
		}

		// 验证是否有重复数据
		RealNameApproCnd idCardNoCnd = new RealNameApproCnd();
		idCardNoCnd.setIdCardNo(updateRealNameApproVo.getIdCardNo());
		idCardNoCnd.setId(String.valueOf(updateRealNameApproVo.getId()));

		MemberVo memberVo = memberMapper.queryMemberById(updateRealNameApproVo.getUserId());
		if (memberVo == null) {
			return "用户不存在，请刷新后再试！";
		}

		Integer count = realNameApproMapper.queryRepeatRealNameApproCount(idCardNoCnd);

		/** 审核时如果是借款用户则不进行身份证号唯一性校验 */
		if (count > 0 && memberVo.getIsFinancialUser() == Constants.MEMBER_FINANCIAL_USER) {
			return "身份证号码已存在,请确认！";
		}
		// 更新状态
		modifyApproPassed(realNameApproVo, user, Constants.REALNAME_APPR_ISPASSED_PASSED, updateRealNameApproVo);
		logModifyRecord(updateRealNameApproVo, user, 1, "实名认证审核通过", HttpTookit.getRealIpAddr(request));

		// 增加信用积分
		IntegralVo integralVo = integralService.queryIntegralByUserId(updateRealNameApproVo.getUserId());
		integralVo.setCreditIntegral(integralVo.getCreditIntegral() + BusinessConstants.REALNAME_APPRO_CREDIT_INTEGRAL);
		integralService.updateCreditLevel(integralVo);
		// 手机与实名认证通过，发放奖励
		MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(updateRealNameApproVo.getUserId());
		if (null != mobileApproVo && null != mobileApproVo.getPassed() && mobileApproVo.getPassed() == 1) {
			mobileApproService.saveRealNameMobileAward(updateRealNameApproVo.getUserId(), request);
		}

		// 实名认证通过,更新用户的论坛权限
		memberMapper.updateBbsUserGroupForRegistered(updateRealNameApproVo.getUserId());
		memberMapper.insertBbsUserPermissionForRegistered(updateRealNameApproVo.getUserId());
		
/*		// 发放“实名认证奖”抽奖机会
		if (chanceInfoService.selectCountByCodeAndUserId(BusinessConstants.LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_REAL_NAME_APPRO, memberVo.getId()) == 0) { //注册时间大于等于定义的新手起始时间
			//新增“实名认证奖”抽奖机会信息记录
			chanceInfoService.insertLotteryChanceInfoByCode(memberVo.getId(), BusinessConstants.LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_REAL_NAME_APPRO);
		}*/
		return reuslt;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveMemberAuditPass(MemberAuditVo memberAuditVo, UserVo user, HttpServletRequest request) throws Exception {
		Member member = new Member();
		member = memberMapper.queryById(memberAuditVo.getId());
		if (member.getStatus() != null) {
			if (Integer.valueOf(member.getStatus()) != -2) {
				return "用户已审核";
			}

		}
		member.setId(memberAuditVo.getId());
		member.setStatus(Integer.valueOf(0));// 审核通过
		memberMapper.updateEntity(member);
		MemberAuditLog memberAuditLog = MemberAuditLog.createMemberAuditLog(member, user, Integer.valueOf(1), "审核通过:  ==" + memberAuditVo.getReason(), request);
		memberAuditLogMapper.insertEntity(memberAuditLog);
		return "success";
	}

	@Transactional(rollbackFor = Throwable.class)
	private void logModifyRecord(RealNameApproVo realNameApproVo, UserVo user, Integer isPassed, String remark, String ip) throws Exception {
		ApproModifyLog apprModifyLog = new ApproModifyLog();
		apprModifyLog.setUserId(realNameApproVo.getUserId());
		apprModifyLog.setAddIp(ip);
		apprModifyLog.setAddtime(new Date());
		apprModifyLog.setStaffId(user.getId());
		apprModifyLog.setAddMac(GetMacAddress.getMacAddress());
		apprModifyLog.setType(BusinessConstants.APPRO_MODIFY_LOG_TYPE_REALNAME);
		apprModifyLog.setNewContent(realNameApproVo.getRealName());
		apprModifyLog.setIsPassed(isPassed);
		apprModifyLog.setRemark(remark);
		apprModifyLog.setPlatform(1);
		approModifyLogService.saveApproModifyLog(apprModifyLog);
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveApproveReject(RealNameApproVo realNameApproVo, UserVo user) throws Exception {
		String reuslt = "success";
		// 验证审核数据是否正确
		List<RealNameApproVo> realNameApproVoList = this.queryApproInfoVersionList(realNameApproVo);
		if (null == realNameApproVoList || realNameApproVoList.size() != 1) {
			return "数据已变更，请刷新页面或稍或重试！";
		}
		RealNameApproVo updateRealNameApproVo = realNameApproVoList.get(0);
		if (updateRealNameApproVo.getIsPassed() != 0) {
			return "实名认证已审核，无法再次审核！";
		}
		// 更新状态
		modifyApproPassed(realNameApproVo, user, Constants.REALNAME_APPR_ISPASSED_REJECT, updateRealNameApproVo);
		logModifyRecord(updateRealNameApproVo, user, -1, "实名认证审核不通过", "####");
		return reuslt;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String saveMemberAuditUnPass(MemberAuditVo memberAuditVo, UserVo user, HttpServletRequest request) throws Exception {
		String reuslt = "success";
		Member member = new Member();
		member = memberMapper.queryById(memberAuditVo.getId());
		if (member.getStatus() != null) {
			if (Integer.valueOf(member.getStatus()) != -2) {
				return "用户已审核";
			}
		}
		member.setId(memberAuditVo.getId());
		member.setStatus(Integer.valueOf(-3));// 审核不通过
		memberMapper.updateEntity(member);
		MemberAuditLog memberAuditLog = MemberAuditLog.createMemberAuditLog(member, user, Integer.valueOf(-1), "审核不通过:  ==" + memberAuditVo.getReason(), request);
		memberAuditLogMapper.insertEntity(memberAuditLog);
		return reuslt;
	}

	/**
	 * <p>
	 * Description:验证审核数据是否正确，并传递更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月6日
	 * @param realNameApproVo
	 * @param staff
	 * @return String
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<RealNameApproVo> queryApproInfoVersionList(RealNameApproVo realNameApproVo) throws Exception {
		List<RealNameApproVo> realNameApproVoList = null;
		// 查询数据
		RealNameApproCnd realNameApproCnd = new RealNameApproCnd();
		realNameApproCnd.setId(String.valueOf(realNameApproVo.getId()));
		realNameApproCnd.setVersion(String.valueOf(realNameApproVo.getVersion()));
		realNameApproVoList = realNameApproMapper.queryRealNameApproList(realNameApproCnd);
		return realNameApproVoList;
	}

	/**
	 * <p>
	 * Description:更新状态<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月6日
	 * @param realNameApproVo
	 * @param staff
	 * @param isPassed
	 * @param updateRealNameApproVo
	 * @throws Exception void
	 */
	@Transactional(rollbackFor = Throwable.class)
	private void modifyApproPassed(RealNameApproVo realNameApproVo, UserVo user, Integer isPassed, RealNameApproVo updateRealNameApproVo) throws Exception {
		// 更新状态
		updateRealNameApproVo.setIsPassed(isPassed);
		updateRealNameApproVo.setReason(realNameApproVo.getReason());
		RealNameAppro realNameAppro = new RealNameAppro();
		BeanUtils.copyProperties(updateRealNameApproVo, realNameAppro);
		realNameAppro.setVersion(realNameAppro.getVersion() + 1);
		realNameAppro.setApproveTime(DateUtils.getTime());
		realNameAppro.setApproveUser(user.getUserName());
		realNameApproMapper.updateEntity(realNameAppro);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryMemberPageListByCnd(MemberCnd memberCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		// 转换成秒数
		/*
		 * if (null != realNameApproCnd.getAppBeginTime()) {
		 * realNameApproCnd.setAppBeginTime
		 * (DateUtils.convert2StartDate(realNameApproCnd.getAppBeginTime()));
		 * realNameApproCnd
		 * .setAppBeginTimeStr(realNameApproCnd.getAppBeginTime().getTime() /
		 * 1000 + ""); } if (null != realNameApproCnd.getAppEndTime()) {
		 * realNameApproCnd
		 * .setAppEndTime(DateUtils.convert2EndDate(realNameApproCnd
		 * .getAppEndTime()));
		 * realNameApproCnd.setAppEndTimeStr(realNameApproCnd
		 * .getAppEndTime().getTime() / 1000 + ""); }
		 */
		int totalCount = MemberAuditMapper.countMemberList(memberCnd);
		page.setTotalCount(totalCount);
		List<MemberAuditVo> list = MemberAuditMapper.queryMemberList(memberCnd, page);
		page.setResult(list);
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public RealNameApproVo getRealNameApproByUserName(String userName) {
		return realNameApproMapper.getRealNameApproByUserName(userName);
	}

}
