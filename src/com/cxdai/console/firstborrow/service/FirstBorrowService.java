package com.cxdai.console.firstborrow.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.base.entity.FirstBorrow;
import com.cxdai.console.base.entity.FirstBorrowAppr;
import com.cxdai.console.base.entity.FirstTenderDetail;
import com.cxdai.console.base.entity.FirstTenderReal;
import com.cxdai.console.base.mapper.BaseFirstBorrowApprMapper;
import com.cxdai.console.base.mapper.BaseFirstBorrowMapper;
import com.cxdai.console.base.mapper.BaseFirstTenderDetailMapper;
import com.cxdai.console.base.mapper.BaseFirstTenderRealMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.firstborrow.mapper.FirstBorrowMapper;
import com.cxdai.console.firstborrow.vo.FirstBorrowCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowVo;
import com.cxdai.console.firstborrow.vo.FirstTenderDetailCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderDetailVo;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.statistics.account.vo.DrawMoneyToNoDrawCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:直通车业务实现类<br />
 * </p>
 * 
 * @title CashRecordServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年6月26日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstBorrowService{

	@Autowired
	private FirstBorrowMapper firstBorrowMapper;
	@Autowired
	private BaseFirstBorrowMapper baseFirstBorrowMapper;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private FirstTenderDetailService firstTenderDetailService;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private AccountNetValueMapper accountNetValueMapper;
	@Autowired
	private BaseFirstTenderDetailMapper baseFirstTenderDetailMapper;
	@Autowired
	private BaseFirstBorrowApprMapper baseFirstBorrowApprMapper;
	@Autowired
	private BaseFirstTenderRealMapper baseFirstTenderRealMapper;

	
	public Integer updateRealAccountByUnlock(Integer unlockaccount, Integer firstBorrowId) throws Exception {
		return firstBorrowMapper.updateRealAccountByUnlock(unlockaccount, firstBorrowId);
	}

	
	public String saveTenderFirstAutoApprove(Integer firstBorrowId, String ip) throws Exception {
		String result = "success";
		// 获取优先投标信息
		FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
		firstBorrowCnd.setId(String.valueOf(firstBorrowId));
		// 锁定投标记录
		firstBorrowCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		FirstBorrowVo firstBorrowVo = this.queryFirstBorrowByCnd(firstBorrowCnd);
		// 如果满标复审中,执行满标操作
		if (firstBorrowVo.getStatus() != Constants.FIRST_BORROW_STATUS_TO_SUCCESS_APPROVE) {
			return "直通车状态非满标复审中,请刷新数据!";
		}
		// 插入满标审核记录
		FirstBorrowAppr firstBorrowAppr = new FirstBorrowAppr();
		firstBorrowAppr.setApprTime(new Date());
		firstBorrowAppr.setStatus(Constants.APPROVE_PASS);
		firstBorrowAppr.setRemark("满标审核，系统自动通过!");
		firstBorrowAppr.setStyle(Constants.APPROVE_STYLE_SUCCESS_APPROVE);
		firstBorrowAppr.setFirstBorrowId(firstBorrowVo.getId());
		baseFirstBorrowApprMapper.insertEntity(firstBorrowAppr);

		// 更新优先投标为满标审核通过
		firstBorrowVo.setStatus(Constants.FIRST_BORROW_STATUS_SUCCESS_APPROVE_PASS);
		firstBorrowVo.setSuccessTime(new Date());
		// 获取锁定日期
		Date lockEndTime = DateUtils.monthOffset(firstBorrowVo.getSuccessTime(), firstBorrowVo.getLockLimit());
		firstBorrowVo.setLockEndtime(lockEndTime);
		FirstBorrow firstBorrow = new FirstBorrow();
		BeanUtils.copyProperties(firstBorrowVo, firstBorrow);
		BeanUtils.copyProperties(firstBorrowVo, firstBorrow);
		firstBorrow.setSelfVersion(firstBorrow.getVersion());
		firstBorrow.setVersion(UUID.randomUUID().toString().replace("-", ""));
		Integer updateCount = baseFirstBorrowMapper.updateEntity(firstBorrow);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("直通车数据已变更,请刷新页面或稍后重试！");
		}

		// 获取开通记录，
		FirstTenderDetailCnd firstTenderDetailCnd = new FirstTenderDetailCnd();
		firstTenderDetailCnd.setFirstBorrowId(firstBorrow.getId());
		List<FirstTenderDetailVo> tenders = firstTenderDetailService.queryListByCnd(firstTenderDetailCnd);
		// 复审通过，更新用户资金账户、，生成开通成功日志、更新开通状态
		reviewPassed(firstBorrow, tenders, ip);
		// 生成最终的开通记录
		this.generateTenderReal(firstBorrow, tenders);
		// 更新关联的最终记录id
		this.updateFirstTenderDetail(firstBorrow);
		return result;
	}

	/**
	 * 
	 * <p>
	 * Description:更新关联的最终记录id<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月4日
	 * @param firstBorrow
	 * @throws Exception void
	 */
	private void updateFirstTenderDetail(FirstBorrow firstBorrow) throws Exception {
		// 更新关联的最终记录id
		firstTenderDetailService.updateRealIdByFirstBorrowId(firstBorrow.getId());
	}

	/**
	 * <p>
	 * Description:生成最终的开通记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年3月6日
	 * @param firstBorrowId void
	 */
	private void generateTenderReal(FirstBorrow firstBorrow, List<FirstTenderDetailVo> firstTenderDetailList) throws Exception {
		// 获取用户的投资总额
		Map<String, Integer> userMoneyMap = new HashMap<String, Integer>();
		for (FirstTenderDetailVo firstTenderDetailVo : firstTenderDetailList) {
			// 如果用户已加入，金融累加
			if (userMoneyMap.containsKey(String.valueOf(firstTenderDetailVo.getUserId()))) {
				userMoneyMap.put(String.valueOf(firstTenderDetailVo.getUserId()), userMoneyMap.get(String.valueOf(firstTenderDetailVo.getUserId())) + firstTenderDetailVo.getAccount());
				// 包含用户
			} else {
				userMoneyMap.put(String.valueOf(firstTenderDetailVo.getUserId()), firstTenderDetailVo.getAccount());
			}
		}
		for (String userId : userMoneyMap.keySet()) {
			FirstTenderReal firstTenderReal = new FirstTenderReal();
			firstTenderReal.setFirstBorrowId(firstBorrow.getId());
			firstTenderReal.setAccount(userMoneyMap.get(userId));
			firstTenderReal.setRate(new BigDecimal(firstTenderReal.getAccount()).divide(new BigDecimal(firstBorrow.getPlanAccount()), 8, BigDecimal.ROUND_DOWN));
			firstTenderReal.setUserId(Integer.parseInt(userId));
			firstTenderReal.setUseMoney(new BigDecimal(userMoneyMap.get(userId)));
			firstTenderReal.setPlanAccount(firstBorrow.getPlanAccount());
			firstTenderReal.setStatus(Constants.TENDER_REAL_STATUS_AVAILABLE);
			firstTenderReal.setAddtime(new Date());
			firstTenderReal.setVersion(UUID.randomUUID().toString().replace("-", ""));
			firstTenderReal.setFirstTenderType(BusinessConstants.FIRST_BORROW_TENDER_TYPE_ONE); // 首次开通
			baseFirstTenderRealMapper.insertEntity(firstTenderReal);
		}
	}

	/**
	 * <p>
	 * Description:复审通过，资金账户、日志、开通状态<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月16日
	 * @param firstBorrow void
	 */
	private void reviewPassed(FirstBorrow firstBorrow, List<FirstTenderDetailVo> tenders, String ip) throws Exception {
		// 获取开通记录用户的帐号信息并锁定记录
		List<AccountVo> accountList = firstTenderDetailService.queryAccountListForUpdateByFirstId(firstBorrow.getId());
		// 更新投标明细为开通成功
		firstTenderDetailService.updateStatusByFirstBorrowId(firstBorrow.getId(), Constants.TENDER_DETAIL_STATUS_SUCCESS);
		// 将帐号记录封装到map中
		Map<Integer, AccountVo> accountVoMap = new HashMap<Integer, AccountVo>();
		for (AccountVo accountVo : accountList) {
			accountVoMap.put(accountVo.getUserId(), accountVo);
		}

		for (FirstTenderDetailVo firstTenderDetailVo : tenders) {
			AccountVo accountVo = accountVoMap.get(firstTenderDetailVo.getUserId());

			accountVo.setNoUseMoney(accountVo.getNoUseMoney().subtract(new BigDecimal(firstTenderDetailVo.getAccount())));
			// 更新用户优先投标余额
			if (null != accountVo.getFirstBorrowUseMoney()) {
				accountVo.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney().add(new BigDecimal(firstTenderDetailVo.getAccount())));
			} else {
				accountVo.setFirstBorrowUseMoney(new BigDecimal(firstTenderDetailVo.getAccount()));
			}

			// 更新帐号
			Account account = new Account();
			BeanUtils.copyProperties(accountVo, account);
			baseAccountMapper.updateEntity(account);

			// 重新设置到map中
			accountVoMap.put(firstTenderDetailVo.getUserId(), accountVo);

			// 开通账户日志-开通成功
			accountLogService.saveAccountLogByParams(accountVo, firstTenderDetailVo.getUserId(), new BigDecimal(firstTenderDetailVo.getAccount()), "投标直通车：(第" + firstBorrow.getPeriods() + "期)"
					+ "审核通过，扣除账户冻结金额。", ip, "first_subscribe_success", Constants.ACCOUNT_LOG_ID_TYPE_FIRST, firstBorrow.getId(), firstBorrow.getName());
		}
	}

	
	public Page queryPageListByCnd(FirstBorrowCnd firstBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);

		int totalCount = firstBorrowMapper.queryFirstBorrowCount(firstBorrowCnd);
		page.setTotalCount(totalCount);
		List<FirstBorrowVo> list = firstBorrowMapper.queryFirstBorrowList(firstBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	
	public FirstBorrowVo queryFirstBorrowByCnd(FirstBorrowCnd firstBorrowCnd) throws Exception {
		List<FirstBorrowVo> list = firstBorrowMapper.queryFirstBorrowList(firstBorrowCnd);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	
	public String updateFirstBorrow(FirstBorrowVo firstBorrowVo) throws Exception {
		String result = "success";
		if (null == firstBorrowVo || null == firstBorrowVo.getId() || null == firstBorrowVo.getVersion()) {
			return "直通车对象不能为空";
		}
		FirstBorrowVo lastfirstBorrowVo = firstBorrowMapper.getNewLatestById(firstBorrowVo.getId());
		if (lastfirstBorrowVo != null && lastfirstBorrowVo.getEndTime() != null) {
			if (firstBorrowVo.getPublishTime() == null) {
				return "请选择定时开通时间！";
			}
			if (firstBorrowVo.getPublishTime().compareTo(lastfirstBorrowVo.getEndTime()) < 0 && !"Y".equals(firstBorrowVo.getFlagFromSub())) {
				return "定时开通时间不能小于" + DateUtils.format(lastfirstBorrowVo.getEndTime(), DateUtils.YMD_HMS);
			}
		}
		if (firstBorrowVo.getValidTimeStyle() != null && firstBorrowVo.getValidTimeStyle().intValue() != 0 && firstBorrowVo.getValidTime() != null) {
			if (firstBorrowVo.getValidTimeStyle() == 1) { // 按天
				firstBorrowVo.setValidTime(firstBorrowVo.getValidTime() * 24 * 60);
			}
			if (firstBorrowVo.getValidTimeStyle() == 2) { // 按小时
				firstBorrowVo.setValidTime(firstBorrowVo.getValidTime() * 60);
			}
			if (firstBorrowVo.getValidTimeStyle() == 3) { // 按分钟
				firstBorrowVo.setValidTime(firstBorrowVo.getValidTime());
			}
		} else {
			return "请选择有效期限及单位！";
		}
		FirstBorrow firstBorrow = new FirstBorrow();
		BeanUtils.copyProperties(firstBorrowVo, firstBorrow);
		// 加密投标密码
		if (null != firstBorrow.getPasswordSource() && !"".equals(firstBorrow.getPasswordSource())) {
			// 设置密文
			firstBorrow.setBidPassword(MD5.toMD5(firstBorrow.getPasswordSource()));
		}
		// 将实际金额设置为计划金额
		firstBorrowVo.setRealAccount(firstBorrowVo.getPlanAccount());
		firstBorrow.setSelfVersion(firstBorrow.getVersion());
		firstBorrow.setVersion(UUID.randomUUID().toString().replace("-", ""));
		if (firstBorrow.getValidTime() != null && firstBorrow.getPublishTime() != null) {
			firstBorrow.setEndTime(DateUtils.minuteOffset(firstBorrow.getPublishTime(), firstBorrow.getValidTime()));
		}
		Integer updateCount = baseFirstBorrowMapper.updateEntity(firstBorrow);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("直通车数据已变更,请刷新页面或稍后重试！");
		}
		return result;
	}

	
	public String updateFirstBorrowStatus(FirstBorrowVo firstBorrowVo) throws Exception {
		String result = "success";
		if (null == firstBorrowVo || null == firstBorrowVo.getId() || null == firstBorrowVo.getVersion()) {
			return "直通车对象不能为空";
		}
		FirstBorrow firstBorrow = new FirstBorrow();
		BeanUtils.copyProperties(firstBorrowVo, firstBorrow);
		firstBorrow.setSelfVersion(firstBorrow.getVersion());
		firstBorrow.setVersion(UUID.randomUUID().toString().replace("-", ""));
		if (firstBorrow.getValidTime() != null && firstBorrow.getPublishTime() != null) {
			firstBorrow.setEndTime(DateUtils.minuteOffset(firstBorrow.getPublishTime(), firstBorrow.getValidTime()));
		}
		Integer updateCount = baseFirstBorrowMapper.updateEntity(firstBorrow);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("直通车数据已变更,请刷新页面或稍后重试！");
		}
		return result;
	}

	
	public String insertFirstBorrow(FirstBorrowVo firstBorrowVo) throws Exception {
		String result = "success";
		boolean haveUnProcess = this.validateAdd();
		// 如果有计划在处理中
		if (haveUnProcess) {
			return "当前已有一个投标直通车，请先处理！";
		}
		FirstBorrowVo lastfirstBorrowVo = firstBorrowMapper.getLatest();
		if (lastfirstBorrowVo != null && lastfirstBorrowVo.getEndTime() != null) {
			if (firstBorrowVo.getPublishTime() == null) {
				return "请选择定时开通时间！";
			}
			if (firstBorrowVo.getPublishTime().compareTo(lastfirstBorrowVo.getEndTime()) < 0) {
				return "定时开通时间不能小于" + DateUtils.format(lastfirstBorrowVo.getEndTime(), DateUtils.YMD_HMS);
			}
		}
		// 新增记录
		if (firstBorrowVo.getValidTimeStyle() != null && firstBorrowVo.getValidTimeStyle().intValue() != 0 && firstBorrowVo.getValidTime() != null) {
			if (firstBorrowVo.getValidTimeStyle() == 1) { // 按天
				firstBorrowVo.setValidTime(firstBorrowVo.getValidTime() * 24 * 60);
			}
			if (firstBorrowVo.getValidTimeStyle() == 2) { // 按小时
				firstBorrowVo.setValidTime(firstBorrowVo.getValidTime() * 60);
			}
			if (firstBorrowVo.getValidTimeStyle() == 3) { // 按分钟
				firstBorrowVo.setValidTime(firstBorrowVo.getValidTime());
			}
		} else {
			return "请选择有效期限及单位！";
		}
		firstBorrowVo.setAddRate((double) 0);
		firstBorrowVo.setServiceRate((double) 0);
		firstBorrowVo.setExitRate((double) 0);

		FirstBorrow firstBorrow = new FirstBorrow();
		BeanUtils.copyProperties(firstBorrowVo, firstBorrow);
		// 加密投标密码
		if (null != firstBorrowVo.getPasswordSource() && !"".equals(firstBorrowVo.getPasswordSource())) {
			// 设置密文
			firstBorrow.setBidPassword(MD5.toMD5(firstBorrow.getPasswordSource()));
		}
		firstBorrow.setAddtime(new Date());
		// 设置状态为草稿
		firstBorrow.setStatus(Constants.FIRST_BORROW_STATUS_NEW);
		firstBorrow.setAccountYes(0);
		firstBorrow.setRealAccount(0);
		firstBorrow.setUuid(UUID.randomUUID().toString().replace("-", ""));
		firstBorrow.setVersion(UUID.randomUUID().toString().replace("-", ""));
		// 查询最大期数
		Integer periods = firstBorrowMapper.queryMaxPeriods();
		periods = periods + 1;
		firstBorrow.setPeriods(periods + "");
		firstBorrow.setName("投标直通车——第" + periods + "期");
		// 设置合同号
		firstBorrow.setContractNo(this.queryContractNo());
		// 设置投标次数
		firstBorrow.setTenderTimes(0);
		if (firstBorrow.getValidTime() != null && firstBorrow.getPublishTime() != null) {
			firstBorrow.setEndTime(DateUtils.minuteOffset(firstBorrow.getPublishTime(), firstBorrow.getValidTime()));
		}
		Integer count = firstBorrowMapper.insertFirstBorrowWidthCondition(firstBorrow);
		if (null == count || count == 0) {
			throw new AppException("保存异常,请刷新页面或稍后重试");
		}
		return result;
	}

	
	public boolean validateAdd() throws Exception {
		boolean result = true;
		FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
		// 验证添加
		firstBorrowCnd.setValidateAdd("true");
		List<FirstBorrowVo> firstBorrows = firstBorrowMapper.queryFirstBorrowList(firstBorrowCnd);
		if (null == firstBorrows || firstBorrows.size() == 0) {
			result = false;
		}
		return result;
	}

	
	public String saveCancelFirstBorrow(String id, String version, String ip) throws Exception {
		String result = "success";
		FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
		// 锁定记录
		firstBorrowCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		firstBorrowCnd.setId(id);
		firstBorrowCnd.setVersion(version);
		FirstBorrowVo firstBorrowVo = this.queryFirstBorrowByCnd(firstBorrowCnd);
		if (null == firstBorrowVo) {
			return "未找到可以撤消的直通车数据,请刷新页面或稍后重试!";
		}
		if (firstBorrowVo.getStatus() != Constants.FIRST_BORROW_STATUS_APPROVE_PASS) {
			return "直通车状态非审核通过投标中,请确认数据";
		}
		// 更新状态为撤标
		firstBorrowVo.setStatus(Constants.FIRST_BORROW_STATUS_CANCEL);
		this.updateFirstBorrowStatus(firstBorrowVo);
		// 直通车撤消更新帐号和保存资金记录
		updateAccountByCancelFirst(ip, firstBorrowVo);
		return result;
	}

	/**
	 * <p>
	 * Description:直通车撤消更新帐号和保存资金记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月22日
	 * @param ip
	 * @param firstBorrowVo
	 * @throws Exception void
	 */
	private void updateAccountByCancelFirst(String ip, FirstBorrowVo firstBorrowVo) throws Exception {
		// 获取开通记录，
		FirstTenderDetailCnd firstTenderDetailCnd = new FirstTenderDetailCnd();
		firstTenderDetailCnd.setFirstBorrowId(firstBorrowVo.getId());
		List<FirstTenderDetailVo> tenders = firstTenderDetailService.queryListByCnd(firstTenderDetailCnd);
		// 获取开通记录用户的帐号信息并锁定记录
		for (FirstTenderDetailVo firstTenderDetailVo : tenders) {
			Account account = baseAccountMapper.queryByUserIdForUpdate(firstTenderDetailVo.getUserId());
			account.setNoUseMoney(account.getNoUseMoney().subtract(new BigDecimal(firstTenderDetailVo.getAccount())));
			account.setUseMoney(account.getUseMoney().add(new BigDecimal(firstTenderDetailVo.getAccount())));
			account.setDrawMoney(account.getDrawMoney().add(firstTenderDetailVo.getDrawMoney()));
			account.setNoDrawMoney(account.getNoDrawMoney().add(firstTenderDetailVo.getNoDrawMoney()));

			account.setSelfVersion(account.getVersion());
			account.setVersion(account.getVersion() + 1);
			baseAccountMapper.updateEntity(account);

			// 撤销认购记录
			firstTenderDetailVo.setStatus(Constants.TENDER_DETAIL_STATUS_FAILURE);
			FirstTenderDetail firstTenderDetail = new FirstTenderDetail();
			BeanUtils.copyProperties(firstTenderDetailVo, firstTenderDetail);
			firstTenderDetail.setSelfVersion(firstTenderDetail.getVersion());
			firstTenderDetail.setVersion(firstTenderDetail.getVersion() + 1);
			baseFirstTenderDetailMapper.updateEntity(firstTenderDetail);

			AccountVo accountVo = new AccountVo();
			BeanUtils.copyProperties(account, accountVo);
			// 开通账户日志-开通成功
			accountLogService.saveAccountLogByParams(accountVo, firstTenderDetailVo.getUserId(), new BigDecimal(firstTenderDetailVo.getAccount()), "投标直通车被撤销，退回资金。", ip, "first_cancel",
					Constants.ACCOUNT_LOG_ID_TYPE_FIRST, firstBorrowVo.getId(), firstBorrowVo.getName());

			// 判断用户的可提金额是否大于净值额度，如果大于，转入受限金额
			saveDrawToNoDrawByCancelFirst(ip, firstTenderDetailVo);
		}
	}

	/**
	 * <p>
	 * Description:获得最新合同号<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年3月3日
	 * @return String
	 */
	private String queryContractNo() throws Exception {
		String result = "";
		String contractNo = firstBorrowMapper.queryMaxContractNo();
		int no = 0;
		if (null != contractNo && contractNo.length() > 0) {
			no = Integer.parseInt(contractNo.substring(contractNo.length() - 4)) + 1;
		} else {
			no = 1000;
		}
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR)).substring(String.valueOf(cal.get(Calendar.YEAR)).length() - 2);
		int month = cal.get(Calendar.MONTH) + 1;
		String monthStr = String.valueOf(cal.get(Calendar.MONTH) + 1);
		if (month < 10) {
			monthStr = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		} else {
			monthStr = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}
		result = "YX-" + year + monthStr + no;
		return result;
	}

	/**
	 * 
	 * <p>
	 * Description:撤消直通车：判断用户的可提金额是否大于净值额度，如果大于，转入受限金额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月25日
	 * @param ip
	 * @param firstTenderDetailVo
	 * @throws Exception void
	 */
	private void saveDrawToNoDrawByCancelFirst(String ip, FirstTenderDetailVo firstTenderDetailVo) throws Exception {
		DrawMoneyToNoDrawCnd drawMoneyToNoDrawCnd = new DrawMoneyToNoDrawCnd();
		drawMoneyToNoDrawCnd.setUserid(firstTenderDetailVo.getUserId());
		drawMoneyToNoDrawCnd.setNetmoneytype(BusinessConstants.NET_TYPE_FIRST_CANCEL);
		drawMoneyToNoDrawCnd.setAddip(ip);
		drawMoneyToNoDrawCnd.setAccountlogType("net_draw_to_nodraw_first_cancel");
		drawMoneyToNoDrawCnd.setAccountlogRemark("直通车撤消之后,可提金额大于净值额度，可提金额转入受限金额。");
		accountNetValueMapper.dealDrawmoneyToNodraw(drawMoneyToNoDrawCnd);
	}

	
	public FirstBorrowVo getLatest() {
		return firstBorrowMapper.getLatest();
	}

	
	public FirstBorrowVo getNewLatestById(int id) {
		return firstBorrowMapper.getNewLatestById(id);
	}

	
	public String saveFirstBorrow(int borrow_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowid", borrow_id);
		firstBorrowMapper.firstTender(map);
		String msg = map.get("msg").toString();
		if (!"0001".equals(msg)) {
			throw new AppException("直通车投标出错");
		}
		return BusinessConstants.SUCCESS;
	}
}
