package com.cxdai.console.account.recharge.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.entity.RechargeRecord;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.account.recharge.mapper.BaseRechargeRecordMapper;
import com.cxdai.console.account.recharge.mapper.RechargeRecordMapper;
import com.cxdai.console.account.recharge.vo.RechargeRecordCnd;
import com.cxdai.console.account.recharge.vo.RechargeRecordVo;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:充值记录查询和审核实现类<br />
 * </p>
 * 
 * @title RechargeRecordServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年6月12日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class RechargeRecordService {

	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;
	@Autowired
	private BaseRechargeRecordMapper baseRechargeRecordMapper;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private AccountService accountService;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryPageListByCnd(RechargeRecordCnd rechargeRecordCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = rechargeRecordMapper.queryRechargeRecordCount(rechargeRecordCnd);
		page.setTotalCount(totalCount);
		List<RechargeRecordVo> list = rechargeRecordMapper.queryRechargeRecordList(rechargeRecordCnd, page);
		page.setResult(list);
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public RechargeRecordVo queryRechargeRecordById(RechargeRecordCnd rechargeRecordCnd) throws Exception {
		List<RechargeRecordVo> list = rechargeRecordMapper.queryRechargeRecordList(rechargeRecordCnd);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	public String saveApprovePass(RechargeRecordVo rechargeRecordVo, UserVo userVo) throws Exception {
		String result = "success";
		// 验证审核数据是否正确
		List<RechargeRecordVo> rechargeRecordVoList = this.queryApproInfoVersionList(rechargeRecordVo);
		if (null == rechargeRecordVoList || rechargeRecordVoList.size() != 1) {
			throw new RuntimeException("数据已变更，请刷新页面或稍或重试！");
		}
		RechargeRecordVo updateRechargeRecordVo = rechargeRecordVoList.get(0);
		if (updateRechargeRecordVo.getStatus() != Constants.RECHARGE_STATUS_APPROVING) {
			throw new RuntimeException("充值记录状态非审核中，请刷新页面或稍或重试！");
		}
		// 更新状态为初审成功
		updateRechargeRecordVo.setVerifyRemark(rechargeRecordVo.getVerifyRemark());
		updateRechargeRecordVo.setVerifyTime(DateUtils.getTime());
		updateRechargeRecordVo.setVerifyUserid(userVo.getId());
		Integer updateCount = modifyStatusToApprovePass(Constants.RECHARGE_STATUS_FIRST_APPROVE_SUCCESS, updateRechargeRecordVo);
		if (null == updateCount || updateCount == 0) {
			throw new RuntimeException("数据已变更,请刷新页面或稍后重试！");
		}
		return result;
	}

	public String saveApproveReject(RechargeRecordVo rechargeRecordVo, UserVo userVo, HttpServletRequest request) throws Exception {
		String result = "success";
		// 验证审核数据是否正确
		List<RechargeRecordVo> rechargeRecordVoList = this.queryApproInfoVersionList(rechargeRecordVo);
		if (null == rechargeRecordVoList || rechargeRecordVoList.size() != 1) {
			throw new RuntimeException("数据已变更，请刷新页面或稍或重试！");
		}
		RechargeRecordVo updateRechargeRecordVo = rechargeRecordVoList.get(0);
		if (updateRechargeRecordVo.getStatus() != Constants.RECHARGE_STATUS_APPROVING) {
			throw new RuntimeException("充值记录状态非审核中，请刷新页面或稍或重试！");
		}
		// 更新状态为充值失败
		updateRechargeRecordVo.setVerifyRemark(rechargeRecordVo.getVerifyRemark());
		updateRechargeRecordVo.setVerifyTime(DateUtils.getTime());
		updateRechargeRecordVo.setVerifyUserid(userVo.getId());
		Integer updateCount = modifyStatusToApprovePass(Constants.RECHARGE_STATUS_FAILURE, updateRechargeRecordVo);
		if (null == updateCount || updateCount == 0) {
			throw new RuntimeException("数据已变更,请刷新页面或稍后重试！");
		}

		AccountVo accountVo = accountService.queryAccountByUserId(updateRechargeRecordVo.getUserId());
		// 保存账户记录-线下充值失败
		StringBuilder remarkbuilder = new StringBuilder("充值审核未通过。【备注：".concat(rechargeRecordVo.getVerifyRemark()).concat("】"));
		accountLogService.saveAccountLogByParams(accountVo, accountVo.getUserId(), updateRechargeRecordVo.getMoney(), remarkbuilder.toString(), HttpTookit.getRealIpAddr(request), "offline_recharge",
				null, null, null);
		return result;
	}

	public String saveApproveFinallyPass(RechargeRecordVo rechargeRecordVo, UserVo userVo, HttpServletRequest request) throws Exception {
		String result = "success";
		// 验证审核数据是否正确
		List<RechargeRecordVo> rechargeRecordVoList = this.queryApproInfoVersionList(rechargeRecordVo);
		if (null == rechargeRecordVoList || rechargeRecordVoList.size() != 1) {
			throw new RuntimeException("数据已变更，请刷新页面或稍或重试！");
		}
		RechargeRecordVo updateRechargeRecordVo = rechargeRecordVoList.get(0);
		if (updateRechargeRecordVo.getStatus() != Constants.RECHARGE_STATUS_FIRST_APPROVE_SUCCESS) {
			throw new RuntimeException("充值记录状态非初审成功，请刷新页面或稍或重试！");
		}
		// 更新状态为充值成功
		updateRechargeRecordVo.setVerifyRemark2(rechargeRecordVo.getVerifyRemark2());
		updateRechargeRecordVo.setVerifyTime2(DateUtils.getTime());
		updateRechargeRecordVo.setVerifyUserid2(userVo.getId());
		Integer updateCount = modifyStatusToApprovePass(Constants.RECHARGE_STATUS_SUCCESS, updateRechargeRecordVo);
		if (null == updateCount || updateCount == 0) {
			throw new RuntimeException("数据已变更,请刷新页面或稍后重试！");
		}
		// 更新用户帐号的可用余额和总金额
		AccountVo accountVo = refreshAccountMoney(updateRechargeRecordVo);
		// 插入资金记录
		StringBuilder remarkbuilder = new StringBuilder("充值银行：".concat(rechargeRecordVo.getPayment().concat("，充值审核通过，资金已到账。")));
		accountLogService.saveAccountLogByParams(accountVo, accountVo.getUserId(), updateRechargeRecordVo.getMoney(), remarkbuilder.toString(), HttpTookit.getRealIpAddr(request), "offline_recharge",
				null, null, null);
		return result;
	}

	public String saveApproveFinallyReject(RechargeRecordVo rechargeRecordVo, UserVo userVo, HttpServletRequest request) throws Exception {
		String result = "success";
		// 验证审核数据是否正确
		List<RechargeRecordVo> rechargeRecordVoList = this.queryApproInfoVersionList(rechargeRecordVo);
		if (null == rechargeRecordVoList || rechargeRecordVoList.size() != 1) {
			throw new RuntimeException("数据已变更，请刷新页面或稍或重试！");
		}
		RechargeRecordVo updateRechargeRecordVo = rechargeRecordVoList.get(0);
		if (updateRechargeRecordVo.getStatus() != Constants.RECHARGE_STATUS_FIRST_APPROVE_SUCCESS) {
			throw new RuntimeException("充值记录状态非初审成功，请刷新页面或稍或重试！");
		}
		// 更新状态为充值失败
		updateRechargeRecordVo.setVerifyRemark2(rechargeRecordVo.getVerifyRemark2());
		updateRechargeRecordVo.setVerifyTime2(DateUtils.getTime());
		updateRechargeRecordVo.setVerifyUserid2(userVo.getId());
		Integer updateCount = modifyStatusToApprovePass(Constants.RECHARGE_STATUS_FAILURE, updateRechargeRecordVo);
		if (null == updateCount || updateCount == 0) {
			throw new RuntimeException("数据已变更,请刷新页面或稍后重试！");
		}
		AccountVo accountVo = accountService.queryAccountByUserId(updateRechargeRecordVo.getUserId());
		// 保存账户记录-线下充值失败
		StringBuilder remarkbuilder = new StringBuilder("充值审核未通过。【备注：".concat(rechargeRecordVo.getVerifyRemark()).concat("】"));
		accountLogService.saveAccountLogByParams(accountVo, accountVo.getUserId(), updateRechargeRecordVo.getMoney(), remarkbuilder.toString(), HttpTookit.getRealIpAddr(request), "offline_recharge",
				null, null, null);
		return result;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<RechargeRecordVo> queryRechargeRecordList(RechargeRecordCnd rechargeRecordCnd) throws Exception {
        if (rechargeRecordCnd.getIsCustody() != null && rechargeRecordCnd.getIsCustody() == -1) {
            rechargeRecordCnd.setIsCustody(null);
        }
		return rechargeRecordMapper.queryRechargeRecordList(rechargeRecordCnd);
	}

	/**
	 * <p>
	 * Description:更新用户帐号的可用余额和总金额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月23日
	 * @param rechargeRecordVo
	 * @return
	 * @throws Exception Account
	 */
	private AccountVo refreshAccountMoney(RechargeRecordVo rechargeRecordVo) throws Exception {
		AccountVo accountVo = accountService.queryAccountByUserIdForUpdate(rechargeRecordVo.getUserId());

		accountVo.setUseMoney(accountVo.getUseMoney().add(rechargeRecordVo.getMoney()));
		accountVo.setNoDrawMoney(accountVo.getNoDrawMoney().add(rechargeRecordVo.getMoney()));
		accountVo.setTotal(accountVo.getTotal().add(rechargeRecordVo.getMoney()));
		Account account = new Account();
		BeanUtils.copyProperties(accountVo, account);
		Integer updateCount = baseAccountMapper.updateEntity(account);
		if (null == updateCount || updateCount == 0) {
			// throw new AppException("帐号数据已变更,请刷新页面或稍后重试！");
		}
		return accountVo;
	}

	/**
	 * <p>
	 * Description:验证审核数据是否正确，并传递更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月6日
	 * @param rechargeRecordVo
	 * @return String
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	private List<RechargeRecordVo> queryApproInfoVersionList(RechargeRecordVo rechargeRecordVo) throws Exception {
		List<RechargeRecordVo> rechargeRecordVoList = null;
		// 查询数据
		RechargeRecordCnd rechargeRecordCnd = new RechargeRecordCnd();
		rechargeRecordCnd.setId(String.valueOf(rechargeRecordVo.getId()));
		rechargeRecordCnd.setVersion(String.valueOf(rechargeRecordVo.getVersion()));
		rechargeRecordVoList = rechargeRecordMapper.queryRechargeRecordList(rechargeRecordCnd);
		return rechargeRecordVoList;
	}

	/**
	 * <p>
	 * Description:更新状态<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月13日
	 * @param rechargeRecordVo
	 * @param updateRechargeRecordVo
	 * @return
	 * @throws Exception Integer
	 */
	private Integer modifyStatusToApprovePass(Integer status, RechargeRecordVo updateRechargeRecordVo) throws Exception {
		RechargeRecord updateRechargeRecord = new RechargeRecord();
		BeanUtils.copyProperties(updateRechargeRecordVo, updateRechargeRecord);
		updateRechargeRecord.setStatus(status);
		updateRechargeRecord.setSelfVersion(updateRechargeRecord.getVersion());
		updateRechargeRecord.setVersion(updateRechargeRecord.getVersion() + 1);
		Integer updateCount = baseRechargeRecordMapper.updateEntity(updateRechargeRecord);
		return updateCount;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryPageListByRechargeRecordCnd(RechargeRecordCnd rechargeRecordCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = rechargeRecordMapper.queryRechargeRecordCount(rechargeRecordCnd);
		page.setTotalCount(totalCount);
		// 分页
		if (curPage <= 1) {
			rechargeRecordCnd.set_offset(0);
		} else {
			rechargeRecordCnd.set_offset((curPage - 1) * pageSize);
		}
		rechargeRecordCnd.set_limit(pageSize);
		if (rechargeRecordCnd.getBeginTime() != null) {
			rechargeRecordCnd.setBeginTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(rechargeRecordCnd.getBeginTime(), DateUtils.YMD_DASH) + " 00:00:00"));
		}
		if (rechargeRecordCnd.getEndTime() != null) {
			rechargeRecordCnd.setEndTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(rechargeRecordCnd.getEndTime(), DateUtils.YMD_DASH) + " 23:59:59"));
		}
        if (rechargeRecordCnd.getIsCustody() != null && rechargeRecordCnd.getIsCustody() == -1) {
            rechargeRecordCnd.setIsCustody(null);
        }
		List<RechargeRecordVo> list = rechargeRecordMapper.queryRechargeRecordListForPages(rechargeRecordCnd);
		page.setResult(list);
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public BigDecimal queryRechargeTotalByCnd(RechargeRecordCnd rechargeRecordCnd) throws Exception {
		if (rechargeRecordCnd.getBeginTime() != null) {
			rechargeRecordCnd.setBeginTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(rechargeRecordCnd.getBeginTime(), DateUtils.YMD_DASH) + " 00:00:00"));
		}
		if (rechargeRecordCnd.getEndTime() != null) {
			rechargeRecordCnd.setEndTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(rechargeRecordCnd.getEndTime(), DateUtils.YMD_DASH) + " 23:59:59"));
		}
        if (rechargeRecordCnd.getIsCustody() != null && rechargeRecordCnd.getIsCustody() == -1) {
            rechargeRecordCnd.setIsCustody(null);
        }
		return rechargeRecordMapper.queryRechargeTotalByCnd(rechargeRecordCnd);
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public BigDecimal queryRealRechargeTotalByCnd(RechargeRecordCnd rechargeRecordCnd) throws Exception {
		BigDecimal result = new BigDecimal(0);
		if (rechargeRecordCnd.getBeginTime() != null) {
			rechargeRecordCnd.setBeginTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(rechargeRecordCnd.getBeginTime(), DateUtils.YMD_DASH) + " 00:00:00"));
		}
		if (rechargeRecordCnd.getEndTime() != null) {
			rechargeRecordCnd.setEndTimeStr(DateUtils.dateTime2TimeStamp(DateUtils.format(rechargeRecordCnd.getEndTime(), DateUtils.YMD_DASH) + " 23:59:59"));
		}
        if (rechargeRecordCnd.getIsCustody() != null && rechargeRecordCnd.getIsCustody() == -1) {
            rechargeRecordCnd.setIsCustody(null);
        }
		// 如果是未选择充值状态或选择是充值成功
		if ("".equals(rechargeRecordCnd.getStatus().trim()) || rechargeRecordCnd.getStatus().equals("1")) {
			result = rechargeRecordMapper.queryRealRechargeTotalByCnd(rechargeRecordCnd);
		}
		return result;

	}
}
