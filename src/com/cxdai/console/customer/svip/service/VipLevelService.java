package com.cxdai.console.customer.svip.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.AutoInvestConfigMapper;
import com.cxdai.console.account.recharge.service.AutoInvestConfigRecordService;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigCnd;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.base.entity.AutoInvestConfig;
import com.cxdai.console.base.entity.AutoInvestConfigRecord;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.svip.entity.VIPAppro;
import com.cxdai.console.customer.svip.entity.VipLevel;
import com.cxdai.console.customer.svip.entity.VipLevelCnd;
import com.cxdai.console.customer.svip.entity.VipLevelVo;
import com.cxdai.console.customer.svip.mapper.BaseAutoInvestConfigMapper;
import com.cxdai.console.customer.svip.mapper.BaseAutoInvestConfigRecordMapper;
import com.cxdai.console.customer.svip.mapper.BaseVIPApproMapper;
import com.cxdai.console.customer.svip.mapper.BaseVipLevelMapper;
import com.cxdai.console.customer.svip.mapper.VipLevelMapper;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.DateUtils;

/**
 * 
 * <p>
 * Description:终身顶级会员业务处理方法<br />
 * </p>
 * 
 * @title TopSvipServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author yangshijin
 * @version 0.1 2015年1月13日
 */
@Service@Transactional(rollbackFor = Throwable.class)
public class VipLevelService {

	@Autowired
	private VipLevelMapper vipLevelMapper;
	@Autowired
	private BaseVipLevelMapper baseVipLevelMapper;
	@Autowired
	private BaseVIPApproMapper baseVIPApproMapper;
	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;
	@Autowired
	private BaseAutoInvestConfigMapper baseAutoInvestConfigMapper;
	@Autowired
	private BaseAutoInvestConfigRecordMapper baseAutoInvestConfigRecordMapper;
	@Autowired
	private AutoInvestConfigRecordService autoInvestConfigRecordService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountLogService accountLogService;

	
	public Page queryTopSvipForPage(VipLevelCnd vipLevelCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = vipLevelMapper.querySvipCount(vipLevelCnd);
		page.setTotalCount(totalCount);
		List<VipLevelVo> list = vipLevelMapper.querySvipList(vipLevelCnd, page);
		page.setResult(list);
		return page;
	}

	
	@Transactional(rollbackFor = Throwable.class)
	public String setSvipForUserId(int userId, String ip, String remark, ShiroUser shiroUser) throws Exception {
		VipLevelVo vipLevelVo = vipLevelMapper.queryByUserIdAndType(userId, BusinessConstants.VIP_LEVEL_TWO);
		if (vipLevelVo != null && vipLevelVo.getStatus() == BusinessConstants.VIP_LEVEL_STATUS_ENABLE) {
			return "该用户已是终身顶级会员，请勿重复操作！";
		} else {
			// 新增终身顶级会员
			saveVipLevel(userId, ip, remark, shiroUser);
			// 处理VIP认证
			handleVipAppro(userId, ip, shiroUser);
			// 处理自动投标规则
			handleAutoInvestConfig(userId);
		}
		return "设置成功！";
	}

