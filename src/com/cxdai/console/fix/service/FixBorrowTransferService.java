package com.cxdai.console.fix.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.BTenderRecord;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.entity.FixAccount;
import com.cxdai.console.fix.entity.FixBorrow;
import com.cxdai.console.fix.entity.FixBorrowTransfer;
import com.cxdai.console.fix.entity.FixOperationLog;
import com.cxdai.console.fix.entity.FixTenderSubscribe;
import com.cxdai.console.fix.entity.FixTenderTransfer;
import com.cxdai.console.fix.entity.FixUserCollectionrecord;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.mapper.FixAccountLogMapper;
import com.cxdai.console.fix.mapper.FixAccountMapper;
import com.cxdai.console.fix.mapper.FixBorrowMapper;
import com.cxdai.console.fix.mapper.FixBorrowTransferMapper;
import com.cxdai.console.fix.mapper.FixOperationLogMapper;
import com.cxdai.console.fix.mapper.FixTenderSubscribeMapper;
import com.cxdai.console.fix.mapper.FixTenderTransferMapper;
import com.cxdai.console.fix.mapper.FixTenderUserMapper;
import com.cxdai.console.fix.mapper.FixUserCollectionrecordMapper;
import com.cxdai.console.fix.vo.BCollectionRecordCnd;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.fix.vo.FixAccountLogVo;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowTransferCnd;
import com.cxdai.console.fix.vo.FixBorrowTransferVo;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixTenderSubscribeVo;
import com.cxdai.console.fix.vo.FixTenderTransferCnd;
import com.cxdai.console.fix.vo.FixTenderTransferVo;
import com.cxdai.console.fix.vo.FixTenderUserCnd;
import com.cxdai.console.fix.vo.FixTenderUserVo;
import com.cxdai.console.fix.vo.FixUserCollectionrecordCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.ShiroUtils;

