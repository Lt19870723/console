package com.cxdai.console.firstborrow.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.FirstBorrow;
import com.cxdai.console.base.entity.FirstBorrowAppr;
import com.cxdai.console.base.mapper.BaseFirstBorrowApprMapper;
import com.cxdai.console.base.mapper.BaseFirstBorrowMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.firstborrow.mapper.FirstBorrowApprMapper;
import com.cxdai.console.firstborrow.mapper.FirstBorrowMapper;
import com.cxdai.console.firstborrow.vo.FirstBorrowApprCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowApprVo;
import com.cxdai.console.firstborrow.vo.FirstBorrowVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:优先投标计划审核业务实现类<br />
 * </p>
 * 
 * @title FirstBorrowApprServiceImpl.java
 * @package com.cxdai.console.first.service.impl
 * @author justin.xu
 * @version 0.1 2014年7月3日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FirstBorrowApprService {
	@Autowired
	private FirstBorrowApprMapper firstBorrowApprMapper;
	@Autowired
	private BaseFirstBorrowMapper baseFirstBorrowMapper;
	@Autowired
	private BaseFirstBorrowApprMapper baseFirstBorrowApprMapper;
	@Autowired
	private FirstBorrowMapper firstBorrowMapper;

	
	public List<FirstBorrowApprVo> queryfirstBorrowApprList(FirstBorrowApprCnd firstBorrowApprCnd) throws Exception {
		return firstBorrowApprMapper.queryfirstBorrowApprList(firstBorrowApprCnd);
	}

	
	public String saveApprovedPass(FirstBorrowVo firstBorrowVo, FirstBorrowAppr firstBorrowAppr, String version) throws Exception {
		String result = "success";
		if (firstBorrowVo.getPublishTime().compareTo(new Date()) < 0) {
			return "定时开通时间已过期，请重新选定时开通时间！";
		}
		FirstBorrowVo lastfirstBorrowVo = firstBorrowMapper.getNewLatestById(firstBorrowVo.getId());
		if (lastfirstBorrowVo != null && lastfirstBorrowVo.getEndTime() != null) {
			if (firstBorrowVo.getPublishTime() == null) {
				return "请选择定时开通时间！";
			}
			if (firstBorrowVo.getPublishTime().compareTo(lastfirstBorrowVo.getEndTime()) < 0) {
				return "定时开通时间不能小于"+DateUtils.format(lastfirstBorrowVo.getEndTime(), DateUtils.YMD_HMS);
			}
		}
		// 更新优先计划状态
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
		firstBorrow.setStatus(Constants.FIRST_BORROW_STATUS_APPROVE_PASS);

		Integer updateCount = baseFirstBorrowMapper.updateEntity(firstBorrow);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("直通车数据已变更,请刷新页面或稍后重试！");
		}
		// 插入审核记录
		firstBorrowAppr.setStyle(Constants.APPROVE_STYLE_NEW_APPROVE);
		firstBorrowAppr.setStatus(Constants.APPROVE_PASS);
		firstBorrowAppr.setApprTime(new Date());
		baseFirstBorrowApprMapper.insertEntity(firstBorrowAppr);
		return result;
	}

	
	public String saveApprovedReject(FirstBorrowVo firstBorrowVo, FirstBorrowAppr firstBorrowAppr, String version) throws Exception {
		String result = "success";
		// 更新优先计划状态
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
		firstBorrow.setStatus(Constants.FIRST_BORROW_STATUS_APPROVE_REJECT);

		Integer updateCount = baseFirstBorrowMapper.updateEntity(firstBorrow);
		if (null == updateCount || updateCount == 0) {
			throw new AppException("直通车数据已变更,请刷新页面或稍后重试！");
		}
		// 插入审核记录
		firstBorrowAppr.setStyle(Constants.APPROVE_STYLE_NEW_APPROVE);
		firstBorrowAppr.setStatus(Constants.APPROVE_REJECT);
		firstBorrowAppr.setApprTime(new Date());
		baseFirstBorrowApprMapper.insertEntity(firstBorrowAppr);
		return result;
	}

	
	public void saveTimingOpen(FirstBorrowAppr firstBorrowAppr, Date publishTime, String version) throws Exception {
		// 更新优先计划状态
		FirstBorrow firstBorrow = baseFirstBorrowMapper.queryById(firstBorrowAppr.getFirstBorrowId());
		firstBorrow.setId(firstBorrowAppr.getFirstBorrowId());
		firstBorrow.setStatus(Constants.FIRST_BORROW_STATUS_APPROVE_PASS);
		firstBorrow.setPublishTime(publishTime);
		firstBorrow.setSelfVersion(version);
		firstBorrow.setVersion(UUID.randomUUID().toString().replace("-", ""));
		if (firstBorrow.getValidTime() != null && firstBorrow.getPublishTime() != null) {
			firstBorrow.setEndTime(DateUtils.minuteOffset(firstBorrow.getPublishTime(), firstBorrow.getValidTime()));
		}
		Integer updateCount = baseFirstBorrowMapper.updateEntity(firstBorrow);

		if (null == updateCount || updateCount == 0) {
			throw new AppException("直通车数据已变更,请刷新页面或稍后重试！");
		}
		firstBorrowAppr.setStyle(Constants.APPROVE_STYLE_NEW_APPROVE);
		firstBorrowAppr.setStatus(Constants.APPROVE_PASS);
		firstBorrowAppr.setRemark("定时开通");
		firstBorrowAppr.setApprTime(new Date());
		baseFirstBorrowApprMapper.insertEntity(firstBorrowAppr);
	}
}