	/**
	 * 
	 * <p>
	 * Description:新增VIP会员等级信息<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月14日
	 * @param vipLevel
	 * @throws Exception void
	 */
	public void saveVipLevel(int userId, String ip, String remark, ShiroUser shiroUser) throws Exception {
		VipLevelVo vipLevelVo = vipLevelMapper.queryByUserIdAndType(userId, BusinessConstants.VIP_LEVEL_TWO);
		if (vipLevelVo == null) {
			VipLevel vipLevel = new VipLevel();
			vipLevel.setUserId(userId);
			vipLevel.setStatus(BusinessConstants.VIP_LEVEL_STATUS_ENABLE);
			vipLevel.setAddId(shiroUser.getUserId());
			vipLevel.setAddIp(ip);
			vipLevel.setRemark(remark);
			vipLevel.setAddTime(new Date());
			vipLevel.setType(BusinessConstants.VIP_LEVEL_TWO);
			Integer order = vipLevelMapper.getMaxOrderByType(BusinessConstants.VIP_LEVEL_TWO);
			if (order != null) {
				vipLevel.setOrder(order + 1);
			} else {
				vipLevel.setOrder(1);
			}
			baseVipLevelMapper.insertEntity(vipLevel);
		} else {
			vipLevelVo.setStatus(0);
			VipLevel vipLevel = new VipLevel();
			BeanUtils.copyProperties(vipLevelVo, vipLevel);
			vipLevel.setAddId(shiroUser.getUserId());
			vipLevel.setAddIp(ip);
			vipLevel.setAddTime(new Date());
			Integer order = vipLevelMapper.getMaxOrderByType(BusinessConstants.VIP_LEVEL_TWO);
			if (order != null) {
				vipLevel.setOrder(order + 1);
			} else {
				vipLevel.setOrder(1);
			}
			baseVipLevelMapper.updateEntity(vipLevel);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:处理VIP认证<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月14日 void
	 */
	public void handleVipAppro(int userId, String ip, ShiroUser shiroUser) throws Exception {
		VIPAppro vipAppro = baseVIPApproMapper.queryByUserId(userId);
		Date indate = DateUtils.parse("2199-12-30 00:00:00", DateUtils.YMD_HMS);
		if (vipAppro != null) {
			if (vipAppro.getPassed() == 1) {
				vipAppro.setIndate(indate);
				// 更新VIP到期时间
				baseVIPApproMapper.updateEntity(vipAppro);
			} else {
				vipAppro.setPassed(1);
				vipAppro.setApprover(shiroUser.getUserName());
				vipAppro.setApproveTime(DateUtils.getTime());
				vipAppro.setApproveRemark("开通终身顶级会员");
				vipAppro.setServiceNum(shiroUser.getUserName());
				vipAppro.setFee(BigDecimal.ZERO);
				vipAppro.setIndate(indate);
				vipAppro.setRemark("开通终身顶级会员");
				// 更新VIP到期时间
				baseVIPApproMapper.updateEntity(vipAppro);

				AccountVo accountVo = accountService.queryAccountByUserId(userId);
				AccountLog accountLog = new AccountLog();
				accountLog.setAddip(ip);
				accountLog.setAddtime(DateUtils.getTime());
				accountLog.setCollection(accountVo.getCollection());
				accountLog.setUseMoney(accountVo.getUseMoney());
				accountLog.setNoUseMoney(accountVo.getNoUseMoney());
				accountLog.setTotal(accountVo.getTotal());
				accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
				accountLog.setMoney(BigDecimal.ZERO);// 交易金额
				accountLog.setRemark("开通终身顶级会员");
				accountLog.setToUser(-1);
				accountLog.setUserId(userId);
				accountLog.setType("vip_cost");
				accountLog.setDrawMoney(accountVo.getDrawMoney());
				accountLog.setNoDrawMoney(accountVo.getNoDrawMoney());
				accountLogService.insertAccountLog(accountLog);
			}
		} else {
			vipAppro = new VIPAppro();
			vipAppro.setUserId(userId);
			vipAppro.setPassed(1);
			vipAppro.setApprover(shiroUser.getUserName());
			vipAppro.setApproveTime(DateUtils.getTime());
			vipAppro.setApproveRemark("开通终身顶级会员");
			vipAppro.setServiceNum(shiroUser.getUserName());
			vipAppro.setFee(BigDecimal.ZERO);
			vipAppro.setIndate(indate);
			vipAppro.setRemark("开通终身顶级会员");
			vipAppro.setAddTime(DateUtils.getTime());
			vipAppro.setAddIp(ip);
			// 新增VIP认证记录
			baseVIPApproMapper.insertEntity(vipAppro);

			AccountVo accountVo = accountService.queryAccountByUserId(userId);
			AccountLog accountLog = new AccountLog();
			accountLog.setAddip(ip);
			accountLog.setAddtime(DateUtils.getTime());
			accountLog.setCollection(accountVo.getCollection());
			accountLog.setUseMoney(accountVo.getUseMoney());
			accountLog.setNoUseMoney(accountVo.getNoUseMoney());
			accountLog.setTotal(accountVo.getTotal());
			accountLog.setFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
			accountLog.setMoney(BigDecimal.ZERO);// 交易金额
			accountLog.setRemark("开通终身顶级会员");
			accountLog.setToUser(-1);
			accountLog.setUserId(userId);
			accountLog.setType("vip_cost");
			accountLog.setDrawMoney(accountVo.getDrawMoney());
			accountLog.setNoDrawMoney(accountVo.getNoDrawMoney());
			accountLogService.insertAccountLog(accountLog);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:对停用和启用的自动投标规则进行升级<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月16日
	 * @param userId
	 * @throws Exception void
	 */
	public void handleAutoInvestConfig(int userId) throws Exception {
		AutoInvestConfigCnd autoIntInvestConfigCnd = new AutoInvestConfigCnd();
		autoIntInvestConfigCnd.setUser_id(userId);
		Page page = new Page(1, 2);
		List<AutoInvestConfigVo> list = autoInvestConfigMapper.queryListByCnd(autoIntInvestConfigCnd, page);
		if (list.size() > 0) {
			VipLevelVo vipLevelVo = vipLevelMapper.queryByUserIdAndType(userId, BusinessConstants.VIP_LEVEL_TWO);
			for (AutoInvestConfigVo autoInvestConfigVo : list) {
				if (vipLevelVo != null && vipLevelVo.getStatus().intValue() == BusinessConstants.VIP_LEVEL_STATUS_ENABLE && autoInvestConfigVo.getStatus() != 2) {
					AutoInvestConfig autoInvestConfig = new AutoInvestConfig();
					BeanUtils.copyProperties(autoInvestConfigVo, autoInvestConfig);
					autoInvestConfig.setUptime(getUptime(userId));
					autoInvestConfig.setVipLevel(BusinessConstants.VIP_LEVEL_TWO);
					// 更新自动投标规则
					baseAutoInvestConfigMapper.updateEntity(autoInvestConfig);
					// 保存历史记录
					AutoInvestConfigRecord autoInvestConfigRecord = autoInvestConfigRecordService.setAutoInvestConfigRecord(autoInvestConfigVo);
					autoInvestConfigRecord.setRownum(autoInvestConfigVo.getRownum());
					autoInvestConfigRecord.setAddtime(new Date());
					autoInvestConfigRecord.setRecord_type(1);
					autoInvestConfigRecord.setRemark("由普通VIP升级为终身顶级会员");
					baseAutoInvestConfigRecordMapper.insertEntity(autoInvestConfigRecord);
				}
			}
		}
	}

	public String getUptime(int userId) throws Exception {
		// 判断该用户是否是终身顶级会员
		VipLevelVo vipLevelVo = vipLevelMapper.queryByUserIdAndType(userId, BusinessConstants.VIP_LEVEL_TWO);
		String initStr = "";
		if (vipLevelVo != null && vipLevelVo.getStatus().intValue() == BusinessConstants.VIP_LEVEL_STATUS_ENABLE) {
			int index = 17 - vipLevelVo.getOrder().toString().length();
			for (int i = 0; i < index; i++) {
				initStr = initStr + "0";
			}
			return initStr + vipLevelVo.getOrder();
		} else {
			// 获取排队时间
			return String.valueOf(new Date().getTime()) + "0001";
		}
	}
}