/**
 * <p>
 * Description:手动转让BLL <br />
 * </p>
 * 
 * @title FixBorrowTransferService.java
 * @package com.cxdai.console.fix.service
 * @author HuangJun
 * @version 0.1 2015年6月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixBorrowTransferService {
	

	// 日志类取得
	Logger logger = LoggerFactory.getLogger(FixBorrowTransferService.class);

	@Autowired
	private FixBorrowTransferMapper fixBorrowTransferMapper;

	@Autowired
	private FixBorrowMapper fixBorrowMapper;

	@Autowired
	private BCollectionrecordMapper bCollectionrecordMapper;

	@Autowired
	private FixAccountMapper fixAccountMapper;

	@Autowired
	private FixTenderTransferMapper fixTenderTransferMapper;

	@Autowired
	private FixTenderSubscribeMapper fixTenderSubscribeMapper;

	@Autowired
	private FixAccountLogMapper fixAccountLogMapper;

	@Autowired
	private FixOperationLogMapper fixOperationLogMapper;

	@Autowired
	private TenderRecordMapper tenderRecordMapper;

	@Autowired
	private FixTenderUserMapper fixTenderUserMapper;

	@Autowired
	private FixUserCollectionrecordMapper fixUserCollectionrecordMapper;
	
	@Autowired
	private BorrowMapper borrowMapper;

	
	public Page getFixBorrowBySubsConn(FixBorrowTransferCnd fixBorrowTransferCnd, Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
		Integer totalCounts = fixBorrowTransferMapper.getFixBorrowCountBySubsConn(fixBorrowTransferCnd);
		page.setTotalCount(totalCounts);
		List<FixBorrowTransferVo> list = fixBorrowTransferMapper.getFixBorrowBySubsConn(fixBorrowTransferCnd, page);
		page.setResult(list);
		return page;
	}

	
	public String saveManualSubscribe(FixBorrowTransferVo fixBorrowTransferVo, String isFirstFlag, String ip) throws Exception {
		ShiroUser shiroUser=ShiroUtils.currentUser();
		// 转让价格
		BigDecimal fixTenderAccountReal = BigDecimal.ZERO;
		// 已经借到的金额
		BigDecimal fixTenderAccountYes = BigDecimal.ZERO;
		// 定期宝剩余认购金额
		BigDecimal fixBorrowSubscribeAccount = BigDecimal.ZERO;
		// 认购金额
		BigDecimal subscribeAccount = BigDecimal.ZERO;
		// 转让定期宝已经借到的金额
		BigDecimal transferAccountYes = fixBorrowTransferVo.getAccountYes();

		// 根据定期宝转让ID查询定期宝待收本金并锁定
		// 获取中待收本金总和
		BigDecimal collectionCapital = bCollectionrecordMapper.queryCollectionrecordSum(fixBorrowTransferVo.getFixBorrowId());
		// 查询转让方定期宝账户信息并锁定
		FixAccount transferAccount = fixAccountMapper.queryFixAccountByFixBorrowId(fixBorrowTransferVo.getFixBorrowId());
		// 判断定期宝待收本金 == 转让定期宝信息.债权金额 并且 定期宝账户.可用余额 == 转让定期宝信息.可用余额 并且转让定期宝信息.转让价格 = 转让定期宝信息.债权金额
		if (collectionCapital.compareTo(fixBorrowTransferVo.getAccount()) != 0 || collectionCapital.compareTo(fixBorrowTransferVo.getAccountReal()) != 0
				|| transferAccount.getUseMoney().compareTo(fixBorrowTransferVo.getUseMoney()) != 0) {
			logger.error("定期宝数据异常。定期宝ID为：" + fixBorrowTransferVo.getFixBorrowId());
			return "定期宝数据异常。定期宝ID为：" + fixBorrowTransferVo.getFixBorrowId();
		}
		
		// 判断定期宝状态是否在转让中
		if (fixBorrowTransferVo.getStatus().intValue() != 1) {
			logger.error("定期宝状态非转让中，无法转让。定期宝ID为：" + fixBorrowTransferVo.getFixBorrowId());
			return "定期宝状态非转让中，无法转让。定期宝ID为：" + fixBorrowTransferVo.getFixBorrowId();
		}

		// 查询认购定期宝信息
		FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
		fixBorrowCnd.setIsFirstFlag(isFirstFlag);
		// 判断是否是优先投标
		if ("0".equals(isFirstFlag)) {
			// 转让价格
			fixBorrowCnd.setAccountReal(fixBorrowTransferVo.getAccountReal());
			// 期限
			fixBorrowCnd.setLockLimit(fixBorrowTransferVo.getFixLockLimit());
		}
		List<FixBorrowVo> fixBorrowSubscribeList = fixBorrowMapper.querySubscribeFixBorrowList(fixBorrowCnd);
		if (fixBorrowSubscribeList != null && fixBorrowSubscribeList.size() > 0) {

			for (FixBorrowVo fixBorrowVo : fixBorrowSubscribeList) {
				// 认购定期宝信息.可用金额 < = 0 的情况
				if (fixBorrowVo.getFixAccountUserMoney().compareTo(BigDecimal.ZERO) < 0) {
					logger.error("定期宝认购失败，可用余额小于0，数据异常。定期宝ID为：" + fixBorrowVo.getId());
					return "定期宝认购失败，可用余额小于0，数据异常。定期宝ID为：" + fixBorrowVo.getId();
				}
				// 定期宝剩余认购金额
				fixBorrowSubscribeAccount = fixBorrowVo.getFixAccountUserMoney();

				// 根据定期宝转让ID查询定期宝投标转让表信息
				FixTenderTransferCnd fixTenderTransferCnd = new FixTenderTransferCnd();
				// 定期宝转让ID
				fixTenderTransferCnd.setFixBorrowTransferId(fixBorrowTransferVo.getId());
				List<FixTenderTransferVo> fixTenderList = fixTenderTransferMapper.queryFixTenderTransferList(fixTenderTransferCnd);

				// 循环定期宝标转让信息
				for (FixTenderTransferVo fixTenderTransferVo : fixTenderList) {
					// 转让价格
					fixTenderAccountReal = fixTenderTransferVo.getAccountReal();
					// 已经借到的金额
					if (fixTenderTransferVo.getAccountYes() != null) {
						fixTenderAccountYes = fixTenderTransferVo.getAccountYes();
					}
					// 定期宝投标转让信息.债权价格(ACCOUNT)-定期宝投标转让信息.已经借到的金额(ACCOUNT_YES) > 认购定期宝信息.可用金额 的情况
					if ((fixTenderAccountReal.subtract(fixTenderAccountYes)).compareTo(fixBorrowSubscribeAccount) > 0) {
						// 认购金额
						subscribeAccount = fixBorrowSubscribeAccount;
						// 定期宝剩余认购金额
						fixBorrowSubscribeAccount = BigDecimal.ZERO;
					} else {
						// 认购金额
						subscribeAccount = fixTenderAccountReal.subtract(fixTenderAccountYes);
						// 定期宝剩余认购金额
						fixBorrowSubscribeAccount = fixBorrowSubscribeAccount.subtract(fixTenderAccountReal.subtract(fixTenderAccountYes));
					}
					// 已经借到的金额
					fixTenderAccountYes = fixTenderAccountYes.add(subscribeAccount);
					// 转让定期宝已经借到的金额
					transferAccountYes = transferAccountYes.add(subscribeAccount);

					// 新增定期宝投标转让认购表
					insertFixTenderSubscribe(fixBorrowTransferVo, fixBorrowVo, fixTenderTransferVo, subscribeAccount, ip);

					// 查询定期宝账户信息表，并锁定
					FixAccount subscribeFixAccount = fixAccountMapper.queryFixAccountByFixBorrowId(fixBorrowVo.getId());

					// 更新认购方定期宝账户，总额减少，可用减少
					subscribeFixAccount.setTotal(subscribeFixAccount.getTotal().subtract(subscribeAccount));
					subscribeFixAccount.setUseMoney(subscribeFixAccount.getUseMoney().subtract(subscribeAccount));
					fixAccountMapper.updateFixAccount(subscribeFixAccount);

					// 添加定期宝操作日志表
					insetFixAccountLog(fixBorrowVo.getId(), 7, fixTenderTransferVo.getBorrowId(), fixTenderTransferVo.getBorrowName(), subscribeFixAccount, subscribeAccount, shiroUser, ip);

					// 更新定期宝投标转让表
					FixTenderTransfer fixTenderTransfer = new FixTenderTransfer();
					// 主键ID
					fixTenderTransfer.setId(fixTenderTransferVo.getId());
					// 已经借到的金额
					fixTenderTransfer.setAccountYes(fixTenderAccountYes);
					fixTenderTransfer.setLastUpdateUser(shiroUser.getUserId());
					fixTenderTransfer.setLastUpdateTime(new Date());
					fixTenderTransferMapper.updateByPrimaryKeySelective(fixTenderTransfer);

					// 转让价格 == 定期宝投标转让表.已经借到的金额(t_fix_tender_transfer.ACCOUNT_YES)的情况
					if (fixTenderAccountReal.compareTo(fixTenderAccountYes) == 0) {
						fixTenderTransfer = new FixTenderTransfer();
						// 主键ID
						fixTenderTransfer.setId(fixTenderTransferVo.getId());
						// 状态
						fixTenderTransfer.setStatus(2);
						fixTenderTransfer.setLastUpdateUser(shiroUser.getUserId());
						fixTenderTransfer.setLastUpdateTime(new Date());
						fixTenderTransferMapper.updateByPrimaryKeySelective(fixTenderTransfer);
					}

					// 更新定期宝转让信息中已借到的金额
					FixBorrowTransfer fixBorrowTransfer = new FixBorrowTransfer();
					// 主键ID
					fixBorrowTransfer.setId(fixBorrowTransferVo.getId());
					// 已经借到的金额
					fixBorrowTransfer.setAccountYes(transferAccountYes);
					fixBorrowTransfer.setLastUpdateTime(new Date());
					fixBorrowTransfer.setLastUpdateUser(shiroUser.getUserId());
					fixBorrowTransferMapper.updateByPrimaryKeySelective(fixBorrowTransfer);

					// 定期宝投标转让表.债权价格(t_fix_tender_transfer.ACCOUNT) == 定期宝投标转让表.已经借到的金额(t_fix_tender_transfer.ACCOUNT_YES)的情况
					if (fixBorrowTransferVo.getAccountReal().compareTo(transferAccountYes) == 0) {
						// 更新定期宝转让信息中已借到的状态
						fixBorrowTransfer = new FixBorrowTransfer();
						// 主键ID
						fixBorrowTransfer.setId(fixBorrowTransferVo.getId());
						// 已经借到的金额
						fixBorrowTransfer.setStatus(2);
						fixBorrowTransfer.setLastUpdateTime(new Date());
						fixBorrowTransfer.setLastUpdateUser(shiroUser.getUserId());
						fixBorrowTransferMapper.updateByPrimaryKeySelective(fixBorrowTransfer);

						// 保存定期宝操作日志
						addFixOperationLog(fixBorrowTransferVo.getFixBorrowId(), 9, ip,shiroUser);
						return "success";
					} else {
						// 剩余认购金额==0的情况
						if (fixBorrowSubscribeAccount.compareTo(BigDecimal.ZERO) == 0) {
							break;
						}
					}
				}
			}
		}
		return "success";
	}

	
	public String saveApproveTransferRecheck(Integer fixBorrowTransferId, String ip) throws Exception {
		ShiroUser staff=ShiroUtils.currentUser();
		// 定期宝标认购信息
		FixTenderSubscribe fixTenderSubscribe = null;

		// 认购比率
		BigDecimal subscribeRatio = BigDecimal.ZERO;

		// 根据ID查询定期宝转让信息
		FixBorrowTransferVo fixBorrowTransferVo = fixBorrowTransferMapper.queryFixBorrowTransferById(fixBorrowTransferId);
		// 根据定期宝转让ID查询定期宝转让认购总和
		BigDecimal subscribeSum = fixTenderSubscribeMapper.queryTenderSubscribeSum(fixBorrowTransferId);
		// 定期宝转让信息.状态 != 2（转让复审中）or 定期宝转让信息.转让价格!= 定期宝转让信息.已经借到的金额 or 认购价格合计 != 定期宝转让信息.转让价格的情况
		if (fixBorrowTransferVo.getStatus() != 2 || fixBorrowTransferVo.getAccountReal().compareTo(fixBorrowTransferVo.getAccountYes()) != 0
				|| subscribeSum.compareTo(fixBorrowTransferVo.getAccountReal()) != 0) {
			logger.error("定期宝自动复审失败。定期宝转让ID为：" + fixBorrowTransferId);
			return "定期宝自动复审失败。定期宝转让ID为：" + fixBorrowTransferId;
		}
		// 根据定期宝转让ID查询定期宝投标转让认购信息,并锁定
		FixTenderTransferCnd fixTenderTransferCnd = new FixTenderTransferCnd();
		fixTenderTransferCnd.setFixBorrowTransferId(fixBorrowTransferId);
		fixTenderTransferCnd.setStatus(1);
		List<FixTenderSubscribeVo> subscribeList = fixTenderSubscribeMapper.queryTenderSubscribeListByTransferId(fixTenderTransferCnd);

		// 更新定期宝转让信息表（t_fix_borrow_transfer）
		FixBorrowTransfer fixBorrowTransfer = new FixBorrowTransfer();
		// 主键ID
		fixBorrowTransfer.setId(fixBorrowTransferVo.getId());
		// 状态
		fixBorrowTransfer.setStatus(3);
		// 转让成功时间
		fixBorrowTransfer.setSuccessTime(new Date());
		fixBorrowTransfer.setLastUpdateTime(new Date());
		fixBorrowTransfer.setLastUpdateUser(staff.getUserId());
		fixBorrowTransferMapper.updateByPrimaryKeySelective(fixBorrowTransfer);
		// 定期宝投标转让表（t_fix_tender_transfer）
		FixTenderTransfer fixTenderTransfer = new FixTenderTransfer();
		fixTenderTransfer.setFixBorrowTransferId(fixBorrowTransferId);
		fixTenderTransfer.setLastUpdateUser(staff.getUserId());
		fixTenderTransfer.setStatus(3);
		fixTenderTransfer.setRemark("定期宝转让复审");
		fixTenderTransferMapper.updateByTransferId(fixTenderTransfer);

		// 更新定期宝投标转让认购表信息
		fixTenderSubscribeMapper.updateTenderSubscribeStatus(fixBorrowTransferId);

		// 更新定期宝信息表的状态
		FixBorrow fixBorrow = new FixBorrow();
		// 定期宝ID
		fixBorrow.setId(fixBorrowTransferVo.getFixBorrowId());
		// 状态
		fixBorrow.setStatus(10);
		fixBorrow.setLastModifyUser(-1);
		fixBorrowMapper.updateFixBorrowStatusById(fixBorrow);

		// 保存定期宝操作日志
		addFixOperationLog(fixBorrowTransferVo.getFixBorrowId(), 10, ip, staff);

		// 查询转让方定期宝账户信息并锁定
		FixAccount transferAccount = fixAccountMapper.queryFixAccountByFixBorrowId(fixBorrowTransferVo.getFixBorrowId());
		// 当前行
		int index = 0;
		BigDecimal tenderRepaymentAccountSum = BigDecimal.ZERO;

		for (FixTenderSubscribeVo fixTenderSubscribeVo : subscribeList) {
			// 根据投标ID查询投标记录
			BTenderRecord tenderrecord = tenderRecordMapper.selectByPrimaryKey(fixTenderSubscribeVo.getTenderId());
			// 认购比率
			subscribeRatio = fixTenderSubscribeVo.getAccount().divide(fixTenderSubscribeVo.getAccountReal(), 8, BigDecimal.ROUND_DOWN);
			// 更新标志
			boolean updateFlag = getUpdateFlag(subscribeList, index);

			BigDecimal tenderRepaymentAccount = tenderrecord.getRepaymentAccount().multiply(subscribeRatio).setScale(2, BigDecimal.ROUND_UP);

			// 更新标志==true的情况
			if (updateFlag) {
				// 新增认购方投标记录
				// 认购定期宝ID
				tenderrecord.setUserId(fixTenderSubscribeVo.getNewFixBorrowId());
				// 认购金额
				tenderrecord.setAccount(fixTenderSubscribeVo.getAccount());
				// 待收金额
				BigDecimal repaymentAccount = tenderrecord.getRepaymentAccount().subtract(tenderRepaymentAccountSum);
				tenderrecord.setRepaymentAccount(repaymentAccount);
				// 利息
				tenderrecord.setInterest(repaymentAccount.subtract(fixTenderSubscribeVo.getAccount()));
				// 可提金额
				tenderrecord.setDrawMoney(fixTenderSubscribeVo.getAccount());
				// 父ID
				tenderrecord.setParentId(fixTenderSubscribeVo.getTenderId());
				tenderRecordMapper.insert(tenderrecord);

				int newTenderId = tenderrecord.getId();

				// 根据投标ID查询待收记录
				BCollectionRecordCnd bCollectionRecordCnd = new BCollectionRecordCnd();
				// 投标ID
				bCollectionRecordCnd.setTenderId(fixTenderSubscribeVo.getTenderId());
				// 根据投标ID查询待收记录
				List<BCollectionRecordVo> collectionList = bCollectionrecordMapper.queryCollectionrecordListByTenderId(bCollectionRecordCnd);
				for (BCollectionRecordVo bCollectionRecordVo : collectionList) {
					// 计算当期的待收总和
					bCollectionRecordCnd = new BCollectionRecordCnd();
					bCollectionRecordCnd.setTenderId(fixTenderSubscribeVo.getTenderId());
					bCollectionRecordCnd.setOrder(bCollectionRecordVo.getOrder());
					BCollectionRecordVo recordSumVo = bCollectionrecordMapper.queryRepayAccountAndCapitalSum(bCollectionRecordCnd);

					// 投标记录
					bCollectionRecordVo.setTenderId(newTenderId);
					// 预还金额
					BigDecimal repayAccountCol = bCollectionRecordVo.getRepayAccount().subtract(recordSumVo.getRepayAccountSum());
					BigDecimal capitalCol = bCollectionRecordVo.getCapital().subtract(recordSumVo.getCapitalSum());
					bCollectionRecordVo.setRepayAccount(repayAccountCol);
					// 利息
					bCollectionRecordVo.setInterest(repayAccountCol.subtract(capitalCol));
					// 本金
					bCollectionRecordVo.setCapital(capitalCol);
					// IP
					bCollectionRecordVo.setAddip(ip);
					// 用户ID
					bCollectionRecordVo.setUserId(fixTenderSubscribeVo.getNewFixBorrowId());
					bCollectionrecordMapper.insert(bCollectionRecordVo);
				}
				tenderRepaymentAccountSum = BigDecimal.ZERO;

				// 修改待收记录状态
				bCollectionrecordMapper.updateStatusById(fixTenderSubscribeVo.getTenderId());

				// 修改投标记录状态
				BTenderRecord record = new BTenderRecord();
				// 投标ID
				record.setId(fixTenderSubscribeVo.getTenderId());
				// 状态
				record.setStatus(-4);
				tenderRecordMapper.updateByPrimaryKeySelective(record);

				// 更新定期宝投标转让认购表
				fixTenderSubscribe = new FixTenderSubscribe();
				// 主键ID
				fixTenderSubscribe.setId(fixTenderSubscribeVo.getId());
				// 投标ID
				fixTenderSubscribe.setTenderId(newTenderId);
				fixTenderSubscribeMapper.updateByPrimaryKeySelective(fixTenderSubscribe);

			} else {
				// 新增认购方投标记录
				// 认购定期宝ID
				tenderrecord.setUserId(fixTenderSubscribeVo.getNewFixBorrowId());
				// 认购金额
				tenderrecord.setAccount(fixTenderSubscribeVo.getAccount());
				// 待收金额
				tenderrecord.setRepaymentAccount(tenderRepaymentAccount);
				// 利息
				tenderrecord.setInterest(tenderRepaymentAccount.subtract(fixTenderSubscribeVo.getAccount()));
				// 可提金额
				tenderrecord.setDrawMoney(fixTenderSubscribeVo.getAccount());
				// 父ID
				tenderrecord.setParentId(fixTenderSubscribeVo.getTenderId());
				tenderRecordMapper.insert(tenderrecord);
				int newTenderId = tenderrecord.getId();

				// 更新定期宝投标转让认购表
				fixTenderSubscribe = new FixTenderSubscribe();
				// 主键ID
				fixTenderSubscribe.setId(fixTenderSubscribeVo.getId());
				// 投标ID
				fixTenderSubscribe.setTenderId(newTenderId);
				fixTenderSubscribeMapper.updateByPrimaryKeySelective(fixTenderSubscribe);

				// 根据投标ID查询待收记录
				BCollectionRecordCnd bCollectionRecordCnd = new BCollectionRecordCnd();
				// 投标ID
				bCollectionRecordCnd.setTenderId(fixTenderSubscribeVo.getTenderId());
				// 根据投标ID查询待收记录
				List<BCollectionRecordVo> collectionList = bCollectionrecordMapper.queryCollectionrecordListByTenderId(bCollectionRecordCnd);
				// 待收当前行
				int colindex = 0;
				// 待收
				BigDecimal repayAccountSumTemp = BigDecimal.ZERO;
				// 本金
				BigDecimal capitalSumTemp = BigDecimal.ZERO;
				for (BCollectionRecordVo bCollectionRecordVo : collectionList) {
					// 待收
					BigDecimal repayAccount = bCollectionRecordVo.getRepayAccount().multiply(subscribeRatio).setScale(2, BigDecimal.ROUND_UP);
					// 本金
					BigDecimal capital = bCollectionRecordVo.getCapital().multiply(subscribeRatio).setScale(2, BigDecimal.ROUND_UP);

					if (colindex == collectionList.size() - 1) {
						// 投标记录
						bCollectionRecordVo.setTenderId(newTenderId);
						// 预还金额
						bCollectionRecordVo.setRepayAccount(tenderRepaymentAccount.subtract(repayAccountSumTemp));
						// 利息
						bCollectionRecordVo.setInterest((tenderRepaymentAccount.subtract(repayAccountSumTemp)).subtract((fixTenderSubscribeVo.getAccount().subtract(capitalSumTemp))));
						// 本金
						bCollectionRecordVo.setCapital(fixTenderSubscribeVo.getAccount().subtract(capitalSumTemp));
						// IP
						bCollectionRecordVo.setAddip(ip);
						// 用户ID
						bCollectionRecordVo.setUserId(fixTenderSubscribeVo.getNewFixBorrowId());
						bCollectionrecordMapper.insert(bCollectionRecordVo);
					} else {
						// 投标记录
						bCollectionRecordVo.setTenderId(newTenderId);
						// 预还金额
						bCollectionRecordVo.setRepayAccount(repayAccount);
						// 利息
						bCollectionRecordVo.setInterest(repayAccount.subtract(capital));
						// 本金
						bCollectionRecordVo.setCapital(capital);
						// IP
						bCollectionRecordVo.setAddip(ip);
						// 用户ID
						bCollectionRecordVo.setUserId(fixTenderSubscribeVo.getNewFixBorrowId());
						bCollectionrecordMapper.insert(bCollectionRecordVo);
						repayAccountSumTemp = repayAccountSumTemp.add(repayAccount);
						capitalSumTemp = capitalSumTemp.add(capital);
					}
					colindex++;
				}
				tenderRepaymentAccountSum = tenderRepaymentAccountSum.add(tenderRepaymentAccount);
			}
			index++;
		}

		// 更新转让定期宝账户信息
		// 总额
		transferAccount.setTotal(transferAccount.getTotal().add(fixBorrowTransferVo.getAccountReal()));
		// 可用余额
		transferAccount.setUseMoney(transferAccount.getUseMoney().add(fixBorrowTransferVo.getAccountReal()));
		fixAccountMapper.updateFixAccount(transferAccount);

		// 新增转让方定期宝账户日志信息
		insetFixAccountLog(fixBorrowTransferVo.getFixBorrowId(), 9, fixBorrowTransferVo.getFixBorrowId(), fixBorrowTransferVo.getFixBorrowName(), transferAccount,
				fixBorrowTransferVo.getAccountReal(), staff, ip);

		// 更新转让定期宝账户信息。待收减少，总额减少
		// 计算待收总额
		BigDecimal repaymentAccountSum = fixTenderTransferMapper.queryRepaymentAccountSumByTransferId(fixBorrowTransferId);
		// 总额
		transferAccount.setTotal(transferAccount.getTotal().subtract(repaymentAccountSum));
		// 待收总额
		transferAccount.setCollection(transferAccount.getCollection().subtract(repaymentAccountSum));
		fixAccountMapper.updateFixAccount(transferAccount);

		// 新增转让方定期宝账户日志信息
		insetFixAccountLog(fixBorrowTransferVo.getFixBorrowId(), 10, fixBorrowTransferVo.getFixBorrowId(), fixBorrowTransferVo.getFixBorrowName(), transferAccount, repaymentAccountSum, staff, ip);

		// 查询认购方信息
		fixTenderTransferCnd = new FixTenderTransferCnd();
		fixTenderTransferCnd.setFixBorrowTransferId(fixBorrowTransferId);
		fixTenderTransferCnd.setStatus(3);
		subscribeList = fixTenderSubscribeMapper.queryTenderSubscribeListByTransferId(fixTenderTransferCnd);
		BCollectionRecordCnd collectionRecordCnd = null;
		for (FixTenderSubscribeVo fixTenderSubscribeVo : subscribeList) {
			// 查询认购方定期宝账户信息并锁定，总额增加，待收增加
			FixAccount subscribeAccount = fixAccountMapper.queryFixAccountByFixBorrowId(fixTenderSubscribeVo.getNewFixBorrowId());
			collectionRecordCnd = new BCollectionRecordCnd();
			collectionRecordCnd.setFixBorrowId(fixTenderSubscribeVo.getNewFixBorrowId());
			collectionRecordCnd.setTenderId(fixTenderSubscribeVo.getTenderId());
			BigDecimal repayAccountSum = bCollectionrecordMapper.queryRepayAccountSum(collectionRecordCnd);
			// 总额
			subscribeAccount.setTotal(subscribeAccount.getTotal().add(repayAccountSum));
			// 待收
			subscribeAccount.setCollection(subscribeAccount.getCollection().add(repayAccountSum));
			fixAccountMapper.updateFixAccount(subscribeAccount);

			// 新增转让方定期宝账户日志信息
			insetFixAccountLog(fixTenderSubscribeVo.getNewFixBorrowId(), 11, fixTenderSubscribeVo.getOldFixBorrowId(), fixTenderSubscribeVo.getSubscribeBorrowName(), subscribeAccount,
					repayAccountSum, staff, ip);
		}

		// 查询新定期宝认购信息
		List<FixTenderSubscribeVo> groupSubscribeList = fixTenderSubscribeMapper.queryTenderSubscribeListGroupByTransferId(fixBorrowTransferId);
		for (FixTenderSubscribeVo fixTenderSubscribeVo : groupSubscribeList) {
			// 调用定期宝中间表存储过程
			Map<String, Object> params = new HashMap<String, Object>();
			// 认购定期宝ID
			params.put("fixBorrowId", fixTenderSubscribeVo.getNewFixBorrowId());
			// 认购金额合计
			params.put("accountSum", fixTenderSubscribeVo.getAccountSum());
			// 借款标ID
			params.put("borrowid", fixTenderSubscribeVo.getBorrowId());
			// 投标ID
			params.put("tenderId", fixTenderSubscribeVo.getTenderId());
			fixTenderSubscribeMapper.saveFixTenderCore(params);
			// 存储过程返回参数
			String msg = params.get("msg").toString();
			if (!"0001".equals(msg)) {
				throw new Exception("定期宝中间表存储过程失败");
			}

			fixTenderSubscribeMapper.updateFixTenderUserStatus(fixTenderSubscribeVo.getOldFixBorrowId());

			// // 调用定期宝中间表存储过程
			// params = new HashMap<String, Object>();
			// // 借款标ID
			// params.put("borrowId", fixTenderSubscribeVo.getBorrowId());
			// // 投标ID
			// params.put("tenderId", fixTenderSubscribeVo.getTenderId());
			// // 借款期限
			// params.put("timelimit", fixTenderSubscribeVo.getTimeLimit());
			// // 认购定期宝ID
			// params.put("fixBorrowId", fixTenderSubscribeVo.getNewFixBorrowId());
			// // 认购定期宝ID
			// params.put("borrowStyle", fixTenderSubscribeVo.getStyle());
			//
			// // 认购金额合计
			// params.put("borrowApr", fixTenderSubscribeVo.getApr());
			// fixTenderSubscribeMapper.saveFixUserTendercollectionCore(params);
			// // 存储过程返回参数
			// msg = params.get("msg").toString();
			// if (!"0001".equals(msg)) {
			// throw new Exception("定期宝投资人投标待收记录存储过程失败");
			// }
			saveFixUserCollectionrecord(fixTenderSubscribeVo);
		}

		return "success";
	}

	private void saveFixUserCollectionrecord(FixTenderSubscribeVo fixTenderSubscribeVo) throws Exception {
		BigDecimal ratio = BigDecimal.ZERO;
		FixTenderUserCnd fixTenderUserCnd = null;
		BCollectionRecordCnd bCollectionRecordCnd = new BCollectionRecordCnd();
		bCollectionRecordCnd.setTenderId(fixTenderSubscribeVo.getTenderId());
		List<BCollectionRecordVo> list = bCollectionrecordMapper.queryCollectionrecordListByTenderId(bCollectionRecordCnd);
		int index = 0;
		for (BCollectionRecordVo bCollectionRecordVo : list) {
			if (BigDecimal.ZERO.compareTo(bCollectionRecordVo.getCapital()) == 0) {
				index++;
				continue;
			}
			BigDecimal capitalSum = BigDecimal.ZERO;
			FixUserCollectionrecordCnd fixUserCollectionrecordCnd = null;
			int tenderUserIndex = 0;
			ratio = bCollectionRecordVo.getCapital().divide(fixTenderSubscribeVo.getAccountSum(), 8, BigDecimal.ROUND_DOWN);
			fixTenderUserCnd = new FixTenderUserCnd();
			fixTenderUserCnd.setFixBorrowId(fixTenderSubscribeVo.getNewFixBorrowId());
			fixTenderUserCnd.setBorrowId(fixTenderSubscribeVo.getBorrowId());
			fixTenderUserCnd.setTenderId(fixTenderSubscribeVo.getTenderId());
			List<FixTenderUserVo> tenderUserList = fixTenderUserMapper.queryFixTenerUserByCnd(fixTenderUserCnd);
			for (FixTenderUserVo fixTenderUserVo : tenderUserList) {
				BigDecimal capital = BigDecimal.ZERO;
				FixUserCollectionrecord record = new FixUserCollectionrecord();
				record.setBorrowId(fixTenderUserVo.getBorrowId());
				record.setFixBorrowId(fixTenderUserVo.getFixBorrowId());
				record.setOrder(bCollectionRecordVo.getOrder());
				record.setTenderId(fixTenderUserVo.getTenderId());
				record.setUserId(fixTenderUserVo.getUserId());
				if (index == list.size() - 1) {
					fixUserCollectionrecordCnd = new FixUserCollectionrecordCnd();
					fixUserCollectionrecordCnd.setBorrowId(fixTenderUserVo.getBorrowId());
					fixUserCollectionrecordCnd.setFixBorrowId(fixTenderUserVo.getFixBorrowId());
					fixUserCollectionrecordCnd.setTenderId(fixTenderUserVo.getTenderId());
					fixUserCollectionrecordCnd.setUserId(fixTenderUserVo.getUserId());
					BigDecimal userCapitalSum = fixUserCollectionrecordMapper.queryCapitalSum(fixUserCollectionrecordCnd);
					capital = fixTenderUserVo.getAccount().subtract(userCapitalSum);
				} else {
					capital = fixTenderUserVo.getAccount().multiply(ratio).setScale(2, BigDecimal.ROUND_UP);
				}
				if (tenderUserIndex == tenderUserList.size() - 1) {
					record.setCapital(bCollectionRecordVo.getCapital().subtract(capitalSum));
				} else {
					record.setCapital(capital);
					capitalSum = capitalSum.add(capital);
				}
				fixUserCollectionrecordMapper.insert(record);
				tenderUserIndex++;
			}
			index++;
		}
	}

	/**
	 * 
	 * <p>
	 * Description:添加定期宝投标转让认购表<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月29日
	 * @param fixBorrowTransferVo
	 * @param fixBorrowVo
	 * @param fixTenderTransferVo
	 * @param subscribeAccount
	 *            void
	 */
	private void insertFixTenderSubscribe(FixBorrowTransferVo fixBorrowTransferVo, FixBorrowVo fixBorrowVo, FixTenderTransferVo fixTenderTransferVo, BigDecimal subscribeAccount, String ip) {
		// 新增定期宝投标转让认购表
		FixTenderSubscribe fixTenderSubscribe = new FixTenderSubscribe();
		// 定期宝转让ID
		fixTenderSubscribe.setFixBorrowTransferId(fixBorrowTransferVo.getId());
		// 定期宝投标转让ID
		fixTenderSubscribe.setFixTenderTransferId(fixTenderTransferVo.getId());
		// 转让定期宝ID
		fixTenderSubscribe.setOldFixBorrowId(fixBorrowTransferVo.getFixBorrowId());
		// 认购定期宝ID
		fixTenderSubscribe.setNewFixBorrowId(fixBorrowVo.getId());
		// 投标ID
		fixTenderSubscribe.setTenderId(fixTenderTransferVo.getTenderId());
		// 借款标ID
		fixTenderSubscribe.setBorrowId(fixTenderTransferVo.getBorrowId());
		// 认购金额
		fixTenderSubscribe.setAccount(subscribeAccount);
		// 状态(1认购中；2认购失败；3认购成功)
		fixTenderSubscribe.setStatus(1);
		// 转让定期名称
		fixTenderSubscribe.setTransferBorrowName(fixBorrowTransferVo.getFixBorrowName());
		// 转让定期宝合同编号
		fixTenderSubscribe.setTransferContractNo(fixBorrowTransferVo.getFixContractNo());
		// 认购定期名称
		fixTenderSubscribe.setSubscribeBorrowName(fixBorrowVo.getName());
		// 认购定期宝合同编号
		fixTenderSubscribe.setSubscribeContractNo(fixBorrowVo.getContractNo());
		// 添加人
		fixTenderSubscribe.setAdduser(-1);
		// 添加IP
		fixTenderSubscribe.setAddIp(ip);
		// 平台来源
		fixTenderSubscribe.setPlatform(1);
		// 备注
		fixTenderSubscribe.setRemark("手动认购定期宝成功");
		fixTenderSubscribeMapper.insert(fixTenderSubscribe);
	}

	/**
	 * 
	 * <p>
	 * Description:添加定期宝操作日志表<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月29日
	 * @param fixBorrowVo
	 * @param fixTenderTransferVo
	 * @param subscribeFixAccount
	 * @param subscribeAccount
	 *            void
	 */
	private void insetFixAccountLog(Integer fixBorrowId, Integer type, Integer borrowId, String borrowName, FixAccount fixAccount, BigDecimal subscribeAccount, ShiroUser shiroUser, String ip)
			throws Exception {
		// 新增认购方定期宝账户日志信息
		FixAccountLogVo fixAccountLog = new FixAccountLogVo();
		// 定期宝ID
		fixAccountLog.setFixBorrowId(fixBorrowId);
		// 操作类型
		fixAccountLog.setType(type);
		// 借款标ID
		fixAccountLog.setBorrowId(borrowId);
		// 借款标名称
		fixAccountLog.setBorrowName(borrowName);
		// 业务类型
		fixAccountLog.setIdType(2);
		// 操作金额
		fixAccountLog.setMoney(subscribeAccount);
		// 总额
		fixAccountLog.setTotal(fixAccount.getTotal());
		// 可用余额
		fixAccountLog.setUseMoney(fixAccount.getUseMoney());
		// 冻结金额
		fixAccountLog.setNoUseMoney(fixAccount.getNoUseMoney());
		// 待收总额
		fixAccountLog.setCollection(fixAccount.getCollection());
		// 待收总额
		fixAccountLog.setCapital(fixAccount.getCapital());
		// 实际收益
		fixAccountLog.setProfit(fixAccount.getProfit());
		// 操作人
		fixAccountLog.setAddUser(shiroUser.getUserId());
		// 操作IP
		fixAccountLog.setAddIp(ip);
		// 备注
		if (type == 7) {
			fixAccountLog.setRemark("自动认购定期宝成功");
		} else if (type == 9) {
			fixAccountLog.setRemark("定期宝自动复审成功，定期宝本金回款，总额增加");
		} else if (type == 10) {
			fixAccountLog.setRemark("定期宝自动复审成功，定期宝待收减少");
		} else if (type == 11) {
			fixAccountLog.setRemark("定期宝自动复审成功，认购定期宝总额增加");
		} else if (type == 12) {
			fixAccountLog.setRemark("定期宝取消转让，认购定期宝总额增加");
		}
		fixAccountLogMapper.insertFixAccountLog(fixAccountLog);
	}

	/**
	 * 
	 * <p>
	 * Description:添加定期宝操作日志表<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月3日
	 * @param fixBorrowId
	 * @param operType
	 *            void
	 */
	private void addFixOperationLog(Integer fixBorrowId, Integer operType, String ip,ShiroUser staff) {
		// 保存定期宝操作日志
		FixOperationLog fixOperationLog = new FixOperationLog();
		// 操作人ID
		fixOperationLog.setUserId(staff.getUserId());
		// 用户类型
		fixOperationLog.setUserType(-1);
		// 定期宝ID
		fixOperationLog.setFixBorrowId(fixBorrowId);
		// 操作类型
		fixOperationLog.setOperType(operType);
		// 备注
		if (operType == 8) {
			fixOperationLog.setRemark("定期宝发起转让");
		} else if (operType == 9) {
			fixOperationLog.setRemark("定期宝转让复审中");
		} else if (operType == 10) {
			fixOperationLog.setRemark("定期宝转让成功");
		} else if (operType == 11) {
			fixOperationLog.setRemark("定期宝取消转让");
		}
		// IP
		fixOperationLog.setAddip(ip);
		// 平台来源
		fixOperationLog.setPlatform(1);
		fixOperationLogMapper.insertFixOperationLog(fixOperationLog);
	}

	/**
	 * 
	 * <p>
	 * Description:判断更新标志：true更新;false不更新<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月1日
	 * @param subscribeList
	 * @param index
	 * @return boolean
	 */
	private boolean getUpdateFlag(List<FixTenderSubscribeVo> subscribeList, int index) {
		// 只有一条数据的情况
		if (subscribeList.size() == 1) {
			return true;
		} else {
			// 最后一条数据的情况
			if (subscribeList.size() - 1 == index) {
				return true;
			} else {
				// 判断当前的投标ID是否等于前一条几率的投标ID，如果相等则不更新，不相等，则更新
				if (subscribeList.get(index).getTenderId().intValue() == subscribeList.get(index + 1).getTenderId().intValue()) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	
	public FixBorrowTransferVo queryTransferingFixBorrowList(Integer id) {
		FixBorrowTransferCnd FixBorrowTransferCnd = new FixBorrowTransferCnd();
		FixBorrowTransferCnd.setId(id);
		List<FixBorrowTransferVo> list = fixBorrowTransferMapper.queryTransferingFixBorrowList(FixBorrowTransferCnd);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 定期宝取消转让重构
	 * @author WangQianJin
	 * @title @param fixBorrowTransferId
	 * @title @param ip
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年9月14日
	 */
	public String saveTransferCancel(Integer fixBorrowTransferId, String ip) throws Exception {
		/*1、修改定期宝投标转让状态为取消转让*/
		ShiroUser staff=ShiroUtils.currentUser();
		FixTenderTransfer record = new FixTenderTransfer();
		record.setStatus(5);
		record.setFixBorrowTransferId(fixBorrowTransferId);
		record.setLastUpdateUser(staff.getUserId());
		record.setRemark("定期宝取消转让");
		fixTenderTransferMapper.updateByTransferId(record);
		/*2、修改定期宝转让状态为取消转让*/		
		FixBorrowTransfer fixBorrowTransfer = new FixBorrowTransfer();
		fixBorrowTransfer.setId(fixBorrowTransferId);
		fixBorrowTransfer.setStatus(5);
		fixBorrowTransfer.setLastUpdateTime(new Date());
		fixBorrowTransfer.setLastUpdateUser(staff.getUserId());
		fixBorrowTransfer.setRemark("定期宝取消转让");
		fixBorrowTransferMapper.updateByPrimaryKeySelective(fixBorrowTransfer);
		//获取定期宝转让信息
		FixBorrowTransferVo fixBorrowTransferVo = fixBorrowTransferMapper.queryFixBorrowTransferById(fixBorrowTransferId);
		/*3、修改定期宝状态为取消转让*/		
		FixBorrow fixBorrow = new FixBorrow();
		fixBorrow.setId(fixBorrowTransferVo.getFixBorrowId());
		fixBorrowMapper.queryFixBorrowByIdForUpdate(fixBorrow);
		fixBorrow.setLastModifyTime(new Date());
		fixBorrow.setLastModifyUser(staff.getUserId());
		fixBorrow.setStatus(5);
		fixBorrowMapper.updateFixBorrowStatusById(fixBorrow);
		/*4、添加定期宝操作日志*/	
		addFixOperationLog(fixBorrowTransferVo.getFixBorrowId(), 11, ip, staff);
		//根据转让ID获取认购明细
		FixTenderTransferCnd fixTenderTransferCnd=new FixTenderTransferCnd();
		fixTenderTransferCnd.setFixBorrowTransferId(fixBorrowTransferId);
		fixTenderTransferCnd.setStatus(1);  //认购中
		List<FixTenderSubscribeVo> subscribeList = fixTenderSubscribeMapper.queryTenderSubscribeListByTransferId(fixTenderTransferCnd);
		if(subscribeList!=null && subscribeList.size()>0){
			for(FixTenderSubscribeVo subscribe:subscribeList){
				/*5、根据认购ID修改状态为认购失败*/	
				FixTenderSubscribeVo fixTenderSubscribeVo=new FixTenderSubscribeVo();
				fixTenderSubscribeVo.setId(subscribe.getId());
				fixTenderSubscribeVo.setRemark("定期宝取消转让,认购失败");
				fixTenderSubscribeMapper.updateStatusForSubFailureById(fixTenderSubscribeVo);
				/*6、更新认购定期宝账户总额与可用*/				
				FixAccount subscribeFixAccount = fixAccountMapper.queryFixAccountByFixBorrowId(subscribe.getNewFixBorrowId());
				//总额增加，可用增加
				subscribeFixAccount.setTotal(subscribeFixAccount.getTotal().add(subscribe.getAccount()));
				subscribeFixAccount.setUseMoney(subscribeFixAccount.getUseMoney().add(subscribe.getAccount()));
				fixAccountMapper.updateFixAccount(subscribeFixAccount);
				/*7、添加定期宝账户日志*/	
				String borrowName="";
				BorrowVo borrowVo=borrowMapper.selectByPrimaryKey(subscribe.getBorrowId());
				if(borrowVo!=null){
					borrowName=borrowVo.getName();
				}
				insetFixAccountLog(subscribe.getNewFixBorrowId(), 12, subscribe.getBorrowId(), borrowName, subscribeFixAccount, subscribe.getAccount(), ShiroUtils.currentUser(), ip);
			}
		}
		
		return "success";
	}
}
