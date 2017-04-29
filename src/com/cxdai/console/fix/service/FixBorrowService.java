package com.cxdai.console.fix.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvest;
import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvestRecord;
import com.cxdai.console.borrow.autoInvestConfig.mapper.FixAutoInvestMapper;
import com.cxdai.console.borrow.autoInvestConfig.mapper.FixAutoInvestRecordMapper;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCnd;
import com.cxdai.console.borrow.emerg.vo.TenderRecordVo;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.curaccount.mapper.CurOutMapper;
import com.cxdai.console.curaccount.service.CurInService;
import com.cxdai.console.curaccount.vo.CurInLaunchVo;
import com.cxdai.console.curaccount.vo.CurOutVo;
import com.cxdai.console.fix.entity.FixBorrow;
import com.cxdai.console.fix.entity.FixBorrowTransfer;
import com.cxdai.console.fix.entity.FixOperationLog;
import com.cxdai.console.fix.entity.FixTenderTransfer;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.mapper.FixAccountLogMapper;
import com.cxdai.console.fix.mapper.FixAccountMapper;
import com.cxdai.console.fix.mapper.FixBorrowMapper;
import com.cxdai.console.fix.mapper.FixBorrowTransferMapper;
import com.cxdai.console.fix.mapper.FixOperationLogMapper;
import com.cxdai.console.fix.mapper.FixTenderDetailMapper;
import com.cxdai.console.fix.mapper.FixTenderTransferMapper;
import com.cxdai.console.fix.vo.FixAccountLogVo;
import com.cxdai.console.fix.vo.FixAccountVo;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixOperationLogVo;
import com.cxdai.console.fix.vo.FixStaticVo;
import com.cxdai.console.fix.vo.FixTenderDetailVo;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.entity.RedAccountLog;
import com.cxdai.console.red.mapper.RedAccountLogMapper;
import com.cxdai.console.red.mapper.RedAccountMapper;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.mapper.AccountLogMapper;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.system.mapper.ConfigurationMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.exception.AppException;

