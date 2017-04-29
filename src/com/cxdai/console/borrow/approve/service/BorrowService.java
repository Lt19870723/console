package com.cxdai.console.borrow.approve.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.BorrowApproved;
import com.cxdai.console.base.entity.Borrower;
import com.cxdai.console.base.entity.GuarantyOrganization;
import com.cxdai.console.base.entity.Mortgage;
import com.cxdai.console.base.mapper.BaseBorrowApprovedMapper;
import com.cxdai.console.base.mapper.BaseGuarantyOrganizationMapper;
import com.cxdai.console.base.mapper.BaseRealNameApproMapper;
import com.cxdai.console.borrow.approve.mapper.AccountUploadDocMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowAuditHistoryMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowerMapper;
import com.cxdai.console.borrow.approve.mapper.MortgageMapper;
import com.cxdai.console.borrow.approve.mapper.SalariatBorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowApprovedVo;
import com.cxdai.console.borrow.approve.vo.BorrowAuditHistoryCnd;
import com.cxdai.console.borrow.approve.vo.BorrowAuditHistoryVo;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.BorrowerVo;
import com.cxdai.console.borrow.approve.vo.CheckBorrowVo;
import com.cxdai.console.borrow.emerg.vo.AccountUploadDocCnd;
import com.cxdai.console.borrow.emerg.vo.AccountUploadDocVo;
import com.cxdai.console.borrow.emerg.vo.MortgageVo;
import com.cxdai.console.borrow.emerg.vo.SalariatBorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.entity.BorrowBusiness;
import com.cxdai.console.customer.information.entity.BorrowerUser;
import com.cxdai.console.customer.information.entity.RealNameAppro;
import com.cxdai.console.customer.information.mapper.BorrowBusinessMapper;
import com.cxdai.console.customer.information.mapper.BorrowerUserMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.BorrowBusinessVo;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.RandomGUIDUtil;
import com.cxdai.console.util.ShiroUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BorrowService {

	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private BorrowAuditHistoryMapper borrowAuditHistoryMapper;
	@Autowired
	private BorrowApprovedService borrowApprovedService;
	@Autowired
	private BaseBorrowApprovedMapper baseBorrowApprovedMapper;
	@Autowired
	private SalariatBorrowMapper salariatBorrowMapper;
	@Autowired
	private BorrowerMapper borrowerMapper;
	@Autowired
	private MortgageMapper mortgageMapper;
	@Autowired
	private BaseRealNameApproMapper baseRealNameApproMapper;
	@Autowired
	private BaseGuarantyOrganizationMapper baseGuarantyOrganizationMapper;
	@Autowired
	private AccountUploadDocMapper accountUploadDocMapper;
	@Autowired
	private BorrowBusinessMapper borrowBusinessMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private BorrowerUserMapper borrowerUserMapper;

	/**
	 * 
	 * <p>
	 * Description:根据id查询借款标<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月26日
	 * @param id
	 * @return
	 * @throws Exception
	 *             BorrowVo
	 */
	public BorrowVo selectByPrimaryKey(Integer id) throws Exception {
		return borrowMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * <p>
	 * Description:反欺诈结果集<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月24日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 *             Page
	 */
	public Page selectAntiFraudCheckBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = borrowMapper.selectAntiFraudCheckBorrowCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<CheckBorrowVo> list = borrowMapper.selectAntiFraudCheckBorrow(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:借款标审核终审结果集<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月24日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 *             Page
	 */
	public Page selectFinalCheckBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = borrowMapper.selectFinalCheckBorrowCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<CheckBorrowVo> list = borrowMapper.selectFinalCheckBorrow(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:借款标审核初审结果集<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月24日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 *             Page
	 */
	public Page selectFirstcheckBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = borrowMapper.selectFirstcheckBorrowCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<CheckBorrowVo> list = borrowMapper.selectFirstcheckBorrow(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:借款审核日志查询<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月24日
	 * @param borrowAuditHistoryCnd
	 * @param curPage
	 * @param pageSize
	 * @return Page
	 * @throws ParseException
	 */

	public Page searchPageborrowAuditHistoryList(BorrowAuditHistoryCnd borrowAuditHistoryCnd, Integer curPage, Integer pageSize)
			throws ParseException {
		Page page = new Page(curPage, pageSize);

		int totalCount = borrowAuditHistoryMapper.countBorrowAuditHistory(borrowAuditHistoryCnd);
		page.setTotalCount(totalCount);
		List<BorrowAuditHistoryVo> list = borrowAuditHistoryMapper.queryBorrowAuditHistoryList(borrowAuditHistoryCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p
	 * Description:更新专区类型<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月26日
	 * @param borrowId
	 * @param areaType
	 * @param areaChangeTime
	 * @return
	 * @throws Exception
	 *             String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String updateBorrowAreaType(int borrowId, Integer areaType, Date areaChangeTime) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		if (areaType != null && areaType.intValue() == 0) {
			areaChangeTime = null;
		}
		if (borrowVo != null && areaType.intValue() == 1) {
			if (borrowVo.getBorrowtype().intValue() != 2) {
				return "只有抵押标才能设置为新手专区";
			}
			if (borrowVo.getAccount().compareTo(new BigDecimal(10000)) == 1) {
				borrowVo.setMostAccount(new BigDecimal(10000));
			} else {
				borrowVo.setMostAccount(borrowVo.getAccount());
			}
		}
		borrowVo.setAreaType(areaType);
		borrowVo.setAreaChangeTime(areaChangeTime);
		Borrow borrow = new Borrow();
		BeanUtils.copyProperties(borrowVo, borrow);
		borrowMapper.updateByPrimaryKey(borrow);
		return "success";
	}

	/**
	 * 
	 * <p>
	 * Description:设定定时发标<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月26日
	 * @param timingBorrowTime
	 * @param borrowId
	 * @param areaType
	 * @param areaChangeTime
	 * @return
	 * @throws Exception
	 *             String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String setTimingBorrow(Date timingBorrowTime, int borrowId, Integer areaType, Date areaChangeTime,BorrowVo bv) throws Exception {
		Borrow borrow = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		// Staff staff = (Staff) SecurityUtils.getSubject().getPrincipal();
		ShiroUser shiroUser = ShiroUtils.currentUser();
		if (timingBorrowTime == null) {
			return "定时发标时间不能为空！";
		}
		BorrowVo borrowVo = borrowMapper.queryBorrowByPublishTime(timingBorrowTime);
		if (borrowVo != null && borrowVo.getId().intValue() != borrowId) {
			return "该时间点已存在一个借款标，请设置其他时间！";
		}
		// 查询借款标与权证人员关系
		BorrowBusinessVo borrowBusinessVo = borrowBusinessMapper.selectBorBusByBorrowId(borrowId);
		if (borrowBusinessVo == null || borrowBusinessVo.getUserId() == null) {
			return "权证人员与借款标未绑定！";
		}
		// 如果选择的权证人员不是其他才进行验证
		if (borrowBusinessVo.getUserId().intValue() != 0) {
			// 查询权证人员账户并锁定
			Account account = baseAccountMapper.queryByUserIdForUpdate(borrowBusinessVo.getUserId());
			if (account.getUseMoney() == null || account.getUseMoney().compareTo(BusinessConstants.BUSINESS_TENDER_MONEY) < 0) {
				return "权证人员账户可用余额小于" + BusinessConstants.BUSINESS_TENDER_MONEY;
			}
		}
		String timingBorrowTimeStr = DateUtils.format(timingBorrowTime, DateUtils.YMD_HMS);

		int current = Integer.parseInt(DateUtils.dateTime2TimeStamp(DateUtils.format(
				DateUtils.minuteOffset(DateUtils.parse(timingBorrowTimeStr, DateUtils.YMD_HMS), -5), DateUtils.YMD_HMS)));
		// 当前时间的时间戳
		int now = Integer.parseInt(DateUtils.getTime());
		if (current > now) {
			BorrowApprovedVo borrowApprovedVo = borrowApprovedService.selectByBorrowIdForUpdate(borrowId);
			borrowApprovedVo.setVerifyRemark3("定时发标");
			borrowApprovedVo.setVerifyTime3(DateUtils.getTime());
			borrowApprovedVo.setVerifyUser3(shiroUser.getUserId());

			borrow.setTimingBorrowTime(DateUtils.dateTime2TimeStamp(timingBorrowTimeStr));
			if (areaType != null && areaType.intValue() == 0) {
				areaChangeTime = null;
			}
			borrow.setAreaType(areaType);
			borrow.setAreaChangeTime(areaChangeTime);
			if (borrow != null && areaType.intValue() == 1) {
				if (borrow.getBorrowtype().intValue() != 2) {
					return "只有抵押标才能设置为新手专区";
				}
				if (borrow.getAccount().compareTo(new BigDecimal(10000)) == 1) {
					borrow.setMostAccount(new BigDecimal(10000));
				} else {
					borrow.setMostAccount(borrow.getAccount());
				}
			}
			borrow.setIsCustody(bv.getIsCustody());
			borrow.setRepayName(bv.getRepayName());
			borrow.setRepayAcct(bv.getRepayAcct());
			borrowMapper.updateByPrimaryKey(borrow);
			// 更新借款审核记录
			BorrowApproved borrowApproved = new BorrowApproved();
			BeanUtils.copyProperties(borrowApprovedVo, borrowApproved);
			baseBorrowApprovedMapper.updateEntity(borrowApproved);
			return "success";
		}
		return "必须设定当前时间5分钟之后的时间点";
	}

	/**
	 * 
	 * <p>
	 * Description:删除定时发标<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月26日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 *             String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String delTimingBorrow(int borrowId) throws Exception {
		Borrow borrow = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		if (borrow.getTimingBorrowTime() == null || borrow.getTimingBorrowTime().equals("")) {
			return "该借款标定时时间已被删除，请勿重复操作！";
		}
		borrow.setTimingBorrowTime(null);
		borrow.setAreaChangeTime(null);
		if (borrowMapper.updateByPrimaryKey(borrow) > 0) {
			return "删除定时发标成功！";
		} else {
			return "删除定时发标失败！";
		}
	}

	/**
	 * 
	 * <p>
	 * Description: 定期宝借款标查询<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 *             Page
	 */
	public Page queryListForHandFixTender(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page p = new Page();
		p.setPageNo(curPage);
		p.setPageSize(pageSize);
		int totalCount = borrowMapper.queryCountForHandFixTender(borrowCnd);
		p.setTotalCount(totalCount);
		List<BorrowVo> list = borrowMapper.queryListForHandFixTender(borrowCnd, p);
		p.setResult(list);
		return p;
	}

	/**
	 * 
	 * <p>
	 * Description:更新标<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月16日
	 * @param borrowVo
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String updateBorrowInfo(BorrowVo borrowVo) throws Exception {
		String result = "success";
		if (borrowVo.getId() == null) {
			return "借款标不存在！";
		}
		if (borrowVo.getAccount() == null) {
			return "借款金额不能为空！";
		}
		if (borrowVo.getTimeLimit() == null) {
			return "借款期限不能为空！";
		}
		if (borrowVo.getApr() == null) {
			return "利率不能为空！";
		}
		if (borrowVo.getContent() == null) {
			return "借款介绍不能为空！";
		}
		BorrowVo oldBorrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowVo.getId());
		Borrow borrow = new Borrow();
		BeanUtils.copyProperties(oldBorrowVo, borrow);
		borrow.setAccount(borrowVo.getAccount());
		borrow.setTimeLimit(borrowVo.getTimeLimit());
		borrow.setApr(borrowVo.getApr());
		borrow.setIsCustody(borrowVo.getIsCustody());
		borrow.setRepayName(borrowVo.getRepayName());
		borrow.setRepayAcct(borrowVo.getRepayAcct());
		if (oldBorrowVo.getStyle() == 4) {
			borrow.setOrder(1);
		} else {
			borrow.setOrder(borrowVo.getTimeLimit());
		}
		borrow.setContent(borrowVo.getContent());
		if (borrowMapper.updateByPrimaryKey(borrow) == 0) {
			return "修改标失败！";
		}
		return result;
	}

	public Page searchEffectiveBorrowListByBorrowCnd(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = borrowMapper.selectEffectiveBorrowListCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<BorrowVo> list = borrowMapper.selectEffectiveBorrowList(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:新增发标<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月17日
	 * @param memberVo
	 * @param salariatBorrowVo
	 * @param borrower
	 * @param mortgage
	 * @param addip
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String saveApplyBorrow(MemberVo memberVo, SalariatBorrowVo salariatBorrowVo, Borrower borrower, Mortgage mortgage, String addip)
			throws Exception {
		// 新增
		if (salariatBorrowVo.getId() == null || salariatBorrowVo.getId() == 0) {
			// 合同编号是否重复
			String newNo = salariatBorrowVo.getContractNo();
			Borrow b = borrowMapper.queryBorrowByContractNo(newNo);
			if (b != null) {
				return "借款单编号已存在，请重新输入。";
			}

			// 保存借款标基本信息
			salariatBorrowVo.setUserId(memberVo.getId());
			salariatBorrowVo.setAddtime(new Date());
			salariatBorrowVo.setAddip(addip);
			salariatBorrowVo.setUuid(RandomGUIDUtil.newGuid());
			salariatBorrowVo.setStatus(Constants.BORROW_STATUS_NEW_CODE);// 1：新标，审核中
			salariatBorrowVo.setApprstatus(Constants.BORROW_APPSTATUS_WAIT_CODE);// 0：待审核
			// 密码加密
			if (salariatBorrowVo.getBidPassword() != null && !"".equals(salariatBorrowVo.getBidPassword())) {
				salariatBorrowVo.setBidPassword(MD5.toMD5(salariatBorrowVo.getBidPassword()));
			}
			salariatBorrowVo = this.dealBorrowInfo(salariatBorrowVo);
			// 保存借款标信息
			salariatBorrowMapper.insertEntity(salariatBorrowVo);
			Integer borrowId = salariatBorrowVo.getId();

			// 保存借款人信息
			borrower.setBorrowId(borrowId);
			borrower.setAddtime(new Date());
			borrower.setAddip(addip);
			borrowerMapper.insertEntity(borrower);
			// 保存审批信息
			salariatBorrowMapper.insertBorrowApproved(borrowId);

			if (1 == salariatBorrowVo.getIsMortgage()) {
				// 保存抵押物信息
				mortgage.setBorrowId(borrowId);
				mortgage.setAddtime(new Date());
				mortgage.setAddip(addip);
				mortgageMapper.insertEntity(mortgage);
			}

			// 保存借款标与权证人员关系信息
			if (salariatBorrowVo.getBusinessUserId() == null) {
				return "请选择权证人员";
			}
			saveBorrowBusiness("add", borrowId, salariatBorrowVo.getBusinessUserId(), addip);
		} else { // 修改
			BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(salariatBorrowVo.getId());
			if (borrowVo == null) {
				return "借款记录不存在";
			}
			if (borrowVo.getStatus() == 1 && borrowVo.getApprstatus() == 0) {
				// 合同编号是否重复
				String newNo = salariatBorrowVo.getContractNo();
				Borrow b = borrowMapper.queryBorrowByContractNo(newNo);
				if (b != null && b.getId().intValue() != salariatBorrowVo.getId().intValue()) {
					return "借款单编号已存在，请重新输入。";
				}
				salariatBorrowVo = dealBorrowInfo(salariatBorrowVo);

				// 密码加密
				String newBidPwd = salariatBorrowVo.getBidPassword();
				String oldBidPwd = salariatBorrowVo.getOldBidPassword();
				if (newBidPwd != null && !"".equals(newBidPwd) && !"mimamima".equals(newBidPwd)) {
					salariatBorrowVo.setBidPassword(MD5.toMD5(salariatBorrowVo.getBidPassword()));
				} else if (newBidPwd == null || "".equals(newBidPwd)) {
					salariatBorrowVo.setBidPassword(null);
				} else {
					salariatBorrowVo.setBidPassword(oldBidPwd);
				}
				salariatBorrowMapper.updateBorrowFullInfoById(salariatBorrowVo);
			} else {
				if (salariatBorrowVo.getIsJointGuaranty() == 0) {
					salariatBorrowVo.setJointGuaranty(null);
				}
				salariatBorrowMapper.updateBorrowById(salariatBorrowVo);
			}
			borrower.setBorrowId(salariatBorrowVo.getId());
			salariatBorrowMapper.updateBorrowerByBorrowId(borrower);

			mortgage.setBorrowId(salariatBorrowVo.getId());
			salariatBorrowMapper.updateMortgageByBorrowId(mortgage);

			// 保存借款标与权证人员关系信息
			if (salariatBorrowVo.getBusinessUserId() == null) {
				return "请选择权证人员";
			}
			saveBorrowBusiness("update", salariatBorrowVo.getId(), salariatBorrowVo.getBusinessUserId(), addip);
		}
		return "success";
	}

	/**
	 * 保存借款标与权证人员信息
	 * 
	 * @author WangQianJin
	 * @throws Exception
	 * @title @param borrowBusiness
	 * @date 2015年8月17日
	 */
	private void saveBorrowBusiness(String type, Integer borrowId, Integer userId, String addip) throws Exception {
		// 保存借款标与权证人员关系信息
		BorrowBusiness borrowBusiness = new BorrowBusiness();
		if ("add".equals(type)) {
			borrowBusiness.setBorrowId(borrowId);
			borrowBusiness.setUserId(userId);
			// 判断选择的权证人员是否为其他
			if (userId.intValue() == 0) {
				borrowBusiness.setUsername("其他");
			} else {
				MemberVo member = memberService.queryMemberById(userId);
				if (member != null) {
					borrowBusiness.setUsername(member.getUsername());
				}
			}
			borrowBusiness.setAddip(addip);
			borrowBusiness.setPlatform(1);
			borrowBusinessMapper.insertBorrowBusiness(borrowBusiness);
		} else {
			borrowBusiness = borrowBusinessMapper.selectBorBusByBorrowIdForUpdate(borrowId);
			borrowBusiness.setUserId(userId);
			// 判断选择的权证人员是否为其他
			if (userId.intValue() == 0) {
				borrowBusiness.setUsername("其他");
			} else {
				MemberVo member = memberService.queryMemberById(userId);
				if (member != null) {
					borrowBusiness.setUsername(member.getUsername());
				}
			}
			borrowBusiness.setAddip(addip);
			borrowBusiness.setPlatform(1);
			borrowBusinessMapper.updateBorrowBusiness(borrowBusiness);
		}
	}

	public SalariatBorrowVo dealBorrowInfo(SalariatBorrowVo salariatBorrowVo) {
		// 处理投标最大金额
		if (salariatBorrowVo.getMostAccount() == null) {
			salariatBorrowVo.setMostAccount(BigDecimal.ZERO);
		}

		if (salariatBorrowVo.getIsGuaranty() == 1) {// 是否担保
			salariatBorrowVo.setBorrowtype(Constants.BORROW_TYPE_GUARANTEED);// 5担保标
		} else {
			salariatBorrowVo.setGuarantyOrganization(null);
			if (1 == salariatBorrowVo.getIsMortgage()) {// 是否抵押
				salariatBorrowVo.setBorrowtype(Constants.BORROW_TYPE_PLEDGE);// 2抵押标
			} else {
				salariatBorrowVo.setMortgageType(null);// 无抵押-抵押类型置空
				salariatBorrowVo.setBorrowtype(Constants.BORROW_TYPE_RECOMMEND);// 1信用标
			}
		}
		if (salariatBorrowVo.getStyle() == Constants.BORROW_STYLE_MONTH_INSTALMENTS
				|| salariatBorrowVo.getStyle() == Constants.BORROW_STYLE_MONTH_PAY_INTEREST) {// 1：等额本息/按月分期还款，2：按月付息到期还本
			salariatBorrowVo.setOrder(salariatBorrowVo.getTimeLimit());
		} else {
			salariatBorrowVo.setOrder(1);
		}
		salariatBorrowVo.setBorrowUse(salariatBorrowVo.getName());

		if (salariatBorrowVo.getIsJointGuaranty() == 0) {
			salariatBorrowVo.setJointGuaranty(null);
		}

		return salariatBorrowVo;
	}

	/**
	 * 
	 * <p>
	 * Description：查询借款标资产信息<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年7月7日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 *             MortgageVo
	 */
	public MortgageVo queryMortgageByBorrow(Integer borrowId) throws Exception {
		return mortgageMapper.getMortgageByBorrowId(borrowId);
	}

	/**
	 * <p>
	 * Description: 根据借款标id查询借款者信息<br />
	 * </p>
	 * 
	 * @author chenlu
	 * @version 0.1 2014年8月22日
	 * @param map
	 * @return
	 * @throws Exception
	 *             BorrowerVo
	 */
	public BorrowerVo queryBorrowerByBorrowId(Integer borrowId) throws Exception {
		return borrowerMapper.queryBorrowerByBorrowId(borrowId);
	}

	/**
	 * 
	 * <p>
	 * Description:初始化申请借款标信息<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月3日
	 * @param memberVo
	 * @return
	 * @throws Exception
	 *             Map<String, Object>
	 */
	public Map<String, Object> initBorrowInfo(MemberVo memberVo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		RealNameAppro approMem = baseRealNameApproMapper.queryByUserId(memberVo.getId());// 用户认证信息
		map.put("loginMemName", memberVo.getUsername());// 用户名
		map.put("loginMemGender", "0".equals(approMem.getSex()) ? '男' : '女');// 性别
		map.put("loginMemBirthDay", approMem.getBirthDay());// 生日
		map.put("loginMemNativePlace", approMem.getNativePlace());// 籍贯
		List<GuarantyOrganization> organizationOptions = baseGuarantyOrganizationMapper.getAll();// 担保机构
		map.put("organizationOptions", organizationOptions);// 籍贯
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:上传借款资料初始化<br />
	 * </p>
	 * 
	 * @author 于斌
	 * @version 0.1 2015年7月17日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	public Map<String, Object> initUpload(int borrowId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 已上传图片
		AccountUploadDocCnd cnd = new AccountUploadDocCnd();
		cnd.setBorrowId(borrowId);
		List<AccountUploadDocVo> list = accountUploadDocMapper.queryAccountUploadDocList(cnd);// 已上传图片
		map.put("accountUploadDocVoList", list);

		// 借款标
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(borrowId);
		map.put("borrowVo", borrowVo);

		// 续贷标查询之前的记录
		if (borrowVo != null && borrowVo.getPledgeType() != null && borrowVo.getPledgeType() == 2) {
			List<Borrow> beforeList = salariatBorrowMapper.getBeforeBorrow(borrowVo.getUserId(), borrowId);
			map.put("beforeList", beforeList);
		}
		return map;
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:查询权证人员信息<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月16日
	 * @return
	 * List<BorrowerUser>
	 */
	public List<BorrowerUser> selectBorrowUser(BorrowerUser borrowerUser) throws Exception {
		return borrowerUserMapper.selectBorrowUser(borrowerUser);
		
	}

}
