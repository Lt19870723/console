package com.cxdai.console.customer.blacklist.service;

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
import com.cxdai.console.base.entity.BlackList;
import com.cxdai.console.base.mapper.BaseBlackListMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.blacklist.mapper.BlackListMapper;
import com.cxdai.console.customer.blacklist.vo.BlackListCnd;
import com.cxdai.console.customer.blacklist.vo.BlackListVo;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.customer.svip.mapper.BaseAutoInvestConfigMapper;
import com.cxdai.console.customer.svip.mapper.BaseAutoInvestConfigRecordMapper;
import com.cxdai.console.firstborrow.mapper.FirstTenderRealMapper;
import com.cxdai.console.firstborrow.service.FirstTenderRealService;
import com.cxdai.console.firstborrow.vo.FirstTenderRealCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderRealVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.exception.AppException;

/**
 * 
 * <p>
 * Description:黑名单业务处理方法<br />
 * </p>
 * 
 * @title BlackListServiceImpl.java
 * @package com.cxdai.console.member.service.impl
 * @author yangshijin
 * @version 0.1 2015年1月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BlackListService {

	@Autowired
	private BlackListMapper blackListMapper;
	@Autowired
	private BaseBlackListMapper baseBlackListMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;
	@Autowired
	private BaseAutoInvestConfigMapper baseAutoInvestConfigMapper;
	@Autowired
	private BaseAutoInvestConfigRecordMapper baseAutoInvestConfigRecordMapper;
	@Autowired
	private AutoInvestConfigRecordService autoInvestConfigRecordService;
	@Autowired
	private FirstTenderRealMapper firstTenderRealMapper;
	@Autowired
	private FirstTenderRealService firstTenderRealService;


	public boolean judgeBlackByUserIdAndType(int userId, int type) throws Exception {
		if (blackListMapper.queryCountByUserIdAndType(userId, type) > 0) {
			return true;
		}
		return false;
	}


	public Page queryPageListByCnd(BlackListCnd blackListCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		if (blackListCnd.getType() != null && blackListCnd.getType() == -1) {
			blackListCnd.setType(null);
		}
		int totalCount = blackListMapper.queryBlackListCount(blackListCnd);
		page.setTotalCount(totalCount);
		List<BlackListVo> list = blackListMapper.queryBlackList(blackListCnd, page);
		page.setResult(list);
		return page;
	}


	public String saveBlackList(BlackListVo blackListVo, ShiroUser user, String ip) throws Exception {
		if (blackListVo.getUsername() == null || blackListVo.getUsername().equals("")) {
			return "用户名不存在，请填写用户名！";
		}
		if (blackListVo.getType() == null || blackListVo.getType() <= 0) {
			return "请选择要禁止的类型！";
		}
		MemberVo memberVo = memberMapper.queryMemberVoByUsername(blackListVo.getUsername());
		if (memberVo == null) {
			return "您填写的用户名不存在！";
		}
		blackListVo.setUserId(memberVo.getId());
		if (blackListMapper.queryCountByUserIdAndType(blackListVo.getUserId(), blackListVo.getType()) > 0) {
			return "该用户已存在该记录，请勿重复提交！";
		}
		blackListVo.setStatus(1); // 生效
		blackListVo.setAddId(user.getUserId());
		blackListVo.setAddIp(ip);
		blackListVo.setAddTime(new Date());
		BlackList blackList = new BlackList();
		BeanUtils.copyProperties(blackListVo, blackList);
		baseBlackListMapper.insertEntity(blackList);
		if (blackList.getId() != null) {
			blackListVo.setId(blackList.getId());
		}
		// 当为禁止设置自动投标时，停用当前已启用的记录
		if (blackList.getType().intValue() == BusinessConstants.BLACK_TYPE_SET_AUTO_INVEST_CONFIG) {
			AutoInvestConfigCnd autoInvestConfigCnd = new AutoInvestConfigCnd();
			autoInvestConfigCnd.setStatus("1");
			autoInvestConfigCnd.setUser_id(blackList.getUserId());
			List<AutoInvestConfigVo> list = autoInvestConfigMapper.queryListByCnd(autoInvestConfigCnd, new Page(1, 2));
			if (list.size() > 0) {
				for (AutoInvestConfigVo autoInvestConfigVo : list) {
					AutoInvestConfigRecord autoInvestConfigRecord = autoInvestConfigRecordService.setAutoInvestConfigRecord(autoInvestConfigVo);
					autoInvestConfigRecord.setRownum(autoInvestConfigVo.getRownum());
					autoInvestConfigRecord.setAddtime(new Date());
					autoInvestConfigRecord.setTender_type(1);
					autoInvestConfigRecord.setRemark("系统已自动停用该规则");
					// 停用规则
					autoInvestConfigVo.setStatus(0);
					AutoInvestConfig autoInvestConfig = new AutoInvestConfig();
					BeanUtils.copyProperties(autoInvestConfigVo, autoInvestConfig);
					baseAutoInvestConfigMapper.updateEntity(autoInvestConfig);

					// 保存日志记录
					baseAutoInvestConfigRecordMapper.insertEntity(autoInvestConfigRecord);
				}
			}
		}
		return "success";
	}

	@Transactional(rollbackFor = Throwable.class)
	public String handleFirstTenderReal(Integer userId, Integer type, ShiroUser user, String ip) throws Exception {
		String resultMsg = "success";
		if (type.intValue() == BusinessConstants.BLACK_TYPE_FIRST_DEBUS) { // 直通车下车
			FirstTenderRealCnd firstTenderRealCnd = new FirstTenderRealCnd();
			firstTenderRealCnd.setUserId(String.valueOf(userId));
			firstTenderRealCnd.setStatus("0");
			List<FirstTenderRealVo> list = firstTenderRealMapper.queryFirstTenderRealList(firstTenderRealCnd, new Page(1, Integer.MAX_VALUE));
			if (list.size() > 0) {
				for (FirstTenderRealVo firstTenderRealVo : list) {
					// 申请解锁
					resultMsg = firstTenderRealService.saveUnlockManual(firstTenderRealVo.getId(), firstTenderRealVo.getUserId());
					if ("success".equals(resultMsg)) {
						// 解锁自动审核
						resultMsg = firstTenderRealService.saveApprovedPass(String.valueOf(firstTenderRealVo.getId()), "后台自动审核", firstTenderRealVo.getUserId(), user, ip);
						if (!"success".equals(resultMsg)) {
							throw new AppException("直通车解锁失败");
						}
					} else {
						throw new AppException("直通车解锁失败");
					}
				}
			}
		}
		return resultMsg;
	}


	public String updateBlackList(BlackListVo blackListVo, ShiroUser user, String ip, Integer status) throws Exception {
		String remark = blackListVo.getUpdateRemark();
		if (blackListVo.getId() == null) {
			return "该记录不存在，请刷新页面后重试！";
		}
		blackListVo = blackListMapper.queryByIdForUpdate(blackListVo.getId());
		if (blackListVo == null || blackListVo.getId() == null) {
			return "该记录不存在，请刷新页面后重试！";
		}
		blackListVo.setStatus(status); // 失效
		blackListVo.setUpdateId(user.getUserId());
		blackListVo.setUpdateIp(ip);
		blackListVo.setUptime(new Date());
		blackListVo.setUpdateRemark(remark);
		BlackList blackList = new BlackList();
		BeanUtils.copyProperties(blackListVo, blackList);
		baseBlackListMapper.updateEntity(blackList);
		return "success";
	}
}