/**
 * 定期宝service类
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title FixBorrowService.java
 * @package com.cxdai.console.fix.service
 * @author 陈建国
 * @version 0.1 2015年5月28日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixBorrowService {

	public Logger logger = Logger.getLogger(FixBorrowService.class);

	@Autowired
	private AccountLogMapper accountLogMapper;

	@Autowired
	private RedAccountMapper redMapper;

	@Autowired
	private RedAccountLogMapper redLogMapper;

	@Autowired
	private FixBorrowMapper fixBorrowMapper;

	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private FixAccountMapper fixAccountMapper;
	@Autowired
	private FixAccountLogMapper fixAccountLogMapper;
	@Autowired
	private TenderRecordMapper tenderRecordMapper;
	@Autowired
	private FixBorrowTransferMapper fixBorrowTransferMapper;
	@Autowired
	private BCollectionrecordMapper bCollectionrecordMapper;
	@Autowired
	private FixTenderTransferMapper fixTenderTransferMapper;
	@Autowired
	private FixOperationLogMapper fixOperationLogMapper;
	@Autowired
	private CurInService curInService;
	@Autowired
	private CurOutMapper curOutMapper;
	@Autowired
	private FixTenderDetailMapper fixTenderDetailMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private FixAutoInvestMapper fixAutoInvestMapper;
	@Autowired
	private FixAutoInvestRecordMapper fixAutoInvestRecordMapper;
	@Autowired
	private ConfigurationMapper configurationMapper;

	public Page queryPageListByCnd(FixBorrowCnd fixBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);

		// 格式化开始日期和结束日期
		if (fixBorrowCnd.getBeginDate() != null && !"".equals(fixBorrowCnd.getBeginDate())) {

			fixBorrowCnd.setBeginTime(DateUtils.formatDateYmd(fixBorrowCnd.getBeginDate()));

		}
		if (fixBorrowCnd.getEndDate() != null && !"".equals(fixBorrowCnd.getEndDate())) {

			fixBorrowCnd.setEndTime(DateUtils.formatDateYmdLastSecond(fixBorrowCnd.getEndDate()));

		}

		int totalCount = fixBorrowMapper.queryFixBorrowCount(fixBorrowCnd);
		page.setTotalCount(totalCount);
		List<FixBorrowVo> list = fixBorrowMapper.queryFixBorrowList(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	public Page queryAppFixBorrowList(FixBorrowCnd fixBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);

		// 格式化开始日期和结束日期
		if (fixBorrowCnd.getBeginDate() != null && !"".equals(fixBorrowCnd.getBeginDate())) {

			fixBorrowCnd.setBeginTime(DateUtils.formatDateYmd(fixBorrowCnd.getBeginDate()));

		}
		if (fixBorrowCnd.getEndDate() != null && !"".equals(fixBorrowCnd.getEndDate())) {

			fixBorrowCnd.setEndTime(DateUtils.formatDateYmdLastSecond(fixBorrowCnd.getEndDate()));

		}

		int totalCount = fixBorrowMapper.queryAppFixBorrowCount(fixBorrowCnd);
		page.setTotalCount(totalCount);
		List<FixBorrowVo> list = fixBorrowMapper.queryAppFixBorrowList(fixBorrowCnd, page);
		page.setResult(list);

		return page;
	}

	public FixBorrowVo queryFixBorrowByCnd(FixBorrowCnd fixBorrowCnd) throws Exception {
		List<FixBorrowVo> list = fixBorrowMapper.queryFixBorrowList(fixBorrowCnd);
		// 如果列表对象不为空且包含元素大于1
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	public String updateFixBorrow(FixBorrowVo fixBorrowVo) throws Exception {
		String result = "success";
		if (null == fixBorrowVo || null == fixBorrowVo.getId()) {
			return "定期宝对象不能为空";
		}
		// 取有效期限和数值
		if (fixBorrowVo.getValidTimeStyle() != null && fixBorrowVo.getValidTimeStyle().intValue() != 0 && fixBorrowVo.getValidTime() != null) {
			if (fixBorrowVo.getValidTimeStyle() == 1) { // 按天
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 24 * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 2) { // 按小时
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 3) { // 按分钟
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime());
			}
		} else {
			return "请选择有效期限及单位！";
		}
		// 如果有效投标期限不为空且定期宝发布时间不为空
		if (fixBorrowVo.getValidTime() != null && fixBorrowVo.getPublishTime() != null) {
			fixBorrowVo.setEndTime(DateUtils.minuteOffset(fixBorrowVo.getPublishTime(), fixBorrowVo.getValidTime()));
		}
		fixBorrowVo.setName(getNumberText(fixBorrowVo.getLockLimit()) + "月宝");
		fixBorrowMapper.updatefixBorrowMapper(fixBorrowVo);
		FixOperationLogVo fixOperationLogVo = new FixOperationLogVo();
		this.setFixOperationLogVo(fixBorrowVo, fixOperationLogVo);
		fixOperationLogVo.setOperType(1);
		fixOperationLogVo.setRemark("修改");
		fixBorrowMapper.insertFixOperationLog(fixOperationLogVo);
		return result;
	}

	public String updateFixBorrowStatus(FixBorrowVo fixBorrowVo) throws Exception {
		String result = "success";
		// 如果定期宝对象为空或定期宝id为空
		if (null == fixBorrowVo || null == fixBorrowVo.getId()) {
			return "定期宝对象不能为空";
		}
		// 如果定期宝对象不为空且定期宝id不为空
		if (fixBorrowVo.getValidTime() != null && fixBorrowVo.getPublishTime() != null) {
			fixBorrowVo.setEndTime(DateUtils.minuteOffset(fixBorrowVo.getPublishTime(), fixBorrowVo.getValidTime()));
		}
		FixOperationLogVo fixOperationLogVo = new FixOperationLogVo();
		// 如果是提交审核
		if (fixBorrowVo.getStatus() == Constants.FIX_BORROW_STATUS_TO_APPROVE) {
			fixOperationLogVo.setOperType(7);
			fixOperationLogVo.setRemark("提交审核");
			fixBorrowMapper.updatefixBorrowMapper(fixBorrowVo);
			// 如果是删除
		} else if (fixBorrowVo.getStatus() == Constants.FIX_BORROW_STATUS_DELETED) {
			fixOperationLogVo.setOperType(2);
			fixOperationLogVo.setRemark("删除");
			fixBorrowVo.setPublishTime(null);
			ShiroUser shiroUser = ShiroUtils.currentUser();
			fixBorrowVo.setLastModifyUser(shiroUser.getUserId());
			FixBorrowVo vo=fixBorrowMapper.getFixBorrowByCnd(fixBorrowVo.getId());
			if(StringUtils.isNotEmpty(vo.getBidPassword())){
				fixBorrowVo.setBidPassword(vo.getBidPassword());
			}
			fixBorrowMapper.updatefixBorrowMapper(fixBorrowVo);
		}
		this.setFixOperationLogVo(fixBorrowVo, fixOperationLogVo);
		fixBorrowMapper.insertFixOperationLog(fixOperationLogVo);
		return result;
	}

	public String insertFixBorrow(FixBorrowVo fixBorrowVo) throws Exception {
		String result = "success";
		/*
		 * boolean haveUnProcess = this.validateAdd(); // 如果有计划在处理中 if (haveUnProcess) { return "当前已有一个投标定期宝，请先处理！"; }
		 */
		// 新增记录
		if (fixBorrowVo.getValidTimeStyle() != null && fixBorrowVo.getValidTimeStyle().intValue() != 0 && fixBorrowVo.getValidTime() != null) {
			if (fixBorrowVo.getValidTimeStyle() == 1) { // 按天
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 24 * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 2) { // 按小时
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 3) { // 按分钟
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime());
			}
		} else {
			return "请选择有效期限及单位！";
		}
		fixBorrowVo.setAddTime(new Date());
		// 设置状态为草稿
		fixBorrowVo.setStatus(Constants.FIX_BORROW_STATUS_NEW);
		fixBorrowVo.setAccountYes(BigDecimal.ZERO);
		// 设置合同号
		// fixBorrow.setContractNo(this.queryContractNo());
		// 设置投标次数
		fixBorrowVo.setTenderTimes(0);
		// 如果定期宝对象不为空且定期宝id不为空
		if (fixBorrowVo.getValidTime() != null && fixBorrowVo.getPublishTime() != null) {
			fixBorrowVo.setEndTime(DateUtils.minuteOffset(fixBorrowVo.getPublishTime(), fixBorrowVo.getValidTime()));
		}
		fixBorrowVo.setLockStyle(0);
		fixBorrowVo.setName(getNumberText(fixBorrowVo.getLockLimit()) + "月宝");
		fixBorrowVo.setSourceFrom("1");// 来自新发宝
		Integer count = fixBorrowMapper.insertFixBorrowWidthCondition(fixBorrowVo);
		// 新增返回null或count为0
		if (null == count || count == 0) {
			throw new AppException("保存异常,请刷新页面或稍后重试");
		}
		FixOperationLogVo fixOperationLogVo = new FixOperationLogVo();
		this.setFixOperationLogVo(fixBorrowVo, fixOperationLogVo);
		fixOperationLogVo.setOperType(0);
		fixOperationLogVo.setRemark("创建");
		fixBorrowMapper.insertFixOperationLog(fixOperationLogVo);
		return result;
	}

	public boolean validateAdd() throws Exception {
		boolean result = true;
		FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
		// 验证添加
		fixBorrowCnd.setValidateAdd("true");
		List<FixBorrowVo> fixBorrows = fixBorrowMapper.queryFixBorrowList(fixBorrowCnd);
		// 如果定期表列表对象为空或者列表数量为0
		if (null == fixBorrows || fixBorrows.size() == 0) {
			result = false;
		}
		return result;
	}

	/**
	 * 获得id最新合同号 取当前日期 和锁定期限的流水号累计加1 1506140301 150614 代表年月日，03代表3月宝 01 代表当前日期的当前宝的第一条
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author 陈建国
	 * @version 0.1 2015年6月14日
	 * @param date
	 * @param lockLimit
	 * @return
	 * @throws Exception
	 *             String
	 */
	private String queryContractNo(Date date, int lockLimit) throws Exception {
		String result = "";
		StringBuilder combineString = new StringBuilder();
		// 取日期的yymmdd格式
		String dateYMD = DateUtils.formatDateyyMMdd(date);
		// System.out.println(date);
		// System.out.println("===============");
		// System.out.println(lockLimit);
		// System.out.println(dateYMD);
		if (lockLimit >= 10) {
			combineString.append(dateYMD).append(lockLimit);
		} else {
			combineString.append(dateYMD).append(0).append(lockLimit);
		}
		// System.out.println(combineString);
		String contractNo = fixBorrowMapper.queryMaxContractNo(combineString.toString());
		// System.out.println(contractNo);
		// 累计加一，反之则为01 开始
		if (null != contractNo && contractNo.length() > 0) {
			result = String.valueOf(Integer.parseInt(contractNo) + 1);
			return result;
		} else {
			result = combineString.append("01").toString();

			return result;
		}

	}

	public FixBorrowVo getLatest() throws Exception {
		return fixBorrowMapper.getLatest();
	}

	public FixBorrowVo getNewLatestById(int id) throws Exception {
		return fixBorrowMapper.getNewLatestById(id);
	}

	public String saveCancelFixBorrow(Integer id, String ip) throws Exception {

		String result = "success";
		FixBorrowVo fixBorrowVo = fixBorrowMapper.getFixBorrowByCnd(id);
		// 如果定期宝对象为空
		if (null == fixBorrowVo) {
			return "未找到可以撤消的定期宝数据,请刷新页面或稍后重试!";
		}
		// 如果定期宝状态不等于审核通过标中
		if (fixBorrowVo.getStatus() != Constants.FIX_BORROW_STATUS_APPROVE_PASS) {
			return "定期宝状态非审核通过投标中,请确认数据";
		}
		// 更新状态为撤标
		fixBorrowVo.setStatus(Constants.FIX_BORROW_STATUS_CANCEL);
		ShiroUser shiroUser = ShiroUtils.currentUser();
		fixBorrowVo.setLastModifyUser(shiroUser.getUserId());
		fixBorrowMapper.updatefixBorrowMapper(fixBorrowVo);
		// 更新t_fix_tender_detail为-2
		fixBorrowMapper.updateFixTenderCancelById(id);
		// 更新定期宝下用户账户及日志
		updateRockyAccountAndLog(id, ip);
		// 更新定期宝操作日志
		FixOperationLogVo fixOperationLogVo = new FixOperationLogVo();
		this.setFixOperationLogVo(fixBorrowVo, fixOperationLogVo);
		fixOperationLogVo.setOperType(-1);
		fixOperationLogVo.setRemark("撤销");
		fixBorrowMapper.insertFixOperationLog(fixOperationLogVo);
		// 将转出的活期宝再转入到活期宝
		result = saveCurInForFixBorrow(id);
		if (!BusinessConstants.SUCCESS.equals(result)) {
			throw new AppException("定期宝退回活期宝失败,请确认数据");
		}

		// 查询该定期宝使用的红包
		List<RedAccount> reds = redMapper.getBizReds(id, 1, 3);
		for (RedAccount r : reds) {
			r = redMapper.getByIdForUpdate(r.getId());
			int n = redMapper.returnRed(r.getId());// 返还红包
			if (n == 1) {
				RedAccountLog redLog = new RedAccountLog();
				redLog.setAddip(ip);
				redLog.setMoney(r.getMoney());
				redLog.setRedId(r.getId());
				redLog.setStatus(2);// 2未使用
				redLog.setBizType(6);// 6撤宝
				redLog.setBizId(id);
				redLog.setUsebizId(r.getUsebizId());
				redLog.setRemark("定期宝撤销，返还红包");
				redLog.setUserId(r.getUserId());
				redLogMapper.add(redLog);

				// 更新账户表
				int userId = r.getUserId();
				AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(userId);
				BigDecimal redMoney = r.getMoney();
				accountMapper.updateAccountForRed(new BigDecimal(-1).multiply(redMoney), userId);
				// 账户日志
				accountVo = accountMapper.queryAccountByUserId(userId);
				AccountLog accountLog = new AccountLog();
				accountLog.setUserId(userId);
				accountLog.setAddip(ip);
				accountLog.setAddtime(new Date().getTime() / 1000 + "");
				accountLog.setMoney(redMoney);
				accountLog.setTotal(accountVo.getTotal());
				accountLog.setDrawMoney(accountVo.getDrawMoney());
				accountLog.setNoDrawMoney(accountVo.getNoDrawMoney());
				accountLog.setUseMoney(accountVo.getUseMoney());
				accountLog.setNoUseMoney(accountVo.getNoUseMoney());
				accountLog.setCollection(accountVo.getCollection());
				accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
				accountLog.setToUser(userId);
				accountLog.setType("red_envelop_out");// 字典表定义（红包转出）
				accountLog.setIdType(8);// 8：红包转出
				accountLog.setBorrowId(redLog.getId());// 红包操作日志ID
				accountLog.setRemark("定期宝撤销,红包金额转出账户");
				accountLogMapper.insertEntity(accountLog);
			}
		}
		// liutao 20151123 撤宝后回退排序
		List<FixAutoInvestRecord> fixAutoInvestRecordList = fixAutoInvestRecordMapper.queryByfixId(id);
		if (null != fixAutoInvestRecordList && fixAutoInvestRecordList.size() > 0) {
			for (FixAutoInvestRecord fixAutoInvestRecord : fixAutoInvestRecordList) {
				fixAutoInvestRecordMapper.updateUptime(fixAutoInvestRecord.getAutoInvestId(), fixAutoInvestRecord.getPreUptime());
				// 查询更新后的定期宝设置表，更新操作日志
				FixAutoInvest fixAutoInvest = fixAutoInvestMapper.queryInvestById(fixAutoInvestRecord.getAutoInvestId());
				insertInvestRecord(fixAutoInvest, ip, fixAutoInvestRecord);
			}
		}
		return result;

	}

	/**
	 * 更新定期宝下用户账户及日志
	 * 
	 * @author WangQianJin
	 * @title @param fixBorrowId
	 * @title @param ip
	 * @title @throws Exception
	 * @date 2015年9月8日
	 */
	private void updateRockyAccountAndLog(Integer fixBorrowId, String ip) throws Exception {
		// 根据定期宝id分组查询出所有用户的可提现金额和受限金额
		List<FixTenderDetailVo> fixTenderDetailVoList = fixTenderDetailMapper.queryFixFenderDetailListByFixBorrowId(fixBorrowId);
		if (null != fixTenderDetailVoList && fixTenderDetailVoList.size() > 0) {
			for (FixTenderDetailVo fixTenderDetailVo : fixTenderDetailVoList) {
				// 将用户认购金额全部退回到用户账户
				AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(fixTenderDetailVo.getUserId());

				// 更新用户账户各金额信息
				FixTenderDetailVo fixTenderDetailVoCnd = new FixTenderDetailVo();
				fixTenderDetailVoCnd.setUserId(fixTenderDetailVo.getUserId());
				fixTenderDetailVoCnd.setSumAccount(fixTenderDetailVo.getSumAccount());
				fixTenderDetailVoCnd.setSumDrawMoney(fixTenderDetailVo.getSumDrawMoney());
				fixTenderDetailVoCnd.setSumNoDrawMoney(fixTenderDetailVo.getSumNoDrawMoney());
				accountMapper.updateAccountByUserId(fixTenderDetailVoCnd);

				// 添加账户变动日志
				AccountLog accountLog = new AccountLog();
				accountLog.setTotal(accountVo.getTotal().add(fixTenderDetailVo.getSumAccount()));
				accountLog.setCollection(accountVo.getCollection());
				accountLog.setDrawMoney(accountVo.getDrawMoney().add(fixTenderDetailVo.getSumDrawMoney()));
				accountLog.setNoDrawMoney(accountVo.getNoDrawMoney().add(fixTenderDetailVo.getSumNoDrawMoney()));
				accountLog.setUserId(fixTenderDetailVo.getUserId());
				accountLog.setToUser(ShiroUtils.currentUser().getUserId());
				accountLog.setMoney(fixTenderDetailVo.getSumAccount());
				accountLog.setUseMoney(accountVo.getUseMoney().add(fixTenderDetailVo.getSumAccount()));
				accountLog.setNoUseMoney(accountVo.getNoUseMoney());
				accountLog.setType("fix_borrow_cancel");
				accountLog.setIdType(6);// 定期宝id
				accountLog.setBorrowId(fixBorrowId);
				accountLog.setAddip(ip);
				accountLog.setRemark("定期宝撤宝");
				accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
				accountLogService.insertAccountLog(accountLog);
			}
		}
	}

	/**
	 * @author WangQianJin
	 * @title @param id
	 * @title @param ip
	 * @title @throws Exception
	 * @date 2015年8月27日
	 */
	private String saveCurInForFixBorrow(Integer id) throws Exception {
		String result = BusinessConstants.SUCCESS;
		// 根据定期宝ID获取活期宝转出记录
		List<CurOutVo> curOutVoList = curOutMapper.getCurOutVoByFixId(id);
		if (curOutVoList != null && curOutVoList.size() > 0) {
			CurInLaunchVo curInLaunchVo = new CurInLaunchVo();
			// 循环处理此定期宝加入的活期宝
			for (CurOutVo curOut : curOutVoList) {
				curInLaunchVo.setTenderUserId(curOut.getUserId());
				curInLaunchVo.setCurOutAccount(curOut.getAccount());
				curInLaunchVo.setTenderBackType(BusinessConstants.CUR_OUT_TYPE_TO_BUY_REGULAR_BAO);
				curInLaunchVo.setCurLogType(BusinessConstants.CUR_OUT_TYPE_TO_BUY_REGULAR_BAO_COMMON_CAPITAL_DETAIL);
				curInLaunchVo.setAccountLogType(BusinessConstants.CUR_OUT_TYPE_TO_BUY_REGULAR_BAO_CAPITAL_DETAIL);
				curInLaunchVo.setCurOutId(curOut.getId());
				curInLaunchVo.setAddIp("0.0.0.1");
				curInLaunchVo.setPlatform(1);
				// 调用存储过程转入到活期宝
				result = curInService.saveCurrentInForTenderBack(curInLaunchVo);
				if (!BusinessConstants.SUCCESS.equals(result)) {
					break;
				}
			}
		}
		return result;
	}

	public String saveApprovedPass(FixBorrowVo fixBorrowVo) throws Exception {

		String result = "success";

		// 若果定期宝发布时间小于当前时间
		if (fixBorrowVo.getPublishTime().compareTo(new Date()) < 0) {
			return "定时开通时间已过期，请重新选定时开通时间！";
		}
		// 如果有效期限单位为空且有效期限值不为0且投标有效期限不为空
		if (fixBorrowVo.getValidTimeStyle() != null && fixBorrowVo.getValidTimeStyle().intValue() != 0 && fixBorrowVo.getValidTime() != null) {
			if (fixBorrowVo.getValidTimeStyle() == 1) { // 按天
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 24 * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 2) { // 按小时
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 3) { // 按分钟
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime());
			}
		} else {
			return "请选择有效期限及单位！";
		}
		// 如果投标有效期限不为空且定期宝发布实际呢不为空
		if (fixBorrowVo.getValidTime() != null && fixBorrowVo.getPublishTime() != null) {
			fixBorrowVo.setEndTime(DateUtils.minuteOffset(fixBorrowVo.getPublishTime(), fixBorrowVo.getValidTime()));
		}
		fixBorrowVo.setStatus(Constants.FIX_BORROW_STATUS_APPROVE_PASS);
		if (fixBorrowVo.getContractNo() == null || "".equals(fixBorrowVo.getContractNo())) {
			fixBorrowVo.setContractNo(this.queryContractNo(fixBorrowVo.getPublishTime(), fixBorrowVo.getLockLimit()));
		}
		fixBorrowVo.setName(getNumberText(fixBorrowVo.getLockLimit()) + "月宝");
		fixBorrowMapper.updatefixBorrowMapper(fixBorrowVo);
		// 插入account表记录
		FixAccountVo fixAccountVo = new FixAccountVo();
		fixAccountVo.setFixBorrowId(fixBorrowVo.getId());
		fixAccountVo.setTotal(BigDecimal.ZERO);
		fixAccountVo.setCollection(BigDecimal.ZERO);
		fixAccountVo.setNoUseMoney(BigDecimal.ZERO);
		fixAccountVo.setUseMoney(BigDecimal.ZERO);
		fixAccountVo.setProfit(BigDecimal.ZERO);
		fixAccountVo.setCapital(BigDecimal.ZERO);

		// 插入accountlog表记录
		FixAccountLogVo fixAccountLogVo = new FixAccountLogVo();

		fixAccountLogVo.setTotal(BigDecimal.ZERO);
		fixAccountLogVo.setUseMoney(BigDecimal.ZERO);
		fixAccountLogVo.setMoney(BigDecimal.ZERO);
		fixAccountLogVo.setProfit(BigDecimal.ZERO);
		fixAccountLogVo.setCapital(BigDecimal.ZERO);
		fixAccountLogVo.setNoUseMoney(BigDecimal.ZERO);
		fixAccountLogVo.setCollection(BigDecimal.ZERO);
		fixAccountLogVo.setType(1); // 表示新加入
		fixAccountLogVo.setRemark("新加入");
		// 插入操作日志表
		FixOperationLogVo fixOperationLogVo = new FixOperationLogVo();
		fixOperationLogVo.setOperType(3);
		fixOperationLogVo.setRemark("审核通过");
		this.setFixOperationLogVo(fixBorrowVo, fixOperationLogVo);
		this.setFixAccountLogVo(fixBorrowVo, fixAccountLogVo);
		fixBorrowMapper.insertFixOperationLog(fixOperationLogVo);
		FixAccountVo accountVo = fixAccountMapper.searchFixAccountByFixBorrowId(fixBorrowVo.getId());
		if (accountVo == null) {
			fixAccountMapper.insertFixAccount(fixAccountVo);
		} else {
			fixAccountLogVo.setRemark("再次审核");
		}
		fixAccountLogMapper.insertFixAccountLog(fixAccountLogVo);
		return result;

	}

	public String saveApprovedReject(FixBorrowVo fixBorrowVo) throws Exception {

		String result = "success";
		/*
		 * if (fixBorrowVo.getPublishTime().compareTo(new Date()) < 0) { return "定时开通时间已过期，请重新选定时开通时间！"; }
		 */
		// 更新有效时间
		if (fixBorrowVo.getValidTimeStyle() != null && fixBorrowVo.getValidTimeStyle().intValue() != 0 && fixBorrowVo.getValidTime() != null) {
			if (fixBorrowVo.getValidTimeStyle() == 1) { // 按天
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 24 * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 2) { // 按小时
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime() * 60);
			}
			if (fixBorrowVo.getValidTimeStyle() == 3) { // 按分钟
				fixBorrowVo.setValidTime(fixBorrowVo.getValidTime());
			}
		} else {
			return "请选择有效期限及单位！";
		}
		if (fixBorrowVo.getValidTime() != null && fixBorrowVo.getPublishTime() != null) {
			fixBorrowVo.setEndTime(DateUtils.minuteOffset(fixBorrowVo.getPublishTime(), fixBorrowVo.getValidTime()));
		}
		fixBorrowVo.setStatus(Constants.FIX_BORROW_STATUS_APPROVE_REJECT);
		ShiroUser shiroUser = ShiroUtils.currentUser();
		fixBorrowVo.setLastModifyUser(shiroUser.getUserId());
		fixBorrowVo.setPublishTime(null); // 审核不通过，开放时间为空
		fixBorrowMapper.updatefixBorrowMapper(fixBorrowVo);
		FixOperationLogVo fixOperationLogVo = new FixOperationLogVo();
		fixOperationLogVo.setOperType(-3);
		fixOperationLogVo.setRemark("审核不通过");
		this.setFixOperationLogVo(fixBorrowVo, fixOperationLogVo);
		fixBorrowMapper.insertFixOperationLog(fixOperationLogVo);

		return result;

	}

	public void setFixOperationLogVo(FixBorrowVo fixBorrowVo, FixOperationLogVo fixOperationLogVo) {

		fixOperationLogVo.setUserType(2);
		ShiroUser shiroUser = ShiroUtils.currentUser();
		fixOperationLogVo.setUserId(shiroUser.getUserId());
		fixOperationLogVo.setFixBorrowId(fixBorrowVo.getId());
		fixOperationLogVo.setAddIp(fixBorrowVo.getAddIp());
		fixOperationLogVo.setPlatForm(1);
	}

	public void setFixAccountLogVo(FixBorrowVo fixBorrowVo, FixAccountLogVo fixAccountLogVo) {

		ShiroUser shiroUser = ShiroUtils.currentUser();
		fixAccountLogVo.setAddUser(shiroUser.getUserId());
		fixAccountLogVo.setFixBorrowId(fixBorrowVo.getId());
		fixAccountLogVo.setAddIp(fixBorrowVo.getAddIp());
	}

	public List<FixOperationLogVo> queryfixBorrowApprList(Integer id) throws Exception {
		return fixBorrowMapper.queryfixBorrowApprList(id);
	}

	public FixStaticVo queryStaticBorrow() throws Exception {
		return fixBorrowMapper.queryStaticBorrow();
	}

	public Page queryWillExpireFixBorrowList(FixBorrowCnd fixBorrowCnd, Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
		Integer totalCounts = fixBorrowMapper.queryWillExpireFixBorrowCounts(fixBorrowCnd);
		page.setTotalCount(totalCounts);
		List<FixBorrowVo> list = fixBorrowMapper.queryWillExpireFixBorrowList(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	public FixStaticVo sumWillExpireFixBorrow(FixBorrowCnd fixBorrowCnd) throws Exception {
		return fixBorrowMapper.sumWillExpireFixBorrow(fixBorrowCnd);
	}

	// 数字转中午
	public String getNumberText(Integer source) throws Exception {

		Map<Integer, String> numberMap = new HashMap<Integer, String>();
		numberMap.put(1, "一");
		numberMap.put(2, "二");
		numberMap.put(3, "三");
		numberMap.put(4, "四");
		numberMap.put(5, "五");
		numberMap.put(6, "六");
		numberMap.put(7, "七");
		numberMap.put(8, "八");
		numberMap.put(9, "九");
		numberMap.put(10, "十");
		numberMap.put(11, "十一");
		numberMap.put(12, "十二");
		return numberMap.get(source);
	}

	public String saveFixBorrow(int borrow_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowid", borrow_id);
		map.put("fixborrowid", null);
		map.put("type", 1);
		fixBorrowMapper.fixTender(map);
		String msg = map.get("msg").toString();
		if (!"0001".equals(msg)) {
			throw new AppException("定期宝投标出错");
		}
		return BusinessConstants.SUCCESS;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String handleFixTender(int borrow_id, int fix_borrow_id) throws Exception {
		String result = "success";
		try {
			// 查询该宝的限制日期
			FixBorrowVo fix = fixBorrowMapper.getFixBorrowByCnd(fix_borrow_id);
			if (fix.getTenderBidFlag() != null && fix.getTenderBidFlag().intValue() == 1 && fix.getDiffTenderDays().intValue() <= 0) {
				return "该定期宝已被限制投标，请修改限制";
			}

			// 查询借款标，并锁记录
			BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrow_id);
			//判断是否是非存管标
			if(borrowVo.getIsCustody()==null || borrowVo.getIsCustody()==0){
			}else{
				return "该借款标是存管标，无法投标！";
			}
			
			// 如果定期宝对象为空或者定期宝状态不等于2
			if (borrowVo == null || borrowVo.getStatus() != 2) {
				return "该借款标状态已变更，请刷新后重试！";
			}
			// 如果定期宝对象不为空且定期宝兑现等于2
			if (borrowVo != null && borrowVo.getStatus() == 2) {
				Map<String, Object> map = tenderRecordMapper.queryTenderRecordByBorrowId(borrowVo.getId());
				// 查询标已投金额 和投标记录总额 是否一致
				if (map.get("total_account") != null && !map.get("total_account").toString().equals("")) {
					if (borrowVo.getAccountYes().compareTo(new BigDecimal(map.get("total_account").toString())) != 0) {
						return "投标记录总额与标已投金额不一致，无法手动定期宝投标，请与技术人员联系！";
					}
				} else {
					if (borrowVo.getAccountYes().compareTo(BigDecimal.ZERO) != 0) {
						return "投标记录总额与标已投金额不一致，无法手动定期宝投标，请与技术人员联系！";
					}
				}
				// 开始手动定期宝投标
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("borrowid", borrow_id);
				dataMap.put("fixborrowid", fix_borrow_id);
				dataMap.put("type", 0);
				fixBorrowMapper.fixTender(dataMap);
				if (dataMap.get("msg") != null) {
					if (!dataMap.get("msg").toString().equals("0001")) {
						result = "手动定期宝投标失败！";
					}
				} else {
					result = "手动定期宝投标失败！";
				}
			}
		} catch (Exception e) {
			logger.error("定期宝投标出错", e);
			result = "手动定期宝投标出错！";
		}

		return result;
	}

	@Transactional(rollbackFor = Throwable.class)
	public String handleFixReCheckTender(int borrow_id) throws Exception {
		String result = "success";
		try {
			// 查询借款标，并锁记录

			BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(borrow_id);
			if (borrowVo.getStatus() == 3) {
				// 抵押标自动复审通过
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("borrowid", borrowVo.getId());
				params.put("checkUserId", "-1");
				params.put("checkRemark", "自动复审通过");
				params.put("addip", "0.0.0.1");
				borrowMapper.approveBorrowReCheck(params);
			}
		} catch (Exception e) {
			logger.error("手动投标满标自动复审出错", e);
			result = "手动投标满标自动复审出错！";
		}

		return result;
	}

	public Page getHandleFixBorrowListTender(FixBorrowCnd fixBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = fixBorrowMapper.getCountHandleFixBorrowListTender(fixBorrowCnd);
		page.setTotalCount(totalCount);
		List<FixBorrowVo> list = fixBorrowMapper.getHandleFixBorrowListTender(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	public Page queryPageInterestUserByCnd(FixBorrowCnd fixBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		/*
		 * //增加对时间的特殊处理 if(fixBorrowCnd.getBeginDate()!= null ){ fixBorrowCnd.setBeginTime (DateUtils.formatDateYmd(fixBorrowCnd.getBeginDate())); }else{ fixBorrowCnd.setBeginTime(null); }
		 * if(fixBorrowCnd.getBeginDate()!= null ){ fixBorrowCnd.setEndTime(DateUtils.formatDateYmdLastSecond(fixBorrowCnd .getEndDate())); }else{ fixBorrowCnd.setEndTime(null); }
		 */
		int totalCount = fixBorrowMapper.queryPageInterestUserCount(fixBorrowCnd);
		page.setTotalCount(totalCount);
		List<FixBorrowVo> list = fixBorrowMapper.queryPageInterestUser(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	public Page queryPageInterestFixByCnd(FixBorrowCnd fixBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = fixBorrowMapper.queryPageInterestFixCount(fixBorrowCnd);
		page.setTotalCount(totalCount);
		List<FixBorrowVo> list = fixBorrowMapper.queryPageInterestFix(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 查询导出数量
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception
	 */
	public int countExport(FixBorrowCnd fixBorrowCnd) throws Exception {
		int totalCount = fixBorrowMapper.queryPageInterestFixCount(fixBorrowCnd);
		return totalCount;
	}

	/**
	 * 查询导出列表
	 * @param fixBorrowCnd
	 * @return
	 * @throws Exception
	 */
	public List<FixBorrowVo> listExport(FixBorrowCnd fixBorrowCnd) throws Exception {
		Page page = new Page(Integer.MAX_VALUE);
		List<FixBorrowVo> list = fixBorrowMapper.queryPageInterestFix(fixBorrowCnd, page);
		return list;
	}
	public FixBorrowVo queryAccountByContactNo(FixBorrowCnd fixBorrowCnd) throws Exception {

		return fixBorrowMapper.queryAccountByContactNo(fixBorrowCnd);
	}

	public FixBorrowVo getFixBorrowById(FixBorrowCnd fixBorrowCnd) throws Exception {
		return fixBorrowMapper.getFixBorrowById(fixBorrowCnd);
	}

	public Page getFixBorrowByTransferConn(FixBorrowCnd fixBorrowCnd, Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
		Integer totalCounts = fixBorrowMapper.getFixBorrowCountByTransferConn(fixBorrowCnd);
		page.setTotalCount(totalCounts);
		List<FixBorrowVo> list = fixBorrowMapper.getFixBorrowByTransferConn(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	public BigDecimal getFixBorrowSumUseMoney(FixBorrowCnd fixBorrowCnd) throws Exception {
		return fixBorrowMapper.getFixBorrowSumUseMoney(fixBorrowCnd);
	}

	public BigDecimal getFixBorrowSumUseMoneyYes(FixBorrowCnd fixBorrowCnd) throws Exception {
		return fixBorrowMapper.getFixBorrowSumUseMoneyYes(fixBorrowCnd);
	}

	public Page getFixBorrowListForRepayMent(FixBorrowCnd fixBorrowCnd, Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
		Integer totalCounts = fixBorrowMapper.queryFixBorrowCountForRepayment(fixBorrowCnd);
		page.setTotalCount(totalCounts);
		List<FixBorrowVo> list = fixBorrowMapper.queryFixBorrowListForRepayment(fixBorrowCnd, page);
		page.setResult(list);
		return page;
	}

	public String saveTransfer(Integer id, String ip) throws Exception {
		// 根据系统时间查询到期的定期宝
		FixBorrowVo fixBorrowVo = fixBorrowMapper.queryMatureFixBorrowById(id);
		if (fixBorrowVo != null) {
			// 判断定期宝是否投标
			if (fixBorrowVo.getFixAccountUserMoney().compareTo(fixBorrowVo.getCapital()) == 0) {
				logger.error("定期宝没有投标，无需转让。定期宝ID为：" + fixBorrowVo.getId());
				return "定期宝没有投标，无需转让。定期宝ID为：" + fixBorrowVo.getId();
			}
			// 获取中待收本金总和
			BigDecimal collectionCapital = bCollectionrecordMapper.queryCollectionrecordSum(fixBorrowVo.getId());
			// 待收本金总和 + 定期宝信息.可用余额 != 定期宝信息.本金 的情况
			if (collectionCapital.add(fixBorrowVo.getFixAccountUserMoney()).compareTo(fixBorrowVo.getCapital()) != 0) {
				logger.error("定期宝金额出错，无法转让。定期宝ID为：" + fixBorrowVo.getId());
				return "定期宝金额出错，无法转让。定期宝ID为：" + fixBorrowVo.getId();
			}
			ShiroUser shiroUser = ShiroUtils.currentUser();
			// 新增定期宝转让信息表（t_fix_borrow_transfer）
			Integer fixBorrowTransferId = addFixBorrowTransfer(fixBorrowVo, collectionCapital);

			// 修改定期宝信息，把状态修改为转让中
			FixBorrow fixBorrow = new FixBorrow();
			fixBorrow.setId(fixBorrowVo.getId());
			fixBorrow.setStatus(8);
			fixBorrow.setLastModifyUser(shiroUser.getUserId());
			fixBorrowMapper.updateFixBorrowStatusById(fixBorrow);

			// 保存定期宝操作日志
			addFixOperationLog(fixBorrow.getId(), 8, ip, shiroUser);

			// 根据定期宝ID查询投标记录表信息
			TenderRecordCnd tenderRecordCnd = new TenderRecordCnd();
			// 定期宝ID
			tenderRecordCnd.setUserId(fixBorrowVo.getId());
			List<TenderRecordVo> tenderList = tenderRecordMapper.queryFixTenderRecordList(tenderRecordCnd);
			// 循环投标记录
			for (TenderRecordVo tenderRecordVo : tenderList) {
				// 保存定期宝投标转让表
				addFixTenderTransfer(fixBorrow.getId(), fixBorrowTransferId, tenderRecordVo, ip);
			}
		}
		return "success";
	}

	/**
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
	private void addFixOperationLog(Integer fixBorrowId, Integer operType, String ip, ShiroUser shiroUser) {
		// 保存定期宝操作日志
		FixOperationLog fixOperationLog = new FixOperationLog();
		// 操作人ID
		fixOperationLog.setUserId(shiroUser.getUserId());
		// 用户类型
		fixOperationLog.setUserType(shiroUser.getUserId());
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
		}
		// IP
		fixOperationLog.setAddip(ip);
		// 平台来源
		fixOperationLog.setPlatform(1);
		fixOperationLogMapper.insertFixOperationLog(fixOperationLog);
	}

	/**
	 * <p>
	 * Description:添加定期宝转让信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月3日
	 * @param fixBorrowVo
	 * @param collectionCapital
	 * @return Integer
	 */
	private Integer addFixBorrowTransfer(FixBorrowVo fixBorrowVo, BigDecimal collectionCapital) {
		// 新增定期宝转让信息表（t_fix_borrow_transfer）
		FixBorrowTransfer fixBorrowTransfer = new FixBorrowTransfer();
		ShiroUser staff = ShiroUtils.currentUser();
		// 定期宝ID
		fixBorrowTransfer.setFixBorrowId(fixBorrowVo.getId());
		// 债权本金
		fixBorrowTransfer.setAccount(collectionCapital);
		// 可用金额
		fixBorrowTransfer.setUseMoney(fixBorrowVo.getFixAccountUserMoney());
		// 奖励
		fixBorrowTransfer.setAwards(BigDecimal.ZERO);
		// 转让价格
		fixBorrowTransfer.setAccountReal(collectionCapital);
		// 已经借到的金额
		fixBorrowTransfer.setAccountYes(BigDecimal.ZERO);
		// 锁定期限
		fixBorrowTransfer.setFixLockLimit(fixBorrowVo.getLockLimit());
		// 锁定方式
		fixBorrowTransfer.setFixLockStyle(fixBorrowVo.getLockStyle());
		// 锁定结束日期，精确到天
		fixBorrowTransfer.setFixLockEndtime(fixBorrowVo.getLockEndtime());
		// 年利率
		fixBorrowTransfer.setFixApr(fixBorrowVo.getApr());
		// 定期宝转让标题
		fixBorrowTransfer.setFixBorrowName(fixBorrowVo.getName());
		// 合同编号
		fixBorrowTransfer.setFixContractNo(fixBorrowVo.getContractNo());
		// 添加人
		fixBorrowTransfer.setAdduser(staff.getUserId());
		// 最后修改人
		fixBorrowTransfer.setLastUpdateUser(staff.getUserId());
		// 状态
		fixBorrowTransfer.setStatus(1);
		// 备注
		fixBorrowTransfer.setRemark("定期宝发起转让");
		// 发起的平台来源
		fixBorrowTransfer.setPlatform(1);
		fixBorrowTransferMapper.insert(fixBorrowTransfer);
		return fixBorrowTransfer.getId();
	}

	/**
	 * <p>
	 * Description:添加定期宝投标转让表数据<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月3日
	 * @param fixBorrowId
	 * @param fixBorrowTransferId
	 * @param tenderRecordVo
	 *            void
	 */
	private void addFixTenderTransfer(Integer fixBorrowId, Integer fixBorrowTransferId, TenderRecordVo tenderRecordVo, String ip) {
		FixTenderTransfer fixTenderTransfer = new FixTenderTransfer();
		// 定期宝转让ID
		fixTenderTransfer.setFixBorrowTransferId(fixBorrowTransferId);
		// 定期宝ID
		fixTenderTransfer.setFixBorrowId(fixBorrowId);
		// 借款标Id
		fixTenderTransfer.setBorrowId(tenderRecordVo.getBorrowId());
		// 投标ID
		fixTenderTransfer.setTenderId(tenderRecordVo.getId().intValue());
		// 债权债权本金
		fixTenderTransfer.setAccount(tenderRecordVo.getTenderCapital());
		// 转让价格
		fixTenderTransfer.setAccountReal(tenderRecordVo.getTenderCapital());
		// 利息
		fixTenderTransfer.setInterest(tenderRecordVo.getTenderInterest());
		// 待收金额
		fixTenderTransfer.setRepaymentAccount(tenderRecordVo.getTenderRepayAccount());
		// 标的合同编号
		fixTenderTransfer.setContractNo(tenderRecordVo.getBorrowContractNo());
		// 添加人
		fixTenderTransfer.setAdduser(-1);
		// 添加IP
		fixTenderTransfer.setAddIp(ip);
		// 最后修改人
		fixTenderTransfer.setLastUpdateUser(-1);
		// 已经借到的金额
		fixTenderTransfer.setAccountYes(BigDecimal.ZERO);
		// 状态(1：转让中，2：转让复审中，3：转让完成，4：复审失败)
		fixTenderTransfer.setStatus(1);
		// 备注
		fixTenderTransfer.setRemark("定期宝中标发起转让");
		// 平台来源
		fixTenderTransfer.setPlatform(1);
		fixTenderTransferMapper.insert(fixTenderTransfer);
	}

	@Transactional(rollbackFor = Throwable.class)
	public String changeAreaBorrow(Integer fixId) throws Exception {
		FixBorrowVo firstFix = fixBorrowMapper.searchFixBorrowByIdForUpdate(fixId);
		firstFix.setAreaType(0);
		firstFix.setAreaChangeTime(new Date());
		firstFix.setMostAccount(firstFix.getPlanAccount());
		firstFix.setLowestAccount(new BigDecimal(100));
		firstFix.setLastModifyUser(ShiroUtils.currentUser().getUserId());
		if (fixBorrowMapper.updatefixBorrowMapper(firstFix) > 0) {
			return "转普通专区成功！";
		}
		return "转普通专区失败！";
	}

	/**
	 * @author liutao
	 * @title @param id
	 * @title @param fixAutoInvest
	 * @title @throws Exception
	 * @date 2015年11月24日
	 */
	private void insertInvestRecord(FixAutoInvest fixAutoInvest, String ip, FixAutoInvestRecord fixAutoInvestRecordTemp) {
		FixAutoInvestRecord fixAutoInvestRecord = new FixAutoInvestRecord();
		BeanUtils.copyProperties(fixAutoInvest, fixAutoInvestRecord);
		fixAutoInvestRecord.setRemark("定期宝ID：" + fixAutoInvestRecordTemp.getFixId() + ",撤宝后回退排序");
		fixAutoInvestRecord.setAddip(ip);
		fixAutoInvestRecord.setAutoInvestId(fixAutoInvest.getId());
		fixAutoInvestRecord.setRecordType("8");
		fixAutoInvestRecord.setFixId(fixAutoInvestRecordTemp.getFixId());
		fixAutoInvestRecord.setFixNo(fixAutoInvestRecordTemp.getFixNo());
		fixAutoInvestRecord.setFixType(fixAutoInvestRecordTemp.getFixType());
		fixAutoInvestRecord.setLimitMoney(0);
		fixAutoInvestRecord.setCurMoney(null);
		fixAutoInvestRecord.setUseMoney(null);
		fixAutoInvestRecordMapper.insertInvestRecord(fixAutoInvestRecord);
	}

	public String updateTenderBid(int fixId, int tenderBidFlag, String tenderBidDate) throws Exception {
		if (fixId == -1) {
			// 全部限制-数据字典
			configurationMapper.updateByType(tenderBidFlag + "", tenderBidDate, 1399, 1);
			//记录设置日志
			FixOperationLog fixOperationLog =new FixOperationLog();
			ShiroUser shiroUser = ShiroUtils.currentUser();
			fixOperationLog.setUserId(shiroUser.getUserId());
			fixOperationLog.setUserType(2);
			fixOperationLog.setFixBorrowId(-100000000);
			fixOperationLog.setAddtime(new Date());
			fixOperationLog.setAddip("127.0.0.1");
			fixOperationLog.setPlatform(1);
			if (tenderBidDate == null) {
			    //取消自动投标设置
				fixOperationLog.setOperType(13);
				fixOperationLog.setRemark("取消定期宝自动投标设置");
			}else{
		        //自动投标设置
				fixOperationLog.setOperType(12);
				fixOperationLog.setSetautobidTime(tenderBidDate);
				fixOperationLog.setRemark("定期宝自动投标设置");
			}
			fixOperationLogMapper.insertFixOperationLog(fixOperationLog);
			return "";
		}
		FixBorrowVo fix = fixBorrowMapper.getFixBorrowByCnd(fixId);
		if (fix == null) {
			return "定期宝不存在";
		}
		if (fix.getStatus().intValue() < 3 || fix.getStatus().intValue() > 5) {
			return "定期宝当前状态为" + fix.getStatusStr() + ",不能设置";
		}
		if (fix.getStatus().intValue() == 5 && fixBorrowMapper.getFixUserMoney(fixId) <= 0) {
			return "定期宝没有余额，不能设置";
		}
		fixBorrowMapper.updateTenderBid(fixId, tenderBidFlag, tenderBidDate);
		return "";
	}

}
